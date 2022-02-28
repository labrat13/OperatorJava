/**
 * @author Селяков Павел
 *         Created: Feb 27, 2022 7:01:06 PM
 *         State: Feb 27, 2022 7:01:06 PM - initial
 */
package Settings;

import OperatorEngine.Utility;

/**
 * NT-Класс представляет элемент данных в файле настроек приложения.
 * Элемент данных содержит название-ключ, текстовое значение и текстовое описание.
 * Текстовое описание должно выводиться на одной строке комментария, если оно однострочное, или на нескольких строках, если оно многострочное.  
 * На следующей строке должно выводиться пара ключ-значение и затем следующая строка должна быть пустой
 * @author Селяков Павел
 *
 */
public class SettingsItem
{

    /**
     * Setting title as dictionary key
     */
    private String m_Title;

    /**
     * Settings description multiline text
     */
    private String m_Description;

    /**
     * Settings value as String
     */
    private String m_Value;

    /**
     * NT-Default constructor
     */
    public SettingsItem()
    {
        this.m_Description = null;
        this.m_Title = null;
        this.m_Value = null;
    }

    /**
     * NT-Parameter constructor
     * 
     * @param title
     *            item title
     * @param value
     *            item value
     * @param descr
     *            item description text
     */
    public SettingsItem(String title, String value, String descr)
    {
        this.m_Value = value;
        this.m_Description = descr;
        this.m_Title = title;

        return;
    }

    /**
     * NT-Setting title as dictionary key
     * 
     * @return the title
     */
    public String getTitle()
    {
        return this.m_Title;
    }

    /**
     * NT-Setting title as dictionary key
     * 
     * @param title
     *            the title to set
     */
    public void setTitle(String title)
    {
        this.m_Title = title;
    }

    /**
     * NT-Settings description multiline text
     * 
     * @return the description
     */
    public String getDescription()
    {
        return this.m_Description;
    }

    /**
     * NT-Settings description multiline text
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description)
    {
        this.m_Description = description;
    }

    /**
     * NT-Settings value as String
     * 
     * @return the value
     */
    public String getValue()
    {
        return this.m_Value;
    }

    /**
     * NT-Settings value as String
     * 
     * @param value
     *            the value to set
     */
    public void setValue(String value)
    {
        this.m_Value = value;
    }

    /**
     * NT-Return string for debug
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        String t = Utility.GetStringTextNull(this.m_Title);
        String v = Utility.GetStringTextNull(this.m_Value);
        return String.format("%s = %s", t, v);
    }

    /**
     * NT- Get value
     * 
     * @return Returns Value as Integer; returns null if key not exists in
     *         collection or has invalid format.
     */
    public Integer getValueAsInteger()
    {
        Integer result = null;

        try
        {
            result = Integer.valueOf(this.m_Value);
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
     * @return Returns Value as Boolean; returns null if key not exists in
     *         collection or has invalid format.
     */
    public Boolean getValueAsBoolean()
    {
        Boolean result = null;

        try
        {
            result = Boolean.valueOf(this.m_Value);
        }
        catch (Exception e)
        {
            result = null;
        }

        return result;
    }

    /**
     * NT-Settings value as Boolean
     * 
     * @param value
     *            the value to set
     */
    public void setValue(Boolean value)
    {
        this.m_Value = value.toString();
    }

    /**
     * NT-Settings value as Integer
     * 
     * @param value
     *            the value to set
     */
    public void setValue(Integer value)
    {
        this.m_Value = value.toString();
    }

}
