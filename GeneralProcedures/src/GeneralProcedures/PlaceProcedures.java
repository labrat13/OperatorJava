/**
 * @author Селяков Павел
 * Created: 3 мая 2022 г. 12:50:00
 * State: 3 мая 2022 г. 12:50:00 - initial
 */
package GeneralProcedures;

import java.util.LinkedList;

import Lexicon.EnumDialogConsoleColor;
import Lexicon.EnumSpeakDialogResult;
import LogSubsystem.EnumLogMsgClass;
import LogSubsystem.EnumLogMsgState;
import OperatorEngine.ArgumentCollection;
import OperatorEngine.Engine;
import OperatorEngine.EnumProcedureResult;
import OperatorEngine.FuncArgument;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import OperatorEngine.UserQuery;
import ProcedureSubsystem.ImplementationState;
import ProcedureSubsystem.LibraryManagerBase;
import ProcedureSubsystem.OperatorProcedure;
import Utility.InOutArgument;

/**
 * Класс тестовых Процедур для Оператор.
 * 
 * @author Селяков Павел
 *
 */
@OperatorProcedure(State = ImplementationState.NotRealized,
    Title = "PlaceProcedures",
    Description = "Класс содержит Процедуры для операций с Местами.")
public class PlaceProcedures
{
    // Для всех операций с Процедурами и Местами из кода Процедур использовать класс ElementCacheManager, а не БД итп.
    // Пример: engine.get_ECM().AddPlace(p);

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
    
  //Заготовка функции обработчика Процедуры - скопируйте в нужный класс и выполните тодо.

//    /**
//     * NR-Обработчик процедуры Шаблон обработчика процедуры.
//     * 
//     * 
//     * @param engine
//     *            Ссылка на объект Движка Оператор для доступа к консоли, логу, БД итп.
//     * @param manager
//     *            Ссылка на объект Менеджера Библиотеки Процедур для доступа к инициализированным ресурсам библиотеки.
//     * @param query
//     *            Текст исходного запроса пользователя для возможной дополнительной обработки.
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
//    @OperatorProcedure(State = ImplementationState.NotRealized,   // TODO: заменить на актуальное
//            Title = "Название команды",   // TODO: заменить название команды на актуальное
//            Description = "Однострочное описание команды.")      // TODO: заменить описание команды на актуальное
//    public static EnumProcedureResult НазваниеШаблона(
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
//        // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.
//
//        EnumProcedureResult result = EnumProcedureResult.Success;
//        // название текущей процедуры для лога итп.
//        // TODO: указать здесь полный путь как название процедуры для вывода на экран.
//        String currentProcedureTitle = "НазваниеБиблиотеки.НазваниеКласса.НазваниеФункции";
//        // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
//        // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
//        try
//        {
//         // вывести в лог тестовое сообщение о начале процедуры
//            String str = String.format("Начата процедура %s(\"%s\")", currentProcedureTitle, args.getByIndex(0).get_ArgumentValue()); 
//            engine.AddMessageToConsoleAndLog(str, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.SubsystemEvent_Procedure, EnumLogMsgState.OK);
//
//            // TODO: код алгоритма добавить здесь
//
//            // TODO: вывести сообщение о результате операции: успешно
//            engine.get_OperatorConsole().PrintTextLine("Команда успешно завершена.", EnumDialogConsoleColor.Успех);
//        }
//        catch (Exception ex)
//        {
//            engine.PrintExceptionMessageToConsoleAndLog("Ошибка в процедуре " + currentProcedureTitle + "()", ex);
//            result = EnumProcedureResult.Error;
//        }
//
//        // вернуть флаг продолжения работы
//        return result;
//    }


    /**
     * NR-Обработчик процедуры Создать место НазваниеМеста.
     * 
     * 
     * @param engine
     *            Ссылка на объект Движка Оператор для доступа к консоли, логу, БД итп.
     * @param manager
     *            Ссылка на объект Менеджера Библиотеки Процедур для доступа к инициализированным ресурсам библиотеки.
     * @param query
     *            Текст исходного запроса пользователя для возможной дополнительной обработки.
     * @param args
     *            Массив аргументов Процедуры, соответствующий запросу.
     * @return Функция возвращает результат как одно из значений EnumProcedureResult:
     *         EnumProcedureResult.Success если Процедура выполнена успешно;
     *         EnumProcedureResult.WrongArguments если аргументы не подходят для запуска Процедуры;
     *         EnumProcedureResult.Error если произошла ошибка при выполнении Процедуры;
     *         EnumProcedureResult.CancelledByUser если выполнение Процедуры прервано Пользователем;
     *         EnumProcedureResult.Exit если после выполнения Процедуры требуется завершить работу Оператор;
     *         EnumProcedureResult.ExitAndLogoff если после выполнения Процедуры требуется завершить сеанс пользователя;
     *         EnumProcedureResult.ExitAndHybernate если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndSleep если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndReload если после выполнения Процедуры требуется перезагрузить компьютер;
     *         EnumProcedureResult.ExitAndShutdown если после выполнения Процедуры требуется выключить компьютер;
     */
    @OperatorProcedure(State = ImplementationState.NotRealized,   // TODO: заменить на актуальное
            Title = "Создать место НазваниеМеста",   
            Description = "Создать Место в БазаДанныхОператора.")
    public static EnumProcedureResult CommandCreatePlace(
            Engine engine,
            LibraryManagerBase manager,
            UserQuery query,
            ArgumentCollection args)
    {

        // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

        EnumProcedureResult result = EnumProcedureResult.Success;
        // название текущей процедуры для лога и вывода на экран.
        String currentProcedureTitle = "GeneralProcedures.PlaceProcedures.CommandCreatePlace";
        // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
        // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
        try
        {
         // вывести в лог тестовое сообщение о начале процедуры
            String str = String.format("Начата процедура %s(\"%s\")", currentProcedureTitle, args.getByIndex(0).get_ArgumentValue()); 
            engine.AddMessageToConsoleAndLog(str, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.SubsystemEvent_Procedure, EnumLogMsgState.OK);

            // TODO: код алгоритма добавить здесь

            // TODO: вывести сообщение о результате операции: успешно
            engine.get_OperatorConsole().PrintTextLine("Команда успешно завершена.", EnumDialogConsoleColor.Успех);
        }
        catch (Exception ex)
        {
            engine.PrintExceptionMessageToConsoleAndLog("Ошибка в процедуре " + currentProcedureTitle + "()", ex);
            result = EnumProcedureResult.Error;
        }

        // вернуть флаг продолжения работы
        return result;
    }
    
    /**
     * NR-Обработчик процедуры Показать Места.
     * 
     * 
     * @param engine
     *            Ссылка на объект Движка Оператор для доступа к консоли, логу, БД итп.
     * @param manager
     *            Ссылка на объект Менеджера Библиотеки Процедур для доступа к инициализированным ресурсам библиотеки.
     * @param query
     *            Текст исходного запроса пользователя для возможной дополнительной обработки.
     * @param args
     *            Массив аргументов Процедуры, соответствующий запросу.
     * @return Функция возвращает результат как одно из значений EnumProcedureResult:
     *         EnumProcedureResult.Success если Процедура выполнена успешно;
     *         EnumProcedureResult.WrongArguments если аргументы не подходят для запуска Процедуры;
     *         EnumProcedureResult.Error если произошла ошибка при выполнении Процедуры;
     *         EnumProcedureResult.CancelledByUser если выполнение Процедуры прервано Пользователем;
     *         EnumProcedureResult.Exit если после выполнения Процедуры требуется завершить работу Оператор;
     *         EnumProcedureResult.ExitAndLogoff если после выполнения Процедуры требуется завершить сеанс пользователя;
     *         EnumProcedureResult.ExitAndHybernate если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndSleep если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndReload если после выполнения Процедуры требуется перезагрузить компьютер;
     *         EnumProcedureResult.ExitAndShutdown если после выполнения Процедуры требуется выключить компьютер;
     */
    @OperatorProcedure(State = ImplementationState.NotTested,   // TODO: заменить на актуальное
            Title = "Показать места",   
            Description = "Вывести на экран список Мест Оператор")  
    public static EnumProcedureResult CommandListPlaces(
            Engine engine,
            LibraryManagerBase manager,
            UserQuery query,
            ArgumentCollection args)
    {

        // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

        EnumProcedureResult result = EnumProcedureResult.Success;
        // название текущей процедуры для лога итп.
        String currentProcedureTitle = "GeneralProcedures.PlaceProcedures.CommandListPlaces";
        // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
        // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
        try
        {
            // вывести это тестовое сообщение о начале процедуры на консоль и в лог
            String str = String.format("Начата процедура %s()", currentProcedureTitle);
            engine.AddMessageToConsoleAndLog(str, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.SubsystemEvent_Procedure, EnumLogMsgState.OK);
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintTextLine("Список всех Мест Оператора:", EnumDialogConsoleColor.Сообщение);
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintListOfPlaces();
            engine.get_OperatorConsole().PrintEmptyLine();
            // вывести сообщение о результате операции: успешно
            engine.get_OperatorConsole().PrintTextLine("Выведен список Мест", EnumDialogConsoleColor.Успех);
        }
        catch (Exception ex)
        {
            engine.PrintExceptionMessageToConsoleAndLog("Ошибка в процедуре " + currentProcedureTitle + "()", ex);
            result = EnumProcedureResult.Error;
        }

        // вернуть флаг продолжения работы
        return result;
    }
    
    /**
     * NR-Обработчик процедуры Удалить место НазваниеМеста.
     * 
     * 
     * @param engine
     *            Ссылка на объект Движка Оператор для доступа к консоли, логу, БД итп.
     * @param manager
     *            Ссылка на объект Менеджера Библиотеки Процедур для доступа к инициализированным ресурсам библиотеки.
     * @param query
     *            Текст исходного запроса пользователя для возможной дополнительной обработки.
     * @param args
     *            Массив аргументов Процедуры, соответствующий запросу.
     * @return Функция возвращает результат как одно из значений EnumProcedureResult:
     *         EnumProcedureResult.Success если Процедура выполнена успешно;
     *         EnumProcedureResult.WrongArguments если аргументы не подходят для запуска Процедуры;
     *         EnumProcedureResult.Error если произошла ошибка при выполнении Процедуры;
     *         EnumProcedureResult.CancelledByUser если выполнение Процедуры прервано Пользователем;
     *         EnumProcedureResult.Exit если после выполнения Процедуры требуется завершить работу Оператор;
     *         EnumProcedureResult.ExitAndLogoff если после выполнения Процедуры требуется завершить сеанс пользователя;
     *         EnumProcedureResult.ExitAndHybernate если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndSleep если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndReload если после выполнения Процедуры требуется перезагрузить компьютер;
     *         EnumProcedureResult.ExitAndShutdown если после выполнения Процедуры требуется выключить компьютер;
     */
    @OperatorProcedure(State = ImplementationState.NotTested,   // TODO: заменить на актуальное
            Title = "Удалить место НазваниеМеста",  
            Description = "Удалить указанное Место из БазаДанныхОператор") 
    public static EnumProcedureResult CommandDeletePlace(
            Engine engine,
            LibraryManagerBase manager,
            UserQuery query,
            ArgumentCollection args)
    {

        // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

        EnumProcedureResult result = EnumProcedureResult.Success;
        // название текущей процедуры для лога итп.
        String currentProcedureTitle = "GeneralProcedures.PlaceProcedures.CommandDeletePlace";
        // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
        // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
        try
        {
            // 1. Извлечь из аргумента название Места
            String placeTitle;
            FuncArgument arg = args.getByIndex(0);
            placeTitle = arg.get_ArgumentQueryValue().trim();// берем сырой текст аргумента из запроса

            // DONE: вывести это тестовое сообщение о начале процедуры - в лог!
            String str = String.format("Начата процедура %s(\"%s\")", currentProcedureTitle, placeTitle);
            engine.AddMessageToConsoleAndLog(str, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.SubsystemEvent_Procedure, EnumLogMsgState.OK);

            // 1. Извлечь из аргумента название команды
            engine.get_OperatorConsole().PrintTextLine(String.format("Название удаляемого Места: \"%s\"", placeTitle), EnumDialogConsoleColor.Сообщение);

            // TODO: проверить признак того, что вместо названия команды движком было подставлено название зарегистрированного места
            if (arg.get_АвтоподстановкаМеста() == true)
            {
                ; // TODO: обработать тут случай автоподстановки места вместо названия команды
                  // если он возникнет
            }

            // 2. извлечь из БД список команд с таким названием и показать пользователю.
            // без учета регистра символов
            // DONE: весь этот пункт 2 надо превратить в отдельную функцию выбора процедуры по ее названию, чтобы использовать ее во многих местах этого класса.

            Place place = null;

            InOutArgument outResult = new InOutArgument();// оболочка для строки-результата, передаваемого как аргумент.
            EnumProcedureResult epr = selectPlace(engine, placeTitle, outResult);
            if (epr == EnumProcedureResult.CancelledByUser)
                return epr;
            // если Место не найдено, selectPlace возвращает Error, а текущая процедура должна вернуть Success,
            // так как не ее проблема, что нечего удалять.
            else if (epr == EnumProcedureResult.Error)
                return EnumProcedureResult.Success;
            else
            {
                // set Place object
                place = outResult.getValuePlace();
            }
            // Тут в объекте place уже должна быть выбранное Место.

            // 3. выбранную пользователем Место - проверить, что оно может быть удалено
            // - она может быть удалена, если она находится в БД. Сейчас БиблиотекиПроцедур не позволяют удалять или редактировать свои Процедуры и Места.
            // - надо добавить в объект Процедуры и Места (Item) флаг ReadOnly, для БД он сброшен, для Библиотек Процедур он установлен.
            // - Флаг не должен храниться в БД, только в памяти.
            // - TODO: решено позже добавить в объект Item ссылку на объект Хранилища, унифицированный для БД и БиблиотекаПроцедур,
            // и из него запрашивать возможность удаления и изменения итемов. А пока только НазваниеХранилища для этого есть.
            if (place.isItemCanRemoved() == false)
            {
                // Если указанное Место не может быть удалено, вывести сообщение об этом и завершить текущую Процедуру успешно.
                String st2 = String.format("Выбранное Место \"%s\" не может быть удалено из его хранилища \"%s\"", placeTitle, place.get_Storage());
                engine.get_OperatorConsole().PrintTextLine(st2, EnumDialogConsoleColor.Предупреждение);
                return EnumProcedureResult.Success;
            }
            // else

            // 4. Показать пользователю свойства выбранного Места и запросить подтверждение удаления.
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintTextLine("Подтвердите удаление Места", EnumDialogConsoleColor.Сообщение);
            engine.get_OperatorConsole().PrintPlaceForm(place);
            // и запросить подтверждение пользователя, что он желает удалить Место.
            // Если пользователь ответит Да, надо удалить Место.
            // Если пользователь ответит Нет или Отмена, отменить операцию.
            EnumSpeakDialogResult sdr = engine.get_OperatorConsole().PrintДаНетОтмена("Желаете удалить Место?");
            if (sdr.isНет() || sdr.isОтмена())
                return EnumProcedureResult.CancelledByUser;
            // 5. Удалить команду.
            engine.get_ECM().RemovePlace(place);

            // 6. вывести сообщение о результате операции: успешно
            String msg6 = String.format("Место \"%s\" успешно удалено.", placeTitle);
            engine.get_OperatorConsole().PrintTextLine(msg6, EnumDialogConsoleColor.Успех);
        }
        catch (Exception ex)
        {
            engine.PrintExceptionMessageToConsoleAndLog("Ошибка в процедуре " + currentProcedureTitle + "()", ex);
            result = EnumProcedureResult.Error;
        }

        // вернуть флаг продолжения работы
        return result;
    }
    
    /**
     * NR-Обработчик процедуры Изменить место НазваниеМеста.
     * 
     * 
     * @param engine
     *            Ссылка на объект Движка Оператор для доступа к консоли, логу, БД итп.
     * @param manager
     *            Ссылка на объект Менеджера Библиотеки Процедур для доступа к инициализированным ресурсам библиотеки.
     * @param query
     *            Текст исходного запроса пользователя для возможной дополнительной обработки.
     * @param args
     *            Массив аргументов Процедуры, соответствующий запросу.
     * @return Функция возвращает результат как одно из значений EnumProcedureResult:
     *         EnumProcedureResult.Success если Процедура выполнена успешно;
     *         EnumProcedureResult.WrongArguments если аргументы не подходят для запуска Процедуры;
     *         EnumProcedureResult.Error если произошла ошибка при выполнении Процедуры;
     *         EnumProcedureResult.CancelledByUser если выполнение Процедуры прервано Пользователем;
     *         EnumProcedureResult.Exit если после выполнения Процедуры требуется завершить работу Оператор;
     *         EnumProcedureResult.ExitAndLogoff если после выполнения Процедуры требуется завершить сеанс пользователя;
     *         EnumProcedureResult.ExitAndHybernate если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndSleep если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndReload если после выполнения Процедуры требуется перезагрузить компьютер;
     *         EnumProcedureResult.ExitAndShutdown если после выполнения Процедуры требуется выключить компьютер;
     */
    @OperatorProcedure(State = ImplementationState.NotRealized,   // TODO: заменить на актуальное
            Title = "Изменить место НазваниеМеста",   // TODO: заменить название команды на актуальное
            Description = "Изменить указанное Место Оператора")      // TODO: заменить описание команды на актуальное
    public static EnumProcedureResult CommandChangePlace(
            Engine engine,
            LibraryManagerBase manager,
            UserQuery query,
            ArgumentCollection args)
    {

        // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

        EnumProcedureResult result = EnumProcedureResult.Success;
        // название текущей процедуры для лога итп.
        String currentProcedureTitle = "GeneralProcedures.PlaceProcedures.CommandChangePlace";
        // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
        // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
        try
        {
         // вывести в лог тестовое сообщение о начале процедуры
            String str = String.format("Начата процедура %s(\"%s\")", currentProcedureTitle, args.getByIndex(0).get_ArgumentValue()); 
            engine.AddMessageToConsoleAndLog(str, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.SubsystemEvent_Procedure, EnumLogMsgState.OK);

            // TODO: код алгоритма добавить здесь

            // TODO: вывести сообщение о результате операции: успешно
            engine.get_OperatorConsole().PrintTextLine("Команда успешно завершена.", EnumDialogConsoleColor.Успех);
        }
        catch (Exception ex)
        {
            engine.PrintExceptionMessageToConsoleAndLog("Ошибка в процедуре " + currentProcedureTitle + "()", ex);
            result = EnumProcedureResult.Error;
        }

        // вернуть флаг продолжения работы
        return result;
    }
    
    /**
     * NR-Обработчик процедуры Удалить все места.
     * 
     * 
     * @param engine
     *            Ссылка на объект Движка Оператор для доступа к консоли, логу, БД итп.
     * @param manager
     *            Ссылка на объект Менеджера Библиотеки Процедур для доступа к инициализированным ресурсам библиотеки.
     * @param query
     *            Текст исходного запроса пользователя для возможной дополнительной обработки.
     * @param args
     *            Массив аргументов Процедуры, соответствующий запросу.
     * @return Функция возвращает результат как одно из значений EnumProcedureResult:
     *         EnumProcedureResult.Success если Процедура выполнена успешно;
     *         EnumProcedureResult.WrongArguments если аргументы не подходят для запуска Процедуры;
     *         EnumProcedureResult.Error если произошла ошибка при выполнении Процедуры;
     *         EnumProcedureResult.CancelledByUser если выполнение Процедуры прервано Пользователем;
     *         EnumProcedureResult.Exit если после выполнения Процедуры требуется завершить работу Оператор;
     *         EnumProcedureResult.ExitAndLogoff если после выполнения Процедуры требуется завершить сеанс пользователя;
     *         EnumProcedureResult.ExitAndHybernate если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndSleep если после выполнения Процедуры требуется перевести компьютер в спящий режим;
     *         EnumProcedureResult.ExitAndReload если после выполнения Процедуры требуется перезагрузить компьютер;
     *         EnumProcedureResult.ExitAndShutdown если после выполнения Процедуры требуется выключить компьютер;
     */
    @OperatorProcedure(State = ImplementationState.NotTested,   // TODO: заменить на актуальное
            Title = "Удалить все места",   
            Description = "Удалить все Места из БазаДанныхОператора.")
    public static EnumProcedureResult CommandDeleteAllPlaces(
            Engine engine,
            LibraryManagerBase manager,
            UserQuery query,
            ArgumentCollection args)
    {

        // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

        EnumProcedureResult result = EnumProcedureResult.Success;
        // название текущей процедуры для лога итп.
        String currentProcedureTitle = "GeneralProcedures.PlaceProcedures.CommandDeleteAllPlaces";
        // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
        // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
        try
        {
            //1. Запросить от пользователя подтверждение о удалении всех Мест из БД. Да - Нет и Отмена.
            //если пользователь ответил Да - удалить все Места из БД
            //если пользователь ответил Нет или Отмена - прервать операцию. 
            engine.get_OperatorConsole().PrintEmptyLine();          
            engine.get_OperatorConsole().PrintTextLine("1. Подтвердите удаление всех мест из БазаДанныхОператора", EnumDialogConsoleColor.Сообщение);
            EnumSpeakDialogResult esdr = engine.get_OperatorConsole().PrintДаНетОтмена("Желаете удалить все Места?");
            if (esdr.isНет() || esdr.isОтмена())
                return EnumProcedureResult.CancelledByUser;
            // выполнить удаление всех Мест из БД Оператора
            engine.get_ECM().RemoveAllPlacesFromDatabase();          
            // вывести сообщение о результате операции: успешно
            engine.get_OperatorConsole().PrintTextLine("Удаление всех Мест завершено успешно", EnumDialogConsoleColor.Успех);
        }
        catch (Exception ex)
        {
            engine.PrintExceptionMessageToConsoleAndLog("Ошибка в процедуре " + currentProcedureTitle + "()", ex);
            result = EnumProcedureResult.Error;
        }

        // вернуть флаг продолжения работы
        return result;
    }
    
    // *** Вспомогательные процедуры ***

    /**
     * NT-Select Place by Title.
     * 
     * @param engine
     *            Engine object.
     * @param title
     *            Place title.
     * @param outResult
     *            Result Place in shell object.
     * @return Function returns EnumProcedureResult.Success if success, Error if procedure not found, CancelledByUser if cancelled.
     * @throws Exception
     *             Error occured.
     */
    private static EnumProcedureResult selectPlace(
            Engine engine,
            String title,
            InOutArgument outResult) throws Exception
    {
        Place p = null;
        LinkedList<Place> lip = engine.get_ECM().get_PlaceCollection().getByTitle(title);
        int lipSize = lip.size();
        if (lipSize == 0)
        {
            // тут вывести сообщение, что указанное Место не найдено и завершить процедуру успешно.
            String stmp = String.format("Указанное Место \"%s\" не найдено.", title);
            engine.get_OperatorConsole().PrintTextLine(stmp, EnumDialogConsoleColor.Предупреждение);
            return EnumProcedureResult.Error;
        }
        else if (lipSize == 1)
        {
            p = lip.get(0);
        }
        else // if(lipSize > 1)
        {
            // тут вывести пользователю найденные Места с тем же названием
            engine.get_OperatorConsole().PrintTextLine("Существующие Места с таким названием:", EnumDialogConsoleColor.Предупреждение);
            // Если в списке более одного Места, то пользователь должен выбрать одно из них для удаления.
            // для этого в списке Мест нужно показать уникальный ИД Места, источник-хранилище, категорию, название и описание.
            // И флаг, возможно ли удаление данного Места - это определяется Хранилищем.
            // Для выбора из нескольких Мест надо запросить у пользователя ИД Места, но проблема в том, что ид сейчас есть только у объектов из БД.
            // - можно вывести порядковый номер в этом списке и запросить у пользователя его.
            engine.get_OperatorConsole().PrintPlaceFormNumberedList(lip);
            // запросить у пользователя порядковый номер элемента списка
            int lip_index = engine.get_OperatorConsole().InputListIndex(lipSize);
            // обработать указанный пользователем индекс
            if (lip_index < 1)
            {
                // Если пользователь отказался выбрать элемент списка, вывести сообщение об этом и завершить текущую Процедуру Отменено пользователем.
                engine.get_OperatorConsole().PrintTextLine("Операция отменена, поскольку пользователь не смог выбрать Место из списка.", EnumDialogConsoleColor.Сообщение);
                return EnumProcedureResult.CancelledByUser;
            }
            else p = lip.get(lip_index);// выбранный пользователем объект команды.
        }

        // return
        outResult.setValue(p);
        return EnumProcedureResult.Success;
    }
    
    //*** End of file ***
}
