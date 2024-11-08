package adventofcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
/*
 * 1) Read all lines from an input file.
 * 2) Iterate through each line and sum the values  until you encounter new line
 * 3) Calculate the max as we sum the values for each elf
 */

public class ElfCalorieCounter {

    public static void main(String[] args) {

        final String filePath = "/Users/raghu.kokku/code/AdventOfCode/ElfCalorieCounter.txt";

        int maxCalories = Integer.MIN_VALUE;

        try {
            List<String> allInputLines = Files.readAllLines(Paths.get(filePath));
            List<Integer> listOfItems = new ArrayList<>();
            Set<Integer> maxCaloriesOfEachElf = new HashSet<>();

            for (String line : allInputLines) {

                if(StringUtils.isBlank(line)) {
                    maxCaloriesOfEachElf.add(caloriesForEachElf(listOfItems));
                    maxCalories = Math.max(maxCalories, caloriesForEachElf(listOfItems));
                    listOfItems.clear();
                } else {
                    listOfItems.add(Integer.valueOf(line));
                }
            }

           System.out.printf("Max calories = %d\n", maxCalories);

           System.out.printf("Max calories of first three = %d", sumOfTopThree(maxCaloriesOfEachElf));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int caloriesForEachElf(List<Integer> calories) {
        return calories.stream().mapToInt(c -> c).sum();
    }

    private static int sumOfTopThree(Set<Integer> maxCalories) {

        List<Integer> listOfCalories = new ArrayList<>(maxCalories);

        Collections.sort(listOfCalories, Collections.reverseOrder());

        return (listOfCalories.get(0) + listOfCalories.get(1) + listOfCalories.get(2));
    }

}
