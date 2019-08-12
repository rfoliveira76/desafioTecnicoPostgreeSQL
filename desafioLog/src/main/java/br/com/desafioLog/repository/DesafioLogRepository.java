package br.com.desafioLog.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.com.desafioLog.model.Arquivo;

public interface DesafioLogRepository extends CrudRepository<Arquivo, Long> {
	List<Arquivo> findByFiltro(String name);
}
