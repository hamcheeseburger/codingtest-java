package problem20115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        final int size = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Double.parseDouble(st.nextToken());
        }

        Arrays.sort(array);

        for (int i = 0; i < size - 1; i++) {
            array[size - 1] += array[i] / 2;
        }
        System.out.println(array[size - 1]);
    }
}
