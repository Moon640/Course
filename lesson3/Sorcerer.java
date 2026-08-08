import java.util.ArrayList;
import java.util.List;

public class Sorcerer {
    private final String name;
    private final List<AncientArtifact> artifacts;

    public Sorcerer(String name) {
        this.name = name;
        this.artifacts = new ArrayList<>();
        System.out.println("마법사 '" + name + "'이 생성되었습니다.");
    }

    public String getName() {
        return name;
    }

    public void addArtifact(AncientArtifact artifact) {
        artifacts.add(artifact);
        System.out.println("마법사 '" + name + "'이 "
                + artifact.getName() + "을(를) 소유하게 되었습니다.");
    }

    public void checkAbility(AncientArtifact artifact) {
        if (!artifacts.contains(artifact)) {
            printNotOwned(artifact);
            return;
        }

        System.out.println("마법사 '" + name + "'이 "
                + artifact.getName() + "의 능력을 확인합니다.");
        artifact.describe();
    }

    public void useEnergyAbility(AncientArtifact artifact) {
        if (!artifacts.contains(artifact)) {
            printNotOwned(artifact);
            return;
        }

        if (artifact instanceof EnergyGenerator) {
            System.out.println("마법사 '" + name + "'이 "
                    + artifact.getName() + "의 에너지 생성 능력을 사용합니다.");
            EnergyGenerator generator = (EnergyGenerator) artifact;
            generator.generateEnergy();
        } else {
            System.out.println(artifact.getName() + "에는 에너지 생성 능력이 없습니다.");
        }
    }

    public void useWeatherAbility(AncientArtifact artifact) {
        if (!artifacts.contains(artifact)) {
            printNotOwned(artifact);
            return;
        }

        if (artifact instanceof WeatherController) {
            System.out.println("마법사 '" + name + "'이 "
                    + artifact.getName() + "의 날씨 조절 능력을 사용합니다.");
            WeatherController controller = (WeatherController) artifact;
            controller.controlWeather();
        } else {
            System.out.println(artifact.getName() + "에는 날씨 조절 능력이 없습니다.");
        }
    }

    private void printNotOwned(AncientArtifact artifact) {
        System.out.println("마법사 '" + name + "'은(는) "
                + artifact.getName() + "을(를) 소유하고 있지 않습니다.");
    }
}
