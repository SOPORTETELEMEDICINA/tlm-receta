package net.amentum.niomedic.receta.configuration;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends ResourceServerConfigurerAdapter {

   @Override
   public void configure(HttpSecurity http) throws Exception {
      http.antMatcher("/**").authorizeRequests()
         .antMatchers("/v2/api-docs**","/info")
         .permitAll()
         .anyRequest().authenticated()
         .and().csrf().disable()
         .httpBasic().disable();
   }
}

