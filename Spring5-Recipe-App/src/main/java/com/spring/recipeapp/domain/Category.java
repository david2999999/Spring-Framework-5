package com.spring.recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

//Any class definition may be annotated with @EqualsAndHashCode to let
//lombok generate implementations of the equals(Object other) and hashCode() methods.
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
