package relationship.association;

/**
 * Student knows / uses a Teacher, but neither owns the other.
 * Both have their own independent lifecycle.
 */
public class Student {

    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** Association: Student only "uses" a Teacher passed in at call time. */
    public void learnFrom(Teacher teacher) {
        System.out.println(name + " is learning from " + teacher.getName());
    }
}
