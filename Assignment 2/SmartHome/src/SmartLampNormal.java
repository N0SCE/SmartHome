public class SmartLampNormal extends SmartLamp{
    private  String name;
    public SmartLampNormal(String name){
        this.name = name;
        setDeviceName(name);
    }

    public String getSmartLampNormalName(){
        return name;
    }

    public  void setSmartLampNormalName(String name){
        this.name = name;

    }
}
