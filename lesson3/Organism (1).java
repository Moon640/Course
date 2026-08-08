public class Organism {
    private String name;
    private String species;
    private String habitat;

    public Organism(String name, String species, String habitat) {
        this.name = name;
        this.species = species;
        this.habitat = habitat;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
        System.out.println("[LifeNest] " + name + " 서식지가 변경되었습니다.");
    }
}
