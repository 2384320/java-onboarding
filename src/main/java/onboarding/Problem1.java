package onboarding;

import java.util.List;

class Problem1 {
    static final int exception = -1;
    static final int pobiWin = 1;
    static final int crongWin = 2;
    static final int draw = 0;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int answer = Integer.MAX_VALUE;

        int pobiPage1 = pobi.get(0);
        int pobiPage2 = pobi.get(1);
        int crongPage1 = crong.get(0);
        int crongPage2 = crong.get(1);

        // 예외 사항 검사
        if (!checkPage(pobiPage1, pobiPage2)
                || !checkPage(crongPage1, crongPage2)) return exception;

        // 각 선수의 점수 측정
        int pobiScore = getBiggerScore(pobiPage1, pobiPage2);
        int crongScore = getBiggerScore(crongPage1, crongPage2);

        // 각 선수의 점수를 통해 승자 결정 (무승부 가능)
        answer = getWinner(pobiScore, crongScore);

        return answer;
    }

    private static int getWinner(int pobiScore, int crongScore) {
        // 두 선수의 점수를 통해 승패를 가림
        /* 승패에 대한 변수는 상수로 표현
        * pobiWin = 1;
        * crongWin = 2;
        * draw = 0;
        */
        if (pobiScore > crongScore) return pobiWin;
        else if (pobiScore < crongScore) return crongWin;
        else return draw;
    }

    private static int getBiggerScore(int page1, int page2) {
        // 각 선수의 페이지 정보를 통해 덧셈 값이랑 곱셈 값 계산
        int plusValue = getPagePlusValue(page1) + getPagePlusValue(page2);
        int multiValue = getPageMultiValue(page1) * getPageMultiValue(page2);

        // 둘 중 더 큰 값의 선수의 점수로 반환
        return Math.max(plusValue, multiValue);
    }

    private static int getPagePlusValue(int page) {
        // 페이지에 대한 자릿수 덧셈 연산
        int plusPage = 0;

        while (page > 0) {
            plusPage += page % 10;
            page /= 10;
        }
        return plusPage;
    }

    private static int getPageMultiValue(int page) {
        // 페이지에 대한 자릿수 곱셈 연산
        int multiPage = 1;

        while (page > 0) {
            multiPage *= page % 10;
            page /= 10;
        }
        return multiPage;
    }

    private static boolean checkPage(int page1, int page2) {
        // 예외사항 1 -> 왼쪽이 홀수, 오른쪽이 짝수가 아닌 경우
        if (page1 % 2 == 0 || page2 % 2 != 0) return false;

        // 예외사항 2 -> 왼쪽과 오른쪽의 차이가 1이 아닌 경우
        if (page2 - page1 != 1) return false;

        // 예외사항 3 -> 페이지 범위가 1~400p 범위을 넘을 경우
        if (page1 < 1 || page2 > 400) return false;

        return true;
    }
}