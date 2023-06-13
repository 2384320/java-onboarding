package onboarding;

public class Problem4 {
    public static String solution(String word) {
        StringBuilder greenFrogString = new StringBuilder();

        for (char alphabet : word.toCharArray()) {
            if (!isBlank(alphabet)) alphabet = inputReverse(alphabet);
            greenFrogString.append(alphabet);
        }

        return greenFrogString.toString();
    }

    private static char inputReverse(char alphabet) {
        if (Character.isLowerCase(alphabet)) alphabet = (char)('z' - alphabet + 'a');
        else alphabet = (char)('Z' - alphabet + 'A');
        return alphabet;
    }

    private static boolean isBlank(char alphabet) {
        return alphabet == ' ';
    }
}
