package com.dev.controllers;

import com.dev.objects.Recipe;
import com.dev.objects.User;
import com.dev.responses.BasicResponse;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dev.utils.Errors.ERROR_MISSING_USERNAME;
import static com.dev.utils.Errors.ERROR_USERNAME_ALREADY_EXISTS;

@RestController
public class RecipesPageController {

    @Autowired
    private Utils utils;

    @Autowired
    private Persist persist;

    @RequestMapping(value = "add-recipe-to-favorites")
    public BasicResponse addRecipe (String token , String title, String imgLink, String link) {
        BasicResponse basicResponse = new BasicResponse();
        User fromDb = persist.getUserByToken(token);
        boolean success = false;
        Integer errorCode = null;
        if (fromDb != null) {
            success = true;
            Recipe recipeToAdd=new Recipe(title,imgLink,link,fromDb);
            persist.addRecipeToFavorite(recipeToAdd);

        }else {
            errorCode = ERROR_MISSING_USERNAME;

        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse ;
    }





}
