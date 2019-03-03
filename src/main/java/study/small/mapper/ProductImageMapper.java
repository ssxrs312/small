package study.small.mapper;

import org.apache.ibatis.annotations.*;
import study.small.entity.ProductImage;

import java.util.List;

@Mapper
public interface ProductImageMapper {

    @Insert("insert into productimage(pid,type) values(#{pid},#{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(ProductImage productimage);

    @Delete("delete from productimage where id = #{id}")
    public void delete(int id);

    @Update("update productimage set pid=#{pid},type=#{type} where id = #{id}")
    public void update(ProductImage productimage);

    @Select("select * from productimage where id =#{id}")
    public ProductImage get(int id);

    @Select("select * from productimage")
    public List<ProductImage> queryAll();

    @Select("select * from productimage where pid = #{pid} and type=#{type}")
    public List<ProductImage> queryByPidAndType(ProductImage productImage);

}
