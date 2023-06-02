package onboarding;

public class Problem2 {
    public static String solution(String cryptogram) {
        String answer = "answer";

        // hasDuplicateWord를 통해 cryptogram 안에 중복되는 글자가 존재하는지 확인
        while (hasDuplicateWord(cryptogram)) {
            // 중복되는 글자가 어디에 저장되어 있는지 boolean 배열로 나타냄.
            boolean[] checkDuplicateWordArray = checkDuplicateWord(cryptogram);
            // boolean 배열의 값을 통해 cryptogram에서 중복되는 글자를 제거함.
            cryptogram = deleteDuplicateWord(cryptogram, checkDuplicateWordArray);
        }
        answer = cryptogram;

        return answer;
    }

    private static String deleteDuplicateWord(
            String cryptogram,
            boolean[] checkDuplicateWordArray) {
        // 중복 글자를 제거한 cryptogram을 저장하는 StringBuilder
        StringBuilder deleteDuplicateCryptogram = new StringBuilder();
        int checkDuplicateWordArrayLength = checkDuplicateWordArray.length;

        // checkDuplicateWordArray에서 true가 나오는 부분은 패스하도록 함.
        // -> 입력하지 않음으로써 중복 글자를 제거
        for (int i = 0; i < checkDuplicateWordArrayLength; i++) {
            if (checkDuplicateWordArray[i]) continue;
            deleteDuplicateCryptogram.append(cryptogram.charAt(i));
        }
        return deleteDuplicateCryptogram.toString();
    }

    private static boolean[] checkDuplicateWord(String cryptogram) {
        int cryptogramLength = cryptogram.length();
        boolean[] checkDuplicateWord = new boolean[cryptogramLength];

        // cryptogram에서 중복 문자가 발견된다면
        // checkDuplicateWord의 해당 인덱스에 true 입력
        for (int i = 0; i < cryptogramLength - 1; i++) {
            if (cryptogram.charAt(i) == cryptogram.charAt(i+1)) {
                checkDuplicateWord[i] = true;
                checkDuplicateWord[i+1] = true;
                i++;
            }
        }
        return checkDuplicateWord;
    }

    private static boolean hasDuplicateWord(String cryptogram) {
        // cryptogram에서 중복 문자가 발견된다면 바로 true 반환
        for (int i = 0; i < cryptogram.length() - 1; i++) {
            if (cryptogram.charAt(i) == cryptogram.charAt(i+1)) return true;
        }

        return false;
    }
}
