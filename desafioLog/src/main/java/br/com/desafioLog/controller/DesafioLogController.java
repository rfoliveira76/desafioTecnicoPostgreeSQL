package br.com.desafioLog.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.desafioLog.model.Arquivo;
import br.com.desafioLog.repository.DesafioLogRepository;

@RestController
@RequestMapping("/api")
public class DesafioLogController {
	
	@Autowired
	DesafioLogRepository repository;
	
	@RequestMapping(value = "/find{filtro}", method = RequestMethod.GET)
	List<Arquivo> findByFiltro(@PathVariable("filtro") String filtro) {
		
		return repository.findByFiltro(filtro);
		
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public List<Arquivo> getfindAll() {
	 
	    List<Arquivo> todosArquitos = new ArrayList<>();
	    repository.findAll().forEach(todosArquitos::add);
	 
	    return todosArquitos;
	  }
	

	@PostMapping(value = "/save")
	public ResponseEntity<String> upload(@RequestParam("arquivo") MultipartFile arquivo) throws Exception {
		
		Arquivo novoArquivo = new Arquivo();
		novoArquivo.setNome(arquivo.getName());
		novoArquivo.setDados(arquivo.getContentType());

		repository.save(novoArquivo);
		
		return new ResponseEntity<String>("Arquivo " + arquivo.getName() + "incluído", HttpStatus.OK);
		
	}
	
	@DeleteMapping("arquivo/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") long id) {

		repository.deleteById(id);

		return new ResponseEntity<>("Arquivo apagado com sucesso", HttpStatus.OK);
	}

}
