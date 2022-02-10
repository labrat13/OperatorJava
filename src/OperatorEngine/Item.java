/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - TODO: указать состояние файла здесь.
 */
package OperatorEngine;

/**
 * Абстрактный класс для Процедур и Мест Оператора
 * 
 * @author 1
 */
public class Item
{
    // #region Fields

    /**
     * первичный ключ таблицы
     */
    protected int    m_id;

    /**
     * Название Сущности
     */
    protected String m_title;

    /**
     * Описание Сущности
     */
    protected String m_descr;

    /**
     * Путь к Сущности
     */
    protected String m_path;

    // #endregion

    /**
     * Стандартный конструктор
     */
    public Item()
    {

    }

    // #region Properties
    /**
     * первичный ключ таблицы
     * 
     * @return the id
     */
    public int get_TableId()
    {
        return m_id;
    }

    /**
     * первичный ключ таблицы
     * 
     * @param id
     *            the id to set
     */
    public void set_TableId(int id)
    {
        this.m_id = id;
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
     * Уникальное название сущности, до 255 символов
     * 
     * @param title
     *            the title to set
     */
    public void set_Title(String title)
    {
        this.m_title = title;
    }

    /**
     * Описание Сущности
     * 
     * @return the descr
     */
    public String get_Description()
    {
        return m_descr;
    }

    /**
     * Описание Сущности
     * 
     * @param descr
     *            the descr to set
     */
    public void set_Description(String descr)
    {
        this.m_descr = descr;
    }

    /**
     * Путь к сущности, до 255 символов
     * 
     * @return the path
     */
    public String get_Path()
    {
        return m_path;
    }

    /**
     * Путь к сущности, до 255 символов
     * 
     * @param path
     *            the path to set
     */
    public void set_Path(String path)
    {
        this.m_path = path;
    }

    @Override
    public String toString()
    {
        return this.getSingleLineProperties();
    }

    /**
     * NT-Получить одну строку описания свойств итема
     * 
     * @return Функция возвращает строку описания свойств итема
     */
    public String getSingleLineProperties()
    {
        // Одна строка, 80 символов макс.
        StringBuilder sb = new StringBuilder();
        sb.append(this.get_TableId());
        sb.append(";");
        sb.append(this.get_Title());
        sb.append(";");
        sb.append(this.get_Path());
        sb.append(";");
        sb.append(this.get_Description());
        if (sb.length() > 80) sb.setLength(80);
        return sb.toString();
    }

    // TODO: узнать как сортируются списки по предикатам

    /**
     * Сортировать список по Названию
     * 
     * @param x
     *            Item object
     * @param y
     *            Item object
     * @return
     */
    public static int SortByTitle(Item x, Item y)
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

}
