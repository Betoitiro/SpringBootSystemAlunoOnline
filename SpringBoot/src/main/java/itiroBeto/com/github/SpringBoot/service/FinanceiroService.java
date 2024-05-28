package itiroBeto.com.github.SpringBoot.service;

import itiroBeto.com.github.SpringBoot.repository.FinanceiroAlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FinanceiroService {
    private static final Integer QUANTITY_OF_DAYS_BEFORE_GENERATE = 10;

    private static final Logger logger = LoggerFactory.getLogger(FinanceiroService.class);

    @Autowired
    FinanceiroAlunoRepository financeiroAlunoRepository;

    @Scheduled(cron = "0 0 0 0 * * ?")
    public void faturaGeneration(){
        logger.info("iniciando a geração de faturas...");

        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime generationDeadLine = currentDate.plusDays(QUANTITY_OF_DAYS_BEFORE_GENERATE);

        
    }
}
