package darkx.darkxcore.lib;

public class Reference {
    /* Debug Mode On-Off */
    public static final boolean DEBUG_MODE = false;

    /* General Mod related constants */
    public static final String VERSION = "1.0.0";
    public static final String CHANNEL_NAME = "DarkxMods";
    public static final int SECOND_IN_TICKS = 20;
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
    public static final String SERVER_PROXY_CLASS = "darkx.darkxcore.proxy.CommonProxy";
    public static final String CLIENT_PROXY_CLASS = "darkx.darkxcore.proxy.ClientProxy";
    public static final String DEPENDENCY_CORE = "required-after:darkxCore@" + VERSION;
    
}
