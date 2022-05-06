/**
 * @author Селяков Павел
 *         Created: 10 апр. 2022 г. 11:29:51
 *         State: 10 апр. 2022 г. 11:29:51 - initial
 */
package Settings;

import java.util.LinkedList;
import OperatorEngine.Item;

// import java.util.Set;

import Utility.ItemDictionaryBase;

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
     * Вложенный класс коллекции - а не наследуемый, поскольку надо переопределять возвращаемые типы для гладкого применения класса.
     */
    protected ItemDictionaryBase m_items;

    // *** Constructors ***
    /**
     * NT-Constructor
     */
    public SettingItemCollection()
    {
        this.m_items = new ItemDictionaryBase();
    }
    // *** Properties ***

    /**
     * NT-Collection has been modified
     * 
     * @return the modified
     */
    protected boolean isModified()
    {
        return this.m_items.isModified();
    }

    /**
     * NT-Collection set modified flag
     * 
     * @param modified
     *            the modified to set
     */
    protected void setModified(boolean modified)
    {
        this.m_items.setModified(modified);
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
        this.m_items.Clear();

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
        return this.m_items.hasKey(title);
    }

    /**
     * NT-Get array of used titles.
     * 
     * @param sorted
     *            Sort titles.
     * @return Function returns array of used keyname strings.
     */
    public String[] getTitles(boolean sorted)
    {
        return this.m_items.getKeys(sorted);
    }

    /**
     * NT-Get count of used titles.
     * 
     * @return Function returns count of used titles.
     */
    public int getTitleCount()
    {
        return this.m_items.getKeyCount();
    }

    // *************************************************

    /**
     * NT-Get all items from collection as list.
     * 
     * @return Function returns all items from collection as list.
     */
    public LinkedList<SettingItem> getAllItems()
    {
        LinkedList<Item> its = this.m_items.getAllItems();

        // скопируем элементы в выходной список, чтобы привести типы к требуемым
        LinkedList<SettingItem> result = new LinkedList<SettingItem>();
        for (Item it : its)
            result.add((SettingItem) it);

        return result;
    }

    /**
     * NT-Get settings item array by title
     * 
     * @param title
     *            Setting item title as key
     * @param sorted
     *            Sort items by title.
     * @return Returns SettingsItem[] array, or returns null if title not exists in
     *         collection.
     */
    public SettingItem[] getItems(String title, boolean sorted)
    {
        LinkedList<Item> its = this.m_items.getItems(title, sorted);
        if (its == null)
            return null;

        // скопируем элементы в выходной массив, чтобы привести типы к требуемым
        int size = its.size();
        SettingItem[] result = new SettingItem[size];
        for (int i = 0; i < size; i++)
            result[i] = (SettingItem) its.get(i);

        return result;
    }

    /**
     * NT-Get only first setting item by title
     * 
     * @param title
     *            Setting item title as key
     * @return Returns SettingsItem object, or returns null if title not exists in
     *         collection.
     */
    public SettingItem getFirstItem(String title)
    {
        Item result = this.m_items.getFirstItem(title);

        if (result == null)
            return null;
        else return (SettingItem) result;
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
        this.m_items.addItem(item.get_Title(), item);

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
        this.m_items.removeItems(title);

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
        return this.m_items.removeItem(item.get_Title(), item);
    }

    // *** End of file ***
}
