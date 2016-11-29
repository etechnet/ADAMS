import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ADAMS_JP_Cal extends JPanel 
{
    protected int yy;
    protected int mm, dd;
    protected myJButton labs[][];
    protected int leadGap = 0;
    Calendar calendar = new GregorianCalendar();
    protected final int thisYear = calendar.get(Calendar.YEAR);
    protected final int thisMonth = calendar.get(Calendar.MONTH);
    private myJButton b0;
    private JComboBox monthChoice;
    private JComboBox yearChoice;
    private java.awt.Cursor Cur_hand = new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR);
    private java.awt.Color color_sel = new java.awt.Color(160,210,252);

    ADAMS_JP_Cal() 
    {
        super();
        setYYMMDD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH));
        buildGUI();
        recompute();
    }

  
    ADAMS_JP_Cal(int year, int month, int today) 
    {
        super();
        setYYMMDD(year, month, today);
        buildGUI();
        recompute();
    }

    private void setYYMMDD(int year, int month, int today) 
    {
        yy = year;
        mm = month;
        dd = today;
    }

    String[] months = { "January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December" };

  
    private void buildGUI() 
    {
        getAccessibleContext().setAccessibleDescription(
            "Calendar not accessible yet. Sorry!");
        setBorder(BorderFactory.createEtchedBorder());

        setLayout(new BorderLayout());

        JPanel tp = new JPanel();

        monthChoice = new JComboBox();
        monthChoice.setFont(ADAMS_GlobalParam.font_B10);
        tp.add(monthChoice);
    
    
        for (int i = 0; i < months.length; i++)
            monthChoice.addItem(months[i]);
        
        monthChoice.setSelectedItem(months[mm]);
        monthChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int i = monthChoice.getSelectedIndex();
                if (i >= 0) {
                  mm = i;
                  // System.out.println("Month=" + mm);
                  recompute();
                }
              }
            });
        monthChoice.getAccessibleContext().setAccessibleName("Months");
        monthChoice.getAccessibleContext().setAccessibleDescription("Choose a month of the year");
    
        yearChoice = new JComboBox();
        yearChoice.setFont(ADAMS_GlobalParam.font_B10);
        tp.add(yearChoice);
        yearChoice.setEditable(true);
        
        for (int i = yy - 5; i < yy + 11; i++)
            yearChoice.addItem(Integer.toString(i));
        
        yearChoice.setSelectedItem(Integer.toString(yy));
        yearChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int i = yearChoice.getSelectedIndex();
                if (i >= 0) {
                  yy = Integer.parseInt(yearChoice.getSelectedItem()
                      .toString());
                  // System.out.println("Year=" + yy);
                  recompute();
                }
              }
            });
        add(BorderLayout.CENTER, tp);

        JPanel bp = new JPanel();
        bp.setLayout(new GridLayout(7, 7));
        labs = new myJButton[6][7]; 

        bp.add(new myJButton("S",Color.red,Color.white));
        bp.add(new myJButton("M",Color.green.brighter(),Color.black));
        bp.add(new myJButton("T",Color.green.brighter(),Color.black));
        bp.add(new myJButton("W",Color.green.brighter(),Color.black));
        bp.add(new myJButton("T",Color.green.brighter(),Color.black));
        bp.add(new myJButton("F",Color.green.brighter(),Color.black));
        bp.add(new myJButton("S",Color.green.brighter(),Color.black));
    
        ActionListener dateSetter = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String num = e.getActionCommand();
            if (!num.equals("")) {
              // set the current day highlighted
              setDayActive(Integer.parseInt(num));
              // When this becomes a Bean, you can
              // fire some kind of DateChanged event here.
              // Also, build a similar daySetter for day-of-week btns.
            }
          }
        };

        b0 = new myJButton("",Color.white,Color.black);

        // Construct all the buttons, and add them.
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++) 
            {
                bp.add(labs[i][j] = new myJButton("",Color.white,Color.black));
                labs[i][j].addActionListener(dateSetter);
            }
        }
        add(BorderLayout.SOUTH, bp);
    }

    public final static int dom[] = { 31, 28, 31, 30, /* jan feb mar apr */
                                    31, 30, 31, 31, /* may jun jul aug */
                                    30, 31, 30, 31 /* sep oct nov dec */
    };
    
    protected void recompute() 
    {
        
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
            {
                labs[i][j].setText("");
                setCursor(null);
            }
        
        // System.out.println("Cal::recompute: " + yy + ":" + mm + ":" + dd);
        if (mm < 0 || mm > 11)
            throw new IllegalArgumentException("Month " + mm
                + " bad, must be 0-11");
        clearDayActive();
        calendar = new GregorianCalendar(yy, mm, dd);

        // Compute how much to leave before the first.
        // getDay() returns 0 for Sunday, which is just right.
        leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) - 1;
        // System.out.println("leadGap = " + leadGap);

        int daysInMonth = dom[mm];
             
        if (isLeap(calendar.get(Calendar.YEAR)) && mm == 1)
            ++daysInMonth;

        // Blank out the labels before 1st day of month
        for (int i = 0; i < leadGap; i++) 
        {
            labs[0][i].setText("");
        }

        // Fill in numbers for the day of month.
        for (int i = 1; i <= daysInMonth; i++) 
        {
              myJButton b = labs[(leadGap + i - 1) / 7][(leadGap + i - 1) % 7];
              b.setText(Integer.toString(i));
              b.setCursor(Cur_hand);
              //System.out.println(Integer.toString(i));
        }

        // 7 days/week * up to 6 rows
        for (int i = leadGap + 1 + daysInMonth; i < 6 * 7; i++) 
        {
            labs[(i) / 7][(i) % 7].setText("");
        }

        // Shade current day, only if current month
        if (thisYear == yy && mm == thisMonth)
            setDayActive(dd); // shade the box for today

        // Say we need to be drawn on the screen
        repaint();
    }

  
    public boolean isLeap(int year) 
    {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
            return true;
        return false;
    }

    /** Set the year, month, and day */
    public void setDate(int yy, int mm, int dd) 
    {
        // System.out.println("Cal::setDate");
        this.yy = yy;
        this.mm = mm; // starts at 0, like Date
        this.dd = dd;
        recompute();
    }

  /** Unset any previously highlighted day */
    private void clearDayActive() {
        myJButton b;

        // First un-shade the previously-selected square, if any
        if (activeDay > 0) {
          b = labs[(leadGap + activeDay - 1) / 7][(leadGap + activeDay - 1) % 7];
          b.setBackground(b0.getBackground());
          b.repaint();
          activeDay = -1;
        }
    }

    private int activeDay = -1;

  /** Set just the day, on the current month */
    public void setDayActive(int newDay) 
    {
        clearDayActive();

        // Set the new one
        if (newDay <= 0)
            dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH); 
        else
            dd = newDay;
        
        // Now shade the correct square
        Component square = labs[(leadGap + newDay - 1) / 7][(leadGap + newDay - 1) % 7];
        square.setBackground(color_sel);
        square.repaint();
        activeDay = newDay;
    }
    
    public int getYear()
    {
        return(yy);
    }
    public int getMonth()
    {
        return(mm+1);
    }
    public int getDay()
    {
        return(dd);
    }
   
    public class myJButton extends JButton
    {
        public myJButton(String target)
        {
            super();
            setText(target);
            setFocusPainted(false);
            setMargin(new java.awt.Insets(0, 0, 0, 0));
            setMaximumSize(new java.awt.Dimension(20, 20));
            setMinimumSize(new java.awt.Dimension(20, 20));
            setPreferredSize(new java.awt.Dimension(20, 20));
            setFont(ADAMS_GlobalParam.font_B10);
        }
        public myJButton(String target, Color bk,Color fg)
        {
            super();
            setText(target);
            setBackground(bk);
            setForeground(fg);
            setFocusPainted(false);
            setMargin(new java.awt.Insets(0, 0, 0, 0));
            setMaximumSize(new java.awt.Dimension(20, 20));
            setMinimumSize(new java.awt.Dimension(20, 20));
            setPreferredSize(new java.awt.Dimension(20, 20));
            setFont(ADAMS_GlobalParam.font_B10);
        }  
  }
}