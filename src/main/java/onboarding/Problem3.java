package onboarding;

public class Problem3 {
    public static int solution(int number) {
        int answer = 0;

        for (int i = 1; i <= number; i++) {
            answer += count369(i);
        }
        return answer;
    }

    private static int count369(int num) {
        int count = 0;
        while (num > 0) {
            int lastNum = num % 10;
            if (lastNum == 3 || lastNum == 6 || lastNum == 9) count++;
            num /= 10;
        }

        return count;
    }
}
