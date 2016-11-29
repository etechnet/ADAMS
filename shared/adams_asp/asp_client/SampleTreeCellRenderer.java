import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SampleTreeCellRenderer extends JLabel implements TreeCellRenderer
{
    /** Font used if the string to be displayed isn't a font. */
    static protected Font             defaultFont;
        /** FontSel used if the string to be displayed isn't a font. */
    static protected Font             defaultFontSel;
    /** Icon to use when the item is collapsed. */
     protected ImageIcon        collapsedIcon=new ImageIcon(getClass().getResource("/images/report.png"));
    /** Icon to use when the item is expanded. */
     protected ImageIcon        expandedIcon=new ImageIcon(getClass().getResource("/images/report.png"));
        /** Icon to use when the item is leaf. */
     protected ImageIcon        leafIcon=new ImageIcon(getClass().getResource("/images/report.png"));
        /** Icon to use when the item is selected. */
     protected ImageIcon        selIcon=new ImageIcon(getClass().getResource("/images/report.png"));

    /** Color to use for the background when selected. */
    static protected final Color SelectedBackgroundColor = Color.lightGray;//new Color(0, 0, 128);

    static
    {
        try {
            defaultFont=new Font ("Helvetica",1, 10);
                defaultFontSel=new Font ("Helvetica",1, 12);
               /*
                collapsedIcon   = new ImageIcon(getClass().getResource("/images/tab.gif"));
            	expandedIcon    = new ImageIcon(getClass().getResource("/images/tab1.gif"));
                leafIcon        = new ImageIcon(getClass().getResource("/images/1847.gif"));
                selIcon         = new ImageIcon(getClass().getResource("/images/1849.gif"));
				*/
        } catch (Exception e) {
            ////System.out.println("Couldn't load images: " + e);
        }
    }

    /** Whether or not the item that was last configured is selected. */
    protected boolean            selected;

    /**
      * This is messaged from JTree whenever it needs to get the size
      * of the component or it wants to draw it.
      * This attempts to set the font based on value, which will be
      * a TreeNode.
      */
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                          boolean selected, boolean expanded,
                                          boolean leaf, int row,
                                                  boolean hasFocus) {
        String          stringValue = tree.convertValueToText(value, selected,
                                           expanded, leaf, row, hasFocus);


        
        setToolTipText("  "+stringValue+"  ");
        
        /** Get nodo <nella forma di SampleData> */
        SampleData userObject = (SampleData)((DefaultMutableTreeNode)value).getUserObject();
        
		/* Set the image. */
        if (expanded)
        {
                if(userObject.expandedIcon!=null)
                        setIcon(userObject.expandedIcon);
                else
                    setIcon(expandedIcon);
        }
        else if (!leaf)
        {
                if(userObject.collapsedIcon!=null)
                        setIcon(userObject.collapsedIcon);
                else
                    setIcon(collapsedIcon);
        }
        else
        {
                if (hasFocus)
                {
                        if(userObject.selIcon!=null)
                                setIcon(userObject.selIcon);
                        else
                        setIcon(selIcon);       
                }
                else
                {
                        if (userObject.leafIcon!=null)
                                setIcon(userObject.leafIcon);
                        else
                            setIcon(leafIcon);
                }       
        }
        
                                                                        
        if (hasFocus)
        {
                setForeground(userObject.colorSel);
            setFont(userObject.fontSel);
                setText(stringValue);
        }       
        else
        {
                setForeground(userObject.color);
            setFont(userObject.font);   
                setText(stringValue+"            ");
        }       

        this.selected = selected;
        
        
        return this;
    }

}
