package com.primercrud.uabc.Primercrud.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@DiscriminatorColumn(name= "TIPO")
@DiscriminatorValue(value="true")
@javax.persistence.SequenceGenerator(name = "PERSONA_SEQUENCE", sequenceName = "PERSONA_SEQ", initialValue = 1, allocationSize = 1)
//@Table(name = "PERSONA")
public abstract class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONA_SEQUENCE")
    @Column(name = "MATRICULA")
    private Integer matricula;
    @Column(name = "type_persona")
    private String typePersona;
    @Column(name = "first_name")
    private String fisrtName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "COURSE")
    private String course;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "SALARIO")
    private Double salario;

    public Persona() {
    }

    public Persona(String fisrtName, String lastName, Date birthDate) {
        this.fisrtName = fisrtName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

}