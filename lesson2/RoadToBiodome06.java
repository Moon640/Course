import java.util.Arrays;
import java.util.Locale;

/**
 * 두 물 높이 배열의 전체 평균값과 중앙값을 계산한다.
 */
public class RoadToBiodome06 {
    private static final int MIN_WATER_HEIGHT = 0;
    private static final int MAX_WATER_HEIGHT = Integer.MAX_VALUE;

    /** 대괄호 내부의 쉼표로 구분된 정수들을 배열로 변환한다. */
    public static int[] parseArray(String text) {
        String content = text.trim();

        if (content.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String[] tokens = content.split("\\s*,\\s*", -1);
        int[] values = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].trim().isEmpty()) {
                throw new IllegalArgumentException();
            }

            int value = Integer.parseInt(tokens[i].trim());

            if (value < MIN_WATER_HEIGHT || value > MAX_WATER_HEIGHT) {
                throw new IllegalArgumentException();
            }

            values[i] = value;
        }

        return values;
    }

    /** 두 배열 전체 데이터의 평균값을 계산한다. */
    public static double calculateMean(int[] firstArray, int[] secondArray) {
        long sum = 0L;

        for (int value : firstArray) {
            sum += value;
        }

        for (int value : secondArray) {
            sum += value;
        }

        return (double) sum / (firstArray.length + secondArray.length);
    }

    /**
     * 정렬된 두 배열을 합치지 않고 이진 분할하여 중앙값을 계산한다.
     * 시간 복잡도는 O(log(min(n, m)))이다.
     */
    public static double calculateMedian(int[] firstArray, int[] secondArray) {
        if (firstArray.length > secondArray.length) {
            return calculateMedian(secondArray, firstArray);
        }

        int n = firstArray.length;
        int m = secondArray.length;
        int left = 0;
        int right = n;
        int leftPartitionSize = (n + m + 1) / 2;

        while (left <= right) {
            int firstPartition = left + (right - left) / 2;
            int secondPartition = leftPartitionSize - firstPartition;

            int firstLeft = firstPartition == 0
                    ? Integer.MIN_VALUE : firstArray[firstPartition - 1];
            int firstRight = firstPartition == n
                    ? Integer.MAX_VALUE : firstArray[firstPartition];
            int secondLeft = secondPartition == 0
                    ? Integer.MIN_VALUE : secondArray[secondPartition - 1];
            int secondRight = secondPartition == m
                    ? Integer.MAX_VALUE : secondArray[secondPartition];

            if (firstLeft <= secondRight && secondLeft <= firstRight) {
                if ((n + m) % 2 == 1) {
                    return Math.max(firstLeft, secondLeft);
                }

                int leftMiddle = Math.max(firstLeft, secondLeft);
                int rightMiddle = Math.min(firstRight, secondRight);
                return ((double) leftMiddle + rightMiddle) / 2.0;
            }

            if (firstLeft > secondRight) {
                right = firstPartition - 1;
            } else {
                left = firstPartition + 1;
            }
        }

        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("두 개의 물 높이 배열을 올바르게 입력해주세요.");
            return;
        }

        String input = String.join(" ", args).trim();
        int firstOpen = input.indexOf('[');
        int firstClose = input.indexOf(']', firstOpen + 1);
        int secondOpen = input.indexOf('[', firstClose + 1);
        int secondClose = input.indexOf(']', secondOpen + 1);

        boolean invalidStructure = firstOpen != 0
                || firstClose < 0
                || secondOpen < 0
                || secondClose < 0
                || !input.substring(firstClose + 1, secondOpen).trim().isEmpty()
                || !input.substring(secondClose + 1).trim().isEmpty();

        if (invalidStructure) {
            System.out.println("두 개의 물 높이 배열을 올바르게 입력해주세요.");
            return;
        }

        try {
            int[] firstArray = parseArray(input.substring(firstOpen + 1, firstClose));
            int[] secondArray = parseArray(input.substring(secondOpen + 1, secondClose));

            Arrays.sort(firstArray);
            Arrays.sort(secondArray);

            double mean = calculateMean(firstArray, secondArray);
            double median = calculateMedian(firstArray, secondArray);

            System.out.printf(Locale.US, "Mean : %.1f, Median : %.1f%n", mean, median);
        } catch (IllegalArgumentException e) {
            System.out.println("입력값이 올바르지 않습니다. 0 이상의 정수를 입력해주세요.");
        }
    }
}
