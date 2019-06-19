package it.uniroma3.CancianiQuintarelli.SilphSPA.Service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;

	//@SuppressWarnings("unused")
	//private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requiresChannel()
	      .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
	      .requiresSecure().and()
		.authorizeRequests()
		.antMatchers("/admin").hasAnyAuthority("admin")
		.antMatchers("/inserimento").hasAnyAuthority("admin")
		.antMatchers("/inserimentoFoto").hasAnyAuthority("admin")
		.antMatchers("/inserimentoFotografo").hasAnyAuthority("admin")
		.antMatchers("/inserimentoAlbum").hasAnyAuthority("admin")
		.antMatchers("/consultaDati").hasAnyAuthority("admin")
		.antMatchers("/ricercaFoto").hasAnyAuthority("admin")
		.antMatchers("/cercaFotoPerNome").hasAnyAuthority("admin")
		.antMatchers("/cercaFotoPerId").hasAnyAuthority("admin")
		.antMatchers("/ricercaAlbum").hasAnyAuthority("admin")
		.antMatchers("/cercaAlbumPerNome").hasAnyAuthority("admin")
		.antMatchers("/cercaAlbumPerId").hasAnyAuthority("admin")
		.antMatchers("/ricercaFotografo").hasAnyAuthority("admin")
		.antMatchers("/cercaFotografoNomeCognome").hasAnyAuthority("admin")
		.antMatchers("/cercaFotografoId").hasAnyAuthority("admin")
		.antMatchers("/richieste").hasAnyAuthority("admin")
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.defaultSuccessUrl("/admin")
		.and()
		.logout()
		.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		  auth.jdbcAuthentication().dataSource(this.buildDatasource())
          .authoritiesByUsernameQuery("SELECT username,role FROM silph_staff WHERE username=?")
          .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM silph_staff WHERE username=?");

	}

	@Bean
	DataSource buildDatasource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(
				environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
