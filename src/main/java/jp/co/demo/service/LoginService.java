package jp.co.demo.service;

import jp.co.demo.repository.User;
import jp.co.demo.repository.UserDetailsImpl;

public interface LoginService {
//    @Override
public User loadUserByUserInfo(String userId);

public int updateFailedCount(String userId, int failedCount);

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
