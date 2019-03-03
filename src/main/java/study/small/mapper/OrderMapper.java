package study.small.mapper;

import org.apache.ibatis.annotations.*;
import study.small.entity.Order;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Insert("insert into order_(orderCode,address,post,receiver,mobile,userMessage,createDate,payDate,deliveryDate," +
            "confirmDate,uid,status) values(#{orderCode},#{address},#{post},#{receiver},#{mobile},#{userMessage}," +
            "#{createDate},#{payDate},#{deliveryDate},#{confirmDate},#{uid},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Order order);

    @Delete("delete from order_ where id = #{id}")
    public void delete(int id);

    @Update("update order_ set orderCode=#{orderCode},address=#{address},post=#{post},receiver=#{receiver},mobile=#{mobile}," +
            "userMessage=#{userMessage},createDate=#{createDate},payDate=#{payDate},deliveryDate=#{deliveryDate}," +
            "confirmDate=#{confirmDate},uid=#{uid},status=#{status} where id = #{id}")
    public void update(Order order);

    @Select("select * from order_ where id =#{id}")
    public Order get(int id);

    @Select("select * from order_")
    public List<Order> queryAll();

    @Select("select * from order_ where uid = #{uid} and status != #{status}")
    List<Order> queryOrderByUid(Order order);

}
