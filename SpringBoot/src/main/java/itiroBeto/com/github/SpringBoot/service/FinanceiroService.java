package itiroBeto.com.github.SpringBoot.service;
import itiroBeto.com.github.SpringBoot.model.Fatura;
import itiroBeto.com.github.SpringBoot.model.FinanceiroAluno;
import itiroBeto.com.github.SpringBoot.repository.FaturaRepository;
import itiroBeto.com.github.SpringBoot.repository.FinanceiroAlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

@Service
public class FinanceiroService {
    private static final Integer QUANTITY_OF_DAYS_BEFORE_GENERATE = 10;

    private static final Logger logger = LoggerFactory.getLogger(FinanceiroService.class);

    @Autowired
    FinanceiroAlunoRepository financeiroAlunoRepository;
    @Autowired
    FaturaRepository faturaRepository;


    // cron a cada minuto:
    // @Scheduled(cron = "0 * * * * *")
    // cron de meia noite:
    @Scheduled(cron = "0 0 0 * * ?")
    public void faturaGeneration() {
        logger.info("Iniciando a geração de faturas...");

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime generationDeadline = currentDate.plusDays(QUANTITY_OF_DAYS_BEFORE_GENERATE);

        //Buscar todos os registros do FinanceiroAluno
        List<FinanceiroAluno> financeiroAlunos = financeiroAlunoRepository.findAll();

        for (FinanceiroAluno financeiroAluno : financeiroAlunos) {
            Integer dueDay = financeiroAluno.getDueDate();

            if (dueDay != null) {
                // Calcular a data de vencimento do mês atual
                LocalDate dueDateCurrentMonth = calculateDueDate(dueDay, currentDate.getYear(), currentDate.getMonthValue());

                // Se a data de vencimento do mês atual já passou, calcular a data de vencimento do próximo mês
                if (dueDateCurrentMonth.isBefore(currentDate.toLocalDate())) {
                    dueDateCurrentMonth = calculateDueDate(dueDay, currentDate.getYear(), currentDate.getMonthValue() + 1);
                }

                // Verificar se falta 10 dias ou menos para a data de vencimento
                if (dueDateCurrentMonth != null && (dueDateCurrentMonth.isBefore(generationDeadline.toLocalDate()) || dueDateCurrentMonth.isEqual(generationDeadline.toLocalDate()))) {
                    // Verificar se já existe uma fatura para este aluno e data de vencimento
                    if (faturaRepository.existsByStudentFinancialAndDueDate(financeiroAluno, dueDateCurrentMonth.atTime(LocalTime.MIDNIGHT))) {
                        // logger.info("Fatura já existe para o aluno: {} com data de vencimento: {}", financeiroAluno.getId(), dueDateCurrentMonth);
                        continue;
                    }

                    logger.info("Gerando fatura para o aluno: {}", financeiroAluno.getId());

                    // Criar uma nova fatura para o aluno
                    Fatura fatura = new Fatura();
                    fatura.setStudentFinancial(financeiroAluno);
                    fatura.setDueDate(dueDateCurrentMonth.atTime(LocalTime.MIDNIGHT));
                    fatura.setCreateAt(currentDate);

                    // Salvar a fatura no repositório
                    faturaRepository.save(fatura);

                    logger.info("Fatura gerada para o aluno: {} com data de vencimento: {}", financeiroAluno.getId(), dueDateCurrentMonth);

                }
            }

            logger.info("Geração de faturas concluída.");
        }

    }

    private LocalDate calculateDueDate(Integer dueDay, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);

        // Verificar se o dia de vencimento é válido para o mês
        int dayOfMonth = Math.min(dueDay, yearMonth.lengthOfMonth());

        return LocalDate.of(year, month, dayOfMonth);
    }
}
