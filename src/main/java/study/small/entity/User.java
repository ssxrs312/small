package study.small.entity;

public class User {
    private int id;
    private String name;
    private String password;
    private String salt;

    public User() {
    }

    public User(int id, String name, String password, String salt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
    //增加一个方法，用来匿名评价
    public String getAnonymousName(){
        if(null==name)
            return null;

        if(name.length()<=1)
            return "*";

        if(name.length()==2)
            return name.substring(0,1) +"*";

        char[] cs =name.toCharArray();
        for (int i = 1; i < cs.length-1; i++) {
            cs[i]='*';
        }

        return new String(cs);
    }
}
