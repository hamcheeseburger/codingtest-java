package kakaoProblem92342;

import java.util.ArrayList;
import java.util.List;

class Solution {

    private final List<Info> map = new ArrayList<>();

    public int[] solution(int n, int[] info) {
        int apeachPoint = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] > 0) {
                apeachPoint += (10 - i);
            }
        }

        cal(0, info, n, 0, apeachPoint, new int[11], 0);

        int maxPoint = -1;
        int maxCount = -1;
        Info in = null;
        for (Info i : map) {
            if (maxPoint == i.pointGap && maxCount < i.count) {
                maxCount = i.count;
                in = i;
            }
            if (maxPoint < i.pointGap) {
                maxPoint = i.pointGap;
                maxCount = i.count;
                in = i;
            }
        }
        if (in == null) {
            int[] empty = {-1};
            return empty;
        }
        return in.info;
    }

    private void cal(int start, int[] info, int leftArrows, int point, int apeachPoint, int[] ryanInfo, int count) {

        if (leftArrows == 0) {
            if (apeachPoint < point) {
                map.add(new Info(point - apeachPoint, ryanInfo.clone(), count));
            }
            return;
        }
        if (start >= info.length || leftArrows < 0) {
            return;
        }

        int currentTargetPoint = 10 - start;
        // 현재 과녁을 이기고
        int apeachInfo = info[start];
        if(start == 10) {
            ryanInfo[start] = leftArrows;
            cal(start + 1, info, 0, point, apeachPoint, ryanInfo, count + 1);
        } else {
            ryanInfo[start] = apeachInfo + 1;
            if (apeachInfo == 0) {
                cal(start + 1, info, leftArrows - (apeachInfo + 1), point + currentTargetPoint, apeachPoint, ryanInfo, count + 1);
            } else {
                cal(start + 1, info, leftArrows - (apeachInfo + 1), point + currentTargetPoint, apeachPoint - currentTargetPoint, ryanInfo, count + 1);
            }

        }
        // 안 이기고
        ryanInfo[start] = 0;
        cal(start + 1, info, leftArrows, point, apeachPoint, ryanInfo, count);
    }

    class Info {
        private int pointGap;
        private int count;
        private int[] info;

        public Info(int pointGap, int[] info, int count) {
            this.pointGap = pointGap;
            this.info = info;
            this.count = count;
        }
    }
}
