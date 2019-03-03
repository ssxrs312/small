package study.small.mapper;

import org.apache.ibatis.annotations.*;
import study.small.entity.Product;
import study.small.entity.Property;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Insert("insert into product(name,subTitle,originalPrice,promotePrice,stock,cid) " +
            "values(#{name},#{subTitle},#{originalPrice},#{promotePrice},#{stock},#{cid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int add(Product product);

    @Delete("delete from product where id = #{id}")
    public void delete(int id);

    @Update("update product set name=#{name},subTitle=#{subTitle},originalPrice=#{originalPrice}," +
            "promotePrice=#{promotePrice},stock=#{stock},cid=#{cid} where id = #{id}")
    public void update(Product product);

    @Select("select * from product where id =#{id}")
    public Product get(int id);

    @Select("select * from product")
    public List<Product> queryAll();

    @Select("select * from product where cid = #{cid}")
    public List<Product> queryByCid(int cid);

    @Select("select * from product where name like '%${value}%'")
    public List<Product> queryByKeyword(String keyword);

}
