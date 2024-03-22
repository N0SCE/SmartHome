import java.time.LocalDateTime;

public class SmartCamera extends SmartDevice{
    private  String name;
    private  boolean isSwitchedOn = false;

    private  double megabytes = 0.0;


    private double storageUsage;
    private double totalStorageUsage;
    private LocalDateTime runStartTime;

    public SmartCamera(String name) {
        this.name = name;
        setDeviceName(name);
    }

    public String getSmartCameraName(){
        return name;
    }

    public  boolean getIsSwitchedOn(){
        return isSwitchedOn;
    }

    /*
     * This method is overriden from the parent class
     */
    @Override
    public  void setIsSwitchedOn(Boolean isSwitchedOn){
        this.isSwitchedOn = isSwitchedOn;
        this.runStartTime = TimeController.getCurrentTime();
        if(!isSwitchedOn){
            update();
        }
    }

    public  double getMegabytes(){
        return megabytes;
    }

    public void setMegabytes(Double megabytes){
        this.megabytes = megabytes;
    }

    public double getStorageUsage() {
        return storageUsage;
    }

    public void setStorageUsage(double storageUsage) {
        this.storageUsage = storageUsage;
    }

    public boolean isStorageUsageValid(double storageUsage) {
        if(storageUsage >= 0){
            return true;
        }else {
            return false;
        }
    }

    public String getZReport() {
        update();
        String status = getIsSwitchedOn() ? "on" : "off";
        String storage = String.format("%.2f", getStorageUsage());
        Output("Smart Camera " + getDeviceName() + " is " + status + " and used " + storage
                + " MB of storage so far (excluding current device), and its time to switch its status is "
                + getSwitchTimeString() + ".");
        return "Smart Camera " + getDeviceName() + " is " + status + " and used " + storage
                + " MB of storage so far (excluding current device), and its time to switch its status is "
                + getSwitchTimeString() + ".";
    }

    public void update() {
        if (isSwitchedOn) {
            if (this.runStartTime == null)
                return;
            double timeDifference = getSecondsTimePassed(this.runStartTime, getCurrentTime());
            this.totalStorageUsage += timeDifference * this.storageUsage;
            this.runStartTime = getCurrentTime();
        }
    }
}
