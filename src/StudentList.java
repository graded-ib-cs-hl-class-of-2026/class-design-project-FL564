public class StudentList {
    
    private String[] students;  
    private int studentCount;

    public StudentList() {
        students = new String[100];
        studentCount = 0;
    }
    public void addStudent(String student) {
        if (studentCount < students.length) {
            students[studentCount] = student;
            studentCount++;
        } else {
            System.out.println("Student list is full.");
        }
    
    }
    public String[] getStudents() {
        return students;
    }
    public int getStudentCount() {
        return studentCount;
    }
    

}

