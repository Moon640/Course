public class Plant extends Organism {
    private String floweringSeason;
    private boolean hasFruit;

    public Plant(String name, String species, String habitat,
                 String floweringSeason, boolean hasFruit) {
        super(name, species, habitat);
        this.floweringSeason = floweringSeason;
        this.hasFruit = hasFruit;
    }

    public String getFloweringSeason() {
        return floweringSeason;
    }

    public void setFloweringSeason(String floweringSeason) {
        this.floweringSeason = floweringSeason;
    }

    public boolean hasFruit() {
        return hasFruit;
    }

    public void setHasFruit(boolean hasFruit) {
        this.hasFruit = hasFruit;
    }

    @Override
    public void displayInfo() {
        String fruitInfo = hasFruit ? "열매 있음" : "열매 없음";

        System.out.println(
                getName() + ", " + getSpecies() + ", " + getHabitat()
                        + ", " + floweringSeason + ", " + fruitInfo
        );
    }
}
