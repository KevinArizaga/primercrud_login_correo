package com.primercrud.uabc.Primercrud.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.AbstractMap;

@Entity
@Data
public class Intendente extends Persona {


    public Intendente() {
    }

    public Intendente(String fisrtName, String lastName, Date birthDate, Double salario) {
        super(fisrtName, lastName, birthDate);
        super.setSalario(salario);
        super.setTypePersona("Intendente");
    }
}
