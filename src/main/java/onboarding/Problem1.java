package onboarding;

import java.util.List;

class Problem1 {

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int answer = Integer.MAX_VALUE;

        int pobiPage1 = pobi.get(0);
        int pobiPage2 = pobi.get(1);
        int crongPage1 = crong.get(0);
        int crongPage2 = crong.get(1);

        // 예외 사항 검사
        if (!checkPage(pobiPage1, pobiPage2) || !checkPage(crongPage1, crongPage2)) return -1;

        int pobiScore = Math.max(getPagePlusValue(pobiPage1, pobiPage2), getPageMultiValue(pobiPage1, pobiPage2));
        int crongScore = Math.max(getPagePlusValue(crongPage1, crongPage2), getPageMultiValue(crongPage1, crongPage2));

        if (pobiScore > crongScore) answer = 1;
        else if (pobiScore < crongScore) answer = -1;
        else answer = 0;

        return answer;
    }

    public static int getPagePlusValue(int page1, int page2) {
        int plusPage = 0;
        while (page1 > 0) {
            plusPage += page1 % 10;
            page1 /= 10;
        }

        while (page2 > 0) {
            plusPage += page2 % 10;
            page2 /= 10;
        }
        return plusPage;
    }

    public static int getPageMultiValue(int page1, int page2) {
        int multiPage = 1;

        while (page1 > 0) {
            multiPage *= page1 % 10;
            page1 /= 10;
        }

        while (page2 > 0) {
            multiPage *= page2 % 10;
            page2 /= 10;
        }
        return multiPage;
    }

    public static boolean checkPage(int page1, int page2) {
        // 예외사항 1 -> 왼쪽이 홀수, 오른쪽이 짝수가 아닌 경우
        if (page1 % 2 == 0 || page2 % 2 != 0) return false;
        // 예외사항 2 -> 왼쪽과 오른쪽의 차이가 1이 아닌 경우
        if (page2 - page1 != 1) return false;
        return true;
    }
}