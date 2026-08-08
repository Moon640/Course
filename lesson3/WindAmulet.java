public class WindAmulet extends AncientArtifact implements WeatherController {
    private String airFlow;

    public WindAmulet(String name, String airFlow) {
        super(name);
        this.airFlow = airFlow;
    }

    public String getAirFlow() {
        return airFlow;
    }

    public void setAirFlow(String airFlow) {
        this.airFlow = airFlow;
    }

    @Override
    public void describe() {
        System.out.println("\"바람의 부적은 주변 공기의 흐름을 이용해 날씨를 조절합니다.\"");
    }

    @Override
    public void controlWeather() {
        System.out.println("\"바람의 부적이 " + airFlow + "의 흐름을 이용해 날씨를 조절합니다.\"");
    }
}
