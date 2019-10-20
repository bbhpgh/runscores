package me.zohar.runscore.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	private AuthenticationSuccessHandler successHandler;

	@Autowired
	private AuthenticationFailHandler failHandler;

	@Autowired
	private LogoutHandler logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/pay").permitAll()
		.antMatchers("/api/**").permitAll()
		.antMatchers("/paySuccessNotice").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/my-home-page").permitAll()
		.antMatchers("/masterControl/**").permitAll()
		.antMatchers("/userAccount/register").permitAll()
		.antMatchers("/userAccount/getUserAccountInfo").permitAll()
		.antMatchers("/storage/fetch/**").permitAll()
		.antMatchers("/recharge/callback/**").permitAll()
		.antMatchers("/statisticalAnalysis/findTodayTop10BountyRank").permitAll()
		.antMatchers("/statisticalAnalysis/findTotalTop10BountyRank").permitAll()
		.antMatchers("/online-customer").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").loginProcessingUrl("/login")
		.successHandler(successHandler).failureHandler(failHandler).permitAll()
		.and().logout().logoutUrl("/logout").logoutSuccessHandler(logoutHandler).permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/plugins/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(customAuthenticationProvider);
	}

}
