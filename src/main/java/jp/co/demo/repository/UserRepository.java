package jp.co.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//@Repository
@Mapper
public interface UserRepository {
    @Select("select * from user_info where user_id = #{userId} and user_psw = #{password}")
    User findUser(String userId,String password);

//    @Select("select * from user_info")
//    User findAll();
//    @Select("select 1 as  id")
//    public int findByUserId1();
}

//@Repository
//public interface UserRepository extends CrudRepository<UserDetailsImpl, Long> {
//    public UserDetailsImpl findByUsername(String userId);
//}