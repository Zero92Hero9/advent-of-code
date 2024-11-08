package adventofcode;

import java.util.List;
import java.util.stream.Collectors;

public class GrovePositioningSystem {
    public static void main(String[] args) {
        List<String> fileContents = CodeUtility.readFile("/Users/raghu.kokku/code/AdventOfCode/gps.txt");
        List<Integer> fileContentsInInts = fileContents.stream().map(Integer::parseInt).collect(Collectors.toList());
    }


}
