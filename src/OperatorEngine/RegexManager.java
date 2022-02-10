/**
 * @author Pavel Seliakov
 *         Copyright Pavel M Seliakov 2014-2021
 *         Created: Feb 6, 2022 4:59:55 AM
 *         State: Feb 6, 2022 4:59:55 AM - TODO: указать состояние файла здесь.
 */
package OperatorEngine;

/**
 * Класс-контейнер для регекс-операций
 * 
 * @author 1
 */
public class RegexManager
{

    /**
     * NT-Определить тип регекса, содержащегося в переданной строке
     * 
     * @param pattern
     *            Строка, содержащая регекс
     * @return Возвращает одно из RegexType значений
     */
    public static EnumRegexType determineRegexType(String pattern)
    {
        // проверка наличия регекса в процедуре
        if (Utility.StringIsNullOrEmpty(pattern)) return EnumRegexType.Empty;
        // проверка наличия ^ и $
        boolean b1 = (pattern.charAt(0) == '^');
        boolean b2 = (pattern.charAt(pattern.length() - 1) == '$');
        if ((b1 & b2) == true)
            return EnumRegexType.NormalRegex; // это сложный регекс
        else if ((b1 | b2) == false)
            return EnumRegexType.SimpleString; // это простой регекс
        else return EnumRegexType.Invalid; // это ни то ни другое
    }

    /**
     * NT-Конвертировать простой регекс в нормальный регекс - не используется,
     * заменено на 2.
     * 
     * @param rx
     *            Конвертируемый простой регекс
     * @return Возвращает нормальный регекс
     */
    public static String ConvertSimpleToRegex(String rx)
    {

        // тут надо распарсить строку запроса, выделив аргументы.
        // затем заменить аргументы на строки, с учетом количества аргументов.

        // Пример простого регекса:
        // Копировать %файл в %папка
        String query = rx.trim();

        // разделим строку на слова по пробелам
        String[] sar = Utility.StringSplit(rx, " ", true);

        // массив может содержать пустые строки!
        // теперь если первый символ %, то все слово заменить на регекс-вставку
        // а потом все эти куски собрать обратно в строку через пробелы
        // получим и строку регекса и строго одиночные пробелы меду словами.
        // и потеряем запятые у аргументов наподобие %арг0, %арг1
        // хотя можно проверять, что последний символ не буква и копировать его
        // в заменяемый, но это потом, если потребуется.
        // а то и вовсе можно замены через регекс делать
        int num = 0;
        for (int i = 0; i < sar.length; i++)
        {
            String s = sar[i];
            if (s.charAt(0) == '%')
            {
                sar[i] = "(?<arg" + Integer.toString(num) + ">.+)";
                num++;
            }
        }
        String result = String.join(" ", sar);

        return result;
    }

    ///// <summary>
    ///// RT-Запустить проверку соответствия запроса и нормального регекса
    ///// </summary>
    ///// <param name="rx">регекс</param>
    ///// <param name="cmdline">запрос, команда пользователя</param>
    ///// <returns></returns>
    // internal static bool IsMatchQuery(string rx, string cmdline)
    // {
    // //Замечу что здесь запросы выполняются без учета регистра символов, а
    ///// имена мест сейчас учитывают регистр символов.
    // return Regex.IsMatch(cmdline, rx, RegexOptions.Singleline |
    ///// RegexOptions.IgnoreCase);
    // }

    // #region *** Замена аргументов командной строки приложений ***

    /**
     * Словарь аргументов для вставки в командную строку.
     */
    private static ArgumentCollection m_AppMatchArguments;

    /**
     * NR-Замена аргументов командной строки приложений
     * 
     * @param cmdline
     * @param arguments
     * @return
     */
    public static String ConvertApplicationCommandString(String cmdline, ArgumentCollection arguments)// TODO:
                                                                                                      // Regex
    {
        // распарсить строку вида: my app.exe -t -d%arg1%[56*4765] -c"%arg2"
        // аргумент начинается с % и содержит буквы или цифры, но не знаки или
        // пробелы

        String pattern = "%\\w+";

        // отправить аргументы в статическую переменную
        m_AppMatchArguments = arguments;

        MatchEvaluator mev = new MatchEvaluator(AppMatchEvaluator);
        String result = Regex.Replace(cmdline, pattern, mev);
        return result;
    }

    /**
     * NR-
     * 
     * @param match
     * @return
     */
    private static String AppMatchEvaluator(Match match)// TODO: Regex
    {
        // аргумент приходит из выражения со знаком % первым символом
        // а в словаре имена аргументов без %,
        // поэтому надо первый символ убрать из имени аргумента из выражения.
        String argName = match.Value.Substring(1);
        // TODO: кавычки в командной строке: Исправление от 10.07.2019 : если
        // значение аргумента содержит пробелы, заключать его в кавычки.
        // Это только для аргументов командной строки
        // а можно было там их сразу в БД в кавычки заключать, а не здесь!
        String argValue = RegexManager.m_AppMatchArguments.GetByName(argName).ArgumentValue;
        if (argValue.contains(" ")) argValue = '"' + argValue + '"';
        return argValue;
        // если аргумента нет в словаре, здесь будет выброшено исключение.
        // такое может быть если изначально задан неправильный шаблон команды
        // или строка запуска приложения
    }
    // #endregion

    // #region *** Конвертировать простой шаблон в регекс, сохраняя названия
    // аргументов ***

    /**
     * NR-Конвертировать простой шаблон в регекс, сохраняя названия аргументов
     * 
     * @param rx
     *            Текст шаблона
     * @return Строка выражения регекса
     */
    public static String ConvertSimpleToRegex2(String rx)// TODO: Regex
    {
        // тут надо распарсить строку запроса, выделив аргументы.
        // затем заменить аргументы на строки, с учетом количества аргументов.

        // Пример простого регекса:
        // Копировать %файл в %папка
        String query = rx.trim();
        String pattern = "%\\w+";

        MatchEvaluator mev = new MatchEvaluator(SimpleMatchEvaluator);
        String result = Regex.Replace(query, pattern, mev);
        return "^" + result + "$";
    }

    /**
     * NR-
     * 
     * @param match
     * @return
     */
    private static String SimpleMatchEvaluator(Match match)
    {
        String argName = match.Value;
        // у аргумента должен быть первый символ %, его надо выкинуть из имени
        // группы.
        String res = "(?<" + argName.substring(1) + ">.+)";
        return res;
    }
    // #endregion

    /**
     * NR-Разделить строку запуска приложения на путь приложения и аргументы.
     * Поддерживаются только exe и com расширения файлов.
     * 
     * @param cmdline
     * @return
     */
    public static String[] ParseCommandLine(String cmdline)
    {
        // Можно было лучше сделать - последовательно брать куски с пробелами и
        // проверять, существует ли такой путь и файл.
        // Если существует, то значит это и есть приложение.
        // Но пока сделаем так.
        // Если надо запустить файл не exe, то можно его переименовать в exe
        // А вот еще надо cmd файлы запускать тоже.

        String[] sar = new String[2];
        String[] patterns = new String[] { ".exe ", ".exe\"", ".com ", ".com\"", ".bat ", ".bat\"", ".cmd ", ".cmd\"" };// TODO:
                                                                                                                        // расширения
                                                                                                                        // в
                                                                                                                        // Линукс
                                                                                                                        // не
                                                                                                                        // используются!
        int position;
        for (String pat : patterns)
        {
            position = cmdline.IndexOf(pat, StringComparison.OrdinalIgnoreCase);
            if (position >= 0)
            {
                position += pat.Length;
                sar[0] = cmdline.Substring(0, position); // app path
                sar[1] = cmdline.Substring(position);// app arguments
                return sar;
            }
        }
        // не нашли ничего
        // может быть, там нет аргументов и нет пробела после расширения?
        if (System.IO.File.Exists(cmdline))
        {
            sar[0] = String.Copy(cmdline);
            sar[1] = "";
            return sar;
        }
        // может быть, это URI?
        sar[0] = String.Copy(cmdline);
        sar[1] = "";
        return sar;

        // пока нечем проверить что это - файловый путь или веб-адрес или я хз
        // что,
        // так что просто пробуем выполнить.
        // throw new Exception(String.Format("Неправильная строка запуска
        // приложения: {0}", cmdline));
    }

    /**
         * NR-Проверить что это путь к сборке кода 
         * @param path
         * @return 
         */
        public  static boolean IsAssemblyCodePath(String path)//TODO: Regex
        {
            //проверить что путь это путь к функции сборки
            //имясборки.имякласса.имяфункции()
            //имясборки.имякласса.имяфункции(арг1)
            //имясборки.имякласса.имяфункции(арг1, арг2, арг3 )
            //строка паттерна регекса:  

            String pattern = @"^\w+\.\w+\.\w+\([\w,\s]*\)\w*$";
            return Regex.IsMatch(path, pattern);
        }

    /**
     * NR-разделить путь сборки на имена частей и имена аргументов
     * 
     * @param path
     * @return
     */
    public static String[] ParseAssemblyCodePath(String path)
    {

        LinkedList<String> lis = new LinkedList<String>();
        String p = path.trim();
        String[] sar1 = Utility.StringSplit(p, "(", true); // p.Split(new char[]
                                                           // { '(' },
                                                           // StringSplitOptions.RemoveEmptyEntries);
        String names = sar1[0];
        String args = sar1[1];

        String[] sar2 = Utility.StringSplit(names, ".", true); // names.Split(
                                                               // new char[] {
                                                               // '.'},
                                                               // StringSplitOptions.RemoveEmptyEntries);
        lis.Add(sar2[0]);// assembly name
        lis.Add(sar2[1]);// class name
        lis.Add(sar2[2]);// func name

        // отсечь все что после закрывающей скобки
        int pos = args.IndexOf(')');
        if (pos < 0) throw new Exception(String.Format("Неправильный путь: {0}", path));
        args = args.Remove(pos);

        // если там еще что-то есть, это должны быть аргументы
        if (args.Length > 0)
        {
            // разделить на аргументы
            String[] sar4 = args.Split(new char[] { ',' }, StringSplitOptions.RemoveEmptyEntries);
            for (String s : sar4)
            {
                lis.Add(s.Trim());
            }
        }
        return lis.ToArray();
    }

    /**
     * NR-Извлечь аргументы из текста команды, если она совпала с шаблоном.
     * Возвращает список аргументов или нуль если не было совпадения.
     * 
     * @param command
     *            Текст команды
     * @param pattern
     *            Шаблон команды
     * @return Возвращается коллекция аргументов
     */
    public static ArgumentCollection ExtractArgumentsFromCommand(String command, string pattern)// TODO:
                                                                                                // Regex
    {
        ArgumentCollection args = null;
        Regex r = new Regex(pattern, RegexOptions.IgnoreCase | RegexOptions.Singleline);
        Match m = r.Match(command);

        if (m.Success == true)
        {
            // создаем коллекцию аргументов как флаг успеха сравнения
            args = new ArgumentCollection();
            for (int i = 0; i < m.Groups.Count; i++)
            {
                // делаем из совпадений регекса аргументы и вносим в список
                // аргументов
                String name = r.GroupNameFromNumber(i);
                String value = m.Groups[i].Value;
                args.Add(new FuncArgument(name, "", value, value));
            }
            // удаляем первый элемент, который содержит всю строку команды.
            if (args.Arguments.Count > 0) args.Arguments.RemoveAt(0);
        }
        // возвращаем коллекцию аргументов, если матч успешный и нуль,если матч
        // неуспешный
        return args;
    }
}
