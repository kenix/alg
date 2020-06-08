/*
 * Created at 20:41 on 06.06.20
 */
package net.kenix.alg.misc;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The {@code Student} class is an immutable data type to encapsulate a student name and section
 * number. It is used to illustrate various comparators.
 *
 * <p>For additional documentation, see <a href="https://algs4.cs.princeton.edu/12oop">Section
 * 1.2</a> of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Student {
  public static final Comparator<Student> NAME_ORDER = new NameOrder();

  /** A comparator for comparing students in ascending order by section number. */
  public static final Comparator<Student> SECTION_ORDER = new SectionOrder();

  private final String name;
  private final int section;

  /**
   * Initializes a new student from the given arguments.
   *
   * @param name the name of this student
   * @param section the section number of this student
   */
  public Student(String name, int section) {
    this.name = name;
    this.section = section;
  }

  /**
   * Returns a comparator for comparing students in lexicographic order by name.
   *
   * @return a {@link Comparator} for comparing students in lexicographic order by name
   */
  private static Comparator<Student> byNameOrder() {
    return new NameOrder();
  }

  /**
   * Returns a comparator for comparing students in numerical order by section.
   *
   * @return a {@link Comparator} for comparing students in numerical order by section
   */
  private static Comparator<Student> bySectionOrder() {
    return new SectionOrder();
  }

  // compare students by names, starting at this student's name
  // and wrapping around alphabetically
  private Comparator<Student> byRelativeNameOrder() {
    return new RelativeNameOrder();
  }

  // comparator to sort by name
  private static class NameOrder implements Comparator<Student> {
    public int compare(Student a, Student b) {
      return a.name.compareTo(b.name);
    }
  }

  // comparator to sort by section
  private static class SectionOrder implements Comparator<Student> {
    public int compare(Student a, Student b) {
      return a.section - b.section;
    }
  }

  // comparator to sort by name with this name first
  // illustrates the use of a non-static comparator
  private class RelativeNameOrder implements Comparator<Student> {
    public int compare(Student a, Student b) {
      if (a.name.compareTo(b.name) == 0) return 0;
      if (a.name.compareTo(name) == 0) return -1;
      if (b.name.compareTo(name) == 0) return +1;
      if ((a.name.compareTo(name) < 0) && (b.name.compareTo(name) > 0)) return +1;
      if ((a.name.compareTo(name) > 0) && (b.name.compareTo(name) < 0)) return -1;
      return a.name.compareTo(b.name);
    }
  }

  /**
   * Compares this student to the specified student.
   *
   * @param other the other student
   * @return {@code true} if this student equals {@code other}; {@code false} otherwise
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (other == null) return false;
    if (other.getClass() != this.getClass()) return false;
    Student that = (Student) other;
    return (this.section == that.section) && (this.name.equals(that.name));
  }

  @Override
  public int hashCode() {
    return 31 * section + name.hashCode();
  }

  @Override
  public String toString() {
    return section + " " + name;
  }

  /**
   * Unit tests the {@code Student} data type.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {

    // create an array of students
    Student alice = new Student("Alice", 2);
    Student bob = new Student("Bob", 1);
    Student carol = new Student("Carol", 2);
    Student dave = new Student("Dave", 1);
    Student eve = new Student("Eve", 2);
    Student frank = new Student("Frank", 3);
    Student grant = new Student("Grant", 1);
    Student helia = new Student("Helia", 3);
    Student isaac = new Student("Isaac", 3);
    Student jen = new Student("Jen", 1);
    Student kevin = new Student("Kevin", 1);
    Student larry = new Student("Larry", 2);
    Student[] students = {
      larry, kevin, jen, isaac, grant, helia,
      frank, eve, dave, carol, bob, alice
    };

    // sort by name and print results
    System.out.println("By name");
    System.out.println("----------");
    Arrays.sort(students, Student.byNameOrder());
    for (int i = 0; i < students.length; i++) System.out.println(students[i]);
    System.out.println();

    // now, sort by section and print results
    System.out.println("By section");
    System.out.println("----------");
    Arrays.sort(students, Student.bySectionOrder());
    for (int i = 0; i < students.length; i++) System.out.println(students[i]);
    System.out.println();

    // now, sort by name relative to Kevin
    System.out.println("By Kevin");
    System.out.println("----------");
    Arrays.sort(students, kevin.byRelativeNameOrder());
    for (int i = 0; i < students.length; i++) System.out.println(students[i]);
    System.out.println();

    Student ali = new Student("Alice", 3);
    String fred = "Fred";

    System.out.println("alice == ali:        " + (alice == ali));
    System.out.println("alice.equals(ali):   " + (alice.equals(ali)));
    System.out.println("alice.equals(bob):   " + (alice.equals(bob)));
    System.out.println("alice.equals(fred):  " + (alice.equals(fred)));
  }

  /******************************************************************************
   *  Compilation:  javac Student.java
   *  Execution:    java Student
   *  Dependencies: StdOut.java
   *
   *  Illustrates implementation of a Comparator and equals() method.
   *
   *  % By name
   *  ----------
   *  2 Alice
   *  1 Bob
   *  2 Carol
   *  1 Dave
   *  2 Eve
   *  3 Frank
   *  1 Grant
   *  3 Helia
   *  3 Isaac
   *  1 Jen
   *  1 Kevin
   *  2 Larry
   *
   *  By section
   *  ----------
   *  1 Bob
   *  1 Dave
   *  1 Grant
   *  1 Jen
   *  1 Kevin
   *  2 Alice
   *  2 Carol
   *  2 Eve
   *  2 Larry
   *  3 Frank
   *  3 Helia
   *  3 Isaac
   *
   *  By Kevin
   *  ----------
   *  1 Kevin
   *  2 Larry
   *  2 Alice
   *  1 Bob
   *  2 Carol
   *  1 Dave
   *  2 Eve
   *  3 Frank
   *  1 Grant
   *  3 Helia
   *  3 Isaac
   *  1 Jen
   *
   ******************************************************************************/
}
