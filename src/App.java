import java.util.HashMap;
import java.util.Map;

public class App {
    private Printer io = new Printer(); // Handles input and output
    private StudentList studentList = new StudentList(); // Stores the list of students
    private Map<Integer, String> groupNames = new HashMap<>(); // Stores custom or default group names
    private String[] excludedStudents = new String[10]; // Stores excluded students
    private int excludedCount = 0; // Tracks the number of excluded students
// random comment: after switching from arrays to ArrayList, I actually found that not only my code became shorter but simpler to understand, this was also good exercise for me to practice working with arrays!
    /**
     * The main method that starts the application.
     */
    public void start() {
        studentList = readStudentList("students.txt"); // Load the student list from a file
        if (studentList.getStudentCount() == 0) { // Check if the list is empty
            io.output("No students found in the file.");
            return;
        }

        // Ask the user if they want to exclude any students
        io.output("Would you like to exclude any students from the groups? (yes/no)");
        boolean excludeStudents = io.input().equalsIgnoreCase("yes");

        if (excludeStudents) {
            io.output("Enter the names of students to exclude (comma-separated):");
            String input = io.input();
            String[] excluded = input.split(","); // Split input into an array of names
            for (String name : excluded) {
                name = name.trim(); // Trim whitespace
                if (studentList.removeStudent(name)) { // Remove the student from the list
                    addExcludedStudent(name); // Add to the excluded list
                } else {
                    io.output("Student \"" + name + "\" not found in the list.");
                }
            }
            io.output("Excluded students have been removed from the list.");
        }

        // Prompt the user for the group size
        io.output("List has been loaded! How many students would you like per group?");
        int groupSize = Integer.parseInt(io.input());
        if (groupSize <= 0) { // Validate the group size
            io.output("Group size must be greater than 0.");
            return;
        }

        // Shuffle the student list and generate groups
        io.output("Loading the groups...");
        studentList.shuffle();
        io.output("Students have been shuffled!");
        studentOutput(groupSize); // Output the groups

        // Show excluded students at the end
        if (excludedCount > 0) {
            io.output("====================");
            io.output("Excluded Students:");
            io.output("====================");
            for (int i = 0; i < excludedCount; i++) {
                io.output(excludedStudents[i]);
            }
        }

        reRandomize(groupSize); // Call the reRandomize method after outputting the groups
    }

    /**
     * Reads the student list from a file and populates the StudentList object.
     */
    public StudentList readStudentList(String filename) {
        try {
            io.openFile(filename); // Open the file
        } catch (Exception e) {
            io.output(e.toString()); // Output any errors
            return studentList;
        }

        // Read each line from the file and add it to the student list
        while (io.fileHasNextLine()) {
            String nl = io.getNextLine();
            studentList.addStudent(nl);
        }

        return studentList; // Return the populated student list
    }

    /**
     * Adds a student to the excluded students array.
     */
    private void addExcludedStudent(String student) {
        if (excludedCount == excludedStudents.length) {
            String[] newArray = new String[excludedStudents.length * 2];
            for (int i = 0; i < excludedStudents.length; i++) {
                newArray[i] = excludedStudents[i];
            }
            excludedStudents = newArray;
        }
        excludedStudents[excludedCount++] = student;
    }

    /**
     * Outputs the groups of students based on the specified group size.
     */
    public void studentOutput(int groupSize) {
        String[] students = studentList.getStudents(); // Get the list of students as an array

        // Ask the user if they want to provide custom names for the groups
        io.output("Would you like to provide custom names for the groups? (yes/no)");
        boolean useCustomNames = io.input().equalsIgnoreCase("yes");

        for (int i = 0; i < studentList.getStudentCount(); i++) {
            if (i % groupSize == 0) { // Start a new group
                int groupNumber = i / groupSize + 1;
                String groupName;

                // Check if a custom name already exists for this group
                if (groupNames.containsKey(groupNumber)) {
                    groupName = groupNames.get(groupNumber); // Reuse the saved name
                } else if (useCustomNames) {
                    io.output("Enter a name for Group " + groupNumber + ":");
                    groupName = io.input();
                    groupNames.put(groupNumber, groupName); // Save the custom name
                } else {
                    groupName = "Group " + groupNumber; // Use default group name
                    groupNames.put(groupNumber, groupName); // Save the default name
                }

                io.output("====================");
                io.output(groupName + ":");
                io.output("====================");
            }

            io.output(students[i]);

            if ((i + 1) % groupSize == 0 || i == studentList.getStudentCount() - 1) {
                io.output(""); // Add a blank line after the group ends
            }
        }
    }

    /**
     * Allows the user to re-randomize the groups and optionally exclude more students.
     */
    public void reRandomize(int groupSize) {
        while (true) {
            io.output("Would you like to re-randomize the groups? (yes/no)");
            String response = io.input();
            if (response.equalsIgnoreCase("yes")) {
                io.output("Would you like to exclude more students from the groups? (yes/no)");
                boolean excludeMoreStudents = io.input().equalsIgnoreCase("yes");

                if (excludeMoreStudents) {
                    io.output("Enter the names of students to exclude (comma-separated):");
                    String input = io.input();
                    String[] excluded = input.split(",");
                    for (String name : excluded) {
                        name = name.trim();
                        if (studentList.removeStudent(name)) {
                            addExcludedStudent(name);
                        } else {
                            io.output("Student \"" + name + "\" not found in the list.");
                        }
                    }
                    io.output("Excluded students have been removed from the list.");
                }

                studentList.shuffle();
                io.output("Shuffled!");

                io.output("Re-randomizing the groups...");
                studentOutput(groupSize); // Let studentOutput handle the custom name prompt
            } else if (response.equalsIgnoreCase("no")) {
                io.output("Perfect! Have a great day!");
                break;
            } else {
                io.output("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    public static void main(String[] args) {
        new App().start();
    }
}
