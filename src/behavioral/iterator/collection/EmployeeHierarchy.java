package behavioral.iterator.collection;

import behavioral.iterator.iterator.CustomIterator;

/**
 * Aggregate / Iterable Collection Interface.
 * Declares factory methods to create iterators.
 */
public interface EmployeeHierarchy {
    CustomIterator<Employee> createBreadthFirstIterator();
    CustomIterator<Employee> createDepthFirstIterator();
    CustomIterator<Employee> createDepartmentFilterIterator(String department);
}
