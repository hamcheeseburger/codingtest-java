package programmers.dataStructure.course_30_lessons_42578;

import java.util.*;

//https://school.programmers.co.kr/learn/courses/30/lessons/42578
class Solution {

    public static void main(String[] args) {
        int result = new Solution().solution(
                new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}
        );
        System.out.println("result = " + result);
    }

    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<>();

        for (String[] cloth : clothes) {
            map.compute(cloth[1], (k, counts) -> {
                if (counts == null) {
                    return 1;
                }
                return counts + 1;
            });
        }

        for (Integer value : map.values()) {
            answer *= (value + 1);
        }

        return answer - 1;
    }
}