package recipes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import recipes.Entities.User;
import recipes.Repositories.UserRepo;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
     @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addUser(User user) {
        if (getUser(user.getUsername()) != null) {
            //means such user already exists
            System.out.println("user already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
    public User getUser(String email){
        return userRepo.findByEmailIgnoreCase(email);
    }
}
