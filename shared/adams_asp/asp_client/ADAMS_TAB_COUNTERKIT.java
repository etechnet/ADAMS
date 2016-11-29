/*
 * ADAMS_TAB_COUNTERKIT.java
 *
 * Created on 5 ottobre 2005, 12.13
 */

/**
 *
 * @author  luca
 * @version 
 */
public class ADAMS_TAB_COUNTERKIT {

    /** Creates new ADAMS_TAB_COUNTERKIT */
    public ADAMS_TAB_COUNTERKIT() {
        
        TRIGGERFIELD    = -1;           //NUMBER
        TRIGGERVALUE    = -1;           //NUMBER
        COUNTERINDEX    = -1;           //NUMBER
        COUNTERTYPE     = -1;           //NUMBER
        PERCENTOF       = -1;           //NUMBER
        TRIGGERCOUNTER  = -1;           //NUMBER
        TAG             = new String(); //VARCHAR2(20)
        DESCRIPTION     = new String(); //VARCHAR2(160)
        index=0;
    }


 public int TRIGGERFIELD;       //NUMBER
 public int TRIGGERVALUE;       //NUMBER
 public int COUNTERINDEX;       //NUMBER
 public int COUNTERTYPE;        //NUMBER
 public int PERCENTOF;          //NUMBER
 public int TRIGGERCOUNTER;     //NUMBER
 public String TAG;             //VARCHAR2(20)
 public String DESCRIPTION;     //VARCHAR2(160)
 public int index;

}
