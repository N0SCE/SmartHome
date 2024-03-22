public class SmartLampWithColor extends SmartLamp{
    private  String name;

    private  int colorCodeInt;
    public SmartLampWithColor(String name){
        this.name = name;
        setDeviceName(name);
    }

    public String getSmartLampWitcColorName(){
        return name;
    }

    public  void setSmartLampWithColorName(String name){
        this.name = name;
    }

    public int getColorCodeInt(){
        return colorCodeInt;
    }

    public void setColorCodeInt(int ColorCode){
        colorCodeInt = ColorCode;
    }
}
