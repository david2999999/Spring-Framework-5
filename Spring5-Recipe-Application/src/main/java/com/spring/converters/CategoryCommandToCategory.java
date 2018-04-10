package com.spring.converters;


import com.spring.commands.CategoryCommand;
import com.spring.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

// The converter classes will be used to convert command objects to original domain objects
// the command objects are used to bind with the forms
@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

    // since, spring does not have security on threads
    // we will use the Lombok's @Synchronized to keep track of the threads
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        // create a new category object
        final Category category = new Category();

        // get the ID and description of the command object
        // and set it into the category object
        category.setId(source.getId());
        category.setDescription(source.getDescription());

        // return the domain object
        return category;
    }
}
