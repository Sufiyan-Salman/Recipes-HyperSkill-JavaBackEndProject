package recipes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.Entities.User;
import recipes.Services.UserService;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserService userservice;

    @PostMapping("/api/register")
    public ResponseEntity registerUser(@RequestBody @Valid User user){
        System.out.println("regostering, email: "+user.getUsername()+" paassword: "+user.getPassword());
        userservice.addUser(user);
        System.out.println("done");
        return ResponseEntity.ok().build();

    }
}
