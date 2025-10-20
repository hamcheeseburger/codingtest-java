package programmers.dataStructure.course_30_lessons_29909;

class Solution_NoStack {

    public static void main(String[] args) {
        boolean result = new Solution_NoStack().solution("(())()");
        System.out.println("result = " + result);
        boolean result2 = new Solution_NoStack().solution("(()(");
        System.out.println("result2 = " + result2);
    }

    boolean solution(String s) {
        int openParenthesesCount = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openParenthesesCount++;
            } else if (c == ')') {
                // 카운트가 0인데 닫는 괄호가 나오면, 올바르지 않은 괄호입니다.
                if (openParenthesesCount == 0) {
                    return false;
                }
                openParenthesesCount--;
            }
        }

        // 반복이 끝났을 때 카운트가 0이면 모든 괄호의 짝이 맞는 것입니다.
        return openParenthesesCount == 0;
    }
}
