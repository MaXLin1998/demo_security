package jp.co.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//@Repository
@Mapper
public interface UserRepository {
    @Select("select * from user_info where user_id = #{userId}")
    User findUser(String userId);

    @Update("update user_info set failed_count = #{failedCount} where user_id = #{userId}")
    int updateFailedCount(String userId, int failedCount);

//    @Select("select * from user_info")
//    User findAll();
//    @Select("select 1 as  id")
//    public int findByUserId1();
}

//@Repository
//public interface UserRepository extends CrudRepository<UserDetailsImpl, Long> {
//    public UserDetailsImpl findByUsername(String userId);
//}