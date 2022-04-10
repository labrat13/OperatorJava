/**
 * @author Селяков Павел
 * Created: 10 апр. 2022 г. 11:29:51
 * State: 10 апр. 2022 г. 11:29:51 - initial
 */
package Settings;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;



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
     * Dictionary of Lists
     */
protected HashMap<String, LinkedList<SettingItem>> m_items;
/**
 * Collection has been modified
 */
private boolean m_Modified;
    //*** Constructors ***
    /**
     * Constructor
     */
    public SettingItemCollection()
    {
        this.m_items = new HashMap<String, LinkedList<SettingItem>>();
        this.m_Modified = false;
    }
    //*** Properties ***

    /** Collection has been modified
     * @return the modified
     */
    protected boolean isModified()
    {
        return m_Modified;
    }

    /** Collection has been modified
     * @param modified the modified to set
     */
    protected void setModified(boolean modified)
    {
        this.m_Modified = modified;
    }

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
     * NT- Add setting items from source.
     * @param items List of items to add.
     */
    public void addItems(LinkedList<SettingItem> items)
    {   
        for(SettingItem item : items)
        {
            this.addItem(item);
        }
        
        return;
    }
    
    /**
     * NT- Clear collection   
     */
   public void Clear()
   {
       //перечислить списки и очистить каждый из них
       for(Entry<String, LinkedList<SettingItem>> entry : this.m_items.entrySet())
       {
           entry.getValue().clear();
       }
       //очистить словарь
       this.m_items.clear();
       //set modified flag
       this.m_Modified = true;
       //TODO: следует ли тут вызвать сборку мусора?
       return;
   }
    
    /**
     * NT-Check setting is present
     * 
     * @param title
     *            Setting title as key
     * @return Returns true if setting present in collection, false otherwise.
     */
    public boolean hasTitle(String title)
    {
        return this.m_items.containsKey(title);
    }
    


/**
 * NT-Get array of used titles.
 * @return Function returns array of used keyname strings.
 */
public String[] getKeyArray()
{
    Set<String> result = this.m_items.keySet();
    int len = result.size();
    
    return result.toArray(new String[len]);
}

//*************************************************

    /**
     * NT-Get settings item array by title
     * 
     * @param title
     *            Setting item title as key
     * @return Returns SettingsItem[] array, or returns null if title not exists in
     *         collection.
     */
    public SettingItem[] getItems(String title)
    {
        LinkedList<SettingItem> result = this.m_items.get(title);
        
        if(result == null) return null;
        else return result.toArray(new SettingItem[result.size()]);
    }

//*****************************************************    
    
    /**
     * NT-Добавить элемент, используя поле Title в качестве ключа для словаря.
     * @param item Добавляемый элемент.
     */
    public void addItem(SettingItem item)
    {
        this.addItem(item.getTitle(), item);
        
        return;
    }
    
    /**
     * NT-Add new settings item in collection.
     * 
     * @param title
     *            Setting item title as key
     * @param item
     *            Settings item object.
     */
    public void addItem(String title, SettingItem item)
    {
        //get copy of title string
        String tl = new String(title);
        //get list by key
        LinkedList<SettingItem> lsi = this.m_items.get(tl);
        //if list == null, create it and add to dictionary
        if(lsi == null)
        {
          lsi = new LinkedList<SettingItem>();
          this.m_items.put(tl, lsi);
        }
        //add item to list
        lsi.add(item);

        // set modified flag
        this.m_Modified = true;

        return;
    }
    
//**********************************************
    
    

//
//    /**
//     * NR-Add new or replace existing settings item in collection.
//     * 
//     * @param title
//     *            Setting item title as key.
//     * @param value
//     *            Setting item value as String.
//     * @param descr
//     *            Setting item description as multiline String.
//     */
//    public void addItem(String title, String value, String descr)
//    {
//        SettingItem item = new SettingItem(title, value, descr);
//        this.m_Dict.put(title, item);
//        // set modified flag
//        this.m_ModifiedFlag = true;
//
//        return;
//    }
//
//    /**
//     * NR-Add new or replace existing settings item in collection.
//     * 
//     * @param title
//     *            Setting item title as key.
//     * @param value
//     *            Setting item value as Integer.
//     * @param descr
//     *            Setting item description as multiline String.
//     */
//    public void addItem(String title, Integer value, String descr)
//    {
//        String val = value.toString();
//        this.addItem(title, val, descr);
//
//        return;
//    }
//
//    /**
//     * NT-Add new or replace existing settings item in collection.
//     * 
//     * @param title
//     *            Setting item title as key.
//     * @param value
//     *            Setting item value as Boolean.
//     * @param descr
//     *            Setting item description as multiline String.
//     */
//    public void addItem(String title, Boolean value, String descr)
//    {
//        String val = value.toString();
//        this.addItem(title, val, descr);
//
//        return;
//    }

    /**
     * NT-Remove list of items from collection
     * 
     * @param title
     *            Setting item title as key
     */
    public void removeItems(String title)
    {
        //remove list of items by title
        this.m_items.remove(title);
        // set modified flag
        this.m_Modified = true;

        return;
    }
    
    /**
     * NT - remove specified item object
     * @param item Объект, уже находящийся в этой коллекции.
     * @return Функция возвращает true, если объект был удален; функция возвращает false, если объект не был найден.
     * @throws Exception Если ключ отсутствует в словаре коллекции.
     */
    public boolean removeItem(SettingItem item) throws Exception
    {
        String key = item.getTitle();
        LinkedList<SettingItem> list = this.m_items.get(key);
        if(list == null)
            throw new Exception(String.format("Ключ \"s\" отсутствует в словаре", key));
        //remove item from list
        boolean result = list.remove(item);
        if(result == true)
            // set modified flag
            this.m_Modified = true;
        
        return result; 
    }

//    /**
//     * NR- Get value by title
//     * 
//     * @param title
//     *            Setting title
//     * @return Returns Value as String; returns null if title not exists in
//     *         collection.
//     */
//    public String getValue(String title)
//    {
//        if (this.m_Dict.containsKey(title))
//            return this.m_Dict.get(title).getValue();
//        else return null;
//    }
//
//    /**
//     * NR- Get value by title
//     * 
//     * @param title
//     *            Setting name
//     * @return Returns Value as Integer; returns null if title not exists in
//     *         collection or has invalid format.
//     */
//    public Integer getValueAsInteger(String title)
//    {
//        if (this.m_Dict.containsKey(title))
//            return this.m_Dict.get(title).getValueAsInteger();
//        else return null;
//    }
//
//    /**
//     * NR- Get value by title
//     * 
//     * @param title
//     *            Setting name
//     * @return Returns Value as Boolean; returns null if title not exists in
//     *         collection or has invalid format.
//     */
//    public Boolean getValueAsBoolean(String title)
//    {
//
//        if (this.m_Dict.containsKey(title))
//            return this.m_Dict.get(title).getValueAsBoolean();
//        else return null;
//
//    }
//
//    // -------------------------------------------------
//    /**
//     * NR-Set value by title, create setting if not exists.
//     * 
//     * @param title
//     *            Keyname
//     * @param value
//     *            Value string
//     * @param description
//     *            Settings description text for new setting or ""
//     */
//    public void setValue(String title, String value, String description)
//    {
//        // add or replace value by title
//        if (this.m_Dict.containsKey(title))
//            this.m_Dict.get(title).setValue(value);
//        else this.m_Dict.put(title, new SettingItem(title, value, description));
//        // set modified flag
//        this.m_ModifiedFlag = true;
//
//        return;
//    }
//
//    /**
//     * NR-Set value by title, create setting if not exists.
//     * 
//     * @param title
//     *            Keyname
//     * @param value
//     *            Value string
//     * @param description
//     *            Settings description text for new setting or ""
//     */
//    public void setValue(String title, Integer value, String description)
//    {
//        // add or replace value by title
//        if (this.m_Dict.containsKey(title))
//            this.m_Dict.get(title).setValue(value);
//        else
//        {
//            SettingItem t = new SettingItem(title, "", description);
//            t.setValue(value);
//            this.m_Dict.put(title, t);
//        }
//        // set modified flag
//        this.m_ModifiedFlag = true;
//
//        return;
//    }
//
//    /**
//     * NR-Set value by title, create setting if not exists.
//     * 
//     * @param title
//     *            Keyname
//     * @param value
//     *            Value string
//     * @param description
//     *            Settings description text for new setting or ""
//     */
//    public void setValue(String title, Boolean value, String description)
//    {
//        // add or replace value by title
//        if (this.m_Dict.containsKey(title))
//            this.m_Dict.get(title).setValue(value);
//        else
//        {
//            SettingItem t = new SettingItem(title, "", description);
//            t.setValue(value);
//            this.m_Dict.put(title, t);
//        }
//        // set modified flag
//        this.m_ModifiedFlag = true;
//
//        return;
//    }
    
    
    //*** End of file ***
}
