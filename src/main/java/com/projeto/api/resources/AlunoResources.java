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

import com.projeto.api.domain.Aluno;
import com.projeto.api.event.RecursoCriadoEvent;
import com.projeto.api.repository.filter.AlunoFilter;
import com.projeto.api.service.AlunoService;

@RestController
@RequestMapping("/alunos")
@CrossOrigin("*")
public class AlunoResources {
	@Autowired
	private AlunoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Aluno> ListarTodos(){
		return this.service.Listar();
	}	
	
	@GetMapping(params = "resumo")
	public Page<Aluno> Resumir(AlunoFilter filtro, Pageable page) {
		return this.service.Filtrando(filtro, page);
	}
	
	@PostMapping
	public ResponseEntity<Aluno> Salvar(@Valid @RequestBody Aluno aluno, HttpServletResponse resposta){
		Aluno salvo = this.service.Criar(aluno);
		this.publisher.publishEvent(new RecursoCriadoEvent(this, resposta, salvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}
	
//	@PostMapping("/{imagem}")
//	public ResponseEntity<Imagem> SalvarImagem(@RequestBody Imagem imagem, HttpServletResponse resposta){
//		Imagem salvo = this.service.GravarImagem(imagem, imagem.getCodigoaluno());
//		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
//	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void Remover(@PathVariable Long codigo) {
		this.service.Deletar(codigo);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Aluno> PorId(@PathVariable Long codigo){
		Aluno salvo = this.service.BuscarPorId(codigo);
		return ResponseEntity.ok(salvo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Aluno> Atualizar(@PathVariable Long codigo, @Valid @RequestBody Aluno aluno){
		Aluno salvo = this.service.Atualizar(codigo, aluno);
		return ResponseEntity.ok(salvo);
	}
}