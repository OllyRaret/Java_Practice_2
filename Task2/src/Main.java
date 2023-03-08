import java.io.*;
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input name of input file:");
        String finname = scanner.nextLine();

        File fin = new File(finname);
        if (!fin.exists()) {
            System.err.println("File " + finname + " not found!");
            System.exit(-1);
        }
        else
            System.out.println("File " + finname + " exists");

        System.out.println("Input name of output file:");
        String foutname = scanner.nextLine();

        int uppers = 0, lowers = 0;
        try(FileReader reader = new FileReader(finname))
        {
            int c;
            while((c=reader.read())!=-1) {
                if (Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.BASIC_LATIN)) {
                    if (Character.isUpperCase(c))
                        ++uppers;
                    else if (Character.isLowerCase(c))
                        ++lowers;
                }
            }
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }

        try (FileWriter writer = new FileWriter(foutname, false))
        {
            writer.append("Uppers: " + uppers + "\n");
            writer.append("Lowers: " + lowers + "\n");
            writer.flush();
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
}