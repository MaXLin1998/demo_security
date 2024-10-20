package jp.co.demo.users;

import jp.co.demo.controller.order.UsesForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;

@Mapper
public interface UserListRepository {
    @Select("select * from orders")
    List<UserList> findAll();
    @Insert("insert into ORDERS(order_id,order_date,company_no,company_name,item_no,item,quantity,unit_price,price) values (#{orderId}, #{orderDate}, #{companyNo}, #{companyName}, #{itemNo}, #{item}, #{quantity}, #{unitPrice}, #{price})")
    void insert(UsesForm usesForm);
    @Select("SELECT MAX(CAST(order_id AS SIGNED)) FROM ORDERS")
    Integer findMaxOrderId();
    @Select("select * from ORDERS where order_id = #{orderId}")
    UserList findById(long orderId);
    @Update("update ORDERS set order_date = #{orderDate}, company_no = #{companyNo}, company_name = #{companyName}, item_no = #{itemNo}, item = #{item}, quantity = #{quantity}, unit_price = #{unitPrice}, price = #{price} where order_id = #{orderId}")
    void update(UsesForm usesForm);
    @Delete("delete from ORDERS where order_id = #{orderId}")
    void delete(long orderId);
}

