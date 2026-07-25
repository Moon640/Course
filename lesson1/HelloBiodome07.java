/**
 * 생명 나무의 DNA 염기서열에서 연속으로 반복되는 뉴클레오타이드를
 * 문자와 반복 횟수의 형태로 압축하는 프로그램이다.
 *
 * <p>실행 예시: java HelloBiodome07 JJJCCCEEHHYYYEEEEE
 */
public class HelloBiodome07 {
    /**
     * 입력된 염기서열의 공백을 제거하고 소문자를 대문자로 변환한다.
     *
     * @param dna 사용자가 입력한 염기서열
     * @return 공백이 없고 대문자로 변환된 염기서열
     */
    public static String normalizeDna(String dna) {
        String normalizedDna = "";

        for (int index = 0; index < dna.length(); index++) {
            char nucleotide = dna.charAt(index);

            // 공백 문자는 결과에 넣지 않는다.
            if (Character.isWhitespace(nucleotide)) {
                continue;
            }

            // 영어 소문자이면 대문자로 변환한다.
            if (nucleotide >= 'a' && nucleotide <= 'z') {
                nucleotide = (char) (nucleotide - ('a' - 'A'));
            }

            normalizedDna += nucleotide;
        }

        return normalizedDna;
    }

    /**
     * 염기서열이 C, Y, J, E, H로만 구성되어 있는지 검사한다.
     *
     * @param dna 검사할 염기서열
     * @return 허용된 뉴클레오타이드로만 구성되면 true, 아니면 false
     */
    public static boolean isValidDna(String dna) {
        for (int index = 0; index < dna.length(); index++) {
            char nucleotide = dna.charAt(index);

            if (nucleotide != 'C'
                    && nucleotide != 'Y'
                    && nucleotide != 'J'
                    && nucleotide != 'E'
                    && nucleotide != 'H') {
                return false;
            }
        }

        return true;
    }

    /**
     * 연속으로 반복되는 뉴클레오타이드를 문자와 반복 횟수로 압축한다.
     * 예를 들어 JJJCC는 J3C2로 변환된다.
     *
     * @param dna 공백이 제거되고 대문자로 변환된 염기서열
     * @return 압축된 염기서열
     */
    public static String compressDna(String dna) {
        String compressedDna = "";
        char currentNucleotide = dna.charAt(0);
        int repeatCount = 1;

        // 두 번째 문자부터 직전 문자와 비교하며 반복 횟수를 센다.
        for (int index = 1; index < dna.length(); index++) {
            char nextNucleotide = dna.charAt(index);

            if (currentNucleotide == nextNucleotide) {
                repeatCount++;
            } else {
                compressedDna += currentNucleotide;
                compressedDna += repeatCount;
                currentNucleotide = nextNucleotide;
                repeatCount = 1;
            }
        }

        // 반복 종료 후 남아 있는 마지막 문자와 횟수를 결과에 추가한다.
        compressedDna += currentNucleotide;
        compressedDna += repeatCount;

        return compressedDna;
    }

    /**
     * Command Line Arguments로 DNA 염기서열을 입력받아 압축 결과를 출력한다.
     *
     * @param args 입력된 DNA 염기서열
     */
    public static void main(String[] args) {
        String inputDna = "";

        // 공백으로 나뉘어 전달된 모든 입력값을 하나의 문자열로 합친다.
        for (int index = 0; index < args.length; index++) {
            inputDna += args[index];
        }

        String normalizedDna = normalizeDna(inputDna);

        // 입력값이 없거나 공백으로만 이루어진 경우를 처리한다.
        if (normalizedDna.length() == 0) {
            System.out.println("염기서열이 입력되지 않았습니다.");
            return;
        }

        // 다섯 가지 이외의 문자가 포함된 경우를 처리한다.
        if (!isValidDna(normalizedDna)) {
            System.out.println(
                    "염기서열은 C, J, H, E, Y 다섯가지로만 입력됩니다. "
                            + "확인하고 다시 입력해주세요");
            return;
        }

        // 올바른 염기서열을 압축한 최종 결과를 출력한다.
        String compressedDna = compressDna(normalizedDna);
        System.out.println(compressedDna);
    }
}
