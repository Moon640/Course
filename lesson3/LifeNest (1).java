import java.util.ArrayList;
import java.util.List;

public class LifeNest {
    private List<Organism> organismList;

    public LifeNest() {
        organismList = new ArrayList<>();
    }

    public void addOrganism(Organism organism) {
        organismList.add(organism);
        System.out.println("[LifeNest] " + organism.getName() + "이(가) 추가되었습니다.");
    }

    public void removeOrganism(Organism organism) {
        if (organismList.remove(organism)) {
            System.out.println("[LifeNest] " + organism.getName() + "이(가) 삭제되었습니다.");
        } else {
            System.out.println("[LifeNest] " + organism.getName() + "을(를) 찾을 수 없습니다.");
        }
    }

    public void changeHabitat(Organism organism, String newHabitat) {
        if (organismList.contains(organism)) {
            organism.setHabitat(newHabitat);
        } else {
            System.out.println("[LifeNest] " + organism.getName() + "을(를) 찾을 수 없습니다.");
        }
    }

    public void displayAll() {
        System.out.println("전체 동식물 목록 출력:");

        if (organismList.isEmpty()) {
            System.out.println("등록된 동식물이 없습니다.");
            return;
        }

        for (int i = 0; i < organismList.size(); i++) {
            Organism organism = organismList.get(i);
            System.out.println(
                    (i + 1) + ". "
                            + organism.getName() + ", "
                            + organism.getSpecies() + ", "
                            + organism.getHabitat()
            );
        }
    }
}
