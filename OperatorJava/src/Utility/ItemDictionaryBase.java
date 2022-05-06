/**
 * @author Селяков Павел
 *         Created: 6 мая 2022 г. 11:06:12
 *         State: 6 мая 2022 г. 11:06:12 - initial
 */
package Utility;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

import OperatorEngine.Item;

// TODO: попробовать позже реализовать этот класс как шаблон<T>

/**
 * NT-Общий класс словаря списков объектов Item
 * 
 * @author Селяков Павел
 *
 */
public class ItemDictionaryBase
{

    /**
     * Dictionary of Lists
     */
    protected HashMap<String, LinkedList<Item>> m_items;

    /**
     * Collection has been modified
     */
    protected boolean                           m_Modified;

    // *** Constructors ***

    /**
     * NT-Constructor
     */
    public ItemDictionaryBase()
    {
        this.m_items = new HashMap<String, LinkedList<Item>>();
        this.m_Modified = false;
    }

    // *** Properties ***
    /**
     * Collection has been modified
     * 
     * @return the modified
     */
    public boolean isModified()
    {
        return m_Modified;
    }

    /**
     * Collection set modified flag
     * 
     * @param modified
     *            the modified to set
     */
    public void setModified(boolean modified)
    {
        this.m_Modified = modified;
    }

    // *** Service functions ***
    /*
     * NR-Return string representation to object.
     */
    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return super.toString();
    }

    /**
     * NT- Clear collection
     */
    public void Clear()
    {
        // перечислить списки и очистить каждый из них
        for (Entry<String, LinkedList<Item>> entry : this.m_items.entrySet())
        {
            entry.getValue().clear();
        }
        // очистить словарь
        this.m_items.clear();
        // set modified flag
        this.m_Modified = true;
        // TODO: следует ли тут вызвать сборку мусора?
        return;
    }

    /**
     * NT-Check key is present
     * 
     * @param key
     *            key string value
     * @return Returns true if key present in collection, false otherwise.
     */
    public boolean hasKey(String key)
    {
        return this.m_items.containsKey(key);
    }

    /**
     * NT-Get array of used keys.
     * 
     * @param sorted
     *            Optionally, sort keys.
     * @return Function returns array of used keyname strings.
     */
    public String[] getKeys(boolean sorted)
    {
        Set<String> result = this.m_items.keySet();
        int len = result.size();
        String[] ar = result.toArray(new String[len]);
        // sort if needed
        if (sorted == true)
            Arrays.sort(ar);

        return ar;
    }

    /**
     * NT-Get count of used keys.
     * 
     * @return Function returns count of used keys.
     */
    public int getKeyCount()
    {
        return this.m_items.size();
    }

    /**
     * NT-Get all items from collection as list.
     * 
     * @return Function returns all items from collection as list.
     */
    public LinkedList<Item> getAllItems()
    {
        LinkedList<Item> result = new LinkedList<Item>();
        // мержим все значения-списки в выходной список.
        for (LinkedList<Item> li : this.m_items.values())
            if ((li != null) && (li.size() > 0))
                result.addAll(li);
        // возвращаем выходной список
        return result;
    }

    /**
     * NT-Get item array by title
     * 
     * @param key
     *            Dictionary key
     * @param sorted
     *            Optionally, sort keys.
     * @return Returns Item list, or returns null if key not exists in
     *         collection.
     */
    public LinkedList<Item> getItems(String key, boolean sorted)
    {
        LinkedList<Item> result = this.m_items.get(key);
        if (result == null)
            return null;

        if (sorted == true)
            Collections.sort(result);

        return result;
    }

    /**
     * NT-Get only first item by key
     * 
     * @param key
     *            Dictionary key
     * @return Returns Item object, or returns null if key not exists in
     *         collection.
     */
    public Item getFirstItem(String key)
    {
        LinkedList<Item> result = this.m_items.get(key);

        if (result == null)
            return null;
        if (result.size() < 1)
            return null;
        else return result.get(0);
    }

    /**
     * NT-Add new item to collection.
     * 
     * @param key
     *            Item field value as key
     * @param item
     *            Item object.
     */
    public void addItem(String key, Item item)
    {
        // get copy of title string
        String tl = new String(key);
        // get list by key
        LinkedList<Item> lsi = this.m_items.get(tl);
        // if list == null, create it and add to dictionary
        if (lsi == null)
        {
            lsi = new LinkedList<Item>();
            this.m_items.put(tl, lsi);
        }
        // add item to list
        lsi.add(item);

        // set modified flag
        this.m_Modified = true;

        return;
    }

    /**
     * NT-Remove list of items from collection
     * 
     * @param key
     *            Dictionary key.
     */
    public void removeItems(String key)
    {
        // remove list of items by title
        this.m_items.remove(key);
        // set modified flag
        this.m_Modified = true;

        return;
    }

    /**
     * NT - remove specified item object
     * 
     * @param key
     *            Dictionary key.
     * @param item
     *            Объект, уже находящийся в этой коллекции.
     * @return Функция возвращает true, если объект был удален; функция возвращает false, если объект не был найден.
     * @throws Exception
     *             Если ключ отсутствует в словаре коллекции.
     */
    public boolean removeItem(String key, Item item) throws Exception
    {
        LinkedList<Item> list = this.m_items.get(key);
        if (list == null)
            throw new Exception(String.format("Ключ \"s\" отсутствует в словаре", key));
        // remove item from list
        boolean result = list.remove(item);
        if (result == true)
            // set modified flag
            this.m_Modified = true;

        return result;
    }
}
