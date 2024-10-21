package jp.co.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.demo.repository.User;
import jp.co.demo.service.LoginService;
import jp.co.demo.repository.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

@Controller
//@RequestMapping("/login")
//@RequiredArgsConstructor
public class LoginController {
    @Autowired
    private LoginService loginService;

//   private final GetUsersService usersService;
    /**
     * ログイン成功時に呼び出されるメソッド
     * SecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
     * @param model リクエストスコープ上にオブジェクトを載せるためのmap
     * @return helloページのViewName
     */
//    @RequestMapping("order/list")
//    private String showList(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        //Principalからログインユーザの情報を取得
//        String userName = auth.getName();
//        model.addAttribute("userName", userName);
//        return "order/list";
//
//    }
     @Autowired
     private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String index() {
        return "redirect:/top";
    }

    //ログイン画面への遷移
    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    //ログイン成功時のメニュー画面への遷移
    @PostMapping("/login")
    public String postLogin(LoginForm form, HttpServletRequest request, HttpServletResponse response) {
        String username = form.getUsername();
        String rawPsw = form.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(rawPsw)) {
            return "login";
        }

        String encoded = passwordEncoder.encode(rawPsw);
        User user  = loginService.loadUserByUserInfo(username, encoded);
        if (null == user){
            return "redirect:/login-error";
        }


//        boolean isAuthed = userDetailsImpl.isAuthorUser(username, encoded);
//        if (!isAuthed) {
//            return "redirect:/login-error";
//        }

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