package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.dtos.CriarAlunoRequest;
import itiroBeto.com.github.SpringBoot.enums.FinanceiroStatusEnum;
import  itiroBeto.com.github.SpringBoot.model.Aluno;
import itiroBeto.com.github.SpringBoot.model.Curso;
import itiroBeto.com.github.SpringBoot.model.FinanceiroAluno;
import  itiroBeto.com.github.SpringBoot.repository.AlunoRepository;
import itiroBeto.com.github.SpringBoot.repository.CursoResitory;
import itiroBeto.com.github.SpringBoot.repository.FinanceiroAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class  AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    FinanceiroAlunoRepository financeiroAlunoRepository;

    @Autowired
    CursoResitory cursoResitory;

    public void create(CriarAlunoRequest criarAlunoRequest){
        Curso curso = cursoResitory.findById(criarAlunoRequest.getCourseId())
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"curso não encontrado"));

        Aluno aLunoSaved = alunoRepository.save(
                new Aluno(
                        null,
                        criarAlunoRequest.getName(),
                        criarAlunoRequest.getEmail(),
                        curso
                )
        );

        createFinanceiroInformation(aLunoSaved, criarAlunoRequest);

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

        alunoRepository.save(alunoUpdated);
    }

    public void deleteById(Long id){
        alunoRepository.deleteById(id);
    }

    public void createFinanceiroInformation(Aluno aluno, CriarAlunoRequest criarAlunoRequest){
        FinanceiroAluno financeiroAluno = new FinanceiroAluno(
                null,
                aluno,
                criarAlunoRequest.getDiscount(),
                criarAlunoRequest.getDueDate(),
                //como o aluno acaba de ser criado, o seu status fica em dia
                FinanceiroStatusEnum.EM_DIA
        );
        financeiroAlunoRepository.save(financeiroAluno);
    }
}
