package d00000.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 「/auth/secret/**」は認証が必要で、それ以外は認証が不要
        http.authorizeRequests().antMatchers("/auth/secret/**").authenticated().anyRequest().permitAll();

        // ログイン
        http.formLogin().loginPage("/auth/login").usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/auth/loginProcess").permitAll().defaultSuccessUrl("/auth/secret/").failureUrl("/auth/login?error");

        // ログアウト
        http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/auth/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

}
