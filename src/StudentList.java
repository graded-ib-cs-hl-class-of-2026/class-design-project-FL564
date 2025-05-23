
public class StudentList {
    private String[] students = new String[10]; // Initial capacity of 10
    private int studentCount = 0; // Tracks the number of students

    /**
     * Adds a student to the list.
     * @param student The name of the student to add.
     */
    public void addStudent(String student) {
        if (studentCount == students.length) {
            resizeArray(); // Resize the array if it's full
        }
        students[studentCount++] = student; // Add the student and increment the count
    }

    /**
     * Removes a student from the list.
     * @param student The name of the student to remove.
     * @return True if the student was successfully removed, false otherwise.
     */
    public boolean removeStudent(String student) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].equals(student)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < studentCount - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[--studentCount] = null; // Decrement count and clear the last element
                return true;
            }
        }
        return false; // Student not found
    }

    /**
     * Gets the total number of students in the list.
     * @return The number of students in the list.
     */
    public int getStudentCount() {
        return studentCount;
    }

    /**
     * Retrieves all students as an array.
     * @return An array containing the names of all students.
     */
    public String[] getStudents() {
        String[] result = new String[studentCount];
        for (int i = 0; i < studentCount; i++) {
            result[i] = students[i];
        }
        return result;
    }

    /**
     * Shuffles the order of students in the list.
     * Randomizes the order of the students for grouping purposes.
     */
    public void shuffle() {
        for (int i = studentCount - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            String temp = students[i];
            students[i] = students[j];
            students[j] = temp;
        }
    }

    /**
     * Resizes the array to double its current capacity.
     */
    private void resizeArray() {
        String[] newArray = new String[students.length * 2];
        for (int i = 0; i < students.length; i++) {
            newArray[i] = students[i];
        }
        students = newArray;
    }
}

