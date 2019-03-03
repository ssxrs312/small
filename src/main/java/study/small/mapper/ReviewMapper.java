package study.small.mapper;

import org.apache.ibatis.annotations.*;
import study.small.entity.Review;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Insert("insert into review(content,uid,pid,createDate) values(#{content},#{uid},#{pid},#{createDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Review review);

    @Delete("delete from review where id = #{id}")
    public void delete(int id);

    @Update("update review set content=#{content},uid=#{uid},pid=#{pid},createDate=#{createDate} where id = #{id}")
    public void update(Review review);

    @Select("select * from review where id =#{id}")
    public Review get(int id);

    @Select("select * from review")
    public List<Review> queryAll();

    @Select("select * from review where pid = #{pid}")
    public List<Review> queryByPid(int pid);
}
