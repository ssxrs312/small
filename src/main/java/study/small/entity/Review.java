package study.small.entity;

import java.util.Date;

public class Review {
    private int id;
    private String content;
    private int uid;
    private int pid;
    private Date createDate;

    //非数据库字段
    private User user;

    public Review() {
    }

    public Review(int id, String content, int uid, int pid, Date createDate) {
        this.id = id;
        this.content = content;
        this.uid = uid;
        this.pid = pid;
        this.createDate = createDate;
    }

    public Review(int id, String content, int uid, int pid, Date createDate, User user) {
        this.id = id;
        this.content = content;
        this.uid = uid;
        this.pid = pid;
        this.createDate = createDate;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", pid=" + pid +
                ", createDate=" + createDate +
                ", user=" + user +
                '}';
    }
}
