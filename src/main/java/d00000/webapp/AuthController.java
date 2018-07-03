package d00000.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;


/**
 * 認証
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private JdbcUserDetailsManager jdbcUserDetailsManager;
    private PasswordEncoder passwordEncoder;

    /** コンストラクタ */
    public AuthController(@Autowired JdbcUserDetailsManager jdbcUserDetailsManager, @Autowired PasswordEncoder passwordEncoder) {
        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    /** DIが終わった後に実行されるメソッド */
    @PostConstruct
    private void postConstruct() {
        if (!jdbcUserDetailsManager.userExists("user1")) {
            String encodedPassword = "$2a$04$SwtM.7S/4egN/2mcxZn4DO5y4ZkKOoBqFmFvA4PFY98sVJOJxoRDS"; // 「password1」をBCryptでhash化したもの
            UserDetails user1 = User.withUsername("user1").password(encodedPassword).roles("USER").build();
            jdbcUserDetailsManager.createUser(user1);
        }
    }

    /** トップページ */
    @GetMapping("/")
    public String index() {
        return "auth/index";
    }

    /** ログインフォーム */
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


    /** 認証済みユーザのページ */
    @GetMapping("/secret/")
    public String secretIndex(@AuthenticationPrincipal UserDetails userDetails, ModelMap modelMap) {
        modelMap.addAttribute("username", userDetails.getUsername());
        return "auth/secret/index";
    }

    /** ユーザ追加フォーム */
    @GetMapping("/add_user")
    public String addUserGet() {
        return "auth/add_user";
    }

    /** ユーザ追加処理 */
    @PostMapping("/add_user")
    public String addUserPost(@RequestParam("username") String username, @RequestParam("password") String password,
                              @RequestParam("role") String role) {
        if (!jdbcUserDetailsManager.userExists(username)) {
            String encodedPassword = this.passwordEncoder.encode(password);
            UserDetails user = User.withUsername(username).password(encodedPassword).roles(role).build();
            jdbcUserDetailsManager.createUser(user);
        }
        return "redirect:/auth/";
    }

}
