/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - Допилить тодо.
 */
package Lexicon;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Locale;

import JTerminal.Terminal;
import OperatorEngine.Engine;
import OperatorEngine.Place;
import OperatorEngine.Procedure;
import OperatorEngine.Utility;

/**
 * @author jsmith
 *         Представляет консоль Оператора для всех процедур и внутренних функций
 *         движка и всего приложения.
 */
public class DialogConsole
{

    // TODO: Доделать и отладить функции Terminal терминала - а то сейчас они не все
    // работают.
    // TODO: Удалить лишние переменные и функции (закомментить пока), заменить
    // обратно видимость с public на package (вместо internal)

    /**
     * Обратная ссылка на объект движка
     */
    private Engine m_Engine;

    /**
     * Constructor
     */
    public DialogConsole()
    {
        this.m_Engine = null;
        return;
    }

    /**
     * Constructor
     * 
     * @param engine
     *            Engine object
     */
    public DialogConsole(Engine engine)
    {
        this.m_Engine = engine;

        return;
    }

    /**
     * NT-Установить обычный цвет текста консоли
     */
    private void ResetColors()
    {
        Terminal.ClearAttributeMode();
    }

    /**
     * NT-Установить указанный цвет текста консоли.
     * Консоль должна печатать этим цветом весь последующий текст, включая
     * промпт и ввод пользователя.
     * 
     * @param color
     *            Цвет для текста консоли
     */
    private void SetTextColor(EnumDialogConsoleColor color)
    {
        Terminal.SetAttributeMode(color.getValue());

        return;
    }

    /**
     * NT-Подать короткий звуковой сигнал
     */
    public void Beep()
    {
        Terminal.Beep();

        return;
    }

    /**
     * NT-Поместить курсор консоли в начало новой строки, если он не там.
     */
    public void SureConsoleCursorStart()
    {
        // //TODO: getCursorPos() needed
        // if (Console.CursorLeft > 0)
        // Console.WriteLine();

        // Поскольку Terminal.getCursorPos() не реализован ввиду проблем (см
        // выше), то просто переводим курсор на новую строку.
        // Это будет коряво, но работать будет.
        Terminal.WriteLine();

        return;
    }

    /**
     * NT-вывести на консоль текст (Console.Write())
     * 
     * @param text
     *            Текст сообщения
     * @param color
     *            Цвет сообщения
     */
    public void PrintText(String text, EnumDialogConsoleColor color)
    {
        this.SetTextColor(color);
        Terminal.Write(text);
        this.ResetColors();

        return;
    }

    /**
     * NT-Вывести на консоль пустую строку
     */
    public void PrintEmptyLine()
    {
        Terminal.WriteLine();

        return;
    }

    /**
     * NT-вывести на консоль строку текста (Console.WriteLine())
     * 
     * @param text
     *            Текст сообщения
     * @param color
     *            Цвет сообщения
     */
    public void PrintTextLine(String text, EnumDialogConsoleColor color)
    {
        // если текст - пустая строка, то незачем цвета переключать
        if (Utility.StringIsNullOrEmpty(text))
            Terminal.WriteLine();
        else
        {
            this.SetTextColor(color);
            Terminal.WriteLine(text);
            this.ResetColors();
        }

        return;
    }

    /// <summary>
    /// NT-вывести массив строк сообщения.
    /// </summary>
    /// <remarks>
    ///
    /// </remarks>
    /// <param name="lines">Массив строк сообщения</param>
    /// <param name="color">Цвет сообщения</param>
    /**
     * NT-вывести массив строк сообщения. Это типа оптимизация - экономит
     * переключения цвета текста консоли
     * 
     * @param lines
     *            Массив строк сообщения
     * @param color
     *            Цвет сообщения
     */
    public void PrintTextLines(String[] lines, EnumDialogConsoleColor color)
    {
        this.SetTextColor(color);
        for (String text : lines)
            Terminal.WriteLine(text);
        this.ResetColors();

        return;
    }

    /**
     * NR-Получить строку ввода с консоли
     * 
     * @return Функция возвращает введенный с консоли текст
     */
    public String ReadLine()
    {
        this.SetTextColor(EnumDialogConsoleColor.ВводПользователя);
        String result = Terminal.ReadLine();
        this.ResetColors();

        return result;
    }

    /**
     * NT-вывести на консоль вопрос и принять ответ
     * 
     * @param keys
     *            Набор флагов ожидаемых субкоманд
     * @param question
     *            Текст вопроса пользователю
     * @param newLine
     *            True - Начинать ответ с новой строки, False - в той же строке.
     * @param noEmptyAnswer
     *            True - требовать повторный ввод, если ответ пустая строка;
     *            False - принимать пустые ответы
     * @return Возвращает строку, введенную пользователем.
     * @throws Exception Функция выбрасывает исключение, если параметр keys имеет неправильные значения.
     */
    public String PrintQuestionAnswer(
            EnumSpeakDialogResult keys,
            String question,
            boolean newLine,
            boolean noEmptyAnswer) throws Exception
    {
        this.SureConsoleCursorStart();// убедиться что курсор находится в начале
                                      // строки
        // установим цвет вопросов в консоли
        this.SetTextColor(EnumDialogConsoleColor.Вопрос);
        Terminal.Write(question);
        Terminal.Write(" ");// разделитель для секции []
        Terminal.Write(Dialogs.makeСтрокаОжидаемыхОтветов(keys));
        this.ResetColors();
        // если указан флаг, вывести символ конца строки
        if (newLine) Terminal.WriteLine();
        String result = ""; // String.Empty;
        do
        {
            result = this.ReadLine();
            if (result == null)
                result = ""; // String.Empty;//случается, если нажата Ctrl+C
            else result = result.trim();// удалить пробелы и всякие там
                                        // случайности
            // no empty answers
            if ((noEmptyAnswer == false) && (Utility.StringIsNullOrEmpty(result))) break;// answer
                                                                                         // can
                                                                                         // be
                                                                                         // empty
        } while (Utility.StringIsNullOrEmpty(result));

        return result;
    }

    /// <summary>
    /// NT-Диалог Да-Нет-Отменить. Другие ответы не принимаются.
    /// </summary>
    /// <param name="текстЗапроса">Текст вопроса пользователю, без символа
    /// перевода строки в конце!</param>
    /// <returns>
    /// Функция возвращает <c>SpeakDialogResult</c> код стандартного ответа Да,
    /// Нет или Отмена.
    /// </returns>
    /**
     * NT-Диалог Да-Нет-Отменить. Другие ответы не принимаются.
     * 
     * @param question
     *            Текст вопроса пользователю, без символа перевода строки в
     *            конце!
     * @return Функция возвращает SpeakDialogResult код стандартного ответа Да,
     *         Нет или Отмена.
     * @throws Exception Функция выбрасывает исключение от Dialogs.makeСтрокаОжидаемыхОтветов().
     */
    public EnumSpeakDialogResult PrintДаНетОтмена(String question) throws Exception
    {
        this.SureConsoleCursorStart();// убедиться что курсор находится в начале
                                      // строки
        // установим цвет вопросов в консоли
        this.SetTextColor(EnumDialogConsoleColor.Вопрос);
        Terminal.Write(question);
        Terminal.Write(" ");// разделитель для секции []
        Terminal.Write(Dialogs.makeСтрокаОжидаемыхОтветов(new EnumSpeakDialogResult(EnumSpeakDialogResult.ДаНетОтмена)));
        this.ResetColors();

        String result = "";// String.Empty;
        do
        {
            result = this.ReadLine();
            if (result == null) result = ""; // String.Empty;//случается, если
                                             // нажата Ctrl+C
            // no empty answers
            if (Dialogs.этоДа(result))
                return new EnumSpeakDialogResult(EnumSpeakDialogResult.Да);
            else if (Dialogs.этоНет(result))
                return new EnumSpeakDialogResult(EnumSpeakDialogResult.Нет);
            else if (Dialogs.этоОтмена(result))
                return new EnumSpeakDialogResult(EnumSpeakDialogResult.Отмена);
            else
            {
                this.SureConsoleCursorStart();// убедиться что курсор находится
                                              // в начале строки
                this.PrintTextLine("Принимаются только ответы Да, Нет или Отмена!", EnumDialogConsoleColor.Предупреждение);
            }
        } while (true);

        // unreachable code: return new EnumSpeakDialogResult(
        // EnumSpeakDialogResult.Unknown);
    }

    /**
     * NT- вывести сообщение об исключении
     * 
     * @param title
     *            Вводный текст сообщения. Если пустая строка, то используется
     *            "Ошибка"
     * @param ex
     *            Объект исключения
     */
    public void PrintExceptionMessage(String title, Exception ex)
    {
        String s;
        if(Utility.StringIsNullOrEmpty(title))
            s = "Ошибка";
        else
            s = title;
        //
        StringBuilder sb = new StringBuilder(s);
        // добавим разделительный пробел
        sb.append(' ');
        // добавим название исключения
        sb.append(ex.getClass().getName());
        // добавим текст исключения
        sb.append(": ");
        sb.append(ex.getMessage());
        // выведем текст на консоль
        this.PrintTextLine(sb.toString(), EnumDialogConsoleColor.Предупреждение);

        return;
    }

    /**
     * NT- вывести сообщение об исключении
     * 
     * @param ex
     *            Объект исключения
     */
    public void PrintExceptionMessage(Exception ex)
    {
        this.PrintExceptionMessage("Ошибка", ex);

        return;
    }

    // #region функции вывода свойств мест и процедур
    // TODO: перенести их в правильное место. Тут им нечего делать! И код их не
    // должен работать с консолью напрямую!

    /**
     * NT-Вывести на консоль короткое описание места в одну строку
     * 
     * @param place
     *            Место
     */
    public void PrintPlaceShortLine(Place place)
    {
        this.SureConsoleCursorStart();
        this.PrintTextLine(place.getSingleLineProperties(), EnumDialogConsoleColor.Сообщение);

        return;
    }

    /**
     * NT-вывести на консоль свойства места подробно, как форму
     * 
     * @param p
     *            Место
     */
    public void PrintPlaceForm(Place p)
    {
        this.SureConsoleCursorStart();
        // тут надо вывести описание свойств Места в виде многострочной формы
        // или списка свойств.
        String[] sar = new String[8];
        sar[0] = String.format("Свойства Места \"%s\":", p.get_Title());
        sar[1] = String.format("Название: %s", p.get_Title());
        sar[2] = String.format("Класс:    %s", p.get_PlaceTypeExpression());
        sar[3] = String.format("Адрес:    %s", p.get_Path());
        sar[4] = String.format("Синонимы: %s", p.get_Synonim());
        sar[5] = String.format("Описание: %s", p.get_Description());
        sar[6] = String.format("ID:       %d", p.get_TableId());
        sar[7] = "";// пустая строка-разделитель
        // print array of lines
        this.PrintTextLines(sar, EnumDialogConsoleColor.Сообщение);

        return;
    }

    /**
     * NT-Вывести на экран список существующих мест - только названия мест
     * @throws Exception 
     * @throws SQLException 
     */
    public void PrintListOfPlaces() throws SQLException, Exception
    {
        this.SureConsoleCursorStart();
        // получить список мест
        LinkedList<Place> places = this.m_Engine.get_Database().GetAllPlaces();
        // TODO: database here
        // сортировать список мест по алфавиту
        Collections.sort(places); //Sort by Title over interface Comparable
        // вывести на экран одни только названия мест
        //TODO: перенести формирование строки в объект Места
        //TODO: Удобство - если строк много, вывести порциями по 20 штук с
        // перерывом на Enter
        for (Place p : places)
            this.PrintTextLine(String.format("%s [%s]", p.get_Title(), p.get_Path()), EnumDialogConsoleColor.Сообщение);

        return;
    }

    /**
     * NT-Вывести на экран список существующих Процедур - только названия
     * процедур
     * @throws SQLException 
     */
    public void PrintListOfProcedures() throws SQLException
    {
        this.SureConsoleCursorStart();
        // получить список процедур
        LinkedList<Procedure> procedures = this.m_Engine.get_Database().GetAllProcedures();
        // TODO: database here
        // сортировать список мест по алфавиту
        Collections.sort(procedures); //Sort by Title over interface Comparable
        // вывести на экран одни только названия процедур
        //TODO: перенести формирование строки в объект Места
        //TODO: Удобство - если строк много, вывести порциями по 20 штук с
        // перерывом на Enter
        for (Procedure p : procedures)
            this.PrintTextLine(String.format("%s [%s]", p.get_Title(), p.get_Description()), EnumDialogConsoleColor.Сообщение);

        return;
    }

    /**
     * NT-Вывести на консоль короткое описание Процедуры в одну строку
     * 
     * @param p
     */
    public void PrintProcedureShortLine(Procedure p)
    {
        this.SureConsoleCursorStart();
        this.PrintTextLine(p.getSingleLineProperties(), EnumDialogConsoleColor.Сообщение);

        return;
    }

    /**
     * NT-вывести на консоль свойства Процедуры подробно, как форму
     * 
     * @param p
     */
    public void PrintProcedureForm(Procedure p)
    {
        this.SureConsoleCursorStart();
        //тут надо вывести описание свойств Процедуры в виде
        // многострочной формы или списка свойств.
        String[] sar = new String[8];
        sar[0] = String.format("Свойства Команды \"%s\":", p.get_Title());
        sar[1] = String.format("Название: %s", p.get_Title());
        sar[2] = String.format("Описание: %s", p.get_Description());
        sar[3] = String.format("Регекс:   %s", p.get_Regex());
        sar[4] = String.format("Адрес:    %s", p.get_Path());
        sar[5] = String.format("Вес:      %s", p.get_Ves().toString());
        sar[6] = String.format("ID:       %d", p.get_TableId());
        sar[7] = "";// пустая строка-разделитель
        // print array of lines
        this.PrintTextLines(sar, EnumDialogConsoleColor.Сообщение);

    }

    // #endregion

}
