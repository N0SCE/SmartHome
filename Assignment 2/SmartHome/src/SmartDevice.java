import java.time.LocalDateTime;

public class SmartDevice extends TimeController {
    private String deviceName;

    protected boolean isSwitchedOn = false;

    protected LocalDateTime switchTime;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean getIsSwitchedOn() {
        return isSwitchedOn;
    }

    /**
     * This method sets the isSwitchedOn variable
     * It can be overriden by the child classes
     * @param isSwitchedOn the isSwitchedOn to set
     */
    public void setIsSwitchedOn(Boolean isSwitchedOn) {
        this.isSwitchedOn = isSwitchedOn;
    }

    public void switchInverse() {
        this.isSwitchedOn = !this.isSwitchedOn;
    }

    public String getZReport() {
        return null;
    }

    public void update() {
    }
}
