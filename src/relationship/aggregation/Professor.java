package relationship.aggregation;

/**
 * Professor exists independently of any Department.
 */
public class Professor {

    private final String name;

    public Professor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
