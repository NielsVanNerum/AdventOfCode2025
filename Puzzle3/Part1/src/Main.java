import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("input.txt");

        int total = 0;

        try (Scanner scanner = new Scanner(inputStream)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                System.out.println(line);
                total = total + biggestCombination(getEveryCharAsInt(line));
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

    static int biggestCombination(List<Integer> list) {
        List<Integer> subList = list.subList(0, list.size() - 1);

        NumberLocationPair one = new NumberLocationPair(0, 0);
        for (int i = 0; i < subList.size(); i++) {
            if (subList.get(i) > one.getNumber()) {
                one.setNumber(subList.get(i));
                one.setLocation(i);
            }
        }

        List<Integer> subList2 = list.subList(one.getLocation() + 1, list.size());

        NumberLocationPair two = new NumberLocationPair(0, 0);
        for (int i = 0; i < subList2.size(); i++) {
            if (subList2.get(i) > two.getNumber()) {
                two.setNumber(subList2.get(i));
                two.setLocation(i);
            }
        }

        String number = String.valueOf(one.getNumber()) + String.valueOf(two.getNumber());
        System.out.println(number);
        return Integer.parseInt(number);
    }

    public static class NumberLocationPair {
        int number;
        int location;
        public NumberLocationPair(int number, int location) {}

        public int getLocation() {
            return location;
        }
        public void setLocation(int location) {
            this.location = location;
        }
        public int getNumber() {
            return number;
        }
        public void setNumber(int number) {
            this.number = number;
        }
    }
}