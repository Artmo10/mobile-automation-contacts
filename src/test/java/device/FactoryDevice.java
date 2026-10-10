package device;

import java.util.HashMap;
import java.util.Map;

public class FactoryDevice {
    public static IDevice make(String deviceType){
        Map<String,IDevice> deviceMap = new HashMap<>();
        deviceMap.put("android", new Android());
        return deviceMap.getOrDefault(deviceType.toLowerCase(), new Android());
    }
}
