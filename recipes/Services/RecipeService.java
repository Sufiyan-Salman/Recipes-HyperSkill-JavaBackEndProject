package recipes.Services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.Entities.Recipe;
import recipes.Entities.User;
import recipes.Repositories.RecipeRepo;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Service
public class RecipeService {
    @Autowired
    RecipeRepo recipeRepo;

    public int addRecipe(Recipe recipe, User user) {
        recipe.setDate(LocalDateTime.now());
        System.out.println("id is : "+recipe.getId()+" seting username: "+user.getUsername());
        recipe.setAuthorEmail(user.getUsername());
        recipeRepo.save(recipe);

        return recipe.getId();
    }
    public Recipe getRecipe(int id){
        return recipeRepo.findById(id);
    }

    public int deleteRecipe(int id, User user) {

        Recipe toBeDeletedRecipe=getRecipe(id);
        if (toBeDeletedRecipe!=null){
        //jo recipe delete krrha h , wahi uska writer b hona chahiye wrna use koi haq nai
            if (toBeDeletedRecipe.getAuthorEmail()!=null) if (!toBeDeletedRecipe.getAuthorEmail().equals(user.getUsername())) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            recipeRepo.delete(toBeDeletedRecipe);
            return 1;
        }
        return 0;
    }

    public int updateRecipe(int id, Recipe updatedRecipe, User user) {
        Recipe oldRecipe=getRecipe(id);
        if (oldRecipe!=null){
        //jo recipe upgrade krrha , wahi uska writer b hona chahiye wrna use koi haq nai
            if (oldRecipe.getAuthorEmail()!=null) if (!oldRecipe.getAuthorEmail().equals(user.getUsername())) throw new ResponseStatusException(HttpStatus.FORBIDDEN); //test 18
            updatedRecipe.setId(oldRecipe.getId());
            updatedRecipe.setDate(LocalDateTime.now());
            recipeRepo.save(updatedRecipe);
            return 1;
        }
        return 0;
    }
    public List<Recipe> searchRecipe(String category) {
        return recipeRepo.findRecipesByCategoryIgnoreCaseOrderByDateDesc(category);

    }

    public List<Recipe> searchRecipeByName(String word) {
        return recipeRepo.findRecipesByNameContainingIgnoreCaseOrderByDateDesc(word);
    }
}
