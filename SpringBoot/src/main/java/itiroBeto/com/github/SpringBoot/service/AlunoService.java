package itiroBeto.com.github.SpringBoot.service;

import  itiroBeto.com.github.SpringBoot.model.Aluno;
import  itiroBeto.com.github.SpringBoot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class  AlunoService {

    //injetando!
    @Autowired
    //criando uma variavel com alunorepository
            AlunoRepository alunoRepository;
    //tudo que estiver dentro de alunorepository vai vir aqui pra denro


    public void create(Aluno aluno){
        alunoRepository.save(aluno);
    }

    public List<Aluno> findAll(){
        return alunoRepository.findAll();
    }

    public Optional<Aluno> findById(long id){
        return alunoRepository.findById(id);
    }

    public void update(Long id, Aluno aluno){
        Optional<Aluno> alunoFromDb = findById(id);

        //isEmpty = esta vazio
        if (alunoFromDb.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado no banco de dados");
        }

        Aluno alunoUpdated = alunoFromDb.get();

        alunoUpdated.setName(aluno.getName());
        alunoUpdated.setEmail(aluno.getEmail());
        alunoUpdated.setCourse(aluno.getCourse());

        alunoRepository.save(alunoUpdated);
    }

    public void deleteById(Long id){
        alunoRepository.deleteById(id);
    }
}
