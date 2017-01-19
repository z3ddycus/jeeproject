package univ.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


/**
 * <b>
 *     Classe de configuration de la sécurité de l'application
 *     NotifCosmoEIApplication
 * </b>
 *
 * La classe permet d'intialiser la configuration de la sécurité, notamment par
 * la configuration de l'authentification et du protocole HTTP.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Contexte de la base de données.
     */
    @Autowired
    protected DataSource dataSource;

    /**
     * Permet d'obtenir un BCryptPasswordEncoder afin d'encoder le mot de passe
     * en base, crypté.
     * @return L'encodeur de mot de passe permettant de crypté celui-ci.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configuration de l'authentification. Permet entre autre de déterminer si
     * l'utilisateur courant est réellement un utilisateur du système et qu'il
     * a les droits (par rapport à son rôle) pour accéder au contenu.
     * @param auth l'élément d'authentification sur lequel la requête va être posée
     * @throws Exception Si invalidité de la demande
     */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select mail,password, role_id from user_id where mail=?")
                .authoritiesByUsernameQuery(
                        "select user_id.mail, role.name from user_id, role where role.id=user_id.role_id and user_id.mail=?");
    }

    /**
     * Configuration de la sécurité des routes et des retours prévus. En effet,
     * pour accéder aux produits, composants et inscription, par exemple,
     * l'utilisateur courant n'a pas besoin d'être authentifié.
     * @param http le protocole que nous souhaitons configurer
     * @throws Exception En cas d'erreur rencontrée
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http
                .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/, /webjars/**, /resources/**", "/product/*", "/component/*", "/registration", "/report/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}
