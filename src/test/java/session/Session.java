package session;

import device.FactoryDevice;
import io.appium.java_client.AppiumDriver;

import java.time.Duration;

public class Session {

    public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(10);

    private static Session session = null;
    private AppiumDriver device;

    private Session(){
        device = FactoryDevice.make("android").create();
    }

    public static Session getSession(){
        if(session == null){
            session = new Session();
        }
        return session;
    }

    public AppiumDriver getDevice(){
        return device;
    }

    public void closeSession(){
        device.quit();
        session = null;
    }
}