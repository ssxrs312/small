package study.small.entity;

public class Property {
    private int id;
    private int cid;
    private String name;

    //非数据库字段
    private Category category;

    public Property() {
    }

    public Property(int id, int cid, String name) {
        this.id = id;
        this.cid = cid;
        this.name = name;
    }

    public Property(int id, int cid, String name, Category category) {
        this.id = id;
        this.cid = cid;
        this.name = name;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", cid=" + cid +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}
