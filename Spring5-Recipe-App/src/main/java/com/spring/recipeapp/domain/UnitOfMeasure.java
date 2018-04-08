package com.spring.recipeapp.domain;

import lombok.*;

import javax.persistence.*;

// @Data is from Lombok, which will create the getters and setters
@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

}
