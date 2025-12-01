import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");

        int dialPosition = 50;
        int dialZeros = 0;

        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                char direction  = row.charAt(0);
                int amount = Integer.parseInt(row.substring(1));

                if  (direction == 'L') {
                    for (int i = amount; i > 0; i--) {
                        dialPosition--;
                        if (dialPosition < 0) {
                            dialPosition = 99;
                        }
                        if (dialPosition == 0) {
                            dialZeros++;
                        }
                    }
                } else if (direction == 'R') {
                    for (int i = amount; i > 0; i--) {
                        dialPosition++;
                        if (dialPosition > 99) {
                            dialPosition = 0;
                        }
                        if (dialPosition == 0) {
                            dialZeros++;
                        }
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println(dialZeros);
    }
}