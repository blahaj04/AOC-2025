package Day_5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MainPart1 {
    static ArrayList<String> ranges = new ArrayList<>();
    static ArrayList<Long> products = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) {

        readFile();

        sortRanges();

        checkProductsInRanges();

        System.out.println("Number of products in ranges: " + count);
    }

    private static void checkProductsInRanges() {
        for (Long product : products) {
            for (String range : ranges) {
                long lowerBound = Long.parseLong(range.split("-")[0]);
                long upperBound = Long.parseLong(range.split("-")[1]);

                if (product >= lowerBound && product <= upperBound) {
                    count++;
                    break;
                }
            }
        }
    }

    private static void sortRanges() {
        ranges.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return Long.compare(
                        Long.parseLong(a.split("-")[0]),
                        Long.parseLong(b.split("-")[0]));
            }
        });
    }

    private static void readFile() {
        String line;
        try {
            Scanner scanner = new Scanner(
                    new java.io.File("AOC-2025/AoC2025/assets/IngredientList.txt"));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                if (!line.trim().isEmpty()) {
                    if (line.contains("-")) {
                        ranges.add(line);
                    } else {
                        products.add(Long.parseLong(line));
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
