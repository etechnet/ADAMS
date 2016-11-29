import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*; 
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

/**
 *<p align="center"><font size="2"><b><font size="6" face="Times New Roman, Times, serif"> Network Traffic Matrix </font></b></font></p>
 *<p align="center"> <b>Author:</b></p>
 *<p align="center">-  Beltrame Luca  - luca.beltrame@e-tech.net</a></p>
 *<p align="center">-  Ficcadenti Raffaele  - raffaele.ficcadenti@e-tech.net</a></p>
 *
 * Classe destinata alla costruzione dell'interfaccia per la selezione dei giorni.
 * La classe estende un oggetto ti tipo javax.swing.JPanel.
 */
public class MDM_JP_Calendar extends javax.swing.JPanel implements MouseListener, ActionListener 
{
    static String[] monthNames;
    static String[] weekNames;
    boolean flagStored=false;
    //static Choice monthChoice = new Choice();
    
    /** Creates new form MDM_JP_Calendar */
    public MDM_JP_Calendar(STRUCT_PARAMS StructParams, boolean Archive_TwoDateFuture,javax.swing.JButton jb_syncCal,boolean flagStored) 
    {
        this.struct_params = StructParams;
        this.flagStored=flagStored;
        //System.out.println("flagStored=: "+this.flagStored);
        if(staticLib.flagCalendar==true)
            this.flagStored=true;
        
        //System.out.println("flagStored(dopo)=: "+flagStored);
        
        initComponents();
        
        if(jb_syncCal != null)
        {
            add(jb_syncCal);
            jb_syncCal.setBounds(65, 290, 110, 20);
            jb_syncCal.setCursor(Cur_hand);
        }
            
        this.Sel_TwoDateFuture = Archive_TwoDateFuture;
        
        if(Sel_TwoDateFuture == true) // only two date seletable
        {
            jL_validDates.setBounds(230, 60, 180, 20);
            jScrollPane2.setBounds(230, 80, 180, 100);
            
            jL1 = new javax.swing.JLabel();
            jL2 = new javax.swing.JLabel();
            jL3 = new javax.swing.JLabel();
            jL4 = new javax.swing.JLabel();
            
            jL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
            jL1.setText("Start Data Selection");
            jL1.setForeground(java.awt.Color.black);
            jL1.setFont(staticLib.fontA10);
            add(jL1);
            jL1.setBounds(230, 190, 180, 18);

            jL2.setText("Press left mouse button.");
            jL2.setForeground(java.awt.Color.black);
            jL2.setFont(staticLib.fontA9);
            add(jL2);
            jL2.setBounds(250, 210, 160, 18);

            jL3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.png")));
            jL3.setText("End Data Selection");
            jL3.setForeground(java.awt.Color.black);
            jL3.setFont(staticLib.fontA10);
            add(jL3);
            jL3.setBounds(230, 240, 180, 18);

            jL4.setText("Press right mouse button.");
            jL4.setForeground(java.awt.Color.black);
            jL4.setFont(staticLib.fontA9);
            add(jL4);
            jL4.setBounds(250, 260, 160, 18);
            
        }
        
        jP_calendar.setBackground(colorBack_DAY_Disability);

        //cursor
        b_precMonth.setCursor(Cur_hand);
        b_succMonth.setCursor(Cur_hand);
        b_precYear.setCursor(Cur_hand);
        b_succYear.setCursor(Cur_hand);
        
        b_precMonth.addActionListener(this);
        b_succMonth.addActionListener(this);
        b_precYear.addActionListener(this);
        b_succYear.addActionListener(this);        

        //Font        
        jl_month.setFont(staticLib.fontA10);
        tfMonth.setFont(staticLib.fontA10);
        jl_year.setFont(staticLib.fontA10);
        tfYear.setFont(staticLib.fontA10);
        jL_validDates.setFont(staticLib.fontA10);
                     
        MostraCalendario(true);
        
        tfYear.setText("" + cal.get(Calendar.YEAR));
        tfMonth.setText(monthNames[cal.get(Calendar.MONTH)]); 
        
    }
    
    private void AddSelectedDays(String[] strSelectedDays)
    {
        if(jP_Validdate != null)
            jP_SelectedDay.remove(jP_Validdate);
        
        jP_Validdate    = new JPanel();
        
        int rows = 0;
        for(int i=0; i<strSelectedDays.length;i++)
        {
            if(strSelectedDays[i].length() != 0)
                rows = rows +1;
        }
        
        if(Sel_TwoDateFuture == false)
        {
            if(rows < 14 )
                rows = 15;
        }
        else
        {
            rows = 2;
        }
        
        jP_Validdate.setLayout(new GridLayout((rows),1));
        jP_Validdate.setOpaque(false);
        
        for(int i=0;i<strSelectedDays.length;i++)
        {    
            if(strSelectedDays[i].length() != 0)
            {
                JLabel JL_Validdate = new JLabel(strSelectedDays[i]);
                JL_Validdate.setIcon(iconDateSel);
                JL_Validdate.setForeground(java.awt.Color.black);
                JL_Validdate.setBackground(java.awt.Color.white);
                JL_Validdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                JL_Validdate.setOpaque(true);
                JL_Validdate.setFont(staticLib.fontA9);
                jP_Validdate.add(JL_Validdate);
            }
        }          
        jP_SelectedDay.add(jP_Validdate); 
        jP_SelectedDay.repaint();
    }

    private void AddSelectedDays(Vector v_strSelectedDays)
    {
        if(jP_Validdate != null)
            jP_SelectedDay.remove(jP_Validdate);

        jP_Validdate    = new JPanel();


        int rows=15;

        //System.out.println("v_strSelectedDays.size() ->>" +v_strSelectedDays.size());
        if(Sel_TwoDateFuture == false)
        {
            if(v_strSelectedDays.size() > (30))
                 rows = (v_strSelectedDays.size()/2)+1;
        }
        else
        {
            rows = 2;
        }
        
        jP_Validdate.setLayout(new GridLayout(rows,1));
        jP_Validdate.setOpaque(false);

        for(int i=0;i<v_strSelectedDays.size();i++)
        {

                JLabel JL_Validdate = new JLabel((String)v_strSelectedDays.elementAt(i));
                JL_Validdate.setIcon(iconDateSel);
                JL_Validdate.setForeground(java.awt.Color.black);
                JL_Validdate.setBackground(java.awt.Color.white);
                JL_Validdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                JL_Validdate.setOpaque(true);
                JL_Validdate.setFont(staticLib.fontA9);
                jP_Validdate.add(JL_Validdate);

        }
        jP_SelectedDay.add(jP_Validdate);
        jP_SelectedDay.updateUI();
        jP_SelectedDay.repaint();
    }
    
    private void MostraCalendario(boolean CF)
    {    
        //resetto Selezioni
        if(Sel_TwoDateFuture == false)
        {
            	for(int x=0; x<DateSelected.length;x++)
                	DateSelected[x] = "";
		
		V_DateSelected=new Vector();
        }
        
        ColoraFestivi = CF;
        cal = Calendar.getInstance();
        ShowCal = Calendar.getInstance();
        
        cal.set(Calendar.DAY_OF_MONTH,(cal.get(Calendar.DAY_OF_MONTH)));
        int yearSelected    = cal.get(Calendar.YEAR);
        int monthSelected   = cal.get(Calendar.MONTH) + 1;
        int daySelected     = cal.get(Calendar.DAY_OF_MONTH);
        
        //cal = Calendar.getInstance();
                
        /********************Label nomi dei giorni Lu Ma....Do**************************/
               
        JP_labelup = new JPanel(new GridLayout(1, weekNames.length));
        for (int i=0; i<weekNames.length; i++) 
        {
            labelup = new JLabel(weekNames[i],0);
            labelup.setForeground(colorFore_DAYNAME);
            JP_labelup.setBackground(colorBack_DAYNAME);
            JP_labelup.add(labelup);
        }
        
        JP_labelup.setPreferredSize(new java.awt.Dimension(100, 30));
        jP_calendar.add(JP_labelup, java.awt.BorderLayout.NORTH);

        /**************** Griglia calendario******************************/
        
        CreaGridDays(yearSelected,(monthSelected - 1),daySelected);
        jP_calendar.add(pDay, java.awt.BorderLayout.CENTER);
    }
    
    private void CreaGridDays(int iYear, int iMonth, int iDay)
    {        
        int i;
        int iCelleBlank;         

        // Creo griglia per i giorni
        pDay = new JPanel(new GridLayout(0, weekNames.length));
        pDay.setBackground(colorBack_DAY_Disability);

        ShowCal.set(iYear, iMonth, 1);
        int dayOfWeek = ShowCal.get(Calendar.DAY_OF_WEEK);

        //celle con blanks.
        iCelleBlank = dayOfWeek - ShowCal.getFirstDayOfWeek();
        if (iCelleBlank < 0)
        iCelleBlank = iCelleBlank + weekNames.length;

        for (i=0; i<iCelleBlank; i++)
        {
            // celle vuote
            JButton tfDay = new JButton();            
            tfDay.setFocusPainted(false);
            tfDay.setBorderPainted(false);
            tfDay.setContentAreaFilled(false);
            tfDay.setOpaque(false);            
            pDay.add(tfDay);
        }

        Calendar Cal_Today = Calendar.getInstance();
        
       // Aggiungo i giorni.
        //System.out.println("gg="+ShowCal.getMaximum(Calendar.DAY_OF_MONTH));
        for (i=ShowCal.getMinimum(Calendar.DAY_OF_MONTH); i<=ShowCal.getMaximum(Calendar.DAY_OF_MONTH); i++)
        {
            Calendar c = Calendar.getInstance();
            c.set(ShowCal.get(Calendar.YEAR), ShowCal.get(Calendar.MONTH), i);

            if (c.get(Calendar.MONTH) != ShowCal.get(Calendar.MONTH))
            {
                    break;
            }
            MyJButton tfDay = new MyJButton();
            tfDay.set_target(c.get(Calendar.DAY_OF_MONTH));            
            tfDay.setText(""+tfDay.get_target());                      
            
            if((""+c.get(Calendar.YEAR)+c.get(Calendar.MONTH)+c.get(Calendar.DAY_OF_MONTH)).equals
               (""+Cal_Today.get(Calendar.YEAR)+Cal_Today.get(Calendar.MONTH)+Cal_Today.get(Calendar.DAY_OF_MONTH)) )
            {
                
                tfDay.setBackground(colorBack_DAY_Ability);
                tfDay.setOpaque(true);                
                tfDay.addMouseListener(this);
                tfDay.setText(""+tfDay.get_target());
                
                //Evidenzio TODAY
                /*Font fontPresent = tfDay.getFont();                
                Font fontToday = new Font(fontPresent.getName(),Font.BOLD+Font.ITALIC,fontPresent.getSize()+2);
                tfDay.setFont(fontToday);*/
                tfDay.setForeground(Color.blue);
                //
                
                if(Sel_TwoDateFuture == false) 
                {
                    String str_daywithData = formatData_Zero(""+c.get(Calendar.YEAR),""+(c.get(Calendar.MONTH)+1),""+tfDay.get_target());

                    String str_daywithData_2 = tfDay.get_target()+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
                    //add_strDate(V_DaywithData,str_daywithData);
                
                    //Controllo se Selezionato  
                    try
                    {
                    	//System.out.println("1) str_daywithData="+str_daywithData_2);
                    	if(isPresent(V_DateSelected,str_daywithData_2))
                                //if(DateSelected[isPresent_str(V_DaywithData,str_daywithData)].length() != 0)
                    	{
                        	tfDay.set_Selected(true);
                     	}
                    }
                    catch(java.lang.ArrayIndexOutOfBoundsException ex)
                    {
                        System.out.println("Resync Calendar 1");
                        //return;
                    }
                }
                else
                {
                    String str_Data_CTRL = formatData_Zero(""+c.get(Calendar.YEAR),""+(c.get(Calendar.MONTH)+1),""+tfDay.get_target());     
                    if ( str_Data_CTRL.equals(StartDate_Format) )
                    {
                        tfDay.set_Selected(true);
                        AppoMyButton_Start = tfDay;
                    }
                }
                tfDay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
            {
	        //if((flagStored==false)&&(staticLib.flagCalendar==false))
                if(flagStored==false)
                {
                    //System.out.println("Vecchio modo");
                    if( (Sel_TwoDateFuture) || (PreviousDaysSelectable == 0) )
                        PreviousDaysSelectable = 1;

                    tfDay.setOpaque(false);   


                    for (int DaysSelectable = PreviousDaysSelectable-1; DaysSelectable>=0; DaysSelectable--)
                    {
                        Calendar Appo_last= Calendar.getInstance();
                        Appo_last.set( Calendar.DAY_OF_MONTH,(Appo_last.get(Calendar.DAY_OF_MONTH)-DaysSelectable) );

                        if(Sel_TwoDateFuture == false) 
                        {
                            if((""+c.get(Calendar.YEAR)+c.get(Calendar.MONTH)+c.get(Calendar.DAY_OF_MONTH)).equals
                                            (""+Appo_last.get(Calendar.YEAR)+Appo_last.get(Calendar.MONTH)+Appo_last.get(Calendar.DAY_OF_MONTH)) )
                            {       

                                String str_daywithData = formatData_Zero(""+c.get(Calendar.YEAR),""+(c.get(Calendar.MONTH)+1),""+tfDay.get_target());
                                add_strDate(V_DaywithData,str_daywithData);

                                String str_daywithData_2 = tfDay.get_target()+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
                                //Controllo se Selezionato
                                try
                                {

                                    //System.out.println("2) str_daywithData="+str_daywithData_2);

                                    if(isPresent(V_DateSelected,str_daywithData_2))
                                    //if(DateSelected[isPresent_str(V_DaywithData,str_daywithData)].length() != 0)
                                    {
                                        tfDay.set_Selected(true);
                                    }
                                }
                                catch(java.lang.ArrayIndexOutOfBoundsException ex)
                                {
                                    System.out.println("Resync Calendar 2");
                                    //return;
                                }

                                tfDay.addMouseListener(this);
                                tfDay.setBackground(colorBack_DAY_Ability);
                                tfDay.setOpaque(true); 
                                tfDay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            }
                        }
                        else
                        {

                            if(c.getTimeInMillis() > Appo_last.getTimeInMillis() )
                            {             
                                String str_Data_CTRL = formatData_Zero(""+c.get(Calendar.YEAR),""+(c.get(Calendar.MONTH)+1),""+tfDay.get_target());

                                if (str_Data_CTRL.equals(StartDate_Format))
                                {
                                    tfDay.set_Selected(true);
                                    AppoMyButton_Start = tfDay;
                                }
                                else if (str_Data_CTRL.equals(EndDate_Format))
                                {
                                    tfDay.set_Selected(true);
                                    AppoMyButton_End = tfDay;
                                }


                                tfDay.addMouseListener(this);                        
                                tfDay.setBackground(colorBack_DAY_Ability);
                                tfDay.setOpaque(true); 
                                tfDay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            }
                        }
                    }
                }
                else
                {
                    //System.out.println("Nuovo modo");
                    tfDay.setOpaque(false);

                    if(Sel_TwoDateFuture == false)
                    {
                            String str_daywithData_2 = tfDay.get_target()+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
                            //Controllo se Selezionato
                            try
                            {
                            	if(isPresent(V_DateSelected,str_daywithData_2))

                                {
                                    tfDay.set_Selected(true);
                                }
                            }
                            catch(java.lang.ArrayIndexOutOfBoundsException ex)
                            {
                                System.out.println("Resync Calendar 2");
                                //return;
                            }
                    }


                    tfDay.addMouseListener(this);
                    tfDay.setBackground(colorBack_DAY_Ability);
                    tfDay.setOpaque(true);
                    tfDay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                }

                if (ColoraFestivi)
                    tfDay.setForeground(ColorFestiviFeriali(c.get(Calendar.DAY_OF_WEEK),c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH),c.get(Calendar.YEAR)));
                else
                    tfDay.setForeground(Color.black);
            }
            pDay.add(tfDay);
            
        }// end FOR

        // Get the day of the week of the last day.
        Calendar c = Calendar.getInstance();
        c.set(iYear, iMonth, i-1);
        dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        iCelleBlank = weekNames.length - dayOfWeek + ShowCal.getFirstDayOfWeek() -1;
        if (iCelleBlank == weekNames.length)
                iCelleBlank = 0;
 
        // Fill the last few cells with blanks.
        for (i=0; i<iCelleBlank; i++)
        {
            JButton tfDay = new JButton();                
            tfDay.setFocusPainted(false);
            tfDay.setBorderPainted(false);
            tfDay.setContentAreaFilled(false);
            tfDay.setOpaque(false);
            pDay.add(tfDay);
        }
        pDay.validate();
        
    } // end CreaGridDays
     
    private Color ColorFestiviFeriali(int WD, int Day, int Month, int Year)
    {
        if (oldYear != Year)
        {
            oldYear = Year;
            pasquetta = Pasquetta(Year);
        }

        if(WD==1)
            return(Color.red);
        
        for (int x=0; x < Holidays.size(); x++)
        {
            if (((String)Holidays.elementAt(x)).equals(Day+"-"+(Month+1)))
            return(Color.red);
        }
        
        if (pasquetta.equals(Day+"-"+(Month+1)))
                return(Color.red);
        return(Color.black);
    }
    
    private void RiempiFestivi(int Day, int Month)
    {
        Holidays.addElement(Day+"-"+Month);
    }
    
    private String Pasquetta(int Year)
    {
        int aa, bb, cc, dd, ee, zeta;
        aa = Year % 19;
        bb = Year % 4;
        cc = Year % 7;
        zeta = aa * 19 +24;
        dd = zeta % 30;
        zeta = (bb * 2) + (cc * 4) + (dd * 6) + 5;
        ee = zeta % 7;
        zeta = 22 + dd + ee;
        if (zeta < 31)
        {
            return((zeta+1)+"/3");
        }
        else
        {
            return((zeta-30)+"/4");
        }
    }
    
    static 
    {
        // Initialize month names.
        Calendar cal = Calendar.getInstance();
        monthNames = new String[cal.getMaximum(Calendar.MONTH) - cal.getMinimum(Calendar.MONTH) + 1];

        // Roll the calendar until it uses the first month.
        
        while (cal.get(Calendar.MONTH) != cal.getMinimum(Calendar.MONTH)) 
        {
            cal.roll(Calendar.MONTH, false);
        }

        // Use SimpleDateFormat to fill monthNames.
        for (int i=0; i<monthNames.length; i++) 
        {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
            monthNames[i] = sdf.format(cal.getTime());
            //monthChoice.addItem(monthNames[i]);
            cal.roll(Calendar.MONTH, true);
        }

        // Initialize day of week names.  The first element contains the name
        // of getFirstDayOfWeek().
        weekNames = new String[cal.getMaximum(Calendar.DAY_OF_WEEK) - cal.getMinimum(Calendar.DAY_OF_WEEK) + 1];

        // Roll the calendar until it uses the first day of week.
        while (cal.get(Calendar.DAY_OF_WEEK) != cal.getFirstDayOfWeek()) 
        {
            cal.roll(Calendar.DAY_OF_WEEK, false);
        }
        
        // Use SimpleDateFormat to fill weekNames.
        for (int i=0; i<weekNames.length; i++) 
        {
            SimpleDateFormat sdf = new SimpleDateFormat("E");
            weekNames[i] = sdf.format(cal.getTime());

            // Only take the first two characters.
            if (weekNames[i].length() > 2) 
            {
                weekNames[i] = weekNames[i].substring(0, 2);
            }
            cal.roll(Calendar.DAY_OF_WEEK, true);
        }
    }
    
    // Event handling methods.


    public boolean isPresent(Vector v,String str)
    {
    	boolean flag=false;
	for(int i=0;i<v.size();i++)
	{
		String s=(String)v.elementAt(i);
		if(s.equals(str))
		{
			flag=true;
			break;
		}
	}
	
	return flag;
    }

    public Vector remove(Vector v,String str)
    {
    	int len=v.size();
    	Vector appo=new Vector();
	for(int i=0;i<len;i++)
	{
		String s=(String)v.elementAt(i);
		if(!s.equals(str))
		{
			appo.addElement(s);
		}
	}
	
	return appo;
    }

    public void stampa(Vector v)
    {
	for(int i=0;i<v.size();i++)
	{
		String s=(String)v.elementAt(i);
		System.out.println(""+i+"-->"+s);
	}
	
    }
    
    public void mousePressed(MouseEvent e) 
    {
    		
        MyJButton tfDaySelected = (MyJButton)e.getSource();   
        //--------- YYYYMMDD ----------
        String dateSelected = formatData_Zero(""+ShowCal.get(Calendar.YEAR),""+(ShowCal.get(Calendar.MONTH)+1),""+tfDaySelected.get_target());

        //--------- DD/MM/YYYY ---------
        String ViewDataSelected = tfDaySelected.get_target()+"/"+(ShowCal.get(Calendar.MONTH)+1)+"/"+ShowCal.get(Calendar.YEAR);


        if(Sel_TwoDateFuture == false)
        {
            {   
                if(V_DateSelected.size()>=PreviousDaysSelectable)
                {
                    if(tfDaySelected.get_Selected())
                    {
                        //System.out.println("deseleziono="+ViewDataSelected);
                        if(isPresent(V_DateSelected,ViewDataSelected))
                        {
                                V_DateSelected=remove(V_DateSelected,ViewDataSelected);
                                V_MyJButton_selected.remove(tfDaySelected);
                                tfDaySelected.set_Selected(!tfDaySelected.get_Selected());     
                        }
                    }else
                    {
                        staticLib.optionPanel("Reached day selection limits.", "Info",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else
                {
                    tfDaySelected.set_Selected(!tfDaySelected.get_Selected());
                    if(tfDaySelected.get_Selected())
                    {
                            if(!isPresent(V_DateSelected,ViewDataSelected))
                            {
                                    V_DateSelected.addElement(ViewDataSelected);
                                    V_MyJButton_selected.addElement(tfDaySelected);
                            }
                            
                    }
                    else
                    {
                            if(isPresent(V_DateSelected,ViewDataSelected))
                            {
                                    V_DateSelected=remove(V_DateSelected,ViewDataSelected);
                                    V_MyJButton_selected.remove(tfDaySelected);
                            }
                            
                    }
                }

                //ampa(V_DateSelected);
                ordina(V_DateSelected);
                AddSelectedDays(V_DateSelected);
                

		
                //////////////////////////////////////////////////////////////////
                /*for(int x=0; x<V_DaywithData.size();x++)
                {
                    System.out.println("--- Ability -->"+x+" "+(String)V_DaywithData.elementAt(x)+" --- Selected -->"+x+" "+DateSelected[x]);
                }
                System.out.println(); */               
                //////////////////////////////////////////////////////////////////

                //Struttura da passare

                java.util.Vector V_ElaborationData_Selected = new java.util.Vector();
		for(int x=0; x<V_DateSelected.size();x++) //cerco le date selezionate
                {
			
                	V_ElaborationData_Selected.addElement( str_aammgg((String)V_DateSelected.elementAt(x)));
                }

                for(int x=0; x<V_DaywithData.size();x++) //cerco le date selezionate 
                {
                    if(DateSelected[x].compareTo("") != 0)
                    {
                        V_ElaborationData_Selected.addElement( V_DaywithData.elementAt(x) );
                    }
                }

                int LENGTH = V_ElaborationData_Selected.size();
                if(LENGTH > 0)
                {
                    struct_params.ElaborationData = new DATE_INFO[LENGTH];            
                    for(int x=0; x<LENGTH;x++)
                    {                
                        struct_params.ElaborationData[x]= new DATE_INFO();
                        struct_params.ElaborationData[x].dateString = set_String_toChar( (String)V_ElaborationData_Selected.elementAt(x), 9 );
                    }
                }
                else
                    struct_params.ElaborationData = null;

                //////////////////////////////////////////////////////////////////
                /*System.out.println("struct_params.ElaborationData.length ==> "+ struct_params.ElaborationData.length );
                for(int i=0; i<struct_params.ElaborationData.length; i++)
                    System.out.println("ElaborationData[i].dateString ==> "+new String(struct_params.ElaborationData[i].dateString).trim() ); */ 
                //////////////////////////////////////////////////////////////////


            }//else
            {
                //se sto in stored
            }
        }
        else
        {
            //System.out.println("DUE DATE Future START --> END data.");
            
            tfDaySelected.set_Selected(true);
            
            appo_Cal.set(ShowCal.get(Calendar.YEAR), ShowCal.get(Calendar.MONTH), tfDaySelected.get_target());
            long date = appo_Cal.getTimeInMillis();

            if(Date_min == 0)
            {
                Date_min = date;
                Date_max = Date_min +1;
                StartDate = ViewDataSelected;
                StartDate_Format = dateSelected;
            }
            
            int mouseID = e.getModifiers();
            if( (mouseID & InputEvent.BUTTON3_MASK)!=0 )
            {
                if(date > Date_min)
                {
                    Date_max = date;
                    EndDate = ViewDataSelected;
                    EndDate_Format = dateSelected;

                    if(AppoMyButton_End != null)
                        AppoMyButton_End.set_Selected(false);
                    AppoMyButton_End = tfDaySelected; 
                }
                else
                    tfDaySelected.set_Selected(false);
            }
            else
            {
                if(date < Date_max)
                {
                    Date_min = date;
                    StartDate = ViewDataSelected;
                    StartDate_Format = dateSelected;

                    if(AppoMyButton_Start != null)
                        AppoMyButton_Start.set_Selected(false);
                    AppoMyButton_Start = tfDaySelected;
                }
                else
                    tfDaySelected.set_Selected(false);      
            }
            
                         
            //System.out.println("StartDate "+StartDate+" --------- EndDate "+EndDate);
            
            String[] Str = new String[2];
            Str[0] = "Start Data:"+StartDate;
            Str[1] = "End Data:  "+EndDate;
            AddSelectedDays(Str);          
            
            struct_params.ElaborationData = new DATE_INFO[2];  
            struct_params.ElaborationData[0]= new DATE_INFO();
            struct_params.ElaborationData[0].dateString = set_String_toChar(StartDate_Format, 9);
            struct_params.ElaborationData[1]= new DATE_INFO();
            struct_params.ElaborationData[1].dateString = set_String_toChar(EndDate_Format, 9);
            
            staticLib.startDATE.setText(StartDate); 
            staticLib.endDATE.setText(EndDate); 
            staticLib.startDATE_ghost   = StartDate_Format;
            staticLib.endDATE_ghost     = EndDate_Format;
            
            /////////////////////////////////////////////////////////////////
            /*System.out.println("struct_params.ElaborationData.length ==> "+ struct_params.ElaborationData.length );
            for(int i=0; i<struct_params.ElaborationData.length; i++)
                System.out.println("ElaborationData[i].dateString ==> "+new String(struct_params.ElaborationData[i].dateString).trim() );
            
            System.out.println("PER JOB -- staticLib.startDATE_ghost --> "+staticLib.startDATE_ghost);
            System.out.println("PER JOB -- staticLib.endDATE_ghost  -->  "+staticLib.endDATE_ghost);*/
            //////////////////////////////////////////////////////////////////
        }
    }
    
    public String str_aammgg(String str)
    {
        String str_appo[]=new String[3];
        int i=2;
        StringTokenizer token=new StringTokenizer(str,"/");

        while (token.hasMoreTokens())
        {
                str_appo[i]=token.nextToken();
                if(str_appo[i].length()<2)
                {
                    str_appo[i]="0"+str_appo[i];
                }
                i--;
        }

        String str_ok=str_appo[0]+str_appo[1]+str_appo[2];
        //System.out.println("str_ok="+str_ok);
        return str_ok;
    }
     
    public void mouseClicked(MouseEvent e) 
    {
    }
    
    public void mouseReleased(MouseEvent e) 
    {    
    }
    
    public void mouseEntered(MouseEvent e) 
    {      
    }
    
    public void mouseExited(MouseEvent e) 
    {
    }
    
    
    public void actionPerformed(ActionEvent evt) 
    {
        int AuxMonth, AuxYear;
        jP_calendar.remove(pDay);
        
        AuxMonth = ShowCal.get(Calendar.MONTH);
        AuxYear = ShowCal.get(Calendar.YEAR);
        
        if (evt.getSource().equals(b_precMonth)) 
        {
            if (ShowCal.get(Calendar.MONTH) == 0)
            {
                AuxMonth = monthNames.length-1;
                AuxYear = AuxYear -1;
            }
            else
                AuxMonth = AuxMonth-1;
            
            tfMonth.setText(monthNames[AuxMonth]);
            tfYear.setText("" + AuxYear);
            CreaGridDays(AuxYear,AuxMonth,1);            
            jP_calendar.add(pDay, java.awt.BorderLayout.CENTER);
        }
        else
        if (evt.getSource().equals(b_succMonth)) 
        {
            if (ShowCal.get(Calendar.MONTH) == monthNames.length - 1)
            {
                AuxMonth = 0;
                AuxYear = AuxYear +1;
            }
            else
                AuxMonth = AuxMonth+1;
            
            tfMonth.setText(monthNames[AuxMonth]);
            tfYear.setText("" + AuxYear);
            CreaGridDays(AuxYear,AuxMonth,1);
            jP_calendar.add(pDay, java.awt.BorderLayout.CENTER);
        }
        else
        if (evt.getSource().equals(b_precYear)) 
        {
            AuxYear = AuxYear - 1;
            tfYear.setText("" + AuxYear);
            CreaGridDays(AuxYear,AuxMonth,1);
            jP_calendar.add(pDay, java.awt.BorderLayout.CENTER);
        }
        else
        if (evt.getSource().equals(b_succYear)) 
        {
            AuxYear = AuxYear +1;
            tfYear.setText("" + AuxYear);
            CreaGridDays(AuxYear,AuxMonth,1);
            jP_calendar.add(pDay, java.awt.BorderLayout.CENTER);
        }
    }
    
    private int isPresent_str(Vector Varchive,String strvalue)
    {
	for(int i=0; i<Varchive.size(); i++)
	{	    
            String str1 = (String)Varchive.elementAt(i);            
	    if(str1.compareTo(strvalue) == 0)
		return i;
	}
	return -1;
    }
    
    private void add_strDate(Vector Varchive,String strvalue)
    {
        if(Varchive.size() == PreviousDaysSelectable) // se sono stati inseriti tutti i giorni validi nonaccetto più inserimenti
        {            
           // System.out.println(" Giorni validi per la selezione COMPLETATI -- No Insert --");
            return; 
        }
        
        int index = isPresent_str(Varchive,strvalue);
        if(index == -1)
            Varchive.add(strvalue);
        
        ordinaDate(Varchive);
    }
    
    public void ordinaDate(Vector V_orderWithStrDate)
    {
        //System.out.println("ordinaDate");
        int len = V_orderWithStrDate.size();
        for(int i=0;i<len-1;i++)
        {
            int min=i;
            for(int j=i+1;j<len;j++)
            {
                int num1 = Integer.valueOf((String)(V_orderWithStrDate.elementAt(j))).intValue();
                int num2 = Integer.valueOf((String)(V_orderWithStrDate.elementAt(min))).intValue();
                
                if(num1 < num2)
                    min=j;
            }
            int appo = Integer.valueOf((String)(V_orderWithStrDate.elementAt(i))).intValue();
            int appo1 = Integer.valueOf((String)(V_orderWithStrDate.elementAt(min))).intValue();           
            
            V_orderWithStrDate.removeElementAt(i);
            V_orderWithStrDate.insertElementAt(""+appo1,i);
            V_orderWithStrDate.removeElementAt(min);
            V_orderWithStrDate.insertElementAt(""+appo,min);    
            
           
            // date visualizzate sull'interfaccia
            String date1 = DateSelected[i];
            String date2 = DateSelected[min];
            
            DateSelected[i] = date2;
            DateSelected[min] = date1;
        }
    }
    
    public void ordina(Vector vett)
    {
        //System.out.println("ordinaDate");
        int len = vett.size();
        for(int i=0;i<len-1;i++)
        {
            int min=i;
            for(int j=i+1;j<len;j++)
            {
                String str1 = str_aammgg((String)vett.elementAt(j));
                String str2 = str_aammgg((String)vett.elementAt(min));
                
                if(str1.compareTo(str2)<0)
                    min=j;
            }
            String appo = (String)vett.elementAt(i);
            String appo1 = (String)vett.elementAt(min);          
            
            vett.removeElementAt(i);
            vett.insertElementAt(appo1,i);
            vett.removeElementAt(min);
            vett.insertElementAt(appo,min);    
        }
    }
    
    /*public void caricaData()
    {
        System.out.println("Carico la data");
            java.util.Vector V_ElaborationData_Selected = new java.util.Vector();
            for(int x=0; x<V_DaywithData.size();x++) //cerco le date selezionate 
            {
                if(DateSelected[x].compareTo("") != 0)
                {
                    V_ElaborationData_Selected.addElement( V_DaywithData.elementAt(x) );
                }
            }

            int LENGTH = V_ElaborationData_Selected.size();
            if(LENGTH > 0)
            {
                struct_params.ElaborationData = new DATE_INFO[LENGTH];            
                for(int x=0; x<LENGTH;x++)
                {                
                    struct_params.ElaborationData[x]= new DATE_INFO();
                    System.out.println((String)V_ElaborationData_Selected.elementAt(x));
                    struct_params.ElaborationData[x].dateString = set_String_toChar( (String)V_ElaborationData_Selected.elementAt(x), 9 );
                }
            }
            else
                struct_params.ElaborationData = null;
          
            System.out.println("****************************");
    }*/
   

    private String formatData_Zero(String yearSelected,String monthSelected,String daySelected)
    {
        String str_format = yearSelected;

        if ((""+monthSelected).length()==1)
            str_format += "0" + monthSelected;
        else
            str_format += "" + monthSelected;
        
        if ((""+daySelected).length()==1)
            str_format += "0" +daySelected;
        else
            str_format += "" +daySelected;
        
        return str_format;      
    }
    
    private void setToDay()
    {
        jP_calendar.remove(pDay);
        cal.set(Calendar.DAY_OF_MONTH,(cal.get(Calendar.DAY_OF_MONTH)));
        int yearSelected    = cal.get(Calendar.YEAR);
        int monthSelected   = cal.get(Calendar.MONTH) + 1;
        int daySelected     = cal.get(Calendar.DAY_OF_MONTH);
        CreaGridDays(yearSelected,(monthSelected - 1),daySelected);            
        jP_calendar.add(pDay, java.awt.BorderLayout.CENTER);
        pDay.validate();        
        tfYear.setText("" + cal.get(Calendar.YEAR));
        tfMonth.setText(monthNames[cal.get(Calendar.MONTH)]);
    }
    
    public void validate_pDay()
    {
        jP_calendar.validate();
        pDay.validate();
        pDay.repaint();
    }
    
    public String[] getDateSelected()
    {
        //return this.DateSelected;
        int len=V_DateSelected.size();
        String appo[]=new String[len];
        for(int i=0;i<len;i++)
        {
		appo[i]=(String)V_DateSelected.elementAt(i);
        }
        return appo;
    }
    
    
    public void resetCalendar()
    {
        //resetto il calendario GUI
        
        if(Sel_TwoDateFuture == false)
        {
            for(int x=0; x<DateSelected.length;x++)
                DateSelected[x] = "";

            AddSelectedDays(DateSelected);

            V_DateSelected=new Vector();

            for(int i=0; i<V_MyJButton_selected.size(); i++)
            {
                ((MyJButton)(V_MyJButton_selected.elementAt(i))).set_Selected(false);
                //System.out.println(((MyJButton)(V_MyJButton_selected.elementAt(i))).get_target());
            }
            V_MyJButton_selected = new Vector();

            setToDay();   
        }
        else
        {
            String[] Str = new String[2];
            Str[0] = "";
            Str[1] = "";
            AddSelectedDays(Str);
            try
            {
                AppoMyButton_Start.set_Selected(false);
                AppoMyButton_End.set_Selected(false);
            }
            catch(Exception e){}
            
            Date_min = 0;
            Date_max = 0;
            StartDate = "";
            StartDate_Format = "";
            EndDate = "";
            EndDate_Format = "";
            
            setToDay();
        }
        
        struct_params.ElaborationData = null;
        staticLib.startDATE.setText(""); 
        staticLib.endDATE.setText("");    
        staticLib.startDATE_ghost="";
        staticLib.endDATE_ghost="";
    }
    public static char[] set_String_toChar(String str, int length)
    {
        char[] appo = str.toCharArray();
        char[] appo1 = new char[length];

        if (appo.length > length)
            return appo1;

        for (int i=0; i<appo.length; i++)
        {
            appo1[i] = appo[i];
        }
        for (int i=appo.length; i<length; i++)
        {
            appo1[i] =0;
        }
        return(appo1);
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        b_precMonth = new javax.swing.JButton();
        jl_month = new javax.swing.JLabel();
        tfMonth = new javax.swing.JLabel();
        b_succMonth = new javax.swing.JButton();
        b_succYear = new javax.swing.JButton();
        jl_year = new javax.swing.JLabel();
        tfYear = new javax.swing.JLabel();
        b_precYear = new javax.swing.JButton();
        jP_calendar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jP_SelectedDay = new javax.swing.JPanel();
        jL_validDates = new javax.swing.JLabel();

        setBackground(new java.awt.Color(183, 206, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Calendar ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Verdana", 1, 11))); // NOI18N
        setForeground(new java.awt.Color(0, 0, 102));
        setLayout(null);

        b_precMonth.setBackground(new java.awt.Color(230, 230, 230));
        b_precMonth.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        b_precMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sx.png"))); // NOI18N
        b_precMonth.setBorderPainted(false);
        b_precMonth.setContentAreaFilled(false);
        b_precMonth.setFocusPainted(false);
        b_precMonth.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b_precMonth.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sx_press.png"))); // NOI18N
        b_precMonth.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sx_over.png"))); // NOI18N
        add(b_precMonth);
        b_precMonth.setBounds(90, 60, 20, 20);

        jl_month.setBackground(new java.awt.Color(0, 0, 102));
        jl_month.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jl_month.setText("Month");
        add(jl_month);
        jl_month.setBounds(10, 60, 80, 20);

        tfMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tfMonth.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        tfMonth.setOpaque(true);
        add(tfMonth);
        tfMonth.setBounds(110, 60, 90, 20);

        b_succMonth.setBackground(new java.awt.Color(230, 230, 230));
        b_succMonth.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        b_succMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dx.png"))); // NOI18N
        b_succMonth.setBorderPainted(false);
        b_succMonth.setContentAreaFilled(false);
        b_succMonth.setFocusPainted(false);
        b_succMonth.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b_succMonth.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dx_press.png"))); // NOI18N
        b_succMonth.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dx_over.png"))); // NOI18N
        add(b_succMonth);
        b_succMonth.setBounds(200, 60, 20, 20);

        b_succYear.setBackground(new java.awt.Color(230, 230, 230));
        b_succYear.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        b_succYear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dx.png"))); // NOI18N
        b_succYear.setBorderPainted(false);
        b_succYear.setContentAreaFilled(false);
        b_succYear.setFocusPainted(false);
        b_succYear.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b_succYear.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dx_press.png"))); // NOI18N
        b_succYear.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dx_over.png"))); // NOI18N
        add(b_succYear);
        b_succYear.setBounds(200, 30, 20, 20);

        jl_year.setBackground(new java.awt.Color(0, 0, 102));
        jl_year.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jl_year.setText("Year");
        jl_year.setIconTextGap(5);
        add(jl_year);
        jl_year.setBounds(10, 30, 70, 20);

        tfYear.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tfYear.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        tfYear.setOpaque(true);
        add(tfYear);
        tfYear.setBounds(110, 30, 90, 20);

        b_precYear.setBackground(new java.awt.Color(230, 230, 230));
        b_precYear.setFont(new java.awt.Font("Helvetica", 1, 12)); // NOI18N
        b_precYear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sx.png"))); // NOI18N
        b_precYear.setBorderPainted(false);
        b_precYear.setContentAreaFilled(false);
        b_precYear.setFocusPainted(false);
        b_precYear.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b_precYear.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sx_press.png"))); // NOI18N
        b_precYear.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sx_over.png"))); // NOI18N
        add(b_precYear);
        b_precYear.setBounds(90, 30, 20, 20);

        jP_calendar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jP_calendar.setLayout(new java.awt.BorderLayout());
        add(jP_calendar);
        jP_calendar.setBounds(10, 90, 210, 190);

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(35, 121, 170), 1, true));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jP_SelectedDay.setLayout(new javax.swing.BoxLayout(jP_SelectedDay, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane2.setViewportView(jP_SelectedDay);

        add(jScrollPane2);
        jScrollPane2.setBounds(227, 40, 185, 280);

        jL_validDates.setBackground(new java.awt.Color(0, 0, 102));
        jL_validDates.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/date.png"))); // NOI18N
        jL_validDates.setText("Selected Dates");
        jL_validDates.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(jL_validDates);
        jL_validDates.setBounds(227, 20, 180, 20);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_precMonth;
    private javax.swing.JButton b_precYear;
    private javax.swing.JButton b_succMonth;
    private javax.swing.JButton b_succYear;
    private javax.swing.JLabel jL_validDates;
    private javax.swing.JPanel jP_SelectedDay;
    private javax.swing.JPanel jP_calendar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jl_month;
    private javax.swing.JLabel jl_year;
    private javax.swing.JLabel tfMonth;
    private javax.swing.JLabel tfYear;
    // End of variables declaration//GEN-END:variables

    private JLabel labelup;         
    private Calendar cal;
    private Calendar ShowCal;
    private JPanel JP_labelup,pDay;
    private Vector Holidays = new Vector();
    boolean ColoraFestivi;
    private int oldYear = 0;
    private String pasquetta = "";
    private Calendar yesterday; 
    
    private Color colorFore_DAYNAME         = Color.white;    
    private Color colorBack_DAYNAME         = new Color(58,85,193);
    private Color colorFore_DAY             = Color.white;
    private Color colorBack_DAY_Ability     = Color.white;
    private Color colorBack_DAY_Disability  = new Color(230,230,230);
    
    //Possibilità giorni indietro selezionabili
    private int PreviousDaysSelectable = 31;
    private Vector V_DaywithData    = new Vector(PreviousDaysSelectable);
    private Vector V_DateSelected    = new Vector(PreviousDaysSelectable);
    private String DateSelected[]   = new String[PreviousDaysSelectable];
    
    private String StartDate    = "";
    private String EndDate      = "";
    private String StartDate_Format = "";
    private String EndDate_Format   = "";
    private long Date_min = 0;
    private long Date_max = 0;
    private Calendar appo_Cal = Calendar.getInstance();
    private MyJButton AppoMyButton_Start,AppoMyButton_End;
    private javax.swing.JLabel jL1;
    private javax.swing.JLabel jL2;
    private javax.swing.JLabel jL3;
    private javax.swing.JLabel jL4;
    
    private boolean Sel_TwoDateFuture = true;
    
    private Vector V_MyJButton_selected = new Vector();

    //private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    //private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    private JPanel jP_Validdate;
    private javax.swing.ImageIcon iconDateSel =  new javax.swing.ImageIcon(getClass().getResource("/images/b_mini_vok.png"));
    
    public STRUCT_PARAMS struct_params;    
}
class MyJButton extends javax.swing.JButton
{
    int target = 0;
    boolean selected = false;

    MyJButton() 
    {
        super();
        setFocusPainted(false);
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setBorderPainted(false);
        //setBorder(new javax.swing.border.LineBorder(Color.red, 3, true));
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(java.awt.Color.white, 4, true),
                                                         new javax.swing.border.LineBorder(java.awt.Color.red, 2, true)));
        setContentAreaFilled(false);
    }
    void set_target(int num)
    {
        this.target=num;
    }

    int get_target()
    {
        return target;
    }

    void set_Selected(boolean flag)
    {
        selected = flag;
        this.setBorderPainted(flag);
    }
    
    boolean get_Selected()
    {
        return selected;
    }
}

