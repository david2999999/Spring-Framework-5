package com.spring.converters;

import com.spring.commands.CategoryCommand;
import com.spring.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

// create a converter class to convert the category domain object
// to Category command object that will be used to bind to the forms
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    // since, spring does not guaranteed thread safety
    // we will use the @Synchonrized
    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }

        // create a new command object
        final CategoryCommand categoryCommand = new CategoryCommand();

        // set the command object with the domain's Id and description
        categoryCommand.setId(source.getId());
        categoryCommand.setDescription(source.getDescription());

        // return the command object
        return categoryCommand;
    }
}
