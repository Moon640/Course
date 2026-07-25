/**
 * 바이오돔의 온도, 습도, 산소 농도를 이용하여
 * 생명나무의 건강지수 H를 계산하는 프로그램이다.
 *
 * <p>실행 예시: java HelloBiodome03 25.5 65.0 21.0
 */
public class HelloBiodome03 {
    // 문제에서 제시한 원주율을 상수로 선언한다.
    private static final double PI = 3.14;

    // 공식에 구체적인 값이 제시되지 않은 생명계수 μB는 0.4로 설정한다.
    private static final double MU_B = 0.4;

    // 제곱근 반복 계산을 종료하기 위한 허용 오차이다.
    private static final double SQRT_PRECISION = 0.000000000000001;

    /**
     * Math.sqrt()를 사용하지 않고 뉴턴-랩슨 방법으로 습도의 제곱근을 계산한다.
     *
     * @param humidity 제곱근을 계산할 습도
     * @return 습도의 제곱근
     */
    public static double calculateSquareRoot(double humidity) {
        // 습도가 0이면 제곱근도 0이므로 바로 반환한다.
        if (humidity == 0.0) {
            return 0.0;
        }

        // 첫 번째 추정값으로 습도값 자체를 사용한다.
        double guess = humidity;
        double previousGuess;

        // 이전 추정값과 새로운 추정값의 차이가 허용 오차보다 작아질 때까지 반복한다.
        do {
            previousGuess = guess;
            guess = (guess + humidity / guess) / 2.0;
        } while (absoluteValue(guess - previousGuess) > SQRT_PRECISION);

        return guess;
    }

    /**
     * 습도의 제곱근과 온도 차이의 절대값을 계산한다.
     *
     * @param squareRootHumidity 습도의 제곱근
     * @param temperature 온도
     * @return |√습도 - 온도| 계산 결과
     */
    public static double calculateAbsoluteDifference(
            double squareRootHumidity, double temperature) {
        return absoluteValue(squareRootHumidity - temperature);
    }

    /**
     * H = μB × |√습도 - 온도| + 산소 농도 / π² 공식을 이용하여
     * 생명나무의 건강지수를 계산한다.
     *
     * @param temperature 온도
     * @param humidity 습도
     * @param oxygen 산소 농도
     * @return 생명나무의 건강지수 H
     */
    public static double calculateHealthIndex(
            double temperature, double humidity, double oxygen) {
        // 별도의 메서드를 호출하여 습도의 제곱근을 구한다.
        double squareRootHumidity = calculateSquareRoot(humidity);

        // 습도의 제곱근과 온도 차이의 절대값을 구한다.
        double temperatureDifference =
                calculateAbsoluteDifference(squareRootHumidity, temperature);

        // 주어진 건강지수 공식에 값을 대입하여 최종 결과를 반환한다.
        return MU_B * temperatureDifference + oxygen / (PI * PI);
    }

    /**
     * Math.abs()를 사용하지 않고 전달받은 값의 절대값을 구한다.
     *
     * @param value 절대값으로 변환할 값
     * @return value의 절대값
     */
    private static double absoluteValue(double value) {
        return value < 0.0 ? -value : value;
    }

    /**
     * 프로그램의 시작 지점이다.
     * Command Line Arguments로 온도, 습도, 산소 농도를 입력받는다.
     *
     * @param args [온도, 습도, 산소 농도] 순서의 문자열 배열
     */
    public static void main(String[] args) {
        // 입력값이 정확히 3개가 아니면 오류 안내 후 프로그램을 종료한다.
        if (args.length != 3) {
            printInputError();
            return;
        }

        try {
            // 문자열로 전달된 세 입력값을 double 타입으로 형변환하여 저장한다.
            double temperature = Double.parseDouble(args[0]);
            double humidity = Double.parseDouble(args[1]);
            double oxygen = Double.parseDouble(args[2]);

            /*
             * 음수 습도는 제곱근을 계산할 수 없으므로 허용하지 않는다.
             * 숫자로 표현할 수 없는 특수값 NaN과 Infinity도 잘못된 입력으로 처리한다.
             */
            if (humidity < 0.0
                    || Double.isNaN(temperature)
                    || Double.isNaN(humidity)
                    || Double.isNaN(oxygen)
                    || Double.isInfinite(temperature)
                    || Double.isInfinite(humidity)
                    || Double.isInfinite(oxygen)) {
                printInputError();
                return;
            }

            // 입력값을 이용해 최종 건강지수 H를 계산한다.
            double healthIndex =
                    calculateHealthIndex(temperature, humidity, oxygen);

            // 건강지수 H를 소수점 아래 10자리까지 출력한다.
            System.out.printf("생명지수 H = %.10f%n", healthIndex);
        } catch (NumberFormatException e) {
            // 입력값 중 하나라도 숫자로 변환할 수 없으면 오류 문구를 출력한다.
            printInputError();
        }
    }

    /**
     * 잘못된 입력에 대한 공통 안내 문구를 출력한다.
     */
    private static void printInputError() {
        System.out.println(
                "입력된 값이 올바르지 않습니다. "
                        + "[온도][습도][산소농도] 순서 대로 숫자 값을 입력해주세요");
    }
}
