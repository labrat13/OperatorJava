/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - TODO: указать состояние файла здесь.
 */
package Lexicon;

import JTerminal.TerminalMode;

/**
 * @author jsmith
 *         Выбранные цвета для текстовых диалогов консоли.
 */
public enum EnumDialogConsoleColor
{
    /**
     * Неопределенный.
     */
    Unknown(0),

    /**
     * Цвет обычного текста консоли.
     */
    Сообщение(TerminalMode.COLOR_GRAY),

    /**
     * Цвет текстов, введенных пользователем.
     */
    ВводПользователя(TerminalMode.COLOR_BRIGHT_GRAY),

    /**
     * Цвет текста выводимых в консоли предупреждений.
     */
    Предупреждение(TerminalMode.COLOR_MAGENTA),

    /**
     * Цвет текста выводимых в консоли вопросов пользователю.
     */
    Вопрос(TerminalMode.COLOR_YELLOW),

    /**
     * Цвет текста выводимых в консоли подтверждений.
     */
    Успех(TerminalMode.COLOR_GREEN),

    /**
     * Цвет текста выводимых в консоли сообщений о критических ошибках.
     */
    Критический(TerminalMode.COLOR_BRIGHT_RED);

    /**
     * Object value
     */
    private int m_Value;

    /**
     * Set object value
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
     * Constructor
     * 
     * @param val
     *            New object value
     */
    private EnumDialogConsoleColor(int val)
    {
        this.m_Value = val;
    }

}
