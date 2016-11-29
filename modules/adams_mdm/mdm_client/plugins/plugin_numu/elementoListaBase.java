
public class elementoListaBase {

	private int    num_type;
	private String cc;
	private String ac;
	private String numu;
	private String numero;
	
	public static final int TYPE_UNDEFINED 	= 0;
	public static final int TYPE_CTO 	= 1;
	public static final int TYPE_CTE 	= 2;
	public static final int TYPE_CTOCTE 	= 3;
	
	public elementoListaBase(int _num_type, String _cc,String _ac,String _numu)
	{
		num_type=_num_type;
		cc=_cc;
		ac=_ac;
		numu=_numu;
		numero=_cc+_ac+_numu;
	}
	
	public elementoListaBase(int _num_type,String str)
	{
		num_type=_num_type;
		cc="";
		ac="";
		numu="";
		numero=str;
	}
	
	public int getNumType()
	{
		return num_type;
	}
	
	public String getType()
	{
		String str="UNDEFINED";
		
		switch(num_type)
		{
			case TYPE_UNDEFINED:
			{	
				str = "UNDEFINED";
			}break;
			
			case TYPE_CTO:
			{	
				str = "CTO";
			}break;
			
			case TYPE_CTE:
			{
				str = "CTE";
			}break;
			
			case TYPE_CTOCTE:
			{
				str = "CTOCTE";
			}break;
		}
		
		return str;
	}
	
	public String getNumeroDESC()
	{
		String prefix="UNDEFINED";
		
		switch(num_type)
		{
			case TYPE_UNDEFINED:
			{	
				prefix = "(UNDEFINED)";
			}break;
			
			case TYPE_CTO:
			{	
				prefix = "(CTO)";
			}break;
			
			case TYPE_CTE:
			{
				prefix = "(CTE)";
			}break;
			
			case TYPE_CTOCTE:
			{
				prefix = "(CTOCTE)";
			}break;
		}
				
		return prefix + "-" + numero;
	}
	
	public String getNumero()
	{
		return numero;
	}
	
	public String getCC()
	{
		return cc;
	}
	
	public String getAC()
	{
		return ac;
	}
	
	public String getNUMU()
	{
		return numu;
	}
}
