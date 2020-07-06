package extend;

import java.util.Date;

/**
 * @author: lixinghao
 * @date: 2020/7/2 9:19 下午
 * @Description:
 */
public class SubClass extends ParentClass {
    private String name;
    private int age;
    private Date birthday;

    public SubClass(int val, String value, String name, int age, Date birthday) {
        super(val, value);
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public SubClass(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public SubClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
