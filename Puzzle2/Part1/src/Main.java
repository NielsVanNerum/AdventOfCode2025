import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        long total = 0;

        String input = readResourceFile("input.txt").trim();
        String[] inputArray = input.split(",");

        for (String s : inputArray) {
            total = total + processIdRange(s);
        }

        System.out.println(total);
    }

    public static long processIdRange(String range) {
        long total = 0;

        String[] rangeArray = range.split("-");

        long start = Long.parseLong(rangeArray[0]);
        long end = Long.parseLong(rangeArray[1]);

        for (long i = start; i <= end; i++) {
            String number = String.valueOf(i);

            if (number.length() % 2 == 0) {
                String numberFirst = number.substring(0, number.length() / 2);
                String numberSecond = number.substring(number.length() / 2);

                if (numberFirst.equals(numberSecond)) {
                    total = total + i;
                }
            }
        }

        return total;
    }

    public static String readResourceFile(String resourceName) throws IOException {
        ClassLoader classLoader = Main.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourceName);
            }

            byte[] bytes = inputStream.readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }
}