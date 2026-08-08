public class BiodomeFamily05 {
    public static void main(String[] args) {
        Sorcerer ariel = new Sorcerer("아리엘");

        SolarStone solarStone = new SolarStone("태양의 돌", 8);
        WindAmulet windAmulet = new WindAmulet("바람의 부적", "강풍");
        WaterMirror waterMirror = new WaterMirror("물의 거울", 80);

        System.out.println();
        ariel.addArtifact(solarStone);
        ariel.addArtifact(windAmulet);
        ariel.addArtifact(waterMirror);

        System.out.println();
        ariel.checkAbility(solarStone);
        ariel.useEnergyAbility(solarStone);

        System.out.println();
        ariel.checkAbility(windAmulet);
        ariel.useWeatherAbility(windAmulet);

        System.out.println();
        ariel.checkAbility(waterMirror);
        ariel.useEnergyAbility(waterMirror);
        ariel.useWeatherAbility(waterMirror);
    }
}
