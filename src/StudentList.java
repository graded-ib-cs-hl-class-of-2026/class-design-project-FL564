import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentList {
    private List<String> students = new ArrayList<>(); // List to store the names of students

    /**
     * Adds a student to the list.
     * @param student The name of the student to add.
     */
    public void addStudent(String student) {
        students.add(student); // Add the student to the list
    }

    /**
     * Removes a student from the list.
     * @param student The name of the student to remove.
     * @return True if the student was successfully removed, false otherwise.
     */
    public boolean removeStudent(String student) {
        return students.remove(student); // Remove the student and return true if successful
    }

    /**
     * Gets the total number of students in the list.
     * @return The number of students in the list.
     */
    public int getStudentCount() {
        return students.size(); // Return the size of the student list
    }

    /**
     * Retrieves all students as an array.
     * @return An array containing the names of all students.
     */
    public String[] getStudents() {
        return students.toArray(new String[0]); // Convert the list to an array and return it
    }

    /**
     * Shuffles the order of students in the list.
     * Randomizes the order of the students for grouping purposes.
     */
    public void shuffle() {
        Collections.shuffle(students); // Shuffle the list of students
    }
}

