import java.util.ArrayList;
import java.util.List;

interface BiologicalFeature {
}

class AnimalFeature implements BiologicalFeature {
    private String behavior;
    private String reproduction;
    private String predator;
    private String prey;
    private String lifespan;

    public AnimalFeature(String behavior, String reproduction, String predator, String prey, String lifespan) {
        this.behavior = behavior;
        this.reproduction = reproduction;
        this.predator = predator;
        this.prey = prey;
        this.lifespan = lifespan;
    }

    public String getBehavior() { return behavior; }
    public void setBehavior(String behavior) { this.behavior = behavior; }

    public String getReproduction() { return reproduction; }
    public void setReproduction(String reproduction) { this.reproduction = reproduction; }

    public String getPredator() { return predator; }
    public void setPredator(String predator) { this.predator = predator; }

    public String getPrey() { return prey; }
    public void setPrey(String prey) { this.prey = prey; }

    public String getLifespan() { return lifespan; }
    public void setLifespan(String lifespan) { this.lifespan = lifespan; }

    @Override
    public String toString() {
        return behavior + ", " + reproduction + ", 포식자: " + predator
                + ", 피식자: " + prey + ", " + lifespan;
    }
}

class PlantFeature implements BiologicalFeature {
    private String flowerColor;
    private String fruit;
    private String bloomingSeason;

    public PlantFeature(String flowerColor, String fruit, String bloomingSeason) {
        this.flowerColor = flowerColor;
        this.fruit = fruit;
        this.bloomingSeason = bloomingSeason;
    }

    public String getFlowerColor() { return flowerColor; }
    public void setFlowerColor(String flowerColor) { this.flowerColor = flowerColor; }

    public String getFruit() { return fruit; }
    public void setFruit(String fruit) { this.fruit = fruit; }

    public String getBloomingSeason() { return bloomingSeason; }
    public void setBloomingSeason(String bloomingSeason) { this.bloomingSeason = bloomingSeason; }

    @Override
    public String toString() {
        return flowerColor + ", " + fruit + ", " + bloomingSeason;
    }
}

class MicrobeFeature implements BiologicalFeature {
    private String environment;
    private boolean pathogenic;
    private String metabolism;

    public MicrobeFeature(String environment, boolean pathogenic, String metabolism) {
        this.environment = environment;
        this.pathogenic = pathogenic;
        this.metabolism = metabolism;
    }

    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }

    public boolean isPathogenic() { return pathogenic; }
    public void setPathogenic(boolean pathogenic) { this.pathogenic = pathogenic; }

    public String getMetabolism() { return metabolism; }
    public void setMetabolism(String metabolism) { this.metabolism = metabolism; }

    @Override
    public String toString() {
        return environment + ", " + (pathogenic ? "병원성 있음" : "병원성 없음") + ", " + metabolism;
    }
}

class BiologicalEntity<T extends BiologicalFeature> {
    private String name;
    private String classification;
    private T feature;

    public BiologicalEntity(String name, String classification, T feature) {
        this.name = name;
        this.classification = classification;
        this.feature = feature;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getClassification() { return classification; }
    public void setClassification(String classification) { this.classification = classification; }

    public T getFeature() { return feature; }
    public void setFeature(T feature) { this.feature = feature; }

    @Override
    public String toString() {
        return name + ", " + classification + ", " + feature;
    }
}

class BiologicalSystem<T extends BiologicalFeature> {
    private final List<BiologicalEntity<? extends T>> biologicalList = new ArrayList<>();

    public BiologicalSystem() {
        System.out.println("생물정보 시스템이 생성되었습니다.");
    }

    public void add(BiologicalEntity<? extends T> entity) {
        biologicalList.add(entity);
        System.out.println("새로운 생물이 등록되었습니다 : " + entity.getName());
    }

    public void delete() {
        if (!isEmpty()) {
            BiologicalEntity<? extends T> entity =
                    biologicalList.remove(biologicalList.size() - 1);
            System.out.println("생물이 삭제 되었습니다 : " + entity.getName());
        } else {
            System.out.println("삭제할 생물 정보가 없습니다.");
        }
    }

    public void clear() {
        biologicalList.clear();
        System.out.println("모든 정보를 삭제했습니다.");
    }

    public void show() {
        if (!isEmpty()) {
            System.out.println("최신 등록 생물 : " + biologicalList.get(biologicalList.size() - 1));
        } else {
            System.out.println("등록된 생물 정보가 없습니다.");
        }
    }

    public boolean isEmpty() {
        return biologicalList.isEmpty();
    }
}

public class RuleOfBiodome04 {
    public static void main(String[] args) {
        BiologicalEntity<AnimalFeature> cat =
                new BiologicalEntity<>("고양이", "동물",
                        new AnimalFeature("귀여움", "포유류", "대형 육식동물", "쥐", "20년"));

        BiologicalEntity<AnimalFeature> zebra =
                new BiologicalEntity<>("얼룩말", "동물",
                        new AnimalFeature("잘 달린다", "포유류", "사자", "풀", "10년"));

        BiologicalEntity<PlantFeature> rosemary =
                new BiologicalEntity<>("로즈마리", "식물",
                        new PlantFeature("보라색", "열매 없음", "7월"));

        BiologicalEntity<PlantFeature> cherryBlossom =
                new BiologicalEntity<>("벚꽃", "식물",
                        new PlantFeature("분홍색", "열매 있음", "3월"));

        BiologicalEntity<MicrobeFeature> ecoli =
                new BiologicalEntity<>("이콜라이", "미생물",
                        new MicrobeFeature("약 산성", true, "호흡 및 발효 대사"));

        BiologicalEntity<MicrobeFeature> bacillus =
                new BiologicalEntity<>("바실러스", "미생물",
                        new MicrobeFeature("약 산성", false, "호흡 대사"));

        System.out.println(cat);
        System.out.println(zebra);
        System.out.println(rosemary);
        System.out.println(cherryBlossom);
        System.out.println(ecoli);
        System.out.println(bacillus);
        System.out.println();

        BiologicalSystem<BiologicalFeature> system = new BiologicalSystem<>();
        System.out.println();

        system.add(cat);
        system.add(zebra);
        system.add(rosemary);
        system.add(cherryBlossom);
        system.add(ecoli);
        system.add(bacillus);
        System.out.println();

        system.delete();
        System.out.println();

        system.show();
        System.out.println();

        if (system.isEmpty()) {
            System.out.println("생물 정보 리스트는 비어있습니다.");
        } else {
            System.out.println("생물 정보 리스트가 비어있지 않습니다.");
        }

        System.out.println();
        system.clear();
        System.out.println();

        if (system.isEmpty()) {
            System.out.println("생물 정보 리스트는 비어있습니다.");
        } else {
            System.out.println("생물 정보 리스트가 비어있지 않습니다.");
        }
    }
}
