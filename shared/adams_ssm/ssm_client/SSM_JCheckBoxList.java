
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/*
#
#                $$$$$$$$                   $$
#                   $$                      $$
#  $$$$$$           $$   $$$$$$    $$$$$$$  $$$$$$$
# $$    $$  $$$$$$  $$  $$    $$  $$        $$    $$
# $$$$$$$$          $$  $$$$$$$$  $$        $$    $$
# $$                $$  $$        $$        $$    $$
#  $$$$$$$          $$   $$$$$$$   $$$$$$$  $$    $$
#
#  MODULE DESCRIPTION:
#  <enter module description here>
#
#  AUTHORS:
#  Luca Beltrame <luca.beltrame@e-tech.net>                                                                                                           
#                                                                                                                                                 
#  LICENSE: See "Licensing/License.txt" under ADAMS top-level source directory                                                                    
#                                                                                                                                                 
#  HISTORY:                                                                                                                                       
#  -[Date]- -[Who]------------- -[What]---------------------------------------
#  00.00.00 Author Name         Creation date
#--
#
*/
    public class SSM_JCheckBoxList extends JList
    {
        protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

        public SSM_JCheckBoxList()
        {
            super();
            setModel(new DefaultListModel());
            this.setCellRenderer(new CellRenderer());

            addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseReleased(MouseEvent e) 
                {
                    if( isEnabled() )
                    {
                        int index = locationToIndex(e.getPoint());

                        if (index != -1) 
                        {
                            Object obj = getModel().getElementAt(index);
                            if (obj instanceof JCheckBox) 
                            {
                                JCheckBox checkbox = (JCheckBox) obj;
                                checkbox.setSelected(!checkbox.isSelected());
                                repaint();
                            }   
                        }
                    }
                }
            });
            

          setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       }      
        
        
        
        @SuppressWarnings("unchecked")
        public int[] getCheckedIdexes() 
        {
            java.util.List list = new java.util.ArrayList();
            ListModel dlm = (ListModel)getModel();
            for (int i=0; i<dlm.getSize();i++)
            {
                Object obj = getModel().getElementAt(i);
                if (obj instanceof JCheckBox) 
                {
                    JCheckBox checkbox = (JCheckBox) obj;
                    if (checkbox.isSelected()) 
                    {
                        list.add(new Integer(i));
                    }
                }
            }

            int[] indexes = new int[list.size()];
            for (int i = 0; i < list.size(); i++) 
            {
                indexes[i] = ((Integer) list.get(i)).intValue();
            }

          return indexes;
        }

        @SuppressWarnings("unchecked")
        public java.util.List getCheckedItems(boolean selected) 
        {
            java.util.List list = new java.util.ArrayList();
            ListModel dlm = (ListModel)getModel();
            for (int i=0; i<dlm.getSize(); i++) 
            {
                Object obj = getModel().getElementAt(i);
                if (obj instanceof JCheckBox) 
                {
                    JCheckBox checkbox = (JCheckBox) obj;
                    if (checkbox.isSelected() == selected)
                    {
                        list.add(checkbox);
                    }
                }
            }
            return list;
        }
    
    protected class CellRenderer implements ListCellRenderer
    {
        public Component getListCellRendererComponent(JList list, Object value, int index,boolean isSelected, boolean cellHasFocus)
        {
            JCheckBox checkbox = (JCheckBox) value;
            checkbox.setBackground(isSelected ? getSelectionBackground() : getBackground());
            checkbox.setForeground(isSelected ? getSelectionForeground() : getForeground());
            
            checkbox.setEnabled(isEnabled());
            checkbox.setFont(getFont());
            checkbox.setFocusPainted(false);
            checkbox.setBorderPainted(true);
            checkbox.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
            return checkbox;
        }
    }
}
