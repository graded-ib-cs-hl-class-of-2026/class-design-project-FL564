public class App {
    private Printer io = new Printer();
    private StudentList studentList = new StudentList();


    public void start() {
        studentList = readStudentList("students.txt");
        
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

    public static void main(String[] args) {
         new App().start();
    } 
}
