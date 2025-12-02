package Day_1;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPart1 {
    public static void main(String[] args) throws Exception {
        int lock = 50;
        int password = 0;
        ArrayList<String> lockPattern = new ArrayList<>();
        readFile(lockPattern);
        for (String dial : lockPattern) {
            int move = passToDigit(dial); // L -> negative, R -> positive
            int raw = lock + move;
            // normalize to 0..99
            int norm = ((raw % 100) + 100) % 100;
            if (norm == 0) {
                password++;
            }
            // print per-step trace (raw and normalized)
            System.out.println(dial + ": raw=" + raw + " norm=" + norm + " zeros=" + password);
            lock = norm;
        }

        System.out.println("The final password (times pointing at 0): " + password);
        System.out.println("Final lock position (0..99): " + lock);

    }

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

    private static void readFile(ArrayList<String> lockPattern) {
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
