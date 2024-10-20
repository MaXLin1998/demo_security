package jp.co.demo.users;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Setter
@Getter
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 5197941260523577515L;

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    //権限は一般ユーザ、システム管理者の2種類とする
    public enum Authority {ROLE_USER, ROLE_ADMIN}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(1);
        authorities.add(new SimpleGrantedAuthority(Authority.ROLE_USER.name()));

        return authorities;
    }

    public boolean isAuthorUser(String userId, String userPsw) {
        String userIdFromDB = getUsername();
        String userPswFromDB = getPassword();
        return Objects.equals(userId, userIdFromDB)
                && Objects.equals(userPswFromDB, userPsw);
    }

    @Override
    public String getPassword() {
        return user.getUserPsw();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @Override
    public String getUserId() {
        return getUsername();
    }



//    @SuppressWarnings("deprecation")
//    public static PasswordEncoder createDelegatingPasswordEncoder() {
//        String idForEncode = "bcrypt";
//        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put(idForEncode, new BCryptPasswordEncoder());
//        encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
//        encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());
//        encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
//        encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
//        encoders.put("pbkdf2", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_5());
//        encoders.put("pbkdf2@SpringSecurity_v5_8", Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8());
//        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v4_1());
//        encoders.put("scrypt@SpringSecurity_v5_8", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
//        encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
//        encoders.put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
//        encoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());
//        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_2());
//        encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
//        return new DelegatingPasswordEncoder(idForEncode, encoders);
//    }
}
