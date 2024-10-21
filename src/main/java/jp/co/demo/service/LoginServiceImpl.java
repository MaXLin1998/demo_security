package jp.co.demo.service;

import ch.qos.logback.core.util.StringUtil;
import jp.co.demo.repository.User;
import jp.co.demo.repository.UserDetailsImpl;
import jp.co.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@RequiredArgsConstructor
//public class LoginService implements UserDetailsService {
public class LoginServiceImpl implements  LoginService {

    @Autowired
    private UserRepository repository;
//
//    private PasswordEncoder passwordEncoder;

//    public LoginService(UserRepository repository, PasswordEncoder passwordEncoder) {
//        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
//    }

//    @Autowired
//    String idForEncode = "bcrypt";
//    private final PasswordEncoder passwordEncoder =
//            new DelegatingPasswordEncoder(idForEncode,
//                 Map.of(idForEncode, new BCryptPasswordEncoder()));

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

//    @Override
    @Transactional
    public User loadUserByUserInfo(String userId,String password)
            throws UsernameNotFoundException {
        if (StringUtil.isNullOrEmpty(userId)) {
            throw new UsernameNotFoundException("UserId is empty");
        }
//        int test = repository.findByUserId1();
        User user = repository.findUser(userId,password);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + userId);
        }

//        UserDetailsImpl userAuthEx = new UserDetailsImpl(user);
//        this.repository.findByUserId(userId);

        return user;
//        return  null;
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
