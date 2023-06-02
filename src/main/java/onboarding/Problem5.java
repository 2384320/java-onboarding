package onboarding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem5 {
    private static final int[] moneyUnit = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
    public static List<Integer> solution(int money) {
        List<Integer> answer = new ArrayList<>();

        for (int unit : moneyUnit) {
            // 환전할 수 있는 수를 배열에 저장
            answer.add(exchangeMoney(money, unit));
            // 환전하고 남은 돈을 거슬러 받음.
            money = getChange(money, unit);
        }

        return answer;
    }
    private static int getChange(int money, int unit) {
        // 거스름돈을 받는 작업
        return money % unit;
    }

    private static int exchangeMoney(int money, int unit) {
        // 소지 금액에서 해당 단위의 돈을 얼만큼 환전할 수 있는지 계산
        return money / unit;
    }
}
