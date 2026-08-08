public class SolarStone extends AncientArtifact implements EnergyGenerator {
    private int lightHours;

    public SolarStone(String name, int lightHours) {
        super(name);
        setLightHours(lightHours);
    }

    public int getLightHours() {
        return lightHours;
    }

    public void setLightHours(int lightHours) {
        if (lightHours < 0) {
            this.lightHours = 0;
        } else {
            this.lightHours = lightHours;
        }
    }

    @Override
    public void describe() {
        System.out.println("\"태양의 돌은 빛을 받아 에너지를 생성하며, 빛을 받은 시간에 따라 에너지의 양이 달라집니다.\"");
    }

    @Override
    public void generateEnergy() {
        int energy = lightHours * 100;
        System.out.println("\"태양의 돌로 에너지 생성 중! "
                + lightHours + "시간의 빛을 받아 " + energy + "만큼의 에너지를 생성했습니다.\"");
    }
}
