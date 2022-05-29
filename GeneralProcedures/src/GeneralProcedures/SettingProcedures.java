/**
 * @author Селяков Павел
 * Created: 3 мая 2022 г. 12:49:41
 * State: 3 мая 2022 г. 12:49:41 - initial
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
import OperatorEngine.Procedure;
import OperatorEngine.UserQuery;
import ProcedureSubsystem.ImplementationState;
import ProcedureSubsystem.LibraryManagerBase;
import ProcedureSubsystem.OperatorProcedure;
import Settings.SettingItem;
import Utility.InOutArgument;

/**
 * Класс тестовых Процедур для Оператор.
 * 
 * @author Селяков Павел
 *
 */
@OperatorProcedure(State = ImplementationState.NotTested,
     Title = "SettingProcedures",
     Description = "Класс содержит Процедуры для операций с Настройками Оператора.")
public class SettingProcedures
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

//  /**
//  * NR-Обработчик процедуры Шаблон обработчика процедуры.
//  * 
//  * 
//  * @param engine
//  *            Ссылка на объект Движка Оператор для доступа к консоли, логу, БД итп.
//  * @param manager
//  *            Ссылка на объект Менеджера Библиотеки Процедур для доступа к инициализированным ресурсам библиотеки.
//  * @param query
//  *            Текст исходного запроса пользователя для возможной дополнительной обработки.
//  * @param args
//  *            Массив аргументов Процедуры, соответствующий запросу.
//  * @return Функция возвращает результат как одно из значений EnumProcedureResult:
//  *         EnumProcedureResult.Success если Процедура выполнена успешно;
//  *         EnumProcedureResult.WrongArguments если аргументы не подходят для запуска Процедуры;
//  *         EnumProcedureResult.Error если произошла ошибка при выполнении Процедуры;
//  *         EnumProcedureResult.CancelledByUser если выполнение Процедуры прервано Пользователем;
//  *         EnumProcedureResult.Exit если после выполнения Процедуры требуется завершить работу Оператор;
//  *         EnumProcedureResult.ExitAndLogoff если после выполнения Процедуры требуется завершить сеанс пользователя;
//  *         EnumProcedureResult.ExitAndHybernate если после выполнения Процедуры требуется перевести компьютер в спящий режим;
//  *         EnumProcedureResult.ExitAndSleep если после выполнения Процедуры требуется перевести компьютер в спящий режим;
//  *         EnumProcedureResult.ExitAndReload если после выполнения Процедуры требуется перезагрузить компьютер;
//  *         EnumProcedureResult.ExitAndShutdown если после выполнения Процедуры требуется выключить компьютер;
//  */
// @OperatorProcedure(State = ImplementationState.NotRealized,   // TODO: заменить на актуальное
//         Title = "Название команды",   // TODO: заменить название команды на актуальное
//         Description = "Однострочное описание команды.")      // TODO: заменить описание команды на актуальное
// public static EnumProcedureResult НазваниеШаблона(
//         Engine engine,
//         LibraryManagerBase manager,
//         UserQuery query,
//         ArgumentCollection args)
// {
//     /*
//      * 07042022 - Добавлена возможность внутри Процедуры изменять текст запроса,
//      * чтобы применить новый текст запроса к дальнейшему поиску Процедур.
//      * Изменение запроса не перезапускает поиск Процедур (в текущей версии Оператора).
//      * Поэтому изменять запрос следует только в хорошо продуманных случаях.
//      * 
//      * Пример вызова функции переопределения запроса, с выводом в лог старого и нового значений.
//      * Example: query.ChangeQuery(engine, "New query text");
//      */
//
//     // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.
//
//     EnumProcedureResult result = EnumProcedureResult.Success;
//     // название текущей процедуры для лога итп.
//     // TODO: указать здесь полный путь как название процедуры для вывода на экран.
//     String currentProcedureTitle = "НазваниеБиблиотеки.НазваниеКласса.НазваниеФункции";
//     // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
//     // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
//     try
//     {
//      // вывести в лог тестовое сообщение о начале процедуры
//         String str = String.format("Начата процедура %s(\"%s\")", currentProcedureTitle, args.getByIndex(0).get_ArgumentValue()); 
//         engine.AddMessageToConsoleAndLog(str, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.SubsystemEvent_Procedure, EnumLogMsgState.OK);
//
//         // TODO: код алгоритма добавить здесь
//
//         // TODO: вывести сообщение о результате операции: успешно
//         engine.get_OperatorConsole().PrintTextLine("Команда успешно завершена.", EnumDialogConsoleColor.Успех);
//     }
//     catch (Exception ex)
//     {
//         engine.PrintExceptionMessageToConsoleAndLog("Ошибка в процедуре " + currentProcedureTitle + "()", ex);
//         result = EnumProcedureResult.Error;
//     }
//
//     // вернуть флаг продолжения работы
//     return result;
// }

  /**
  * NR-Обработчик процедуры Создать настройку НазваниеНастройки.
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
         Title = "Создать настройку НазваниеНастройки",   
         Description = "Создать Настройку в БазаДанныхОператора.")  
 public static EnumProcedureResult CommandCreateSetting(
         Engine engine,
         LibraryManagerBase manager,
         UserQuery query,
         ArgumentCollection args)
 {

     // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

     EnumProcedureResult result = EnumProcedureResult.Success;
     // название текущей процедуры для лога итп.
     String currentProcedureTitle = "GeneralProcedures.SettingProcedures.CommandCreateSetting";
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
 * NR-Обработчик процедуры Показать настройки.
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
        Title = "Показать настройки",   
        Description = "Вывести на экран список Настроек Оператора") 
public static EnumProcedureResult CommandListSettings(
        Engine engine,
        LibraryManagerBase manager,
        UserQuery query,
        ArgumentCollection args)
{
    // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

    EnumProcedureResult result = EnumProcedureResult.Success;
    // название текущей процедуры для лога итп.
    String currentProcedureTitle = "GeneralProcedures.SettingProcedures.CommandListSettings";
    // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
    // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
    try
    {
        // вывести это тестовое сообщение о начале процедуры на консоль и в лог
        String str = String.format("Начата процедура %s()", currentProcedureTitle);
        engine.AddMessageToConsoleAndLog(str, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.SubsystemEvent_Procedure, EnumLogMsgState.OK);
        engine.get_OperatorConsole().PrintEmptyLine();
        engine.get_OperatorConsole().PrintTextLine("Список всех Настроек Оператора:", EnumDialogConsoleColor.Сообщение);
        engine.get_OperatorConsole().PrintEmptyLine();
        engine.get_OperatorConsole().PrintListOfSettings();
        engine.get_OperatorConsole().PrintEmptyLine();
        // вывести сообщение о результате операции: успешно
        engine.get_OperatorConsole().PrintTextLine("Выведен список Настроек", EnumDialogConsoleColor.Успех);
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
* NR-Обработчик процедуры Удалить Настройку НазваниеНастройки.
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
       Title = "Удалить настройку НазваниеНастройки",   
       Description = "Удалить указанную Настройку из БазаДанныхОператора") 
public static EnumProcedureResult CommandDeleteSetting(
       Engine engine,
       LibraryManagerBase manager,
       UserQuery query,
       ArgumentCollection args)
{
   // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

   EnumProcedureResult result = EnumProcedureResult.Success;
   // название текущей процедуры для лога итп.
   String currentProcedureTitle = "GeneralProcedures.SettingProcedures.CommandDeleteSetting";
   // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
   // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
   try
   {
       // 1. Извлечь из аргумента название Настройки
       String settingTitle;
       FuncArgument arg = args.getByIndex(0);
       settingTitle = arg.get_ArgumentQueryValue().trim();// берем сырой текст аргумента из запроса

       // DONE: вывести это тестовое сообщение о начале процедуры - в лог!
       String str = String.format("Начата процедура %s(\"%s\")", currentProcedureTitle, settingTitle);
       engine.AddMessageToConsoleAndLog(str, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.SubsystemEvent_Procedure, EnumLogMsgState.OK);

       // 1. Извлечь из аргумента название Настройки
       engine.get_OperatorConsole().PrintTextLine(String.format("Название удаляемой Настройки: \"%s\"", settingTitle), EnumDialogConsoleColor.Сообщение);

       // TODO: проверить признак того, что вместо названия Настройки движком было подставлено название зарегистрированного места
       if (arg.get_АвтоподстановкаМеста() == true)
       {
           ; // TODO: обработать тут случай автоподстановки места вместо названия Настройки
             // если он возникнет
       }

       // 2. извлечь из БД список Настроек с таким названием и показать пользователю.
       // без учета регистра символов
       // DONE: весь этот пункт 2 надо превратить в отдельную функцию выбора Настройки по ее названию, чтобы использовать ее во многих местах этого класса.

       SettingItem sett = null;

       InOutArgument outResult = new InOutArgument();// оболочка для строки-результата, передаваемого как аргумент.
       EnumProcedureResult epr = selectSetting(engine, settingTitle, outResult);
       if (epr == EnumProcedureResult.CancelledByUser)
           return epr;
       // если Настройка не найдена, selectSetting возвращает Error, а эта процедура должна вернуть Success,
       // так как не ее проблема, что нечего удалять.
       else if (epr == EnumProcedureResult.Error)
           return EnumProcedureResult.Success;
       else
       {
           // set SettingItem object
           sett = outResult.getValueSettingItem();
       }
       // Тут в объекте sett уже должна быть выбранная Настройка.

       // 3. выбранную пользователем Настройку - проверить, что она может быть удалена
       // - она может быть удалена, если она находится в БД. Сейчас БиблиотекиПроцедур не позволяют удалять или редактировать свои Процедуры и Места.
       // - надо добавить в объект Процедуры и Места (Item) флаг ReadOnly, для БД он сброшен, для Библиотек Процедур он установлен.
       // - Флаг не должен храниться в БД, только в памяти.
       // - TODO: решено позже добавить в объект Item ссылку на объект Хранилища, унифицированный для БД и БиблиотекаПроцедур,
       // и из него запрашивать возможность удаления и изменения итемов.
       if (sett.isItemCanRemoved() == false)
       {
           // Если указанная Настройка не может быть удалена, вывести сообщение об этом и завершить текущую Процедуру успешно.
           String st2 = String.format("Выбранная настройка \"%s\" не может быть удалена из ее хранилища \"%s\"", settingTitle, sett.get_Storage());
           engine.get_OperatorConsole().PrintTextLine(st2, EnumDialogConsoleColor.Предупреждение);
           return EnumProcedureResult.Success;
       }
       // else

       // 4. Показать пользователю свойства выбранной Настройки и запросить подтверждение удаления.
       engine.get_OperatorConsole().PrintEmptyLine();
       engine.get_OperatorConsole().PrintTextLine("Подтвердите удаление Настройки", EnumDialogConsoleColor.Сообщение);
       engine.get_OperatorConsole().PrintSettingForm(sett);
       // и запросить подтверждение пользователя, что он желает удалить Настройку.
       // Если пользователь ответит Да, надо удалить Настройку.
       // Если пользователь ответит Нет или Отмена, отменить операцию.
       EnumSpeakDialogResult sdr = engine.get_OperatorConsole().PrintДаНетОтмена("Желаете удалить Настройку?");
       if (sdr.isНет() || sdr.isОтмена())
           return EnumProcedureResult.CancelledByUser;
       // 5. Удалить команду.
       engine.get_ECM().RemoveSetting(sett);

       // 6. вывести сообщение о результате операции: успешно
       String msg6 = String.format("Настройка \"%s\" успешно удалена.", settingTitle);
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
* NR-Обработчик процедуры Изменить настройку НазваниеНастройки.
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
       Title = "Изменить настройку НазваниеНастройки",   
       Description = "Изменить указанную Настройку Оператора")
public static EnumProcedureResult CommandChangeSetting(
       Engine engine,
       LibraryManagerBase manager,
       UserQuery query,
       ArgumentCollection args)
{
   // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

   EnumProcedureResult result = EnumProcedureResult.Success;
   // название текущей процедуры для лога итп.
   String currentProcedureTitle = "GeneralProcedures.SettingProcedures.CommandChangeSetting";
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
* NR-Обработчик процедуры Удалить все настройки.
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
       Title = "Удалить все настройки",   
       Description = "Удалить все Настройки из БазаДанныхОператора") 
public static EnumProcedureResult CommandDeleteAllSettings(
       Engine engine,
       LibraryManagerBase manager,
       UserQuery query,
       ArgumentCollection args)
{
   // TODO: Не забудьте добавить эту Процедуру в LibraryManager.getLibraryProcedures() функцию, чтобы она была добавлена в Оператор.

   EnumProcedureResult result = EnumProcedureResult.Success;
   // название текущей процедуры для лога итп.
   String currentProcedureTitle = "GeneralProcedures.SettingProcedures.CommandDeleteAllSetting";
   // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
   // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
   try
   {
       //1. Запросить от пользователя подтверждение о удалении всех Настроек из БД. Да - Нет и Отмена.
       //если пользователь ответил Да - удалить все Настройки из БД
       //если пользователь ответил Нет или Отмена - прервать операцию. 
       engine.get_OperatorConsole().PrintEmptyLine();          
       engine.get_OperatorConsole().PrintTextLine("1. Подтвердите удаление всех Настроек из БазаДанныхОператора", EnumDialogConsoleColor.Сообщение);
       EnumSpeakDialogResult esdr = engine.get_OperatorConsole().PrintДаНетОтмена("Желаете удалить все настройки?");
       if (esdr.isНет() || esdr.isОтмена())
           return EnumProcedureResult.CancelledByUser;
       // выполнить удаление всех Настроек из БД Оператора
       engine.get_ECM().RemoveAllSettingsFromDatabase();          
       // вывести сообщение о результате операции: успешно
       engine.get_OperatorConsole().PrintTextLine("Удаление всех Настроек завершено успешно", EnumDialogConsoleColor.Успех);
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
 * NT-Select Procedure by Title.
 * 
 * @param engine
 *            Engine object.
 * @param title
 *            Setting title.
 * @param outResult
 *            Result SettingItem in shell object.
 * @return Function returns EnumProcedureResult.Success if success, Error if procedure not found, CancelledByUser if cancelled.
 * @throws Exception
 *             Error occured.
 */
private static EnumProcedureResult selectSetting(
        Engine engine,
        String title,
        InOutArgument outResult) throws Exception
{
    SettingItem p = null;
    LinkedList<SettingItem> lip = engine.get_ECM().get_SettingCollection().getByTitle(title);
    int lipSize = lip.size();
    if (lipSize == 0)
    {
        // тут вывести сообщение, что указанная Настройка не найдена и завершить процедуру успешно.
        String stmp = String.format("Указанная Настройка \"%s\" не найдена.", title);
        engine.get_OperatorConsole().PrintTextLine(stmp, EnumDialogConsoleColor.Предупреждение);
        return EnumProcedureResult.Error;
    }
    else if (lipSize == 1)
    {
        p = lip.get(0);
    }
    else // if(lipSize > 1)
    {
        // тут вывести пользователю найденные Настройки с тем же названием
        engine.get_OperatorConsole().PrintTextLine("Существующие Настройки с таким названием:", EnumDialogConsoleColor.Предупреждение);
        // Если в списке более одной Настройки, то пользователь должен выбрать одну из них для удаления.
        // для этого в списке Команд нужно показать уникальный ИД Настройки, источник-хранилище, категорию, название и описание.
        // И флаг, возможно ли удаление данной Настройки - это определяется Хранилищем.
        // Для выбора из нескольких Настроек надо запросить у пользователя ИД Настройки, но проблема в том, что ид сейчас есть только у объектов из БД.
        // - можно вывести порядковый номер в этом списке и запросить у пользователя его.
        engine.get_OperatorConsole().PrintSettingFormNumberedList(lip);
        // запросить у пользователя порядковый номер элемента списка
        int lip_index = engine.get_OperatorConsole().InputListIndex(lipSize);
        // обработать указанный пользователем индекс
        if (lip_index < 1)
        {
            // Если пользователь отказался выбрать элемент списка, вывести сообщение об этом и завершить текущую Процедуру Отменено пользователем.
            engine.get_OperatorConsole().PrintTextLine("Операция отменена, поскольку пользователь не смог выбрать Настройку из списка.", EnumDialogConsoleColor.Сообщение);
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
