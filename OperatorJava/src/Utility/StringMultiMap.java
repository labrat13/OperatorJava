/**
 * @author Селяков Павел
 * Created: 10 апр. 2022 г. 12:23:35
 * State: 10 апр. 2022 г. 12:23:35 - initial
 */
package Utility;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * NR - Коллекция Ключ-Список значений.
 * @author Селяков Павел
 *
 */
public class StringMultiMap
{


    //*** Constants and  Fields ***
    
    /**
     * Dictionary of Lists
     */
protected HashMap<String, LinkedList<String>> m_multimap;
    //*** Constructors ***
    /**
     * Default constructor
     */
    public StringMultiMap()
    {
        this.m_multimap = new HashMap<String, LinkedList<String>>();
    }
    //*** Properties ***

    //*** Service  functions ***

/* 
 * NR-Return string representation to object.
 */
@Override
public String toString()
{
    // TODO Auto-generated method stub
    return super.toString();
}
    // *** Work functions ***
    
    
    /**
     * NR- get list of values by key
     * @param key
     * @return
     * @throws Exception 
     */
        public LinkedList<String> get(String key) throws Exception
        {
            throw new Exception("This method not implemented.");
        }


        /**
         * NR- add value to collection
         * @param key
         * @param value
         * @throws Exception 
         */
        public void add(String key, String value) throws Exception
        {
            throw new Exception("This method not implemented.");
            
        }
    
    //*** End of file ***
}
