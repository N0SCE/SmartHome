import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeController extends Output {

    private static LocalDateTime initialTime;
    private static LocalDateTime previousTime;
    private static LocalDateTime currentTime;

    private static LocalDateTime switchTime;

    static DateTimeFormatter dateTimeFormatterformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");

    public LocalDateTime getInitialTime() {
        return initialTime;
    }

    public static void setInitialTime(LocalDateTime initialTime) {
        TimeController.initialTime = initialTime;
    }

    public static LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public static String getCurrentTimeString() {
        if (currentTime != null)
            return currentTime.format(dateTimeFormatterformatter);
        else
            return "null";
    }

    public static void setCurrentTime(LocalDateTime currentTime) {
        TimeController.currentTime = currentTime;
    }

    public LocalDateTime getSwitchTime() {
        return switchTime;
    }

    public static void setSwitchTime(LocalDateTime switchTime) {
        TimeController.switchTime = switchTime;
    }

    public String getSwitchTimeString() {
        if (this.switchTime != null)
            return switchTime.format(TimeController.dateTimeFormatterformatter);
        else
            return "null";
    }

    /**
     * This method executes the SetInitialTime command
     * 
     * @param command is the command to be executed
     */
    public void executeSetInitialTime(String command) {
        String[] commandTime = command.split("\t");

        if (initialTime != null) {
            System.out.println("ERROR: Initial time is already set!");
            return;
        }
        System.out.println(commandTime[1]);
        if (commandTime.length < 2) {
            System.out.println("ERROR: Erroneous command!");
            return;
        }

        try {
            if (commandTime.length == 2) {
                LocalDateTime initialCommandTime = LocalDateTime.parse(commandTime[1].replace("_", "T"));
                setInitialTime(initialCommandTime);
                System.out.println("SUCCESS: Time has been set to " + commandTime[1]);
                previousTime = initialTime;
            }
        } catch (Exception e) {
            System.out.println("ERROR: Time format is not correct!");

        }
    }

    /**
     * This method executes the SetCurrentTime command
     * 
     * @param command is the command to be executed
     */
    public void executeSetCurrentTime(String command) {
        String[] commandTime = command.split("\t");

        if (commandTime.length < 2) {
            System.out.println("ERROR: Erroneous command!");
            return;
        }

        try {
            if (commandTime.length == 2) {
                LocalDateTime initialCurrentTime = LocalDateTime.parse(commandTime[1].replace("_", "T"));
                previousTime = currentTime;
                setCurrentTime(initialCurrentTime);
                System.out.println("SUCCESS: Time has been set to " + commandTime[1]);
            }
        } catch (Exception e) {
            System.out.println("ERROR: Time format is not correct!"); // check message ?? Time format

        }
    }

    /**
     * This method executes the SkipMinutes command
     * 
     * @param command is the command to be executed
     */
    public void executeSkipMinutes(String command) {
        String[] commandTime = command.split("\t");

        if (commandTime.length < 2) {
            System.out.println("ERROR: Erroneous command!");
            return;
        }

        try {
            if (commandTime.length == 2) {
                int minutesToSkip = Integer.parseInt(commandTime[1]);
                currentTime = currentTime.plusMinutes(minutesToSkip);
            }
        } catch (Exception e) {
            System.out.println("ERROR: Erroneous command!");

        }
    }

    /**
     * This method executes the SkipSeconds command
     * 
     * @param command is the command to be executed
     */
    public void executeNop(String command) {

        setCurrentTime(switchTime);

    }

    /**
     * This method executes the SetSwitchTime command
     * 
     * @param command is the command to be executed
     */
    public static void executeSetSwitchTime(String command) {
        String[] commandTime = command.split("\t");
        if (commandTime.length != 3) {
            System.out.println("Error: Erroneous command!");
            return;
        }

        // check is device added

        try {
            LocalDateTime switchCommandTime = LocalDateTime.parse(commandTime[1].replace("_", "T"));
            setSwitchTime(switchCommandTime);
        } catch (Exception e) {
            System.out.println("ERROR: Erroneous command!");
        }

        // Change the status when switch time came

    }

    /**
     * This method calculates the time passed between two times in seconds
     * 
     * @param runStartTime is the time when the device started running
     * @param currentTime2 is the current time
     * @return the time passed in seconds
     */
    public static int getSecondsTimePassed(LocalDateTime runStartTime, LocalDateTime currentTime2) {
        if (currentTime2 == null) {
            return 0;
        }
        return (int) ChronoUnit.MINUTES.between(runStartTime, currentTime2);
    }
}
