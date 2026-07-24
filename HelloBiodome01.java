public class HelloBiodome01 {
    public static void main(String[] args) {
        String name = "";

        if (args.length > 0) {
            name = args[0];
        }

        if (name.length() < 1) {
            System.out.println("이름을 1글자 이상 입력해 주세요.");
            return;
        }

        System.out.println(name + "님, 환영합니다!");
    }
}
