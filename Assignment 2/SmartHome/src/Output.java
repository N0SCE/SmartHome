import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    // This class is used to write the output to a file
    // It uses a different and cool approach to collect the output data
    public static String output = null;
    public void Output(String output){
        Output.output += output + "\n";
    }

    public static void WriteToFile(String output){
        try {
            System.out.println(Output.output);
            File myFile = new File(output);
            FileWriter writer = new FileWriter(myFile);
            writer.write(Output.output);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
