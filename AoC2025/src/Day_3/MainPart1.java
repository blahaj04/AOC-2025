package Day_3;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPart1 {
    public static ArrayList<String> batteryList = new ArrayList<>();
    public static void main(String[] args) {
        long total = 0;
        readFile();

        for(String bank : batteryList) {
            long bestBateries = sortBateries(bank);
            
            total += bestBateries;
        }
        System.out.println("Total: " + total);
    }

    private static long sortBateries(String bankStr) {
        final int K = 12;
        char[] digits = bankStr.toCharArray();
        int n = digits.length;
        if (n < K)
            return 0;

        StringBuilder sb = new StringBuilder(K);
        int start = 0;
        for (int remaining = K; remaining > 0; remaining--) {
            int end = n - remaining;
            int bestPos = start;
            char bestChar = digits[start];
            for (int i = start; i <= end; i++) {
                char c = digits[i];
                if (c > bestChar) {
                    bestChar = c;
                    bestPos = i;
                    if (bestChar == '9')
                        break; 
                }
            }
            sb.append(bestChar);
            start = bestPos + 1;
        }

        return Long.parseLong(sb.toString());
    }

    private static void readFile() {
        String[] candidates = new String[] {
            "assets\\BatteryBank.txt",
            "src\\..\\assets\\BatteryBank.txt",
            "..\\assets\\BatteryBank.txt"
        };

        java.io.File file = null;
        for (String p : candidates) {
            java.io.File f = new java.io.File(p);
            if (f.exists() && f.isFile()) {
                file = f;
                break;
            }
        }

        if (file == null) {
            System.err.println("BatteryBank.txt not found in any relative path. Tried: ");
            for (String p : candidates) System.err.println("  - " + p);
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    batteryList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            // shouldn't happen because we checked exists()
            e.printStackTrace();
        }
    }

}
