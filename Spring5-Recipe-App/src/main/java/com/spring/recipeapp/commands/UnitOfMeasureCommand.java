package com.spring.recipeapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//command objects are used for data model
// you could expose ur domain object but that is not a good idea
// as the project grows and becomes more complex, requirements of the domain object
// will become different than what is exposed to the web tier
@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasureCommand {
    private Long id;
    private String description;
}
