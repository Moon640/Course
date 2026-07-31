public class RoadToBiodome05 {
    private static final int MIN_WATER_HEIGHT = 0;
    private static final int MAX_WATER_HEIGHT = Integer.MAX_VALUE;

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

    public static int[] mergeArrays(int[] firstArray, int[] secondArray) {
        int[] mergedArray = new int[firstArray.length + secondArray.length];
        int index = 0;

        for (int value : firstArray) {
            mergedArray[index++] = value;
        }

        for (int value : secondArray) {
            mergedArray[index++] = value;
        }

        return mergedArray;
    }

    public static void quickSort(int[] values, int left, int right) {
        if (left >= right) {
            return;
        }

        int leftIndex = left;
        int rightIndex = right;
        int pivot = values[left + (right - left) / 2];

        while (leftIndex <= rightIndex) {
            while (values[leftIndex] < pivot) {
                leftIndex++;
            }

            while (values[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                int temporary = values[leftIndex];
                values[leftIndex] = values[rightIndex];
                values[rightIndex] = temporary;
                leftIndex++;
                rightIndex--;
            }
        }

        if (left < rightIndex) {
            quickSort(values, left, rightIndex);
        }

        if (leftIndex < right) {
            quickSort(values, leftIndex, right);
        }
    }

    public static String formatArray(int[] values) {
        StringBuilder result = new StringBuilder("[");

        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                result.append(",");
            }

            result.append(values[i]);
        }

        result.append("]");
        return result.toString();
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
            int[] mergedArray = mergeArrays(firstArray, secondArray);

            quickSort(mergedArray, 0, mergedArray.length - 1);
            System.out.println(formatArray(mergedArray));
        } catch (IllegalArgumentException e) {
            System.out.println("입력값이 올바르지 않습니다. 0 이상의 정수를 입력해주세요.");
        }
    }
}
