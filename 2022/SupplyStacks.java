package adventofcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class SupplyStacks {
    public static void main(String[] args) {
        List<String> fileContents = CodeUtility.readFile("/Users/raghu.kokku/code/AdventOfCode/crates.txt");
        Map<Integer, Stack<String>> dataMap = constructMap();

        supplyStack(fileContents, dataMap);

        System.out.printf("Message = %s\n", peek(dataMap));
    }

    private static String peek(Map<Integer, Stack<String>> dataMap) {
        StringBuilder result = new StringBuilder();

        dataMap.entrySet().forEach(entry -> {
           Stack<String> stackInMap = entry.getValue();
           if (!stackInMap.isEmpty()) {
               result.append(stackInMap.peek());
           }
        });

        return result.toString();
    }

    private static void supplyStack(List<String> fileContents, Map<Integer, Stack<String>> dataMap) {

        for (String line : fileContents) {
            String replacedStringWithUnderscores = line.replace("move ", "").replace(" from ","_").replace(" to ","_");
            String[] digits = replacedStringWithUnderscores.split("_");

            int numberOfMoves = Integer.parseInt(digits[0]);
            int sourceStackNumber = Integer.parseInt(digits[1]);
            int destStackNumber = Integer.parseInt(digits[2]);

            Stack<String> sourceStack = dataMap.get(sourceStackNumber);
            Stack<String> destinationStack = dataMap.get(destStackNumber);

            if (numberOfMoves <= sourceStack.size()) {
                StringBuilder result = new StringBuilder();
                for (int i=0 ; i<numberOfMoves;i++) {
                    if (!sourceStack.isEmpty()) {
                        String element = sourceStack.pop();
                        result.append(element);
                    }
                }

                for (char ch : result.reverse().toString().toCharArray()) {
                    destinationStack.push(String.valueOf(ch));
                }

                dataMap.put(sourceStackNumber, sourceStack);
                dataMap.put(destStackNumber, destinationStack);
            }
        }
    }


    private static Map<Integer, Stack<String>> constructMap() {
        Map<Integer, List<String>> dataMap = new HashMap<>();

        dataMap.put(1, List.of("B", "Q", "C"));
        dataMap.put(2, List.of("R", "Q", "W", "Z"));
        dataMap.put(3, List.of("B", "M", "R", "L", "V"));
        dataMap.put(4, List.of("C", "G", "H", "V", "T", "W"));
        dataMap.put(5, List.of("D", "Z", "H", "B", "N", "V", "G"));
        dataMap.put(6, List.of("H", "N", "P", "C", "J", "F", "V", "Q"));
        dataMap.put(7, List.of("D", "G", "T", "R", "W", "Z", "S"));
        dataMap.put(8, List.of("C", "G", "M", "N", "B", "W", "Z", "P"));
        dataMap.put(9, List.of("N", "J", "B", "M", "W", "Q", "F", "P"));

        return constructStackMap(dataMap);

    }

    private static Map<Integer, Stack<String>> constructStackMap(Map<Integer, List<String>> dataMap) {

        Map<Integer, Stack<String>> stackMap = new HashMap<>();

        for(Map.Entry<Integer, List<String>> entry : dataMap.entrySet()) {

            int key = entry.getKey();
            List<String> values = entry.getValue();
            Stack<String> crates = new Stack<>();

            values.stream().forEach(s -> {
                crates.push(s);
            });

            stackMap.put(key, crates);
        }

        return stackMap;
    }

}
