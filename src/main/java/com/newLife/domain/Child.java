package com.newLife.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "child")
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id"})
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameChild;
    private String state;

    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    public Child() {
    }

    public Child(String nameChild, String state, Patient patient) {
        this.nameChild = nameChild;
        this.state = state;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameChild() {
        return nameChild;
    }

    public void setNameChild(String nameChild) {
        this.nameChild = nameChild;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
