package darkx.darkxcore.lib;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class Log {
	private static Logger logger = Logger.getLogger("Darkx");

    public static void init() {
    	logger.setParent(FMLLog.getLogger());
    }
    
    public static void log(String message) {
    	log(Level.INFO, message);
    }

    public static void log(Level logLevel, String message) {
    	logger.log(logLevel, message);
    }
}
