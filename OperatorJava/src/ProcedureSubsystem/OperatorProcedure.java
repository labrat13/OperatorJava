package ProcedureSubsystem;
/**
 * @author Селяков Павел
 *         Created: Mar 9, 2022 1:19:08 PM
 *         State: Mar 21, 2022 12:37:20 AM - Ported, Готов к отладке.
 */

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This attribute marks assemblies, classes and functions as usable in method execution process.
 * If assembly, class, function has this attribute, user can view and select as method realization.
 * 
 * @author Селяков Павел
 *
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD, PACKAGE })
public @interface OperatorProcedure
{
    // /**
    // * Состояние реализации методов, помеченных данным атрибутом.
    // */
    // public enum ImplementationState
    // {
    //  NotRealized,    //Метод не реализован (NR).
    //  NotTested,      //Метод, класс, сборка реализован, но не тестирован (NT). 
    //  Ready;          //Метод, класс, сборка реализован, тестирован, готов к применению (RT).
    // }
    /**
     * Степень готовности помеченного элемента.
     * 
     * @return Возвращает степень готовности помеченного элемента.
     */
    ImplementationState State() default ImplementationState.NotRealized;

    /**
     * Название Процедуры для просмотра пользователем.
     */
    String Title() default "I am stupid!";

    /**
     * Однострочное описание действия Процедуры для просмотра пользователем при выборе Процедуры для Команды.
     */
    String Description() default "I not replace this annotation text to valid description of Procedure because I am stupid Java coder.";

}
