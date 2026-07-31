import java.util.ArrayList;
import java.util.List;

/**
 * 여러 나무의 파동수 중 한 번만 등장하는 값을 찾는 프로그램이다.
 *
 * 실행 예시:
 * java RoadToBiodome01 5 9 16 16 5 11 5 5 9 16 16 9 9
 */
public class RoadToBiodome01 {
    private static final int MIN_WAVE_NUMBER = 0;
    private static final int MAX_WAVE_NUMBER = 1000;

    /**
     * 리스트 전체를 순회하여 특정 숫자의 등장 횟수를 계산한다.
     *
     * @param waveNumbers 입력된 파동수가 저장된 리스트
     * @param target 등장 횟수를 확인할 숫자
     * @return target이 리스트에 등장한 횟수
     */
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
                // "1 2 3"뿐 아니라 "[1, 2, 3]" 형태의 명령행 입력도 처리한다.
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

        // 입력 순서를 유지하면서 한 번만 등장하는 첫 번째 숫자를 찾는다.
        for (int waveNumber : waveNumbers) {
            if (countOccurrences(waveNumbers, waveNumber) == 1) {
                System.out.println(waveNumber);
                return;
            }
        }

        System.out.println("한 번만 등장하는 숫자가 없습니다.");
    }
}
