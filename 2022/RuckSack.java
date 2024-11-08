package adventofcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuckSack {

    private final static Map<Character, Integer> characterMap = characterMap(new HashMap<>());

    public static void main(String[] args) {

        String filePath = "/Users/raghu.kokku/code/AdventOfCode/rucksack.txt";

        List<String> ruckSacks = CodeUtility.readFile(filePath);
        int sum =0;

        for(String line : ruckSacks) {

            int median = line.length() / 2;
            String firstString = line.substring(0, median);
            String secondString = line.substring(median, line.length());

            char result = compareStringsAndReturnChar(firstString, secondString);

            if (Character.isLowerCase(result)) {
                sum += characterMap.get(result);
            } else {
                sum += (characterMap.get(Character.toLowerCase(result)) + 26);
            }
        }

        System.out.printf("Sum = %d\n", sum);
        System.out.printf("Sum of priorities = %d", sumOfPrioritiesForGroup(ruckSacks));

    }

    private static Map<Character, Integer> characterMap(Map<Character, Integer> lowercaseMap) {
        int priority =1;

        for (int i = 97; i< 124; i++) {
            lowercaseMap.put((char)i, priority++);
        }

        return lowercaseMap;
    }

    private static char compareStringsAndReturnChar(String first, String second) {
        char[] firstChars = first.toCharArray();

        for(char ch : firstChars) {
            if (second.indexOf(ch) != -1) {
                return ch;
            }
        }
        return 0;
    }

    private static void compareStringsAndPopulateMap(String first, String second, Map<Character, Integer> comparisionMap) {
        char[] firstChars = first.toCharArray();

        for(char ch : firstChars) {
            if (second.indexOf(ch) != -1) {
                int count = comparisionMap.getOrDefault(ch, 0);
                comparisionMap.put(ch, count+1);
            }
        }
    }

    private static char checkIfCharacterExists(String str, Map<Character, Integer> comparisionMap) {

        for(char ch : str.toCharArray()) {
            boolean exists = comparisionMap.entrySet().stream().anyMatch(entry -> ch == entry.getKey());
            if (exists) {
                return ch;
            }
        }
        return Character.MIN_VALUE;
    }

    private static int sumOfPrioritiesForGroup(List<String> listOfItemTypes) {

        int sum = 0;

        Map<Character, Integer> comparisionMap = new HashMap<>();

        for(int i=0;i<listOfItemTypes.size();i+=3) {

            compareStringsAndPopulateMap(listOfItemTypes.get(i), listOfItemTypes.get(i+1), comparisionMap);

            char ch = checkIfCharacterExists(listOfItemTypes.get(i+2), comparisionMap);

            if (ch != Character.MIN_VALUE) {

                if (Character.isLowerCase(ch)) {
                    sum += characterMap.get(ch);
                } else {
                    sum += (characterMap.get(Character.toLowerCase(ch)) + 26);
                }
            }
            comparisionMap.clear();
        }
        return sum;
    }
}
