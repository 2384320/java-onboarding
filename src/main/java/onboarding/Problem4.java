package onboarding;

public class Problem4 {
    public static String solution(String word) {
        String answer = "";
        StringBuilder greenFrogString = new StringBuilder();

        for (char alphabet : word.toCharArray()) {
            // 해당 글자가 공백이 아니면 inputReverse 함수를 거치도록 함.
            if (!isBlank(alphabet)) alphabet = inputReverse(alphabet);
            greenFrogString.append(alphabet);
        }

        answer = greenFrogString.toString();
        return answer;
    }

    private static char inputReverse(char alphabet) {
        // 해당 문자가 소문자라면 소문자 범위에서 변하도록 했고,
        // 대문자라면 대문자 범위에서 변하도록 함.
        // 이 부분에서는 아스키 코드를 활용함.
        if (Character.isLowerCase(alphabet)) alphabet = (char)('z' - alphabet + 'a');
        else alphabet = (char)('Z' - alphabet + 'A');
        return alphabet;
    }

    private static boolean isBlank(char alphabet) {
        // 해당 글자가 빈칸이면 true 반환
        return alphabet == ' ';
    }
}
