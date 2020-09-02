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

import com.projeto.api.domain.Cartao;
import com.projeto.api.event.RecursoCriadoEvent;
import com.projeto.api.repository.filter.CartaoFilter;
import com.projeto.api.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
@CrossOrigin("*")
public class CartaoResources {
	@Autowired
	private CartaoService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Cartao> ListarTodos() {
		return this.service.Listar();
	}
	
	@GetMapping(params = "resumo")
	public Page<Cartao> Resumir(CartaoFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@GetMapping("/barras/{codigobarras}")
	public ResponseEntity<Cartao> BuscarCodBarras(@PathVariable String codigobarras) {
		Cartao cartao = this.service.BuscarPorCodigoBarras(codigobarras);
		return ResponseEntity.ok(cartao);
	}

	@PostMapping
	public ResponseEntity<Cartao> Salvar(@Valid @RequestBody Cartao cartao, HttpServletResponse resposta) {
		Cartao salvo = this.service.Criar(cartao);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Cartao> PorId(@PathVariable Long codigo) {
		Cartao salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Cartao> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Cartao cartao) {
		Cartao salvo = this.service.Atualizar(codigo, cartao);
		return ResponseEntity.ok(salvo);
	}
}