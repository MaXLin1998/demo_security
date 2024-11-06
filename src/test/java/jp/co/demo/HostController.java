package jp.co.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.co.demo.repository.User;
import jp.co.demo.request.Request;
import jp.co.demo.response.Response;
import jp.co.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@RestController
@RequestMapping("/v1")
public class HostController {
    @Autowired
    private LoginService loginService;

//   private final GetUsersService usersService;
    /**
     * ログイン成功時に呼び出されるメソッド
     * SecurityContextHolderから認証済みユーザの情報を取得しモデルへ追加する
     *
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
        // TODO
//        getInfoByClient();
        getInfoByClient01();
        return "v1/login";
    }

    //ログイン成功時のメニュー画面への遷移
    @PostMapping("/login")
    public Object postLogin(@RequestBody LoginForm form) {
        // TODO
//        getInfoByClient();
        getInfoByClient01();

        Response response = new Response();
        try {
            String username = form.getUsername();
            String rawPsw = form.getPassword();
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(rawPsw)) {
                return "v1/login";
            }

            String encoded = passwordEncoder.encode(rawPsw);
            System.out.println("pswEncoded: " + encoded);

            User user = loginService.loadUserByUserInfo(username);
            // 会員が存在しない場合、
            if (null == user) {
                return "redirect:/login-error";
            }

            // 失敗回数の取得
            int failedCount = user.getFailedCount();

            // パスワードが不一致である場合
            if (!passwordEncoder.matches(rawPsw, user.getUserPsw())) {
                // ログイン失敗回数のチェック
                if (user.getFailedCount() > 5) {
                    return "redirect:/login-error";
                } else {
                    failedCount = failedCount + 1;
                    loginService.updateFailedCount(username, failedCount);
                    return "redirect:/login-error";
                }
            } else {
                failedCount = 0;
                loginService.updateFailedCount(username, failedCount);
            }
            response.setResultCode("0");
            System.out.println("レスポンスで返す処理結果コードは→" + response.getResultCode());
            return response;
        } catch (Exception e) {
            response.setResultCode("1");
            System.out.println("エラーが発生しました。処理結果コード1を返却");
            return response;

        }

    }

    public void getInfoByClient() {
        String url = "https://dog.ceo/api/breeds/image/random";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println(res);
            System.out.println(res.body());
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void getInfoByClient01() {
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // HTTPリクエストの設定を行います。
            // ここでは例としてタイムアウトの時間を設定します。
            RequestConfig config = RequestConfig.custom()
                    .setSocketTimeout(3000)
                    .setConnectTimeout(3000)
                    .build();

            // HTTPのGETリクエストを構築します。
            // ここでは例としてHTTPヘッダ(User-Agent)と設定をセットします。
            String url01 = "https://www.yahoo.co.jp";
            String url02 = "https://dog.ceo/api/breeds/image/random";
            HttpGet httpGet = new HttpGet(url02);
//            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36");
            httpGet.setConfig(config);

            // HTTPリクエストを実行します。 HTTPステータスが200の場合は取得したHTMLを表示します。
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            if( httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
                System.out.println(EntityUtils.toString(httpResponse.getEntity()));
            }
            else {
                System.out.println("200以外のステータスコードが返却されました。");
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }
}
