package itiroBeto.com.github.SpringBoot.model;

import itiroBeto.com.github.SpringBoot.enums.CursotypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * NÃO FUNCIONA
 *
 *@AllArgsConstructor
 * @NoArgsConstructo
 */

@Data
@Entity
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CursotypeEnum type;

    private BigDecimal monthlyCost;

    public Curso() {
    }

    public Curso(Long id, String name, CursotypeEnum type, BigDecimal monthlyCost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.monthlyCost = monthlyCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CursotypeEnum getType() {
        return type;
    }

    public void setType(CursotypeEnum type) {
        this.type = type;
    }

    public BigDecimal getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(BigDecimal monthlyCost) {
        this.monthlyCost = monthlyCost;
    }
}
