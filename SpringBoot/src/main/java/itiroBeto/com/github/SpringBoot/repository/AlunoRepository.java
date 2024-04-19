package itiroBeto.com.github.SpringBoot.repository;

import  itiroBeto.com.github.SpringBoot.model.Aluno;
import itiroBeto.com.github.SpringBoot.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
//Aqui so tem que ter uma classe do java "JpaRepository" para autenticar no application.properties (liga com o postgres)

}

