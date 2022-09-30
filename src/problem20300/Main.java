package problem20300;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final int totalCount = Integer.parseInt(bf.readLine());
        final String value = bf.readLine();
        final List<Long> miss = Arrays.stream(value.split(" "))
                .map(Long::parseLong)
                .sorted()
                .collect(Collectors.toList());

        int even = totalCount - (totalCount % 2);
        long max = 0;
        int evenIndex = even - 1;
        for (int i = 0; i < even / 2; i++) {
            long total = miss.get(i) + miss.get(evenIndex);
            if (max < total) {
                max = total;
            }
            evenIndex--;
        }

        if (totalCount > even && max < miss.get(totalCount - 1)) {
            max = miss.get(totalCount - 1);
        }

        System.out.println(max);
    }
}
