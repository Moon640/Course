public abstract class AncientArtifact {
    private final String name;

    public AncientArtifact(String name) {
        this.name = name;
        System.out.println(name + " 유물이 생성되었습니다.");
    }

    public String getName() {
        return name;
    }

    public abstract void describe();
}
