package problem1463;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<Integer, Integer> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        final int number = Integer.parseInt(bf.readLine());

        System.out.println(cal(number));
    }

    private static int cal(int x) {
        if (x == 1) {
            return 0;
        }

        if (cache.containsKey(x)) {
            return cache.get(x);
        }

        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        if (x % 2 == 0) {
            a = cal(x / 2) + 1;
        }
        if (x % 3 == 0) {
            b = cal(x / 3) + 1;
        }
        int c = cal(x - 1) + 1;

        int min = a;
        if (c < min) {
            min = c;
        }
        if (b < min) {
            min = b;
        }
        cache.put(x, min);
        return min;
    }
}
