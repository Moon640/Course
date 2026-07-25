/**
 * 두 유전자 코드를 한 문자씩 비교하여 동일한 코드인지 판별하는 프로그램이다.
 *
 * <p>실행 예시: java HelloBiodome06 sfd215j sfd215j
 */
public class HelloBiodome06 {
    private static final int MIN_CODE_LENGTH = 5;
    private static final int MAX_CODE_LENGTH = 20;

    /**
     * 두 유전자 코드의 길이와 각 문자를 순서대로 비교한다.
     * String 클래스의 equals() 메서드를 사용하지 않고 while문으로 확인한다.
     *
     * @param firstCode 첫 번째 유전자 코드
     * @param secondCode 두 번째 유전자 코드
     * @return 두 코드가 완전히 같으면 true, 다르면 false
     */
    public static boolean areGenesEqual(
            String firstCode, String secondCode) {
        // 문자열의 길이가 다르면 동일한 코드가 될 수 없다.
        if (firstCode.length() != secondCode.length()) {
            return false;
        }

        int index = 0;

        // 같은 위치에 있는 문자를 처음부터 끝까지 순차적으로 비교한다.
        while (index < firstCode.length()) {
            if (firstCode.charAt(index) != secondCode.charAt(index)) {
                return false;
            }
            index++;
        }

        // 길이와 모든 문자가 같으면 동일한 유전자 코드이다.
        return true;
    }

    /**
     * 유전자 코드가 5~20자이며 숫자와 영어 소문자로만 구성되었는지 확인한다.
     *
     * @param code 검사할 유전자 코드
     * @return 올바른 형식이면 true, 아니면 false
     */
    public static boolean isValidGeneCode(String code) {
        if (code.length() < MIN_CODE_LENGTH
                || code.length() > MAX_CODE_LENGTH) {
            return false;
        }

        int index = 0;

        while (index < code.length()) {
            char nucleotide = code.charAt(index);
            boolean isLowercaseLetter =
                    nucleotide >= 'a' && nucleotide <= 'z';
            boolean isNumber =
                    nucleotide >= '0' && nucleotide <= '9';

            if (!isLowercaseLetter && !isNumber) {
                return false;
            }
            index++;
        }

        return true;
    }

    /**
     * Command Line Arguments로 두 유전자 코드를 입력받아 비교 결과를 출력한다.
     *
     * @param args 비교할 두 개의 유전자 코드
     */
    public static void main(String[] args) {
        // 두 개의 입력값이 들어오지 않으면 안내 후 프로그램을 종료한다.
        if (args.length != 2) {
            System.out.println("두 개의 유전자 코드를 입력해주세요.");
            return;
        }

        // 참조 자료형인 String 변수에 두 유전자 코드를 저장한다.
        String firstGeneCode = args[0];
        String secondGeneCode = args[1];

        // 길이 또는 구성 문자가 잘못된 유전자 코드는 비교하지 않는다.
        if (!isValidGeneCode(firstGeneCode)
                || !isValidGeneCode(secondGeneCode)) {
            System.out.println(
                    "유전자 코드는 5~20자의 영어 소문자와 숫자로 입력해주세요.");
            return;
        }

        // 각 문자를 직접 비교한 boolean 결과에 따라 문구를 출력한다.
        if (areGenesEqual(firstGeneCode, secondGeneCode)) {
            System.out.println("동일한 유전자 코드입니다.");
        } else {
            System.out.println("일치하지 않습니다.");
        }
    }
}
