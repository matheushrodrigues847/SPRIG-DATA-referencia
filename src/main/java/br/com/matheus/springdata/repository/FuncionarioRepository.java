package br.com.matheus.springdata.repository;

import br.com.matheus.springdata.orm.Funcionario;
import br.com.matheus.springdata.orm.FuncionarioDTO;
import br.com.matheus.springdata.orm.FuncionarioProjecao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//jpa repository é para trabalhar com arquivos em lote
//PagingAndSortingRepository, colocando só isso na extensao é possivel fazer a paginacao
//paginacao é importante para que as resposta do back end nao demorem para o cliente
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {
    //findBy + nome do atributo
    //nao é preciso escrever sql ou jpql, o spring faz tudo

    //derived querie
    List<Funcionario> findByNome(String nome);


    //consulta com jpql
    @Query("select f from Funcionario f where f.nome = :nome and f.dataContratacao > :data")
    List<Funcionario> findNomeDataContratacaoComSalarioMaior(String nome, LocalDate data);

    @Query("select f from Funcionario f join f.unidades u where u.descricao = :descricao")
    List<Funcionario> findByUnidadesDescricao(String descricao);


    //native query
    @Query(value = "select * from funcionarios f where f.data_contratacao > :data",
    nativeQuery = true)
    List<Funcionario> findByDataDeContratacaoMaior(LocalDate data);

    @Query("select f from Funcionario f join f.cargo")
    Page<Funcionario> findByJoinFetch(Pageable pageable);


    //para projecoes usar native query
//    @Query(value = "select f.id, f.nome, f.cpf from funcionarios f", nativeQuery = true)
//    List<FuncionarioProjecao> findIdNomeCpf();

    //Para projecao com uma class usar select new do jpa
    @Query("select new br.com.matheus.springdata.orm.FuncionarioDTO(f.id, f.nome, f.cpf) from Funcionario f")
    List<FuncionarioDTO> findIdNomeCpf();
}
