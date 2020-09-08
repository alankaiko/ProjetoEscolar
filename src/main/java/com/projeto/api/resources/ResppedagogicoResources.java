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

import com.projeto.api.domain.Resppedagogico;
import com.projeto.api.event.RecursoCriadoEvent;
import com.projeto.api.repository.filter.ResppedagogicoFilter;
import com.projeto.api.service.ResppedagogicoService;

@RestController
@RequestMapping("/resppedagogicos")
@CrossOrigin("*")
public class ResppedagogicoResources {
	@Autowired
	private ResppedagogicoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Resppedagogico> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Resppedagogico> Resumir(ResppedagogicoFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@PostMapping
	public ResponseEntity<Resppedagogico> Salvar(@Valid @RequestBody Resppedagogico resp, HttpServletResponse resposta){
		Resppedagogico salvo = this.service.Criar(resp);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Resppedagogico> PorId(@PathVariable Long codigo){
		Resppedagogico salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Resppedagogico> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Resppedagogico resp){
		Resppedagogico salvo = this.service.Atualizar(codigo, resp);
		return ResponseEntity.ok(salvo);
	}
}