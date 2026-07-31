public class RoadToBiodome04 {

    public static void selectionSort(double[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            int minimumIndex = i;

            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[minimumIndex]) {
                    minimumIndex = j;
                }
            }

            if (minimumIndex != i) {
                double temporary = values[i];
                values[i] = values[minimumIndex];
                values[minimumIndex] = temporary;
            }
        }
    }

    public static double calculateAverage(double[] values) {
        double sum = 0.0;

        for (double value : values) {
            sum += value;
        }

        return sum / values.length;
    }

    public static double calculateMedian(double[] values) {
        int middleIndex = values.length / 2;

        if (values.length % 2 == 1) {
            return values[middleIndex];
        }

        return (values[middleIndex - 1] + values[middleIndex]) / 2.0;
    }

    public static String formatNumber(double value) {
        String result = Double.toString(value);

        if (result.endsWith(".0")) {
            return result.substring(0, result.length() - 2);
        }

        return result;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("에너지 소비 값을 입력해주세요.");
            return;
        }

        String input = String.join(" ", args)
                             .replace("[", "")
                             .replace("]", "")
                             .replace(",", " ")
                             .trim();

        if (input.isEmpty()) {
            System.out.println("에너지 소비 값을 입력해주세요.");
            return;
        }

        String[] inputValues = input.split("\\s+");
        double[] energyValues = new double[inputValues.length];

        try {
            for (int i = 0; i < inputValues.length; i++) {
                energyValues[i] = Double.parseDouble(inputValues[i]);

                if (Double.isNaN(energyValues[i]) || Double.isInfinite(energyValues[i])) {
                    System.out.println("입력값이 올바르지 않습니다. 숫자만 입력해주세요.");
                    return;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("입력값이 올바르지 않습니다. 숫자만 입력해주세요.");
            return;
        }

        selectionSort(energyValues);

        double average = calculateAverage(energyValues);
        double median = calculateMedian(energyValues);

        System.out.println("평균값 : " + formatNumber(average)
                + ", 중앙값 : " + formatNumber(median));
    }
}
