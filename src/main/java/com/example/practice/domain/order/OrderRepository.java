package com.example.practice.domain.order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import java.util.List;
import com.example.practice.web.order.OrderForm;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface OrderRepository {
    @Select("select * from orders")
    List<OrderEntity> findAll();
    @Insert("insert into ORDERS(order_id,order_date,company_no,company_name,item_no,item,quantity,unit_price,price) values (#{orderId}, #{orderDate}, #{companyNo}, #{companyName}, #{itemNo}, #{item}, #{quantity}, #{unitPrice}, #{price})")
    void insert(OrderForm orderForm);
    @Select("SELECT MAX(CAST(order_id AS SIGNED)) FROM ORDERS")
    Integer findMaxOrderId();
    @Select("select * from ORDERS where order_id = #{orderId}")
    OrderEntity findById(long orderId);
    @Update("update ORDERS set order_date = #{orderDate}, company_no = #{companyNo}, company_name = #{companyName}, item_no = #{itemNo}, item = #{item}, quantity = #{quantity}, unit_price = #{unitPrice}, price = #{price} where order_id = #{orderId}")
    void update(OrderForm orderForm);
    @Delete("delete from ORDERS where order_id = #{orderId}")
    void delete(long orderId);
}

