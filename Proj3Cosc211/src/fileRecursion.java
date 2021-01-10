import java.io.File;
import java.io.IOException;
import java.util.*; 
/**
 * A Java program that reads in a directory inputted by the user and determines the number of 
 * files within the given directory and displays the file names, it will then search if a file
 * (inputted by the user) is located in the original directory. 
 * @author Alex Wells, (4-12-2020)
 */
public class fileRecursion {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);//For user input
		
		System.out.print("Please input a directory: "); //Taking in directory from user
		String dir = kb.nextLine(); 
		File file = new File(dir); //Creates file object for input
		
		if (file.isDirectory()) {
		System.out.println("Number of files: \n " + countFiles(file)); //Displays number of files
		displayFiles(file); //Displays filenames within given directory 
		}
		else
			System.out.print("Entered path was not a directory");
		
		System.out.println("Enter the name of a file (a file or directory): ");
		String newFile = kb.nextLine(); 
		File findFile = new File(newFile); //File to be checked in directory
		
		System.out.println("Is the given file in the original directory? "+ fileFound(file,findFile)); 
		//Checks if input is located in directory
	
		
		
	//Recursive methods 	
	}
	
	//Determines whether an entered file is within a given directory
	private static boolean fileFound(File file, File findFile) {
		if (file.isDirectory()) {
	        File[] dir = file.listFiles();//Creates an array of filenames
	        for (File f : dir) {
	            boolean locate = fileFound(f, findFile);//Recursively calls new arrays and the original file
	            if (locate)
	                return true;
	            }
	    } else {
	        String name = file.getName();
			if (findFile.equals(name)) { // checks if findFile name equals file name
	            return true;
	        }
	    }
	    return false;
}
	//Counts number of files
	public static int countFiles(File file) {
		 int numFiles = 0;
		  File[] dir = file.listFiles();//Creates an array of filenames
		  for (File f : dir)
		    if (f.isDirectory())
		      numFiles += countFiles(f);//Recursively calls new array of files
		    else
		      numFiles++;

		  return numFiles;//Returns number of files from file f
		}
	
	//Displays files within given directory
	public static void displayFiles(File file) {
     System.out.println("Directory: "+ file.getName());  
     String[] files = file.list(); //Creates an array of files 
     for (int i = 0; i < files.length; i++) {
         File j = new File(file,files[i]);  // file in directory
         if ( j.isDirectory() ) {
             displayFiles(j);//Recursively calls array of files to display contents
         }
         else {
             System.out.println(files[i]);
         }
     }
 }

	}