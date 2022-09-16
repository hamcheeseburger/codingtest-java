package kakaoProblem118667;

import java.util.*;

class Solution {
    public long solution(int[] queue1, int[] queue2) {
        long answer = 0;
        Deque<Integer> que1 = new LinkedList<>();
        for (int q : queue1) {
            que1.add(q);
        }
        Deque<Integer> que2 = new LinkedList<>();
        for (int q : queue2) {
            que2.add(q);
        }
        long que1Total = 0;
        long que2Total = 0;
        for (int q : queue1) {
            que1Total += q;
        }
        for (int q : queue2) {
            que2Total += q;
        }
        long expected = (que1Total + que2Total) / 2;
        while (true) {
            // (queue1.length + queue2.length) * 2
            // 서로 뒤바뀌었다가 다시 원본으로 돌아오는 경우의 수 (모든 경우의 수)
            if (answer == (queue1.length + queue2.length) * 2) {
                return -1;
            }
            if (que1Total == expected && que2Total == expected) {
                return answer;
            }
            if (que1Total > que2Total) {
                final Integer x = que1.poll();
                que2.add(x);
                que1Total -= x;
                que2Total += x;
            } else {
                final Integer x = que2.poll();
                que1.add(x);
                que2Total -= x;
                que1Total += x;
            }
            answer++;
        }
    }
}
