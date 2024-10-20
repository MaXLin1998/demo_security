package jp.co.demo.service;

import ch.qos.logback.core.util.StringUtil;
import jp.co.demo.users.User;
import jp.co.demo.users.UserDetailsImpl;
import jp.co.demo.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

//    @Autowired
    private UserRepository repository;

//    @Autowired
    String idForEncode = "bcrypt";
    private final PasswordEncoder passwordEncoder =
            new DelegatingPasswordEncoder(idForEncode,
                 Map.of(idForEncode, new BCryptPasswordEncoder()));

//    public boolean AuthExecute(String userId,String userPsw) {
//        if (StringUtil.isNullOrEmpty(userId)) {
//            throw new UsernameNotFoundException("UserId is empty");
//        }
//
//        User user = repository.findByUserId(userId);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found: " + userId);
//        }
//
//        UserDetailsImpl userImpl = new UserDetailsImpl(user);
//        userImpl.getAuthorities();
//
//        return  true;
//    }

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String userId) throws UsernameNotFoundException {
        if (StringUtil.isNullOrEmpty(userId)) {
            throw new UsernameNotFoundException("UserId is empty");
        }

        User user = repository.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + userId);
        }

        UserDetailsImpl userAuthEx = new UserDetailsImpl(user);
        repository.findByUserId(userId);

//        if (userAuthEx == null) {
//            throw new UsernameNotFoundException("User not found: " + userId);
//        }

        return userAuthEx;
    }

    //adminを登録するメソッド
//    @Transactional
//    public void registerAdmin(String username, String password, String mailAddress) {
//        UserDetailsImpl user = new UserDetailsImpl(username, passwordEncoder.encode(password));
//        user.setAdmin(true);
//        repository.save(user);
//    }

    //管理者を登録するメソッド
//    @Transactional
//    public void registerManager(String username, String password, String mailAddress) {
//        UserDetailsImpl user = new UserDetailsImpl(username, passwordEncoder.encode(password));
//        user.setManager(true);
//        repository.save(user);
//    }

    //一般ユーザを登録するメソッド
//    @Transactional
//    public void registerUser(String username, String password, String mailAddress) {
//        UserDetailsImpl user = new UserDetailsImpl(username, passwordEncoder.encode(password));
//        repository.save(user);
//    }

}
