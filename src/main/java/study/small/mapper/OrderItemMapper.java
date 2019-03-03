package study.small.mapper;

import org.apache.ibatis.annotations.*;
import study.small.entity.OrderItem;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    @Insert("insert into orderitem(pid,oid,uid,number) values(#{pid},#{oid},#{uid},#{number})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(OrderItem orderItem);

    @Delete("delete from orderitem where id = #{id}")
    public void delete(int id);

    @Update("update orderitem set pid=#{pid},oid=#{oid},uid=#{uid},number=#{number} where id = #{id}")
    public void update(OrderItem orderItem);

    @Select("select * from orderitem where id =#{id}")
    public OrderItem get(int id);

    @Select("select * from orderitem")
    public List<OrderItem> queryAll();

    @Select("select * from orderitem where oid = #{oid}")
    public List<OrderItem> queryByOid(int oid);

    @Select("select * from orderitem where pid = #{pid}")
    public List<OrderItem> queryByPid(int pid);

    @Select("select * from orderitem where uid = #{uid} and oid is null")
    public List<OrderItem> queryByUidAndOrderIsNull(int uid);

}
