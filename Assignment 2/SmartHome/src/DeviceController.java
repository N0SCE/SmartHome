import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeviceController extends SmartDevice{
    private static List<SmartDevice> smartDevices = new ArrayList<SmartDevice>();

    static Output output = new Output();

    private static void addDeviceName(SmartDevice deviceName){
        smartDevices.add(deviceName);
    }

    private static void removeDeviceName(SmartDevice deviceName){
        smartDevices.remove(smartDevices.indexOf(deviceName));
    }

    /**
     * This method checks if the device is added before 
     * @param command is the command to be executed
     * @return the device if it is added before, null otherwise
     */
    public static void executeAddSmartPlug(String command){
        String[] commandInput = command.split("\t");


        if(commandInput.length < 3 || commandInput.length > 5){
            System.out.println("Error: Erroneous command!");

            return;
        }

        if(isDeviceAdded(commandInput[2]) != null){
            System.out.println("ERROR: There is already a smart device with same name!");
            return;
        }



        boolean status = false;
        double ampere = 0.0;

        if(commandInput.length >= 4){
            if(commandInput[3].equals("On")){
                status = true;
            } else if (commandInput[3].equals("Off")) {
                status = false;
            }else {
                System.out.println("ERROR: Erroneous command!");
                return;
            }
        }

        if(commandInput.length == 5){
            try{
                ampere = Double.parseDouble(commandInput[4]);
            }catch (Exception e){
                System.out.println("ERROR: Erroneous command!");
            }
        }

        SmartPlug smartPlug = new SmartPlug(commandInput[2]);
        smartPlug.setIsSwitchedOn(status);
        try{
            smartPlug.setAmpere(ampere);
        }catch (Exception e){
            System.out.println("ERROR: Erroneous command!"); // check this
        }

        addDeviceName(smartPlug);
        sortDevices();
    }


    /**
     * This method executes the add smart camera command
     * @param command is the command to be executed
     */
    public static void executeAddSmartCamera(String command){
        String[] commandInput = command.split("\t");
        System.out.println(commandInput);

        if(commandInput.length < 4 || commandInput.length > 5){
            System.out.println("Error: Erroneous command!");
            return;
        }

        if(isDeviceAdded(commandInput[2]) != null){
            System.out.println("ERROR: There is already a smart device with same name!");
            return;
        }

        double megabytes = 0.0;
        boolean status = false;



        try{
            megabytes = Double.parseDouble(commandInput[3]);
        }catch (Exception e){
            System.out.println("ERROR: Erroneous command!");
        }


        if(commandInput.length == 5){
            if(commandInput[4].equals("On")){
                status = true;
            } else if (commandInput[4].equals("Off")) {
                status = false;
            }else {
                System.out.println("ERROR: Erroneous command!");
                return;
            }

        }

        SmartCamera smartCamera = new SmartCamera(commandInput[2]);
        smartCamera.setIsSwitchedOn(status);

        try{
            smartCamera.setMegabytes(megabytes);
        }catch (Exception e){
            System.out.println("ERROR: Erroneous command!"); // check this
        }
        //System.out.println("COMMAND: " + command);
        //output.Output("COMMAND: " + command);
        addDeviceName(smartCamera);
        sortDevices();

    }

    /**
     * This method executes the add smart lamp command
     * @param command is the command to be executed
     */
    public static void executeAddSmartLamp(String command){
        String[] commandInput = command.split("\t");

        if(commandInput.length < 3 || commandInput.length > 6){
            System.out.println("Error: Erroneous command!");
            return;
        }

        if(isDeviceAdded(commandInput[2]) != null){
            System.out.println("ERROR: There is already a smart device with same name!");
            return;
        }

        boolean status = false;
        int kelvinValue = 0;
        int brightness = 0;
        String colorCodeString = null;
        int colorCodeInt = 0;

        if(commandInput.length >= 4){
            if(commandInput[3].equals("On")){
                status = true;
            } else if (commandInput[3].equals("Off")) {
                status = false;
            }else {
                System.out.println("ERROR: Erroneous command!");
                return;
            }
        }


        if(commandInput.length == 6 && !String.valueOf(commandInput[4].charAt(1)).equals("x")){
            kelvinValue = Integer.valueOf(commandInput[4]);
            if(kelvinValue < 2000 || kelvinValue > 6500){
                System.out.println("ERROR: Kelvin value must be in range of 2000K-6500K !");
                return;
            }
            brightness = Integer.valueOf(commandInput[5]);
            if (brightness < 0 || brightness > 100){
                System.out.println("ERROR: Brightness must be in range of 0%-100% !");
                return;
            }
            if (commandInput[1].equals("SmartLamp")){
                SmartLampNormal smartLampNormal = new SmartLampNormal(commandInput[2]);
                smartLampNormal.setIsSwitchedOn(status);
                smartLampNormal.setBrightness(brightness);
                smartLampNormal.setKelvinValue(kelvinValue);
                addDeviceName(smartLampNormal);

            } else if (commandInput[1].equals("SmartColorLamp")) {
                SmartLampWithColor smartLampWithColor = new SmartLampWithColor(commandInput[2]);
                smartLampWithColor.setIsSwitchedOn(status);
                smartLampWithColor.setBrightness(brightness);
                smartLampWithColor.setKelvinValue(kelvinValue);
                addDeviceName(smartLampWithColor);
                sortDevices();
            }


        } else if (commandInput.length == 6 && String.valueOf(commandInput[4].charAt(1)).equals("x")) {
            colorCodeString = commandInput[4];
            try{
                colorCodeInt = Integer.parseInt(colorCodeString, 16); // check
                if (colorCodeInt < Integer.parseInt("0x000000", 16) || colorCodeInt > Integer.parseInt("0xFFFFFF", 16)){
                    System.out.println("ERROR: Color code value must be in range of 0x0-0xFFFFFF");
                    return;
                }
            }catch (Exception e){
                System.out.println("ERROR: Erroneous command !");
            }


            brightness = Integer.valueOf(commandInput[5]);
            if (brightness < 0 || brightness > 100){
                System.out.println("ERROR: Brightness must be in range of 0%-100% !");
                return;
            }

            SmartLampWithColor smartLampWithColor = new SmartLampWithColor(commandInput[2]);
            smartLampWithColor.setIsSwitchedOn(status);
            smartLampWithColor.setBrightness(brightness);
            smartLampWithColor.setColorCodeInt(colorCodeInt);

            addDeviceName(smartLampWithColor);
            sortDevices();

        }

    }

    /**
     * This method executes the add smart speaker command
     * @param command is the command to be executed
     */
    public static void removeCommand(String command){
        String[] commandInput = command.split("\t");

        if(commandInput.length != 2){
            System.out.println("Error: Erroneous command!");
            return;
        }

        if(isDeviceAdded(commandInput[1]) == null){
            System.out.println("ERROR: There is not such device");
            return;
        }


        SmartDevice device = isDeviceAdded(commandInput[1]);
        System.out.println(device.getIsSwitchedOn());
        // switch device
        removeDeviceName(device);
        sortDevices();
    }

    /**
     * This method executes the switch device status command
     * @param command is the command to be executed
     */
    public static void executeSwitchDeviceStatus(String command){
        String[] commandInput = command.split("\t");

        if(commandInput.length != 3){
            System.out.println("Error: Erroneous command!");


            return;
        }

        if(isDeviceAdded(commandInput[1]) == null){
            System.out.println("ERROR: There is not such device");
            return;
        }

        if(commandInput[2].equals("On")){
            if(isDeviceAdded(commandInput[1]).getIsSwitchedOn()){
                System.out.println("ERROR: Device status is already on!");
                return;
            }
            isDeviceAdded(commandInput[1]).setIsSwitchedOn(true);
        } else if (commandInput[2].equals("Off")) {
            if(!isDeviceAdded(commandInput[1]).getIsSwitchedOn()){
                System.out.println("ERROR: Device status is already off!");
                return;
            }
            isDeviceAdded(commandInput[1]).setIsSwitchedOn(false);
        }

    }

    /**
     * This method executes the change device name command
     * @param command is the command to be executed
     */
    public static void changeName(String command){
        String[] commandInput = command.split("\t");

        if(commandInput.length != 3){
            System.out.println("Error: Erroneous command!");
            return;
        }

        if(isDeviceAdded(commandInput[1]) == null){
            System.out.println("ERROR: There is not such device");
            return;
        }

        if(isDeviceAdded(commandInput[1]).getDeviceName().equals(commandInput[2])){
            System.out.println("ERROR: New name is already exist"); // check the message
            return;
        }

        if(isDeviceAdded(commandInput[1]).equals(isDeviceAdded(commandInput[2]))){
            System.out.println("ERROR: There is no change at name"); // check the message
            return;
        }

        isDeviceAdded(commandInput[1]).setDeviceName(commandInput[2]);

    }

    /**
     * This method executes the change device brightness command
     * @param command is the command to be executed
     */
    public static void executePlugIn(String command){
        String[] commandInput = command.split("\t");

        if(commandInput.length != 3){
            System.out.println("Error: Erroneous command!");
            return;
        }

        if(!(isDeviceAdded(commandInput[1]) instanceof SmartPlug)){ //check
            System.out.println("ERROR: Device is not a plug");
            return;
        }

        if(isDeviceAdded(commandInput[1]) == null){
            System.out.println("ERROR: There is not such device");
            return;
        }

        if(((SmartPlug) isDeviceAdded(commandInput[1])).getIsPluggedIn()){
            System.out.println("ERROR: There is already a device plugged in");
            return;
        }



        int ampere = 0;

        try{
            ampere = Integer.parseInt(commandInput[2]);
        }catch (Exception e){
            System.out.println("Error: Erroneous command!");
        }

        if(ampere < 0 ){
            System.out.println("ERROR: Ampere value is not positive");
            return;
        }
        ((SmartPlug) isDeviceAdded(commandInput[1])).setIsPluggedIn(true);
        isDeviceAdded(commandInput[1]).update();

    }

    /**
     * This method executes the change device brightness command
     * @param command is the command to be executed
     */
    public static void executePlugOut(String command){
        String[] commandInput = command.split("\t");

        if(commandInput.length != 3){
            System.out.println("Error: Erroneous command!");
            return;
        }

        if(!(isDeviceAdded(commandInput[1]) instanceof SmartPlug)){ //check
            System.out.println("ERROR: Device is not a plug");
            return;
        }

        if(isDeviceAdded(commandInput[1]) == null){
            System.out.println("ERROR: There is not such device");
            return;
        }

        if(!(((SmartPlug) isDeviceAdded(commandInput[1])).getIsPluggedIn())){
            System.out.println("ERROR: There is already a device plugged out");
            return;
        }

        ((SmartPlug) isDeviceAdded(commandInput[1])).setIsPluggedIn(false);
        isDeviceAdded(commandInput[1]).update();


    }

    /**
     * This method executes the change device brightness command
     * @param command is the command to be executed
     */
    public static void executeSetColorValues(String command){
        String[] commandInput = command.split("\t");

        if(commandInput.length < 3 || commandInput.length > 4){
            System.out.println("Error: Erroneous command!");
            return;
        }

        if(!(isDeviceAdded(commandInput[1]) instanceof SmartLamp)){ //check
            System.out.println("ERROR: Device is not a lamp");
            return;
        }

        if(isDeviceAdded(commandInput[1]) == null){
            System.out.println("ERROR: There is not such device");
            return;
        }

        if(commandInput[0].equals("SetKelvin")){
            ((SmartLamp) isDeviceAdded(commandInput[1])).setKelvinValue(Integer.parseInt(commandInput[2]));
            return;
        }

        if(commandInput[0].equals("SetBrightness")){
            ((SmartLamp) isDeviceAdded(commandInput[1])).setBrightness(Integer.parseInt(commandInput[2]));
            return;
        }

        if(commandInput[0].equals("SetColorCode")){
            String colorCodeString = commandInput[2];
            try{
                int colorCodeInt = Integer.parseInt(colorCodeString, 16); // check
                if (colorCodeInt < Integer.parseInt("0x000000", 16) || colorCodeInt > Integer.parseInt("0xFFFFFF", 16)){
                    System.out.println("ERROR: Color code value must be in range of 0x0-0xFFFFFF");
                    return;
                }
                ((SmartLampWithColor) isDeviceAdded(commandInput[1])).setColorCodeInt(Integer.parseInt(commandInput[2]));

            }catch (Exception e){
                System.out.println("ERROR: Erroneous command !");
            }

        }

        if(commandInput[0].equals("SetWhite")){
            ((SmartLamp) isDeviceAdded(commandInput[1])).setKelvinValue(Integer.parseInt(commandInput[2]));
            ((SmartLamp) isDeviceAdded(commandInput[1])).setBrightness(Integer.parseInt(commandInput[3]));
            return;

        }

        if(commandInput[0].equals("SetColor")){
            String colorCodeString = commandInput[2];
            try{
                int colorCodeInt = Integer.parseInt(colorCodeString, 16); // check
                if (colorCodeInt < Integer.parseInt("0x000000", 16) || colorCodeInt > Integer.parseInt("0xFFFFFF", 16)){
                    System.out.println("ERROR: Color code value must be in range of 0x0-0xFFFFFF");
                    return;
                }
                ((SmartLampWithColor) isDeviceAdded(commandInput[1])).setColorCodeInt(Integer.parseInt(commandInput[2]));

            }catch (Exception e){
                System.out.println("ERROR: Erroneous command !");
            }

            ((SmartLampWithColor) isDeviceAdded(commandInput[1])).setBrightness(Integer.parseInt(commandInput[3]));
            return;

        }


    }

    /**
     * This method executes the change device brightness command
     */
    public  void executeZReport(){
        System.out.println("Time is:\t" + getCurrentTimeString());
        for (SmartDevice device : smartDevices) {
            System.out.println(device.getZReport());
        }
    }

    private static SmartDevice isDeviceAdded(String deviceName){
        for(SmartDevice device : smartDevices){

            if (device.getDeviceName().equals(deviceName)){
                return device;
            }


        }
        return null;
    }

    public static void sortDevices() {
        smartDevices = smartDevices.stream()
                .sorted(Comparator.comparing(SmartDevice::getSwitchTime, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }
}
