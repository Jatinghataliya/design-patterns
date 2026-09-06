package behavioral.iterator.collection;

import behavioral.iterator.iterator.CustomIterator;

import java.util.*;

/**
 * Concrete Aggregate: OrganizationTree.
 * Encapsulates the tree data structure and provides different traversal iterators.
 */
public class OrganizationTree implements EmployeeHierarchy {
    private final Employee rootLeader;

    public OrganizationTree(Employee rootLeader) {
        this.rootLeader = rootLeader;
    }

    public Employee getRootLeader() {
        return rootLeader;
    }

    @Override
    public CustomIterator<Employee> createBreadthFirstIterator() {
        return new BreadthFirstIterator(rootLeader);
    }

    @Override
    public CustomIterator<Employee> createDepthFirstIterator() {
        return new DepthFirstIterator(rootLeader);
    }

    @Override
    public CustomIterator<Employee> createDepartmentFilterIterator(String department) {
        return new DepartmentFilterIterator(createBreadthFirstIterator(), department);
    }

    // =========================================================================
    // 1. Breadth-First Search (Level-Order) Iterator
    // =========================================================================
    private static class BreadthFirstIterator implements CustomIterator<Employee> {
        private final Employee root;
        private final Queue<Employee> queue = new LinkedList<>();

        public BreadthFirstIterator(Employee root) {
            this.root = root;
            reset();
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Employee next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more employees in tree");
            }
            Employee current = queue.poll();
            for (Employee report : current.getDirectReports()) {
                queue.offer(report);
            }
            return current;
        }

        @Override
        public void reset() {
            queue.clear();
            if (root != null) {
                queue.offer(root);
            }
        }
    }

    // =========================================================================
    // 2. Depth-First Search (Pre-Order / Hierarchy-Order) Iterator
    // =========================================================================
    private static class DepthFirstIterator implements CustomIterator<Employee> {
        private final Employee root;
        private final Deque<Employee> stack = new ArrayDeque<>();

        public DepthFirstIterator(Employee root) {
            this.root = root;
            reset();
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Employee next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more employees in tree");
            }
            Employee current = stack.pop();
            // Push children in reverse order so leftmost child is popped first
            List<Employee> reports = current.getDirectReports();
            for (int i = reports.size() - 1; i >= 0; i--) {
                stack.push(reports.get(i));
            }
            return current;
        }

        @Override
        public void reset() {
            stack.clear();
            if (root != null) {
                stack.push(root);
            }
        }
    }

    // =========================================================================
    // 3. Filtered Department Iterator (Decorates an underlying iterator)
    // =========================================================================
    private static class DepartmentFilterIterator implements CustomIterator<Employee> {
        private final CustomIterator<Employee> underlyingIterator;
        private final String targetDepartment;
        private Employee nextMatch;

        public DepartmentFilterIterator(CustomIterator<Employee> underlyingIterator, String targetDepartment) {
            this.underlyingIterator = underlyingIterator;
            this.targetDepartment = targetDepartment;
            advance();
        }

        private void advance() {
            nextMatch = null;
            while (underlyingIterator.hasNext()) {
                Employee candidate = underlyingIterator.next();
                if (candidate.getDepartment().equalsIgnoreCase(targetDepartment)) {
                    nextMatch = candidate;
                    break;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return nextMatch != null;
        }

        @Override
        public Employee next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more matching employees for department: " + targetDepartment);
            }
            Employee result = nextMatch;
            advance();
            return result;
        }

        @Override
        public void reset() {
            underlyingIterator.reset();
            advance();
        }
    }
}
