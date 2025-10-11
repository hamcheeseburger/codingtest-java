package programmers.course_30_lessons_29909;

import java.util.Stack;
//https://school.programmers.co.kr/learn/courses/30/lessons/12909
class Solution {

    public static void main(String[] args) {
        boolean result = new Solution().solution("(())()");
        System.out.println("result = " + result);
    }

    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(character);
                continue;
            }

            char peeked = stack.peek();
            if (character == ')' && peeked == '(') {
                stack.pop();
            } else {
                stack.push(character);
            }
        }

        return stack.isEmpty();
    }
}

/*
*  처음에 String s를 split("") 하여 String 배열로 문제를 풀었는데, 시간 효율성 문제에서 통과를 못했었다.
*  문자열이 길어지다 보니 배열로 변경하고, char 를 String 객체로 생성하는데에 시간이 많이 걸리는 것 같다.
*  char 자체로 활용하는 것으로 바꾸니 시간효율성 문제를 통과했음.
* */
