package dev.ak.medium;

import java.util.List;

public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private double marks;

    private List<String> courses;

    public Student(Integer id, String name, Integer age, double marks, List<String> courses) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.courses = courses;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", marks=" + marks +
                '}';
    }

//    @Override
//    public int compareTo(Student o) {
//        return o.name.compareTo(this.name);
//    }
}
