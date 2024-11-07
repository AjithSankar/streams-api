package basic;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Integer> nums = List.of(1,2,3,4,5,6,7,8,9,10);
        List<Integer> evenNums = nums.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("Even Numbers: " + evenNums);

        List<String> names = List.of("ajith","hema","kavinila", "kavinesh","babu");
        List<String> capitalNames = names.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("Capital Names: " + capitalNames);

        String nameStartsWithB = names.stream()
                .filter(name -> name.startsWith("b"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Name is not present"));
        System.out.println("nameStartsWithB : "+ nameStartsWithB);

        Integer sumOfStrings = names.stream()
                .map(String::length)
                //.reduce(0, (Integer::sum))
                .reduce(0, ( a , b) -> a + b);

        System.out.println("sumOfStrings:" + sumOfStrings);

        Integer sumOfNameLengths = names.stream()
                //.reduce(0, (sum , name) -> sum + name.length(), Integer::sum)
                .reduce(0, (sum, name) -> sum + name.length(), (a, b) -> a + b);

        System.out.println("sumOfNameLengths:" + sumOfNameLengths);

        Integer maxNum = nums.stream()
                .reduce((a, b) -> Math.max(a,b))
                //.reduce(Integer::max)
                .orElseThrow();

        System.out.println("MaxNum: " + maxNum);

        Set<Character> uniqueCharsInListOfString = names.stream()
                .flatMap(name -> name.chars().mapToObj(c -> (char) c))
                .collect(Collectors.toSet());

        System.out.println("uniqueCharsInListOfString: " + uniqueCharsInListOfString);


        // Advance Level

        Map<String, List<String>> groupNameByFirstLetter = names.stream()
                .collect(Collectors.groupingBy(name -> name.substring(0,1)));

        System.out.println("Group by Name : " + groupNameByFirstLetter);

    }
}