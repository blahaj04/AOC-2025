package Day_2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPart1 {
    public static ArrayList<String> idRangeList = new ArrayList<>();
    public static long answer = 0;

    public static void main(String[] args) {
        readFile();

        for (String range : idRangeList) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0].trim());
            long end = Long.parseLong(parts[1].trim());
            for (long i = start; i <= end; i++) {
                if (checkSimetry(i)) {
                    answer += i;
                }
            }
        }
        System.out.println("The final answer is: " + answer);
    }

    private static boolean checkSimetry(long id) {
        String idStr = Long.toString(id);
        int len = idStr.length();
        
        if (len % 2 != 0) {
            return false;
        }
        
        int half = len / 2;
        String firstHalf = idStr.substring(0, half);
        String secondHalf = idStr.substring(half);
        
        return firstHalf.equals(secondHalf);
    }

    private static void readFile() {
        String line;
        try {
            Scanner scanner = new Scanner(
                    new java.io.File("AOC-2025\\Day 1\\SecretEntrance\\assets\\idRanges.txt"));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                for (String part : line.split(",")) {
                    idRangeList.add(part);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
