public class SmartLamp extends SmartDevice{

    private static int kelvinValue = 4000;
    private static int brightness = 100;

/*
*    public boolean getIsSwitchedOn(){
 *       return isSwitchedOn;
  *  }
   * public  void setIsSwitchedOn(Boolean isSwitchedOn){
    *    SmartLamp.isSwitchedOn = isSwitchedOn;
   * }
*/
    public int getKelvinValue(){
        return kelvinValue;
    }
    public  void setKelvinValue(int kelvinValue){
        SmartLamp.kelvinValue = kelvinValue;
    }
    public int getBrightness(){
        return brightness;
    }
    public  void setBrightness(int brightness){
        SmartLamp.brightness = brightness;
    }



    public String getZReport() {

        String status = getIsSwitchedOn() ? "on" : "off";
        Output(String.format(
                "Smart Lamp %s is %s and its kelvin value is %dK with %d%% brightness, and its time to switch its status is %s.",
                this.getDeviceName(), status, this.getKelvinValue(), this.getBrightness(), getSwitchTimeString()));
        return String.format(
                "Smart Lamp %s is %s and its kelvin value is %dK with %d%% brightness, and its time to switch its status is %s.",
                this.getDeviceName(), status, this.getKelvinValue(), this.getBrightness(), getSwitchTimeString());
    }
}
