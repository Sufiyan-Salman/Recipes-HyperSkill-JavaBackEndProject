package recipes.Configurations;

import recipes.Repositories.UserRepo;
import recipes.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//ye hamne http basic and form k lie banaya tha

@Service
@ComponentScan
public class CustomUserDetailService implements UserDetailsService {
    // jab hamne ise implement krlia , to ab hamara in mememory wala function joke mesucrtiyconfig me hai , wo nai chalega , hmen authentication provider apna dena prega
    // phir authenticatino provider jo ham apna banayenge , usme ham wo inmemeory wala userdetails pass krskte hen
     //this is userdetail service impl
    @Autowired
    private UserRepo userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //=======================
        System.out.println("came here in load by username");
        User user = this.userRepository.findByEmailIgnoreCase(email); //interface repo me ek alag se method banana prega with name findByUsername(String username)
        if (user == null) {
              throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No such user exist");// we can use either of them
        }
        return user;
    }

}
