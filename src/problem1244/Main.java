package problem1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final int switchSize = Integer.parseInt(bf.readLine());
        int[] switches = new int[switchSize];
        final String[] rawSwitches = bf.readLine().split(" ");
        for (int i = 0; i < rawSwitches.length; i++) {
            switches[i] = Integer.parseInt(rawSwitches[i]);
        }

        final int infoSize = Integer.parseInt(bf.readLine());
        int[][] infos = new int[infoSize][];
        for (int j = 0; j < infoSize; j++) {
            final String[] rawInfo = bf.readLine().split(" ");
            int[] info = new int[2];
            for (int i = 0; i < info.length; i++) {
                info[i] = Integer.parseInt(rawInfo[i]);
            }
            infos[j] = info;
        }

        for (int[] info : infos) {
            if (info[0] == 1) {
                int val = info[1];
                int i = 1;
                int temp = val;
                while (true) {
                    if (val > switchSize) {
                        break;
                    }

                    final int original = switches[val - 1];
                    switches[val - 1] = original == 0 ? 1 : 0;
                    i++;
                    val = temp * i;
                }
            }
            if (info[0] == 2) {
                int val = info[1];
                int frontIndex = val - 1;
                int backIndex = val + 1;
                if (frontIndex <= 0 || backIndex > switchSize) {
                    int original = switches[val - 1];
                    switches[val - 1] = original == 0 ? 1 : 0;
                } else {
                    while (true) {
                        if (frontIndex <= 0 || backIndex > switchSize || switches[frontIndex - 1] != switches[backIndex - 1]) {
                            for (int i = frontIndex + 1; i < backIndex; i++) {
                                int original = switches[i - 1];
                                switches[i - 1] = original == 0 ? 1 : 0;
                            }
                            break;
                        }

                        frontIndex--;
                        backIndex++;
                    }
                }
            }
        }

        for (int i = 0; i < switchSize; i++) {
            System.out.print(switches[i] + " ");
            if ((i + 1) % 20 == 0) {
                System.out.println();
            }
        }
    }
}
