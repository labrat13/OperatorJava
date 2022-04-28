/**
 * @author Селяков Павел
 *         Created: 28 апр. 2022 г. 21:51:15
 *         State: 28 апр. 2022 г. 21:51:15 - initial
 */
package GeneralProcedures;

import ProcedureSubsystem.OperatorProcedure;

import java.util.LinkedList;

import Lexicon.Dialogs;
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

// DONE: Класс методов для Процедур должен быть помечен аннотацией OperatorProcedure с ImplementationState = NotTested либо Ready, чтобы его методы можно было
// вызывать в качестве процедур.
// Класс может также содержать любые элементы, необходимые для методов Процедур.

/**
 * NR-Класс операций с Процедурами в БазаДанныхОператора.
 * 
 * @author Селяков Павел
 *
 */

@OperatorProcedure(State = ImplementationState.NotTested,
        Title = "Класс операций Процедур.",
        Description = "Класс операций с Процедурами в БазаДанныхОператора.")
public class ProcedureProcedures
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

    /**
     * NT- Процедура создания новой Процедуры в БазаДанныхОператора.
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
     * @throws Exception
     *             Ошибка при исполнении Процедуры.
     */
    @OperatorProcedure(State = ImplementationState.NotTested,
            Title = "Создать Команду",
            Description = "Создать Процедуру в БазаДанныхОператора.")
    public static EnumProcedureResult CommandCreateProcedure(
            Engine engine,
            LibraryManagerBase manager,
            UserQuery query,
            ArgumentCollection args) throws Exception
    {
        /*
         * 07042022 - Добавлена возможность внутри Процедуры изменять текст запроса,
         * чтобы применить новый текст запроса к дальнейшему поиску Процедур.
         * Изменение запроса не перезапускает поиск Процедур (в текущей версии Оператора).
         * Поэтому изменять запрос следует только в хорошо продуманных случаях.
         * 
         * Пример вызова функции переопределения запроса, с выводом в лог старого и нового значений.
         * Example: query.ChangeQuery(engine, "New query text");
         */

        // содержимое списка аргументов
        // args[0].name = "команда" - название аргумента в строке регекса команды
        // args[0].value = "Скачать файл ХХХ" - значение аргумента - название создаваемого места
        // args[0].type = "" - тип аргумента - TODO: сейчас не указывается, так как мне лень думать

        EnumProcedureResult result = EnumProcedureResult.Success;
        // название текущей процедуры для лога итп.
        String currentProcedureTitle = "GeneralProcedures.ProcedureProcedures.CommandCreateProcedure";
        // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
        // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
        try
        {

            String str = String.format("Начата процедура %s(\"%s\")", currentProcedureTitle, args.getByIndex(0).get_ArgumentValue());
            // DONE: вывести это тестовое сообщение о начале процедуры - в лог!
            // engine.get_OperatorConsole().PrintTextLine(str, EnumDialogConsoleColor.Сообщение); заменено на:
            engine.AddMessageToConsoleAndLog(str, EnumDialogConsoleColor.Сообщение, EnumLogMsgClass.SubsystemEvent_Procedure, EnumLogMsgState.OK);

            Procedure proc = new Procedure();// новый пустой объект для заполнения

            // Как создать процедуру создания Процедуры?
            // 1 извлечь из аргументов название Процедуры, если оно есть
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintTextLine("1. Название Команды", EnumDialogConsoleColor.Сообщение);

            // извлечь название процедуры из аргумента
            FuncArgument arg = args.getByIndex(0);
            str = arg.get_ArgumentQueryValue().trim();// берем сырой текст аргумента из запроса
            engine.get_OperatorConsole().PrintTextLine(String.format("Название новой Команды: \"%s\"", str), EnumDialogConsoleColor.Сообщение);

            // TODO: проверить признак того, что вместо названия процедуры движком было подставлено название зарегистрированного места
            if (arg.get_АвтоподстановкаМеста() == true)
            {
                ; // TODO: обработать тут случай автоподстановки места вместо названия команды
                  // если он возникнет
            }

            // 2 проверить что в БД нет Процедуры с таким названием, без учета регистра символов
            // TODO: я просто скопировал этот код из похожей процедуры создания мест, и не знаю, подойдет ли он.

            boolean notUnicalProcedure = false;
            while (true)
            {
                // если название команды - пустая строка, вывести сообщение и перейти к приему нового названия команды
                if (OperatorEngine.Utility.StringIsNullOrEmpty(str))
                    engine.get_OperatorConsole().PrintTextLine("Пустая строка недопустима для названия Команды!", EnumDialogConsoleColor.Предупреждение);
                else
                {
                    // проверить что в БД нет Процедуры с таким названием, без учета регистра символов
                    // LinkedList<Procedure> lip = engine.DbGetProceduresByTitle(str); заменено на:
                    LinkedList<Procedure> lip = engine.get_ECM().get_ProcedureCollection().getByTitle(str);
                    notUnicalProcedure = (lip.size() > 0);// временный флаг для упрощения проверок позже
                    if (notUnicalProcedure)
                    {
                        // тут вывести пользователю найденные команды с тем же названием
                        engine.get_OperatorConsole().PrintTextLine("Команды с таким названием уже существуют:", EnumDialogConsoleColor.Предупреждение);
                        for (Procedure pp : lip)
                        {
                            engine.get_OperatorConsole().PrintProcedureShortLine(pp);
                        }
                        engine.get_OperatorConsole().PrintTextLine("Дубликаты Команд недопустимы!", EnumDialogConsoleColor.Предупреждение);
                        lip.clear();// очистить временный список, поскольку он в цикле
                    }
                }
                if (str.isEmpty() || (notUnicalProcedure == true))
                {
                    // Раз есть такие Процедуры, пользователь должен сменить название Процедуры прямо тут же
                    // или же завершить диалог Отменой создания команды
                    str = engine.get_OperatorConsole().PrintQuestionAnswer(EnumSpeakDialogResult.Отмена, "Введите новое название Команды:", false, true);
                    if (Dialogs.этоОтмена(str))
                        return EnumProcedureResult.CancelledByUser;
                }
                else break;// end while loop
            }// while loop
             // Тут мы окажемся, если название Процедуры уникальное
            proc.set_Title(str);

            // 2 Пользователь должен ввести описание процедуры
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintTextLine("2. Описание Команды", EnumDialogConsoleColor.Сообщение);
            str = engine.get_OperatorConsole().PrintQuestionAnswer(EnumSpeakDialogResult.Отмена, "Введите краткое описание Команды:", true, true);
            if (Dialogs.этоОтмена(str))
                return EnumProcedureResult.CancelledByUser;
            proc.set_Description(str);

            // 3. Пользователь должен ввести регекс процедуры
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintTextLine("3. Регекс Команды", EnumDialogConsoleColor.Сообщение);
            // TODO: для Пользователя нужно вывести краткую справку с примерами регекса,
            // простым и сложным форматом регекса. Целую краткую инструкцию и примеры
            String[] regexDescr = new String[] {
                    " - Команда будет выбрана для исполнения, если ее Регекс опознает текст, введенный Пользователем",
                    " - Простой Регекс содержит текст Команды и аргументы. Аргумент обозначается словом с знаком % перед ним.",
                    " - Название аргумента должно содержать только латинские буквы, цифры, знак_подчеркивания.",
                    " - Например: Открыть сайт %arg_1",
                    " - Простой регекс может быть и без аргументов, например: Выключить компьютер",
                    " - Сложный Регекс это специально форматированный текст. ",
                    " - Обратитесь к документации, чтобы узнать больше о Регексе Команды",
                    "" };
            engine.get_OperatorConsole().PrintTextLines(regexDescr, EnumDialogConsoleColor.Сообщение);

            str = engine.get_OperatorConsole().PrintQuestionAnswer(EnumSpeakDialogResult.Отмена, "Введите регекс для Команды:", true, true);
            if (Dialogs.этоОтмена(str))
                return EnumProcedureResult.CancelledByUser;
            // TODO: я пока не знаю, как проверить регекс в этом месте. Поэтому просто поверим, что пользователь все сделал правильно.
            proc.set_Regex(str);

            // 4. Пользователь должен ввести путь к процедуре
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintTextLine("4. Адрес Процедуры Команды", EnumDialogConsoleColor.Сообщение);
            // TODO: для Пользователя нужно вывести краткую справку с примерами путей
            String[] adresDescr = new String[] {
                    " - Описывает командную строку исполняемого файла или путь к Процедуре Команды в Сборке Процедур",
                    " - Для исполняемых файлов, используемых в качестве Процедур, путь может содержать аргументы.",
                    "   Например: \"/home/username/firefox/firefox.sh %www\"",
                    "   Аргументы идентифицируются по своим именам, заданным в Регексе Команды.",
                    " - Для Процедур из СборкиПроцедур прописывается путь в формате СборкаПроцедур.Класс.Функция().",
                    "   Аргументы идентифицируются внутри кода функции Процедуры, по своим именам, заданным в Регексе Команды.",
                    "   Например: ProceduresInt.ProcedureProcedures.CommandCreateProcedure()",
                    " - Обратитесь к документации, чтобы узнать больше о Адресе Процедуры Команды",
                    "" };
            engine.get_OperatorConsole().PrintTextLines(adresDescr, EnumDialogConsoleColor.Сообщение);

            str = engine.get_OperatorConsole().PrintQuestionAnswer(EnumSpeakDialogResult.Отмена, "Введите адрес Процедуры для Команды:", true, true);
            if (Dialogs.этоОтмена(str))
                return EnumProcedureResult.CancelledByUser;
            // TODO: надо проверить что файл или сборка, класс, функция - существуют, и если нет - сообщить об этом пользователю.
            // но это не критичная ошибка - пользователь может заново ввести или применить это значение, несмотря на.
            proc.set_Path(str);

            // 5. Пользователь должен ввести Вес процедуры
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintTextLine("5. Вес Команды", EnumDialogConsoleColor.Сообщение);
            // для Пользователя нужно вывести краткую справку по Вес процедуры
            String[] vesDescr = new String[] {
                    " - Вес определяет порядок выбора для исполнения одной из Команд, подходящих по Регексу",
                    " - Команда с наибольшим Весом будет выполнена последней",
                    " - Вес должен быть больше 0.0 и меньше 1.0",
                    " - Для новой Команды рекомендуется значение 0.5",
                    "" };
            engine.get_OperatorConsole().PrintTextLines(vesDescr, EnumDialogConsoleColor.Сообщение);
            // ввод значения
            boolean isValidValue = false;
            str = engine.get_OperatorConsole().PrintQuestionAnswer(EnumSpeakDialogResult.Отмена, "Введите Вес Команды:", true, true);
            if (Dialogs.этоОтмена(str))
                return EnumProcedureResult.CancelledByUser;
            do
            {
                // TODO: надо проверить что введенное значение конвертируется в Double, больше 0 и меньше 1.
                isValidValue = Procedure.IsValidVesFormat(str);
                // если значение веса неправильное, то сообщить об этом и запросить новое значение, в цикле, пока пользователь не отменит всю команду.
                if (isValidValue == false)
                {
                    engine.get_OperatorConsole().PrintTextLine("Это значение Веса является недопустимым!", EnumDialogConsoleColor.Предупреждение);
                    str = engine.get_OperatorConsole().PrintQuestionAnswer(EnumSpeakDialogResult.Отмена, "Введите новое значение Веса для Команды:", true, true);
                    if (Dialogs.этоОтмена(str))
                        return EnumProcedureResult.CancelledByUser;
                }
            }
            while (isValidValue == false);

            // proc.Ves = Double.Parse(str, engine.OperatorConsole.RuCulture); заменено на :
            Double ves = Double.valueOf(str);
            proc.set_Ves(ves);
            // 6. Вывести свойства Процедуры и запросить подтверждение создания процедуры.
            // Вроде все свойства должны быть заполнены, теперь надо вывести все их в форме
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintTextLine("6. Подтвердите создание Команды", EnumDialogConsoleColor.Сообщение);// пункт плана
            engine.get_OperatorConsole().PrintProcedureForm(proc);
            // и запросить подтверждение пользователя, что он желает создать Место
            // Если пользователь ответит Да, надо создать место.
            // Если пользователь ответит Нет или Отмена, отменить операцию.
            EnumSpeakDialogResult sdr = engine.get_OperatorConsole().PrintДаНетОтмена("Желаете создать новую Команду?");
            if (sdr.isНет() || sdr.isОтмена())
                return EnumProcedureResult.CancelledByUser;

            // 9 заполнить объект Процедуры и создать новую процедуру в БД
            // сначала добавить название хранилища в объект Процедуры - согласно концепту Библиотек Процедур Оператора.
            proc.set_Storage(Procedure.StorageKeyForDatabaseItem);
            // engine.DbInsertProcedure(proc); заменено на:
            engine.get_ECM().AddProcedure(proc);

            // 10 вывести сообщение о результате операции: успешно
            engine.get_OperatorConsole().PrintTextLine(String.format("Команда \"%s\" создана успешно", proc.get_Title()), EnumDialogConsoleColor.Успех);
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
     * NT- Обработчик процедуры вывода пользователю списка Команд.
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
    @OperatorProcedure(State = ImplementationState.NotTested,
            Title = "Показать список Команд.",
            Description = "Вывести на экран список доступных Команд Оператора.")
    public static EnumProcedureResult CommandListProcedures(
            Engine engine,
            LibraryManagerBase manager,
            UserQuery query,
            ArgumentCollection args)
    {
        /*
         * 07042022 - Добавлена возможность внутри Процедуры изменять текст запроса,
         * чтобы применить новый текст запроса к дальнейшему поиску Процедур.
         * Изменение запроса не перезапускает поиск Процедур (в текущей версии Оператора).
         * Поэтому изменять запрос следует только в хорошо продуманных случаях.
         * 
         * Пример вызова функции переопределения запроса, с выводом в лог старого и нового значений.
         * Example: query.ChangeQuery(engine, "New query text");
         */
        EnumProcedureResult result = EnumProcedureResult.Success;
        // название текущей процедуры для лога итп.
        String currentProcedureTitle = "GeneralProcedures.ProcedureProcedures.CommandListProcedures";
        // выброшенное тут исключение будет заменено на Reflection исключение и его текст потеряется.
        // Поэтому надо здесь его перехватить, вывести в лог и на консоль, и погасить, вернув EnumProcedureResult.Error.
        try
        {
            // TODO: вывести это тестовое сообщение о начале процедуры - в лог!
            engine.get_OperatorConsole().PrintTextLine("Начата процедура CommandListProcedures()", EnumDialogConsoleColor.Сообщение);
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintTextLine("Список всех Команд Оператора:", EnumDialogConsoleColor.Сообщение);
            engine.get_OperatorConsole().PrintEmptyLine();
            engine.get_OperatorConsole().PrintListOfProcedures();
            engine.get_OperatorConsole().PrintEmptyLine();
            // вывести сообщение о результате операции: успешно
            engine.get_OperatorConsole().PrintTextLine("Выведен список Команд", EnumDialogConsoleColor.Успех);
        }
        catch (Exception ex)
        {
            engine.PrintExceptionMessageToConsoleAndLog("Ошибка в процедуре " + currentProcedureTitle + "()", ex);
            result = EnumProcedureResult.Error;
        }

        // вернуть флаг продолжения работы
        return result;
    }

   

}
