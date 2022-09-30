package problem15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final String s = bf.readLine();
        final String[] split = s.split(" ");
        final int num = Integer.parseInt(split[0]);
        final int pickNum = Integer.parseInt(split[1]);

        permutation(num, 0, pickNum, new ArrayList<>());
    }

    private static void permutation(int maxNum, int pickedCount, int maxPickCount, List<String> selected) {
        if (pickedCount == maxPickCount) {
            final String join = String.join(" ", selected);
            System.out.println(join);
            return;
        }

        for (int i = 1; i <= maxNum; i++) {
            if (selected.contains(String.valueOf(i))) {
                continue;
            }
            final String wrappedCurrentNum = String.valueOf(i);
            selected.add(wrappedCurrentNum);
            permutation(maxNum, pickedCount + 1, maxPickCount, selected);
            selected.remove(wrappedCurrentNum);
            System.out.println(Arrays.toString(new int[3]));
        }
    }
}
