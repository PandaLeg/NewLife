package com.newLife.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "child")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "nameChild", "age"})
public class Child implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameChild;
    private String state;
    private String age;
    private String height;
    private String weight;
    private String temperature;
    private String pulse;
    private String pressure;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Patient patient;

    public Child() {
    }

    public Child(String nameChild, String state, String height, String weight, String temperature, String pulse,
                 String pressure, Patient patient) {
        this.nameChild = nameChild;
        this.state = state;
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.pulse = pulse;
        this.pressure = pressure;
        this.patient = patient;
    }
}
