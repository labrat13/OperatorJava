/**
 * @author Селяков Павел
 *         Created: Mar 9, 2022 12:46:00 PM
 *         State: Mar 9, 2022 12:46:00 PM - initial
 */
package OperatorEngine;

/**
 * Состояние реализации методов, помеченных данным атрибутом.
 * 
 * @author Селяков Павел
 *
 */
public enum EnumImplementationState
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
