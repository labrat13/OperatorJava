/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package OperatorEngine;

/**
 * NT-Базовый класс для Процедур и Мест Оператора
 * 
 * @author 1
 */
public class Item implements Comparable<Item>
{
    // #region Fields

    /**
     * Константа для поля m_storage, обозначает, что данный элемент хранится в Бд Оператор.
     * Все остальные значения этого поля должны соответствовать названиям Библиотек Процедур,
     * из которых извлечен данный элемент (Место или Процедура).
     */
    public static final String StorageKeyForDatabaseItem    = "Database";

    // TODO: придумать, как это преобразовать в подобие енума, но оставить String, чтобы не пополнять енум именами новых Библиотек Процедур.
    // TODO: описать эту фичу с источниками итемов в документации, иначе я про нее забуду совсем. Проект становится сложным и запутанным.
    /**
     * Константа для поля m_storage, обозначает, что данный элемент хранится в ФайлНастроек Оператор.
     * Все остальные значения этого поля должны соответствовать названиям Библиотек Процедур,
     * из которых извлечен данный элемент (Место или Процедура).
     */
    public static final String StorageKeyForSettingFileItem = "SettingFile";

    /**
     * Значение неправильного TableID, если итем не из ТаблицаНастроекОператора.
     */
    public static final int    Invalid_TableID              = -1;                                 // TODO: удалить позднее или переместить в БД адаптер.

    /**
     * первичный ключ таблицы
     */
    protected int              m_tableid;

    /**
     * Название Сущности
     */
    protected String           m_title;

    /**
     * Описание Сущности
     */
    protected String           m_descr;

    /**
     * Путь к Сущности или Значение.
     */
    protected String           m_path;

    /**
     * Название Хранилища ( Библиотеки Процедур или БД).
     * Не сохранять в таблицу БД!
     */
    protected String           m_storage;

    /**
     * Название пространства имен Сущности.
     */
    protected String           m_namespace;

    // #endregion

    /**
     * NT-Стандартный конструктор
     */
    public Item()
    {
        this.m_descr = "";
        this.m_path = "";
        this.m_tableid = 0;
        this.m_title = "";
        this.m_storage = "";
        this.m_namespace = "";

        return;
    }

    /**
     * RT-Название пространства имен.
     * 
     * @return Функция возвращает название пространства имен для Сущности.
     */
    public String get_Namespace()
    {
        return this.m_namespace;
    }

    /**
     * RT-Название пространства имен.
     * 
     * @param namespace
     *            Название пространства имен для Сущности.
     */
    public void set_Namespace(String namespace)
    {
        this.m_namespace = namespace;
    }

    /**
     * RT-Название Хранилища ( Библиотеки Процедур или БД).
     * Не сохранять в таблицу БД!
     * 
     * @return the storage
     */
    public String get_Storage()
    {
        return this.m_storage;
    }

    /**
     * RT-Название Хранилища ( Библиотеки Процедур или БД)
     * Не сохранять в таблицу БД!
     * 
     * @param storage
     *            the storage to set
     */
    public void set_Storage(String storage)
    {
        this.m_storage = storage;
    }

    // #region Properties
    /**
     * RT-первичный ключ таблицы
     * 
     * @return the id
     */
    public int get_TableId()
    {
        return m_tableid;
    }

    /**
     * RT-первичный ключ таблицы
     * 
     * @param id
     *            the id to set
     */
    public void set_TableId(int id)
    {
        this.m_tableid = id;
    }

    /**
     * Уникальное название сущности, до 255 символов
     * 
     * @return the title
     */
    public String get_Title()
    {
        return m_title;
    }

    /**
     * RT-Уникальное название сущности, до 255 символов
     * 
     * @param title
     *            the title to set
     */
    public void set_Title(String title)
    {
        this.m_title = title;
    }

    /**
     * RT-Описание Сущности
     * 
     * @return the descr
     */
    public String get_Description()
    {
        return m_descr;
    }

    /**
     * RT-Описание Сущности
     * 
     * @param descr
     *            the descr to set
     */
    public void set_Description(String descr)
    {
        this.m_descr = descr;
    }

    /**
     * RT-Путь к сущности, или Значение, до 255 символов
     * 
     * @return the path
     */
    public String get_Path()
    {
        return m_path;
    }

    /**
     * RT-Путь к сущности, или Значение, до 255 символов
     * 
     * @param path
     *            the path to set
     */
    public void set_Path(String path)
    {
        this.m_path = path;
    }

    /**
     * RT-Получить строку описания свойств Процедуры для отладчика.
     * 
     * @return Функция возвращает описание свойств Процедуры одной строкой.
     */
    @Override
    public String toString()
    {
        return this.getSingleLineProperties();
    }

    /**
     * NT-Проверить что элемент должен храниться в указанном Хранилище.
     * 
     * @param storageTitle
     *            Название Хранилища.
     * 
     * @return Функция возвращает True, если элемент хранится в указанном Хранилище, False в противном случае.
     */
    public boolean isItemFromStorage(String storageTitle)
    {
        return Utility.StringEqualsOrdinalIgnoreCase(this.m_storage, storageTitle);
    }

    /**
     * NT-Проверить что элемент должен храниться в БД.
     * 
     * @return Функция возвращает True, если элемент хранится в БД, False в противном случае.
     */
    public boolean isItemFromDatabase()// TODO: заменить функцию и все ее использования на isItemFromStorage(String storageTitle)
    {

        return Utility.StringEqualsOrdinalIgnoreCase(this.m_storage, Item.StorageKeyForDatabaseItem);
    }

    /**
     * NT-Получить одну строку описания свойств итема
     * 
     * @return Функция возвращает строку описания свойств итема
     */
    public String getSingleLineProperties()
    {
        // TODO: формат строки свойств Сущности неудовлетворительный - нужно переделать на понятный.
        // Одна строка, 80 символов макс.
        StringBuilder sb = new StringBuilder();
        sb.append(this.m_storage);
        sb.append(':');
        sb.append(this.m_tableid);
        sb.append(";");
        sb.append(this.m_title);
        sb.append(";");
        sb.append("[").append(this.m_namespace).append("]");
        sb.append(";");
        sb.append(this.m_path);
        sb.append(";");
        sb.append(this.m_descr);
        if (sb.length() > 80)
            sb.setLength(80);
        return sb.toString();
    }

    /**
     * NT-Получить одну строку описания свойств Элемента: название и описание, длиной менее 80 символов.
     * 
     * @return Функция возвращает строку вроде: название Элемента (Описание Элемента.)
     */
    public String GetShortInfo()
    {
        // TODO: формат строки свойств Сущности неудовлетворительный - нужно переделать на понятный.
        // Одна строка, 80 символов макс.
        StringBuilder sb = new StringBuilder();
        sb.append(Utility.GetStringTextNull(this.m_title));
        sb.append(" ");
        sb.append('(');
        sb.append(Utility.GetStringTextNull(this.m_descr));
        if (sb.length() > 75)
        {
            sb.setLength(75);
        }
        sb.append(')');

        return sb.toString();
    }

    // Сортировка

    /**
     * Сортировать список по Названию
     * 
     * @param x
     *            Item object
     * @param y
     *            Item object
     * @return
     *         Returns: -1 if x<y; 0 if x=y; 1 if x>y;
     */
    public static int CompareByTitle(Item x, Item y)
    {
        if (x == null)
        {
            if (y == null)
                return 0;// If x is null and y is null, they're equal.
            else return -1;// If x is null and y is not null, y is greater.
        }
        else
        {   // If x is not null...
            if (y == null)
                return 1;// ...and y is null, x is greater.
            else
            {
                String titleX = x.get_Title();
                String titleY = y.get_Title();

                return titleX.compareTo(titleY);

            }
        }
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Item o)
    {
        return Item.CompareByTitle(this, o);
    }

}
