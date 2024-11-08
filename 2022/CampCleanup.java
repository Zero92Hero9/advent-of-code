package adventofcode;

import java.util.List;

public class CampCleanup {
    public static void main(String[] args) {
        List<String> fileContents = CodeUtility.readFile("/Users/raghu.kokku/code/AdventOfCode/CampCleanup.txt");

        int count =0;
        int overlapCount = 0;


        for (String line : fileContents ) {
            if (isPair(line)) {
                count++;
            }
            if (doesOverlap(line)) {
                overlapCount++;
            }
        }

        System.out.printf("Count = %d\n", count);
        System.out.printf("Count of overlap = %d", overlapCount);

    }

    private static boolean isPair(String line) {
        String[] pairs = line.split(",");

        String[] firstPair = pairs[0].split("-");
        String[] secondPair = pairs[1].split("-");

        int firstNumberInPair1 = Integer.valueOf(firstPair[0]);
        int secondNumberInPair1 = Integer.valueOf(firstPair[1]);
        int firstNumberInPair2 = Integer.valueOf(secondPair[0]);
        int secondNumberInPair2 = Integer.valueOf(secondPair[1]);

        return ((firstNumberInPair1 <= firstNumberInPair2 && secondNumberInPair1 >= secondNumberInPair2) ||
                ((firstNumberInPair1 >= firstNumberInPair2 && secondNumberInPair1 <= secondNumberInPair2)));
    }

    private static boolean doesOverlap(String line) {
        String[] pairs = line.split(",");

        String[] firstPair = pairs[0].split("-");
        String[] secondPair = pairs[1].split("-");

        int firstNumberInPair1 = Integer.valueOf(firstPair[0]);
        int secondNumberInPair1 = Integer.valueOf(firstPair[1]);
        int firstNumberInPair2 = Integer.valueOf(secondPair[0]);
        int secondNumberInPair2 = Integer.valueOf(secondPair[1]);

       return (firstNumberInPair2 >= firstNumberInPair1 && firstNumberInPair2 <= secondNumberInPair1) ||
               (firstNumberInPair1 >= firstNumberInPair2 && firstNumberInPair1 <= secondNumberInPair2) ||
               (secondNumberInPair1 == secondNumberInPair2);
    }

}
