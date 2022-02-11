/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - Используется как запуск тестов
 *         функций вместо запуска основного процесса.
 */

import JTerminal.IntegerProxy;
import JTerminal.Terminal;
import JTerminal.TerminalMode;
import JTerminal.TerminalModeStrings;
import Lexicon.BCSA;
import OperatorEngine.Utility;

/**
 * @author jsmith
 *
 */
public class Operator
{

    /**
     * @param args
     *            - main program arguments
     */
    public static void main(String[] args)
    {

        String s = "redText";
        String t = "BlueText";
        String f = "GreenText";

        String ru1 = "Спать";
        String ru2 = "Спать или не спать - вот в чем вопрос!";

        // Terminal.eraseScreen();
        // Terminal.WriteLn("SimpleText");
        // Terminal.WriteLn(TerminalModeStrings.BGCOLOR_BRIGHT_RED +
        // TerminalModeStrings.COLOR_BRIGHT_BLACK +
        // TerminalModeStrings.STYLE_UNDERLINE + s +
        // TerminalModeStrings.COLOR_RESET);
        // Terminal.WriteLn("SimpleText");
        // Terminal.ClearAttributeMode();
        // Terminal.WriteLn("SimpleText");
        // Terminal.moveCursorDown(1);
        // Terminal.SetAttributeMode(Terminal.STYLE_BOLD, Terminal.COLOR_GREEN,
        // Terminal.BGCOLOR_BRIGHT_GRAY);
        // Terminal.WriteLn(f);
        // Terminal.forceCursorPosition(15, 5);
        // Terminal.WriteLn(t);

        try
        {
            // System.out.println("Press Enter to continue...");
            // int result = System.in.read();
            // String result = BCSA.CreateLongDatetimeString(null);
            // Terminal.WriteLine(result);

            String[] sar = "alfa, beta, gamma, delta, saturn   floating - points".split(" ");
            for (String sl : sar)
                Terminal.WriteLine(sl + "|");
            sar = Utility.RemoveEmptyItems(sar);
            Terminal.WriteLine("-----------");
            for (String sl : sar)
                Terminal.WriteLine(sl + "-");
            Terminal.WriteLine("-----------");

            // test for getCursorPosition function
            // IntegerProxy r = new IntegerProxy();
            // IntegerProxy c = new IntegerProxy();
            // Terminal.getCursorPosition(r,c);
            // String coords = String.format("r=%d;c=%d", r.getValue(),
            // c.getValue());
            // Terminal.WriteLine(coords);

            // Terminal.Beep();
            // //Terminal.SetAttributeMode(TerminalMode.COLOR_RED);
            // boolean b1 = BCSA.IsNotRussianFirst(s);
            // Terminal.ErrorWriteLine(String.format("%s = %b", s, b1));
            // boolean b2 = BCSA.IsNotRussianFirst(ru1);
            // Terminal.ErrorWriteLine(String.format("%s = %b",ru1, b2));
            // Terminal.SetAttributeMode(TerminalMode.COLOR_BRIGHT_GREEN);

        }
        catch (Exception e)
        {
            Terminal.WriteLine(e.getClass().getTypeName());
            Terminal.WriteLine(e.getMessage());
            Terminal.WriteLine();
        }

        return;

    }

    // /// <summary>
    // /// Объект механизма исполнения команд поьзователя
    // /// </summary>
    // private static Engine m_engine;
    //
    // /// <summary>
    // /// Писатель файла лога
    // /// </summary>
    // private static StreamWriter logWriter = null;
    //
    // /// <summary>
    // /// код операции выполняемой перед завершением приложения
    // /// </summary>
    // private static ProcedureResult m_exitcode;
    //
    //
    // /// <summary>
    // /// Это консольное приложение, работающее подобно интерпретатору
    // командной строки,
    // /// чтобы обрабатывать полноценный русскоязычный текст.
    // /// </summary>
    // /// <param name="args"></param>
    // static void Main(string[] args)
    // {
    // //попытаться определить, запущен ли уже Оператор,
    // //и если да, завершить работу и передать фокус ввода более старой копии
    // //Это работает, но криво сделано.
    // if (Utility.DoubleApplication() == true)
    // return;
    //
    // //TODO: приложение не умеет перехватывать свое завершение, поэтому не
    // обрабатывает закрытие окна.
    // //Так что в лог не выводится запись, завершающая сеанс работы.
    // //И БД не закрывается, это надо исправить!
    // Utility.DisableConsoleCloseButton();
    // //это выключает кнопку Close на окне, и теперь приложение нельзя закрыть
    // через системное меню.
    // //И по alt+F4 консольные приложения не закрываются никакие.
    // //Закрываются по Ctrl+C или Ctrl+Break
    // //Console.TreatControlCAsInput = true; - я пробовал включить это, но
    // консоль команды странно обрабатывает - виснет на них.
    // Console.TreatControlCAsInput = false;
    // Console.CancelKeyPress += new
    // ConsoleCancelEventHandler(Console_CancelKeyPress);
    //
    // //можно добавить поддержку ввода клавиш команд Да Нет Отмена,
    // //есть идеи в
    // ms-help://MS.VSCC.v90/MS.MSDNQTR.v90.en/fxref_mscorlib/html/bcf70b80-2b4b-d7d2-05ed-15ff0ceab52f.htm
    // //Для этого надо сначала запрашивать первый символ через
    // Console.readKey(true),
    // //а потом, если это не командная клавиша, выводить ее на экран и собирать
    // остальной ввод как обычную строку.
    // //Esc = Отмена; + = Да; - = Нет;
    // //Решено: не делать подобные фичи - использовать только обычный текст!
    //
    //
    // try
    // {
    // //открываем в текущем каталоге текстовый файл лога для записи введенных
    // запросов
    // logWriter = new StreamWriter("log.txt", true, Encoding.UTF8);
    // logWriter.AutoFlush = true;
    //
    // //создать и инициализировать механизм обработки команд пользователя
    // m_engine = new Engine(logWriter);
    // m_engine.Init();
    //
    // //DEBUG TESTS: место для запуска проверочных тестов
    // DebugTestPlace();
    //
    // //запускаем цикл приема запросов
    // m_exitcode = m_engine.ProcessLoop();
    //
    // m_engine.Exit();
    // //записываем в лог сообщение о завершении работы и завершаем работу
    // приложения.
    // logWriter.WriteLine("ENDSESSION {0}", Utility.DateTimeNowToString());
    // }
    // catch (Exception ex)
    // {
    // Console.WriteLine("EXCEPTION {0} : {1}", ex.GetType().ToString(),
    // ex.Message);
    // if (logWriter != null)
    // logWriter.WriteLine("EXCEPTION {0} : {1}", ex.GetType().ToString(),
    // ex.Message);
    // //попробуем закрыть движок при ошибке
    // if(m_engine != null)
    // m_engine.Exit();
    // }
    //
    // //закрываем лог
    // if (logWriter != null)
    // logWriter.Close();
    //
    // //обработать требование завершения работы перед самым закрытием
    // приложения.
    // PowerManager.ProcessExitCode(m_exitcode);
    //
    // return;
    // }
    //
    // static void Console_CancelKeyPress(object sender, ConsoleCancelEventArgs
    // e)
    // {
    // if (e.SpecialKey == ConsoleSpecialKey.ControlC)
    // e.Cancel = true;
    // else if (e.SpecialKey == ConsoleSpecialKey.ControlBreak)
    // {
    // e.Cancel = false;
    // //
    // m_engine.Exit();
    // logWriter.WriteLine("Close by Ctrl+Break {0}",
    // Utility.DateTimeNowToString());
    // //записываем в лог сообщение о завершении работы и завершаем работу
    // приложения.
    // logWriter.WriteLine("ENDSESSION {0}", Utility.DateTimeNowToString());
    // }
    //
    // return;
    // }
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    // /// <summary>
    // /// Площадка для отладки функций. Размещать тесты здесь.
    // /// </summary>
    // private static void DebugTestPlace()
    // {
    // //тест парсера выражения классов сущностей - успешно 31072016
    // //EntityTypesCollection etc = new EntityTypesCollection();
    // //etc.ParseExpression("Файловая система :: Папка <Файловая система ::
    // Папка, Файл > ; Мои каталоги :: Каталог музыки <Файловая система ::
    // Папка, Файл::Файл музыки>; моя музыка");
    //
    // //Тест парсера простого регекса.
    // //String regex = RegexManager.ConvertSimpleToRegex2("копировать %файл в
    // %папку");
    // //bool match = RegexManager.IsMatchQuery(regex, "Копировать
    // С:\\Temp\\file.txt в C:\\Мои Документы");
    //
    // //Тест замены аргументов командной строки приложения
    // //Dictionary<String, String> dict = new Dictionary<string,string>();
    // //dict.Add("arg1", "value1");
    // //dict.Add("arg2", "value2");
    // //String result = RegexManager.ConvertApplicationCommandString(" my
    // app.exe -t -d%arg1%[56*4765] -c\"%arg2\"", dict);
    //
    // //Тест деления командной строки на аргументы и путь приложения.
    // //Только exe и com приложения поддерживаются
    // //String[] sar = RegexManager.ParseCommandLine("C:\\Temp
    // files\\notepad.exe -dfile cmd");
    //
    // //PowerManager.ExecuteApplication("C:\\Program Files\\Windows
    // NT\\Accessories\\wordpad.exe");
    // //PowerManager.ExecuteApplication("\"C:\\Program Files\\Windows
    // NT\\Accessories\\wordpad.exe\"");
    // //PowerManager.ExecuteApplication("C:\\Program Files\\Windows
    // NT\\Accessories\\wordpad.exe C:\\Tenp\\1.txt");
    // //PowerManager.ExecuteApplication("\"C:\\Program Files\\Windows
    // NT\\Accessories\\wordpad.exe\" C:\\Tenp\\1.txt");
    //
    // ////проверить что путь это путь к функции сборки
    // //bool result = false;
    // //result =
    // RegexManager.IsAssemblyCodePath("имясборки.имякласса.имяфункции()");
    // //result =
    // RegexManager.IsAssemblyCodePath("имясборки.имякласса.имяфункции(арг1)");
    // //result =
    // RegexManager.IsAssemblyCodePath("имясборки.имякласса.имяфункции(арг1,
    // арг2, арг3 )");
    //
    // ////тест выделения аргументов из команды пользователя - успешно
    // //String command = "копировать кошку в резиновую кошку";
    // //String pattern = "^копировать (?<file>.+) в (?<folder>.+)$";
    // //ArgumentCollection coll =
    // RegexManager.ExtractArgumentsFromCommand(command, pattern);
    //
    // }
    //
    //
    //

}
