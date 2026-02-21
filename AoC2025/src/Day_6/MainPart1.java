package Day_6;

import java.math.BigInteger;
import java.util.ArrayList;

public class MainPart1 {

    static ArrayList<ArrayList<Integer>> matrizNumeros = new ArrayList<>();
    static int columnLength = -1; // -1 porque solo contamos las columnas de numeros, no de operadores
    static ArrayList<Character> operadores = new ArrayList<>();

    public static void main(String[] args) {
        readfile();

        BigInteger respuesta = resolveOperations();
        System.out.println("Respuesta: " + respuesta);
    }

    private static BigInteger resolveOperations() {
        BigInteger respuesta = BigInteger.ZERO;

        int filas = matrizNumeros.size();
        int cols = matrizNumeros.get(0).size();

        if (operadores.size() != cols) {
            throw new IllegalStateException("El número de operadores no coincide con el número de columnas.");
        }
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                
            }
        }
        return respuesta;
    }

    private static void readfile() {
        String line;
        try {
            try (java.util.Scanner scanner = new java.util.Scanner(
                    new java.io.File("AOC-2025\\AoC2025\\assets\\CephalopodMathProblems.txt"))) {
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();

                    if (!line.trim().isEmpty()) {
                        ArrayList<Integer> numeros = new ArrayList<>();

                        for (String s : line.trim().split("\\s+")) {
                            if (s.isEmpty())
                                continue;
                            if (s.equals("+") || s.equals("*")) {
                                operadores.add(s.charAt(0));
                                numeros = null;
                            } else {
                                numeros.add(Integer.parseInt(s));
                            }
                        }
                        if (numeros != null) {
                            matrizNumeros.add(numeros);
                            columnLength++;
                        }
                    }
                }
            }
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
