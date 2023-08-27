package recipes.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.Entities.Recipe;

import java.util.List;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Integer> {
    public Recipe findById(int id);
    public List<Recipe> findRecipesByCategoryIgnoreCaseOrderByDateDesc(String category);
    //recipes ko by category search kre aur category ki casing ko ignore kre aur phir recipes ko date ki bunyad pe descending order me sort
    public List<Recipe> findRecipesByNameContainingIgnoreCaseOrderByDateDesc(String word);
    //recipes ko by name search kre jin me ye word shamil ho aur word k casing ko ignore kre aur phir recipes ko date ki bunyad pe descending order me sort

}
