package recipes.Configurations;

//import lombok.RequiredArgsConstructor;

//import account.CustomHandlers.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import  org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import  org.springframework.security.web.util.matcher.RequestMatcher;



import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity() //(prePostEnabled=true)
//@EnableMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity  //(prepostEnabled=true ) , jab hemen ye krna hia to jis controller pe ye laga a hai wahan preauthrorize lagate hen as written in line 55

public class SecurityConfiguration {

    @Autowired
    private CustomUserDetailService customUserDetailService;

//    @Autowired//ye na lagao to masla krrha tha , either this , or above
//    private AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//
        http.httpBasic()
                .and()
                .csrf()
                .disable().headers().frameOptions().disable() // for Postman, the H2 console
                .and()
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/api/register" ) ).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/h2") ).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/actuator/shutdown") ).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/error") ).permitAll()
                .requestMatchers(toH2Console()) //for h2 to work and for us to access it via url
                //sirf in url ko permit krega for outsiders
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //hamne stateless isliye rkha take server kuch b na rkhe aur har baar token ko validate  , means new session for each request
                .and()
                .userDetailsService(customUserDetailService); // srif is se bhi chal rha hai , is ke zariye application config me kuch b nai krna  prh rha hai
//                .authenticationProvider(authenticationProvider) ;// application config me hai , is se bhi chalega lekin phir config me jake sab uncomment krna prega



        return http.build();
    }

}
