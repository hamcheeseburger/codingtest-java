package programmers.greedy.course_30_lessons_42883;

import java.util.Arrays;
import java.util.Stack;

public class Solution_Corrected_2 {

    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();

        int[] numbers = Arrays.stream(number.split(""))
                .mapToInt(Integer::parseInt)
                .toArray();

        Stack<Integer> stack = new Stack<>();
        stack.push(numbers[0]);
        int removed = 0;

        for (int i = 1; i < numbers.length; i++) {
            while (removed < k && !stack.isEmpty() && stack.peek() < numbers[i]) {
                stack.pop();
                removed++;
            }
            stack.push(numbers[i]);
        }

        if (numbers.length == stack.size()) {
            removed = 0;
            while (removed < k) {
                stack.pop();
                removed++;
            }
        }

        while (!stack.isEmpty()) {
            answer.insert(0, stack.pop());
        }

        return answer.toString();
    }
}
