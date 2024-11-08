package dev.ak.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static List<Student> createStudents() {
        Student student = new Student(1, "ajith", 27, 90, List.of("Maths", "Science"));
        Student student1 = new Student(2, "hema", 25, 80, List.of("Maths", "Social"));
        Student student2 = new Student(3, "kavinila", 23, 70, List.of("English", "Science"));
        Student student3 = new Student(4, "kavinesh", 21, 60, List.of("Biology", "Maths"));
        Student student4 = new Student(5, "babu", 29, 50, List.of("Maths", "English", "Biology"));
        Student student5 = new Student(6, "ajith", 27, 90, List.of("Social", "Science"));
        return new ArrayList<>(List.of(student, student1, student2, student3, student4, student5));
    }

    public static void main(String[] args) {
        List<Student> students = createStudents();

        Map<Integer, List<Student>> groupByAge = students.stream()
                .collect(Collectors.groupingBy(Student::getAge));
        System.out.println("groupByAGe: \n" + groupByAge);

        Map<Double, Long> groupByMarksAndCounting = students.stream()
                .collect(Collectors.groupingBy(Student::getMarks, Collectors.counting()));
        System.out.println("groupByMarksAndCounting: \n" + groupByMarksAndCounting);

        Map<Integer, List<String>> groupByAgeAndCollectName = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println("groupByAgeAndCollectName: \n" + groupByAgeAndCollectName);

        Map<Integer, List<String>> groupByAgeAndFlattenCourse = students.stream()
                .collect(Collectors.groupingBy(Student::getAge,
                        Collectors.flatMapping(
                                student -> student.getCourses()
                                        .stream(),
                                Collectors.toList()))
                );

        System.out.println("groupByAgeAndFlattenCourse: \n" + groupByAgeAndFlattenCourse);

        // Multilevel grouping

        Map<Integer, Map<String, List<Student>>> groupByAgeAndThenName = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.groupingBy(Student::getName)));

        System.out.println("groupByAgeAndThenName: \n" + groupByAgeAndThenName);

        // Find the oldest student in each group

        Map<Double, Optional<Student>> groupByAgeAndFindOldest = students.stream()
                .collect(Collectors.groupingBy(Student::getMarks, Collectors.maxBy(Comparator.comparing(Student::getAge))));

        System.out.println("groupByAgeAndFindOldest: \n" + groupByAgeAndFindOldest);

        Map<String, Double> groupByNameAndFindMarksSum = students.stream()
                .collect(Collectors.groupingBy(Student::getName, Collectors.summingDouble(Student::getMarks)));

        System.out.println("groupByNameAndFindMarksSum: \n" + groupByNameAndFindMarksSum);

        // Longest Word: Given a list of words, find the longest word. If multiple words have the same length, any can be chosen

        List<String> words = new ArrayList<>(List.of("Hello", "Awesome", "Hello", "You"));

        String longestWord = words.stream()
                .reduce((w1, w2) -> w1.length() >= w2.length() ? w1 : w2)
                .orElseThrow(() -> new RuntimeException("No words found"));

        System.out.println("longestWord: " + longestWord);

        String longestWordUsingMax = words.stream()
                .max(Comparator.comparing(String::length))
                .orElseThrow();

        System.out.println("longestWordUsingMax: " + longestWord);

        String longestWordUsingSorted = words.stream()
                //.sorted(Comparator.comparingInt(String::length).reversed())
                .sorted((w1, w2) -> Integer.compare(w2.length(), w1.length()))
                .findFirst()
                .orElseThrow();

        System.out.println("longestWordUsingSorted: " + longestWordUsingSorted);

        // Filter and Transform: Given a list of integers, filter numbers greater than 10, multiply each by 2, and collect the results in a list.

        List<Integer> nums = List.of(5, 10, 12, 15, 20, 25);

        List<Integer> numsGreaterThan10 = nums.stream()
                .filter(n -> n > 10)
                .map(n -> n * 2)
                .toList();

        System.out.println("numsGreaterThan10: " + numsGreaterThan10);

        // Top Three Salaries: Given a list of employees, find the top three highest salaries. Each employee has a name and salary.

        List<Employee> employees = List.of(
                new Employee("Leo",100000),
                new Employee("Jon", 130000),
                new Employee("Ram",50000),
                new Employee("Ramesh",60000),
                new Employee("Raju",10000),
                new Employee("Raja",90000));

        List<Employee> topThreeEmployee = employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                //.sorted((a, b) -> Double.compare(b.getSalary(), a.getSalary()))
                .limit(3)
                .toList();

        System.out.println("topThreeEmployee: \n" + topThreeEmployee);

        // List to Map: Given a list of strings, create a map where the key is the string itself and the value is the length of the string.
        /* In Java Streams, there is no direct toMap() method like toList(),
        but you can easily collect stream results into a Map by using Collectors.toMap().
        * toMap(keyMapper, valueMapper): Collects to Map with specified keys and values.
        * toMap(keyMapper, valueMapper, mergeFunction): Handles duplicate keys with a merge function.
        * toMap(keyMapper, valueMapper, mergeFunction, mapSupplier): Specifies the type of Map to use.
        * */
        List<String> fruits = new ArrayList<>(List.of("Apple", "Orange", "Banana", "Grapes"));

        Map<String, Integer> NamesMap = fruits.stream()
                .collect(Collectors.toMap(name -> name, String::length));
        System.out.println("NamesMap: \n" + NamesMap);


    }
}
