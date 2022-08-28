package problem11399;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        final int size = Integer.parseInt(bf.readLine());

        final StringTokenizer st = new StringTokenizer(bf.readLine());
        final int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);
        int total = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1) {
                array[i + 1] += array[i];
            }
            total += array[i];
        }
        System.out.println(total);
    }
}
