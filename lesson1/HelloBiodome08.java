/**
 * 공백 없이 입력된 메시지에서 사전 단어를 찾아 띄어쓰기를 추가하는 프로그램이다.
 *
 * <p>실행 예시:
 * java HelloBiodome08 weneednewsolutionforthisbiodome
 *
 * <p>출력 결과:
 * we need new solution for this biodome.
 *
 * <p>한글 보너스 실행 예시:
 * java HelloBiodome08 새로운나무를위한신속한지원감사합니다
 *
 * <p>한글 출력 결과:
 * 새로운 나무를 위한 신속한 지원 감사합니다.
 */
public class HelloBiodome08 {
    private static final int MAX_MESSAGE_LENGTH = 100;

    // 과제에서 제공한 영어 단어 사전이다.
    private static final String[] ENGLISH_DICTIONARY = {
        "hello", "where", "this", "biodome", "help", "tree", "new",
        "is", "problem", "please", "need", "we", "isn’t", "there",
        "a", "your", "any", "thanks", "the", "for", "solution",
        "can", "?", "you"
    };

    // 보너스 과제의 예시 문장을 처리하기 위한 한글 단어 사전이다.
    private static final String[] KOREAN_DICTIONARY = {
        "안녕하세요", "새로운", "나무를", "발견했습니다", "신속한",
        "지원", "감사합니다", "당신의", "도움이", "필요합니다"
    };

    /**
     * 현재 위치에서 시작하는 사전 단어 중 가장 긴 단어를 찾는다.
     *
     * @param message 공백 없는 메시지
     * @param start 검색을 시작할 문자 위치
     * @param end 문장 부호를 제외한 메시지의 끝 위치
     * @param dictionary 사용할 단어 사전
     * @return 가장 길게 일치하는 단어, 일치하는 단어가 없으면 null
     */
    public static String findLongestWord(
            String message, int start, int end, String[] dictionary) {
        String longestWord = null;

        for (int index = 0; index < dictionary.length; index++) {
            String word = dictionary[index];

            // 물음표는 단어가 아니므로 단어 검색에서 제외한다.
            if (word.length() == 1 && word.charAt(0) == '?') {
                continue;
            }

            if (start + word.length() <= end
                    && message.startsWith(word, start)) {
                if (longestWord == null
                        || word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }
        }

        return longestWord;
    }

    /**
     * StringBuilder를 이용해 사전 단어 사이에 공백을 추가한다.
     * 사전에 없는 연속 문자열은 원래 형태를 유지한다.
     *
     * @param message 공백 없이 입력된 메시지
     * @param dictionary 사용할 단어 사전
     * @return 띄어쓰기와 문장 부호가 추가된 문장
     */
    public static String addSpaces(
            String message, String[] dictionary) {
        boolean endsWithQuestionMark =
                message.charAt(message.length() - 1) == '?';
        int messageEnd = endsWithQuestionMark
                ? message.length() - 1 : message.length();

        StringBuilder result = new StringBuilder();
        int currentPosition = 0;

        while (currentPosition < messageEnd) {
            String matchedWord = findLongestWord(
                    message, currentPosition, messageEnd, dictionary);

            if (matchedWord != null) {
                appendWord(result, matchedWord);
                currentPosition += matchedWord.length();
            } else {
                /*
                 * 사전 단어가 아닌 부분은 다음 사전 단어가 나타나기 전까지
                 * 하나의 문자열로 묶어 원래 형태 그대로 출력한다.
                 */
                int unknownStart = currentPosition;
                currentPosition++;

                while (currentPosition < messageEnd
                        && findLongestWord(
                                message, currentPosition,
                                messageEnd, dictionary) == null) {
                    currentPosition++;
                }

                String unknownWord =
                        message.substring(unknownStart, currentPosition);
                appendWord(result, unknownWord);
            }
        }

        // 물음표 앞에는 공백을 넣지 않고, 평서문에는 마침표를 붙인다.
        if (endsWithQuestionMark) {
            result.append('?');
        } else {
            result.append('.');
        }

        return result.toString();
    }

    /**
     * 결과에 이미 단어가 있으면 공백을 먼저 넣고 다음 단어를 추가한다.
     */
    private static void appendWord(
            StringBuilder result, String word) {
        if (result.length() > 0) {
            result.append(' ');
        }
        result.append(word);
    }

    /**
     * 입력값이 영문 소문자와 마지막 물음표로 구성되어 있는지 확인한다.
     */
    public static boolean isValidEnglishMessage(String message) {
        for (int index = 0; index < message.length(); index++) {
            char character = message.charAt(index);
            boolean isLowercase =
                    character >= 'a' && character <= 'z';
            boolean isLastQuestionMark =
                    character == '?' && index == message.length() - 1;

            if (!isLowercase && !isLastQuestionMark) {
                return false;
            }
        }
        return true;
    }

    /**
     * 입력값에 한글이 포함되었는지 확인하여 보너스 사전 사용 여부를 결정한다.
     */
    public static boolean containsKorean(String message) {
        for (int index = 0; index < message.length(); index++) {
            char character = message.charAt(index);

            if (character >= '가' && character <= '힣') {
                return true;
            }
        }
        return false;
    }

    /**
     * Command Line Arguments로 메시지를 입력받아 띄어쓰기가 추가된 문장을 출력한다.
     */
    public static void main(String[] args) {
        if (args.length != 1 || args[0].length() == 0) {
            System.out.println("공백 없는 메시지를 입력해주세요.");
            return;
        }

        // 사용자가 입력한 메시지를 String 변수에 저장한다.
        String message = args[0];

        if (message.length() > MAX_MESSAGE_LENGTH) {
            System.out.println("메시지는 최대 100자까지 입력할 수 있습니다.");
            return;
        }

        boolean isKoreanMessage = containsKorean(message);

        if (!isKoreanMessage && !isValidEnglishMessage(message)) {
            System.out.println(
                    "메시지는 공백 없이 영문 소문자로 입력해주세요.");
            return;
        }

        String[] selectedDictionary = isKoreanMessage
                ? KOREAN_DICTIONARY : ENGLISH_DICTIONARY;
        String finalMessage = addSpaces(message, selectedDictionary);

        System.out.println(finalMessage);
    }
}
