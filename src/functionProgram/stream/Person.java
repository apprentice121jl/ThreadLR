package functionProgram.stream;

/**
 * @author julong
 * @description person实例类
 * @createTime 2020/7/22  10:22
 **/
public class Person {
    String name;
    Integer  sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }

    public Person(String name, Integer sex) {
        this.name = name;
        this.sex = sex;
    }
}
