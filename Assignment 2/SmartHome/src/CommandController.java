import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandController extends DeviceController{
    private static String[] inputList;
    String inputNotSplitted = null;

    private static String[] lines;
    private static ArrayList<ArrayList<String>> inputs= new ArrayList<ArrayList<String>>();

    public static int lineNumber = 0;

    /**
     * This method reads the input file and splits the commands into lines
     * @param inputFileName is the name of the input file
     */
    public void Commands(String inputFileName){
        FileReader fileReader = new FileReader();
        lines = fileReader.ReadFile(inputFileName);
        lineNumber = lines.length;
        for (int i = 0; i < lines.length; i++){
            inputs.add(new ArrayList<String>(Arrays.asList(lines[i].split("/t"))) );
        }
        decideCommand(lines);

    }



    public CommandController(String inputFileName){
        Commands("input1.txt");
    }

    /**
     * This method decides which command to execute and calls the corresponding method
     * @param lines is the array of commands
     */
    public void decideCommand(String[] lines){

        for(int i = 0; i < lineNumber; i++){

            boolean isEmpty = false;
            if(lines[i].isEmpty()){
                isEmpty = true;
            }

            if(!isEmpty) {
                System.out.println("COMMAND: " + lines[i]);
                Output("COMMAND: " + lines[i]);
                if (lines[i].startsWith("SetInitialTime")) {
                    executeSetInitialTime(lines[i]);

                } else if (lines[i].isEmpty()) {

                } else if (lines[i].startsWith("SetTime")) {
                    executeSetCurrentTime(lines[i]);

                    
                    
                } else if (lines[i].startsWith("Add")) { // fix
                    if (lines[i].contains("SmartPlug")) {
                        executeAddSmartPlug(lines[i]);
                    } else if (lines[i].contains("SmartCamera")) {
                        executeAddSmartCamera(lines[i]);
                    } else if (lines[i].contains("SmartLamp")) {
                        executeAddSmartLamp(lines[i]);
                    }
                } else if (lines[i].startsWith("Remove")) {
                    removeCommand(lines[i]);
                } else if (lines[i].startsWith("SkipMinutes")) {
                    executeSkipMinutes(lines[i]);

                } else if (lines[i].startsWith("SetSwitchTime")) {
                    executeSetSwitchTime(lines[i]); //check
                    
                } else if (lines[i].startsWith("Switch")) {
                    executeSwitchDeviceStatus(lines[i]); // check
                    
                } else if (lines[i].startsWith("ChangeName")) {
                    changeName(lines[i]);
                    
                } else if (lines[i].startsWith("PlugIn")) {
                    executePlugIn(lines[i]);
                    
                } else if (lines[i].startsWith("PlugOut")) {
                    executePlugOut(lines[i]);
                    
                } else if (lines[i].startsWith("SetKelvin") || lines[i].startsWith("SetBrightness") || lines[i].startsWith("SetColorCode") || lines[i].startsWith("SetWhite") || lines[i].startsWith("SetColor")) {
                    executeSetColorValues(lines[i]);
                } else if (lines[i].startsWith("Nop")) {
                    executeNop(lines[i]);

                } else if (lines[i].startsWith("ZReport")) {
                    executeZReport();
                }  else {
                    System.out.println("ERROR: Erroneous command");
                    Output("ERROR: Erroneous command");
                }
            }
        }
    }
}
