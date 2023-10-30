package br.com.projetotrt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotrt.models.Aluno;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long>{

}
