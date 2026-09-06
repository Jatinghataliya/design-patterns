package behavioral.iterator;

import behavioral.iterator.collection.Employee;
import behavioral.iterator.collection.OrganizationTree;
import behavioral.iterator.iterator.CustomIterator;

/**
 * Demo runner for Iterator Pattern.
 */
public class IteratorDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("          ITERATOR DESIGN PATTERN DEMO            ");
        System.out.println("==================================================\n");

        // 1. Build an Organization Hierarchy Tree
        // CEO
        //  ├── VP of Engineering (Engineering)
        //  │    ├── Tech Lead (Engineering)
        //  │    │    ├── Senior Dev (Engineering)
        //  │    │    └── Junior Dev (Engineering)
        //  │    └── QA Lead (QA)
        //  │         └── QA Engineer (QA)
        //  └── VP of Marketing (Marketing)
        //       ├── Content Lead (Marketing)
        //       └── SEO Specialist (Marketing)

        Employee ceo = new Employee("EMP-001", "Satya N.", "Chief Executive Officer", "Executive");

        Employee vpEng = new Employee("EMP-002", "Alex Rivera", "VP of Engineering", "Engineering");
        Employee vpMkt = new Employee("EMP-003", "Sarah Connor", "VP of Marketing", "Marketing");
        ceo.addReport(vpEng);
        ceo.addReport(vpMkt);

        Employee techLead = new Employee("EMP-004", "David Miller", "Staff Tech Lead", "Engineering");
        Employee qaLead = new Employee("EMP-005", "Emma Watson", "QA Team Lead", "QA");
        vpEng.addReport(techLead);
        vpEng.addReport(qaLead);

        Employee devSenior = new Employee("EMP-006", "Lucas Chen", "Senior Software Dev", "Engineering");
        Employee devJunior = new Employee("EMP-007", "Mia Khalifa", "Junior Software Dev", "Engineering");
        techLead.addReport(devSenior);
        techLead.addReport(devJunior);

        Employee qaEng = new Employee("EMP-008", "Oliver Queen", "Automation QA Eng", "QA");
        qaLead.addReport(qaEng);

        Employee contentLead = new Employee("EMP-009", "Grace Hopper", "Content Marketing Lead", "Marketing");
        Employee seoSpec = new Employee("EMP-010", "Tom Holland", "SEO Specialist", "Marketing");
        vpMkt.addReport(contentLead);
        vpMkt.addReport(seoSpec);

        OrganizationTree orgTree = new OrganizationTree(ceo);

        // 2. Traversal #1: Breadth-First Search (Level by level - by executive tier)
        System.out.println("--- 1. Breadth-First Iterator (Level-by-Level Tier Traversal) ---");
        CustomIterator<Employee> bfsIterator = orgTree.createBreadthFirstIterator();
        int count = 1;
        while (bfsIterator.hasNext()) {
            System.out.printf("  %2d. %s%n", count++, bfsIterator.next());
        }

        // 3. Traversal #2: Depth-First Search (Organizational tree reporting hierarchy)
        System.out.println("\n--- 2. Depth-First Iterator (Deep Branch-by-Branch Reporting Traversal) ---");
        CustomIterator<Employee> dfsIterator = orgTree.createDepthFirstIterator();
        count = 1;
        while (dfsIterator.hasNext()) {
            System.out.printf("  %2d. %s%n", count++, dfsIterator.next());
        }

        // 4. Traversal #3: Department Filtering Iterator (e.g. Find all "QA" employees)
        System.out.println("\n--- 3. Filter Iterator: Finding All 'QA' Department Employees ---");
        CustomIterator<Employee> qaIterator = orgTree.createDepartmentFilterIterator("QA");
        count = 1;
        while (qaIterator.hasNext()) {
            System.out.printf("  %2d. %s%n", count++, qaIterator.next());
        }

        // 5. Traversal #4: Filter Iterator: Finding All 'Marketing' Employees
        System.out.println("\n--- 4. Filter Iterator: Finding All 'Marketing' Department Employees ---");
        CustomIterator<Employee> mktIterator = orgTree.createDepartmentFilterIterator("Marketing");
        count = 1;
        while (mktIterator.hasNext()) {
            System.out.printf("  %2d. %s%n", count++, mktIterator.next());
        }
    }
}
