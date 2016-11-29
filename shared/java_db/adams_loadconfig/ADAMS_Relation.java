package net.etech.loadconfig;

/*
 * ADAMS_Relation.java
 *
 * Created on 13 dicembre 2005, 13.58
 */


/**
 *
 * @author  Raffo
 * @version
 */

import java.util.Vector;
import java.sql.*;
import java.util.StringTokenizer;
import net.etech.*;
import net.etech.MDM.*;

public class ADAMS_Relation extends Vector
{
    private int ID_REL_COUNT = 1;
    private int ID_FIRST_PAR;
    private int ID_SECOND_PAR;

    /** Creates new ADAMS_Relation */

    public ADAMS_Relation()
    {
    }

    public void addRelation(ADAMS_TAB_RELAZIONI_ELEMENTO REL_ELEM,Vector V_ALL_LISTA_RELAZIONI,boolean rel_GHOST)
    {
        /*for(int i=0; i<V_ALL_LISTA_RELAZIONI.size(); i++)
        {
            ADAMS_TAB_RELAZIONI_ELEMENTO appoRel = (ADAMS_TAB_RELAZIONI_ELEMENTO)V_ALL_LISTA_RELAZIONI.elementAt(i);
            System.out.println("Exists "+appoRel.RELATION_NAME);
        }*/


        if(rel_GHOST == true)
        {
            ADAMS_TAB_RELAZIONI_ELEMENTO obj_rel_ghost = isPresentRelation(REL_ELEM.RELATION_NAME,this);
            if(obj_rel_ghost == null)
            {
                obj_rel_ghost = new ADAMS_TAB_RELAZIONI_ELEMENTO("");
                copy_ADAMS_TAB_RELAZIONI_ELEMENTO_to(REL_ELEM,obj_rel_ghost);

                obj_rel_ghost.idRelation = ID_REL_COUNT;
                ID_REL_COUNT++;

                obj_rel_ghost.idFirstElement     = 0;
                obj_rel_ghost.idSecondElement    = 0;
                obj_rel_ghost.idParentRelation   = 0;

                this.addElement(obj_rel_ghost);
            }
            return;
        }

        while(true)
        {
            StringTokenizer STKrelationName = new StringTokenizer(REL_ELEM.RELATION_NAME,"::");

            int El1 = new Integer(STKrelationName.nextToken()).intValue();
            int El2 = new Integer(STKrelationName.nextToken()).intValue();

            String relationBase = ""+El1+"::"+El2;
            int ID_RELATION_BASE;

            ADAMS_TAB_RELAZIONI_ELEMENTO obj_rel = isPresentRelation(relationBase,this);

            if(obj_rel == null)
            {
                obj_rel = new ADAMS_TAB_RELAZIONI_ELEMENTO("");
                copy_ADAMS_TAB_RELAZIONI_ELEMENTO_to(REL_ELEM,obj_rel);

                ID_RELATION_BASE = obj_rel.idRelation = ID_REL_COUNT;
                ID_REL_COUNT++;

                obj_rel.idFirstElement     = El1;
                obj_rel.idSecondElement    = El2;
                obj_rel.idParentRelation   = 0;

                this.addElement(obj_rel);
            }
            else
            {
                while(STKrelationName.hasMoreTokens())
                {
                    boolean newEl_n = false;
                    int El_n = new Integer(STKrelationName.nextToken()).intValue();
                    relationBase = relationBase+"::"+El_n;

                    ADAMS_TAB_RELAZIONI_ELEMENTO obj_rel_appo = null;
                    obj_rel_appo = isPresentRelation(relationBase,this);
                    if(obj_rel_appo == null)
                    {
                        obj_rel_appo = isPresentRelation(relationBase,V_ALL_LISTA_RELAZIONI);
                        newEl_n = true;
                    }

                    if(obj_rel_appo != null)
                    {
                        //System.out.println("Trovato "+obj_rel_appo.RELATION_NAME);

                        ADAMS_TAB_RELAZIONI_ELEMENTO obj_rel_n;
                        if(newEl_n)
                        {
                            obj_rel_n = new ADAMS_TAB_RELAZIONI_ELEMENTO("");
                            copy_ADAMS_TAB_RELAZIONI_ELEMENTO_to(obj_rel_appo,obj_rel_n);

                            obj_rel_n.idRelation = ID_REL_COUNT;
                            obj_rel_n.idFirstElement    = El_n;
                            obj_rel_n.idSecondElement   = 0;
                            obj_rel_n.idParentRelation  = obj_rel.idRelation;
                            this.addElement(obj_rel_n);

                            ID_REL_COUNT++;
                        }
                        else
                            obj_rel_n = obj_rel_appo;

                        //NextLevel Padre
                        for(int i=0; i<obj_rel.nextLevelRelations.length; i++)
                        {
                            if(obj_rel.nextLevelRelations[i] == obj_rel_n.idRelation) // già inserito
                                break;

                            if (obj_rel.nextLevelRelations[i] == 0)
                            {
                                obj_rel.nextLevelRelations[i] = obj_rel_n.idRelation;
                                break;
                            }
                        }

                        obj_rel = obj_rel_n;
                    }
                    //else
                        //System.out.println("NON Trovato "+obj_rel_appo.RELATION_NAME);

                    obj_rel = isPresentRelation(relationBase,this);

                }
                //System.out.println("Finito Relazione "+REL_ELEM.RELATION_NAME);
                break;
            }
        }

    }

    private void copy_ADAMS_TAB_RELAZIONI_ELEMENTO_to(ADAMS_TAB_RELAZIONI_ELEMENTO RE_original,ADAMS_TAB_RELAZIONI_ELEMENTO RE_copy)
    {
        RE_copy.RELATION_NAME = RE_original.RELATION_NAME;
        RE_copy.DIREZIONE   = RE_original.DIREZIONE;
        RE_copy.HEXOUTPUT   = RE_original.HEXOUTPUT;
        RE_copy.NETWORK     = RE_original.NETWORK;
        RE_copy.GHOST       = RE_original.GHOST;
        RE_copy.V_LISTA_ANALISI     = RE_original.V_LISTA_ANALISI;
        RE_copy.V_RESTRIZIONI       = RE_original.V_RESTRIZIONI;
        RE_copy.V_RESTRIZIONI_OBBL  = RE_original.V_RESTRIZIONI_OBBL;
    }


    public ADAMS_TAB_RELAZIONI_ELEMENTO isPresentRelation(String relation_name, Vector vectorRelation)
    {
        for(int i=0; i<vectorRelation.size(); i++)
        {
            ADAMS_TAB_RELAZIONI_ELEMENTO appoRel = (ADAMS_TAB_RELAZIONI_ELEMENTO)vectorRelation.elementAt(i);
            if(appoRel.RELATION_NAME.compareTo(relation_name) == 0)
                return appoRel;
        }
        return null;
    }



    public Vector getVRelation(int idAnalisi)
    {
        int     _MAX_RELATION   = mdm_server_server.eMAX_RELATION; // dovrà puntare all'enum definita in ntmdataserver.idl
        Vector  appo            = new Vector();
        int     len             = size();

        for(int i=0;i<len;i++)
        {
             ADAMS_TAB_RELAZIONI_ELEMENTO rel=(ADAMS_TAB_RELAZIONI_ELEMENTO)elementAt(i);
             int len1=rel.V_LISTA_ANALISI.size();
             for(int j=0;j<len1;j++)
             {
                 int idA=((Integer)rel.V_LISTA_ANALISI.elementAt(j)).intValue();
                 if(idAnalisi==idA)
                 {
                     //La relazione appartiene all'analisi con ID=idAnalisi
                     if(appo.size()<_MAX_RELATION)
                     {
                        appo.addElement(new Integer(rel.idRelation));
                     }else
                     {
                         System.out.println("ADAMS_Relation [getVRelation("+idAnalisi+")] ---> ERRORE: è stato raggiunto il limite max di ["+_MAX_RELATION+"] delle relazioni per singola analisi");
                     }
                     break;
                 }
             }
        }
        return appo;
    }

    public void printALLRelazioni()
    {
    System.out.println("*** NUMERO DI RELATION   "+this.size()+" ************************");
        for(int i=0; i<this.size(); i++)
        {
            System.out.println();

            ADAMS_TAB_RELAZIONI_ELEMENTO appoRel = (ADAMS_TAB_RELAZIONI_ELEMENTO)this.elementAt(i);
            System.out.println("*** RELATION_NAME   "+appoRel.RELATION_NAME+" ***");
            System.out.println("idRelation          "+appoRel.idRelation);
            System.out.println("idFirstElement      "+appoRel.idFirstElement);
            System.out.println("idSecondElement     "+appoRel.idSecondElement);
            System.out.println("idParentRelation    "+appoRel.idParentRelation);

            if(appoRel.GHOST == 1)
                System.out.println("***************** E' UNA GHOST  *****************");


            System.out.print("nextLevelRelations = ");
            for(int x=0; x<appoRel.nextLevelRelations.length; x++)
            {
                if(appoRel.nextLevelRelations[x] !=0)
                    System.out.print(appoRel.nextLevelRelations[x]+" ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public int numMAX_nextLevelRelations()
    {
        int MAX_NEXT_LEVEL = 0;
        for(int i=0; i<this.size(); i++)
        {
            ADAMS_TAB_RELAZIONI_ELEMENTO appoRel = (ADAMS_TAB_RELAZIONI_ELEMENTO)this.elementAt(i);
            //System.out.print("nextLevelRelations = ");

            int count_Level = 0;
            for(int x=0; x<appoRel.nextLevelRelations.length; x++)
            {
                if(appoRel.nextLevelRelations[x] !=0)
                    count_Level++;
            }
            if(count_Level > MAX_NEXT_LEVEL)
                MAX_NEXT_LEVEL = count_Level;
        }
        return MAX_NEXT_LEVEL;
    }


}
