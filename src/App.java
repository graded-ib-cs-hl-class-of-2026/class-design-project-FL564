public class App {
    private Printer io = new Printer();
    private StudentList studentList = new StudentList();

/**
 * * The main method that starts the application.
 * It initializes the student list, prompts the user for group size,
 * shuffles the student list, and outputs the groups.
 * It also handles file reading and writing for student data.
 */
    public void start() {
        studentList = readStudentList("students.txt");
        if (studentList.getStudentCount() == 0) {
            io.output("No students found in the file.");
            return;
        }
        io.output("List has been loaded! How many students would you like per group?");
        int groupSize = Integer.parseInt(io.input());
        if (groupSize <= 0) {
            io.output("Group size must be greater than 0.");
            return;
        }
        io.output("Loading the groups...");
        studentList.shuffle();
        io.output("shuffled");
        studentOutput(groupSize);

        reRandomize(groupSize); // Call the reRandomize method after outputting the groups
        


    }


    public StudentList readStudentList(String filename) {

        try {
            io.openFile(filename);
        } catch (Exception e) {
            io.output(e.toString());
            return studentList;
        }

        while (io.fileHasNextLine()) {
            String nl = io.getNextLine();
            studentList.addStudent(nl);
        }

        return studentList;
    }

    public void studentOutput(int groupSize){
        String[] students = studentList.getStudents();
        for(int i = 0; i < studentList.getStudentCount(); i++){
            if(i%groupSize == 0){
                io.output("Group" + (i/groupSize + 1));
                io.fileOutput("Group" + (i/groupSize + 1));
            }

            io.output(students[i]);
            io.fileOutput(students[i]);   
        }




    }

    //give the user the option to re randomize the groups 
    public void reRandomize(int groupSize) {
        while (true) {
            io.output("Would you like to re-randomize the groups? (yes/no)");
            String response = io.input();
            if (response.equalsIgnoreCase("yes")) {
                studentList.shuffle(); // Shuffle the student list again
                io.output("shuffled");
                io.output("Re-randomizing the groups...");
                studentOutput(groupSize); // Use the same group size as before
            } else if (response.equalsIgnoreCase("no")) {
                io.output("Goodbye!");
                break; // Exit the loop
            } else {
                io.output("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }


    
    public static void main(String[] args) {
         new App().start();
    } 
}
