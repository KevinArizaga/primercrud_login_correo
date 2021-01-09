package com.primercrud.uabc.Primercrud.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Profesor extends Persona {

    public Profesor() {
    }

    public Profesor(String fisrtName, String lastName, Date birthDate, String course, Double salario) {
        super(fisrtName, lastName, birthDate);
        super.setCourse(course);
        super.setSalario(salario);
        super.setTypePersona("Profesor");
    }
}

