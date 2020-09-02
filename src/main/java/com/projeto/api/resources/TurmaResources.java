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

import com.projeto.api.domain.Turma;
import com.projeto.api.event.RecursoCriadoEvent;
import com.projeto.api.repository.filter.TurmaFilter;
import com.projeto.api.service.TurmaService;

@RestController
@RequestMapping("/turmas")
@CrossOrigin("*")
public class TurmaResources {
	@Autowired
	private TurmaService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Turma> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Turma> Resumir(TurmaFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@PostMapping
	public ResponseEntity<Turma> Salvar(@Valid @RequestBody Turma turma, HttpServletResponse resposta){
		Turma salvo = this.service.Criar(turma);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Turma> PorId(@PathVariable Long codigo){
		Turma salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Turma> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Turma turma){
		Turma salvo = this.service.Atualizar(codigo, turma);
		return ResponseEntity.ok(salvo);
	}
}