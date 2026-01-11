package Day_4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPart1 {
    static int accesibleRols;
    static ArrayList<ArrayList<Character>> grid = new ArrayList<>();

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        readFile();
        System.out.println(grid.toString());

        countAccesibleRols();

        System.out.println("Total Accesible Rols: " + accesibleRols);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // in milliseconds
        System.out.println("Execution time: " + duration + " ms");
    }

    private static void countAccesibleRols() {
        int count = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                count = 0;
                char current = grid.get(i).get(j);
                if (current == '@') {

                    // Check upper neighbours
                    if (i > 0) {
                        char up = grid.get(i - 1).get(j);
                        if (up == '@') {
                            count++;
                        }
                        if (j > 0) {
                            char upLeft = grid.get(i - 1).get(j - 1);
                            if (upLeft == '@') {
                                count++;
                            }
                        }
                        if (j < grid.get(i).size() - 1) {
                            char upRight = grid.get(i - 1).get(j + 1);
                            if (upRight == '@') {
                                count++;
                            }
                        }
                    }
                    // Check inline neighbours
                    if (j > 0) {
                        char left = grid.get(i).get(j - 1);
                        if (left == '@') {
                            count++;
                        }
                    }
                    if (j < grid.get(i).size() - 1) {
                        char right = grid.get(i).get(j + 1);
                        if (right == '@') {
                            count++;
                        }
                    }

                    // Check lower neighbours
                    if (i < grid.size() - 1) {
                        char down = grid.get(i + 1).get(j);
                        if (down == '@') {
                            count++;
                        }
                        if (j > 0) {
                            char downLeft = grid.get(i + 1).get(j - 1);
                            if (downLeft == '@') {
                                count++;
                            }
                        }
                        if (j < grid.get(i).size() - 1) {
                            char downRight = grid.get(i + 1).get(j + 1);
                            if (downRight == '@') {
                                count++;
                            }
                        }
                    }

                    if (count < 4){
                        accesibleRols++;
                    }
                }
            }
        }
    }

    private static void readFile() {
        String line;
        try {
            Scanner scanner = new Scanner(
                    new java.io.File("AOC-2025/AoC2025/assets/RollGrid.txt"));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                ArrayList<Character> row = new ArrayList<>();
                for (char c : line.toCharArray()) {
                    row.add(c);
                }
                grid.add(row);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
