import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
  * @version 1.3 04/23/99
  * @author Scott Violet
  */

public class SampleData extends Object
{
	/** Font used for drawing. */
    protected Font          font;

    /** Color used for text. */
    protected Color         color;

    /** Value to display. */
    protected String        string;
        
        
    /** Font used for drawing. */
    protected Font          fontSel;

    /** Color used for text. */
    protected Color         colorSel;

    /** Value to display. */
    protected String        stringSel;

        /** Image icon */
        protected ImageIcon             iconSel;
        
        /** Icon to use when the item is collapsed. */
    protected ImageIcon        collapsedIcon;
    /** Icon to use when the item is expanded. */
    protected ImageIcon        expandedIcon;
        /** Icon to use when the item is leaf. */
        protected ImageIcon        leafIcon;
        /** Icon to use when the item is selected. */
        protected ImageIcon        selIcon;
        
        protected int id;
        
        
    /**
      * Constructs a new instance of SampleData with the passed in
      * arguments.
      */
    public SampleData(Font newFont, Color newColor, String newString) 
        {
                font = newFont;
                color = newColor;
                string = newString;
                
                fontSel=font;
                colorSel=color;
                stringSel=string;
                
                collapsedIcon=null;
                expandedIcon=null;
                leafIcon=null;
                
    }
        
        
        public SampleData(int id,Font newFont, Color newColor, String newString,
                                                Font newFontSel, Color newColorSel, String newStringSel) 
        {
                font = newFont;
                color = newColor;
                string = newString;
                
                fontSel=newFontSel;
                colorSel=newColorSel;
                stringSel=newStringSel;
                
                collapsedIcon=null;
                expandedIcon=null;
                leafIcon=null;
                this.id=id;
    }
        
        
        /**
      * Sets the icon that is used to represent this object.
      */
    public void setIcon(ImageIcon collapsedIcon,ImageIcon expandedIcon,ImageIcon leafIcon,ImageIcon selIcon) 
        {
                this.collapsedIcon=collapsedIcon;
                this.expandedIcon=expandedIcon;
                this.leafIcon=leafIcon;
                this.selIcon=selIcon;
    }

    

    /**
      * Sets the font that is used to represent this object.
      */
    public void setFont(Font newFont) {
        font = newFont;
    }

    /**
      * Returns the Font used to represent this object.
      */
    public Font getFont() {
        return font;
    }
    
    public int getID() {
        return id;
    }

    /**
      * Sets the color used to draw the text.
      */
    public void setColor(Color newColor) {
        color = newColor;
    }

    /**
      * Returns the color used to draw the text.
      */
    public Color getColor() {
        return color;
    }

    /**
      * Sets the string to display for this object.
      */
    public void setString(String newString) {
        string = newString;
    }

    /**
      * Returnes the string to display for this object.
      */
    public String string() {
        return string;
    }

    public String toString() {
        return string;
    }
    
}
