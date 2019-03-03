package study.small.mapper;

import org.apache.ibatis.annotations.*;
import study.small.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into user(name,password,salt) values(#{name},#{password},#{salt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(User user);

    @Delete("delete from user where id = #{id}")
    public void delete(int id);

    @Update("update user set name=#{name},password=#{password},salt={salt} where id = #{id}")
    public void update(User user);

    @Select("select * from user where id =#{id}")
    public User get(int id);

    @Select("select * from user")
    public List<User> queryAll();

    @Select("select * from user where name = #{name}")
    public User queryByName(String name);

    @Select("select * from user where name = #{name} and password = #{password}")
    public User queryByNameAndPassword(User user);


}
