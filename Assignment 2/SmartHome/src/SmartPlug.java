import java.time.LocalDateTime;

public class SmartPlug extends SmartDevice{
    private  String name;
    private  boolean isSwitchedOn = false;

    private  double ampere = 0.0;

    private  boolean isPluggedIn = false;

    private  int volt = 220;

    private LocalDateTime runStartTime;
    private double storageUsage;
    private double totalStorageUsage;

    private int consumedEnergy;

    public SmartPlug(String name) {
        this.name = name;
        setDeviceName(name);
    }

    public String getSmartPlugName(){
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

        if(!isSwitchedOn && isPluggedIn){
            update();
        }
    }

    public  double getAmpere(){
        return ampere;
    }

    public void setAmpere(double ampere){
        this.ampere = ampere;
    }

    public boolean getIsPluggedIn(){
        return isPluggedIn;
    }

    public void setIsPluggedIn(boolean isPluggedIn){
        this.isPluggedIn = isPluggedIn;

        if(isPluggedIn){
            this.runStartTime = getCurrentTime();
        }

        if(isSwitchedOn && !isPluggedIn){
            update();
        }
    }

    public int getVolt() {
        return volt;
    }

    public int getConsumedEnergy() {
        return consumedEnergy;
    }

    public void update() {
        if (isSwitchedOn && isSwitchedOn) {
            if (this.runStartTime == null)
                return;
            double timeDifference = getSecondsTimePassed(this.runStartTime, getCurrentTime());
            this.consumedEnergy += (int) (timeDifference * getAmpere() * getVolt() / 30);
            this.runStartTime = getCurrentTime();
        }
    }

    public String getZReport() {
        update();
        String status = getIsSwitchedOn() ? "on" : "off";
        String consumedEnergy = String.format("%.2f", (float) getConsumedEnergy());
        return "Smart Plug " + getDeviceName() + " is " + status + " and consumed " + consumedEnergy
                + "W so far (excluding current device), and its time to switch its status is " + getSwitchTimeString()
                + ".";
    }
}
