package com.newLife.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "request")
@Data
public class Request implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    public Request() {
    }

    public Request(String message) {
        this.message = message;
    }
}
