package baekjun.atm_11399;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 번째 줄: 사람의 수 N
        int n = Integer.parseInt(br.readLine());

        // 두 번째 줄: 각 사람이 돈을 인출하는데 걸리는 시간
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(times);
        // 1 2 3 3 4
//        System.out.println(Arrays.toString(times));
        int totalWaitTime = 0;
        int currentWaitTime = 0;
        for (int time: times) {
            currentWaitTime += time;
            totalWaitTime += currentWaitTime;
        }

        System.out.println(totalWaitTime);
    }
}
