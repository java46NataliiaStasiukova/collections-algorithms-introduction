package telran.util;

public interface SortedSet<T> extends Set<T> {
/**
 * 
 * @return reference to the least object
 */
T first();
/**
 * 
 * @return reference to the most object
 */
T last();
/**
 * 
 * @param pattern
 * @return the least element in the set
 * greater than or equal to the given element,
 * or null if there is no such element.
 */
T ceiling(T pattern);
/**
 * 
 * @param pattern
 * @return the greatest element in the set
 * less than or equal to the given element,
 * or null if there is no such element.
 */
T floor(T pattern);
}
