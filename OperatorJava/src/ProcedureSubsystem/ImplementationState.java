/**
 * @author Селяков Павел
 *         Created: Mar 14, 2022 8:16:09 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */
package ProcedureSubsystem;

/**
 * Состояние реализации методов, помеченных данным атрибутом.
 * 
 * @author Селяков Павел
 *
 */
public enum ImplementationState
{
    /**
     * Метод не реализован (NR).
     */
    NotRealized,
    /**
     * Метод, класс, сборка реализован, но не тестирован (NT).
     */
    NotTested,
    /**
     * Метод, класс, сборка реализован, тестирован, готов к применению (RT).
     */
    Ready;
}
