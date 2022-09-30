package problem1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(bf.readLine());
        Pair[] values = new Pair[N];
        for (int i = 0; i < N; i++) {
            final String[] s = bf.readLine().split(" ");
            final Pair pair = new Pair(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            values[i] = pair;
        }

        final List<Pair> pairs = Arrays.stream(values)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(Arrays.toString(pairs.toArray()));
        int endTime = -1;
        int count = 0;
        for (Pair pair : pairs) {
            if (endTime <= pair.start) {
                endTime = pair.end;
                count++;
            }
        }
        System.out.println(count);
    }

    static class Pair implements Comparable {
        int start;
        int end;

        private Pair(final int start, final int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        public int compareTo(final Object o) {
            Pair compared = (Pair) o;
            if (end != compared.end) {
                return end - compared.end;
            }
            return (compared.end - compared.start) - (end - start);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
