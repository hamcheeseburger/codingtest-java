package programmers.greedy.course_30_lessons_42883;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/42883
public class Solution {
    public String solution(String number, int k) {
        // 이건 greedy로 푼게 아니다. 시간효율성 실패
        StringBuilder answer = new StringBuilder();

        int[] numbers = Arrays.stream(number.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] newNumbers = new int[numbers.length];

        Arrays.fill(newNumbers, -1);

        for (int count = 0; count < numbers.length - k; count++) {
            long max = 0;
            int maxIndex = -1;
            for (int i = 0; i < numbers.length; i++) {
                if (newNumbers[i] != -1) {
                    continue;
                }
                newNumbers[i] = numbers[i];
                StringBuilder s = new StringBuilder();
                for (int n : newNumbers) {
                    if (n != -1) {
                        s.append(n);
                    }
                }

                long mediumValue = Long.parseLong(s.toString());
                if (max <= mediumValue) {
                    max = mediumValue;
                    maxIndex = i;
                }
                newNumbers[i] = -1;
            }

            if (maxIndex != -1) {
                newNumbers[maxIndex] = numbers[maxIndex];
            }
        }

        for (int newNumber : newNumbers) {
            if (newNumber != -1) {
                answer.append(newNumber);
            }
        }

        return answer.toString();
    }
}
