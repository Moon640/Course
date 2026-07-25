/**
 * 4비트 범위에서 두 수식을 모두 만족하는 g와 h를 찾고,
 * 찾은 값으로 마지막 수식의 결과를 계산하는 프로그램이다.
 */
public class HelloBiodome05 {
    // 4비트로 표현할 수 있는 최댓값은 2^4 - 1인 15이다.
    private static final int MAX_4_BIT_VALUE = 15;

    /**
     * 0부터 15까지의 값을 모두 확인하여 두 수식을 만족하는 g와 h를 찾는다.
     *
     * @return 첫 번째 원소는 g, 두 번째 원소는 h인 정수 배열
     */
    public static int[] findGAndH() {
        for (int g = 0; g <= MAX_4_BIT_VALUE; g++) {
            for (int h = 0; h <= MAX_4_BIT_VALUE; h++) {
                /*
                 * 돌에 적힌 두 수식을 계산한다.
                 * 비교 연산자 ==가 각 수식 전체의 결과와 비교되도록 괄호를 사용했다.
                 */
                boolean firstEquation =
                        (g & 1 >> g << 2 | h + g ^ h) == 1;
                boolean secondEquation =
                        (g % 2 << h >> g | 1 & 0 ^ 0) == 2;

                // 두 수식을 동시에 만족하면 논리 연산자 &&의 결과가 true가 된다.
                if (firstEquation && secondEquation) {
                    return new int[] {g, h};
                }
            }
        }

        // 조건을 만족하는 값이 없는 경우를 나타낸다.
        return null;
    }

    /**
     * 찾은 g와 h를 세 번째 수식에 대입하여 결과를 계산하고 출력한다.
     *
     * @param g 두 수식을 만족하는 g
     * @param h 두 수식을 만족하는 h
     */
    public static void calculateAndPrintResult(int g, int h) {
        int result = (h * h + g) * (h << h) + (g << g);

        System.out.println("g = " + g);
        System.out.println("h = " + h);
        System.out.println("세 번째 수식의 결과 = " + result);
    }

    /**
     * 프로그램의 시작 지점이다.
     */
    public static void main(String[] args) {
        // 두 수식을 모두 만족하는 g와 h를 구한다.
        int[] values = findGAndH();

        // 값을 찾지 못한 예외 상황을 처리한다.
        if (values == null) {
            System.out.println("두 수식을 모두 만족하는 g와 h가 없습니다.");
            return;
        }

        // 배열에서 g와 h를 각각 숫자 타입 변수에 저장한다.
        int g = values[0];
        int h = values[1];

        // g와 h를 이용해 마지막 수식의 결과를 계산하고 출력한다.
        calculateAndPrintResult(g, h);
    }
}
