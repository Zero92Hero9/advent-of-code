package adventofcode;

import java.util.List;

public class TreeVisibility {
    public static void main(String[] args) {

        List<String> fileContents = CodeUtility.readFile("/Users/raghu.kokku/code/AdventOfCode/map1.txt");

        int[][] matrix = convertFileContentsToMatrix(fileContents);

        int edges = numberOfEdgeColumns(fileContents) + numberOfEdgeRows(fileContents);

        int visibleTrees = visibleTrees(matrix);

        System.out.println(edges+visibleTrees);
    }


    private static int visibleTrees(int[][] matrix) {
        int numberOfVisibleTrees = 0;
        int length = matrix.length - 1;

        for (int row = 1; row < length; row++ ) {
            for (int column = 1; column < length; column++) {
                if (matrix[row][column] > matrix[row-1][column] || matrix[row][column] > matrix[row][column-1]
                    || matrix[row][column] > matrix[row+1][column] || matrix[row][column] > matrix[row][column+1]) {
                    numberOfVisibleTrees++;
                }
            }
        }
        return numberOfVisibleTrees;

    }

    private static int[][] convertFileContentsToMatrix(List<String> fileContents) {

        int[][] matrix = new int[fileContents.size()][fileContents.get(0).toCharArray().length];

        for (int row = 0; row< fileContents.size(); row++) {
            char[] chars = fileContents.get(row).toCharArray();
            for (int column =0; column < chars.length; column++) {
                matrix[row][column] = Character.getNumericValue(chars[column]);
            }
        }

        return matrix;
    }

    private static int numberOfEdgeColumns(List<String> fileContents) {
        char[] chars = fileContents.get(0).toCharArray();
        return 2 * chars.length;
    }

    private static int numberOfEdgeRows(List<String> fileContents) {
        return 2 * (fileContents.size() -2);
    }

}
