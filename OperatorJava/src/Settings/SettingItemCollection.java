/**
 * @author Селяков Павел
 * Created: 10 апр. 2022 г. 11:29:51
 * State: 10 апр. 2022 г. 11:29:51 - initial
 */
package Settings;

import Utility.StringMultiMap;

/**
 * NR-Класс коллекции элементов настроек.
 * 
 * @author Селяков Павел
 *
 */
public class SettingItemCollection
{

 
    //*** Constants and  Fields ***
    /**
     * 
     */
protected StringMultiMap m_items;
    //*** Constructors ***
    /**
     * Constructor
     */
    public SettingItemCollection()
    {
        // TODO Auto-generated constructor stub
    }
    //*** Properties ***

    //*** Service  functions ***

    // *** Work functions ***
    
    /**
     * NT-Check setting is present
     * 
     * @param title
     *            Setting title as key
     * @return Returns true if setting present in collection, false otherwise.
     */
    public boolean hasSetting(String title)
    {
        return this.m_Dict.containsKey(title);
    }
    

    /**
     * NT-Get settings item by title
     * 
     * @param title
     *            Setting item title as key
     * @return Returns SettingsItem; returns null if title not exists in
     *         collection.
     */
    public SettingItem getItem(String title)
    {
        if (this.m_Dict.containsKey(title))
            return this.m_Dict.get(title);
        else return null;
    }
    
    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param title
     *            Setting item title as key
     * @param item
     *            Settings item object.
     */
    public void addItem(String title, SettingItem item)
    {
        this.m_Dict.put(title, item);
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param title
     *            Setting item title as key.
     * @param value
     *            Setting item value as String.
     * @param descr
     *            Setting item description as multiline String.
     */
    public void addItem(String title, String value, String descr)
    {
        SettingItem item = new SettingItem(title, value, descr);
        this.m_Dict.put(title, item);
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param title
     *            Setting item title as key.
     * @param value
     *            Setting item value as Integer.
     * @param descr
     *            Setting item description as multiline String.
     */
    public void addItem(String title, Integer value, String descr)
    {
        String val = value.toString();
        this.addItem(title, val, descr);

        return;
    }

    /**
     * NT-Add new or replace existing settings item in collection.
     * 
     * @param title
     *            Setting item title as key.
     * @param value
     *            Setting item value as Boolean.
     * @param descr
     *            Setting item description as multiline String.
     */
    public void addItem(String title, Boolean value, String descr)
    {
        String val = value.toString();
        this.addItem(title, val, descr);

        return;
    }

    /**
     * NT-Remove setting item from collection
     * 
     * @param title
     *            Setting item title as key
     */
    public void removeItem(String title)
    {
        this.m_Dict.remove(title);
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT- Get value by title
     * 
     * @param title
     *            Setting title
     * @return Returns Value as String; returns null if title not exists in
     *         collection.
     */
    public String getValue(String title)
    {
        if (this.m_Dict.containsKey(title))
            return this.m_Dict.get(title).getValue();
        else return null;
    }

    /**
     * NT- Get value by title
     * 
     * @param title
     *            Setting name
     * @return Returns Value as Integer; returns null if title not exists in
     *         collection or has invalid format.
     */
    public Integer getValueAsInteger(String title)
    {
        if (this.m_Dict.containsKey(title))
            return this.m_Dict.get(title).getValueAsInteger();
        else return null;
    }

    /**
     * NT- Get value by title
     * 
     * @param title
     *            Setting name
     * @return Returns Value as Boolean; returns null if title not exists in
     *         collection or has invalid format.
     */
    public Boolean getValueAsBoolean(String title)
    {

        if (this.m_Dict.containsKey(title))
            return this.m_Dict.get(title).getValueAsBoolean();
        else return null;

    }

    // -------------------------------------------------
    /**
     * NT-Set value by title, create setting if not exists.
     * 
     * @param title
     *            Keyname
     * @param value
     *            Value string
     * @param description
     *            Settings description text for new setting or ""
     */
    public void setValue(String title, String value, String description)
    {
        // add or replace value by title
        if (this.m_Dict.containsKey(title))
            this.m_Dict.get(title).setValue(value);
        else this.m_Dict.put(title, new SettingItem(title, value, description));
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT-Set value by title, create setting if not exists.
     * 
     * @param title
     *            Keyname
     * @param value
     *            Value string
     * @param description
     *            Settings description text for new setting or ""
     */
    public void setValue(String title, Integer value, String description)
    {
        // add or replace value by title
        if (this.m_Dict.containsKey(title))
            this.m_Dict.get(title).setValue(value);
        else
        {
            SettingItem t = new SettingItem(title, "", description);
            t.setValue(value);
            this.m_Dict.put(title, t);
        }
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }

    /**
     * NT-Set value by title, create setting if not exists.
     * 
     * @param title
     *            Keyname
     * @param value
     *            Value string
     * @param description
     *            Settings description text for new setting or ""
     */
    public void setValue(String title, Boolean value, String description)
    {
        // add or replace value by title
        if (this.m_Dict.containsKey(title))
            this.m_Dict.get(title).setValue(value);
        else
        {
            SettingItem t = new SettingItem(title, "", description);
            t.setValue(value);
            this.m_Dict.put(title, t);
        }
        // set modified flag
        this.m_ModifiedFlag = true;

        return;
    }
    
    
    //*** End of file ***
}
