/**
 * @author Селяков Павел
 * Created: Feb 22, 2022 5:05:11 PM
 * State: Feb 22, 2022 5:05:11 PM - initial
 */
package LogSubsystem;


/**
 * NR-Код класса события лога
 * @author Селяков Павел
 *
 */
public enum EnumLogMsgClass
{
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
    SessionFinished(2);
    
    
    
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
