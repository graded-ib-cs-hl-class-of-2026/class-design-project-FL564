import java.util.Scanner;
import java.io.*;

public class Printer {
    
    private Scanner in = new Scanner(System.in); // Scanner for user input
    private Scanner fileInput; // Scanner for reading from a file
    private File file; // File object to represent the file being read

    /**
     * Opens a file for reading.
     * Initializes the file and fileInput objects.
     * @param filename The name of the file to open.
     * @throws FileNotFoundException If the file does not exist.
     */
    public void openFile(String filename) throws FileNotFoundException {
        file = new File(filename); // Create a File object for the specified filename
        fileInput = new Scanner(file); // Initialize the Scanner to read from the file
    }

    /**
     * Closes the currently opened file.
     * Releases resources associated with the file and fileInput objects.
     */
    public void closeFile() {
        fileInput.close(); // Close the Scanner
        file = null; // Set the file object to null
        fileInput = null; // Set the fileInput object to null
    }

    /**
     * Checks if the file has another line to read.
     * @return True if there is another line in the file, false otherwise.
     */
    public boolean fileHasNextLine() {
        if (fileInput == null) { // Check if the fileInput Scanner is initialized
            return false; // Return false if no file is open
        } else {
            return fileInput.hasNextLine(); // Return true if there is another line to read
        }
    }

    /**
     * Reads the next line from the file.
     * @return The next line from the file, or an empty string if there are no more lines.
     */
    public String getNextLine() {
        if (fileHasNextLine()) { // Check if there is another line to read
            return fileInput.nextLine(); // Return the next line
        } else {
            return ""; // Return an empty string if no more lines are available
        }
    }

    /**
     * Outputs a string to the console.
     * @param s The string to output.
     */
    public void output(String s) {
        System.out.println(s); // Print the string to the console
    }

    /**
     * Reads a line of input from the user.
     * @return The user's input as a string.
     */
    public String input() {
        return in.nextLine(); // Read and return the next line of user input
    }

    /**
     * Writes a string to a file named "randomizedHistory.txt".
     * Appends the string to the file, creating the file if it does not exist.
     * @param s The string to write to the file.
     */
    public void fileOutput(String s) {
        try {
            FileWriter writer = new FileWriter("randomizedHistory.txt", true); // Open the file in append mode
            writer.write(s + "\n"); // Write the string followed by a newline
            writer.close(); // Close the FileWriter
        } catch (IOException e) { // Handle any IO exceptions
            output("There was an error"); // Output an error message to the console
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}
