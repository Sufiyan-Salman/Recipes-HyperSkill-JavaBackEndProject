package recipes.Repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import recipes.Entities.Recipe;

import java.util.HashMap;
import java.util.Map;

@Component

public class RecipeRepoDemo {
    private Map<Integer, Recipe> recipeRepo;
    public RecipeRepoDemo(){
        recipeRepo=new HashMap<>();
    }
    public void save(Recipe recipe, int id){
        System.out.println("saving in map , id is:"+id);
        recipeRepo.put(id , recipe);
    }
    public Recipe findById(int id){
        System.out.println("getting from map , id is:"+id);
        return recipeRepo.get(id);
    }
}
