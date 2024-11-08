package adventofcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonkeyMath {

    private static List<String> operators = List.of("+", "-", "/", "*");
    private static List<String> fileContents = CodeUtility.readFile("/Users/raghu.kokku/code/AdventOfCode/monkey-yells1.txt");
    private static Map<String, String> dataMap = dataMap(fileContents);

    public static void main(String[] args) {

        int sum =0;

        for (String value : dataMap.values()) {

            //sum = monkeyMath(value, sum);

        }

        System.out.println("Sum = " + sum);

    }



    private static Map<String, String> dataMap(List<String> fileContents) {
        Map<String, String> dataMap = new HashMap<>();

        for(String line : fileContents) {
            String[] keyValues = line.split(":");

            String key = keyValues[0].trim();
            String value = keyValues[1].trim();

            dataMap.put(key, value);
        }

        return dataMap;
    }
}
