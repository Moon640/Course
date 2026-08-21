import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class AnimalFrequencyAnalyzer {
    private final Map<String, Integer> animalCountMap;

    public AnimalFrequencyAnalyzer() {
        animalCountMap = new HashMap<>();
        System.out.println("동물 빈도 분석기가 생성되었습니다.");
    }

    public void analyze(String[] animals) {
        animalCountMap.clear();

        for (String animal : animals) {
            animalCountMap.put(animal, animalCountMap.getOrDefault(animal, 0) + 1);
        }

        System.out.println("동물 관찰 데이터 분석이 완료되었습니다.");
    }

    public Set<String> getMostFrequentAnimals() {
        Set<String> mostFrequentAnimals = new LinkedHashSet<>();

        if (animalCountMap.isEmpty()) {
            return mostFrequentAnimals;
        }

        int maxCount = 0;

        for (int count : animalCountMap.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }

        for (Map.Entry<String, Integer> entry : animalCountMap.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostFrequentAnimals.add(entry.getKey());
            }
        }

        return mostFrequentAnimals;
    }

    public Set<String> getUniqueAnimals(String[] animals) {
        Set<String> uniqueAnimals = new LinkedHashSet<>();

        for (String animal : animals) {
            uniqueAnimals.add(animal);
        }

        return uniqueAnimals;
    }
}

public class RuleOfBiodome06 {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("동물 데이터를 Command Line Arguments로 입력해주세요.");
            return;
        }

        if (args.length < 20) {
            System.out.println("20마리 이상의 동물 데이터를 입력해주세요. 현재 입력 수: " + args.length);
            return;
        }

        System.out.print("[");
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]);
            if (i < args.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println();

        AnimalFrequencyAnalyzer analyzer = new AnimalFrequencyAnalyzer();
        analyzer.analyze(args);

        Set<String> mostFrequentAnimals = analyzer.getMostFrequentAnimals();
        Set<String> uniqueAnimals = analyzer.getUniqueAnimals(args);

        System.out.println();
        System.out.println("가장 많이 발견된 동물 : " + String.join(", ", mostFrequentAnimals));
        System.out.println("관찰된 모든 동물 : " + String.join(", ", uniqueAnimals));
    }
}
