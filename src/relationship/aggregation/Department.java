package relationship.aggregation;

import java.util.ArrayList;
import java.util.List;

/**
 * Department HAS Professors (whole-part), but does not own their lifecycle.
 * Professors are created outside and passed in; they survive the Department.
 */
public class Department {

    private final String name;
    private final List<Professor> professors = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    public void printProfessors() {
        System.out.println("Department " + name + " has:");
        for (Professor professor : professors) {
            System.out.println("  - " + professor.getName());
        }
    }
}
