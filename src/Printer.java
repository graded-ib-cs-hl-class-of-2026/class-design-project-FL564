
import java.util.Scanner;
import java.io.*;

public class Printer {
    
    private Scanner in = new Scanner(System.in);
    private Scanner fileInput;
    private File file;

    public void openFile(String filename) throws FileNotFoundException {
        file = new File(filename);
        fileInput = new Scanner(file);
    }

    public void closeFile() {
        fileInput.close();
        file = null;
        fileInput = null;
    }

    public boolean fileHasNextLine() {
        if (fileInput == null) {
            return false;
        }else {
            return fileInput.hasNextLine();
        }
    }

    public String getNextLine() {
        if (fileHasNextLine()) {
            return fileInput.nextLine();
        }else {
            return "";
        }
        
    }

    public void output(String s) {
        System.out.println(s);
    }

    public String input() {
        return in.nextLine();
    }

    public void fileOutput(String s){
        try{
            FileWriter writer = new FileWriter("randomizedHistory.txt", true);
            writer.write(s + "\n");
            writer.close();
        } catch(IOException e){
            output("There was an error"); 
            e.printStackTrace();
        }
       
    }

}
