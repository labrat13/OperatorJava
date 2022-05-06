/**
 * @author Селяков Павел
 *         Created: 6 мая 2022 г. 10:46:06
 *         State: 6 мая 2022 г. 10:46:06 - initial
 */
package Utility;

import java.util.LinkedList;

import OperatorEngine.Item;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import Settings.SettingItem;

/**
 * @author Селяков Павел
 *
 */
public class ItemDictionaryByNamespace extends ItemDictionaryBase
{
    // *** Constants and Fields ***

    // *** Constructors ***

    /**
     * NT-Constructor
     */
    public ItemDictionaryByNamespace()
    {
        super();
    }

    // *** Properties ***
    // /**
    // * Collection has been modified
    // *
    // * @return the modified
    // */
    // protected boolean isModified()
    // {
    // return this.m_Modified;
    // }
    //
    // /**
    // * Collection set modified flag
    // *
    // * @param modified
    // * the modified to set
    // */
    // protected void setModified(boolean modified)
    // {
    // this.m_Modified = modified;
    // }

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

    // /**
    // * NT-Check setting is present
    // *
    // * @param ns
    // * Namespace title as key
    // * @return Returns true if setting present in collection, false otherwise.
    // */
    // public boolean hasNamespace(String ns)
    // {
    // return super.hasKey(ns);
    // }
    //
    // /**
    // * NT-Get array of used namespaces.
    // * @param sorted
    // * Optionally, sort keys.
    // * @return Function returns array of used keyname strings.
    // */
    // public String[] getNamespaces(boolean sorted)
    // {
    // return super.getKeys(sorted);
    // }
    //
    // /**
    // * NT-Get count of used namespaces.
    // *
    // * @return Function returns count of used titles.
    // */
    // public int getNamespaceCount()
    // {
    // return super.getKeyCount();
    // }

    /**
     * NT- Add setting items from source.
     * 
     * @param items
     *            List of items to add.
     */
    public void addItems(LinkedList<Item> items)
    {
        for (Item item : items)
        {
            this.addItem(item.get_Namespace(), item);
        }

        return;
    }

    /**
     * NT- Add setting items from source.
     * 
     * @param items
     *            List of items to add.
     */
    public void addSettingItems(LinkedList<SettingItem> items)
    {
        for (Item item : items)
        {
            this.addItem(item.get_Namespace(), item);
        }

        return;
    }

    /**
     * NT- Add Procedure items from source.
     * 
     * @param items
     *            List of items to add.
     */
    public void addProcedureItems(LinkedList<Procedure> items)
    {
        for (Item item : items)
        {
            this.addItem(item.get_Namespace(), item);
        }

        return;
    }

    /**
     * NT- Add Place items from source.
     * 
     * @param items
     *            List of items to add.
     */
    public void addPlaceItems(LinkedList<Place> items)
    {
        for (Item item : items)
        {
            this.addItem(item.get_Namespace(), item);
        }

        return;
    }

    /**
     * NT-Добавить элемент, используя поле Namespace в качестве ключа для словаря.
     * 
     * @param item
     *            Добавляемый элемент.
     */
    public void addItem(Item item)
    {
        this.addItem(item.get_Namespace(), item);

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
    public boolean removeItem(Item item) throws Exception
    {
        String key = item.get_Namespace();

        return super.removeItem(key, item);
    }

    // *** End of file ***
}
