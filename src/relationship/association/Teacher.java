package relationship.association;

/**
 * Teacher is independent. It does not belong to any Student.
 */
public class Teacher {

    private final String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
