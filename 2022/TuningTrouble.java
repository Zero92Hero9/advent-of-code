package adventofcode;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class TuningTrouble {
    public static void main(String[] args) {
        List<String> fileContents = CodeUtility.readFile("/Users/raghu.kokku/code/AdventOfCode/marker.txt");

        Scanner scan = new Scanner(System.in);
        int countOfDistinctCharacters = scan.nextInt();



        System.out.printf("Result = %d", findMarker(fileContents, countOfDistinctCharacters));
        System.out.printf("Result = %d", findMarkerDynamically(fileContents, countOfDistinctCharacters));

    }

    private static int findMarker(List<String> fileContents, int countOfDistinctCharacters) {
        Set<Character> setOfCharacters = new HashSet<>();

        int start = 0;
        int end = 0;

        for (String line : fileContents) {
            char[] chars = line.toCharArray();

                while (end < chars.length) {

                    if (setOfCharacters.size() == countOfDistinctCharacters) {
                        return end;
                    }

                    if (setOfCharacters.add(chars[end])) {
                        end++;
                    } else if (!setOfCharacters.add(chars[end])) {
                        start++;
                        end = start;
                        setOfCharacters.clear();
                    }
                }
        }

        return 0;
    }

    private static int findMarkerDynamically(List<String> fileContents, int countOfDistinctCharacters) {
        String markerString = StringUtils.EMPTY;

        int start = 0;
        int end = 0;

        for (String line : fileContents) {
            char[] chars = line.toCharArray();

            while (end < chars.length) {

                if (markerString.length() == countOfDistinctCharacters) {
                    return end;
                }

                if (markerString.contains(String.valueOf(chars[end]))) {
                    markerString = StringUtils.EMPTY;
                    start++;
                    end = start;
                } else {
                    markerString = StringUtils.join(markerString, chars[end]);
                    end ++;
                }
            }
        }
        return 0;
    }





}
