package behavioral.iterator.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Element / Node in the hierarchy tree.
 */
public class Employee {
    private final String id;
    private final String name;
    private final String title;
    private final String department;
    private final List<Employee> directReports = new ArrayList<>();

    public Employee(String id, String name, String title, String department) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.department = department;
    }

    public void addReport(Employee subordinate) {
        if (subordinate != null && !directReports.contains(subordinate)) {
            directReports.add(subordinate);
        }
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getTitle() { return title; }
    public String getDepartment() { return department; }
    public List<Employee> getDirectReports() { return Collections.unmodifiableList(directReports); }

    @Override
    public String toString() {
        return String.format("[%s] %-16s | %-20s | %s", id, name, title, department);
    }
}
