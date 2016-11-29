import java.util.Vector;
import java.util.Collections;

public class elementoLista extends Vector<elementoListaBase> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public elementoLista()
	{
		super();
	}
	
	public elementoLista(int _num_type,String _cc,String _ac,String _numu)
	{
		super();
		elementoListaBase appo=new elementoListaBase(_num_type,_cc,_ac,_numu);
		addElement(appo);
	}
	
	public elementoLista(int _num_type,String str)
	{
		super();
		elementoListaBase appo=new elementoListaBase(_num_type,str);
		super.addElement(appo);
	}
	
	public void addElement(int _num_type,String _cc,String _ac,String _numu)
	{
		elementoListaBase appo=new elementoListaBase(_num_type,_cc,_ac,_numu);
		super.addElement(appo);
	}
	
	public void addElement(elementoListaBase elb)
	{
		super.addElement(elb);
	}
	
	public void addElement(int _num_type,String str)
	{
		elementoListaBase appo=new elementoListaBase(_num_type,str);
		addElement(appo);
	}
	
	public elementoListaBase elementAt(int index)
	{
		return (elementoListaBase)super.elementAt(index);
	}
	
	public elementoListaBase elementAt(String str)
	{
		boolean flagTrovato		= false;
		elementoListaBase elb	= null;
		
		for(int i=0;i<size();i++)
		{
			String appo=super.elementAt(i).getNumeroDESC();
			elb=super.elementAt(i);
			if(appo.equals(str))
			{
				flagTrovato=true;
				break;
			}
		}
		if(flagTrovato==false)
		{
			return null;
		}else
			return elb;
	}
	
	public int indexOf(elementoListaBase elb)
	{
		int index=-1;
		for(int i=0;i<size();i++)
		{
			elementoListaBase appo=super.elementAt(i);
			//System.out.println(elb.getNumero()+"   su("+i+") "+appo.getNumero());
			
			if(appo.getNumero().equals(elb.getNumero()))
			{
				index=i;
				break;
			}
		}
		
		return index;
	}
	
	public void remove(int index[])
	{
		for(int i=0;i<index.length;i++)
			System.out.println(" DELETE index="+index[i]);
		super.remove(index);
	}
	
	public void removeAllElements()
	{
		super.removeAllElements();
	}
	
	public void elimina(String str)
	{
		boolean flagTrovato		= false;
		elementoListaBase elb	= null;
		
		for(int i=0;i<size();i++)
		{
			String appo=elementAt(i).getNumeroDESC();
			elb=elementAt(i);
			if(appo.equals(str))
			{
				flagTrovato=true;
				break;
			}
		}
		if(flagTrovato==true)
		{
			super.remove(elb);
		}
	}
	
	public Vector<String> getNumeri()
	{
		Vector<String> appo=new Vector<String>();
		for(int i=0;i<size();i++)
		{
			appo.addElement(elementAt(i).getNumeroDESC());
		}
		return appo;
	}
	
	public Vector<String> getNumeriSort()
	{
		Vector<String> appo=new Vector<String>();
		for(int i=0;i<size();i++)
		{
			appo.addElement(elementAt(i).getNumeroDESC());
		}
		Collections.sort(appo);
		return appo;
	}
	
	public void stampaNumeri()
	{
		for(int i=0;i<size();i++)
		{
			elementoListaBase appo=super.elementAt(i);
			System.out.println(appo.getType()+": CC="+appo.getCC()+" AC="+appo.getAC()+" NUMU="+appo.getNUMU()+":  "+appo.getNumero());
		}
	}

}
