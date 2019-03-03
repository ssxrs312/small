package study.small.entity;

public class ProductImage {
    private int id;
    private int pid;
    private String type;

    //非数据库字段
    private Product product;

    public ProductImage() {
    }

    public ProductImage(int id, int pid, String type) {
        this.id = id;
        this.pid = pid;
        this.type = type;
    }

    public ProductImage(int id, int pid, String type, Product product) {
        this.id = id;
        this.pid = pid;
        this.type = type;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", pid=" + pid +
                ", type='" + type + '\'' +
                ", product=" + product +
                '}';
    }
}
