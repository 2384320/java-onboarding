package onboarding;

import java.util.ArrayList;
import java.util.List;

public class Problem5 {
    private static final int[] moneyUnit = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
    public static List<Integer> solution(int money) {
        List<Integer> answer = new ArrayList<>();

        for (int unit : moneyUnit) {
            answer.add(exchangeMoney(money, unit));
            money = getChange(money, unit);
        }
        return answer;
    }
    private static int getChange(int money, int unit) {
        return money % unit;
    }

    private static int exchangeMoney(int money, int unit) {
        return money / unit;
    }
}
