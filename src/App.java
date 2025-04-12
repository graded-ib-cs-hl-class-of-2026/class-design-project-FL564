public class App {
    private Printer io = new Printer();
    private StudentList studentList = new StudentList();


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


    
    public static void main(String[] args) {
         new App().start();
    } 
}
