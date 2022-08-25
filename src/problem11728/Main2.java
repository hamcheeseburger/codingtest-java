package problem11728;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

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

        int totalIndex = 0;
        int aIndex = 0;
        int bIndex = 0;

        int[] newArray = new int[aSize + bSize];
        while (totalIndex < aSize + bSize) {
            if (aIndex == aSize) {
                for (int i = bIndex; i < bSize; i++) {
                    newArray[totalIndex] = bArray[i];
                    totalIndex++;
                }
                break;
            }

            if (bIndex == bSize) {
                for (int i = aIndex; i < aSize; i++) {
                    newArray[totalIndex] = aArray[i];
                    totalIndex++;
                }
                break;
            }

            int a = aArray[aIndex];
            int b = bArray[bIndex];

            if (a < b) {
                newArray[totalIndex] = a;
                aIndex++;
            } else {
                newArray[totalIndex] = b;
                bIndex++;
            }
            totalIndex++;
        }

        for (int i : newArray) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
