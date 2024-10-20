package jp.co.demo.users;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    @Select("select * from user_info where user_id = #{userId}")
    User findByUserId(String userId);
//    @Select("select * from user_info")
//    User findAll();
}

//@Repository
//public interface UserRepository extends CrudRepository<UserDetailsImpl, Long> {
//    public UserDetailsImpl findByUsername(String userId);
//}