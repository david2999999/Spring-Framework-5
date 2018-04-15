package com.spring.services;

import com.spring.domain.Recipe;
import com.spring.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {


    private final RecipeRepository recipeRepository;

    public ImageServiceImpl( RecipeRepository recipeService) {

        this.recipeRepository = recipeService;
    }

    @Override
    @Transactional
    public void saveImageFile(Long recipeId, MultipartFile file) {

        try {
            // the optional object is converted using .get()
            Recipe recipe = recipeRepository.findById(recipeId).get();

            // create a new byte array
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            // get all the bytes from the file into the byte array
            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            recipe.setImage(byteObjects);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            //todo handle better
            log.error("Error occurred", e);

            e.printStackTrace();
        }
    }
}