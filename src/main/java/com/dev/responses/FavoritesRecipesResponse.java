package com.dev.responses;

import com.dev.objects.Recipe;

import java.util.List;

public class FavoritesRecipesResponse extends BasicResponse{
    private List<Recipe> recipeList ;

    public FavoritesRecipesResponse(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public FavoritesRecipesResponse(boolean success, Integer errorCode, List<Recipe> recipeList) {
        super(success, errorCode);
        this.recipeList = recipeList;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
}
