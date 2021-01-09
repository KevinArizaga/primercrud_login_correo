package com.primercrud.uabc.Primercrud.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Alumno extends Persona {


    public Alumno() {
    }

    public Alumno(String fisrtName, String lastName, Date birthDate, String course) {
        super(fisrtName, lastName, birthDate);
        super.setTypePersona("Alumno");
        super.setCourse(course);
        super.setSalario(null);
    }
}
