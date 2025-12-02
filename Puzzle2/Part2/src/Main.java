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

            if (sequenceChecker(number)) {
                total = total + i;
            }
        }

        return total;
    }

    public static boolean sequenceChecker(String number) {
        float halvedLength = (float) number.length() / 2;

        boolean sequence = false;

        for (int i = 1; i <= halvedLength ; i++) {
            if (number.length() % i == 0) {
                sequence = true;
                String oldSubString = "";
                for (int j = 0; (j + i) <= number.length(); j = j + i) {
                    String currentSubString = number.substring(j, j + i);

                    if (j != 0) {
                        if (!currentSubString.equals(oldSubString)) {
                            sequence = false;
                            break;
                        }
                    }

                    oldSubString = currentSubString;
                }
                if (sequence) {
                    break;
                }
            }
        }

        return sequence;
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