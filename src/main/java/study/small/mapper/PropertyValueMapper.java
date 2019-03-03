package study.small.mapper;

import org.apache.ibatis.annotations.*;
import study.small.entity.PropertyValue;

import java.util.List;

@Mapper
public interface PropertyValueMapper {

    @Insert("insert into propertyvalue(pid,ptid,value) values(#{pid},#{ptid},#{value})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(PropertyValue propertyValue);

    @Delete("delete from propertyvalue where id = #{id}")
    public void delete(int id);

    @Update("update propertyvalue set pid=#{pid},ptid=#{ptid},value=#{value} where id = #{id}")
    public void update(PropertyValue propertyValue);

    @Select("select * from propertyvalue where id =#{id}")
    public PropertyValue get(int id);

    @Select("select * from propertyvalue")
    public List<PropertyValue> queryAll();

    @Select("select * from propertyvalue where ptid=#{ptid} and pid=#{pid}")
    public PropertyValue queryByProperty(PropertyValue propertyValue);

    @Select("select * from propertyvalue where pid = #{pid}")
    public List<PropertyValue> queryByPid(int pid);

}
