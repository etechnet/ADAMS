//package plugins;


public interface plugin_base
{
    public final int NTM_CLIENT_PLUGIN	= 1;
    
    class pluginInfo
    {
	    public int globalTypeID;
	    public String name;
	    public String description;
	    public int pluginName;
	    public int majorVersionNumber;
	    public int minorVersionNumber;
	    public java.util.Date releaseDate;
	    public String vendorName;
	    public String author;		
    }
    
    public void pluginSetupConfig(Object o1,Object o2,Object o3);
    
    public boolean pluginWorker();
    
    public boolean startup();
    
    public boolean shutdown();
    
    public boolean verifyType();
    
    public StringBuffer info(int type);

}