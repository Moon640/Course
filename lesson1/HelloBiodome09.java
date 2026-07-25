/**
 * 입력받은 높이만큼 별을 이용해 나무 모양을 출력하는 프로그램이다.
 *
 * <p>기본 실행 예시: java HelloBiodome09 8
 * <p>중앙 장식 실행 예시: java HelloBiodome09 8 "&"
 */
public class HelloBiodome09 {
    private static final int MIN_HEIGHT = 3;
    private static final int MAX_HEIGHT = 100;
    private static final String ERROR_MESSAGE =
            "잘못된 입력입니다. 3~100 사이의 숫자를 입력하세요.";

    /**
     * 입력된 높이가 나무를 그릴 수 있는 정상 범위인지 확인한다.
     *
     * @param height 사용자가 입력한 나무 높이
     * @return 3 이상 100 이하이면 true, 아니면 false
     */
    public static boolean isValidHeight(int height) {
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }

    /**
     * 지정한 횟수만큼 공백을 출력한다.
     *
     * @param count 출력할 공백의 개수
     */
    private static void printSpaces(int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(" ");
        }
    }

    /**
     * 별로 구성된 나무와 아래 중앙의 기둥을 출력한다.
     * 두 번째 입력값이 있으면 각 줄의 중앙 별을 해당 문자로 바꾼다.
     *
     * @param height 출력할 나무의 높이
     * @param decoration 중앙에 표시할 문자
     * @param hasDecoration 중앙 장식 사용 여부
     */
    public static void printTree(
            int height, char decoration, boolean hasDecoration) {
        /*
         * 별 사이에 한 칸씩 공백이 있으므로 줄이 한 단계 내려갈 때마다
         * 왼쪽 공백을 두 칸 줄여 전체 나무를 중앙에 맞춘다.
         */
        for (int row = 0; row < height; row++) {
            int leadingSpaces = (height - row - 1) * 2;
            int symbolCount = row * 2 + 1;

            printSpaces(leadingSpaces);

            for (int column = 0; column < symbolCount; column++) {
                // 장식이 있으면 현재 줄의 정중앙에 장식 문자를 출력한다.
                if (hasDecoration && column == row) {
                    System.out.print(decoration);
                } else {
                    System.out.print("*");
                }

                // 각 기호 사이에만 한 칸의 공백을 넣는다.
                if (column < symbolCount - 1) {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }

        // 가장 넓은 마지막 줄의 중앙에 맞춰 나무 기둥을 출력한다.
        printSpaces((height - 1) * 2);
        System.out.println("|");
    }

    /**
     * Command Line Arguments로 높이와 선택적인 중앙 장식을 입력받는다.
     *
     * @param args 첫 번째 값은 높이, 선택적인 두 번째 값은 중앙 장식 문자
     */
    public static void main(String[] args) {
        // 높이 외에는 중앙 장식 문자 한 개까지만 입력할 수 있다.
        if (args.length < 1
                || args.length > 2
                || (args.length == 2 && args[1].length() != 1)) {
            System.out.println(ERROR_MESSAGE);
            return;
        }

        try {
            // 문자열로 입력된 첫 번째 값을 정수 타입으로 변환한다.
            int height = Integer.parseInt(args[0]);

            if (!isValidHeight(height)) {
                System.out.println(ERROR_MESSAGE);
                return;
            }

            boolean hasDecoration = args.length == 2;
            char decoration = hasDecoration ? args[1].charAt(0) : '*';

            printTree(height, decoration, hasDecoration);
        } catch (NumberFormatException exception) {
            // 숫자로 변환할 수 없는 입력값을 처리한다.
            System.out.println(ERROR_MESSAGE);
        }
    }
}
