package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.model.Disciplina;
import itiroBeto.com.github.SpringBoot.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;

    public void create (Disciplina disciplina){
        disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> FindAll(){
        return disciplinaRepository.findAll();
    }

    public Optional<Disciplina> findById(Long id){
        return disciplinaRepository.findById(id);
    }


    public List<Disciplina> findByIdProfessor(Long professorId){
        return disciplinaRepository.findByProfessorId(professorId);
    }

    public void updated(Long id, Disciplina disciplina){
        Optional<Disciplina> diciplinaFromDb = findById(id);

        if (diciplinaFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diciplina não encontrada");
        }

        Disciplina disciplinaUpDated = diciplinaFromDb.get();

        disciplinaUpDated.setName(disciplina.getName());
        disciplinaUpDated.setProfessor(disciplina.getProfessor());

        disciplinaRepository.save(disciplinaUpDated);
    }



    public void deleteById(Long id){
        disciplinaRepository.deleteById(id);
    }
}
