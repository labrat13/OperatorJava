/**
 * @author Селяков Павел
 * Created: 28 апр. 2022 г. 21:30:17
 * State: 28 апр. 2022 г. 21:30:17 - initial
 */
package GeneralProcedures;

import ProcedureSubsystem.OperatorProcedure;
import OperatorEngine.ArgumentCollection;
import OperatorEngine.Engine;
import OperatorEngine.EnumProcedureResult;
import OperatorEngine.UserQuery;
import ProcedureSubsystem.ImplementationState;
import ProcedureSubsystem.LibraryManagerBase;


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

//    /**
//     * NT- Тестовая процедура: выводит на консоль helloworld и звуковой сигнал.
//     * 
//     * @param engine
//     *            Ссылка на объект Движка Оператор для доступа к консоли, логу, БД итп.
//     * @param manager
//     *            Ссылка на объект Менеджера Библиотеки Процедур для доступа к инициализированным ресурсам библиотеки.
//     * @param query
//     *            Текст исходного запроса пользователя.
//     * @param args
//     *            Массив аргументов Процедуры, соответствующий запросу.
//     * @return Функция возвращает результат как одно из значений EnumProcedureResult:
//     *         EnumProcedureResult.Success если Процедура выполнена успешно;
//     *         EnumProcedureResult.WrongArguments если аргументы не подходят для запуска Процедуры;
//     *         EnumProcedureResult.Error если произошла ошибка при выполнении Процедуры;
//     *         EnumProcedureResult.CancelledByUser если выполнение Процедуры прервано Пользователем;
//     *         EnumProcedureResult.Exit если после выполнения Процедуры требуется завершить работу Оператор;
//     *         EnumProcedureResult.ExitAndLogoff если после выполнения Процедуры требуется завершить сеанс пользователя;
//     *         EnumProcedureResult.ExitAndHybernate если после выполнения Процедуры требуется перевести компьютер в спящий режим;
//     *         EnumProcedureResult.ExitAndSleep если после выполнения Процедуры требуется перевести компьютер в спящий режим;
//     *         EnumProcedureResult.ExitAndReload если после выполнения Процедуры требуется перезагрузить компьютер;
//     *         EnumProcedureResult.ExitAndShutdown если после выполнения Процедуры требуется выключить компьютер;
//     */
//    @OperatorProcedure(State = ImplementationState.Ready, Title = "Test method",
//            Description = "Test procedures engine method.")
//    public static EnumProcedureResult test(
//            Engine engine,
//            LibraryManagerBase manager,
//            UserQuery query,
//            ArgumentCollection args)
//    {
//        /*
//         * 07042022 - Добавлена возможность внутри Процедуры изменять текст запроса,
//         * чтобы применить новый текст запроса к дальнейшему поиску Процедур.
//         * Изменение запроса не перезапускает поиск Процедур (в текущей версии Оператора).
//         * Поэтому изменять запрос следует только в хорошо продуманных случаях.
//         * 
//         * Пример вызова функции переопределения запроса, с выводом в лог старого и нового значений.
//         * Example: query.ChangeQuery(engine, "New query text");
//         */
//
//        // print helloworld message to console and exit
//        engine.get_OperatorConsole().PrintTextLine("helloworld", EnumDialogConsoleColor.Сообщение);
//        engine.get_OperatorConsole().Beep();
//
//        return EnumProcedureResult.Success;
//    }

}
