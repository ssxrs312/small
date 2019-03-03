package study.small.mapper;

import org.apache.ibatis.annotations.*;
import study.small.entity.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert into category(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Category category);

    @Delete("delete from category where id = #{id}")
    public void delete(int id);

    @Update("update category set name=#{name} where id = #{id}")
    public void update(Category category);

    @Select("select * from category where id =#{id}")
    public Category get(int id);

    @Select("select * from category")
    public List<Category> queryAll();

}
