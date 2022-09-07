package problem16508;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int minPrice = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final String word = bufferedReader.readLine();
        final int size = Integer.parseInt(bufferedReader.readLine());
        int[] prices = new int[size];
        String[] names = new String[size];

        for (int i = 0; i < size; i++) {
            final String[] s = bufferedReader.readLine().split(" ");
            final int price = Integer.parseInt(s[0]);
            final String name = s[1];
            prices[i] = price;
            names[i] = name;
        }
        // 1개 뽑는경우 ~ size개 뽑는 경우
        combination(0, size, new ArrayList<>(), word, names, prices, 0);
        if (minPrice == Integer.MAX_VALUE) {
            minPrice = -1;
        }
        System.out.println(minPrice);
    }

    private static void combination(int index, int size, List<Integer> selected, String word, String[] names, int[] prices, int price) {
        if (index == size) {
            // 현재의 조합이 문자열을 만족하는지 확인
            if (isEnough(word, selected, names)) {
                // 만족한다면 현재의 최소가격을 대체할 수 있는지 확인
                minPrice = Math.min(price, minPrice);
            }
            return;
        }
        // 책을 선택하고
        selected.add(index);
        combination(index + 1, size, selected, word, names, prices, price + prices[index]);
        // 선택하지 않고
        selected.remove(selected.size() - 1);
        combination(index + 1, size, selected, word, names, prices, price);
    }

    private static boolean isEnough(String word, List<Integer> indexes, String[] names) {
        int[] wordAlpha = makeAlphaCount(word);
        for (int index : indexes) {
            int[] nameAlpha = makeAlphaCount(names[index]);
            for (int i = 0; i < 26; i++) {
                final int minus = wordAlpha[i] - nameAlpha[i];
                wordAlpha[i] = Math.max(minus, 0);
            }
        }
        return Arrays.stream(wordAlpha)
                .sum() == 0;
    }

    private static int[] makeAlphaCount(String value) {
        int[] count = new int[26];
        for (int i = 0; i < value.length(); i++) {
            count[value.charAt(i) - 'A']++;
        }
        return count;
    }
}
