package problem1541;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        final String line = bf.readLine();
        final String[] split = line.split("-");
        List<Integer> totalNumbers = new ArrayList<>();
        for(String s : split) {
            final String[] numbers = s.split("\\+");
            final int sum = Arrays.stream(numbers)
                    .mapToInt(Integer::parseInt)
                    .sum();
            totalNumbers.add(sum);
        }

        int total = totalNumbers.get(0);
        for(int i=1; i < totalNumbers.size(); i++) {
            total -= totalNumbers.get(i);
        }
        System.out.println(total);
    }
}
