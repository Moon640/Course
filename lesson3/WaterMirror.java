public class WaterMirror extends AncientArtifact
        implements EnergyGenerator, WeatherController {
    private int humidity;

    public WaterMirror(String name, int humidity) {
        super(name);
        setHumidity(humidity);
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        if (humidity < 0) {
            this.humidity = 0;
        } else if (humidity > 100) {
            this.humidity = 100;
        } else {
            this.humidity = humidity;
        }
    }

    @Override
    public void describe() {
        System.out.println("\"물의 거울은 수증기를 모아 에너지를 생성하고 비와 눈을 내리게 합니다.\"");
    }

    @Override
    public void generateEnergy() {
        System.out.println("\"물의 거울을 이용해 수증기로 에너지를 생성했습니다! 현재 습도는 "
                + humidity + "%입니다.\"");
    }

    @Override
    public void controlWeather() {
        String weather = humidity >= 70 ? "비" : "눈";
        System.out.println("\"물의 거울이 습도를 이용해 " + weather + "을(를) 내리게 합니다.\"");
    }
}
