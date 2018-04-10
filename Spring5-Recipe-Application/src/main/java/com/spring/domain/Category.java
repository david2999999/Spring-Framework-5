package com.spring.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

// Lombok's @Data will create the getter,setter and constructor
// @EqualsAndHashCode will create the equal and hashCode for the non-static, non-transient fields
// it will exclude the recipes
// @Entity means the class will be interacting with the database
@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

    // creates unique Ids
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
