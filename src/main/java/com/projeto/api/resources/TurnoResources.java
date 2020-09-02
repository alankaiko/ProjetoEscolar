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

import com.projeto.api.domain.Turno;
import com.projeto.api.event.RecursoCriadoEvent;
import com.projeto.api.repository.filter.TurnoFilter;
import com.projeto.api.service.TurnoService;


@RestController
@RequestMapping("/turnos")
@CrossOrigin("*")
public class TurnoResources {
	@Autowired
	private TurnoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Turno> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Turno> Resumir(TurnoFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@PostMapping
	public ResponseEntity<Turno> Salvar(@Valid @RequestBody Turno turno, HttpServletResponse resposta){
		Turno salvo = this.service.Criar(turno);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Turno> PorId(@PathVariable Long codigo){
		Turno salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Turno> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Turno turno){
		Turno salvo = this.service.Atualizar(codigo, turno);
		return ResponseEntity.ok(salvo);
	}
}