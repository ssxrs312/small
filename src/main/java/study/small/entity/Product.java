package study.small.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Document(indexName = "small",type = "product")
public class Product {
    private int id;
    private String name;
    private String subTitle;
    private Float originalPrice;
    private Float promotePrice;
    private int stock;
    private int cid;
    private Date createDate;

    //非数据库字段
    private Category category;
    private ProductImage firstProductImage;

    private List<ProductImage> productSingleImages;      //单个产品图片集合
    private List<ProductImage> productDetailImages;     //详情产品图片集合
    private int saleCount;                             //销量
    private int reviewCount;                          //累计评价


    public Product() {
    }

    public Product(int id, String name, String subTitle, Float originalPrice, Float promotePrice, int stock, int cid, Date createDate) {
        this.id = id;
        this.name = name;
        this.subTitle = subTitle;
        this.originalPrice = originalPrice;
        this.promotePrice = promotePrice;
        this.stock = stock;
        this.cid = cid;
        this.createDate = createDate;
    }

    public Product(int id, String name, String subTitle, Float originalPrice, Float promotePrice, int stock, int cid, Date createDate, Category category, ProductImage firstProductImage, List<ProductImage> productSingleImages, List<ProductImage> productDetailImages, int saleCount, int reviewCount) {
        this.id = id;
        this.name = name;
        this.subTitle = subTitle;
        this.originalPrice = originalPrice;
        this.promotePrice = promotePrice;
        this.stock = stock;
        this.cid = cid;
        this.createDate = createDate;
        this.category = category;
        this.firstProductImage = firstProductImage;
        this.productSingleImages = productSingleImages;
        this.productDetailImages = productDetailImages;
        this.saleCount = saleCount;
        this.reviewCount = reviewCount;
    }

    public Product(int id, String name, String subTitle, Float originalPrice, Float promotePrice, int stock, int cid, Date createDate, Category category, ProductImage firstProductImage) {
        this.id = id;
        this.name = name;
        this.subTitle = subTitle;
        this.originalPrice = originalPrice;
        this.promotePrice = promotePrice;
        this.stock = stock;
        this.cid = cid;
        this.createDate = createDate;
        this.category = category;
        this.firstProductImage = firstProductImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(Float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductImage getFirstProductImage() {
        return firstProductImage;
    }

    public void setFirstProductImage(ProductImage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }

    public List<ProductImage> getProductSingleImages() {
        return productSingleImages;
    }

    public void setProductSingleImages(List<ProductImage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }

    public List<ProductImage> getProductDetailImages() {
        return productDetailImages;
    }

    public void setProductDetailImages(List<ProductImage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", originalPrice=" + originalPrice +
                ", promotePrice=" + promotePrice +
                ", stock=" + stock +
                ", cid=" + cid +
                ", createDate=" + createDate +
                ", category=" + category +
                ", firstProductImage=" + firstProductImage +
                ", productSingleImages=" + productSingleImages +
                ", productDetailImages=" + productDetailImages +
                ", saleCount=" + saleCount +
                ", reviewCount=" + reviewCount +
                '}';
    }
}
