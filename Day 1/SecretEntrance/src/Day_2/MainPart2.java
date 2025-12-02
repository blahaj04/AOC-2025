package Day_2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainPart2 {
    public static ArrayList<String> idRangeList = new ArrayList<>();
    public static long answer = 0;

    public static void main(String[] args) {
        readFile();

        for (String range : idRangeList) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0].trim());
            long end = Long.parseLong(parts[1].trim());
            for (long i = start; i <= end; i++) {
                if (checkSequence(i)) {
                    answer += i;
                }
            }
        }
        System.out.println("The final answer is: " + answer);
    }

    private static boolean checkSequence(long id) {
        String idStr = Long.toString(id);
        int len = idStr.length();

        //Intenta todas las longitudes posibles de la secuencia base
        for (int seqLen = 1; seqLen <= len / 2; seqLen++) {
            if (len % seqLen == 0) {
                String pattern = idStr.substring(0, seqLen);
                StringBuilder repeated = new StringBuilder();
                int repetitions = len / seqLen;
                
                // Construye la secuencia base repetida 'repetitions' veces
                for (int i = 0; i < repetitions; i++) {
                    repeated.append(pattern);
                }
                
                // Verifica si coincide y tiene al menos 2 repeticiones
                if (repeated.toString().equals(idStr) && repetitions >= 2) {
                    return true;
                }
            }
        }
        return false;
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
