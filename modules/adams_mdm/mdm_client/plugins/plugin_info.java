public class plugin_info 
{
	public final static int NTM_TYPE_TXT	= 1;
        public final static int NTM_TYPE_HTML	= 2;
        
	public static StringBuffer info(plugin_base.pluginInfo plInfo,int type)
	{
		StringBuffer str=new StringBuffer();
		if(type==NTM_TYPE_TXT)
		{
			System.out.println("globalTypeID       = "+getTypeID(plInfo.globalTypeID));
			System.out.println("name               = "+plInfo.name);
			System.out.println("description        = "+plInfo.description);
			System.out.println("majorVersionNumber = "+plInfo.majorVersionNumber);
			System.out.println("minorVersionNumber = "+plInfo.minorVersionNumber);
			System.out.println("releaseDate        = "+plInfo.releaseDate);
			System.out.println("vendorName         = "+plInfo.vendorName);
			System.out.println("author             = "+plInfo.author);
		}
		else if(type==NTM_TYPE_HTML)
		{
			str.append("<html>");
			str.append("<body>");
			str.append("<p><strong>Plugin Type:</strong> "+getTypeID(plInfo.globalTypeID)+"</p>");
			str.append("<p><strong>Name:</strong> "+plInfo.name+"</p>");
			str.append("<p><strong>Description:</strong> "+plInfo.description+"</p>");
			str.append("<p><strong>Major Version Number:</strong> "+plInfo.majorVersionNumber+"</p>");
			str.append("<p><strong>Minor Version Number:</strong> "+plInfo.minorVersionNumber+"</p>");
			str.append("<p><strong>Release Date:</strong> "+plInfo.releaseDate+"</p>");
			str.append("<p><strong>Vendor Name:</strong> "+plInfo.vendorName+"</p>");
			str.append("<p><strong>Author:</strong> "+plInfo.author+"</p>");
			str.append("</body>");
			str.append("</html>");
		}
		
		return str;
	}

	
	private static String getTypeID(int id)
	{
		switch(id)
		{
			case plugin_base.NTM_CLIENT_PLUGIN:
			{
				return "NTM_CLIENT_PLUGIN";
			}
			
			default:
			{
				return "UNDEFINED";
			}		
		}	
	}	
}
