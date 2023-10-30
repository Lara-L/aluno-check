package br.com.projetotrt.template;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.projetotrt.models.Aluno;

public class AlunoRestClient {
	private RestTemplate restTemplate;
	private final String BASE_URL = "http://localhost:8080/aluno";
	
	public ResponseEntity<Aluno> getAlunoById(Long id){
		 return restTemplate.getForEntity(BASE_URL + "/" + id, Aluno.class);
	}
	
	public ResponseEntity<List<Aluno>> getAllAluno(){
		return restTemplate.exchange(BASE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Aluno>>() {});
	}
	
	public ResponseEntity<Aluno> createAluno(Aluno aluno) {
	    return restTemplate.postForEntity(BASE_URL, aluno, Aluno.class);
	}

	public void deleteAluno(Long id) {
	    restTemplate.delete(BASE_URL + "/" + id);
	}
}
