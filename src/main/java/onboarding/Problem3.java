package onboarding;

public class Problem3 {
    public static int solution(int number) {
        int answer = 0;

        for (int i = 1; i <= number; i++) {
            // count369를 통해 해당 숫자에 얼만큼의 3 6 9가
            // 포함되어 있는지 그 수를 반환하도록 함.
            answer += count369(i);
        }
        return answer;
    }

    public static int count369(int num) {
        int count = 0;
        while (num > 0) {
            int lastNum = num % 10;
            if (lastNum == 3 || lastNum == 6 || lastNum == 9) count++;
            num /= 10;
        }

        return count;
    }
}
