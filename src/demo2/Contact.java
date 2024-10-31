package demo2;

public class Contact {
    private int id;          // 联系人的唯一标识符
    private String name;     // 联系人的名字
    private String address;  // 联系人的地址
    private String phone;    // 联系人的电话号码

    // 默认构造函数
    public Contact() {}

    // 带参数的构造函数
    public Contact(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    // Getter 和 Setter 方法
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 用于打印联系人信息的方法
    @Override
    public String toString() {
        return  id +
                ". 姓名：" + name +
                ", 地址：" + address +
                ", 电话号码：" + phone ;
    }
}