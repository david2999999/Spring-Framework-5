package com.spring.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// creating commands that are similar to the domain objects
// we do not want to expose our domain objects to web tier
// for large projects, we want to expose the property of objects that will be used in the form
@Setter
@Getter
@NoArgsConstructor
public class CategoryCommand {
    private Long id;
    private String description;
}
