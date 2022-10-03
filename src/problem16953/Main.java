package problem16953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        final String[] s = bf.readLine().split(" ");
        final long base = Long.parseLong(s[0]);
        final String s1 = s[1];
        long purpose = Long.parseLong(s1);

        int count = 0;
        while (true) {
            if (purpose == base) {
                System.out.println(count + 1);
                return;
            }
            final String string = String.valueOf(purpose);
            int length = string.length();
            final int end = Integer.parseInt(string.substring(length - 1));

            if (purpose < base || (purpose % 2 != 0 && end != 1)) {
                System.out.println(-1);
                return;
            }
            if (purpose % 2 == 0) {
                purpose /= 2;
            } else {
                purpose *= 0.1;
            }
            count++;
        }

    }
}
