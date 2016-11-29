/*
 * jP_ExpressionParser.java
 *
 * Created on 20 novembre 2006, 11.13
 */


import java.awt.*;
import java.awt.event.*;
import org.nfunk.jep.*;
import org.nfunk.jep.type.*;
import javax.swing.table.*;
import java.util.Vector;
import com.braju.format.*;

public class jP_ExpressionParser extends javax.swing.JPanel implements Runnable {

    /** Creates new form jP_ExpressionParser */
    public jP_ExpressionParser(MDM_JF_Pivot f_Pivot,JF_MathExpression parentDialog) 
    {
        this.TABLE = f_Pivot.getTable_Pivot();
        this.PIVOT_MAXROW = TABLE.getRowCount();
        this.parent_Dialog = parentDialog;
        this.parent_f_Pivot = f_Pivot;        
        this.Arr_INDEX_Column_ORIGIN = f_Pivot.getIndexColumnOrigin();
        this.VALUE_ROUND = f_Pivot.getValueRound();
                
        initComponents();
        
        eventButtons = (new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jButtonColActionPerformed(evt);
                    }
                });
        
        V_NameVarColumns = new Vector();
        V_NameCounterColumns = new Vector();
        V_IndexValueVarColumns = new Vector();
        String CTRL_COL_MATH = InitTagHTMLColumn+TAG_Name_COLUMN_MATH;
                
        for(int i=0; i<this.TABLE.getColumnCount(); i++)
        {
            //System.out.println("Name Column = "+this.TABLE.getColumnName(i));                        
            String NameColumn_HTML = this.TABLE.getColumnName(i).trim();
            TableColumn hideColumn = (TableColumn)TABLE.getColumnModel().getColumn(i);
                        
            if (NameColumn_HTML.indexOf(CTRL_COL_MATH) != -1)
            {
                V_IndexValueVarColumns.addElement(new Integer(i)); 
                V_NameVarColumns.addElement(TAG_Name_COLUMN_MATH);                        
                jCBox_outCol.addItem(TAG_Name_COLUMN_MATH);
                
                String nameMathColumns = new String(NameColumn_HTML);
                nameMathColumns = nameMathColumns.replaceAll(InitTagHTMLColumn,"");
                nameMathColumns = nameMathColumns.replaceAll(TAG2,"");
                nameMathColumns = nameMathColumns.replaceAll(TAG3,"");
                V_NameCounterColumns.addElement(nameMathColumns.substring(TAG_Name_COLUMN_MATH.length()));
            }
            else if(/*(this.TABLE.getColumnClass(i).getName().equals("java.lang.String") == false ) && */ (hideColumn.getPreferredWidth() > 0 ) )
            {                  
                //System.out.println("------ Valore -----> "+this.TABLE.getValueAt(0,i));
                if (this.TABLE.getColumnClass(i).getName().equals("java.lang.String")) // Controllo chiavi Convertibili
                {
                    try
                    {
                        int CTRL_value = new Integer((String)this.TABLE.getValueAt(0,i)).intValue();
                    }
                    catch(NumberFormatException e)
                    {
                        //System.out.println("Functions: "+this.TABLE.getValueAt(0,i)+" NON CONVERTIBILE ");
                        continue;
                    }                
                }
                
                javax.swing.JButton jButton1 = new javax.swing.JButton();
                
                if(this.TABLE.getColumnClass(i).getName().equals("java.lang.String"))
                    jButton1.setBackground(new Color(0,128,192)); //0080c0
                else
                    jButton1.setBackground(java.awt.Color.gray);
                
                jButton1.setText(""+NameColumn_HTML);
                jButton1.setToolTipText("Push button to select column.");
                jButton1.setFocusPainted(false);
                jButton1.setMargin(new java.awt.Insets(2, 2, 2, 2));
                jButton1.setMinimumSize(new java.awt.Dimension(90, 42));
                jButton1.setPreferredSize(new java.awt.Dimension(90, 42));
                jButton1.addActionListener(eventButtons);
                jButton1.setFont(staticLib.fontA10);
                jButton1.setCursor(Cur_hand);

                jP_Colbuttons.add(jButton1);                    

                V_IndexValueVarColumns.addElement(new Integer(i)); 
                //V_IndexValueVarColumns.addElement(this.TABLE.getValueAt(0,i));    

                String str_var = "";

                if(NameColumn_HTML.indexOf(InitTagHTMLColumn) != -1) // se contiene TAG HTML 
                {
                    NameColumn_HTML = NameColumn_HTML.replaceAll(InitTagHTMLColumn,""); //Rimuovo Tag HTML

                    java.util.StringTokenizer tokenStrName = new java.util.StringTokenizer(NameColumn_HTML,"<");                    

                    str_var = tokenStrName.nextToken();
                    V_NameVarColumns.addElement(str_var);                        
                    jCBox_outCol.addItem(str_var);                        
                }
                else
                    V_NameVarColumns.addElement(NameColumn_HTML);

                String nameCounters = new String(NameColumn_HTML);
                nameCounters = nameCounters.replaceAll(InitTagHTMLColumn,"");
                nameCounters = nameCounters.replaceAll(TAG2,"");
                nameCounters = nameCounters.replaceAll(TAG3,"");

                V_NameCounterColumns.addElement(nameCounters.substring(str_var.length()));
               
            }
           
        }  
        jCBox_outCol.setSelectedItem(TAG_Name_COLUMN_MATH);
        int IndexItemSel = jCBox_outCol.getSelectedIndex();
        jTextNameColumn.setText((String)V_NameCounterColumns.elementAt(IndexItemSel));
        INDEX_COL_PIVOT_OUTPUT = ((Integer)V_IndexValueVarColumns.elementAt(IndexItemSel)).intValue();
        
        jText_row.setDocument(new JTextFieldFilter(JTextFieldFilter.NUMERIC,Integer.MAX_VALUE));
        
        //Font GUI
        jL_descExpre.setFont(staticLib.fontA10);
        exprField.setFont(staticLib.fontA10);
        jL_descResult.setFont(staticLib.fontA10);
        resultLabel.setFont(staticLib.fontA10);
        jL_descErr.setFont(staticLib.fontA10);
        jScrollPane1.setFont(staticLib.fontA10);
        errorTextArea.setFont(staticLib.fontA10);
        //implicitCheckbox.setFont(staticLib.fontA10);
        //jL_DescOption.setFont(staticLib.fontA10);
        jText_row.setFont(staticLib.fontA10);
        jL_row.setFont(staticLib.fontA10);
        jL_outputCol.setFont(staticLib.fontA10);
        jCBox_outCol.setFont(staticLib.fontA10);
        jTextNameColumn.setFont(staticLib.fontA10);
        jL_1.setFont(staticLib.fontA10);
                
        //Font PopUP
        jPopupMenu1.setFont(staticLib.fontA10);
        jMenu_operators.setFont(staticLib.fontA10);
        jMenu_add.setFont(staticLib.fontA10);
        jMenu_div.setFont(staticLib.fontA10);
        jMenu_multi.setFont(staticLib.fontA10);
        jMenu_sott.setFont(staticLib.fontA10);
        jMenu_modulus.setFont(staticLib.fontA10);
        jMenu_power.setFont(staticLib.fontA10);
        jMenu_functios.setFont(staticLib.fontA10);
        jMenu_nat_logar.setFont(staticLib.fontA10);
        jMenu_expon.setFont(staticLib.fontA10);
        jMenu_log10.setFont(staticLib.fontA10);
        jMenu_sum.setFont(staticLib.fontA10);
        jMenu_squareroot.setFont(staticLib.fontA10);
        jMenu_not_equal.setFont(staticLib.fontA10);
        jMenu_equal.setFont(staticLib.fontA10);
        jMenu_booleanOR.setFont(staticLib.fontA10);
        jMenu_booleanAnd.setFont(staticLib.fontA10);
        jMenu_grater_than.setFont(staticLib.fontA10);
        jMenu_less_than.setFont(staticLib.fontA10);
        jMenu_more_OR_equal.setFont(staticLib.fontA10);
        jMenu_less_OR_equal.setFont(staticLib.fontA10);
        jMenu_booleanNot.setFont(staticLib.fontA10);
        jMenu_str.setFont(staticLib.fontA10);
        jMenu_if.setFont(staticLib.fontA10);
        jMenu_random.setFont(staticLib.fontA10);
        jMenu_absolute_value.setFont(staticLib.fontA10);
        
        //Cursor
        jPopupMenu1.setCursor(Cur_hand);
        jMenu_operators.setCursor(Cur_hand);
        jMenu_add.setCursor(Cur_hand);
        jMenu_div.setCursor(Cur_hand);
        jMenu_multi.setCursor(Cur_hand);
        jMenu_sott.setCursor(Cur_hand);
        jMenu_modulus.setCursor(Cur_hand);
        jMenu_power.setCursor(Cur_hand);
        jMenu_functios.setCursor(Cur_hand);
        jMenu_nat_logar.setCursor(Cur_hand);
        jMenu_expon.setCursor(Cur_hand);
        jMenu_log10.setCursor(Cur_hand);
        jMenu_sum.setCursor(Cur_hand);
        jMenu_squareroot.setCursor(Cur_hand);
        jB_columnResult.setCursor(Cur_hand);
        jMenu_not_equal.setCursor(Cur_hand);
        jMenu_equal.setCursor(Cur_hand);
        jMenu_booleanOR.setCursor(Cur_hand);
        jMenu_booleanAnd.setCursor(Cur_hand);
        jMenu_grater_than.setCursor(Cur_hand);
        jMenu_less_than.setCursor(Cur_hand);
        jMenu_more_OR_equal.setCursor(Cur_hand);
        jMenu_less_OR_equal.setCursor(Cur_hand);
        jMenu_booleanNot.setCursor(Cur_hand);
        jMenu_str.setCursor(Cur_hand);
        jMenu_if.setCursor(Cur_hand);
        jMenu_random.setCursor(Cur_hand);
        jMenu_absolute_value.setCursor(Cur_hand);
        
        init();
        
    }
    
    
    /**
     * The initialization function of the applet. It adds all the
     * components such as text fields and also creates the JEP object
     */
    public void init() 
    {
        // initialize value for x
        //xValue = 10;

        // Set up the parser (more initialization in parseExpression()) 
        myParser = new JEP();
        myParser.initFunTab(); // clear the contents of the function table
        myParser.addStandardFunctions();
        myParser.setTraverse(true);
        // simulate changed options to initialize output
        
        optionsChanged();
    }
    

    /**
     * Parses the current expression in the exprField. This method also
     * re-initializes the contents of the symbol and function tables. This
     * is neccessary because the "allow undeclared variables" option adds
     * variables from expressions to the symbol table.
     */
    private void parseExpression() 
    {
        String str_rowtable = jText_row.getText().trim();
        int indexrowPivot = 0;
                
        if(str_rowtable.length() == 0)
        {            
            jText_row.setText(""+1);
        }
        else
        {               
            try
            {
                if(str_rowtable.startsWith("0"))
                    jText_row.setText(""+1);                
                else
                {
                    indexrowPivot = new Integer(str_rowtable).intValue();
                    if(indexrowPivot == 0)
                        jText_row.setText(""+1);
                    else
                        indexrowPivot = indexrowPivot-1;
                }
            }
            catch (Exception e)
            {
                indexrowPivot = 0;
            }         
            
        }
        
        //System.out.println("indexrowPivot "+indexrowPivot);
                
        myParser.initSymTab(); // clear the contents of the symbol table
        myParser.addStandardConstants();
        myParser.addComplex(); // among other things adds i to the symbol table
        //myParser.addVariable("x", xValue);
        
        try
        {
            for(int i=0; i<V_NameVarColumns.size(); i++)
            {
                String var = new String(""+V_NameVarColumns.elementAt(i));            
                //if(var.equals(TAG_Name_COLUMN_MATH) == false)
                //{
                    int indexColumnPivot = ((Integer)V_IndexValueVarColumns.elementAt(i)).intValue();           
                    Object objValue = this.TABLE.getValueAt(indexrowPivot,indexColumnPivot);

                    if(objValue == null)
                        objValue = new Double(0.0);
                    
                    //System.out.println("getClass --- "+objValue.getClass().getName());
                    
                    if(objValue.getClass().getName().equals("java.lang.String")) // -- aggiunto chiavi
                    {
                        String str_objValue = new String((String)objValue).trim();
                        if(str_objValue.length() > 0)
                        {                        
                            try
                            {
                                if((str_objValue.indexOf(".") == -1) || (str_objValue.indexOf(",") == -1))
                                {
                                    double value_d = (double)(new Long(str_objValue).longValue());                                
                                    value_d = setRound(value_d);
                                    myParser.addVariable(var, value_d);    
                                }   
                                else
                                {
                                    double value_of_S = new Double(str_objValue).doubleValue();
                                    myParser.addVariable(var, value_of_S);
                                }
                            }
                            catch (NumberFormatException e)
                            {
                                //System.out.println(var+" 1 --> KEY NON CONVERTIBILE "+str_objValue);
                                //System.out.println(e);
                            }
                        }
                    } 
                    else if(objValue.getClass().getName().equals("java.lang.Long"))
                    {
                        double value_d = (double)(((Long)objValue).longValue());
                        value_d = setRound(value_d);
                        myParser.addVariable(var, value_d); 
                    }
                    else
                        myParser.addVariable(var, objValue);                      
                    
               //}
            }        
            myParser.parseExpression(exprField.getText());
        }
        catch  (Exception e)
        {
            System.out.println("Exception parseExpression "+e);
        }

    }
    
    public double setRound(double value_d)
    {        
        java.math.BigDecimal bdec = new java.math.BigDecimal(value_d);
        bdec = bdec.setScale(this.VALUE_ROUND,java.math.BigDecimal.ROUND_HALF_UP);   
        return bdec.doubleValue();
    }
    
        
    private void getResultRows_TH()
    {      
        dialog_wait = new javax.swing.JDialog(parent_Dialog,true);        
        jPrBar = new javax.swing.JProgressBar();
        jPrBar.setForeground(java.awt.Color.blue);    
        jLabel_wait = new javax.swing.JLabel();
        jLabel_wait.setText("Progress...");
        
        dialog_wait.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);        
        dialog_wait.getContentPane().add(jLabel_wait, java.awt.BorderLayout.NORTH);
        dialog_wait.getContentPane().add(jPrBar,java.awt.BorderLayout.CENTER);
        dialog_wait.setSize(450,90);
        dialog_wait.setLocationRelativeTo(parent_Dialog);
       
        
        TH_EXIT = false;
        OperationTH = 0;
        th = null;
        th = new Thread(this,"getResultRows()");
        th.start();
        
        //dialog_wait.show();
	dialog_wait.setVisible(true);
    }
    
    public void stop_TH()
    {
        TH_EXIT = true;
    }
    
    public void run()
    {
        this.setCursor(Cur_wait);
        errorTextArea.setCursor(Cur_wait);
        
        if(OperationTH == 0) // getResultRows
        {                     
            int NUMROWS = this.TABLE.getRowCount();        

            unit_bar = 1;
            if(NUMROWS >= 100)
            {
                unit_bar = NUMROWS/100;
                //System.out.println("jPrBar ------------------ "+jPrBar);
                jPrBar.setMaximum(NUMROWS);                
            }
            jPrBar.setValue(0);
            getResultRows();           
                        
        }
        OperationTH = -1;
        errorTextArea.setCursor(Cur_default);
        this.setCursor(Cur_default);
    }
    
    
    private void getResultRows()
    {   
        this.setCursor(Cur_wait);
        errorTextArea.setCursor(Cur_wait);
        
        /*System.out.println();
        System.out.println();
        System.out.println("getResultRows.........");*/
        
        if(myParser.getErrorInfo() != null)
        {
            staticLib.optionPanel("Syntax Error.", "Error",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            this.setCursor(Cur_default);
            errorTextArea.setCursor(Cur_default);
            dialog_wait.setVisible(false);
            dialog_wait.dispose();
            return;
        }
        else
        {        
            String namecol = (String)V_NameCounterColumns.elementAt(jCBox_outCol.getSelectedIndex());
            String newNameCol_text = jTextNameColumn.getText().trim();

            if(namecol.equals(newNameCol_text) == false )
            {        
                String letter = ""+jCBox_outCol.getSelectedItem();                        
                String newNameCol_HTML = InitTagHTMLColumn + letter + TAG2+ newNameCol_text+ TAG3;                        

                TABLE.getColumnModel().getColumn(INDEX_COL_PIVOT_OUTPUT).setHeaderValue(newNameCol_HTML);
                TABLE.getTableHeader().resizeAndRepaint();
            }  
                        
            
            // Usando la Struttura dataPivot, in caso di spostamento delle colonne, si dovranno utilizzare comunque gli indici delle colonne Originari.
            Object[][] dataPivot = parent_f_Pivot.getTableModelDataValues();
            int NUM_ROWS = dataPivot.length;
            
            VAR_PIVOT_OUTPUT = ""+jCBox_outCol.getSelectedItem();
            INDEX_COL_PIVOT_OUTPUT = getIndexColumn_DATA_PIVOT(""+VAR_PIVOT_OUTPUT);
            
         
            for(int row=0; row<NUM_ROWS;row++)
            {            
                try
                {
                    if(unit_bar == row)
                    {
                        //System.out.println("setValue "+unit_bar);
                        jPrBar.setValue(unit_bar);
                        jLabel_wait.setText("Progress... (row: "+unit_bar+")");
                        unit_bar = unit_bar+unit_bar;

                        if(TH_EXIT)
                        {
                            //System.out.println("ESCI TH MATH....");
                            dialog_wait.setVisible(false);
                            dialog_wait.dispose();
                            return;
                        }
                    }

                    for(int col=0; col<V_IndexValueVarColumns.size(); col++)
                    {
                        String var = new String(""+V_NameVarColumns.elementAt(col)); 

                        int indexColumnPivot = getIndexColumn_DATA_PIVOT(var);

                        Object objValue = dataPivot[row][indexColumnPivot];                    
                        //System.out.println("indexColumnPivot "+indexColumnPivot);
                        //System.out.println(objValue.getClass().getName());

                        //if((var.equals(TAG_Name_COLUMN_MATH) == false))
                        
                        if(objValue != null)
                        {                            
                            if(objValue.getClass().getName().equals("java.lang.String")) // aggiunto chiavi
                            {
                                String str_objValue = new String((String)objValue).trim();
                                if(str_objValue.length() > 0)
                                {
                                    try
                                    {
                                        if((str_objValue.indexOf(".") == -1) || (str_objValue.indexOf(",") == -1))
                                        {
                                            Long value_long = new Long(str_objValue);
                                            //System.out.println(value_long +" long");                              
                                            myParser.addVariable(var,value_long);  // Long
                                        }   
                                        else
                                        {
                                            double value_of_S = new Double(str_objValue).doubleValue();
                                            java.math.BigDecimal bdec = new java.math.BigDecimal(value_of_S);
                                            bdec = bdec.setScale(this.VALUE_ROUND,java.math.BigDecimal.ROUND_HALF_UP);
                                            //System.out.println("BigDecimal = " +bdec);
                                            myParser.addVariable(var, bdec.doubleValue());
                                        }
                                    }
                                    catch (NumberFormatException e)
                                    {
                                        //System.out.println(var + "getResultRows() --> KEY NON CONVERTIBILE "+str_objValue);
                                        //System.out.println(e);                                        
                                    }
                                }
                            }
                            else if(objValue.getClass().getName().equals("java.lang.Double"))
                            {            
                                // Arrotondamento 
                                double value_d = (double)(((Double)objValue).doubleValue());
                                
                                java.math.BigDecimal bdec = new java.math.BigDecimal(value_d);
                                bdec = bdec.setScale(this.VALUE_ROUND,java.math.BigDecimal.ROUND_HALF_UP);  

                                //System.out.println("BigDecimal = " +bdec);
                                myParser.addVariable(var, bdec.doubleValue()); 
                            }
                            else
                            {
                                myParser.addVariable(var, objValue);  // Long
                            }
                        }
                    }

                    myParser.parseExpression(exprField.getText());
                    Object obj_result = myParser.getValueAsObject();
                    
                    //System.out.println("obj_result "+obj_result.getClass().getName());
                    
                    if( (obj_result.getClass().getName().equals("java.lang.Long")) || (obj_result.getClass().getName().equals("java.lang.String")) )                    
                    {   
                        //System.out.println("-----------> ENTRO --- String");
                        dataPivot[row][INDEX_COL_PIVOT_OUTPUT] = obj_result; 
                    }
                    else
                    {
                        //Float result = new Float(setRound(((Double)obj_result).doubleValue()));
                        Double result = new Double(setRound(((Double)obj_result).doubleValue())); 
                        
                        //System.out.println(" result row "+(row+1)+" ==>"+ result.floatValue() );   
                        //System.out.println("INDEX_COL_PIVOT_OUTPUT "+INDEX_COL_PIVOT_OUTPUT);
                        dataPivot[row][INDEX_COL_PIVOT_OUTPUT] = result;
                    }

                }
                catch(Exception e)
                {
                    //System.out.println("Errore jP_ExpressionParser.java  see --> getResultRows() "+e);
                    //System.out.println(" result row "+(row+1)+" ==>"+ obj_result.toString());
                }                
            } 
            TABLE.repaint();
        }
        
        if( VAR_PIVOT_OUTPUT.equals(TAG_Name_COLUMN_MATH) )
                parent_f_Pivot.showColumnMathResult();
            
        jPrBar.setValue(jPrBar.getMaximum());
        jPrBar.setForeground(java.awt.Color.green.darker());
               
        dialog_wait.setVisible(false);
        dialog_wait.dispose();
        parent_Dialog.close_JF_MathExpression();
        
        parent_f_Pivot.repaint();
        parent_f_Pivot.reload_setTotalColumns();
        
        //System.out.println("FINE getResultRows........."); 
        
    }
    
    private int getIndexColumn_DATA_PIVOT(String NameVarColumn)
    {
        //System.out.println("NameVarColumn "+NameVarColumn);
        for(int i=0; i<Arr_INDEX_Column_ORIGIN.length; i++)
        {
            if(Arr_INDEX_Column_ORIGIN[i].equals(NameVarColumn))
            {
                //System.out.println("index column "+i);
                return i;
            }
        }
        System.out.println("Errore getIndexColumn_DATA_PIVOT -1");
        return -1;        
    }
    
    
    /**
     * Whenever the expression is changed, this method is called.
     * The expression is parsed, and the updateResult() method
     * invoked.
     */
    private void exprFieldTextValueChanged() 
    {
        parseExpression();
        updateResult();
    }
    
    /**
     * Every time the value in the x field is changed, this method is
     * called. It takes the value from the field as a double, and
     * sets the value of x in the parser.
     */
   /* private void xFieldTextValueChanged() 
    {
        try {
                xValue = Double.valueOf(xField.getText()).doubleValue();
        } catch (NumberFormatException e) {
                System.out.println("Invalid format in xField");
                xValue = 0;
        }

        myParser.addVariable("x", xValue);

        updateResult();
    }*/

    /**
     * Every time one of the options is changed, this method is called. The
     * parser settings are adjusted to the GUI settings, the expression is
     * parsed again, and the results updated.
     */
    private void optionsChanged() 
    {
        //myParser.setImplicitMul(implicitCheckbox.isSelected());
        //myParser.setAllowUndeclared(undeclaredCheckbox.getState());
        parseExpression();
        updateResult();
    }

    /**
     * This method uses JEP's getValueAsObject() method to obtain the current
     * value of the expression entered.
     */
    private void updateResult() 
    {
        Object result;
        String errorInfo;

        // Get the value
        result = myParser.getValueAsObject();

        // Is the result ok?
        if (result!=null) 
        {            
            try
            {
                double val_result = setRound( ((Double)result).doubleValue());
                
                // Formattazione valore in Stringa                   
                String strdecimal = "%d";
                if(this.VALUE_ROUND > 0)
                    strdecimal  = "%0."+this.VALUE_ROUND+"f";

                String Str_Value = Format.sprintf(strdecimal, new Parameters(val_result));
                resultLabel.setText(""+Str_Value);
            }
            catch(Exception e)
            {
                resultLabel.setText(result.toString());
            }
        } 
        else 
        {
                resultLabel.setText("");
        }

        // Get the error information
        if ((errorInfo = myParser.getErrorInfo()) != null) 
        {
                errorTextArea.setText(errorInfo);
                errorTextArea.setCaretPosition(0);
        } 
        else 
        {
                errorTextArea.setText("");
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu_operators = new javax.swing.JMenu();
        jMenu_add = new javax.swing.JMenuItem();
        jMenu_div = new javax.swing.JMenuItem();
        jMenu_multi = new javax.swing.JMenuItem();
        jMenu_sott = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenu_modulus = new javax.swing.JMenuItem();
        jMenu_power = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenu_grater_than = new javax.swing.JMenuItem();
        jMenu_less_than = new javax.swing.JMenuItem();
        jMenu_less_OR_equal = new javax.swing.JMenuItem();
        jMenu_more_OR_equal = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenu_booleanAnd = new javax.swing.JMenuItem();
        jMenu_booleanNot = new javax.swing.JMenuItem();
        jMenu_booleanOR = new javax.swing.JMenuItem();
        jMenu_equal = new javax.swing.JMenuItem();
        jMenu_not_equal = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenu_functios = new javax.swing.JMenu();
        jMenu_absolute_value = new javax.swing.JMenuItem();
        jMenu_nat_logar = new javax.swing.JMenuItem();
        jMenu_expon = new javax.swing.JMenuItem();
        jMenu_if = new javax.swing.JMenuItem();
        jMenu_log10 = new javax.swing.JMenuItem();
        jMenu_random = new javax.swing.JMenuItem();
        jMenu_sum = new javax.swing.JMenuItem();
        jMenu_squareroot = new javax.swing.JMenuItem();
        jMenu_str = new javax.swing.JMenuItem();
        jP_N = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jP_Colbuttons = new javax.swing.JPanel();
        jP_C = new javax.swing.JPanel();
        jL_descExpre = new javax.swing.JLabel();
        jp_exp = new javax.swing.JPanel();
        jB_AllFunction = new javax.swing.JButton();
        exprField = new javax.swing.JTextField();
        jL_descResult = new javax.swing.JLabel();
        resultLabel = new javax.swing.JLabel();
        jL_descErr = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        errorTextArea = new javax.swing.JTextArea();
        jText_row = new javax.swing.JTextField();
        jL_row = new javax.swing.JLabel();
        jP_S = new javax.swing.JPanel();
        jL_outputCol = new javax.swing.JLabel();
        jCBox_outCol = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jL_1 = new javax.swing.JLabel();
        jTextNameColumn = new javax.swing.JTextField();
        jB_columnResult = new javax.swing.JButton();
        
        jMenu_operators.setText("Operators");
        jMenu_add.setText("Addition: +");
        jMenu_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_addActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_add);
        jMenu_div.setText("Division: /");
        jMenu_div.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_divActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_div);
        jMenu_multi.setText("Multiplication: *");
        jMenu_multi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_multiActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_multi);
        jMenu_sott.setText("Subtraction: -");
        jMenu_sott.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_sottActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_sott);
        jMenu_operators.add(jSeparator2);
        jMenu_modulus.setText("Modulus:  %");
        jMenu_modulus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_modulusActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_modulus);
        jMenu_power.setText("Power: ^");
        jMenu_power.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_powerActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_power);
        jMenu_operators.add(jSeparator3);
        jMenu_grater_than.setText("Greater Than: >");
        jMenu_grater_than.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_grater_thanActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_grater_than);
        jMenu_less_than.setText("Less Than: <");
        jMenu_less_than.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_less_thanActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_less_than);
        jMenu_less_OR_equal.setText("Less or Equal: <=");
        jMenu_less_OR_equal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_less_OR_equalActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_less_OR_equal);
        jMenu_more_OR_equal.setText("More or Equal: >=");
        jMenu_more_OR_equal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_more_OR_equalActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_more_OR_equal);
        jMenu_operators.add(jSeparator4);
        jMenu_booleanAnd.setText("Boolean And: &&");
        jMenu_booleanAnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_booleanAndActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_booleanAnd);
        jMenu_booleanNot.setText("Boolean Not: !");
        jMenu_booleanNot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_booleanNotActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_booleanNot);
        jMenu_booleanOR.setText("Boolean Or: ||");
        jMenu_booleanOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_booleanORActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_booleanOR);
        jMenu_equal.setText("Equal: ==");
        jMenu_equal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_equalActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_equal);
        jMenu_not_equal.setText("Not Equal: !=");
        jMenu_not_equal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_not_equalActionPerformed(evt);
            }
        });
        
        jMenu_operators.add(jMenu_not_equal);
        jPopupMenu1.add(jMenu_operators);
        jPopupMenu1.add(jSeparator1);
        jMenu_functios.setText("Functions");
        jMenu_absolute_value.setText("Absolute Value: abs(x)");
        jMenu_absolute_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_absolute_valueActionPerformed(evt);
            }
        });
        
        jMenu_functios.add(jMenu_absolute_value);
        jMenu_nat_logar.setText("Natural Logarithm: In(x) ");
        jMenu_nat_logar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_nat_logarActionPerformed(evt);
            }
        });
        
        jMenu_functios.add(jMenu_nat_logar);
        jMenu_expon.setText("Exponential: exp(x)    ");
        jMenu_expon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_exponActionPerformed(evt);
            }
        });
        
        jMenu_functios.add(jMenu_expon);
        jMenu_if.setText("If: if(cond,trueval,falseval)");
        jMenu_if.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_ifActionPerformed(evt);
            }
        });
        
        jMenu_functios.add(jMenu_if);
        jMenu_log10.setText("Logarithm base 10: log(x)    ");
        jMenu_log10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_log10ActionPerformed(evt);
            }
        });
        
        jMenu_functios.add(jMenu_log10);
        jMenu_random.setText("Random number (0-1): rand()");
        jMenu_random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_randomActionPerformed(evt);
            }
        });
        
        jMenu_functios.add(jMenu_random);
        jMenu_sum.setText("Sum: sum(x,y,..)");
        jMenu_sum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_sumActionPerformed(evt);
            }
        });
        
        jMenu_functios.add(jMenu_sum);
        jMenu_squareroot.setText("Square Root: sqrt(x)");
        jMenu_squareroot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_squarerootActionPerformed(evt);
            }
        });
        
        jMenu_functios.add(jMenu_squareroot);
        jMenu_str.setText("Str (number to string): str(x)");
        jMenu_str.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_strActionPerformed(evt);
            }
        });
        
        jMenu_functios.add(jMenu_str);
        jPopupMenu1.add(jMenu_functios);
        
        setLayout(new java.awt.BorderLayout(4, 4));
        
        setBackground(new java.awt.Color(230, 230, 230));
        jP_N.setLayout(new java.awt.BorderLayout(5, 5));
        
        jP_N.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane2.setBorder(new javax.swing.border.TitledBorder(null, " Column Counters", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11)));
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setMinimumSize(new java.awt.Dimension(22, 95));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(13, 95));
        jScrollPane2.setOpaque(false);
        jP_Colbuttons.setBackground(new java.awt.Color(230, 230, 230));
        jScrollPane2.setViewportView(jP_Colbuttons);
        
        jP_N.add(jScrollPane2, java.awt.BorderLayout.CENTER);
        
        add(jP_N, java.awt.BorderLayout.NORTH);
        
        jP_C.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_C.setOpaque(false);
        jL_descExpre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kcalc.png")));
        jL_descExpre.setText("Data Function:");
        jL_descExpre.setMinimumSize(new java.awt.Dimension(125, 24));
        jL_descExpre.setPreferredSize(new java.awt.Dimension(125, 24));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(10, 3, 3, 3);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jP_C.add(jL_descExpre, gridBagConstraints1);
        
        jp_exp.setLayout(new javax.swing.BoxLayout(jp_exp, javax.swing.BoxLayout.X_AXIS));
        
        jp_exp.setMinimumSize(new java.awt.Dimension(135, 24));
        jp_exp.setPreferredSize(new java.awt.Dimension(235, 24));
        jp_exp.setOpaque(false);
        jB_AllFunction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fx.png")));
        jB_AllFunction.setToolTipText("Data Functions");
        jB_AllFunction.setContentAreaFilled(false);
        jB_AllFunction.setFocusPainted(false);
        jB_AllFunction.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_AllFunction.setMinimumSize(new java.awt.Dimension(20, 20));
        jB_AllFunction.setPreferredSize(new java.awt.Dimension(24, 24));
        jB_AllFunction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jB_AllFunctionMouseReleased(evt);
            }
        });
        
        jp_exp.add(jB_AllFunction);
        
        exprField.setMinimumSize(new java.awt.Dimension(100, 24));
        exprField.setPreferredSize(new java.awt.Dimension(200, 24));
        exprField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                exprFieldKeyReleased(evt);
            }
        });
        
        jp_exp.add(exprField);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(10, 3, 3, 3);
        gridBagConstraints1.weightx = 1.0;
        jP_C.add(jp_exp, gridBagConstraints1);
        
        jL_descResult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/result.png")));
        jL_descResult.setText("Result:");
        jL_descResult.setPreferredSize(new java.awt.Dimension(100, 24));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(3, 3, 3, 3);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.WEST;
        jP_C.add(jL_descResult, gridBagConstraints1);
        
        resultLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        resultLabel.setMinimumSize(new java.awt.Dimension(150, 24));
        resultLabel.setPreferredSize(new java.awt.Dimension(250, 24));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(3, 3, 3, 3);
        gridBagConstraints1.weightx = 1.0;
        jP_C.add(resultLabel, gridBagConstraints1);
        
        jL_descErr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info22.png")));
        jL_descErr.setText("Info:");
        jL_descErr.setPreferredSize(new java.awt.Dimension(100, 22));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(3, 3, 3, 3);
        gridBagConstraints1.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jP_C.add(jL_descErr, gridBagConstraints1);
        
        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 60));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 80));
        errorTextArea.setEditable(false);
        jScrollPane1.setViewportView(errorTextArea);
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 1;
        gridBagConstraints1.gridy = 3;
        gridBagConstraints1.gridwidth = 3;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints1.insets = new java.awt.Insets(3, 3, 3, 3);
        gridBagConstraints1.weighty = 1.0;
        jP_C.add(jScrollPane1, gridBagConstraints1);
        
        jText_row.setText("1");
        jText_row.setToolTipText("Pivot Row");
        jText_row.setMinimumSize(new java.awt.Dimension(50, 24));
        jText_row.setPreferredSize(new java.awt.Dimension(70, 24));
        jText_row.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jText_rowKeyReleased(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 3;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(3, 3, 3, 3);
        jP_C.add(jText_row, gridBagConstraints1);
        
        jL_row.setText("Row:");
        jL_row.setMinimumSize(new java.awt.Dimension(30, 24));
        jL_row.setPreferredSize(new java.awt.Dimension(35, 24));
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 1;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(3, 3, 3, 3);
        jP_C.add(jL_row, gridBagConstraints1);
        
        add(jP_C, java.awt.BorderLayout.CENTER);
        
        jP_S.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jP_S.setBackground(new java.awt.Color(230, 230, 230));
        jP_S.setMinimumSize(new java.awt.Dimension(589, 60));
        jP_S.setPreferredSize(new java.awt.Dimension(536, 60));
        jL_outputCol.setText("Output Column:");
        jL_outputCol.setPreferredSize(new java.awt.Dimension(110, 24));
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(3, 3, 3, 3);
        jP_S.add(jL_outputCol, gridBagConstraints2);
        
        jCBox_outCol.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_outCol.setMinimumSize(new java.awt.Dimension(105, 24));
        jCBox_outCol.setPreferredSize(new java.awt.Dimension(105, 24));
        jCBox_outCol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_outColActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(3, 3, 3, 3);
        jP_S.add(jCBox_outCol, gridBagConstraints2);
        
        jPanel5.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jPanel5.setOpaque(false);
        jL_1.setText("Tag:");
        jL_1.setToolTipText("");
        jL_1.setMinimumSize(new java.awt.Dimension(45, 20));
        jL_1.setPreferredSize(new java.awt.Dimension(45, 20));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(3, 3, 3, 3);
        jPanel5.add(jL_1, gridBagConstraints3);
        
        jTextNameColumn.setMinimumSize(new java.awt.Dimension(200, 24));
        jTextNameColumn.setPreferredSize(new java.awt.Dimension(200, 24));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints3.insets = new java.awt.Insets(3, 3, 3, 3);
        gridBagConstraints3.weightx = 1.0;
        jPanel5.add(jTextNameColumn, gridBagConstraints3);
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.weightx = 1.0;
        jP_S.add(jPanel5, gridBagConstraints2);
        
        jB_columnResult.setBackground(new java.awt.Color(230, 230, 230));
        jB_columnResult.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_calculated.jpg")));
        jB_columnResult.setToolTipText("Calculate Column");
        jB_columnResult.setBorderPainted(false);
        jB_columnResult.setContentAreaFilled(false);
        jB_columnResult.setFocusPainted(false);
        jB_columnResult.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jB_columnResult.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_calculated_press.jpg")));
        jB_columnResult.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_calculated_over.jpg")));
        jB_columnResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_columnResultActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.insets = new java.awt.Insets(4, 4, 4, 4);
        jP_S.add(jB_columnResult, gridBagConstraints2);
        
        add(jP_S, java.awt.BorderLayout.SOUTH);
        
    }//GEN-END:initComponents

    private void jMenu_strActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_strActionPerformed
        insertOperator_Funcion("str(");
    }//GEN-LAST:event_jMenu_strActionPerformed

    private void jMenu_ifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_ifActionPerformed
        insertOperator_Funcion("if(");
    }//GEN-LAST:event_jMenu_ifActionPerformed

    private void jMenu_randomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_randomActionPerformed
        insertOperator_Funcion("rand(");
    }//GEN-LAST:event_jMenu_randomActionPerformed

    private void jMenu_absolute_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_absolute_valueActionPerformed
        insertOperator_Funcion("abs(");
    }//GEN-LAST:event_jMenu_absolute_valueActionPerformed

    private void jMenu_not_equalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_not_equalActionPerformed
        insertOperator_Funcion("!=");
    }//GEN-LAST:event_jMenu_not_equalActionPerformed

    private void jMenu_equalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_equalActionPerformed
        insertOperator_Funcion("==");
    }//GEN-LAST:event_jMenu_equalActionPerformed

    private void jMenu_booleanORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_booleanORActionPerformed
        insertOperator_Funcion("||");
    }//GEN-LAST:event_jMenu_booleanORActionPerformed

    private void jMenu_booleanAndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_booleanAndActionPerformed
        insertOperator_Funcion("&&");
    }//GEN-LAST:event_jMenu_booleanAndActionPerformed

    private void jMenu_grater_thanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_grater_thanActionPerformed
        insertOperator_Funcion(">");
    }//GEN-LAST:event_jMenu_grater_thanActionPerformed

    private void jMenu_less_thanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_less_thanActionPerformed
        insertOperator_Funcion("<");
    }//GEN-LAST:event_jMenu_less_thanActionPerformed

    private void jMenu_more_OR_equalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_more_OR_equalActionPerformed
        insertOperator_Funcion(">=");
    }//GEN-LAST:event_jMenu_more_OR_equalActionPerformed

    private void jMenu_less_OR_equalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_less_OR_equalActionPerformed
        insertOperator_Funcion("<=");
    }//GEN-LAST:event_jMenu_less_OR_equalActionPerformed

    private void jMenu_booleanNotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_booleanNotActionPerformed
        insertOperator_Funcion("!");
    }//GEN-LAST:event_jMenu_booleanNotActionPerformed

    private void jCBox_outColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBox_outColActionPerformed
    
        try
        {
            int indexCol = jCBox_outCol.getSelectedIndex();
            String namecol = (String)V_NameCounterColumns.elementAt(indexCol);           
            jTextNameColumn.setText(namecol);
            
            INDEX_COL_PIVOT_OUTPUT = ( (Integer)V_IndexValueVarColumns.elementAt(indexCol) ).intValue();
        }
        catch (Exception ex)
        {
        }
    }//GEN-LAST:event_jCBox_outColActionPerformed

    private void jText_rowKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jText_rowKeyReleased
        String text_index = jText_row.getText();

        try
        {
            int index = new Integer(text_index).intValue();

            if(text_index.startsWith(""+0))
            {
                jText_row.setText(""); 
            }
            else if( index > PIVOT_MAXROW )
            {
                jText_row.setText(""+PIVOT_MAXROW);
            }
            
            parseExpression();
            updateResult();
        }
        catch (Exception e)
        {           
        }
        
        
    }//GEN-LAST:event_jText_rowKeyReleased

    private void jB_columnResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_columnResultActionPerformed
        getResultRows_TH();
    }//GEN-LAST:event_jB_columnResultActionPerformed

    private void jMenu_squarerootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_squarerootActionPerformed
         insertOperator_Funcion("sqrt(");
    }//GEN-LAST:event_jMenu_squarerootActionPerformed

    private void jMenu_sumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_sumActionPerformed
         insertOperator_Funcion("sum(");
    }//GEN-LAST:event_jMenu_sumActionPerformed

    private void jMenu_log10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_log10ActionPerformed
        insertOperator_Funcion("log(");
    }//GEN-LAST:event_jMenu_log10ActionPerformed

    private void jMenu_exponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_exponActionPerformed
        insertOperator_Funcion("exp(");
    }//GEN-LAST:event_jMenu_exponActionPerformed

    private void jMenu_nat_logarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_nat_logarActionPerformed
        insertOperator_Funcion("In(");
    }//GEN-LAST:event_jMenu_nat_logarActionPerformed

    private void jMenu_powerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_powerActionPerformed
        insertOperator_Funcion("^");
    }//GEN-LAST:event_jMenu_powerActionPerformed

    private void jMenu_modulusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_modulusActionPerformed
        insertOperator_Funcion("%");
    }//GEN-LAST:event_jMenu_modulusActionPerformed

    private void jMenu_sottActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_sottActionPerformed
        insertOperator_Funcion("-");
    }//GEN-LAST:event_jMenu_sottActionPerformed

    private void jMenu_multiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_multiActionPerformed
        insertOperator_Funcion("*");
    }//GEN-LAST:event_jMenu_multiActionPerformed

    private void jMenu_divActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_divActionPerformed
       insertOperator_Funcion("/");
    }//GEN-LAST:event_jMenu_divActionPerformed

    private void jMenu_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_addActionPerformed
       insertOperator_Funcion("+");
    }//GEN-LAST:event_jMenu_addActionPerformed

    
    private void jButtonColActionPerformed(java.awt.event.ActionEvent evt) {
        Object target = evt.getSource();
        
        String strVarColumn_html = ((javax.swing.JButton)target).getText();
        String VarColumn = strVarColumn_html;
        
        if(strVarColumn_html.indexOf(InitTagHTMLColumn) != -1)
        {
            strVarColumn_html = strVarColumn_html.replaceAll(InitTagHTMLColumn,""); //Rimuovo Tag HTML

            java.util.StringTokenizer tokenVarName = new java.util.StringTokenizer(strVarColumn_html,"<");
            VarColumn = tokenVarName.nextToken();
        }        
        insertOperator_Funcion(VarColumn);
        exprFieldTextValueChanged();
        
    }
    
    
    public void insertOperator_Funcion(String oper_func)
    {
        int caret = exprField.getCaretPosition();
        String str_exp = exprField.getText();
        
        if(str_exp.length() == 0)            
            exprField.setText(oper_func);
        else
        {
            String str1 = str_exp.substring(0,caret).trim();
            str1+=oper_func+(str_exp.substring(caret,str_exp.length())).trim();
            exprField.setText(str1);
        }               
        exprField.requestFocus();
        exprField.setCaretPosition(caret+oper_func.length());
    }
    
    private void jB_AllFunctionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jB_AllFunctionMouseReleased
        
        jPopupMenu1.show((java.awt.Component)evt.getSource(),(evt.getX())+10,evt.getY());
        //System.out.println("getCaretPosition "+exprField.getCaretPosition());
        
    }//GEN-LAST:event_jB_AllFunctionMouseReleased

    private void exprFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_exprFieldKeyReleased
        exprFieldTextValueChanged();
    }//GEN-LAST:event_exprFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JMenu jMenu_operators;
    private javax.swing.JMenuItem jMenu_add;
    private javax.swing.JMenuItem jMenu_div;
    private javax.swing.JMenuItem jMenu_multi;
    private javax.swing.JMenuItem jMenu_sott;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenuItem jMenu_modulus;
    private javax.swing.JMenuItem jMenu_power;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JMenuItem jMenu_grater_than;
    private javax.swing.JMenuItem jMenu_less_than;
    private javax.swing.JMenuItem jMenu_less_OR_equal;
    private javax.swing.JMenuItem jMenu_more_OR_equal;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JMenuItem jMenu_booleanAnd;
    private javax.swing.JMenuItem jMenu_booleanNot;
    private javax.swing.JMenuItem jMenu_booleanOR;
    private javax.swing.JMenuItem jMenu_equal;
    private javax.swing.JMenuItem jMenu_not_equal;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenu jMenu_functios;
    private javax.swing.JMenuItem jMenu_absolute_value;
    private javax.swing.JMenuItem jMenu_nat_logar;
    private javax.swing.JMenuItem jMenu_expon;
    private javax.swing.JMenuItem jMenu_if;
    private javax.swing.JMenuItem jMenu_log10;
    private javax.swing.JMenuItem jMenu_random;
    private javax.swing.JMenuItem jMenu_sum;
    private javax.swing.JMenuItem jMenu_squareroot;
    private javax.swing.JMenuItem jMenu_str;
    private javax.swing.JPanel jP_N;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jP_Colbuttons;
    private javax.swing.JPanel jP_C;
    private javax.swing.JLabel jL_descExpre;
    private javax.swing.JPanel jp_exp;
    private javax.swing.JButton jB_AllFunction;
    private javax.swing.JTextField exprField;
    private javax.swing.JLabel jL_descResult;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JLabel jL_descErr;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea errorTextArea;
    private javax.swing.JTextField jText_row;
    private javax.swing.JLabel jL_row;
    private javax.swing.JPanel jP_S;
    private javax.swing.JLabel jL_outputCol;
    private javax.swing.JComboBox jCBox_outCol;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel jL_1;
    private javax.swing.JTextField jTextNameColumn;
    private javax.swing.JButton jB_columnResult;
    // End of variables declaration//GEN-END:variables
   
    /** Parser */
    private JEP myParser;

    private JF_MathExpression parent_Dialog = null;
    private MDM_JF_Pivot parent_f_Pivot = null;
    private int unit_bar = 1;
    
    /** Current xValue */
    //private double xValue;
    private Vector V_NameVarColumns;
    private Vector V_IndexValueVarColumns;
    private Vector V_NameCounterColumns;
    private int INDEX_COL_PIVOT_OUTPUT = 0;
    
    private int PIVOT_MAXROW = 0;
    private javax.swing.JTable TABLE = null;
    private java.awt.event.ActionListener eventButtons = null;
    
    private java.awt.Cursor Cur_default  = new Cursor(java.awt.Cursor.DEFAULT_CURSOR);
    private java.awt.Cursor Cur_wait     = new Cursor(java.awt.Cursor.WAIT_CURSOR);
    private java.awt.Cursor Cur_hand     = new Cursor(java.awt.Cursor.HAND_CURSOR);
    
    // ATTENZIONE DIPENDONO dalla dichiarazione in MDM_JF_Pivot.java    
    private String InitTagHTMLColumn = "<html><p align=center><b><font color=#FFFFFF>";
    private String TAG2 = "</font></p></b><p align=center><font face=\"Arial\" style=\"font-size:11pt\">";
    private String TAG3 = "</font></p></html>";    
    private String TAG_Name_COLUMN_MATH = "COMPUTED";
    // *** ATTENZIONE DIPENDONO dalla dichiarazione in MDM_JF_Pivot.java
    
    private Thread th        = null;
    private boolean TH_EXIT = false;
    private int OperationTH   = -1;
    
    private String Arr_INDEX_Column_ORIGIN[] = null;
    private String VAR_PIVOT_OUTPUT;
    
    private javax.swing.JProgressBar jPrBar;
    private javax.swing.JDialog dialog_wait;
    private javax.swing.JLabel jLabel_wait;
    
    private int VALUE_ROUND = 2;

}

 
