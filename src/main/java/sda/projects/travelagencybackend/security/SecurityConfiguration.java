package sda.projects.travelagencybackend.security;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

/*   private static final String password = "{bcrypt}$2y$12$fqITdfOgS2Z3QnL781Yf9OcmlPi3O5TwVAUnGqS2EDYiXI6zDsxyu";

   @Override
   protected void configure(final HttpSecurity hs) throws Exception {
      hs.authorizeRequests().
           antMatchers(HttpMethod.POST, "/api/trips/**").hasRole("ADMIN").
           antMatchers(HttpMethod.DELETE, "/api/trips/**").hasRole("ADMIN").
           antMatchers(HttpMethod.GET, "/api/trips/**").hasRole("ADMIN").
           anyRequest().permitAll().
           and().formLogin().
           and().logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)).
           and().csrf().disable();
   }

   @Override
   protected void configure(final AuthenticationManagerBuilder amb) throws Exception {
      amb.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
   }*/

   @Override
   protected void configure(HttpSecurity hs) throws Exception {
      hs.authorizeRequests()
              .antMatchers("/api/trips/**")
              .authenticated()
              .and()
              .oauth2ResourceServer()
              .jwt();

      hs.cors();
      Okta.configureResourceServer401ResponseBody(hs);
      hs.csrf().disable();
   }
}
