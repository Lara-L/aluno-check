package br.com.projetotrt.template;

import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.projetotrt.models.Aluno;
@Component
public class AlunoRestClient {

	private RestTemplate restTemplate = new RestTemplate(); //- tava retornando null pq não tinha iniciado a variável
	public String BASE_URL = "https://654a1de1e182221f8d528774.mockapi.io/api/v1/aluno";

	public Optional<Aluno> getById(Long id){
		return Optional.ofNullable(restTemplate.getForObject(BASE_URL + "/" + id, Aluno.class));
	}


	public ResponseEntity<List<Aluno>> getAll(){
		return restTemplate.exchange(BASE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Aluno>>() {});
	}
	
	public ResponseEntity<Aluno> create(Aluno aluno) {
	    return restTemplate.postForEntity(BASE_URL, aluno, Aluno.class);
	}

	public void delete(Long id) {
	    restTemplate.delete(BASE_URL + "/" + id);
	}
}
