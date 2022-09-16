/**
 * @author Селяков Павел
 *         Created: Feb 22, 2022 5:06:41 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package LogSubsystem;

/**
 * NR-Код состояния события лога
 * 
 * @author Селяков Павел
 *
 */
public enum EnumLogMsgState
{
    /**
     * Default (0) or Unknown
     */
    Default(0),
    /**
     * Fail state
     */
    Fail(1),
    /**
     * Success state
     */
    OK(2);

    /**
     * Object value
     */
    private int m_Value;

    /**
     * Get object value
     * 
     * @return Object value
     */
    public int getValue()
    {
        return this.m_Value;
    }

    /**
     * Set object value
     * 
     * @param val
     *            New object value
     */
    public void setValue(int val)
    {
        this.m_Value = val;
    }

    /**
     * NT-Get object value as int as String
     * 
     * @return Return text integer value
     */
    public String getValueIntAsString()
    {
        return Integer.toString(this.m_Value);
    }

    /**
     * Constructor
     * 
     * @param val
     *            New object value
     */
    private EnumLogMsgState(int val)
    {
        this.m_Value = val;
    }
}
