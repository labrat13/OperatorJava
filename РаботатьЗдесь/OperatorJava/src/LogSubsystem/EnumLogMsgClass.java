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
    ExceptionSuppressed(7),
    /**
     * Startup Operator procedure events
     */
    StartupExecution(8),
    /**
     * Finish Operator procedure execution
     */
    FinishExecution(9),
    /**
     * Общесистемное событие для лога.
     */
    SubsystemEvent_General(10),
    /**
     * Событие подсистемы БД для лога.
     */
    SubsystemEvent_Database(11),
    /**
     * Событие подсистемы терминала для лога.
     */
    SubsystemEvent_Terminal(12),
    /**
     * Событие подсистемы Семантической обработки для лога.
     */
    SubsystemEvent_Lexicon(13),
    /**
     * Событие подсистемы Движка для лога.
     */
    SubsystemEvent_Engine(14),
    /**
     * Событие подсистемы Процедур для лога.
     */
    SubsystemEvent_Procedure(15),
    /**
     * Событие подсистемы Настроек для лога.
     */
    SubsystemEvent_Settings(16),
    /**
     * Событие подсистемы утилит для лога.
     */
    SubsystemEvent_Utility(17),
    /**
     * Сообщение дампа данных для отладки
     */
    DebugLoggingMessage(18),
    
    // TODO: add new enum member here

    /**
     * Максимальное значение енума для упрощения добавления новых членов.
     */
    MaxValue(16384);

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
