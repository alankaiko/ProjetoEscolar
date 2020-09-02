package com.projeto.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.api.domain.Sala;
import com.projeto.api.event.RecursoCriadoEvent;
import com.projeto.api.repository.filter.SalaFilter;
import com.projeto.api.service.SalaService;

@RestController
@RequestMapping("/salas")
@CrossOrigin("*")
public class SalaResources {
	@Autowired
	private SalaService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Sala> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Sala> Resumir(SalaFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@PostMapping
	public ResponseEntity<Sala> Salvar(@Valid @RequestBody Sala sala, HttpServletResponse resposta){
		Sala salvo = this.service.Criar(sala);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Sala> PorId(@PathVariable Long codigo){
		Sala salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Sala> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Sala sala){
		Sala salvo = this.service.Atualizar(codigo, sala);
		return ResponseEntity.ok(salvo);
	}
}