/*
 * TText.java
 *
 * Created on 17 gennaio 2006, 15.32
 */

/**
 *
 * @author  Raffo rficcad@tin.it
 * @version 
 */

import java.awt.Cursor;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.table.*;
import java.awt.event.InputEvent;
import java.awt.Component;
import javax.swing.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.awt.Toolkit;


public class TText implements Transferable 
{
	protected static DataFlavor[]  supported =
		{ DataFlavor.stringFlavor };
	protected String theText;		

	TText(String text) { theText = text; }

	public java.awt.datatransfer.DataFlavor[] getTransferDataFlavors()
		{ return supported; }
	public boolean isDataFlavorSupported(DataFlavor f)
		{ return (f == DataFlavor.stringFlavor); }
	public Object getTransferData(DataFlavor f)
	{
		if (f.equals(DataFlavor.stringFlavor))
			return theText;
		else
			return null;
	}
        
        /*public java.lang.Object getTransferData(java.awt.datatransfer.DataFlavor dataFlavor) throws java.awt.datatransfer.UnsupportedFlavorException, java.io.IOException {
        }
        
        public boolean isDataFlavorSupported(java.awt.datatransfer.DataFlavor dataFlavor) {
        }
        */
    }
