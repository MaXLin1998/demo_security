package jp.co.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.demo.service.LoginService;
import jp.co.demo.users.UserDetailsImpl;
import jp.co.demo.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

//    public LoginController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.passwordEncoder = passwordEncoder;
//    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/top";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(LoginForm form, HttpServletRequest request, HttpServletResponse response) {
        loginService = new LoginService((UserRepository) jdbcTemplate, passwordEncoder);
        String encoded = this.passwordEncoder.encode(form.getPassword());
        String username = form.getUsername();
        UserDetailsImpl userDetailsImpl  = loginService.loadUserByUsername(username);

        if (null == userDetailsImpl){
            return "redirect:/login-error";
        }
        // サインアップ後の自動ログイン処理省略 (GitHub上のソースコードを見てください)
        return "redirect:/top";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/top")
    public String top() {
        return "/top";
    }
}