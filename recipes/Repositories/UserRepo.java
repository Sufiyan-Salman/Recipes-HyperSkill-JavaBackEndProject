package recipes.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.Entities.Recipe;
import recipes.Entities.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
    public User findByEmailIgnoreCase(String email);
}
