/**
 * 온도, 습도, 산소 농도가 생명의 나무에 적합한 범위인지 확인하는 프로그램이다.
 *
 * <p>실행 예시: java HelloBiodome04 27 50 20.8
 */
public class HelloBiodome04 {
    /**
     * 세 환경 값이 모두 정상 범위인지 확인한다.
     *
     * @param temperature 온도
     * @param humidity 습도
     * @param oxygen 산소 농도
     * @return 세 값이 모두 정상 범위이면 true, 아니면 false
     */
    public static boolean isEnvironmentStable(
            double temperature, double humidity, double oxygen) {
        boolean isTemperatureNormal =
                temperature >= 10.0 && temperature < 27.5;
        boolean isHumidityNormal =
                humidity > 40.0 && humidity < 60.0;
        boolean isOxygenNormal =
                oxygen >= 19.5 && oxygen <= 23.5;

        return isTemperatureNormal && isHumidityNormal && isOxygenNormal;
    }

    /**
     * Command Line Arguments로 전달된 환경 값을 검사하고 결과를 출력한다.
     *
     * @param args [온도, 습도, 산소 농도] 순서의 입력값
     */
    public static void main(String[] args) {
        // 입력값이 정확히 3개가 아니면 안내 문구를 출력하고 종료한다.
        if (args.length != 3) {
            printInputError();
            return;
        }

        try {
            // 문자열로 전달된 값을 double 타입으로 형변환하여 저장한다.
            double temperature = Double.parseDouble(args[0]);
            double humidity = Double.parseDouble(args[1]);
            double oxygen = Double.parseDouble(args[2]);

            // NaN과 Infinity는 유효한 환경 측정값이 아니므로 오류로 처리한다.
            if (!isFiniteNumber(temperature)
                    || !isFiniteNumber(humidity)
                    || !isFiniteNumber(oxygen)) {
                printInputError();
                return;
            }

            // 세 값이 모두 정상 범위이면 안정 상태 문구를 출력한다.
            if (isEnvironmentStable(temperature, humidity, oxygen)) {
                System.out.println("생명의 나무는 안정적인 상태입니다 :)");
                return;
            }

            // 정상 범위를 벗어난 요소를 찾아 해당 안내 문구를 출력한다.
            if (temperature < 10.0 || temperature >= 27.5) {
                System.out.println(
                        "온도값이 정상 범위를 벗어났습니다. 확인이 필요합니다.");
            } else if (humidity <= 40.0 || humidity >= 60.0) {
                System.out.println(
                        "습도값이 정상 범위를 벗어났습니다. 확인이 필요합니다.");
            } else {
                System.out.println(
                        "산소 농도값이 정상 범위를 벗어났습니다. 확인이 필요합니다.");
            }
        } catch (NumberFormatException e) {
            // 입력값에 문자가 포함되어 숫자로 바꿀 수 없는 경우를 처리한다.
            printInputError();
        }
    }

    /**
     * 입력값이 NaN이나 Infinity가 아닌 유한한 숫자인지 확인한다.
     */
    private static boolean isFiniteNumber(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value);
    }

    /**
     * 입력 형식이 잘못된 경우 공통 안내 문구를 출력한다.
     */
    private static void printInputError() {
        System.out.println(
                "입력된 값이 올바르지 않습니다. "
                        + "[온도][습도][산소농도] 순서대로 숫자 값을 입력해주세요");
    }
}
