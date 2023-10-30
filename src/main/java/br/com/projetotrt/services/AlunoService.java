package br.com.projetotrt.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotrt.exceptions.ResourceNotFoundException;
import br.com.projetotrt.models.Aluno;
import br.com.projetotrt.repositories.IAlunoRepository;

@Service
public class AlunoService {
	
	private Logger logger = Logger.getLogger(AlunoService.class.getName());
	
	@Autowired //lembrar pra que serve
	IAlunoRepository repository;
	
	public List<Aluno> findAll(){
		logger.info("Buscando todos os alunos...");
		return repository.findAll();
	}
	
	public Aluno findById(Long id) {
		logger.info("Buscando o aluno...");
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sem servico para esse ID."));
	}
	
	public Aluno create(Aluno aluno) {
		logger.info("Criando o aluno...");
		return repository.save(aluno);
	}
	
	public Aluno update(Aluno aluno) {
		logger.info("Atualizado dados do aluno...");
		
		var entity = repository.findById(aluno.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Sem servico para esse ID."));
		
		entity.setNome(aluno.getNome());
		entity.setDataDeNascimento(aluno.getDataDeNascimento());
		entity.setCpf(aluno.getCpf());
		
		return repository.save(aluno);
	}
	
	public void delete(Long id) {
		logger.info("Deletando dados do aluno...");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sem servico para esse ID."));
		repository.delete(entity);
	}
}





