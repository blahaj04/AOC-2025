package Day_1;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPart2 {
    public static int lock = 50;
    public static int password = 0;
    public static ArrayList<String> lockPattern = new ArrayList<>();

    public static void main(String[] args) {
        MainPart2.readFile();
        for (String dial : lockPattern) {
            int move = passToDigit(dial);
            // Count how many times 0 is reached during this rotation.
            // If move > 0 (right): we look for k in [1..move] with (lock + k) % 100 == 0
            // First positive k for right is first = (100 - lock) % 100; if first==0 -> 100
            if (move > 0) {
                int first = (100 - lock) % 100;
                if (first == 0) first = 100;
                if (move >= first) {
                    password += 1 + (move - first) / 100;
                }
            } else if (move < 0) {
                int m = -move; // magnitude of left move
                // For left moves we want k with (lock - k) % 100 == 0 -> k == lock mod100
                int first = lock % 100;
                if (first == 0) first = 100;
                if (m >= first) {
                    password += 1 + (m - first) / 100;
                }
            }

            // update lock position to normalized 0..99
            lock = ((lock + move) % 100 + 100) % 100;
        }

        System.out.println("The final password (times pointing at 0): " + password);

    }

    // checkClick removed — logic implemented directly in main for method 0x434C49434B

    private static int passToDigit(String dial) {
        int num = 0;
        if (dial.startsWith("L")) {
            num = Integer.parseInt(dial.substring(1));
            num = num * -1;
        } else if (dial.startsWith("R")) {
            num = Integer.parseInt(dial.substring(1));
        }
        return num;
    }

    private static void readFile() {
        try (Scanner scanner = new Scanner(
                new java.io.File("AOC-2025\\Day 1\\SecretEntrance\\assets\\lockPattern.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lockPattern.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
