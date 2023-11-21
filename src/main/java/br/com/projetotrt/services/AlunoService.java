package br.com.projetotrt.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import br.com.projetotrt.template.AlunoRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projetotrt.exceptions.ResourceNotFoundException;
import br.com.projetotrt.models.Aluno;
import br.com.projetotrt.repositories.IAlunoRepository;
import org.springframework.web.client.RestTemplate;

@Service
public class AlunoService {
	
	private Logger logger = Logger.getLogger(AlunoService.class.getName());
	
	@Autowired //lembrar pra que serve
	IAlunoRepository alunoRepository;

	@Autowired
	AlunoRestClient alunoRestClient;
	
	public List<Aluno> findAll(){
		logger.info("Buscando todos os alunos...");
		return alunoRepository.findAll();
	}

	public Aluno findById(Long id) {
		logger.info("Buscando o aluno...");
		return alunoRepository.findById(id).or(() -> alunoRestClient.getById(id)).orElseThrow(() -> new ResourceNotFoundException("Sem servico para esse ID."));
	}

	public Aluno create(Aluno aluno) {
		logger.info("Criando o aluno...");
		return alunoRepository.save(aluno);
	}
	
	public Aluno update(Aluno aluno) {
		logger.info("Atualizado dados do aluno...");
		
		var entity = alunoRepository.findById(aluno.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Sem servico para esse ID."));
		
		entity.setNome(aluno.getNome());
		entity.setDataDeNascimento(aluno.getDataDeNascimento());
		entity.setCpf(aluno.getCpf());
		
		return alunoRepository.save(aluno);
	}
	
	public void delete(Long id) {
		logger.info("Deletando dados do aluno...");
		
		var entity = alunoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sem servico para esse ID."));
		alunoRepository.delete(entity);
	}
}





