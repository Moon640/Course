public class Animal extends Organism {
    private String digestionType;
    private String food;

    public Animal(String name, String species, String habitat,
                  String digestionType, String food) {
        super(name, species, habitat);
        this.digestionType = digestionType;
        this.food = food;
    }

    public String getDigestionType() {
        return digestionType;
    }

    public void setDigestionType(String digestionType) {
        this.digestionType = digestionType;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    @Override
    public void displayInfo() {
        System.out.println(
                getName() + ", " + getSpecies() + ", " + getHabitat()
                        + ", " + digestionType + ", " + food
        );
    }
}
