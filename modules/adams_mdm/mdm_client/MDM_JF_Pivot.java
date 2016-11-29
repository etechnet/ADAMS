/*
 * MDM_JF_Pivot.java
 *
 * Created on 21 aprile 2006, 11.27
 */

/**
 *
 * @author  luca
 */
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import java.util.StringTokenizer;
import javax.swing.JTable;
import com.braju.format.*;
import javax.swing.ScrollPaneConstants;
import net.etech.*;
import net.etech.ASP.*;
import net.etech.MDM.*;
import net.etech.loadconfig.*;

public class MDM_JF_Pivot extends javax.swing.JFrame implements Runnable 
{

    public MDM_JF_Pivot(javax.swing.JApplet JA,String[] nameKeyREL,String[] nameCounterSel,int[] index_CounterSel,String default_URL,int TotRowsPivot,int indexOrder,boolean Sort_Decreasing, String Report_FOCUS,int valuedecimal,int pageRows) 
    {
        this.JAppl = JA;
        this.DEFAULT_URL                = default_URL;
        this.indexCounterUserSel        = index_CounterSel;
        this.SERVER_TOT_ROWS_REQUEST    = TotRowsPivot;
        this.STR_REPORT_FOCUS   = Report_FOCUS;
        this.numKeyRel_NAME     = nameKeyREL;
        this.nameCounter        = nameCounterSel;
        this.VALUE_DECIMAL = valuedecimal;
        this.renderer = new PrecisionCellRenderer(VALUE_DECIMAL);
                
        this.ID_Order = indexOrder;
        this.decreasing = Sort_Decreasing;
        this.CustomPageRows = pageRows;
        
        V_HideColumn = new Vector();
        V_HideRow    = new Vector();
        
        initComponents();
        buttonGroup2.add(jMenu_jRb_selCell);
        buttonGroup2.add(jMenu_jRb_selColumn);
        buttonGroup2.add(jMenu_jRb_selRow);
        //Cursor
               
        jMenu_HideColumn.setCursor(Cur_hand);        
        jMenu_HideRow.setCursor(Cur_hand);
        jMenu_ShowColumn.setCursor(Cur_hand);  
        jMenu_ShowRow.setCursor(Cur_hand);
        jCBox_Pages.setCursor(Cur_hand);
        jR_TotalColumns.setCursor(Cur_hand);
        jMenuFocus.setCursor(Cur_hand);
        jMenuRestore.setCursor(Cur_hand);
        jB_ReloadPage.setCursor(Cur_hand);
        jCBox_align.setCursor(Cur_hand);
        jMenu_jRb_selCell.setCursor(Cur_hand);
        jMenu_jRb_selColumn.setCursor(Cur_hand);
        jMenu_jRb_selRow.setCursor(Cur_hand);
        jB_option.setCursor(Cur_hand);
        jB_Expression.setCursor(Cur_hand);
        jMen_font.setCursor(Cur_hand);
        jMenuBackground.setCursor(Cur_hand);
        jMenuForeground.setCursor(Cur_hand);
        jM_setColor.setCursor(Cur_hand);
        jM_drill_down.setCursor(Cur_hand);
        jB_print.setCursor(Cur_hand);
        jB_csv.setCursor(Cur_hand);
        jB_pdf.setCursor(Cur_hand);
        jB_excel.setCursor(Cur_hand);
        
        
        //font
        jCBox_align.setFont(staticLib.fontA9);
        jL_infoReport.setFont(staticLib.fontA10);
        jCBox_Pages.setFont(staticLib.fontA10);
        jL_tPage.setFont(staticLib.fontA10);
        jR_TotalColumns.setFont(staticLib.fontA10);
        jMenuFocus.setFont(staticLib.fontA10);
        jMenuRestore.setFont(staticLib.fontA10);
        jB_ReloadPage.setFont(staticLib.fontA10);
        jMenu_HideColumn.setFont(staticLib.fontA10);
        jMenu_HideRow.setFont(staticLib.fontA10);
        jMenu_ShowRow.setFont(staticLib.fontA10);
        jMenu_ShowColumn.setFont(staticLib.fontA10);
        jMenu_jRb_selCell.setFont(staticLib.fontA10);
        jMenu_jRb_selColumn.setFont(staticLib.fontA10);
        jMenu_jRb_selRow.setFont(staticLib.fontA10);
        jMen_font.setFont(staticLib.fontA10);
        jMenuBackground.setFont(staticLib.fontA10);
        jMenuForeground.setFont(staticLib.fontA10);
        jM_setColor.setFont(staticLib.fontA10);
        jM_drill_down.setFont(staticLib.fontA10);
        
        jB_print.setFont(staticLib.fontA10);
        jB_csv.setFont(staticLib.fontA10);
        jB_pdf.setFont(staticLib.fontA10);
        jB_excel.setFont(staticLib.fontA10);
             
        
        // Inizializzazione Colonne KEY Abilitazione per Server
        for(int i=0; i<ARR_KEY_ENABLED.length; i++)
        {
            ARR_KEY_ENABLED[i] = true;
            ARR_KEY_ENABLED_NAME[i] =  new String();
        }
        
        NUM_KEY_ENABLED = numKeyRel_NAME.length;
        for(int i=0; i<NUM_KEY_ENABLED; i++)
        {
            ARR_KEY_ENABLED_NAME[i] = new String(numKeyRel_NAME[i]).trim();
        }       
        ////////////////////////
        
        for(int i=0; i<align_label.length; i++)
            jCBox_align.addItem(align_label[i]);        
        
        jCBox_Pages.removeAllItems();
        
        jTable_Index = new javax.swing.JTable() {
            public java.awt.Component prepareRenderer(TableCellRenderer renderer,int rowIndex, int vColIndex) {               
                java.awt.Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                c.setBackground(new Color(0,128,192)); //0080c0
                c.setFont(fontDefaultPivot);
                return c;
            }
        };


        jTable_Index.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            
        },
        new String [] {" "}
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        //jTable_Index.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable_Index.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable_Index.getTableHeader().setToolTipText("Rows Index");
        jTable_Index.setPreferredScrollableViewportSize(new java.awt.Dimension(50, 100));
        jTable_Index.setRowHeight(16);
        jTable_Index.setRowMargin(2);
        jTable_Index.setAutoCreateColumnsFromModel(true);
        JTableHeader  header_index = jTable_Index.getTableHeader();
        //header_index.setFont(staticLib.fontA12);
        header_index.setReorderingAllowed(false);
        header_index.setPreferredSize(new java.awt.Dimension(52,35));
        
        TableColumn column_index =  jTable_Index.getColumnModel().getColumn(0);
        column_index.setMinWidth(52);
        column_index.setPreferredWidth(52);
        jTable_Index.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTable_Index.setForeground(java.awt.Color.white);
        
        
        jTable_Index.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_IndexMouseReleased(evt);
            }
        });        
        jScroll_IDPivot.setViewportView(jTable_Index);        
        indexJTableModel_general = (javax.swing.table.DefaultTableModel)jTable_Index.getModel();

        
        //  Costruzione JTABLE ///        
        numKeyRel = nameKeyREL.length;
        numCounters = nameCounterSel.length;
        NUM_COLUMN = (numKeyRel+numCounters)+1; //+1 colonna risultati per Math Expression
        
        //System.out.println("A NUM_COLUMN --> "+ NUM_COLUMN);
        
        Vector V_STR_HEAD = new Vector();
        int indexColor = 0;
        for(int w=0; w<numKeyRel; w++)
        {  
            V_STR_HEAD.addElement(new String(nameKeyREL[w]));
        }
        for(int z=0; z<numCounters; z++)
        {
            V_STR_HEAD.addElement(new String(nameCounterSel[z]));
        }
                
        V_STR_HEAD.addElement(new String("ITEM"));
        
        CellDimension = new int[NUM_COLUMN];
        INIT_columnNames = new String[NUM_COLUMN];
                
        Arr_INDEX_Column_ORIGIN = new String[NUM_COLUMN];
        
        for(int i=0; i<NUM_COLUMN; i++)
        {       
            String NameColumn = (String)(V_STR_HEAD.elementAt(i));
            
            String letter;
            if(i == NUM_COLUMN-1)
            {
                letter = TAG_Name_COLUMN_MATH;
                CellDimension[i] = 0;
            }
            else
            {
                if(i > alphabet.length-1)
                    letter = "AA"+i;
                else
                    letter = alphabet[i];
                CellDimension[i] = (int)(NameColumn.length()*2.5);
            }

            INIT_columnNames[i] = InitTagHTMLColumn + letter + TAG2+ NameColumn.trim()+ TAG3;
            Arr_INDEX_Column_ORIGIN[i] = letter;
        }
                
        myTableModel = new MyTableModel(INIT_columnNames);
        sorter = new TableSorter(myTableModel);            
        sorter.setTabNameKeyforColor(numKeyRel_NAME);
        
        //jTable_Pivot = new javax.swing.JTable(sorter);        
        jTable_Pivot = new javax.swing.JTable(sorter) {
            public TableCellRenderer getCellRenderer(int row, int column) {
                return renderer;
            }
        };
        
        JTableHeader header_pivot = jTable_Pivot.getTableHeader();
       
        header_pivot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                header_pivotMouseReleased(evt);
            }
        });
           
        header_pivot.setFont(staticLib.fontA10);
        header_pivot.setReorderingAllowed(false);     
        
        sorter.setTableHeader(header_pivot);     
              
        jTable_Pivot.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_Pivot.setFont(fontDefaultPivot);
        jTable_Pivot.setRowHeight(16);
        //jTable_Pivot.setSelectionBackground(java.awt.Color.green);
        //jTable_Pivot.setPreferredScrollableViewportSize(new java.awt.Dimension(1000, 100));
        jTable_Pivot.setRowMargin(2);
        jTable_Pivot.setAutoCreateColumnsFromModel(false);
        jTable_Pivot.setBackground(colorBackPivot);
        jTable_Pivot.setForeground(colorForePivot);
        
        jScroll_Pivot.setViewportView(jTable_Pivot);
        
        jTable_Pivot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_PivotMouseReleased(evt);
            }
        });
        
        // **************************** //
                
        jScroll_Pivot.getVerticalScrollBar().addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                verticalScrollBarAdjustmentListener(evt);
            }
        });
        
        SetTable(jTable_Pivot);                
        jTable_Pivot.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //jTable_Index.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jTable_Pivot.setAutoResizeMode(2);
        jCBox_align.setSelectedIndex(2);
        
        setRowSelection();
        jMenu_HideRow.setEnabled(false);

        this.getContentPane().setBackground(new java.awt.Color(230, 230, 230));
        setCenteredFrame(1024,768);
        
        // ---         
        //jPopMenu.remove(jM_drill_down);
        //jM_drill_down.setVisible(false);
        // --
        
        //show();
	this.setVisible(true);
        
        Init_Read_ADD_Data();
    }

    public void setNameReportKey(String NameReportKey)
    {
        this.nameReportKey = NameReportKey;
    }
    
    public void setConfiguration(getConfigFiltro Configuration)
    {
        this.configuration = Configuration;
        localAnalisi = new Analisi(configuration.get_TipoAnalisi());
    }
    
    private void totalReloadPivot()
    {        
        ///////////////////////// libero Memoria
        jMenu_ShowColumn.setEnabled(false);
        jMenu_ShowRow.setEnabled(false);

        jMenuRestore.setEnabled(false);
        jR_TotalColumns.setSelected(false);
        
        jP_totalTable.removeAll();
        jP_totalTable.validate();
        jP_totalTable.repaint();

        freeMemory();
        //////////////////////////
        
        SHOW_PAGE = 0;
        jCBox_Pages.removeAllItems();       
                 
        PIVOTDATANODE[] pivotData_init = staticLib.CORBA.getPivotData(ARR_KEY_ENABLED,DEFAULT_URL,0,-1,indexCounterUserSel,this.ID_Order,decreasing,STR_REPORT_FOCUS,ELEMENT_SORT,ALL_ABSOLUTE_VALUES);
    
        SERVER_TOT_ROWS_REQUEST = staticLib.CORBA.getTotalRowsPivot();
        //System.out.println("SERVER_TOT_ROWS_REQUEST "+SERVER_TOT_ROWS_REQUEST);

        /*for(int i=0; i<numKeyRel_NAME.length; i++)
        {
            System.out.println(" PRIMA numKeyRel_NAME["+i+"] = "+new String(numKeyRel_NAME[i]).trim());
        }*/

                       
        Vector V_appoNameKeyREL = new Vector();
        for(int y=0; y<NUM_KEY_ENABLED; y++)
        {
            if(ARR_KEY_ENABLED[y] == true)
            {
               V_appoNameKeyREL.addElement(new String(ARR_KEY_ENABLED_NAME[y]));
            }
            //System.out.println("ARR_KEY_ENABLED -->"+ARR_KEY_ENABLED[y]);
        }
            
        numKeyRel = V_appoNameKeyREL.size();
        numKeyRel_NAME = new String[numKeyRel];
        
        for(int i=0; i<numKeyRel; i++)
            numKeyRel_NAME[i] = (String)V_appoNameKeyREL.elementAt(i);

        /*for(int i=0; i<numKeyRel_NAME.length; i++)
        {
            System.out.println(" DOPO numKeyRel_NAME["+i+"] = "+new String(numKeyRel_NAME[i]).trim());
        }*/
        
        NUM_COLUMN = (numKeyRel+numCounters)+1; //+1 colonna risultati per Math Expression
        
        //System.out.println("B NUM_COLUMN --> "+ NUM_COLUMN);

        Vector V_STR_HEAD = new Vector();
        int indexColor = 0;
        for(int w=0; w<numKeyRel; w++)
        {  
            V_STR_HEAD.addElement(new String(numKeyRel_NAME[w]));
        }
        for(int z=0; z<numCounters; z++)
        {
            V_STR_HEAD.addElement(new String(nameCounter[z]));
        }

        V_STR_HEAD.addElement(new String("ITEM"));

        CellDimension = new int[NUM_COLUMN];
        INIT_columnNames = new String[NUM_COLUMN];

        Arr_INDEX_Column_ORIGIN = new String[NUM_COLUMN];

        for(int i=0; i<NUM_COLUMN; i++)
        {       
            String NameColumn = (String)(V_STR_HEAD.elementAt(i));

            String letter;
            if(i == NUM_COLUMN-1)
            {
                letter = TAG_Name_COLUMN_MATH;
                CellDimension[i] = 0;
            }
            else
            {
                if(i > alphabet.length-1)
                    letter = "AA"+i;
                else
                    letter = alphabet[i];
                CellDimension[i] = (int)(NameColumn.length()*2.5);
            }

            INIT_columnNames[i] = InitTagHTMLColumn + letter + TAG2+ NameColumn.trim()+ TAG3;
            Arr_INDEX_Column_ORIGIN[i] = letter;
        }

        myTableModel = new MyTableModel(INIT_columnNames);
        sorter = new TableSorter(myTableModel);            
        sorter.setTabNameKeyforColor(numKeyRel_NAME);

        
        //System.out.println(" ---- REMOVE jTable_Pivot ---- ");
        jScroll_Pivot.remove(jTable_Pivot);
        
        // *****************
        jTable_Pivot = new javax.swing.JTable(sorter) {
            public TableCellRenderer getCellRenderer(int row, int column) {
                return renderer;
            }
        };
        
        JTableHeader header_pivot = jTable_Pivot.getTableHeader();
       
        header_pivot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                header_pivotMouseReleased(evt);
            }
        });
           
        header_pivot.setFont(staticLib.fontA10);
        header_pivot.setReorderingAllowed(false);     
        
        sorter.setTableHeader(header_pivot);     
              
        jTable_Pivot.getTableHeader().setToolTipText("Click to specify sorting; Control-Click to specify secondary sorting");
        jTable_Pivot.setFont(fontDefaultPivot);
        jTable_Pivot.setRowHeight(16);
        //jTable_Pivot.setSelectionBackground(java.awt.Color.green);
        //jTable_Pivot.setPreferredScrollableViewportSize(new java.awt.Dimension(1000, 100));
        jTable_Pivot.setRowMargin(2);
        jTable_Pivot.setAutoCreateColumnsFromModel(false);
        jTable_Pivot.setBackground(colorBackPivot);
        jTable_Pivot.setForeground(colorForePivot);
        
        jScroll_Pivot.setViewportView(jTable_Pivot);
        
        jTable_Pivot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable_PivotMouseReleased(evt);
            }
        });
        
        // **************************** //
                
        jScroll_Pivot.getVerticalScrollBar().addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                verticalScrollBarAdjustmentListener(evt);
            }
        });
        // *****************
        
        SetTable(jTable_Pivot);                
        jTable_Pivot.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        Init_Read_ADD_Data();    
        setColumnSelection();
        
    }
    
     
    private void SetTable(javax.swing.JTable jtable)
    {
        this.setCursor(Cur_wait);
                  
        JTableHeader header = jtable.getTableHeader();
        header.setFont(staticLib.fontA10);
        header.setReorderingAllowed(true);
        header.setPreferredSize(new java.awt.Dimension(100,35));
        
        TableColumn column = null;
        int columnCount = jtable.getColumnCount();
        for(int i=0; i<columnCount; i++)
        {
            column = jtable.getColumnModel().getColumn(i);
            column.setMinWidth(minCellDimension);            
            column.setPreferredWidth(CellDimension[i]);
            column.setWidth(CellDimension[i]);
            column.setMaxWidth(2000);
            column.setResizable(true);
                        
            
            if(CellDimension[i] == 0)
            {
                //--------------------- //Column "Math Expression" hide
                column.setMinWidth(0);
                column.setMaxWidth(0);
                column.setWidth(0);
                column.setPreferredWidth(0);
                column.setResizable(false);
                //---------------------
            }
        }        
        //sorter.setSortingStatus(0,1); 
        this.setCursor(Cur_default);
    }
    
   
    private int init_CalculateTable()
    {
        //System.out.println("Inizio init_CalculateTable");
        
        this.setCursor(Cur_wait);
        
        jBarPage.setValue(bar_Value);
        bar_Value+=5;
        
        runGC();

        long maxmemory1  = s_runtime.maxMemory();
        long usedmemory1  = usedMemory();

        long freemem_calc = (maxmemory1-usedmemory1);                 

        jBarPage.setValue(bar_Value);
        bar_Value+=8;        
        
        int righe_test = 2500;
        int byteRow = 0;
        
        if(SERVER_TOT_ROWS_REQUEST == 0)
        {
            staticLib.optionPanel("NO DATA FOUND PIVOT...", "DATA NOT FOUND",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            jBarPage.setValue(0);            
            this.setCursor(Cur_default);
            return -1;
        }
        
        if(SERVER_TOT_ROWS_REQUEST > righe_test)
            byteRow = ( (sizeOfJTable(righe_test,NUM_COLUMN+1,numKeyRel))/righe_test);  //colonna INDEX TAB (+1)        

        if(byteRow <= 0)
        {
            //System.out.println("byteRow");
            byteRow = 300;
        }
       
        double perct_mem_use    = 0.60;
        int MAX_ROWS_TABLE      = (int)((freemem_calc*perct_mem_use)/byteRow);
        
        if(CustomPageRows > 0)
        {
            if(CustomPageRows <= MAX_ROWS_TABLE);
                MAX_ROWS_TABLE = CustomPageRows;
        }

        /*System.out.println("--------------------------------------------------------");
        System.out.println("Memoria Totale          ------> "+s_runtime.maxMemory());
        System.out.println("Memoria disponibile     ------> "+freemem_calc);
        System.out.println("Imposto % di utilizzo   ------> "+(double)(perct_mem_use*100)+"%");
        System.out.println("Memoria effettiva in %  ------> "+(long)(freemem_calc*perct_mem_use));
        System.out.println("Riga stimata in byte    ------> "+byteRow+" byte");
        System.out.println("Numero Colonne          **** ------> "+(NUM_COLUMN));
        System.out.println("Numero Righe Stimate    **** ------> "+MAX_ROWS_TABLE);        
        System.out.println("--------------------------------------------------------");*/

        MAX_PAGES = 1;
            
        if(MAX_ROWS_TABLE > SERVER_TOT_ROWS_REQUEST)
            MAX_ROWS_TABLE = SERVER_TOT_ROWS_REQUEST;
        else
        {     
            MAX_PAGES = (int)(SERVER_TOT_ROWS_REQUEST/MAX_ROWS_TABLE);

            if( (SERVER_TOT_ROWS_REQUEST % MAX_ROWS_TABLE) != 0 )
                MAX_PAGES++; 
        }
        
        jBarPage.setValue(bar_Value);
        bar_Value+=10;
        
        PAGES_START_END = new int [MAX_PAGES][3];
        
        jL_infoReport.setText("Report Rows: "+SERVER_TOT_ROWS_REQUEST);
        for (int p=0; p<MAX_PAGES; p++)
            jCBox_Pages.addItem("Page "+(p+1)+"/"+MAX_PAGES);        
        jCBox_Pages.setSelectedIndex(0);

        int request_for_page = 3;
        if(MAX_ROWS_TABLE < 75000)
        {
            request_for_page = 2;
            if(MAX_ROWS_TABLE < 50000)
                request_for_page = 1;
        }
        
        /*System.out.println();
        System.out.println("------ ------ SERVER_TOT_ROWS_REQUEST **** ------> "+SERVER_TOT_ROWS_REQUEST);
        System.out.println("------ ------ MAX_ROWS_TABLE   **** ------> "+MAX_ROWS_TABLE);
        System.out.println("------ ------ MAX_PAGES **** ------> "+MAX_PAGES);
        System.out.println();*/   
        
        int start = 0;
        int gap = MAX_ROWS_TABLE;
        int end = gap;
        
        PAGES_START_END[0][START_ROW]   = start;
        PAGES_START_END[0][END_ROW]     = end;
        PAGES_START_END[0][REQUEST_PAG] = request_for_page;
        for(int i=1; i<PAGES_START_END.length; i++ )
        {                       
            start = end;
            if(PAGES_START_END.length-1 == i)
            {
                end = SERVER_TOT_ROWS_REQUEST;
                gap = end - start;
            }
            else
                end = end+gap;
            
            request_for_page = 3;
            if(gap < 75000)
            {
                request_for_page = 2;
                if(gap < 50000)
                    request_for_page = 1;
            }
            PAGES_START_END[i][START_ROW]   = start;
            PAGES_START_END[i][END_ROW]     = end;
            PAGES_START_END[i][REQUEST_PAG] = request_for_page;
        }
        
        //PRINT Struttura Pagine e righe 
        /*for(int x=0; x<PAGES_START_END.length; x++)
        {
            System.out.println();
            System.out.print("--- > Page "+(x+1));
            for(int z=0; z<PAGES_START_END[x].length; z++)
                System.out.print(" "+PAGES_START_END[x][z]+" ");
        }
        System.out.println();
        System.out.println("Fine init_CalculateTable");*/
        
        jBarPage.setValue(bar_Value);
        bar_Value+=5;
        
        this.setCursor(Cur_default);
        return 0;
    }
    
    private void AddRowJTabel_general(javax.swing.JTable jtable,int Page)
    {        
        this.setCursor(Cur_wait);
        
        jB_search.setEnabled(false);
        jR_TotalColumns.setEnabled(false);
        jB_option.setEnabled(false);
        jB_Expression.setEnabled(false);        
        jMenu_jRb_selColumn.setEnabled(false);        
        jMenu_jRb_selRow.setEnabled(false);     
        jMenu_jRb_selCell.setEnabled(false);
        jMenu_HideRow.setEnabled(false);
        jMenu_HideColumn.setEnabled(false);
        jB_ReloadPage.setEnabled(false);
        jB_print.setEnabled(false);
        jB_csv.setEnabled(false);
        jB_pdf.setEnabled(false);
        jB_excel.setEnabled(false);
        jMen_font.setEnabled(false);
        jMenuBackground.setEnabled(false);
        jMenuForeground.setEnabled(false);
        jM_setColor.setEnabled(false);
        
        boolean restoreEnabled_C = jMenu_ShowColumn.isEnabled();
        boolean restoreEnabled_R = jMenu_ShowRow.isEnabled();
        jMenu_ShowColumn.setEnabled(false);
        jMenu_ShowRow.setEnabled(false);

        this.validate();
        this.repaint();
        
        boolean error = false;
        int i_index = 0;
        
        Object[][] dataValues = new Object[1][NUM_COLUMN];
        for(int c=0;c<NUM_COLUMN;c++)
            dataValues[0][c] = "";

        indexJTableModel_general.setNumRows(0);
        jTable_Index.repaint();
        myTableModel.setDataValues(dataValues);
        sorter.setTableModel(myTableModel);
        jScroll_Pivot.setViewportView(jTable_Pivot);
        jScroll_IDPivot.setViewportView(jTable_Index);
          
        int calc_potenza = (int)Math.pow(10,VALUE_DECIMAL);
        //System.out.println("calc_potenza "+calc_potenza);
        
        runGC();
        
        jBarPage.setValue(bar_Value);
        bar_Value+=10;
        
        /*if(OLD_PAGE >= 0)
        {
            
            if(OLD_PAGE < Page)
                SKIP_ROWS = ((PAGES_START_END[Page][0])-(PAGES_START_END[OLD_PAGE][1])); //SKIP_ROWS == ((StartRow new Page) - (EndRow old Page))
            else
                SKIP_ROWS = ((PAGES_START_END[OLD_PAGE][0])-(PAGES_START_END[Page][0]))*-1; //SKIP_ROWS == ((StartRow old Page) - (StartRow new Page))
                
            //System.out.println("SKIP_ROWS = "+SKIP_ROWS);
        }*/
        
        try
        {
            //jtable.setCursor(Cur_wait);
     
            /*System.out.println("--------- AddRow Page -->"+
                                " START ="+PAGES_START_END[Page][START_ROW]+" "+
                                " END "+PAGES_START_END[Page][END_ROW]+" "+
                                " TOT_REQUEST "+PAGES_START_END[Page][REQUEST_PAG]);*/
                      
            int RowStart_request = PAGES_START_END[Page][START_ROW];
            int RowEnd_request = ((PAGES_START_END[Page][END_ROW]-RowStart_request)/PAGES_START_END[Page][REQUEST_PAG])+RowStart_request;
            int Row_gap_request = RowEnd_request-RowStart_request;
            
            int TOT_ROWS_TABLE = PAGES_START_END[Page][END_ROW]-RowStart_request;
            ROWS_PAGE = TOT_ROWS_TABLE;
            
            dataValues = new Object[TOT_ROWS_TABLE][NUM_COLUMN];
            for(int c=0;c<NUM_COLUMN;c++)
                dataValues[0][c] = "";
            
            myTableModel.setDataValues(dataValues);
            sorter.setTableModel(myTableModel);
            
            
            int rowsADD = 0;
            for( int i_req = 1; i_req <= PAGES_START_END[Page][REQUEST_PAG]; i_req++)
            {   
                jBarPage.setValue(bar_Value);
                bar_Value+=7;
                
                PIVOTDATANODE[] Local_pivotData = null;
                
                if(TH_EXIT)//in caso di chiusura Frame
                {
                    //System.out.println("TH_EXIT");
                    dataValues = null;
                    Local_pivotData = null;
                    this.setCursor(Cur_default);
                    return;
                }
                
                //System.out.println("PAGE "+(Page+1)+" -- REQUEST "+i_req+" ----->RowStart_request="+RowStart_request+" --->RowEnd_request="+RowEnd_request);  
                //System.out.println("SKIP_ROWS ---------------> "+SKIP_ROWS);
                Local_pivotData = staticLib.CORBA.getPivotData(ARR_KEY_ENABLED,DEFAULT_URL,RowStart_request,RowEnd_request,indexCounterUserSel,this.ID_Order,this.decreasing,STR_REPORT_FOCUS,ELEMENT_SORT,ALL_ABSOLUTE_VALUES);                           
                //SKIP_ROWS = 0;
                
                if(TH_EXIT)//in caso di chiusura Frame
                {
                    //System.out.println("TH_EXIT");
                    dataValues = null;
                    Local_pivotData = null;
                    this.setCursor(Cur_default);
                    return;
                }
                
                if(i_req == PAGES_START_END[Page][REQUEST_PAG]-1)
                    RowEnd_request = PAGES_START_END[Page][END_ROW];
                else
                    RowEnd_request = RowEnd_request + Row_gap_request;
                
                
                int SIZE_ROWS = 0;
                if(Local_pivotData != null)
                {
                    SIZE_ROWS = Local_pivotData.length;
                    //System.out.println("SIZE_ROWS "+SIZE_ROWS);
                }

                jBarPage.setValue(bar_Value);
                bar_Value+=12;
                
                if(SIZE_ROWS == 0)
                {
                    staticLib.optionPanel("Not Data Found.", "Information",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    this.setCursor(Cur_default);
                    //jtable.setCursor(Cur_default);
                    jBarPage.setForeground(java.awt.Color.red);
                    return;
                }
                //else
                    //System.out.println("RIGHE INVIATE dal Server -------->"+SIZE_ROWS);
                
                for(int i=0; i<SIZE_ROWS; i++)
                {      
                    int count_COL = 0;                
                    for(int x=0; x<Local_pivotData[i].pkeySeq.length; x++)
                    {      
                        //System.out.println("pkeySeq --> "+new String(Local_pivotData[i].pkeySeq[x]));                        
                        dataValues[rowsADD][count_COL] = new String(Local_pivotData[i].pkeySeq[x]).trim();

                        //libero Memoria
                        Local_pivotData[i].pkeySeq[x] = null;
                        count_COL++;
                    }

                    //System.out.println("Numero Riga index--> "+i);
                    //System.out.println("Local_pivotData[i].ParamSeq.length --> "+Local_pivotData[i].ParamSeq.length);

                    for(int d=0; d<Local_pivotData[i].ParamSeq.length; d++)
                    {
                        //System.out.println("TIPO DATO "+Local_pivotData[i].ParamSeq[d].Type);
                        if(Local_pivotData[i].ParamSeq[d].Type == 0) //int senza segno
                        {
                            //System.out.println("param Valore Intero --> "+Local_pivotData[i].ParamSeq[d].Value.UnsignedInteger());
                            
                            
                            dataValues[rowsADD][count_COL] = new Long(Local_pivotData[i].ParamSeq[d].Value.UnsignedInteger());                             
                        }
                        else //Local_pivotData[i].ParamSeq[d].Type == 0 float
                        {
                            //System.out.println("param Valore Decimale --> "+Local_pivotData[i].ParamSeq[d].Value.Decimal());
                            Double val = new Double(Local_pivotData[i].ParamSeq[d].Value.Decimal());
                            //System.out.println("Double val "+val);
                            //System.out.println("Double val round "+new Double( (double)(Math.round(val.doubleValue()*100))/100 ));
                            
                            dataValues[rowsADD][count_COL] = new Double( (double)(Math.round(val.doubleValue()*calc_potenza))/calc_potenza );
                        }
                        
                        Local_pivotData[i].ParamSeq[d].Value = null;                                                
                        count_COL++;            
                    }
                    //libero Memoria
                    Local_pivotData[i] = null;                    
                    RowStart_request++;
                    rowsADD++;
                    
                    /// tabella Indice
                    Vector V_index = new Vector();
                    V_index.addElement(new Integer(RowStart_request));
                    indexJTableModel_general.addRow(V_index);                                        
                    ///////////////                    
                }

                //libero Memoria
                Local_pivotData = null;
                
                ////////////////////////                
                jScroll_Pivot.setViewportView(jTable_Pivot);
                jScroll_IDPivot.setViewportView(jTable_Index);
                ///////////////////////
                
                runGC(); 
                /*long maxmemory_1  = s_runtime.maxMemory();
                long usedmemory_1  = usedMemory();
                long freememory_1 = (maxmemory_1-usedmemory_1);        
                System.out.println("Memoria Disponibile request ------> "+freememory_1);*/
            }
            ////////////////////////////////////////           
        }
        catch (java.lang.OutOfMemoryError e)
        {
            System.out.println("!!!!!! Intercetto !!!!!! ++ CRASH ++ Inserite-->"+i_index);
            System.out.println(e.toString());
            e.printStackTrace();
            jBarPage.setForeground(java.awt.Color.red);
            error = true;
        }
        
        jBarPage.setValue(bar_Value);
        bar_Value+=10;
        try
        {                 
            jScroll_Pivot.setViewportView(jTable_Pivot);
            jScroll_IDPivot.setViewportView(jTable_Index);            
        }
        catch (java.lang.NullPointerException e)
        {
            //System.out.println(" ctrl updateUI 1");
        }
        
                
        jL_infoReport.setText("Report Rows: "+SERVER_TOT_ROWS_REQUEST);
        jL_tPage.setText("Page Rows: "+ROWS_PAGE);
        
        this.setCursor(Cur_default);
        //jtable.setCursor(Cur_default);
        
        runGC();
        /*long maxmemory  = s_runtime.maxMemory();
        long usedmemory  = usedMemory();
        long freememory = (maxmemory-usedmemory);        
        System.out.println("Memoria Disponibile FINALE ------> "+freememory);*/
        
        // Memorizzo Pagina Visualizzata
        OLD_PAGE = Page;
        
        if(error == false)
        {
            jBarPage.setValue(100);
            jBarPage.setForeground(java.awt.Color.green.darker());
            jB_search.setEnabled(true);
            jR_TotalColumns.setEnabled(true);
            jB_option.setEnabled(true);
            jB_print.setEnabled(true);
            jB_csv.setEnabled(true);
            jB_pdf.setEnabled(true);
            jB_excel.setEnabled(true);
            jB_Expression.setEnabled(true);
            jMenu_jRb_selColumn.setEnabled(true);            
            jMenu_jRb_selRow.setEnabled(true);
            jMenu_jRb_selCell.setEnabled(true);
            jB_ReloadPage.setEnabled(true);
            jMen_font.setEnabled(true);
            jMenuBackground.setEnabled(true);
            jMenuForeground.setEnabled(true);
            jM_setColor.setEnabled(true);
                        
            if(jMenu_jRb_selColumn.isSelected())
                jMenu_HideColumn.setEnabled(true);            
            else if (jMenu_jRb_selRow.isSelected())
                jMenu_HideRow.setEnabled(true);
                
            jMenu_ShowColumn.setEnabled(restoreEnabled_C);
            jMenu_ShowRow.setEnabled(restoreEnabled_R);
        
        }
    }
       
    private boolean Init_NameColumn()
    {
        V_HideColumn.removeAllElements();
        V_HideRow.removeAllElements();
        jMenu_ShowColumn.setEnabled(false);
        jMenu_ShowRow.setEnabled(false);
        
        for(int i=0; i<jTable_Pivot.getColumnCount(); i++)
        {
            String NameColumn_Table = jTable_Pivot.getColumnName(i).trim();
            //System.out.println("NameColumn_Table "+NameColumn_Table);
            for(int x=0; x<INIT_columnNames.length; x++)
            {
                String start_name = InitTagHTMLColumn+Arr_INDEX_Column_ORIGIN[x]+TAG2;
                if(NameColumn_Table.indexOf(start_name)!= -1)
                {
                    TableColumn column = jTable_Pivot.getColumnModel().getColumn(i);                    
                    column.setHeaderValue(INIT_columnNames[x]);

                    if(NameColumn_Table.indexOf(InitTagHTMLColumn+TAG_Name_COLUMN_MATH+TAG2) != -1)
                    {
                        column.setMinWidth(0);            
                        column.setPreferredWidth(0);
                        column.setWidth(0);
                        column.setMaxWidth(0);
                        column.setResizable(false);
                    }
                    else
                    {
                        int width = (int)(NameColumn_Table.length()-50);

                        column.setMinWidth(minCellDimension);            
                        column.setPreferredWidth(width);
                        column.setWidth(width);
                        column.setMaxWidth(2000);
                        column.setResizable(true);
                    }
                }
            }
        }
        jTable_Pivot.getTableHeader().resizeAndRepaint();
        //System.out.println("Fine Init_NameColumn");
        return true;
    }
    
    public void isReportOptions(boolean f , int elementToSort)
    {
        this.ALL_ABSOLUTE_VALUES = f;
        this.ELEMENT_SORT = elementToSort;
    }
    
    public javax.swing.JTable getTable_Pivot()
    {
        return this.jTable_Pivot;
    }
    
    public Object[][] getTableModelDataValues()
    {
        return myTableModel.getDataValues();
    }
    
    public String[] getIndexColumnOrigin()
    {
        return Arr_INDEX_Column_ORIGIN;
    }
    
    public int getValueRound()
    {
        return this.VALUE_DECIMAL;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPopMenu = new javax.swing.JPopupMenu();
        jMenu_jRb_selColumn = new javax.swing.JRadioButtonMenuItem();
        jMenu_jRb_selRow = new javax.swing.JRadioButtonMenuItem();
        jMenu_jRb_selCell = new javax.swing.JRadioButtonMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuFocus = new javax.swing.JMenuItem();
        jMenuRestore = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenu_HideColumn = new javax.swing.JMenuItem();
        jMenu_ShowColumn = new javax.swing.JMenuItem();
        jMenu_HideRow = new javax.swing.JMenuItem();
        jMenu_ShowRow = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMen_font = new javax.swing.JMenuItem();
        jM_setColor = new javax.swing.JMenu();
        jMenuForeground = new javax.swing.JMenuItem();
        jMenuBackground = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jM_drill_down = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jP_TOT = new javax.swing.JPanel();
        jP_Control = new javax.swing.JPanel();
        jP_Align = new javax.swing.JPanel();
        jCBox_align = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jB_Expression = new javax.swing.JButton();
        jB_search = new javax.swing.JButton();
        jR_TotalColumns = new javax.swing.JRadioButton();
        jB_option = new javax.swing.JButton();
        jP_page = new javax.swing.JPanel();
        jCBox_Pages = new javax.swing.JComboBox();
        jL_infoReport = new javax.swing.JLabel();
        jBarPage = new javax.swing.JProgressBar();
        jL_tPage = new javax.swing.JLabel();
        jB_ReloadPage = new javax.swing.JButton();
        jP_GlobalPivot = new javax.swing.JPanel();
        jScroll_Pivot = new javax.swing.JScrollPane();
        jScroll_IDPivot = new javax.swing.JScrollPane();
        jP_totalTable = new javax.swing.JPanel();
        jP_north = new javax.swing.JPanel();
        jL_title = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jB_print = new javax.swing.JButton();
        jB_csv = new javax.swing.JButton();
        jB_pdf = new javax.swing.JButton();
        jB_excel = new javax.swing.JButton();
        
        jPopMenu.setBorder(new javax.swing.border.TitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), " Advance Options"));
        jMenu_jRb_selColumn.setBackground(java.awt.Color.blue);
        jMenu_jRb_selColumn.setForeground(java.awt.Color.white);
        jMenu_jRb_selColumn.setText("Mode: column selection");
        jMenu_jRb_selColumn.setBorderPainted(false);
        jMenu_jRb_selColumn.setContentAreaFilled(false);
        jMenu_jRb_selColumn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenu_jRb_selModeMouseReleased(evt);
            }
        });
        
        jPopMenu.add(jMenu_jRb_selColumn);
        jMenu_jRb_selRow.setSelected(true);
        jMenu_jRb_selRow.setText("Mode: row selection");
        jMenu_jRb_selRow.setBorderPainted(false);
        jMenu_jRb_selRow.setContentAreaFilled(false);
        jMenu_jRb_selRow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenu_jRb_selModeMouseReleased(evt);
            }
        });
        
        jPopMenu.add(jMenu_jRb_selRow);
        jMenu_jRb_selCell.setText("Mode: cell selection");
        jMenu_jRb_selCell.setBorderPainted(false);
        jMenu_jRb_selCell.setContentAreaFilled(false);
        jMenu_jRb_selCell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenu_jRb_selModeMouseReleased(evt);
            }
        });
        
        jPopMenu.add(jMenu_jRb_selCell);
        jPopMenu.add(jSeparator1);
        jMenuFocus.setText("Focus ON");
        jMenuFocus.setToolTipText("Focus on Selections");
        jMenuFocus.setContentAreaFilled(false);
        jMenuFocus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/focus.png")));
        jMenuFocus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFocusActionPerformed(evt);
            }
        });
        
        jPopMenu.add(jMenuFocus);
        jMenuRestore.setText("Focus OFF");
        jMenuRestore.setToolTipText("Restore Data");
        jMenuRestore.setContentAreaFilled(false);
        jMenuRestore.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/focus_off.png")));
        jMenuRestore.setEnabled(false);
        jMenuRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuRestoreActionPerformed(evt);
            }
        });
        
        jPopMenu.add(jMenuRestore);
        jPopMenu.add(jSeparator2);
        jMenu_HideColumn.setBackground(new java.awt.Color(230, 230, 230));
        jMenu_HideColumn.setText("Hide column");
        jMenu_HideColumn.setToolTipText("Hide column");
        jMenu_HideColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_HideColumnActionPerformed(evt);
            }
        });
        
        jPopMenu.add(jMenu_HideColumn);
        jMenu_ShowColumn.setText("Show hidden column");
        jMenu_ShowColumn.setToolTipText("Show hidden column");
        jMenu_ShowColumn.setEnabled(false);
        jMenu_ShowColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_ShowColumnActionPerformed(evt);
            }
        });
        
        jPopMenu.add(jMenu_ShowColumn);
        jMenu_HideRow.setText("Hide row");
        jMenu_HideRow.setToolTipText("Hide row");
        jMenu_HideRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_HideRowActionPerformed(evt);
            }
        });
        
        jPopMenu.add(jMenu_HideRow);
        jMenu_ShowRow.setText("Show hidden row");
        jMenu_ShowRow.setToolTipText("Show hidden row");
        jMenu_ShowRow.setEnabled(false);
        jMenu_ShowRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_ShowRowActionPerformed(evt);
            }
        });
        
        jPopMenu.add(jMenu_ShowRow);
        jPopMenu.add(jSeparator3);
        jMen_font.setText("Set Font");
        jMen_font.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMen_fontActionPerformed(evt);
            }
        });
        
        jPopMenu.add(jMen_font);
        jM_setColor.setText("Set Color");
        jMenuForeground.setText("Set Foreground");
        jMenuForeground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuForegroundActionPerformed(evt);
            }
        });
        
        jM_setColor.add(jMenuForeground);
        jMenuBackground.setText("Set background");
        jMenuBackground.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBackgroundActionPerformed(evt);
            }
        });
        
        jM_setColor.add(jMenuBackground);
        jPopMenu.add(jM_setColor);
        jPopMenu.add(jSeparator4);
        jM_drill_down.setText("Drill-down");
        jM_drill_down.setToolTipText("Drill-down (additional information)");
        jM_drill_down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_drill_downActionPerformed(evt);
            }
        });
        
        jPopMenu.add(jM_drill_down);
        
        setTitle("N.T.M. Pivoting");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
            public void windowIconified(java.awt.event.WindowEvent evt) {
                formWindowIconified(evt);
            }
        });
        
        jP_TOT.setLayout(new java.awt.BorderLayout());
        
        jP_Control.setLayout(new java.awt.BorderLayout());
        
        jP_Control.setBackground(new java.awt.Color(230, 230, 230));
        jP_Control.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_Control.setMinimumSize(new java.awt.Dimension(206, 50));
        jP_Control.setPreferredSize(new java.awt.Dimension(10, 50));
        jP_Align.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints1;
        
        jP_Align.setBackground(new java.awt.Color(230, 230, 230));
        jP_Align.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_Align.setMinimumSize(new java.awt.Dimension(225, 46));
        jP_Align.setPreferredSize(new java.awt.Dimension(225, 46));
        jCBox_align.setMinimumSize(new java.awt.Dimension(32, 20));
        jCBox_align.setPreferredSize(new java.awt.Dimension(32, 20));
        jCBox_align.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_alignActionPerformed(evt);
            }
        });
        
        gridBagConstraints1 = new java.awt.GridBagConstraints();
        gridBagConstraints1.gridx = 0;
        gridBagConstraints1.gridy = 0;
        gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints1.insets = new java.awt.Insets(3, 3, 3, 3);
        gridBagConstraints1.weightx = 1.0;
        jP_Align.add(jCBox_align, gridBagConstraints1);
        
        jP_Control.add(jP_Align, java.awt.BorderLayout.WEST);
        
        jPanel1.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints2;
        
        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(256, 46));
        jB_Expression.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_functions.jpg")));
        jB_Expression.setToolTipText("Functions");
        jB_Expression.setBorderPainted(false);
        jB_Expression.setContentAreaFilled(false);
        jB_Expression.setFocusPainted(false);
        jB_Expression.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jB_Expression.setMinimumSize(new java.awt.Dimension(72, 20));
        jB_Expression.setPreferredSize(new java.awt.Dimension(72, 20));
        jB_Expression.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_functions_press.jpg")));
        jB_Expression.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_functions_over.jpg")));
        jB_Expression.setEnabled(false);
        jB_Expression.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ExpressionActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jB_Expression, gridBagConstraints2);
        
        jB_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_search.jpg")));
        jB_search.setToolTipText("Search");
        jB_search.setBorderPainted(false);
        jB_search.setContentAreaFilled(false);
        jB_search.setFocusPainted(false);
        jB_search.setMinimumSize(new java.awt.Dimension(70, 30));
        jB_search.setPreferredSize(new java.awt.Dimension(70, 30));
        jB_search.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_search_press.jpg")));
        jB_search.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_search_over.jpg")));
        jB_search.setEnabled(false);
        jB_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_searchActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 2;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jB_search, gridBagConstraints2);
        
        jR_TotalColumns.setBackground(new java.awt.Color(230, 230, 230));
        jR_TotalColumns.setToolTipText("Show Total Columns (ON/OFF)");
        jR_TotalColumns.setContentAreaFilled(false);
        jR_TotalColumns.setFocusPainted(false);
        jR_TotalColumns.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jR_TotalColumns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pagetotal.jpg")));
        jR_TotalColumns.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pagetotal_press.jpg")));
        jR_TotalColumns.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pagetotal_over.jpg")));
        jR_TotalColumns.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pagetotal_over_on.jpg")));
        jR_TotalColumns.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_pagetotal_on.jpg")));
        jR_TotalColumns.setEnabled(false);
        jR_TotalColumns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jR_TotalColumnsMouseReleased(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 3;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jR_TotalColumns, gridBagConstraints2);
        
        jB_option.setBackground(new java.awt.Color(230, 230, 230));
        jB_option.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_options.jpg")));
        jB_option.setToolTipText(" Advance Options");
        jB_option.setBorderPainted(false);
        jB_option.setContentAreaFilled(false);
        jB_option.setFocusPainted(false);
        jB_option.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jB_option.setMaximumSize(new java.awt.Dimension(53, 20));
        jB_option.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_options_press.jpg")));
        jB_option.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_options_over.jpg")));
        jB_option.setEnabled(false);
        jB_option.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_optionActionPerformed(evt);
            }
        });
        
        gridBagConstraints2 = new java.awt.GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints2.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jB_option, gridBagConstraints2);
        
        jP_Control.add(jPanel1, java.awt.BorderLayout.CENTER);
        
        jP_page.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints3;
        
        jP_page.setBackground(new java.awt.Color(230, 230, 230));
        jP_page.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jP_page.setMinimumSize(new java.awt.Dimension(400, 46));
        jP_page.setPreferredSize(new java.awt.Dimension(400, 46));
        jCBox_Pages.setBackground(new java.awt.Color(230, 230, 230));
        jCBox_Pages.setMinimumSize(new java.awt.Dimension(100, 18));
        jCBox_Pages.setPreferredSize(new java.awt.Dimension(100, 18));
        jCBox_Pages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBox_PagesActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        jP_page.add(jCBox_Pages, gridBagConstraints3);
        
        jL_infoReport.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_infoReport.setText("Report Rows:");
        jL_infoReport.setMaximumSize(new java.awt.Dimension(100, 20));
        jL_infoReport.setMinimumSize(new java.awt.Dimension(80, 16));
        jL_infoReport.setPreferredSize(new java.awt.Dimension(80, 16));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 0, 1);
        gridBagConstraints3.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints3.weightx = 1.0;
        jP_page.add(jL_infoReport, gridBagConstraints3);
        
        jBarPage.setMinimumSize(new java.awt.Dimension(80, 8));
        jBarPage.setPreferredSize(new java.awt.Dimension(80, 8));
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.gridwidth = 2;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(2, 2, 0, 2);
        jP_page.add(jBarPage, gridBagConstraints3);
        
        jL_tPage.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_tPage.setText("Page Rows:");
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints3.insets = new java.awt.Insets(1, 1, 1, 1);
        gridBagConstraints3.weightx = 1.0;
        jP_page.add(jL_tPage, gridBagConstraints3);
        
        jB_ReloadPage.setBackground(new java.awt.Color(230, 230, 230));
        jB_ReloadPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload.jpg")));
        jB_ReloadPage.setToolTipText("Reload Page");
        jB_ReloadPage.setBorderPainted(false);
        jB_ReloadPage.setContentAreaFilled(false);
        jB_ReloadPage.setFocusPainted(false);
        jB_ReloadPage.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jB_ReloadPage.setMinimumSize(new java.awt.Dimension(60, 20));
        jB_ReloadPage.setPreferredSize(new java.awt.Dimension(60, 20));
        jB_ReloadPage.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload_press.jpg")));
        jB_ReloadPage.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/b_reload_over.jpg")));
        jB_ReloadPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ReloadPageActionPerformed(evt);
            }
        });
        
        gridBagConstraints3 = new java.awt.GridBagConstraints();
        gridBagConstraints3.gridx = 2;
        gridBagConstraints3.gridy = 1;
        jP_page.add(jB_ReloadPage, gridBagConstraints3);
        
        jP_Control.add(jP_page, java.awt.BorderLayout.EAST);
        
        jP_TOT.add(jP_Control, java.awt.BorderLayout.SOUTH);
        
        jP_GlobalPivot.setLayout(new java.awt.BorderLayout());
        
        jP_GlobalPivot.setMinimumSize(new java.awt.Dimension(29, 1000));
        jScroll_Pivot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScroll_PivotMouseReleased(evt);
            }
        });
        
        jP_GlobalPivot.add(jScroll_Pivot, java.awt.BorderLayout.CENTER);
        
        jScroll_IDPivot.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jP_GlobalPivot.add(jScroll_IDPivot, java.awt.BorderLayout.WEST);
        
        jP_totalTable.setLayout(new javax.swing.BoxLayout(jP_totalTable, javax.swing.BoxLayout.X_AXIS));
        
        jP_totalTable.setBorder(new javax.swing.border.MatteBorder(new java.awt.Insets(2, 5, 2, 10), new java.awt.Color(230, 230, 230)));
        jP_totalTable.setPreferredSize(new java.awt.Dimension(10, 22));
        jP_totalTable.setOpaque(false);
        jP_GlobalPivot.add(jP_totalTable, java.awt.BorderLayout.SOUTH);
        
        jP_TOT.add(jP_GlobalPivot, java.awt.BorderLayout.CENTER);
        
        getContentPane().add(jP_TOT, java.awt.BorderLayout.CENTER);
        
        jP_north.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints4;
        
        jP_north.setBackground(new java.awt.Color(230, 230, 230));
        jL_title.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pivot.png")));
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        jP_north.add(jL_title, gridBagConstraints4);
        
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));
        
        jPanel2.setBackground(new java.awt.Color(230, 230, 230));
        jPanel2.setMinimumSize(new java.awt.Dimension(600, 10));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 10));
        jB_print.setBackground(new java.awt.Color(230, 230, 230));
        jB_print.setFont(new java.awt.Font("Dialog", 1, 10));
        jB_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ico_html.png")));
        jB_print.setText("Export HTML");
        jB_print.setBorderPainted(false);
        jB_print.setFocusPainted(false);
        jB_print.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_print.setMinimumSize(new java.awt.Dimension(110, 32));
        jB_print.setPreferredSize(new java.awt.Dimension(110, 32));
        jB_print.setEnabled(false);
        jB_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_printActionPerformed(evt);
            }
        });
        
        jPanel2.add(jB_print);
        
        jB_csv.setBackground(new java.awt.Color(230, 230, 230));
        jB_csv.setFont(new java.awt.Font("Dialog", 1, 10));
        jB_csv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ico_excel.png")));
        jB_csv.setText("Export CSV");
        jB_csv.setBorderPainted(false);
        jB_csv.setFocusPainted(false);
        jB_csv.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_csv.setMinimumSize(new java.awt.Dimension(110, 32));
        jB_csv.setPreferredSize(new java.awt.Dimension(110, 32));
        jB_csv.setEnabled(false);
        jB_csv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_csvActionPerformed(evt);
            }
        });
        
        jPanel2.add(jB_csv);
        
        jB_pdf.setBackground(new java.awt.Color(230, 230, 230));
        jB_pdf.setFont(new java.awt.Font("Dialog", 1, 10));
        jB_pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ico_pdf.png")));
        jB_pdf.setText("Export PDF");
        jB_pdf.setBorderPainted(false);
        jB_pdf.setFocusPainted(false);
        jB_pdf.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_pdf.setMinimumSize(new java.awt.Dimension(110, 32));
        jB_pdf.setPreferredSize(new java.awt.Dimension(110, 32));
        jB_pdf.setEnabled(false);
        jB_pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_pdfActionPerformed(evt);
            }
        });
        
        jPanel2.add(jB_pdf);
        
        jB_excel.setBackground(new java.awt.Color(230, 230, 230));
        jB_excel.setFont(new java.awt.Font("Dialog", 1, 10));
        jB_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ico_excel.png")));
        jB_excel.setText("Export EXCEL");
        jB_excel.setBorderPainted(false);
        jB_excel.setFocusPainted(false);
        jB_excel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jB_excel.setMinimumSize(new java.awt.Dimension(110, 32));
        jB_excel.setPreferredSize(new java.awt.Dimension(110, 32));
        jB_excel.setEnabled(false);
        jB_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_excelActionPerformed(evt);
            }
        });
        
        jPanel2.add(jB_excel);
        
        gridBagConstraints4 = new java.awt.GridBagConstraints();
        gridBagConstraints4.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints4.weightx = 1.0;
        jP_north.add(jPanel2, gridBagConstraints4);
        
        getContentPane().add(jP_north, java.awt.BorderLayout.NORTH);
        
        pack();
    }//GEN-END:initComponents

    private void jM_drill_downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_drill_downActionPerformed
        // Add your handling code here:

        this.setCursor(Cur_wait);
        
        int[] RowsIndexSel = jTable_Pivot.getSelectedRows();
        int[] ColumnsIndexSel = jTable_Pivot.getSelectedColumns();

        if(RowsIndexSel.length > 0)
        {        
            int row_max = 10;
            if(RowsIndexSel.length > row_max)
            {            
                staticLib.optionPanel("- Drill Down -\nYou can select a maximum of "+row_max+" rows.", "INFORMATION",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                this.setCursor(Cur_default);
                return; 
            }

            if(!jTable_Pivot.getColumnSelectionAllowed() && jTable_Pivot.getRowSelectionAllowed()) // --> selection mode rows
            {
                ColumnsIndexSel = new int[jTable_Pivot.getColumnCount()];
                for(int n=0; n<ColumnsIndexSel.length; n++)
                    ColumnsIndexSel[n] = n;
            }
            else if (jTable_Pivot.getColumnSelectionAllowed() && !jTable_Pivot.getRowSelectionAllowed()) // --> selection mode columns
            {
                RowsIndexSel = new int[jTable_Pivot.getRowCount()];
                for(int n=0; n<RowsIndexSel.length; n++)
                    RowsIndexSel[n] = n;
            }

            //System.out.println("RowsIndexSel.length -->"+RowsIndexSel.length);
            //System.out.println("ColumnsIndexSel.length -->"+ColumnsIndexSel.length);

            if(this.nameReportKey != null)
            {
                STRUCT_PARAMS structParam_drillDown = staticLib.cloneSTRUCT_PARAMS(staticLib.hashT_StructParams.get(nameReportKey));
                
                if(structParam_drillDown.NetworkRealTime == 1) //Disabilita' il drill down se è di tipo "Network Analysis";
                {
                    staticLib.optionPanel("It is not possible to drill down on a Network Analysis.", "INFORMATION",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    this.setCursor(Cur_default);
                    return;
                }                 
                int ID_RELATION = structParam_drillDown.Relation; 

                DATA_DATAELEMENT[] DATA_TEs_Relation = configuration.getDataElement_Relation(ID_RELATION);

                //printDebug_STRUCT_PARAMS(structParam_drillDown);

                BufferRestrizioni BR_drillDown = new BufferRestrizioni(false);
                BR_drillDown.setConfiguration(configuration);


                //------ Inserisco le REST_INFO originale della structParam_drillDown
                for(int i=0; i<structParam_drillDown.Filters.length; i++)
                {
                    REST_INFO Restrizioni = structParam_drillDown.Filters[i];
                    BR_drillDown.addRestriction(Restrizioni);
                }
                //------ END Inserisco le REST_INFO originale della structParam_drillDown

                int count_VALUE = 0;
                for(int r=0; r<RowsIndexSel.length; r++)        
                {
                    for(int c=0; c<ColumnsIndexSel.length; c++)
                    {
                        String ValueCell = new String(""+jTable_Pivot.getValueAt(RowsIndexSel[r],ColumnsIndexSel[c]));
                        //System.out.println("OK KEY VALUE CELL -----> row["+RowsIndexSel[r]+"] col=["+ColumnsIndexSel[c]+"] --------> ["+ValueCell+"]");

                        if(isColumnKey(ColumnsIndexSel[c]))
                        {                  
                            if(count_VALUE <= staticLib.mdm_server_serverRef.eMAX_N_VALUE) //staticLib.mdm_server_serverRef.eMAX_N_VALUE --> Capacità Massima di Restrizioni per Analisi
                            {
                                // ********* In caso di spostamento delle KEY relation nel JTABLE pivot. 
                                String NameColumn_HTML = jTable_Pivot.getColumnName(ColumnsIndexSel[c]).trim();
                                NameColumn_HTML = NameColumn_HTML.replaceAll(InitTagHTMLColumn,"^"); //Rimuovo Tag HTML
                                NameColumn_HTML = NameColumn_HTML.replaceAll(TAG2,"^");
                                NameColumn_HTML = NameColumn_HTML.replaceAll(TAG3,"^");
                                //System.out.println("str_var "+NameColumn_HTML);

                                java.util.StringTokenizer tokenStrName = new java.util.StringTokenizer(NameColumn_HTML,"^");                
                                String appo = tokenStrName.nextToken(); // scarto lettera variabile

                                //System.out.println("NameColumn_HTML "+NameColumn_HTML);
                                //System.out.println("appo "+appo);
                                String str_NameKey = tokenStrName.nextToken();     
                                
                                //System.out.println("str_NameKey "+str_NameKey);
                                
                                int POS_TE_RELATION = getIndexColumnKeyPosOrigin(str_NameKey);
                                //System.out.println("POSIZIONE originale del TE nella Relazione POS -->"+POS_TE_RELATION+" "+str_NameKey);
                                // ********* In caso di spostamento delle KEY relation nel JTABLE pivot.

                                if(ValueCell.trim().length() != 0 )
                                {
                                    java.util.StringTokenizer Value_STK = new java.util.StringTokenizer(ValueCell," "); 
                                    String value = Value_STK.nextToken(); 
                                    //System.out.println("OK KEY VALUE CELL -----> row["+RowsIndexSel[r]+"] col=["+ColumnsIndexSel[c]+"] --------> ["+ValueCell+"]");

                                    // ************************************                        
                                    DATA_DATAELEMENT DATA_TE = DATA_TEs_Relation[POS_TE_RELATION];

                                    BR_drillDown.addRestriction(DATA_TE.idElement,
                                                                new String(DATA_TE.longDescription).trim(),
                                                                value,
                                                                value,
                                                                true,
                                                                DATA_TE.compareSelection,
                                                                DATA_TE.priority,
                                                                DATA_TE.exceptions[0]);

                                    BR_drillDown.applicaEccezioni(DATA_TE.idElement);                        
                                    // ************************************
                                }
                                //else
                                    //System.out.println("OK KEY VALUE CELL -----> row["+RowsIndexSel[r]+"] col=["+ColumnsIndexSel[c]+"] --------> ["+ValueCell+"]");
                            }
                            else
                            {
                                staticLib.optionPanel("- Drill Down -\nYour selection exceeds analysis capability.", "INFORMATION",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                               // System.out.println("count_VALUE --> "+count_VALUE);
                                this.setCursor(Cur_default);
                                return;
                            }                            
                            count_VALUE++;
                        }
                        //else
                            //System.out.println("value - row["+RowsIndexSel[r]+"] col=["+ColumnsIndexSel[c]+"] --------> "+ValueCell);
                    }
                }
                
                REST_INFO[] REST_INFO_drillDown = BR_drillDown.creaRest();
                //printDebug_REST_INFO(REST_INFO_drillDown);

                // ------ Imposto STRUCT_PARAM per drillDown
                structParam_drillDown.Filters = REST_INFO_drillDown;    
                
                structParam_drillDown.AnalysisType = staticLib.dd_TipoAnalisi; 
                structParam_drillDown.idReportSchema = staticLib.dd_idReportSchema;  
               
                // ******* eventuale ID RELAZIONE GHOST per ANALISI selezionata ---
                int POS_Analisi = localAnalisi.getPOS_Analisi_conId(structParam_drillDown.AnalysisType);
                if(staticLib.ghostAnalisi[POS_Analisi])
                    structParam_drillDown.Relation = staticLib.idGhostRel[POS_Analisi]; 
                // end --------------  Imposto STRUCT_PARAM per drillDown

               //System.out.println("-- STRUCT PARAM PER drillDown --");
               //System.out.println("drillDown Tipo ANALISI  --> id = "+structParam_drillDown.AnalysisType);
               //System.out.println("drillDown Report Schema --> id = "+structParam_drillDown.idReportSchema);
               //System.out.println("ANALISI id ="+structParam_drillDown.AnalysisType+" GHOST ---<"+staticLib.ghostAnalisi[POS_Analisi] +">---  ID_GHOST_RELATION ==> "+structParam_drillDown.Relation);
                
             // end -- Imposto STRUCT_PARAM per drillDown
                
                MDM_JD_Request jd_Request = new MDM_JD_Request(this,structParam_drillDown,BR_drillDown,configuration);
                jd_Request.show_JD_Request();
                
                //printDebug_STRUCT_PARAMS(structParam_drillDown);
            }
            else
                System.out.println("ERRORE: Attenzione nameReportKey è null");
        }       
        this.setCursor(Cur_default);
    }//GEN-LAST:event_jM_drill_downActionPerformed

    private void printDebug_REST_INFO(REST_INFO[] rest_info)
    {
        for(int r=0; r<rest_info.length; r++)
        {            
            REST_INFO Restrizioni = rest_info[r];
            System.out.println();
            if(Restrizioni != null)
            {
                System.out.println("Restrizioni.Level     -->"+Restrizioni.Level);
                System.out.println("Restrizioni.Element    -->"+Restrizioni.Element);
                
                System.out.println("Restrizioni.Value.length   -->"+Restrizioni.Value.length);
                for(int v=0; v<Restrizioni.Value.length; v++)
                {
                    if(Restrizioni.Value[v] > 0)
                        System.out.println("Restrizioni.Value["+v+"] --> "+Restrizioni.Value[v]);
                }

                int size_a1 = Restrizioni.AsciiValue.length;
                System.out.println("Restrizioni.AsciiValue.length   -->"+size_a1);
                for(int a1=0; a1<size_a1; a1++)
                {
                    String str_AsciiValue = new String(Restrizioni.AsciiValue[a1]).trim();
                    if(str_AsciiValue.length() >0)
                        System.out.println("Restrizioni.AsciiValue["+a1+"] -->" +str_AsciiValue);
                }

                System.out.println("Restrizioni.Operator --> "+Restrizioni.Operator);
                System.out.println("Restrizioni.Priority  --> "+Restrizioni.Priority);
                System.out.println();
            }
        }

    }
    
    private void printDebug_STRUCT_PARAMS(STRUCT_PARAMS structParam_debug)
    {
        System.out.println();
        System.out.println("********** printDebug_STRUCT_PARAMS ***********");
        System.out.println();
        System.out.println("structParam_debug.Name --> "+new String(structParam_debug.Name).trim());
      
        System.out.println("structParam_debug.TipoAnalisi     --> "+structParam_debug.AnalysisType);
        System.out.println("structParam_debug.NetworkRealTime --> "+structParam_debug.NetworkRealTime);
        System.out.println("structParam_debug.TipoDati        --> "+structParam_debug.DataType);
        System.out.println("structParam_debug.TipoOutput      --> "+structParam_debug.OutputType);

        for(int d=0; d<structParam_debug.ElaborationData.length; d++)
        {
            String dateString = new String(structParam_debug.ElaborationData[d].dateString).trim();
            if(dateString.length() > 0)
                System.out.println("dateString["+d+"] --> "+dateString);
        }

        for(int c=0; c<structParam_debug.Fep.length; c++)
            System.out.println("structParam_debug.Fep["+c+"] --> "+structParam_debug.Fep[c]);

        System.out.println("structParam_debug.Relazione --> "+structParam_debug.Relation);
        System.out.println("structParam_debug.DirezioneRelazione --> "+structParam_debug.RelationDirection);
        
        printDebug_REST_INFO(structParam_debug.Filters);

        System.out.println("structParam_debug.OppositeRestriction   --> "+structParam_debug.OppositeRestriction);
        System.out.println("structParam_debug.FlagSort       --> "+structParam_debug.FlagSort);
        System.out.println("structParam_debug.ElementToSort  --> "+structParam_debug.ElementToSort);
        System.out.println("structParam_debug.Ascendente     --> "+structParam_debug.Ascendent);
        System.out.println("structParam_debug.Reserved       --> "+structParam_debug.Reserved);
        System.out.println("structParam_debug.SingleRelation --> "+structParam_debug.SingleRelation);
        System.out.println("structParam_debug.IsPercent      --> "+structParam_debug.IsPercent);
        System.out.println("structParam_debug.HexValue    --> "+structParam_debug.HexValue);
        System.out.println("structParam_debug.isScheduled --> "+structParam_debug.isScheduled);
        System.out.println("structParam_debug.idUser      --> "+structParam_debug.idUser);
        System.out.println("structParam_debug.idJob       --> "+structParam_debug.idJob);
        
        System.out.println("structParam_debug.isDescription  -->"+new String(structParam_debug.Description).trim());
        System.out.println("structParam_debug.idReportSchema -->"+structParam_debug.idReportSchema);
        System.out.println("structParam_debug.user           -->"+new String(structParam_debug.user).trim());
        System.out.println("structParam_debug.user_ip        -->"+new String(structParam_debug.user_ip).trim());
        System.out.println("structParam_debug.freeFormat     -->"+structParam_debug.freeFormat);
        System.out.println("structParam_debug.ffRelation     -->"+structParam_debug.freeFormat);
        
        System.out.println();
        System.out.println("** END ********** printDebug_STRUCT_PARAMS ***********");
        System.out.println();
        System.out.println();
   }
    
    private void jB_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_excelActionPerformed
        this.setCursor(Cur_wait);
            
        
        int count_row = jTable_Pivot.getRowCount();
        int row_max = 65536; //Limite Excel
        if(count_row >= row_max)
        {
            staticLib.optionPanel("EXCEL Export is possible for a page rows number less then "+row_max+".", "INFORMATION",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        this.setCursor(Cur_wait);
        
        
        jPopMenu.setEnabled(false);
        jB_search.setEnabled(false);
        jR_TotalColumns.setEnabled(false);
        jB_option.setEnabled(false);
        jB_Expression.setEnabled(false);
        jB_ReloadPage.setEnabled(false);
        jB_print.setEnabled(false);
        jB_csv.setEnabled(false);
        jB_pdf.setEnabled(false);
        jB_excel.setEnabled(false);        
        jTable_Index.setEnabled(false);
        jTable_Pivot.setEnabled(false);

        runGC();        
        MDM_PrintWebPivot prn_pivot_excel = new MDM_PrintWebPivot("EXCEL_MDM_Pivot",JAppl,jTable_Pivot,numKeyRel_NAME,4,VALUE_DECIMAL);
        prn_pivot_excel = null;
        runGC();

        jPopMenu.setEnabled(true);
        jB_search.setEnabled(true);
        jR_TotalColumns.setEnabled(true);
        jB_option.setEnabled(true);
        jB_Expression.setEnabled(true);
        jB_ReloadPage.setEnabled(true);
        jB_print.setEnabled(true);
        jB_csv.setEnabled(true);
        jB_pdf.setEnabled(true);
        jB_excel.setEnabled(true);
        jTable_Index.setEnabled(true);
        jTable_Pivot.setEnabled(true);

        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_excelActionPerformed

    private void jB_pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_pdfActionPerformed
        this.setCursor(Cur_wait);
            
        jPopMenu.setEnabled(false);
        jB_search.setEnabled(false);
        jR_TotalColumns.setEnabled(false);
        jB_option.setEnabled(false);
        jB_Expression.setEnabled(false);
        jB_ReloadPage.setEnabled(false);
        jB_print.setEnabled(false);
        jB_csv.setEnabled(false);
        jB_pdf.setEnabled(false);
        jB_excel.setEnabled(false);        
        jTable_Index.setEnabled(false);
        jTable_Pivot.setEnabled(false);

        runGC();        
        MDM_PrintWebPivot prn_pivot_pdf = new MDM_PrintWebPivot("PDF_MDM_Pivot",JAppl,jTable_Pivot,numKeyRel_NAME,3,-1);
        prn_pivot_pdf = null;
        runGC();

        jPopMenu.setEnabled(true);
        jB_search.setEnabled(true);
        jR_TotalColumns.setEnabled(true);
        jB_option.setEnabled(true);
        jB_Expression.setEnabled(true);
        jB_ReloadPage.setEnabled(true);
        jB_print.setEnabled(true);
        jB_csv.setEnabled(true);
        jB_pdf.setEnabled(true);
        jB_excel.setEnabled(true);
        jTable_Index.setEnabled(true);
        jTable_Pivot.setEnabled(true);

        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_pdfActionPerformed

    private void jMenuBackgroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBackgroundActionPerformed
        colorBackPivot = javax.swing.JColorChooser.showDialog(this, "Color Chooser",colorBackPivot);
        jTable_Pivot.setBackground(colorBackPivot);
    }//GEN-LAST:event_jMenuBackgroundActionPerformed

    private void jMenuForegroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuForegroundActionPerformed
        
        colorForePivot = javax.swing.JColorChooser.showDialog(this, "Color Chooser",colorForePivot);
        jTable_Pivot.setForeground(colorForePivot);
    }//GEN-LAST:event_jMenuForegroundActionPerformed

    private void jMen_fontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMen_fontActionPerformed
        
        try
        {
            MDM_JD_FontChooser jf_font = new MDM_JD_FontChooser((java.awt.Frame)this,true);
            jf_font.openFontChooser(fontDefaultPivot);
            //System.out.println("font Selected "+jf_font.getFontSelected().toString());            
            fontDefaultPivot = jf_font.getFontSelected();
            
            java.awt.FontMetrics fm;
            fm = getFontMetrics(fontDefaultPivot);
            int highFont = fm.getHeight();
            
            jTable_Index.setRowHeight(highFont);
            jTable_Pivot.setRowHeight(highFont);
            jTable_Index.setRowMargin(2);
            jTable_Pivot.setRowMargin(2);
            
            repaint();
        }
        catch (Exception e)
        {}
        
        //Color color = javax.swing.JColorChooser.showDialog(this, "Color Chooser",Color.red);

    }//GEN-LAST:event_jMen_fontActionPerformed

    private void jB_csvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_csvActionPerformed
        // Add your handling code here:
        this.setCursor(Cur_wait);
            
        jPopMenu.setEnabled(false);
        jB_search.setEnabled(false);
        jR_TotalColumns.setEnabled(false);
        jB_option.setEnabled(false);
        jB_Expression.setEnabled(false);
        jB_ReloadPage.setEnabled(false);
        jB_print.setEnabled(false);
        jB_csv.setEnabled(false);
        jB_pdf.setEnabled(false);
        jB_excel.setEnabled(false);
        jTable_Index.setEnabled(false);
        jTable_Pivot.setEnabled(false);

        runGC();        
        MDM_PrintWebPivot prn_pivot_csv = new MDM_PrintWebPivot("ExcelExportPivot",JAppl,jTable_Pivot,numKeyRel_NAME,2,-1);
        prn_pivot_csv = null;
        runGC();

        jPopMenu.setEnabled(true);
        jB_search.setEnabled(true);
        jR_TotalColumns.setEnabled(true);
        jB_option.setEnabled(true);
        jB_Expression.setEnabled(true);
        jB_ReloadPage.setEnabled(true);
        jB_print.setEnabled(true);
        jB_csv.setEnabled(true);
        jB_pdf.setEnabled(true);
        jB_excel.setEnabled(true);
        jTable_Index.setEnabled(true);
        jTable_Pivot.setEnabled(true);

        this.setCursor(Cur_default);
    }//GEN-LAST:event_jB_csvActionPerformed

    private void jB_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_printActionPerformed
        
        int count_row = jTable_Pivot.getRowCount();
        int row_max = 1000;
        if(count_row >= row_max)
        {
            staticLib.optionPanel("WEB Export is possible for a page rows number less then "+row_max+".", "INFORMATION",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        this.setCursor(Cur_wait);
        
        jPopMenu.setEnabled(false);
        jB_search.setEnabled(false);
        jR_TotalColumns.setEnabled(false);
        jB_option.setEnabled(false);
        jB_Expression.setEnabled(false);
        jB_ReloadPage.setEnabled(false);
        jB_print.setEnabled(false);
        jB_csv.setEnabled(false);
        jB_pdf.setEnabled(false);
        jB_excel.setEnabled(false);
        jTable_Index.setEnabled(false);
        jTable_Pivot.setEnabled(false);

        runGC();        
        MDM_PrintWebPivot prn_pivot = new MDM_PrintWebPivot("WebExportPivot",JAppl,jTable_Pivot,numKeyRel_NAME,1,-1);
        prn_pivot = null;
        runGC();

        jPopMenu.setEnabled(true);
        jB_search.setEnabled(true);
        jR_TotalColumns.setEnabled(true);
        jB_option.setEnabled(true);
        jB_Expression.setEnabled(true);
        jB_ReloadPage.setEnabled(true);
        jB_print.setEnabled(true);
        jB_csv.setEnabled(true);
        jB_pdf.setEnabled(true);
        jB_excel.setEnabled(true);
        jTable_Index.setEnabled(true);
        jTable_Pivot.setEnabled(true);

        this.setCursor(Cur_default);
        
        
        
    }//GEN-LAST:event_jB_printActionPerformed
            
   
    private void jB_optionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_optionActionPerformed
        
        if(jTable_Pivot.isEnabled())
        {
            if((jTable_Pivot.getSelectedColumns().length == 1)&&(jTable_Pivot.getCellSelectionEnabled()))
                jMenuFocus.setEnabled(true);
            else
                jMenuFocus.setEnabled(false);


            jPopMenu.show((java.awt.Component)evt.getSource(),jB_option.getX(),jB_option.getX()-210);
        }
        /*else
            System.out.println("jTable_Pivot.isEnabled() == false");*/

    }//GEN-LAST:event_jB_optionActionPerformed

    
    
    private void jMenu_jRb_selModeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu_jRb_selModeMouseReleased
        //System.out.println("jMenu_jRb_selModeMouseReleased");
        if(jMenu_jRb_selColumn.isSelected())
        {
            setColumnSelection();
        }
        else if (jMenu_jRb_selRow.isSelected())
        {
            setRowSelection();
        }
        else //jMenu_jRb_selCell.isSelected()
        {
            setCellSelection();
        }
    }//GEN-LAST:event_jMenu_jRb_selModeMouseReleased

    private void jCBox_alignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBox_alignActionPerformed
     
         
        int countAlign = jCBox_align.getSelectedIndex();
        if(jTable_Pivot != null)
        {
            jTable_Pivot.setAutoResizeMode(countAlign);
            //System.out.println("getAutoResizeMode "+jTable_Pivot.getAutoResizeMode());
        }
        
       /* System.out.println("AUTO_RESIZE_OFF "+JTable.AUTO_RESIZE_OFF);
        System.out.println("AUTO_RESIZE_ALL_COLUMNS "+JTable.AUTO_RESIZE_ALL_COLUMNS);
        System.out.println("AUTO_RESIZE_LAST_COLUMN "+JTable.AUTO_RESIZE_LAST_COLUMN);
        System.out.println("AUTO_RESIZE_NEXT_COLUMN "+JTable.AUTO_RESIZE_NEXT_COLUMN);
        System.out.println("AUTO_RESIZE_SUBSEQUENT_COLUMNS "+JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);*/
    }//GEN-LAST:event_jCBox_alignActionPerformed

    private void jScroll_PivotMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScroll_PivotMouseReleased
        Object target = evt.getSource();
        int m = evt.getModifiers();
        if ((m & java.awt.event.InputEvent.BUTTON3_MASK)!=0)
        {
            //System.out.println("a");

            if((jTable_Pivot.getSelectedColumns().length == 1)&&(jTable_Pivot.getCellSelectionEnabled()))
                jMenuFocus.setEnabled(true);
            else
                jMenuFocus.setEnabled(false);


            jPopMenu.show((java.awt.Component)evt.getSource(),(evt.getX()),evt.getY());
        }
    }//GEN-LAST:event_jScroll_PivotMouseReleased

    private void jMenu_ShowRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_ShowRowActionPerformed
        restoreRows();
    }//GEN-LAST:event_jMenu_ShowRowActionPerformed

    private void jMenu_ShowColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_ShowColumnActionPerformed
        jMenu_ShowColumn.setEnabled(false);

        for(int x=0; x<V_HideColumn.size(); x++)
        {
            TableColumn HideColumn = (TableColumn)V_HideColumn.elementAt(x);
            HideColumn.setMinWidth(minCellDimension);
            HideColumn.setMaxWidth(2000);
            HideColumn.setPreferredWidth(100);
            HideColumn.setWidth(100);
            HideColumn.setResizable(true);
        }
        V_HideColumn.removeAllElements();
                  
        if(jR_TotalColumns.isSelected())
            setTotalColumns();
    }//GEN-LAST:event_jMenu_ShowColumnActionPerformed

    private void jMenu_HideRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_HideRowActionPerformed
        deleteSelectionRows();
    }//GEN-LAST:event_jMenu_HideRowActionPerformed

    private boolean isColumnKey(int indexCol)
    {
        String NameColumn_HTML = jTable_Pivot.getColumnName(indexCol).trim();
        for(int i=0; i<numKeyRel_NAME.length; i++)
        {
            if(NameColumn_HTML.indexOf(numKeyRel_NAME[i]) != -1)
                return true;
        }
        return false;
    }
    
    
    private int getIndexColumn_DATA_PIVOT(String NameKEY)
    {
        //System.out.println("NameVarColumn "+NameVarColumn);
        for(int i=0; i<ARR_KEY_ENABLED_NAME.length; i++)
        {
            if(ARR_KEY_ENABLED_NAME[i].equals(NameKEY))
            {
                return i;
            }
        }
        System.out.println("Errore getIndexColumn_DATA_PIVOT -1");
        return -1;        
    }
    
    
    private int getIndexColumnKeyPosOrigin(String NameColumnKEY)
    {
        //System.out.println("NameColumnKEY "+NameColumnKEY);
        for(int i=0; i<numKeyRel_NAME.length; i++)
        {
            if(numKeyRel_NAME[i].equals(NameColumnKEY))
            {
                return i;
            }
        }
        System.out.println("Errore nameKeyREL -1");
        return -1;        
    }
    
    
    
    private void jMenu_HideColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_HideColumnActionPerformed
        int[] ColumnsIndexSel =  jTable_Pivot.getSelectedColumns();
              
        boolean TOTAL_RELOAD_PIVOT = false;
        for(int i=0; i<ColumnsIndexSel.length; i++)
        {        
            if(isColumnKey(ColumnsIndexSel[i]))
            {

                String NameColumn_HTML = jTable_Pivot.getColumnName(ColumnsIndexSel[i]).trim();
                NameColumn_HTML = NameColumn_HTML.replaceAll(InitTagHTMLColumn,"^"); //Rimuovo Tag HTML
                NameColumn_HTML = NameColumn_HTML.replaceAll(TAG2,"^");
                NameColumn_HTML = NameColumn_HTML.replaceAll(TAG3,"^");
                //System.out.println("str_var "+NameColumn_HTML);
                
                java.util.StringTokenizer tokenStrName = new java.util.StringTokenizer(NameColumn_HTML,"^");                
                tokenStrName.nextToken(); // scarto lettera variabile
                
                String str_NameKey = tokenStrName.nextToken();     
                
                //System.out.println("str_var -"+str_NameKey.trim()+"-");
                int indexColumnKeyDisable = getIndexColumn_DATA_PIVOT(str_NameKey);
                
                //System.out.println("indexColumnKeyDisable pos -->"+indexColumnKeyDisable);
                if(indexColumnKeyDisable >= 0)
                {
                    ARR_KEY_ENABLED[indexColumnKeyDisable] = false;                                        
                    /*for(int y=0; y<ARR_KEY_ENABLED.length; y++)
                    {
                        System.out.println("ARR_KEY_ENABLED pos -->"+ARR_KEY_ENABLED[y]);
                    }*/
                    TOTAL_RELOAD_PIVOT = true;                    
                }
                else
                    return;
            }
            else
            {
                jMenu_ShowColumn.setEnabled(true);
                //System.out.println("Ho cancellato un Contatore ");
                
                TableColumn hideColumn = (TableColumn)jTable_Pivot.getColumnModel().getColumn(ColumnsIndexSel[i]);
            
                int SIZE = V_HideColumn.size();            
                if(SIZE == 0)
                    V_HideColumn.add(hideColumn);
                else
                {
                    boolean find = false;
                    for(int y=0; y<SIZE; y++)
                    {
                        if(((TableColumn)V_HideColumn.elementAt(y)).equals(hideColumn) )
                        {
                            find = true;
                            break;
                        }                    
                    }
                    if(find == false)
                    {
                        V_HideColumn.add(hideColumn);
                    }
                }
                hideColumn.setMinWidth(0);
                hideColumn.setMaxWidth(0);
                hideColumn.setWidth(0);
                hideColumn.setPreferredWidth(0);
                hideColumn.setResizable(false);          
            }            
        }        
        
        if(TOTAL_RELOAD_PIVOT == true)
            totalReloadPivot();
        
        if(jR_TotalColumns.isSelected())
        {
            setTotalColumns();            
        }
        
    }//GEN-LAST:event_jMenu_HideColumnActionPerformed

    private void jB_ReloadPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ReloadPageActionPerformed
        Read_ADD_Data();
    }//GEN-LAST:event_jB_ReloadPageActionPerformed

    private void jB_ExpressionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ExpressionActionPerformed
        
        if(jf_MathExp != null)
            jf_MathExp.close_JF_MathExpression();
        
        
        JTableHeader header = jTable_Pivot.getTableHeader();
        header.setReorderingAllowed(false);       
        
        jf_MathExp = null;     
        jf_MathExp = new JF_MathExpression(this);
        //jf_MathExp.show();
	jf_MathExp.setVisible(true);
    }//GEN-LAST:event_jB_ExpressionActionPerformed

    private void jMenuRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuRestoreActionPerformed
        restoreRows();
    }//GEN-LAST:event_jMenuRestoreActionPerformed

    private void jMenuFocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFocusActionPerformed
   
        if(jTable_Pivot.getCellSelectionEnabled())
        {
            int count_rows = jTable_Pivot.getRowCount();
            //Object[][] DATA = new Object[count_rows][1];
            Vector v_AllCell_focus = new Vector();
            
            int colSel = jTable_Pivot.getSelectedColumn();
            
            /*for(int i=0; i<count_rows; i++)
                DATA[i][0] = jTable_Pivot.getValueAt(i,colSel);*/
            
            int rowIndexStart   = jTable_Pivot.getSelectedRow();
            int rowIndexEnd     = jTable_Pivot.getSelectionModel().getMaxSelectionIndex();
            int colIndexStart   = jTable_Pivot.getSelectedColumn();
            int ColIndexEnd     = jTable_Pivot.getColumnModel().getSelectionModel().getMaxSelectionIndex();

            for(int r=rowIndexStart; r<=rowIndexEnd; r++)
            {
                for(int c=colIndexStart; c<=ColIndexEnd; c++)
                {
                    if(jTable_Pivot.isCellSelected(r,c))
                    {
                        //String selcell = new String(""+DATA[r][0]);
                        String selcell = new String(""+jTable_Pivot.getValueAt(r,colSel));

                        v_AllCell_focus.addElement(selcell);
                        //System.out.println("- "+r+" col="+c+" "+selcell);
                    }
                }
            }
            
            jTable_Pivot.selectAll();
            for(int r=0; r<count_rows; r++)
            {                
                //String str_cell = new String(""+DATA[r][0]);
                String str_cell = new String(""+jTable_Pivot.getValueAt(r,colSel));
                
                for(int f=0; f<v_AllCell_focus.size(); f++)
                {                  
                    String str_search = (String)v_AllCell_focus.elementAt(f);                        
                    if( str_cell.compareTo(str_search) == 0 )
                    {
                        //System.out.println("deseleziona riga ="+r+" col="+colSel+" "+str_cell);
                        jTable_Pivot.removeRowSelectionInterval(r,r);
                    }
                }                
                //DATA[r][0] = null;
            }
            
            //DATA = null;
            v_AllCell_focus.removeAllElements();
            deleteSelectionRows();           
        }
    }//GEN-LAST:event_jMenuFocusActionPerformed

    private void header_pivotMouseReleased(java.awt.event.MouseEvent evt) 
    {
       if(jR_TotalColumns.isSelected())
       {
            setTotalColumns();
       }
    }
      
    private void jR_TotalColumnsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jR_TotalColumnsMouseReleased
        if(jR_TotalColumns.isSelected())
        {            
            setTotalColumns();
        }
        else
        {
            jP_totalTable.removeAll();
            jP_totalTable.validate();
            jP_totalTable.repaint();
        }
            
    }//GEN-LAST:event_jR_TotalColumnsMouseReleased

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        if(jR_TotalColumns.isSelected())
        {
            setTotalColumns();
        }
    }//GEN-LAST:event_formComponentResized

    private void formWindowIconified(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowIconified
       // System.out.println("formWindowIconified");
        if(jf_search != null)
        {
            jf_search.dispose();
            jf_search = null;
        }
    }//GEN-LAST:event_formWindowIconified

    private void jB_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_searchActionPerformed
        jMenu_jRb_selRow.setSelected(true);
        setRowSelection();  
        
        if(jf_search != null)
        {
            jf_search.dispose();
            jf_search = null;
        }  
            
        jf_search = new JF_Search(jTable_Pivot,InitTagHTMLColumn);
  
    }//GEN-LAST:event_jB_searchActionPerformed

    private void jCBox_PagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBox_PagesActionPerformed
        int index = jCBox_Pages.getSelectedIndex();
        if((index >=0)&&(MAX_PAGES>1)&&(index!=SHOW_PAGE))
        {
            SHOW_PAGE = jCBox_Pages.getSelectedIndex();
            Read_ADD_Data();            
        }
    }//GEN-LAST:event_jCBox_PagesActionPerformed
    
    public void showColumnMathResult()
    {
        String CTRL_COL_MATH = InitTagHTMLColumn+TAG_Name_COLUMN_MATH;    
        for(int i=0; i<this.jTable_Pivot.getColumnCount(); i++)
        {                                   
            String NameColumn_HTML = jTable_Pivot.getColumnName(i).trim();            
            if(NameColumn_HTML.indexOf(CTRL_COL_MATH) != -1)
            {                
                TableColumn HideColumn = (TableColumn)jTable_Pivot.getColumnModel().getColumn(i);
                HideColumn.setMinWidth(minCellDimension);
                            
                int width = (int)(NameColumn_HTML.length()-50);
                
                HideColumn.setMaxWidth(2000);
                HideColumn.setPreferredWidth(width);
                HideColumn.setWidth(width);
                HideColumn.setResizable(true);
            }
        }
    }
          
    private void restoreRows()
    {
        this.setCursor(Cur_wait);
        //jTable_Pivot.setCursor(Cur_wait);
        jMenu_ShowRow.setEnabled(false);
        
        jMenuRestore.setEnabled(false);
        
        Object[][] datavalues_SHOW = myTableModel.getDataValues();
        
        int SIZE_HIDE_ROWS = V_HideRow.size();
        Object[][] datavalues = new Object[(datavalues_SHOW.length)+SIZE_HIDE_ROWS][NUM_COLUMN];

        int y=0;
        for(y=0; y<datavalues_SHOW.length; y++)
        {
            for(int z=0; z<datavalues_SHOW[y].length; z++)
                datavalues[y][z] = datavalues_SHOW[y][z];           
        }     
        
        for(int x=0; x<SIZE_HIDE_ROWS; x++)
        {
            Object[] row = (Object[])V_HideRow.elementAt(x);
            for(int t=0; t<row.length; t++)
            {
                if(row != null)
                    datavalues[y][t] = row[t];
                else
                    datavalues[y][t] = new Double(0.0); //new Float(0.0)
            }
            y++;
            row = null;
        }        
        V_HideRow.removeAllElements();
        
        //System.out.println("restore datavalues prima "+datavalues_SHOW.length);
        //System.out.println("restore datavalues dopo "+datavalues.length);        
        //System.out.println("restore Num Rows Hide "+V_HideRow.size());
        datavalues_SHOW = null;
        
        myTableModel.setDataValues(datavalues);
        sorter.setTableModel(myTableModel);
        jScroll_Pivot.setViewportView(jTable_Pivot);
        jTable_Index.clearSelection();
        
        jL_infoReport.setToolTipText(null);
        jL_infoReport.setIcon(null);
        jL_tPage.setToolTipText(null);
        jL_tPage.setIcon(null);
        jL_tPage.setText("Page Rows: "+ROWS_PAGE);
        
        
        this.setCursor(Cur_default);
        //jTable_Pivot.setCursor(Cur_default);
                              
        if(jR_TotalColumns.isSelected())
            setTotalColumns();
        
        datavalues = null;
    }
        
    
    private void deleteSelectionRows()
    {
        this.setCursor(Cur_wait);
        //jTable_Pivot.setCursor(Cur_wait);
        
        int[] RowsIndexSel =  jTable_Pivot.getSelectedRows();   
             
        Object[][] datavalues_SHOW = myTableModel.getDataValues();                
        int HIDE_ROWS_NOW   = RowsIndexSel.length;
        
        if(HIDE_ROWS_NOW > 0)
        {
            jMenu_ShowRow.setEnabled(true);
            jMenuRestore.setEnabled(true);
        }
        else
        {
            this.setCursor(Cur_default);
            //jTable_Pivot.setCursor(Cur_default);
            return;
        }
       
        
        int sizecolumn = NUM_COLUMN;
        for(int i=0; i<HIDE_ROWS_NOW; i++)
        {                  
            Object[] row = new Object[sizecolumn];  
            
            String PRINT_rowDelete="";
            int indexRow = -1;
            for(int x=0; x<sizecolumn;x++)
            {
                indexRow = sorter.modelIndex(RowsIndexSel[i]);
                //System.out.println("indexRow "+indexRow);
                
                if(datavalues_SHOW[indexRow][x] == null)
                    row[x] = null;
                else
                {
                    String nomeclasse = (datavalues_SHOW[indexRow][x].getClass().getName());

                    /*if( nomeclasse.equals("java.lang.String"))
                        row[x] = new String((String)datavalues_SHOW[indexRow][x]);
                    else if( nomeclasse.equals("java.lang.Float"))
                        row[x] = new Float( ((Float)datavalues_SHOW[indexRow][x]).floatValue());
                    else 
                        row[x] = new Integer(((Integer)datavalues_SHOW[indexRow][x]).intValue());*/
                    
                    if( nomeclasse.equals("java.lang.String"))
                        row[x] = new String((String)datavalues_SHOW[indexRow][x]);
                    else if( nomeclasse.equals("java.lang.Double"))
                        row[x] = new Double( ((Double)datavalues_SHOW[indexRow][x]).doubleValue());
                    else 
                        row[x] = new Long(((Long)datavalues_SHOW[indexRow][x]).longValue());
                }
                
                PRINT_rowDelete += ""+row[x]+" -- ";
                
            }  
            
            if(indexRow >= 0)
            {
                //System.out.println(indexRow +"delete "+PRINT_rowDelete);
                V_HideRow.addElement(row);
                datavalues_SHOW[indexRow]= null;      
            }
            else
                System.out.println("Errore indice jB_delRowsActionPerformed");
        }
        
        Object[][] datavalues = new Object[(datavalues_SHOW.length)-HIDE_ROWS_NOW][sizecolumn];            
        int conut = 0;
        for(int y=0; y<datavalues_SHOW.length; y++)
        {
            if(datavalues_SHOW[y] != null)
            {
                for(int z=0; z<datavalues_SHOW[y].length; z++)
                    datavalues[conut][z] = datavalues_SHOW[y][z];

                conut++;
            }
            datavalues_SHOW[y] = null;
        }     
        
        //System.out.println("delRows datavalues prima "+datavalues_SHOW.length);
        //System.out.println("delRows datavalues dopo "+datavalues.length);
        datavalues_SHOW = null;
        
        //System.out.println("delRows Num Rows Hide "+V_HideRow.size());
        
        int hideROWS = V_HideRow.size();
        
        jL_infoReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png")));
        jL_infoReport.setToolTipText("Report Rows: "+SERVER_TOT_ROWS_REQUEST+" (Hidden Rows:"+hideROWS+")");
        jL_tPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/info.png")));
        jL_tPage.setToolTipText("Page Rows: "+(ROWS_PAGE)+" (Hidden Rows:"+hideROWS+")");
        jL_tPage.setText("Page Rows: "+(ROWS_PAGE-hideROWS));
        
        myTableModel.setDataValues(datavalues);
        sorter.setTableModel(myTableModel);
        jScroll_Pivot.setViewportView(jTable_Pivot);
        jTable_Index.clearSelection();

        /*if(columnSort != -1)
            sorter.setSortingStatus(columnSort, sortingStatus);*/
        
        runGC(); 
        /*long maxmemory_1  = s_runtime.maxMemory();
        long usedmemory_1  = usedMemory();
        long freememory_1 = (maxmemory_1-usedmemory_1);        
        System.out.println("Memoria Disponibile request ------> "+freememory_1);*/
        
        this.setCursor(Cur_default);
        //jTable_Pivot.setCursor(Cur_default);
      
        if(jR_TotalColumns.isSelected())
            setTotalColumns();
    }
    
    public void reload_setTotalColumns()
    {
        if(jR_TotalColumns.isSelected())
        {
            setTotalColumns();
            //jP_totalTable.repaint();
        }
    }
       
    private void setTotalColumns()
    {
        
        if(ALL_ABSOLUTE_VALUES == false)
        {           
            staticLib.optionPanel("Page Total no possible with percentage values. ", "Information Message",javax.swing.JOptionPane.INFORMATION_MESSAGE);
            jR_TotalColumns.setSelected(false);
            return;
        } 
        
        
        jTable_Pivot.validate();
        jTable_Pivot.repaint();
        this.validate();
        this.repaint();
        
        int numRows = jTable_Pivot.getRowCount();        
        if(numRows == 0)
            return;

        //System.out.println(" setTotalColumns ");
        
         ///////////////////////// TOTAL
        
        jP_totalTable.removeAll();
        
        if(jScroll_Pivot.getVerticalScrollBar().isShowing())
            jP_totalTable.setBorder(new javax.swing.border.MatteBorder(new java.awt.Insets(2, 2, 2, jScroll_Pivot.getVerticalScrollBar().getWidth()), new java.awt.Color(230, 230, 230)));
               
        int column_width_INDEX = (jTable_Index.getColumnModel().getColumn(0)).getWidth();
        
        javax.swing.JLabel jL = new javax.swing.JLabel("TOTAL");
        jL.setForeground(Color.black);
        jL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL.setOpaque(true);
        jL.setMaximumSize(new java.awt.Dimension(column_width_INDEX, 20));
        jL.setMinimumSize(new java.awt.Dimension(column_width_INDEX, 20));
        jL.setPreferredSize(new java.awt.Dimension(column_width_INDEX, 20));
        jP_totalTable.add(jL);    
        
        for(int col=0; col<NUM_COLUMN; col++)
        {            
            int column_width = (jTable_Pivot.getColumnModel().getColumn(col)).getWidth();
            if(column_width > 0)
            {
                String ColumnName = jTable_Pivot.getColumnName(col); 
                //System.out.println("----- ColumnName ===> "+ColumnName);

                boolean isKEY = false;
                for(int k=0; k<numKeyRel_NAME.length; k++)
                {
                   // System.out.println("----- numKeyRel_NAME["+k+"] ===> "+numKeyRel_NAME[k]);

                    if(ColumnName.indexOf(numKeyRel_NAME[k]) != -1)
                    {                   
                        javax.swing.JLabel jLk = new javax.swing.JLabel();
                        jLk.setMaximumSize(new java.awt.Dimension(column_width, 20));
                        jLk.setMinimumSize(new java.awt.Dimension(column_width, 20));
                        jLk.setPreferredSize(new java.awt.Dimension(column_width, 20));

                        jP_totalTable.add(jLk);
                        jP_totalTable.validate();
                        jP_totalTable.repaint();
                        isKEY = true;
                        break;
                    }
                }

                if(isKEY)
                    continue;

                String Str_Value ="";  
                Object cell = jTable_Pivot.getValueAt(0,col);
                String nomeclasse = (cell.getClass().getName());

                if(nomeclasse.equals("java.lang.Double"))
                {    
                    double TOTAL_COLUM_F = 0;
                    for(int row=0; row<numRows; row++)
                        TOTAL_COLUM_F = ((Double)(jTable_Pivot.getValueAt(row,col))).doubleValue() + TOTAL_COLUM_F;

                    // Formattazione valore in Stringa                   
                    String strdecimal = "%d";
                    if(VALUE_DECIMAL > 0)
                        strdecimal  = "%0."+VALUE_DECIMAL+"f";
                    
                    Str_Value = Format.sprintf(strdecimal, new Parameters(TOTAL_COLUM_F));
                    
                }
                else if (nomeclasse.equals("java.lang.Long"))
                {
                    long TOTAL_COLUM_I = 0;
                    for(int row=0; row<numRows; row++)
                        TOTAL_COLUM_I = ((Long)(jTable_Pivot.getValueAt(row,col))).longValue() + TOTAL_COLUM_I;

                    Str_Value = ""+TOTAL_COLUM_I;
                }
                else
                {
                    System.out.println("Errore tipo Dato...");
                    return;
                }
                
                //Sostituzione del punto con la virgola... com in JTable                                
                Str_Value = Str_Value.replace('.',',');
                
                javax.swing.JLabel jL1 = new javax.swing.JLabel(Str_Value);            
                jL1.setBackground(Color.gray);
                jL1.setForeground(Color.black);
                jL1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                jL1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
                jL1.setOpaque(true);

                int col_width = jTable_Pivot.getColumnModel().getColumn(col).getWidth();
                jL1.setMinimumSize(new java.awt.Dimension(col_width, 20));
                jL1.setPreferredSize(new java.awt.Dimension(col_width, 20));
                jL1.setMaximumSize(new java.awt.Dimension(column_width, 20));

                jP_totalTable.add(jL1);
            }
        }
        jP_totalTable.validate();
        jP_totalTable.repaint();
        this.validate();
        this.repaint();  
    }
    
    
    private void setColumnSelection()
    {        
        jTable_Pivot.setColumnSelectionAllowed(true);
        jTable_Pivot.setRowSelectionAllowed(false);
        jMenu_HideRow.setEnabled(false);        
        
        int[] indexSelected = jTable_Pivot.getSelectedColumns();
        boolean enabledHideColumn = false;
        
        /*for(int i=0; i<indexSelected.length; i++)
        {            
            if(isColumnKey(indexSelected[i]) == false)
            {
                enabledHideColumn = true;
                break;
            }
        }   
        //jMenu_HideColumn.setEnabled(enabledHideColumn);*/
        jMenu_HideColumn.setEnabled(true);
        jM_drill_down.setEnabled(false);
        
    }
    
    private void setRowSelection()
    {
        jTable_Pivot.clearSelection();
        jTable_Pivot.setColumnSelectionAllowed(false);
        jTable_Pivot.setRowSelectionAllowed(true);
        jMenu_HideRow.setEnabled(true);
        jMenu_HideColumn.setEnabled(false);
        jM_drill_down.setEnabled(true);
    }
    
    private void setCellSelection()
    {
        jTable_Pivot.setColumnSelectionAllowed(true);
        jTable_Pivot.setRowSelectionAllowed(true);
        jMenu_HideRow.setEnabled(false);
        jMenu_HideColumn.setEnabled(false);
        jM_drill_down.setEnabled(false);
    }
    
    private void jTable_IndexMouseReleased(java.awt.event.MouseEvent evt) 
    {       
        if(jf_search != null)
        {
            if(jf_search.isShowing())
            {
                jf_search.setVisible(true);
                jf_search.toFront();
            }
        }
        
        setRowSelection();
        jMenu_jRb_selRow.setSelected(true);
        
        int[] rowsIndexSel = jTable_Index.getSelectedRows();

        try
        {
            if(rowsIndexSel.length > 0)
                jTable_Pivot.setRowSelectionInterval(rowsIndexSel[0],rowsIndexSel[0]);

            for(int i=1; i<rowsIndexSel.length; i++)
            {
                jTable_Pivot.addRowSelectionInterval(rowsIndexSel[i],rowsIndexSel[i]);
            }
        }
        catch (Exception e)
        {
            System.out.println("riga non presente!");
        }
    }
    
    private void jTable_PivotMouseReleased(java.awt.event.MouseEvent evt) 
    {        
        Object target = evt.getSource();
        int m = evt.getModifiers();

        if ((m & java.awt.event.InputEvent.BUTTON1_MASK) !=0) 
        {
            //System.out.println("jTable_PivotMouseReleased");
            jTable_Index.clearSelection();

            if(jf_search != null)
            {
                if(jf_search.isShowing())
                {
                    jf_search.setVisible(true);
                    jf_search.toFront();
                }
            }

            /*if(jMenu_jRb_selColumn.isSelected())
            {
                
                int[] indexSelected = jTable_Pivot.getSelectedColumns();
                boolean enabledHideColumn = false;

                for(int i=0; i<indexSelected.length; i++)
                {            
                    if(isColumnKey(indexSelected[i]) == false)
                    {
                        enabledHideColumn = true;
                        break;
                    }
                } 
                jMenu_HideColumn.setEnabled(enabledHideColumn);
            }*/

        }
        else if ((m & java.awt.event.InputEvent.BUTTON3_MASK)!=0)
        {
            //System.out.println("a");
            
            if((jTable_Pivot.getSelectedColumns().length == 1)&&(jTable_Pivot.getCellSelectionEnabled()))
                jMenuFocus.setEnabled(true);
            else
                jMenuFocus.setEnabled(false);
            
            jPopMenu.show((java.awt.Component)evt.getSource(),(evt.getX()),evt.getY());
        }        
    }
    
    /*private boolean isSelectedPresentColumnNoHide()
    {
        int[] ColumnsIndexSel =  jTable_Pivot.getSelectedColumns();
        
        for(int i=0; i<ColumnsIndexSel.length; i++)
        {            
            Class columnType = jTable_Pivot.getModel().getColumnClass(ColumnsIndexSel[i]);
            
            if(columnType == null) // aggiunto per Exception
                return false;
            else if(columnType.equals(new String().getClass()))            
                return true;
        }
        return false;
    }*/
    
    private void verticalScrollBarAdjustmentListener(java.awt.event.AdjustmentEvent evt)
    {
        //System.out.println("verticalScrollBarAdjustmentListener");
        javax.swing.JScrollBar v_bar_index =jScroll_IDPivot.getVerticalScrollBar();
        v_bar_index.setValue(jScroll_Pivot.getVerticalScrollBar().getValue());
    }
    
    private void Init_Read_ADD_Data()
    {
        TH_EXIT = false;
        OperationTH = 0;
        th = null;
        th = new Thread(this,"Init_Read_ADD_Data()");
        th.start();
    }
    
    private void Read_ADD_Data()
    {
        TH_EXIT = false;
        OperationTH = 1;
        th = null;
        th = new Thread(this,"Read_ADD_Data()");
        th.start();
    }
    
       
    private void freeMemory()
    {    
        long maxmemory1  = s_runtime.maxMemory();
        long usedmemory1  = usedMemory();
        long freemem_calc = (maxmemory1-usedmemory1);             
        //System.out.println("PRIMA freeMemory------> MEMORIA DISPONIBILE = "+freemem_calc);

        V_HideColumn.removeAllElements();
        V_HideRow.removeAllElements();          
        jTable_Index.removeAll();
        jTable_Pivot.removeAll();

        myTableModel.setDataValues(null);
        sorter.setTableModel(null);
        indexJTableModel_general.setNumRows(0);

        runGC();  

        long maxmemory2  = s_runtime.maxMemory();
        long usedmemory2  = usedMemory();
        long freemem_calc2 = (maxmemory2-usedmemory2);             
        //System.out.println("DOPO freeMemory -----> MEMORIA DISPONIBILE  = "+freemem_calc2);
    }
    
    
    public void run()
    {
        jCBox_Pages.setEnabled(false);
        if(OperationTH == 0) // Init_Read_ADD_Data
        {  
            bar_Value = 0;
            jBarPage.setForeground(java.awt.Color.blue); 
            if( init_CalculateTable() != -1)
                AddRowJTabel_general(jTable_Pivot,SHOW_PAGE);
        }           
        else if(OperationTH == 1) // Read_ADD_Data
        {  
            bar_Value = 0;
            jBarPage.setForeground(java.awt.Color.blue);
            
            ///////////////////////// libero Memoria
            jMenu_ShowColumn.setEnabled(false);
            jMenu_ShowRow.setEnabled(false);
            
            jMenuRestore.setEnabled(false);
            
            jR_TotalColumns.setSelected(false);
            
            jP_totalTable.removeAll();
            jP_totalTable.validate();
            jP_totalTable.repaint();
            
            freeMemory();
            //////////////////////////
                     
            AddRowJTabel_general(jTable_Pivot,SHOW_PAGE);
            jTable_Pivot.setVisible(false);
            Init_NameColumn();
            jTable_Pivot.setVisible(true);

            jR_TotalColumns.setSelected(false);          
        } 
        else if(OperationTH == 2) //destroyPivot()
        {            
            if(jf_search != null)
            {
                jf_search.dispose();
                jf_search = null;
            }
            
            if(jf_MathExp != null)
            {
                jf_MathExp.dispose();
                jf_MathExp = null;
            }    
            this.setVisible(false);
            freeMemory();
            
            this.dispose();
            //System.out.println("FINE --------- DESTROY PIVOT");
        }    
        
        jCBox_Pages.setEnabled(true);
        OperationTH = -1;
    }
    
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
       destroyPivot();
    }//GEN-LAST:event_exitForm

    
    public void destroyPivot()
    {       
        //System.out.println("INIZIO --------- DESTROY PIVOT");
        
        TH_EXIT = true;     
        //th.stop();
        try
        {
        	th.interrupt();
        }
        catch (Exception e)
        {
        	System.out.println("err. th interrupt MDM_JF_Pivot.java ");
        	}
                
        OperationTH = 2;
        th = null;
        th = new Thread(this,"destroyPivot()");
        th.start();
    }
    
    
    private void setCenteredFrame(int width,int height)
    {
        java.awt.Toolkit kit = java.awt.Toolkit.getDefaultToolkit();
        java.awt.Dimension screenSize = kit.getScreenSize();        
        int screenWCenter = screenSize.width/2;
        int screenHCenter = screenSize.height/2;        
        this.setSize(width,height);
        this.setLocation(screenWCenter-(width/2),screenHCenter-(height/2));  
    }
    
    
//////////////////////////////////////////// Internal CLASS ////////////////////////////////////////////    
    class MyTableModel extends AbstractTableModel 
    {
        private String[] columnNames = { "Name Column 1",
                                         "Name Column 2",
                                         "Name Column 3"};
        
        MyTableModel()
        {            
        }
        MyTableModel(String[] colNames)
        {
            columnNames = colNames;            
        }

        private Object[][] data = {};   
        
        public int getColumnCount() {
            return columnNames.length;
        }
        public int getRowCount() 
        {
            return data.length;
        }    
        public String getColumnName(int col) 
        {
            return columnNames[col];
        }
        
        public int getIDColumnName(String NameColumn)
        {
            for(int i=0; i<columnNames.length; i++)
            {
                if(columnNames[i].compareTo(NameColumn) == 0)
                    return i;
            }
            return -1;
        }
        
        
        public java.lang.Object getValueAt(int row, int col) 
        {
            return data[row][col];
        }
        public void setDataValues(Object[][] datavalues)
        {
            data = datavalues;
        }
        
        public Object[][] getDataValues()
        {
            return data;
        }
        
        
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) 
        {
            Class classdefault = (new String()).getClass();
            try
            {     
                //Class classok = getValueAt(0, c).getClass();
                //if(classok != null)
                return getValueAt(0, c).getClass();
            }
            catch (Exception e)
            {
               //System.out.println("--------------- catch (Exception e) "+e);               
            }
            
            return classdefault;
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) 
        {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            /*if (col < 2) {
                return false;
            } else {
                return true;
            }*/
            return false; //nessuna cella editabile
        }
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) 
        {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
        private void printDebugData() 
        {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
//////////////////////////////////////////// END Internal CLASS ////////////////////////////////////////////  
    
    
// -------------------- Size --------------------- 
    private final Runtime s_runtime = Runtime.getRuntime();
        
    
    private int sizeOfJTable(int righe,int colonne,int numKey)
    {            
        
        //System.out.println("sizeOfJTable "+righe+" "+colonne+" "+numKey);        
        runGC();
        
        // Array to keep strong references to allocated objects
        final int count = 1;
        PIVOTDATANODE[] test_pivotData = null;
        Object [] objects = new Object [count];
        
        long heap1 = 0;

        // Alloco tante tabelle "objAllocated" con ognuna con num="righe"
        for (int i = -1; i < count; ++ i)
        {            
                        
            String[] columnNames_test = new String[colonne];
            for(int c=0; c<colonne;c++)
                columnNames_test[c] = new String();
            
            Object[][] objRows_test = new Object[righe][colonne];
            for(int x=0; x<righe; x++)
            {
                for(int y=0; y<colonne; y++)
                {
                    if(y < numKey)
                    {
                        objRows_test[x][y] = new String("prova test sizerows");
                    }
                    else
                        objRows_test[x][y] = new Double(1.0); //new Float(1.0);
                }
            }
            
            MyTableModel myTableModel_test  = new MyTableModel(columnNames_test); 
            TableSorter sorter_test         = new TableSorter(myTableModel_test);
            
            myTableModel_test.setDataValues(objRows_test);
            sorter_test.setTableModel(myTableModel_test);

            Object object = new javax.swing.JTable(sorter_test);
            // Instantiate your data here and assign it to object
            
            if (i >= 0)
                objects [i] = object;
            else
            {
                object = null; // Discard the warm up object
                runGC();
                heap1 = usedMemory (); // Take a before heap snapshot 
            }
        }
        test_pivotData = staticLib.CORBA.getPivotData(ARR_KEY_ENABLED,DEFAULT_URL,0,righe,indexCounterUserSel,-1,this.decreasing,STR_REPORT_FOCUS,-1,false);
        
        //if(test_pivotData.length > 0)
            //System.out.println("Test ---> test_pivotData.length ---> "+test_pivotData.length);
        
        runGC();
        long heap2 = usedMemory (); // Take an after heap snapshot:
                
        final int size = Math.round (((float)(heap2 - heap1))/count);
        
        /*System.out.println ("'before' heap: " + heap1 +
                            ", 'after' heap: " + heap2);
        System.out.println ("heap delta: " + (heap2 - heap1) +
            ", {" + objects [0].getClass () + "} size = " + size + " bytes");
        System.out.println();*/
        
        
        for (int i = 0; i < count; ++ i) 
            objects [i] = null;
        objects = null;
            
        for (int x = 0; x < test_pivotData.length; ++ x) 
            test_pivotData[x] = null;
        test_pivotData = null;
                       
        runGC();
        
        return size;
    }
    
    private void runGC() 
    {
        // It helps to call Runtime.gc()
        // using several method calls:
        for (int r = 0; r < 6; ++ r) _runGC();
    }

    private void _runGC() 
    {
        long usedMem1 = usedMemory (), usedMem2 = Long.MAX_VALUE;
        for (int i = 0; (usedMem1 < usedMem2) && (i < 500); ++ i)
        {
            s_runtime.runFinalization ();
            s_runtime.gc ();
            Thread.currentThread ().yield ();
            
            usedMem2 = usedMem1;
            usedMem1 = usedMemory ();
        }
    }

    private long usedMemory ()
    {
        return s_runtime.totalMemory () - s_runtime.freeMemory ();
    }
    
// -------------------- Size ---------------------     
      
    private class PrecisionCellRenderer extends DefaultTableCellRenderer 
    {
        private java.text.NumberFormat format;
        int DecimaPrecision;
     
        PrecisionCellRenderer(int precision) 
        {          
            DecimaPrecision = precision;
        }
        
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) 
        {            
            java.awt.Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column); 
            
            if(value == null)
                return c;
            
            javax.swing.JLabel l = (javax.swing.JLabel) c;
            /*if( value instanceof Float )
            {
                value = new Float( (float)(Math.round(((Float)value).floatValue()*calc_potenza))/calc_potenza );
            }*/
            
            if( value instanceof Double )
            {           
                //System.out.println("origina double Value ---> "+((Double)value).doubleValue() );
           
                String strdecimal = "%d";
                if(DecimaPrecision > 0)
                    strdecimal  = "%0."+DecimaPrecision+"f";
                
                //System.out.println("-- DecimaPrecision ="+DecimaPrecision);
                //System.out.println("-- strdecimal ="+strdecimal);
                
                String str = Format.sprintf(strdecimal, new Parameters((((Double)value).doubleValue())) );
                l.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
                l.setText(str);
                
                //value = new Double( (double)(Math.round(((Double)value).doubleValue()*calc_potenza))/calc_potenza );                
            }            
            else if( value instanceof String )          
            {
                l.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                l.setText(value.toString());
            }
            else
            {
                l.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);                
                String str_value = value.toString().replace('.',',');
                l.setText(str_value.toString());
            }                      
            l.setFont(fontDefaultPivot);
            
            //System.out.println(value.toString());
            return c;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopMenu;
    private javax.swing.JRadioButtonMenuItem jMenu_jRb_selColumn;
    private javax.swing.JRadioButtonMenuItem jMenu_jRb_selRow;
    private javax.swing.JRadioButtonMenuItem jMenu_jRb_selCell;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem jMenuFocus;
    private javax.swing.JMenuItem jMenuRestore;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenuItem jMenu_HideColumn;
    private javax.swing.JMenuItem jMenu_ShowColumn;
    private javax.swing.JMenuItem jMenu_HideRow;
    private javax.swing.JMenuItem jMenu_ShowRow;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JMenuItem jMen_font;
    private javax.swing.JMenu jM_setColor;
    private javax.swing.JMenuItem jMenuForeground;
    private javax.swing.JMenuItem jMenuBackground;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JMenuItem jM_drill_down;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel jP_TOT;
    private javax.swing.JPanel jP_Control;
    private javax.swing.JPanel jP_Align;
    private javax.swing.JComboBox jCBox_align;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jB_Expression;
    private javax.swing.JButton jB_search;
    private javax.swing.JRadioButton jR_TotalColumns;
    private javax.swing.JButton jB_option;
    private javax.swing.JPanel jP_page;
    private javax.swing.JComboBox jCBox_Pages;
    private javax.swing.JLabel jL_infoReport;
    private javax.swing.JProgressBar jBarPage;
    private javax.swing.JLabel jL_tPage;
    private javax.swing.JButton jB_ReloadPage;
    private javax.swing.JPanel jP_GlobalPivot;
    private javax.swing.JScrollPane jScroll_Pivot;
    private javax.swing.JScrollPane jScroll_IDPivot;
    private javax.swing.JPanel jP_totalTable;
    private javax.swing.JPanel jP_north;
    private javax.swing.JLabel jL_title;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jB_print;
    private javax.swing.JButton jB_csv;
    private javax.swing.JButton jB_pdf;
    private javax.swing.JButton jB_excel;
    // End of variables declaration//GEN-END:variables
    
    private javax.swing.JTable jTable_Index = null;
    private java.awt.Color colorDefault = new java.awt.Color(230,230,230);
    private javax.swing.table.DefaultTableModel indexJTableModel_general = null;
       
    private String[] INIT_columnNames = null;
    private boolean DEBUG = false;
    private javax.swing.JTable jTable_Pivot = null;
    private TableSorter sorter = null;
    private MyTableModel myTableModel = null;    
    private int minCellDimension = 20;
    private int[] CellDimension  = null;
    private int NUM_COLUMN = 0;
    
    private Cursor Cur_default  = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor Cur_wait     = new Cursor(Cursor.WAIT_CURSOR);
    private Cursor Cur_hand     = new Cursor(Cursor.HAND_CURSOR);
    
    private String DEFAULT_URL = null;
    
    private String numKeyRel_NAME[] = null;
    private String nameCounter[]    = null;
    private int numKeyRel   = 0;
    private int numCounters = 0;
    private int[] indexCounterUserSel   = null;

    private int PAGES_START_END[][] = null;
    private final int START_ROW     = 0;
    private final int END_ROW       = 1;
    private final int REQUEST_PAG   = 2;
    
    private int MAX_PAGES = 1;
    private int SHOW_PAGE = 0;
    private int SERVER_TOT_ROWS_REQUEST = 0;
    private String strPage = "Page";
    private int bar_Value = 0;
    
    private Thread th        = null;
    private boolean TH_EXIT = false;
    private int OperationTH   = -1;
        
    private Vector V_HideColumn = null;
    private Vector V_HideRow    = null;
       

    private JF_Search jf_search = null;
    
    //int countAlign   = JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS;
    private String align_label[] = {"Resize OFF",
                            "Resize Next COLUMN",
                            "Resize Sub Sequent COLUMNS",
                            "Resize Last COLUMN",
                            "Resize All COLUMNS"};
                            
    private String alphabet[] = {"A","B","C","D","E","F","G","H","I",
                            "J","K","L","M","N","O","P","Q","R",
                            "S","T","U","V","W","X","Y","Z"};

    // ATTENZIONE DICHIARARLE ANCHE IN jP_ExpressionParser.java
    private String InitTagHTMLColumn = "<html><p align=center><b><font color=#FFFFFF>";
    private String TAG2 = "</font></p></b><p align=center><font face=\"Arial\" style=\"font-size:11pt\">";
    private String TAG3 = "</font></p></html>";    
    private String TAG_Name_COLUMN_MATH = "COMPUTED";
    // ATTENZIONE DICHIARARLE ANCHE IN jP_ExpressionParser.java
    
    private int ID_Order = -1;
    private boolean decreasing = false;
    private String STR_REPORT_FOCUS = "";
    
    private JF_MathExpression jf_MathExp;
    private int OLD_PAGE = -1;
    private int ROWS_PAGE = 0;
    
    private boolean ALL_ABSOLUTE_VALUES;
    private int ELEMENT_SORT = -1;
    //Indice e Desc colonne stato iniziale: nel causo si usi la struttura interna della tabella (dataValues..) e si effettuano spostamenti di colonna. 
    String Arr_INDEX_Column_ORIGIN[];
    
    private javax.swing.JApplet JAppl;
    private int VALUE_DECIMAL = 2;
    
    private TableCellRenderer renderer;
    
    private Color colorBackPivot = Color.white;
    private Color colorForePivot = Color.black;
    private java.awt.Font fontDefaultPivot = new java.awt.Font("Verdana", 0, 10);
    
    private boolean[] ARR_KEY_ENABLED       = new boolean[staticLib.MAX_PIVOT_KEYS];
    private String[] ARR_KEY_ENABLED_NAME   = new String[staticLib.MAX_PIVOT_KEYS];
    private int NUM_KEY_ENABLED             = 0;
    private int CustomPageRows              = -1;
    
    private String nameReportKey = null;
    private getConfigFiltro configuration;
    private Analisi localAnalisi;
}
   
