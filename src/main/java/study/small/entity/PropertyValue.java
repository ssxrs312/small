package study.small.entity;

public class PropertyValue {
    private int id;
    private int pid;
    private int ptid;
    private String value;

    //非数据库字段
    private Property property;

    public PropertyValue() {
    }

    public PropertyValue(int id, int pid, int ptid, String value) {
        this.id = id;
        this.pid = pid;
        this.ptid = ptid;
        this.value = value;
    }

    public PropertyValue(int id, int pid, int ptid, String value, Property property) {
        this.id = id;
        this.pid = pid;
        this.ptid = ptid;
        this.value = value;
        this.property = property;
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

    public int getPtid() {
        return ptid;
    }

    public void setPtid(int ptid) {
        this.ptid = ptid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "id=" + id +
                ", pid=" + pid +
                ", ptid=" + ptid +
                ", value='" + value + '\'' +
                ", property=" + property +
                '}';
    }
}
