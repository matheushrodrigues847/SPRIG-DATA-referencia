package br.com.matheus.springdata.repository;

import br.com.matheus.springdata.orm.Unidade;
import org.springframework.data.repository.CrudRepository;

public interface UnidadeRepository extends CrudRepository<Unidade, Integer> {
}
