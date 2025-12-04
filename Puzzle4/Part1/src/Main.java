import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");

        List<String> lines = new ArrayList<>();
        int removableScrolls = 0;

        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }
        }

        char[][] matrix = parseMatrix(lines);

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == '@') {
                    int count = checkAdjacent(matrix, row, col);

                    System.out.println(matrix[row][col]);
                    if (count < 4) {
                        removableScrolls++;
                        System.out.println("Removable Scrolls: " + removableScrolls);
                    }
                }
            }
        }

        System.out.println(removableScrolls);
    }

    public static char[][] parseMatrix(List<String> lines) {
        char[][] matrix = new char[lines.size()][lines.getFirst().length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                matrix[i][j] = lines.get(i).charAt(j);
            }
        }

        return matrix;
    }

    public static int checkAdjacent(char[][] matrix, int row, int col) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    System.out.print(matrix[row + i][col + j] + " : skipped");
                } else {
                    try {
                        if (matrix[row + i][col + j] == '@') {
                            count++;
                        }
                    } catch (Exception ignored) {

                    }
                }
            }
        }

        return count;
    }
}