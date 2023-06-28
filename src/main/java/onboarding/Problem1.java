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
        int pobiLeftPage = pobi.get(0);
        int pobiRightPage = pobi.get(1);
        int crongLeftPage = crong.get(0);
        int crongRightPage = crong.get(1);

        if (checkPage(pobiLeftPage, pobiRightPage)
                || checkPage(crongLeftPage, crongRightPage)) return EXCEPTION;

        int pobiScore = getBiggerScore(pobiLeftPage, pobiRightPage);
        int crongScore = getBiggerScore(crongLeftPage, crongRightPage);

        return getWinner(pobiScore, crongScore);
    }

    private static int getWinner(int pobiScore, int crongScore) {
        if (pobiScore > crongScore) return POBI_WIN;
        else if (pobiScore < crongScore) return CRONG_WIN;
        return DRAW;
    }

    private static int getBiggerScore(int leftPage, int rightPage) {
        int plusValue = getPagePlusValue(leftPage) + getPagePlusValue(rightPage);
        int multiValue = getPageMultiValue(leftPage) * getPageMultiValue(rightPage);

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

    private static boolean checkPage(int leftPage, int rightPage) {
        if (leftPage % 2 == 0 || rightPage % 2 != 0) return true;
        if (rightPage - leftPage != 1) return true;
        return leftPage < FIRST_PAGE || rightPage > LAST_PAGE;
    }
}