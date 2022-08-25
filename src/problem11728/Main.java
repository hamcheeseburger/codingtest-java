package problem11728;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        final String[] sizes = bf.readLine().split(" ");
        int aSize = Integer.parseInt(sizes[0]);
        int bSize = Integer.parseInt(sizes[1]);

        final String[] aStrings = bf.readLine().split(" ");
        final int[] aArray = new int[aSize];
        for (int i = 0; i < aStrings.length; i++) {
            aArray[i] = Integer.parseInt(aStrings[i]);
        }

        final String[] bStrings = bf.readLine().split(" ");
        final int[] bArray = new int[bSize];
        for (int i = 0; i < bStrings.length; i++) {
            bArray[i] = Integer.parseInt(bStrings[i]);
        }

        final int[] newArray = new int[aSize + bSize];
        for (int i = 0; i < aSize + bSize; i++) {
            if (i < aSize) {
                newArray[i] = aArray[i];
            } else {
                newArray[i] = bArray[i - aSize];
            }
        }

        Arrays.sort(newArray);
        List<String> forPrint = new ArrayList<>();
        for (int i : newArray) {
            forPrint.add(String.valueOf(i));
        }
        System.out.println(String.join(" ", forPrint));
    }
}
