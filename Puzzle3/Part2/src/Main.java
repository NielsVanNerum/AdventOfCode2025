import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");

        long total = 0;

        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                System.out.println(line);
                total = total + biggestCombination(getEveryCharAsInt(line), 12);
            }
        }

        System.out.println(total);
    }

    static List<Integer> getEveryCharAsInt(String line) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            list.add(Integer.parseInt(line.charAt(i) + ""));
        }

        return list;
    }

    static long biggestCombination(List<Integer> list, int numberAmount) {
        StringBuilder number = new StringBuilder();
        int currentSearchStart = 0;

        for (int i = 0; i < numberAmount; i++) {
            int numbersNeededAfterThis = (numberAmount - 1) - i;
            int searchEndIndex = list.size() - numbersNeededAfterThis;

            List<Integer> subList = list.subList(currentSearchStart, searchEndIndex);

            int maxVal = -1;
            int maxIndexRel = -1;

            for (int j = 0; j < subList.size(); j++) {
                if (subList.get(j) > maxVal) {
                    maxVal = subList.get(j);
                    maxIndexRel = j;
                }
            }

            number.append(maxVal);
            currentSearchStart = currentSearchStart + maxIndexRel + 1;
        }

        return Long.parseLong(number.toString());
    }
}