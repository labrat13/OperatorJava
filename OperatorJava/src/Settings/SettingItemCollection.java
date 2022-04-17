/**
 * @author Селяков Павел
 *         Created: 10 апр. 2022 г. 11:29:51
 *         State: 10 апр. 2022 г. 11:29:51 - initial
 */
package Settings;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

/**
 * NT-Класс коллекции элементов настроек.
 * 
 * @author Селяков Павел
 *
 */
public class SettingItemCollection
{

    // *** Constants and Fields ***
    /**
     * Dictionary of Lists
     */
    protected HashMap<String, LinkedList<SettingItem>> m_items;

    /**
     * Collection has been modified
     */
    private boolean                                    m_Modified;

    // *** Constructors ***
    /**
     * Constructor
     */
    public SettingItemCollection()
    {
        this.m_items = new HashMap<String, LinkedList<SettingItem>>();
        this.m_Modified = false;
    }
    // *** Properties ***

    /**
     * Collection has been modified
     * 
     * @return the modified
     */
    protected boolean isModified()
    {
        return m_Modified;
    }

    /**
     * Collection has been modified
     * 
     * @param modified
     *            the modified to set
     */
    protected void setModified(boolean modified)
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

    // *** Work functions ***

    /**
     * NT- Add setting items from source.
     * 
     * @param items
     *            List of items to add.
     */
    public void addItems(LinkedList<SettingItem> items)
    {
        for (SettingItem item : items)
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
        // перечислить списки и очистить каждый из них
        for (Entry<String, LinkedList<SettingItem>> entry : this.m_items.entrySet())
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
     * 
     * @return Function returns array of used keyname strings.
     */
    public String[] getTitles()
    {
        Set<String> result = this.m_items.keySet();
        int len = result.size();

        return result.toArray(new String[len]);
    }

    /**
     * NT-Get count of used titles.
     * 
     * @return Function returns count of used titles.
     */
    public int getTitleCount()
    {
        // TODO Auto-generated method stub
        return this.m_items.size();
    }

    /**
     * NT-Get all items from collection as list.
     * 
     * @return Function returns all items from collection as list.
     */
    public LinkedList<SettingItem> getAllItems()
    {
        LinkedList<SettingItem> result = new LinkedList<SettingItem>();
        // мержим все значения-списки в выходной список.
        for (LinkedList<SettingItem> li : this.m_items.values())
            if ((li != null) && (li.size() > 0))
                result.addAll(li);
        // возвращаем выходной список
        return result;
    }

    // *************************************************

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

        if (result == null)
            return null;
        else return result.toArray(new SettingItem[result.size()]);
    }

    // *****************************************************

    /**
     * NT-Добавить элемент, используя поле Title в качестве ключа для словаря.
     * 
     * @param item
     *            Добавляемый элемент.
     */
    public void addItem(SettingItem item)
    {
        this.addItem(item.get_Title(), item);

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
        // get copy of title string
        String tl = new String(title);
        // get list by key
        LinkedList<SettingItem> lsi = this.m_items.get(tl);
        // if list == null, create it and add to dictionary
        if (lsi == null)
        {
            lsi = new LinkedList<SettingItem>();
            this.m_items.put(tl, lsi);
        }
        // add item to list
        lsi.add(item);

        // set modified flag
        this.m_Modified = true;

        return;
    }

    // **********************************************

    /**
     * NT-Remove list of items from collection
     * 
     * @param title
     *            Setting item title as key
     */
    public void removeItems(String title)
    {
        // remove list of items by title
        this.m_items.remove(title);
        // set modified flag
        this.m_Modified = true;

        return;
    }

    /**
     * NT - remove specified item object
     * 
     * @param item
     *            Объект, уже находящийся в этой коллекции.
     * @return Функция возвращает true, если объект был удален; функция возвращает false, если объект не был найден.
     * @throws Exception
     *             Если ключ отсутствует в словаре коллекции.
     */
    public boolean removeItem(SettingItem item) throws Exception
    {
        String key = item.get_Title();
        LinkedList<SettingItem> list = this.m_items.get(key);
        if (list == null)
            throw new Exception(String.format("Ключ \"s\" отсутствует в словаре", key));
        // remove item from list
        boolean result = list.remove(item);
        if (result == true)
            // set modified flag
            this.m_Modified = true;

        return result;
    }

    // *** End of file ***
}
