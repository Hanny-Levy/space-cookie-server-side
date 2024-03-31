package com.dev.controllers;

import com.dev.objects.Recipe;
import com.dev.objects.User;
import com.dev.responses.BasicResponse;
import com.dev.responses.FavoritesRecipesResponse;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dev.utils.Errors.*;

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
            errorCode = ERROR_USER_NOT_FOUND;

        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse ;
    }

    @RequestMapping(value = "get-all-favorites-recipes")
    public BasicResponse getFavRecipes (String token) {
        BasicResponse basicResponse = new BasicResponse();
        User fromDb = persist.getUserByToken(token);
        boolean success = false;
        Integer errorCode = null;
        if (fromDb != null) {
            success = true;
            List<Recipe> favRecipes = persist.getFavoritesRecipes(token) ;
            if (favRecipes==null){
                System.out.println("nullllll");
            }else {
                basicResponse=new FavoritesRecipesResponse(favRecipes);

            }


        }else {
            errorCode = ERROR_USER_NOT_FOUND;
            basicResponse.setErrorCode(errorCode);
        }
        basicResponse.setSuccess(success);
        return basicResponse ;



    }







}
