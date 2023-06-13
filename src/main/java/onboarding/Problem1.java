package onboarding;

import java.util.List;

class Problem1 {
    private static final int EXCEPTION = -1;
    private static final int POBI_WIN = 1;
    private static final int CRONG_WIN = 2;
    private static final int DRAW = 0;
    private static final int FIRST_PAGE = 1;
    private static final int LAST_PAGE = 400;

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int pobiPage1 = pobi.get(0);
        int pobiPage2 = pobi.get(1);
        int crongPage1 = crong.get(0);
        int crongPage2 = crong.get(1);

        if (checkPage(pobiPage1, pobiPage2)
                || checkPage(crongPage1, crongPage2)) return EXCEPTION;

        int pobiScore = getBiggerScore(pobiPage1, pobiPage2);
        int crongScore = getBiggerScore(crongPage1, crongPage2);

        return getWinner(pobiScore, crongScore);
    }

    private static int getWinner(int pobiScore, int crongScore) {
        if (pobiScore > crongScore) return POBI_WIN;
        else if (pobiScore < crongScore) return CRONG_WIN;
        return DRAW;
    }

    private static int getBiggerScore(int page1, int page2) {
        int plusValue = getPagePlusValue(page1) + getPagePlusValue(page2);
        int multiValue = getPageMultiValue(page1) * getPageMultiValue(page2);

        return Math.max(plusValue, multiValue);
    }

    private static int getPagePlusValue(int page) {
        int plusPage = 0;

        while (page > 0) {
            plusPage += page % 10;
            page /= 10;
        }
        return plusPage;
    }

    private static int getPageMultiValue(int page) {
        int multiPage = 1;

        while (page > 0) {
            multiPage *= page % 10;
            page /= 10;
        }
        return multiPage;
    }

    private static boolean checkPage(int page1, int page2) {
        if (page1 % 2 == 0 || page2 % 2 != 0) return true;
        if (page2 - page1 != 1) return true;
        return page1 < FIRST_PAGE || page2 > LAST_PAGE;
    }
}