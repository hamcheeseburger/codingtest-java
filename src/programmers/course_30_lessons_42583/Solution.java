package programmers.course_30_lessons_42583;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583?language=java
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.solution(10, 5, new int[]{2, 2, 2, 2, 1, 1, 1, 1, 1});
        System.out.println("result = " + result);
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        // truck_weights Stack으로 만든다.
        Stack<Integer> stack = new Stack<>();
        for (int i=truck_weights.length-1; i >= 0; i--) {
            stack.push(truck_weights[i]);
        }

        // weight를 초과하기 전까지 pop
        int popedElementCounts = 0;
        int popedWeights = 0;
        while (!stack.isEmpty()) {
            // System.out.println("stack :" + stack);
            int peeked = stack.peek();
            if (popedWeights + peeked <= weight) {
                popedWeights += stack.pop();
                popedElementCounts++;
            }

            if (popedWeights + peeked > weight || stack.isEmpty()) {
                // (pop한 원소의 개수-1) + bridge_length를 answer에 누적
                answer += (popedElementCounts-1 + bridge_length);
                // System.out.println("answer :" + answer);
                popedElementCounts = 0;
                popedWeights = 0;
            }
        }

        return answer + 1;
    }
}
