package study.small.mapper;

import org.apache.ibatis.annotations.*;
import study.small.entity.Property;

import java.util.List;

@Mapper
public interface PropertyMapper {

    @Insert("insert into property(cid,name) values(#{cid},#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Property property);

    @Delete("delete from property where id = #{id}")
    public void delete(int id);

    @Update("update property set cid=#{cid},name=#{name} where id = #{id}")
    public void update(Property property);

    @Select("select * from property where id =#{id}")
    public Property get(int id);

    @Select("select * from property")
    public List<Property> queryAll();

    @Select("select * from property where cid = #{cid}")
    public List<Property> queryByCid(int cid);
}
