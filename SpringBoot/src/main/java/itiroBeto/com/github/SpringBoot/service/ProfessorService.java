package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.model.MatriculaAluno;
import itiroBeto.com.github.SpringBoot.model.Professor;
import itiroBeto.com.github.SpringBoot.repository.MatriculaAlunoRepository;
import itiroBeto.com.github.SpringBoot.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public void createProfessor(Professor professor) {
        professorRepository.save(professor);
    }

    public List<Professor> findAllProfessors() {
        return professorRepository.findAll();
    }

    public Optional<Professor> findProfessorById(Long id) {
        return professorRepository.findById(id);
    }

    public void updateProfessor(Long id, Professor professor) {
        Optional<Professor> professorFromDb = findProfessorById(id);

        if (professorFromDb.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Professor não encontrado no banco de dados");
        }

        Professor professorUpdated = professorFromDb.get();
        professorUpdated.setName(professor.getName());
        professorUpdated.setEmail(professor.getEmail());

        professorRepository.save(professorUpdated);
    }

    public void deleteProfessorById(Long id) {
        professorRepository.deleteById(id);
    }

}
