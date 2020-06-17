package com.newLife.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "message")
@Data
@ToString(of = {"id", "title", "description"})
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "title can't be empty")
    private String title;
    @NotBlank(message = "description can't be empty")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIdentityReference
    @JsonIdentityInfo(
            property = "id",
            generator = ObjectIdGenerators.PropertyGenerator.class
    )
    private Patient patient;

    public Message() {
    }

    public Message(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
