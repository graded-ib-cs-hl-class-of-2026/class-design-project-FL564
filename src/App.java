import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class App {
    private Printer io = new Printer(); // Handles input and output
    private StudentList studentList = new StudentList(); // Stores the list of students
    private Map<Integer, String> groupNames = new HashMap<>(); // Stores custom or default group names
    private List<String> excludedStudents = new ArrayList<>(); // Stores excluded students

    /**
     * The main method that starts the application.
     * It initializes the student list, prompts the user for group size,
     * shuffles the student list, and outputs the groups.
     * It also handles file reading and writing for student data.
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
                    excludedStudents.add(name); // Add to the excluded list
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
        if (!excludedStudents.isEmpty()) {
            io.output("====================");
            io.output("Excluded Students:");
            io.output("====================");
            for (String student : excludedStudents) {
                io.output(student);
            }
        }

        reRandomize(groupSize); // Call the reRandomize method after outputting the groups
    }

    /**
     * Reads the student list from a file and populates the StudentList object.
     * @param filename The name of the file containing the student list.
     * @return The populated StudentList object.
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
     * Outputs the groups of students based on the specified group size.
     * Allows the user to provide custom names for the groups.
     * @param groupSize The number of students per group.
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
                    // Prompt the user for a custom group name
                    io.output("Enter a name for Group " + groupNumber + ":");
                    groupName = io.input();
                    groupNames.put(groupNumber, groupName); // Save the custom name
                } else {
                    // Use default group name
                    groupName = "Group " + groupNumber;
                    groupNames.put(groupNumber, groupName); // Save the default name
                }

                // Print the group name with a border
                io.output("====================");
                io.output(groupName + ":");
                io.output("====================");
                io.fileOutput("====================");
                io.fileOutput(groupName + ":");
                io.fileOutput("====================");
                io.output(""); // Add a blank line for spacing
                io.fileOutput(""); // Add a blank line for spacing
            }

            // Print the student name
            io.output(students[i]);
            io.fileOutput(students[i]);

            // Add a blank line after the group ends
            if ((i + 1) % groupSize == 0 || i == studentList.getStudentCount() - 1) {
                io.output(""); // Add a blank line after the group ends
                io.fileOutput(""); // Add a blank line in the file output
            }
        }
    }

    /**
     * Outputs the groups of students based on the specified group size.
     * Allows the user to provide custom names for the groups.
     * @param groupSize The number of students per group.
     * @param useCustomNames Whether to use custom names for the groups.
     */
    public void studentOutputWithCustomNames(int groupSize, boolean useCustomNames) {
        String[] students = studentList.getStudents(); // Get the list of students as an array

        for (int i = 0; i < studentList.getStudentCount(); i++) {
            if (i % groupSize == 0) { // Start a new group
                int groupNumber = i / groupSize + 1;
                String groupName;

                // Check if a custom name already exists for this group
                if (groupNames.containsKey(groupNumber)) {
                    groupName = groupNames.get(groupNumber); // Reuse the saved name
                } else if (useCustomNames) {
                    // Prompt the user for a custom group name
                    io.output("Enter a name for Group " + groupNumber + ":");
                    groupName = io.input();
                    groupNames.put(groupNumber, groupName); // Save the custom name
                } else {
                    // Use default group name
                    groupName = "Group " + groupNumber;
                    groupNames.put(groupNumber, groupName); // Save the default name
                }

                // Print the group name with a border
                io.output("====================");
                io.output(groupName + ":");
                io.output("====================");
                io.fileOutput("====================");
                io.fileOutput(groupName + ":");
                io.fileOutput("====================");
                io.output(""); // Add a blank line for spacing
                io.fileOutput(""); // Add a blank line for spacing
            }

            // Print the student name
            io.output(students[i]);
            io.fileOutput(students[i]);

            // Add a blank line after the group ends
            if ((i + 1) % groupSize == 0 || i == studentList.getStudentCount() - 1) {
                io.output(""); // Add a blank line after the group ends
                io.fileOutput(""); // Add a blank line in the file output
            }
        }
    }

    /**
     * Allows the user to re-randomize the groups and optionally exclude more students.
     * @param groupSize The number of students per group.
     */
    public void reRandomize(int groupSize) {
        while (true) {
            io.output("Would you like to re-randomize the groups? (yes/no)");
            String response = io.input();
            if (response.equalsIgnoreCase("yes")) {
                // Ask if they want to exclude more students
                io.output("Would you like to exclude more students from the groups? (yes/no)");
                boolean excludeMoreStudents = io.input().equalsIgnoreCase("yes");

                if (excludeMoreStudents) {
                    io.output("Enter the names of students to exclude (comma-separated):");
                    String input = io.input();
                    String[] excluded = input.split(","); // Split input into an array of names
                    for (String name : excluded) {
                        name = name.trim(); // Trim whitespace
                        if (studentList.removeStudent(name)) { // Remove the student from the list
                            excludedStudents.add(name); // Add to the excluded list
                        } else {
                            io.output("Student \"" + name + "\" not found in the list.");
                        }
                    }
                    io.output("Excluded students have been removed from the list.");
                }

                // Shuffle and re-randomize the groups
                studentList.shuffle(); // Shuffle the student list again
                io.output("Shuffled!");

                // Ask if they want to provide custom names for the groups
                io.output("Would you like to provide custom names for the groups? (yes/no)");
                boolean useCustomNames = io.input().equalsIgnoreCase("yes");

                if (useCustomNames) {
                    groupNames.clear(); // Clear the group names to allow new custom names
                }

                io.output("Re-randomizing the groups...");
                studentOutputWithCustomNames(groupSize, useCustomNames); // Pass the custom name option

                // Show excluded students after re-randomizing
                if (!excludedStudents.isEmpty()) {
                    io.output("====================");
                    io.output("Excluded Students:");
                    io.output("====================");
                    for (String student : excludedStudents) {
                        io.output(student);
                    }
                }
            } else if (response.equalsIgnoreCase("no")) {
                io.output("Perfect! Have a great day!");
                break; // Exit the loop
            } else {
                io.output("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    /**
     * The main entry point of the application.
     * Creates an instance of the App class and starts the program.
     */
    public static void main(String[] args) {
         new App().start();
    } 
}
