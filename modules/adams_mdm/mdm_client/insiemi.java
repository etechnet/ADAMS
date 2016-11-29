/*
 * insiemi.java
 *
 * Created on 27 febbraio 2001, 16.03
 */
 
/**
 *
 * @author  Luca Beltrame & Raffaele Ficcadenti 
 * @version 
 */
 
import java.awt.*;
import java.awt.image.*;
import java.math.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import java.net.*;

/**
 *
 * @author  gundam
 * @version 
 */
public class insiemi extends JPanel
{
	public Graphics2D g;
	public int xc=300;
	public int yc=300;
	public int r=60;
	public int rInsiemi=90;
	public double angoloGiro=2*Math.PI;
	public boolean statusSelection=true;  //true=normal false=inverse;
	
	TexturePaint t1,t2;	
	Paint defaultP;
    
	//puntati da ID_REST    
	String[] nameIcon={ "b_red.gif","b_green.gif","b_yellow.gif","b_pink.gif","b_blue.gif","b_magenta.gif","b_orange.gif","b_cyan.gif",
                            "b_red_darker.gif","b_green_darker.gif","b_yellow_darker.gif",
                            "b_pink_darker.gif","b_blue_darker.gif","b_magenta_darker.gif",
                            "b_orange_darker.gif","b_cyan_darker.gif"
			   			};
	Color[] colori={Color.red,Color.green,Color.yellow,Color.pink,Color.blue,Color.magenta,Color.orange,Color.cyan,
			Color.red.darker(),Color.green.darker(),Color.yellow.darker(),
			Color.pink.darker(),Color.blue.darker(),Color.magenta.darker(),
			Color.orange.darker(),Color.cyan.darker()};
	
	Vector vetInsiemi = new Vector();
	JListIcon jl;        
	
	public insiemi(JListIcon jl)
	{
		this.jl=jl;
                jl.set_Font(staticLib.fontA9,staticLib.fontA9);
		jl.addElement("b_black.gif",null,"Traffic flow");
		t2=getImageTexture("/images/texture2.gif");
		t1=getImageTexture("/images/default.gif");				
	}
	
	public void setInsiemi(int xCentro,int yCentro,int raggio,int rInsiemi)
	{
		this.xc=xCentro;
		this.yc=yCentro;
		this.r=raggio;
		this.rInsiemi=rInsiemi;
	}
	
	public boolean returnStatus()
	{
		return statusSelection;
	}
	
	private TexturePaint getImageTexture(String nameTexture) 
	{
            URL url = getClass().getResource(nameTexture);
            Image img = getToolkit().getImage(url);
            try 
            {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                tracker.waitForID(0);
            } 
            catch (Exception e) {}
            int iw = img.getWidth(this);
            int ih = img.getHeight(this);
            BufferedImage bi = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
            Graphics2D tG2 = bi.createGraphics();
            tG2.drawImage(img, 0, 0, this);
            Rectangle r = new Rectangle(0,0,iw,ih);
            return new TexturePaint(bi,r);
    }
    
    public void inverseIntersect()
    {
        //if (!State)
         //   return;
        TexturePaint appo;
        appo=t1;
        t1=t2;
        t2=appo;
        paint(getGraphics());
            statusSelection=!statusSelection;
    }

    public void inverseIntersect2()
    {
        TexturePaint appo;
        appo=t1;
        t1=t2;
        t2=appo;
        statusSelection=!statusSelection;
    }

    public void removeAllInsiemi()
    {
            vetInsiemi.removeAllElements();	
            jl.removeAll();
                jl.addElement("b_black.gif",null,"Traffic flow");
    }

    public void finezza()
    {
        paint(getGraphics());
    }
	
    public void removeInsieme(int id)
    {
        boolean found=false;
        for (int i=0; i<vetInsiemi.size(); i++)
        {
            if ( ((DataInsiemi)(vetInsiemi.elementAt(i))).id == id)
            {
                    vetInsiemi.removeElementAt(i);
                    jl.removeAll();
                    jl.addElement("b_black.gif",null,"Traffic flow");
                    found=true;
                    paint(getGraphics());
                    break;
            }
        }
        if(found)
            for (int i=0; i<vetInsiemi.size(); i++)
            {
                jl.addElement(((DataInsiemi)(vetInsiemi.elementAt(i))).nameIcon,null,((DataInsiemi)(vetInsiemi.elementAt(i))).desc);
            }

    }
	
    public void addInsieme(int id,String str,int idIcon)
    {
        //////System.out.println("id Icon "+idIcon);
        DataInsiemi appo = new DataInsiemi();
        appo.desc=str;
        appo.id=id; 

        try
        {
            appo.col=colori[idIcon];
        }catch(Exception e)
        {
            appo.col=Color.black;
        }
        try
        {
            appo.nameIcon=nameIcon[idIcon];
        }catch(Exception e)
        {
            appo.nameIcon="b_black.gif";
        }
        vetInsiemi.addElement(appo);
        try
        {
            jl.addElement(nameIcon[idIcon],null,str);
        }catch(Exception e)
        {
            jl.addElement("b_black.gif",null,str);
        }
        paint(getGraphics());
    }
	
	
    public void paint(Graphics g)
    {
        update(g);
    }
	
	
    public void update(Graphics g)
    {	
        this.g=(Graphics2D)g;

        //this.g.drawString("{ Traffic Flow  }",(int)(xc+((r+rInsiemi+30)*Math.cos(Math.PI/4))),(int)(yc-((r+rInsiemi+30)*Math.sin(Math.PI/4))));

        defaultP =this.g.getPaint();
        Shape[] s=new Shape[vetInsiemi.size()];
        double xVertex,yVertex;
        double angle=angoloGiro/vetInsiemi.size();
        double somAngle=angle;
        int[] xPoints=new int[vetInsiemi.size()];
        int[] yPoints=new int[vetInsiemi.size()];
        Area a1=null,a2;	
        this.g.setColor(Color.black);

        this.g.setPaint(defaultP);
        BasicStroke bs1 = new BasicStroke(2, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
        this.g.setStroke(bs1);
        this.g.drawOval(xc-(r+rInsiemi+21),yc-(r+rInsiemi+21),(r+rInsiemi+21)*2,(r+rInsiemi+21)*2);

        Stroke appostroke = this.g.getStroke();
        this.g.setPaint(t1);
        this.g.fillOval(xc-(r+rInsiemi+20),yc-(r+rInsiemi+20),(r+rInsiemi+20)*2,(r+rInsiemi+20)*2);

        BasicStroke bs = new BasicStroke(3, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
        this.g.setStroke(bs);

        for(int i=0;i<vetInsiemi.size();i++)
        {
            xVertex=r*Math.cos(somAngle);
            yVertex=r*Math.sin(somAngle);			
            xPoints[i]=(int)(xc+xVertex);
            yPoints[i]=(int)(yc+yVertex);
            s[i]=new Ellipse2D.Double((int)(xc+xVertex)-rInsiemi,(int)(yc+yVertex)-rInsiemi,rInsiemi*2,rInsiemi*2);

            this.g.setColor( ( (DataInsiemi)(vetInsiemi.elementAt(i)) ).col);
            this.g.draw(s[i]);
            if (i==0)
            {
                a1=new Area(s[0]);
            }else
            {
                a2=new Area(s[i]);
                a1.intersect(a2);
            }
            somAngle=somAngle+angle;
        }
        if (a1!=null)
        {
            this.g.setPaint(t2);
            this.g.fill(a1);
        }
        this.g.setStroke(appostroke);
        this.g.setPaint(defaultP);	
    }


    class DataInsiemi 
    {
        public String desc;
        public int id;
        public Color col;
        public String nameIcon;
    }
	
		
}
