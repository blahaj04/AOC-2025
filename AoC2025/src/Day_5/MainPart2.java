package Day_5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MainPart2 {
    static ArrayList<String> ranges = new ArrayList<>();
    static long count = 0;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

            readFile();

            sortRanges();

            checkidsInRanges();

            long endTime = System.currentTimeMillis();
            System.out.println("Number of products in ranges: " + count);
            System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }

    private static void checkidsInRanges() {
        for (String range : ranges) {
            long lowerBound = Long.parseLong(range.split("-")[0]);
            long upperBound = Long.parseLong(range.split("-")[1]);
            System.out.println("Rango: " + lowerBound + " - " + upperBound);
            count += (upperBound - lowerBound + 1); // +1 para incluir ambos extremos
        }
    }

    private static void sortRanges() {
        // Ordenar primero
        ranges.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return Long.compare(
                        Long.parseLong(a.split("-")[0]),
                        Long.parseLong(b.split("-")[0]));
            }
        });
        
        // Unir rangos adyacentes o solapados
        ArrayList<String> mergedRanges = new ArrayList<>();
        
        for (String range : ranges) {
            long[] currentBounds = parseRange(range);
            
            if (mergedRanges.isEmpty()) {
                mergedRanges.add(range);
            } else {
                String lastRange = mergedRanges.get(mergedRanges.size() - 1);
                long[] lastBounds = parseRange(lastRange);
                
                // Si se solapan o están adyacentes (fin >= inicio - 1 del siguiente)
                if (lastBounds[1] >= currentBounds[0] - 1) {
                    // Unir: tomar el mínimo del inicio y máximo del fin
                    long newStart = Math.min(lastBounds[0], currentBounds[0]);
                    long newEnd = Math.max(lastBounds[1], currentBounds[1]);
                    mergedRanges.set(mergedRanges.size() - 1, newStart + "-" + newEnd);
                } else {
                    // No se solapan, agregar como nuevo rango
                    mergedRanges.add(range);
                }
            }
        }
        
        ranges.clear();
        ranges.addAll(mergedRanges);
    }
    
    private static long[] parseRange(String range) {
        String[] parts = range.split("-");
        return new long[]{Long.parseLong(parts[0]), Long.parseLong(parts[1])};
    }

    private static void readFile() {
        String line;
        try {
            Scanner scanner = new Scanner(
                    new java.io.File("AOC-2025/AoC2025/assets/IngredientList.txt"));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();

                if (!line.trim().isEmpty() && line.contains("-")) {
                    ranges.add(line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
