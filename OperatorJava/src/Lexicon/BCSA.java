/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - Код готов к отладке.
 */
package Lexicon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import OperatorEngine.Engine;
import OperatorEngine.EnumProcedureResult;

/**
 * @author jsmith
 *         Анализатор команды - для исполнения сложных и составных команд.
 */
public class BCSA // BigCommandSemanticAnalyser - такое длинное название
{

    /**
     * NT-Разобрать входной запрос команды, построить граф исполнения и
     * исполнить команду
     * 
     * @param engine
     *            Объект движка
     * @param query
     *            Текст исходного запроса
     * @return Функция возвращает код результата исполнения процедуры
     * @throws Exception Error on DoQuery()
     */
    public static EnumProcedureResult ProcessQuery(Engine engine, String query) throws Exception
    {
        // сейчас тупо исполним весь запрос целиком
        EnumProcedureResult result = engine.DoQuery(query);

        // а вообще это неправильно - для составных команд надо:
        // 1. попытаться найти для нее процедуру-исполнителя как есть. Но найти
        // и не исполнять пока!
        // 2. если не нашлось, то пытаться разделить запрос на части по смыслу,
        // и пытаться найти для них процедуру-исполнителя. Но найти и не
        // исполнять пока!
        // 3. если не нашлось процедуры хотя бы для одной из частей запроса,
        // исполнять команду нельзя!
        // В итоге, это все не вписывается в существующую архитектуру системы.
        // Надо все переделывать для поддержки составных команд.
        // а тут - пока - можно приводить глаголы в команде к первичной форме:
        // спи = спать, найди = найти.
        // Вот и вся интеллектуальная обработка тут.

        return result;

    }

    // #region Функции для Русского языка

    /**
     * Русский язык для конверсий и зависимых от языка операций
     */
    public static final Locale  RuCulture            = new Locale("ru", "RU");

    /**
     * Буквы русского алфавита маленькие (Строчные)
     */
    private static final String RussianAlphabet      = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    /**
     * Буквы русского алфавита большие (Прописные)
     */
    private static final String RussianAlphabetShift = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

    /**
     * NT-Вернуть True, если первые три символа - не русскоязычные.
     * 
     * @param query
     *            Текст запроса
     * @return Функция возвращает True, если первые три символа - не
     *         русскоязычные.
     */
    public static boolean IsNotRussianFirst(String query)
    {
        // Это функция для распознавания русскоязычной команды. Против текстов
        // для командной строки, подобных "wget -c -nc www.xxx.com"
        // если команда русскоязычная, она должна начинаться с глагола или
        // подобного русского слова.
        // Предположительно, длиной не менее 3 символов.
        // Значит, если первые три символа - русские, то весь текст -
        // русскоязычная команда.
        // А если нет - то это команда для терминала.
        // Но я точно не представляю себе эту ситуацию, так что почему два
        // символа, а не один или три?
        String q = query.trim();
        if (q.length() < 2) return true;// вернуть истину, поскольку если букв
                                        // всего 1, то это точно не русские
        return (!(IsRussianLetter(q.charAt(0)) && IsRussianLetter(q.charAt(1))));
    }

    /**
     * NT-Возвращает True если проверяемый символ - русская буква
     * @param p Проверяемый символ
     * @return Возвращает True если проверяемый символ - русская буква
     */
    private static boolean IsRussianLetter(char p)
    {
        int pi = (int) p;
        return ((RussianAlphabet.indexOf(pi) != -1) || (RussianAlphabetShift.indexOf(pi) != -1));
    }
    // #endregion

    /**
     * RT-Форматировать дату и время в русской культуре/
     * Пример: воскресенье, 26 апреля 2020г. 01:03:18
     * 
     * @param dt
     *            дата и время
     * @return Функция возвращает строку даты и времени.
     */
    public static String CreateLongDatetimeString(LocalDateTime dt)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("cccc, dd LLLL yyyy'г. 'HH:mm:ss", BCSA.RuCulture);

        return dtf.format(dt);
    }
}
