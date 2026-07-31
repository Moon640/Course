import java.util.ArrayList;
import java.util.List;

public class RoadToBiodome01 {
    private static final int MIN_WAVE_NUMBER = 0;
    private static final int MAX_WAVE_NUMBER = 1000;

    public static int countOccurrences(List<Integer> waveNumbers, int target) {
        int count = 0;

        for (int waveNumber : waveNumbers) {
            if (waveNumber == target) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("파동수를 입력해주세요.");
            return;
        }

        List<Integer> waveNumbers = new ArrayList<>();

        try {
            for (String arg : args) {
                String cleanedArg = arg.replace("[", "")
                                       .replace("]", "")
                                       .replace(",", " ")
                                       .trim();

                if (cleanedArg.isEmpty()) {
                    continue;
                }

                String[] values = cleanedArg.split("\\s+");

                for (String value : values) {
                    int waveNumber = Integer.parseInt(value);

                    if (waveNumber < MIN_WAVE_NUMBER || waveNumber > MAX_WAVE_NUMBER) {
                        System.out.println(
                                "입력된 값의 범위가 올바르지 않습니다. "
                                + "0에서 1000까지의 값을 입력해주세요.");
                        return;
                    }

                    waveNumbers.add(waveNumber);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("입력값이 올바르지 않습니다. 숫자만 입력해주세요.");
            return;
        }

        if (waveNumbers.isEmpty()) {
            System.out.println("파동수를 입력해주세요.");
            return;
        }

        for (int waveNumber : waveNumbers) {
            if (countOccurrences(waveNumbers, waveNumber) == 1) {
                System.out.println(waveNumber);
                return;
            }
        }

        System.out.println("한 번만 등장하는 숫자가 없습니다.");
    }
}
