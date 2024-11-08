package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class RockPaperScissors {
    public static void main(String[] args) {

        List<String> myOptions = List.of("X", "Y", "Z");

        Map<String, Integer> myMap = constructMap(new HashMap<>(), myOptions);

        List<String> winCombinations = List.of("AY", "BZ", "CX");
        List<String> drawCombinations = List.of("AX", "BY", "CZ");
        List<String> loseCombinations = List.of("AZ", "BX", "CY");

        int totalScore = 0;

        for (String line : readInputFile()) {

            String opponent = line.split(" ")[0];
            String player = line.split(" ")[1];

            String combination = StringUtils.join(opponent, player);

            if (winCombinations.contains(combination)) {
                totalScore += myMap.get(player) + 6;
            } else if (drawCombinations.contains(combination)) {
                totalScore += myMap.get(player) + 3;
            } else if (loseCombinations.contains(combination)) {
                totalScore += myMap.get(player);
            }
        }

        System.out.printf("Total score = %d\n", totalScore);
        System.out.printf("Total score = %d", partTwo(myMap, winCombinations, drawCombinations, loseCombinations));

    }

    private static Map<String, Integer> constructMap(Map<String, Integer> playerMap, List<String> listOfOptions) {
        int i = 1;
        for(String c : listOfOptions) {
            playerMap.put(c, i++);
        }
        return playerMap;
    }

    private static List<String> readInputFile() {
        try {
            return Files.readAllLines(Paths.get("/Users/raghu.kokku/code/AdventOfCode/RockPaperScissors.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int partTwo(Map<String, Integer> compareMap, List<String> winCombinations,
                               List<String> drawCombinations, List<String> loseCombinations) {
       int score = 0;

       for (String line : readInputFile()) {
           String opponent = line.split(" ")[0];
           String player = line.split(" ")[1];

           if (player.equals("Y")) {
               List<String> result = drawCombinations.stream().filter(s -> s.startsWith(opponent)).collect(Collectors.toList());
               char[] chars = result.get(0).toCharArray();
               score += compareMap.get(String.valueOf(chars[1])) + 3;
           } else if (player.equals("X")) {
               List<String> result = loseCombinations.stream().filter(s -> s.startsWith(opponent)).collect(Collectors.toList());
               char[] chars = result.get(0).toCharArray();
               score += compareMap.get(String.valueOf(chars[1])) + 0;
           } else if (player.equals("Z")) {
               List<String> result = winCombinations.stream().filter(s -> s.startsWith(opponent)).collect(Collectors.toList());
               char[] chars = result.get(0).toCharArray();
               score += compareMap.get(String.valueOf(chars[1])) + 6;
           }
       }

       return score;
    }


}
