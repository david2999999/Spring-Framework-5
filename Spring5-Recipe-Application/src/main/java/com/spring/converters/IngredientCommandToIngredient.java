package com.spring.converters;

import com.spring.commands.IngredientCommand;
import com.spring.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    // create a converter object for unit of measure
    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;

    // constructor autowired
    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }

        // create a new ingredient domain class
        final Ingredient ingredient = new Ingredient();

        // set the domain object with the properties of the command object
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUom(uomConverter.convert(source.getUom()));

        // return the domain object
        return ingredient;
    }
}
