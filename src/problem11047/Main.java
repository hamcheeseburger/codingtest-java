package problem11047;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        final String s = bf.readLine();
        final String[] s1 = s.split(" ");
        final int N = Integer.parseInt(s1[0]);
        int K = Integer.parseInt(s1[1]);

        int[] coins = new int[N];
        for (int i = 0; i < N; i++) {
            final int coin = Integer.parseInt(bf.readLine());
            coins[i] = coin;
        }

        final int[] sortedCoins = Arrays.stream(coins)
                .sorted()
                .toArray();

        int enoughCount = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (K == 0) {
                break;
            }
            if (K < sortedCoins[i]) {
                continue;
            }
            final int count = K / sortedCoins[i];
            enoughCount += count;
            K -= (count * sortedCoins[i]);
        }
        System.out.println(enoughCount);
    }
}
