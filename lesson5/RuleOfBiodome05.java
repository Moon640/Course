class PlantHashMap<K, V> {
    private static final int SIZE = 16;
    private Entry<K, V>[] table;

    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public PlantHashMap() {
        table = (Entry<K, V>[]) new Entry[SIZE];
        System.out.println("식물 관리 시스템이 생성되었습니다.");
    }

    public int getIndex(K key) {
        if (key == null) {
            return 0;
        }
        return Math.floorMod(key.hashCode(), SIZE);
    }

    public void put(K key, V value) {
        int index = getIndex(key);

        if (table[index] != null) {
            System.out.println("인덱스 " + index + "에 기존 값이 있어 새로운 값으로 덮어씁니다.");
        }

        table[index] = new Entry<>(key, value);
        System.out.println("'" + key + "' 추가: '" + value + "'");
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        if (entry != null && isSameKey(entry.key, key)) {
            System.out.println("'" + key + "' 검색: '" + entry.value + "'");
            return entry.value;
        }

        System.out.println("'" + key + "' 검색: 해당 식물 정보가 없습니다.");
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        if (entry != null && isSameKey(entry.key, key)) {
            V removedValue = entry.value;
            table[index] = null;
            System.out.println("'" + key + "' 삭제: '" + key + "'와 그 특징이 삭제되었습니다.");
            return removedValue;
        }

        System.out.println("'" + key + "' 삭제: 해당 식물 정보가 없습니다.");
        return null;
    }

    private boolean isSameKey(K key1, K key2) {
        if (key1 == null) {
            return key2 == null;
        }
        return key1.equals(key2);
    }
}

public class RuleOfBiodome05 {
    public static void main(String[] args) {
        PlantHashMap<String, String> plantMap = new PlantHashMap<>();

        System.out.println();

        plantMap.put("장미", "장미는 관상용으로 많이 재배되는 화초 중 하나이다.");
        plantMap.put("해바라기", "해바라기는 태양을 따라 움직이는 것으로 알려져 있다.");
        plantMap.put("민들레", "민들레는 약용으로도 사용되는 풀이다.");
        plantMap.put("벚꽃", "벚꽃은 봄에 분홍색 또는 흰색 꽃을 피운다.");
        plantMap.put("소나무", "소나무는 사계절 내내 푸른 잎을 유지하는 상록수이다.");
        plantMap.put("단풍나무", "단풍나무는 가을에 잎이 붉거나 노랗게 물드는 나무이다.");
        plantMap.put("라벤더", "라벤더는 보라색 꽃과 독특한 향으로 잘 알려져 있다.");
        plantMap.put("선인장", "선인장은 건조한 환경에 적응하여 줄기에 물을 저장한다.");
        plantMap.put("대나무", "대나무는 성장 속도가 매우 빠른 식물이다.");
        plantMap.put("로즈마리", "로즈마리는 향이 강해 요리와 허브로 많이 사용된다.");

        System.out.println();
        System.out.println("(식물 특징 검색)");
        plantMap.get("장미");
        plantMap.get("해바라기");

        System.out.println();
        System.out.println("(식물 삭제)");
        plantMap.remove("민들레");

        System.out.println();
        System.out.println("(식물 이름으로 인덱스 출력)");
        System.out.println("'장미' 인덱스: " + plantMap.getIndex("장미"));
        System.out.println("'해바라기' 인덱스: " + plantMap.getIndex("해바라기"));
    }
}
