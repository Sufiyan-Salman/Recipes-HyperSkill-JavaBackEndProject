package recipes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import recipes.Entities.Recipe;
import recipes.Entities.User;
import recipes.Services.RecipeService;

import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    @PostMapping("/api/recipe/new")
    public ResponseEntity addRecipe(@RequestBody Recipe recipe , @AuthenticationPrincipal User user){
        System.out.println("came in add recipe controller");
        return ResponseEntity.ok(Map.of("id",recipeService.addRecipe(recipe , user)));

    }
    @GetMapping("/api/recipe/{id}")
    public ResponseEntity getRecipe(@PathVariable(name = "id") int id ){
        Recipe recipe =recipeService.getRecipe(id);
        if (recipe==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(recipe);

    }
//    @PreAuthorize("hasAnyRole('author')")
    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity deleteRecipe(@PathVariable(name = "id") int id , @AuthenticationPrincipal User user){
        // is principle se hamen wo user miilega jo hamne abhi hhtp basic auth me username password me dal ahai , agar to wo exist krta hoga to
        int status =recipeService.deleteRecipe(id , user);
        if (status==0) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.noContent().build();

    }
//    @PreAuthorize("hasAnyRole('author')")
    @PutMapping("/api/recipe/{id}")
    public ResponseEntity updateRecipe(@PathVariable(name = "id") int id, @RequestBody Recipe recipe , @AuthenticationPrincipal User user){
        int status =recipeService.updateRecipe(id, recipe, user );
        if (status==0) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/api/recipe/search/")
    public ResponseEntity searchRecipe(@RequestParam(required = false) String category, @RequestParam(required = false) String name){
        if((category!=null && name!=null) || (category==null && name==null)){
          return ResponseEntity.badRequest().build();
        }
        else if(category==null)
        {
            return ResponseEntity.ok(recipeService.searchRecipeByName(name));

        }else return ResponseEntity.ok(recipeService.searchRecipe(category));

    }



}
