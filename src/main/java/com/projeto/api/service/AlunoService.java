package com.projeto.api.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.projeto.api.domain.Aluno;
import com.projeto.api.domain.Imagem;
import com.projeto.api.repository.AlunoRepository;
import com.projeto.api.repository.filter.AlunoFilter;
import com.projeto.api.utils.ConverterParaJpeg;


@Service
public class AlunoService {
	@Autowired
	private AlunoRepository dao;
	
	@Autowired
	private ImagemService serviceimagem;
	
	public List<Aluno> Listar() {
		return this.dao.findAll();
	}
	
	public Page<Aluno> Filtrando(AlunoFilter filtro, Pageable page){
		try {
			return this.dao.Filtrando(filtro, page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}


	public Aluno Criar(Aluno aluno) {
		try {
			Aluno salvo =  this.dao.save(aluno);
			if(aluno.getImagem() != null)
				this.GravarImagem(aluno.getImagem(), salvo.getCodigo());	
			
			return salvo;
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Criar------------------ de AlunoService");
			e.printStackTrace();
			return null;
		}		
	}

	public Aluno BuscarPorId(Long id) {
		Optional<Aluno> aluno = this.dao.findById(id);

		if (aluno.get() == null)
			throw new EmptyResultDataAccessException(1);

		return aluno.get();
	}
	
	
	public void Deletar(Long id) {
		try {
			this.dao.deleteById(id);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de AlunoService");
			e.printStackTrace();
		}
	}

	public void Deletar(Aluno aluno) {
		try {
			this.dao.delete(aluno);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Deletar------------------ de AlunoService");
			e.printStackTrace();
		}
	}

	public Aluno Atualizar(Long id, Aluno aluno) {
		try {
			Aluno salvo = this.BuscarPorId(id);
			if(aluno.getImagem() != null)
				this.GravarImagem(aluno.getImagem(), salvo.getCodigo());	
			
			BeanUtils.copyProperties(aluno, salvo, "codigo");
			return this.Criar(salvo);
		} catch (Exception e) {
			System.out.println("Erro ao executar o metodo Atualizar------------------ de AlunoService");
			e.printStackTrace();
			return null;
		}		
	}
	
	
	public void DeletarImagens(Imagem img, Long codigoatendimento) {
		String caminho = "./arquivo/imagens";
		
		String montarnome = caminho + "/" + codigoatendimento + "/" + img.getNomeimagem() + img.getExtensao();
		File file = new File(montarnome);
		if(file.exists()) {
			file.delete();
		}			
	}
	
	public Imagem GravarImagem(Imagem img, Long codigoatendimento) {
		ConverterParaJpeg converter = new ConverterParaJpeg();
		String caminho = "./imagens";
		this.VerificaSePastaExiste(caminho + "/" + codigoatendimento);

		String montarnome = caminho + "/" + codigoatendimento + "/" + img.getNomeimagem() + img.getExtensao();
		img.setCaminho(montarnome);
		this.serviceimagem.Atualizar(img.getCodigo(), img);
		
		if(img.getImagem() != null)
			converter.CriaImagemNaPasta(montarnome, img.getImagem());
		
		return img;
	}
	
	public Imagem RecuperarImagem(Imagem img) {
    	try {    		
			byte[] bytes = null;
			InputStream imagem = new FileInputStream(img.getCaminho());
			bytes = StreamUtils.copyToByteArray(imagem);
			img.setImagem(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}   
    	
        return img;
	}
	
	private void VerificaSePastaExiste(String  caminho) {
  		File file = new File(caminho);
  		
  		if(!file.exists()) {
  			file.mkdirs();
  			//file.setReadable(Boolean.TRUE, Boolean.TRUE);
  			//file.setWritable(Boolean.FALSE, Boolean.TRUE);
  			//file.setExecutable(Boolean.FALSE, Boolean.TRUE);
  		}
  	}
	
	public byte[] BuscarImagem(Long codigo){
    	Imagem img = this.serviceimagem.BuscarPorId(codigo);
    	
    	byte[] bytes = null;
    	try {
    		InputStream imagem = new FileInputStream(img.getCaminho());
    		bytes = StreamUtils.copyToByteArray(imagem);
		} catch (Exception e) {
			e.printStackTrace();
		}                
        return bytes;
    }
	
	public String BuscarImagemString(Long codigo){
    	Imagem img = this.serviceimagem.BuscarPorId(codigo);
    	String encodedString = "";
    	
    	if(img.getCaminho() == null)
    		return encodedString;
    				
    	byte[] bytes = null;
    	try {
    		InputStream imagem = new FileInputStream(img.getCaminho());
    		bytes = StreamUtils.copyToByteArray(imagem);

            Base64 base64 = new Base64();
            encodedString = new String(base64.encode(bytes));
            encodedString = "data:image/jpeg;base64," + encodedString;
		} catch (Exception e) {
			// nem precisa de tratativa, se nao encontrar nao faz nada e ja esta sendo tratado la no If
		}                
        return encodedString;
    }
}






