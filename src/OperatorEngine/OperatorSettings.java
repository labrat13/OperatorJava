/**
 * @author Селяков Павел
 *         Created: Feb 24, 2022 12:43:30 AM
 *         State: Feb 24, 2022 01:23:30 AM - wait for write functions
 */
package OperatorEngine;

import java.util.HashMap;

/**
 * NR-Представляет настройки программы Оператор.
 * 
 * @author Селяков Павел
 *
 */
public class OperatorSettings
{

    /**
     * Application settings file name
     */
    public final static String        AppSettingsFileName = FileSystemManager.AppSettingsFileName;

    /**
     * Dictionary<String, String> for application setings
     */
    protected HashMap<String, String> m_Dict;

    /**
     * Settings file pathname
     */
    protected String                  m_filepath;

    /**
     * Constructor
     */
    public OperatorSettings()
    {
        m_Dict = new HashMap<String, String>();
    }

    /**
     * NR-Load or reload settings from file
     */
    public void Load()
    {
        //TODO: Add code here
        //open current file and read all items to dictionary
    }

    /**
     * NR-Load or reload settings from file
     * 
     * @param filename
     *            Settings file name
     */
    public void Load(String filename)
    {
        //TODO: Add code here
        //open specified file and read all items to dictionary
        //set specified file as current file
    }

    /**
     * NR- Write settings to file
     */
    public void Store()
    {
        //TODO: Add code here
        //open current file, write all items from dictionary to file and close file.
    }

    /**
     * NR- Write settings to file
     * 
     * @param filename
     *            Settings file name
     */
    public void Store(String filename)
    {
        //TODO: Add code here
      //open specified file, write all items from dictionary to file and close file.
        //do not set specified file as current file
    }

    /**
     * NR-Reset settings to default values
     */
    public void Reset()
    {
        //TODO: Add code here
        //clear dictionary and store default values
    }

    /**
     * NT- Get value by key
     * 
     * @param key
     *            Keyname
     * @return Returns Value as String; returns null if key not exists in
     *         collection.
     */
    public String getValue(String key)
    {
        if (this.m_Dict.containsKey(key))
            return this.m_Dict.get(key);
        else return null;

    }

    /**
     * NT- Get value by key
     * 
     * @param key
     *            Keyname
     * @return Returns Value as Integer; returns null if key not exists in
     *         collection or has invalid format.
     */
    public Integer getValueAsInteger(String key)
    {
        Integer result = null;
        
        if (!this.m_Dict.containsKey(key)) return null;

        try
        {
            String s = this.m_Dict.get(key);
            result = Integer.valueOf(s);

        }
        catch (Exception e)
        {
            result = null;
        }
        return result;
    }
    
    /**
     * NT- Get value by key
     * 
     * @param key
     *            Keyname
     * @return Returns Value as Boolean; returns null if key not exists in
     *         collection or has invalid format.
     */
    public Boolean getValueAsBoolean(String key)
    {
        Boolean result = null;
        
        if (!this.m_Dict.containsKey(key)) return null;

        try
        {
            String s = this.m_Dict.get(key);
            result = Boolean.valueOf(s);

        }
        catch (Exception e)
        {
            result = null;
        }
        return result;
    }
    
    
    /**
     * NT-Set value by key
     * 
     * @param key
     *            Keyname
     * @param value
     *            Value string
     */
    public void setValue(String key, String value)
    {
        // add or replace value by key
        this.m_Dict.put(key, value);

        return;
    }

    /**
     * NT-Set value by key
     * 
     * @param key
     *            Keyname
     * @param value
     *            Value string
     */
    public void setValue(String key, Integer value)
    {
        // add or replace value by key
        String s = value.toString();
        this.m_Dict.put(key, s);

        return;
    }
    
    /**
     * NT-Set value by key
     * 
     * @param key
     *            Keyname
     * @param value
     *            Value string
     */
    public void setValue(String key, Boolean value)
    {
        // add or replace value by key
        String s = value.toString();
        this.m_Dict.put(key, s);

        return;
    }
    

}
