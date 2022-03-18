/**
 * @author Селяков Павел
 * Created: Mar 14, 2022 9:31:59 PM
 * State: Mar 14, 2022 9:31:59 PM - initial
 */
package GeneralProcedures;

import ProcedureSubsystem.OperatorProcedure;
import Lexicon.EnumDialogConsoleColor;
import OperatorEngine.Engine;
import OperatorEngine.EnumProcedureResult;
import ProcedureSubsystem.ImplementationState;

//TODO: Класс методов для Процедур должен быть помечен аннотацией OperatorProcedure  с  ImplementationState = NotTested либо Ready, чтобы его методы можно было вызывать в качестве процедур.
//Класс может также содержать любые элементы, необходимые для методов Процедур.

/** Класс тестовых Процедур для Оператор.
 * @author Селяков Павел
 *
 */
@OperatorProcedure(State = ImplementationState.NotTested, Title = "Test class", Description = "Test procedures engine class.")
public class TestProcedures
{
    //Для всех операций с Процедурами и Местами из кода Процедур использовать класс ElementCacheManager, а не  БД итп.
    //engine.get_ECM().AddPlace(p);
    
    
    /**
     * NT- Тестовая процедура: выводит на консоль helloworld  и звуковой сигнал.
     * @param engine    Ссылка на объект Движка Оператор для доступа к консоли, логу, БД итп.
     * @param query     Текст исходного запроса пользователя.
     * @param args      Массив аргументов Процедуры, соответствующий запросу.
     * @return Функция возвращает результат как одно из значений EnumProcedureResult:
     *  EnumProcedureResult.Success если Процедура выполнена успешно;
     *  EnumProcedureResult.WrongArguments если аргументы не подходят для запуска Процедуры;
     *  EnumProcedureResult.Error если произошла ошибка при выполнении Процедуры;
     *  EnumProcedureResult.CancelledByUser если выполнение Процедуры прервано Пользователем;
     *  EnumProcedureResult.Exit если после выполнения Процедуры требуется завершить работу Оператор;
     *  EnumProcedureResult.ExitAndLogoff если после выполнения Процедуры требуется завершить сеанс пользователя;
     *  EnumProcedureResult.ExitAndHybernate если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *  EnumProcedureResult.ExitAndSleep если после выполнения Процедуры требуется перевести компьютер в спящий режим; 
     *  EnumProcedureResult.ExitAndReload если после выполнения Процедуры требуется перезагрузить компьютер;
     *  EnumProcedureResult.ExitAndShutdown если после выполнения Процедуры требуется выключить компьютер;
     */
    @OperatorProcedure(State = ImplementationState.NotTested, Title = "Test method", Description = "Test procedures engine method.")
    public static EnumProcedureResult testHelloWorld(Engine engine, String query, String[] args)
    {
        //print helloworld message to console and exit
        engine.get_OperatorConsole().PrintTextLine("helloworld", EnumDialogConsoleColor.Сообщение);
        engine.get_OperatorConsole().Beep();
        

        
        
        return EnumProcedureResult.Success;
    }
}
