/**
 * @author Селяков Павел
 * Created: 28 апр. 2022 г. 21:30:17
 * State: 28 апр. 2022 г. 21:30:17 - initial
 */
package GeneralProcedures;

import ProcedureSubsystem.ImplementationState;
import ProcedureSubsystem.OperatorProcedure;


//DONE: Класс методов для Процедур должен быть помечен аннотацией OperatorProcedure с ImplementationState = NotTested либо Ready, чтобы его методы можно было
//вызывать в качестве процедур.
//Класс может также содержать любые элементы, необходимые для методов Процедур.

/** NT-Класс Процедур управления питанием компьютера (Выключение, перезагрузка, сон, итп)
 * @author Селяков Павел
 *
 */
@OperatorProcedure(State = ImplementationState.NotRealized, Title = "Power class",
Description = "Power procedures class.")
public class PowerProcedures
{
    // Для всех операций с Процедурами и Местами из кода Процедур использовать класс ElementCacheManager, а не БД итп.
    // engine.get_ECM().AddPlace(p);

    // Памятка: Если аннотация @OperatorProcedure не указана, Движок будет выдавать исключение, что соответствующий элемент не помечен аннотацией, и не будет
    // исполнять Процедуру.
    // Если @OperatorProcedure.State = ImplementationState.NotRealized, Движок будет выдавать исключение, что соответствующий элемент не реализован, и не будет
    // исполнять Процедуру.
    // Если @OperatorProcedure.State = ImplementationState.NotTested, Движок будет выдавать сообщение, что соответствующий элемент не тестирован, и будет
    // исполнять Процедуру.
    // Если @OperatorProcedure.State = ImplementationState.Ready, Движок будет исполнять Процедуру.
    // Следовательно:
    // - Если вы недописали Процедуру, пометьте ее ImplementationState.NotRealized, чтобы Оператор не пытался исполнять эту Процедуру.
    // - Если вы дописали но не протестировали Процедуру, пометьте ее ImplementationState.NotTested, чтобы Оператор выводил предупреждение, что запускаемая
    // Процедура не проверена.
    // - Если вы протестировали Процедуру, и она правильно работает, пометьте ее ImplementationState.Ready, чтобы Оператор не выводил ненужные более
    // предупреждения.



}

