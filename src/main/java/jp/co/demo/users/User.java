package jp.co.demo.users;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

//
//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Table(name="user_info")
@AllArgsConstructor
@Data
public class User {

//    @Id
//    @GeneratedValue(strategy= GenerationType.TABLE)
//    @Column(name = "user_id")
    private String userId;

//    @Column(name = "user_psw")
    private String userPsw;

//    @Column(name = "name_first")
    private String nameFirst;

//    @Column(name = "name_last")
    private String nameLast;

//    @Column(name = "user_role")
    private String userRole;

//    @Column(name = "create_date")
    private Date createDate;

//    @Column(name = "create_user")
    private String createUser;

//    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user")
    private String updateUser;
}
