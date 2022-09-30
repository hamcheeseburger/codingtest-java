package problem9095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<Integer, Integer> cache = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final int size = Integer.parseInt(bf.readLine());
        final int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(bf.readLine());
        }

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 4);

        for (int i = 4; i <= array[size - 1]; i++) {
            int val = cache.get(i - 1) + cache.get(i - 2) + cache.get(i - 3);
            cache.put(i, val);
        }

        for (int num : array) {
            System.out.println(cache.get(num));
        }
    }
}
