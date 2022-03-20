/**
 * @author Pavel Seliakov
 * Copyright Pavel M Seliakov 2014-2021
 * Created: Feb 6, 2022 4:59:55 AM
 * State: Mar 21, 2022 12:37:20 AM - TODO: указать состояние файла здесь.
 */
package OperatorEngine;

/**
 * Содержит функции управления питанием компьютера
 * @author 1
 */
    public class PowerManager
    {


        //Тут функции должны вызывать команды из БД, переопределяемые пользователем под свою ОС.
        //Это нужно, чтобы Процедуры могли усыпить компьютер, возвратив соответствующий код завершения.
        //TODO: переопределить функции так, чтобы они вызывали Процедуры из БД по их стандартным именам.
        //TODO: пользователь должен настроить Процедуры для этих стандартных имен, ему надо это предложить в процессе настройки Оператора.
        
        
        /**
         * NT-Выполнить операцию согласно коду операции
         * @param exitcode Код возврата из Процедуры
         */
        public static void ProcessExitCode(EnumProcedureResult exitcode)
        {
            switch (exitcode)
            {
                case ExitAndShutdown:
                    DoShutdown();
                    break;
                case ExitAndReload:
                    DoReload();
                    break;
                case ExitAndLogoff:
                    DoLogoff();
                    break;
                case ExitAndSleep:
                    DoSleep();
                    break;
                case ExitAndHybernate:
                    DoHybernate();
                    break;
                default:
                    //Не выполнять операций по коду, возвращенному Процедурой.
                    break;
            }
            return;
        }

        /**
         * Гибернация машины
         */
        public static void DoHybernate()
        {
            // Hibernate
            //TODO: Запустить тут команду для  Hibernate
            return;
        }

        /**
         * Спящий режим машины
         */
        public static void DoSleep()
        {
            // Standby
            //TODO: Запустить тут команду для Standby
            return;
        }

        /**
         * NT-Завершение сеанса текущего поьзователя
         */
        public static void DoLogoff()
        {

            //TODO: Запустить тут команду для LogOff
            return;
        }

        /**
         *  Перезагрузка машины
         */
        public static void DoReload()
        {

            //TODO: Запустить тут команду для Reload
        }

        /**
         *  Выключение машины
         */
        public static void DoShutdown()
        {

            //TODO: Запустить тут команду для Shutdown
        }

        /**
         * NT-Запустить приложение
         * @param cmdline Командная строка с аргументами приложения
         */
        public static void ExecuteApplication(String cmdline)
        {
            String[] sar = RegexManager.ParseCommandLine(cmdline);
            //Process.Start(sar[0], sar[1]);
            ExecuteApplication(sar[0], sar[1]);
            return;
        }
        
        /**
         *  NT-Запустить приложение - улучшенная функция. Приложение запускается с нормальным окном и с рабочим каталогом=Мои документы.
         * @param app Путь исполняемого файла
         * @param args Аргументы или пустая строка 
         */
        public static void ExecuteApplication(String app, String args)
        {
            ProcessStartInfo psi = new ProcessStartInfo(app, args);
            //psi.StandardOutputEncoding = ?
            psi.WindowStyle = ProcessWindowStyle.Normal;
            psi.WorkingDirectory = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments);
            Process.Start(psi);
            
            return;
        }
    }
}
