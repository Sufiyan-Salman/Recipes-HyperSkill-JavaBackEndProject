package recipes.Entities;

import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "Users")
@Setter
@ToString

public class User implements UserDetails {

    @Id
    @NotBlank(message="email cannot be empty")
    //iska matlab ye k @ se pehle , baad me aur phir .k baad me kuch ba asakta h
    @Pattern(regexp = ".+@.+\\..+") // ye istrha bhi kia ja skta hai
    private String email;

    @NotBlank(message = "Should not be empty")
    @Size(min = 8)
    private String password;

    //
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
