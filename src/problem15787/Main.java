package problem15787;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final String[] infos = bufferedReader.readLine().split(" ");
        int N = Integer.parseInt(infos[0]);
        int M = Integer.parseInt(infos[1]);
        final boolean[][] arrays = new boolean[N][20];

        for (int i = 0; i < M; i++) {
            final String[] line = bufferedReader.readLine().split(" ");
            final int command = Integer.parseInt(line[0]);
            final int trainNum = Integer.parseInt(line[1]);
            switch (command) {
                case 1:
                    putIn(arrays, trainNum, Integer.parseInt(line[2]));
                    break;
                case 2:
                    putOut(arrays, trainNum, Integer.parseInt(line[2]));
                    break;
                case 3:
                    push(arrays, trainNum);
                    break;
                case 4:
                    pull(arrays, trainNum);
                    break;
            }
        }

        final List<boolean[]> objects = new ArrayList<>();
        for (boolean[] array : arrays) {
            boolean flag = false;
            for (boolean[] unique : objects) {
                if(Arrays.equals(array, unique)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                objects.add(array);
            }
        }

        System.out.println(objects.size());
    }

    private static void putIn(boolean[][] arrays, int trainNum, int seatNum) {
        if (!arrays[trainNum - 1][seatNum - 1]) {
            arrays[trainNum - 1][seatNum - 1] = true;
        }
    }

    private static void putOut(boolean[][] arrays, int trainNum, int seatNum) {
        if (arrays[trainNum - 1][seatNum - 1]) {
            arrays[trainNum - 1][seatNum - 1] = false;
        }
    }

    private static void push(boolean[][] arrays, int trainNum) {
        for (int i = 18; i >= 0; i--) {
            arrays[trainNum-1][i + 1] = arrays[trainNum-1][i];
        }
        arrays[trainNum-1][0] = false;
    }

    private static void pull(boolean[][] arrays, int trainNum) {
        for (int i = 0; i <= 18; i++) {
            arrays[trainNum-1][i] = arrays[trainNum-1][i + 1];
        }
        arrays[trainNum-1][19] = false;
    }
}
