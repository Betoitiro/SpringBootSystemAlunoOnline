package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.model.Diciplina;
import itiroBeto.com.github.SpringBoot.repository.DiciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DiciplinaService {

    @Autowired
    DiciplinaRepository diciplinaRepository;

    public void create (Diciplina diciplina){
        diciplinaRepository.save(diciplina);
    }

    public List<Diciplina> FindAll(){
        return diciplinaRepository.findAll();
    }

    public Optional<Diciplina> findById(Long id){
        return diciplinaRepository.findById(id);
    }


    public List<Diciplina> findByIdProfessor(Long professorId){
        return diciplinaRepository.findByProfessorId(professorId);
    }

    public void updated(Long id, Diciplina diciplina){
        Optional<Diciplina> diciplinaFromDb = findById(id);

        if (diciplinaFromDb.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Diciplina não encontrada");
        }

        Diciplina diciplinaUpDated = diciplinaFromDb.get();

        diciplinaUpDated.setName(diciplina.getName());
        diciplinaUpDated.setProfessor(diciplina.getProfessor());

        diciplinaRepository.save(diciplinaUpDated);
    }

    public void deleteById(Long id){
        diciplinaRepository.deleteById(id);
    }
}
