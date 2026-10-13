package relationship;

import relationship.aggregation.Department;
import relationship.aggregation.Professor;
import relationship.association.Student;
import relationship.association.Teacher;
import relationship.composition.House;

/**
 * Demo runner for Association, Aggregation and Composition.
 */
public class RelationshipDemo {

    public static void main(String[] args) {
        System.out.println("=== Association (Student uses Teacher) ===");
        Teacher teacher = new Teacher("Mr. Sharma");
        Student student = new Student("Amit");
        student.learnFrom(teacher);
        student = null; // Teacher is unaffected
        System.out.println("Teacher still exists: " + teacher.getName());

        System.out.println("\n=== Aggregation (Department has Professors) ===");
        Professor p1 = new Professor("Dr. Rao");
        Professor p2 = new Professor("Dr. Mehta");
        Department department = new Department("Computer Science");
        department.addProfessor(p1);
        department.addProfessor(p2);
        department.printProfessors();
        department = null; // Department gone, professors survive
        System.out.println("Professors still exist: " + p1.getName() + ", " + p2.getName());

        System.out.println("\n=== Composition (House owns Rooms) ===");
        House house = new House("12 Main Street", "Bedroom", "Kitchen", "Bathroom");
        house.printRooms();
        house = null; // Rooms have no outside reference; they are gone with the House
        System.out.println("House destroyed -> its Rooms are destroyed too");
    }
}
