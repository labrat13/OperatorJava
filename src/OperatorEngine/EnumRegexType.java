/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - TODO: указать состояние файла здесь.
 */
package OperatorEngine;

/**
 * Оценка типа регекса
 * 
 * @author 1
 */
public enum EnumRegexType
{

    /**
     * Неизвестно
     */
    Unknown, // = 0,

    /**
     * Обычный регекс
     */
    NormalRegex, // = 1,

    /**
     * Простая строка
     */
    SimpleString, // = 2,

    /**
     * Пустое поле
     */
    Empty, // = 3,

    /**
     * Неправильный формат
     */
    Invalid; // = 4,
}
