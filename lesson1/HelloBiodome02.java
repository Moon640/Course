public class HelloBiodome02 {
    public static void main(String[] args) {
        int solar = 0;
        int wind = 0;
        int geothermal = 0;

        if (args.length < 3) {
            System.out.println("태양광, 풍력, 지열 에너지 생산량을 입력해 주세요.");
            return;
        }

        try {
            solar = Integer.parseInt(args[0]);
            wind = Integer.parseInt(args[1]);
            geothermal = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("숫자가 아닌 값이 입력되었습니다.");
            return;
        }

        int total = solar + wind + geothermal;
        System.out.println("총 에너지 사용량은 " + total + "입니다.");
    }
}
