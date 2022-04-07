/**
 * @author Селяков Павел
 *         Created: Feb 22, 2022 5:05:11 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package LogSubsystem;

/**
 * NR-Код класса события лога
 * 
 * @author Селяков Павел
 *
 */
public enum EnumLogMsgClass
{
    // TODO: Добавить новые классы событий лога здесь.
    /**
     * Default (0) or Unknown
     */
    Default(0),
    /**
     * Session start message
     */
    SessionStarted(1),
    /**
     * Session finish message
     */
    SessionFinished(2),
    /**
     * User input query text
     */
    QueryStarted(3),
    /**
     * Query text has been changed
     */
    QueryReplaced(4),
    /**
     * User query processing finished
     */
    QueryFinished(5),
    /**
     * Exception raised to break execution of Operator
     */
    ExceptionRaised(6),
    /**
     * Exception suppressed or processed by engine
     */
    ExceptionSuppressed(7);

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
     * NT-Get object value as int as String
     * 
     * @return Return text integer value
     */
    public String getValueIntAsString()
    {
        return Integer.toString(this.m_Value);
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
     * Constructor
     * 
     * @param val
     *            New object value
     */
    private EnumLogMsgClass(int val)
    {
        this.m_Value = val;
    }
}
