import java.util.Scanner;

enum RomToArabicConvert{
    //инициализация класса перечислений для работы с римской системой счисления (конвертации в арабскую и наоборот)
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10),
    XI(11), XII(12), XIII(13), XIV(14), XV(15), XVI(16), XVII(17), XVIII(18), XIX(19), XX(20),
    XXI(21), XXII(22), XXIII(23), XXIV(24), XXV(25), XXVI(26), XXVII(27), XXVIII(28), XXIX(29), XXX(30),
    XXXI(31), XXXII(32), XXXIII(33), XXXIV(34), XXXV(35), XXXVI(36), XXXVII(37), XXXVIII(38), XXXIX(39), XL(40),
    XLI(41), XLII(42), XLIII(43), XLIV(44), XLV(45), XLVI(46), XLVII(47), XLVIII(48), XLIX(49), L(50),
    LI(51), LII(52), LIII(53), LIV(54), LV(55), LVI(56), LVII(57), LVIII(58), LIX(59), LX(60),
    LXI(61), LXII(62), LXIII(63), LXIV(64), LXV(65), LXVI(66), LXVII(67), LXVIII(68), LXIX(69), LXX(70),
    LXXI(71), LXXII(72), LXXIII(73), LXXIV(74), LXXV(75), LXXVI(76), LXXVII(77), LXXVIII(78), LXXIX(79), LXXX(80),
    LXXXI(81), LXXXII(82), LXXXIII(83), LXXXIV(84), LXXXV(85), LXXXVI(86), LXXXVII(87), LXXXVIII(88), LXXXIX(89), XC(90),
    XCI(91), XCII(92), XCIII(93), XCIV(94), XCV(95), XCVI(96), XCVII(97), XCVIII(98), XCIX(99), C(100);

    private final int arab;

    private static final RomToArabicConvert[] list = RomToArabicConvert.values();
    public static RomToArabicConvert getRom(int i) {
        return list[i];
    }

    RomToArabicConvert(int arab){
        this.arab = arab;
    }

    public int getArab() {
        return arab;
    }
}



public class MyCalculator {

    public static void main(String[] args) {
        //ГЛАВНАЯ ПРОГРАММА

        int arab = 0;           //инициализация счетчика арабских цифр
        int rom = 0;            //инициализация счетчика римских цифр
        int operator = 0;       //инициализация счетчика количества операторов
        char signOper = '\0';  //инициализация переменной, в которой хранится знак оператора
        String arabArray = "0123456789"; //список допустимых арабских цифр
        String romArray = "IVXCDML"; //список допустимых римских цифр
        String signOperArray = "+-*/"; //список допустимых операторов
        boolean f = false; //флаг наличия в строке недопустимых символов (при наличие == true)

        Scanner scanner = new Scanner(System.in); // инициализация объекта для работы с консолью
        String greeting = "Введите данные для операции в одну строку без пробелов (для выхода наберите exit): ";
        System.out.println(greeting);
        String inputText = scanner.nextLine();

        while (!inputText.equalsIgnoreCase("exit")) // цикл обработки данных, пока не поступит команда на выход "exit"
        {
            // проводим анализ полученной строки
            char[] line = inputText.toCharArray();  //преобразование строки в массив символов
            //цикл анализа каждого символа в массиве
            for (char c : line)
                if (arabArray.indexOf(c) >= 0) {
                    arab++;
                } else if (romArray.indexOf(c) >= 0) {
                    rom++;
                } else if (signOperArray.indexOf(c) >= 0) {
                    operator++;
                    signOper = c;
                } else {
                    f = true;
                }

            if ((arab != 0) & (rom != 0)) { //обработка ошибки
                System.out.println("используются одновременно разные системы счисления");
            }else if (operator > 1){ //обработка ошибки
                System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }else if (operator == 0) { //обработка ошибки
                System.out.println("нет ни одного оператора (+, -, /, *)");
            }else if (f) { //обработка ошибки
                System.out.println("в строке введены недопустимые символы");
            }else if (arab != 0){ //операция над арабскими цифрами
                         ArabicGetResult(inputText, signOper);
            }else if (rom != 0){ //операция над римскими цифрами
                RomanGetResult(inputText, signOper);
            }else { //обработка непредвиденного варианта
                System.out.println("Произошла непредвиденная ситуация с данными");
            }

            // подготовка к получению следующей порции данных (обнуление счетчиков, приветствие и ввод)
            arab = rom = operator = 0;
            signOper = '\0';
            f = false;
            System.out.println(greeting);
            inputText = scanner.nextLine();
        }

        System.out.println("OK");

    }



    public static void ArabicGetResult(String inputText, char signOper)
    {   //Функция вывода результата операции с арабскими цифрами

        String[] operands; //два операнда, выделяемых из введенной строки
        int a, b; //операнды в формате целого числа (преоборазуются из operands)
        String c; //разделитель для рассплитовки двух операторов

        switch (signOper){
            case ('+') -> c = "\\+";
            case ('-') -> c = "-";
            case ('*') -> c = "\\*";
            case ('/') -> c = "/";
            default -> throw new IllegalStateException("Не ожидаемое значение оператора(+-*/): " + signOper);
        }

        operands = inputText.split(c);
        a = Integer.parseInt(operands[0]);
        b = Integer.parseInt(operands[1]);
        if ((a < 0) | (a > 10) | (b < 0) | (b > 10)){
            System.out.print("числа должны быть в промежутке между 1 и 10, хотя ответ будет: ");
        }
        switch (signOper) {
            case ('+') -> System.out.println(a + b);
            case ('-') -> System.out.println(a - b);
            case ('*') -> System.out.println(a * b);
            case ('/') -> System.out.println(a / b);
            default -> throw new IllegalStateException("Не ожидаемое значение оператора(+-*/): " + signOper);
        }
    }



    public static void RomanGetResult(String inputText, char signOper) {
        //Функция вывода результата операции с римскими цифрами

        String[] operands; //два операнда, выделяемых из введенной строки
        int a, b, y; //операнды в формате целого числа (преоборазуются из operands)
        String c; //разделитель для рассплитовки двух операторов

        switch (signOper){
            case ('+') -> c = "\\+";
            case ('-') -> c = "-";
            case ('*') -> c = "\\*";
            case ('/') -> c = "/";
            default -> throw new IllegalStateException("Не ожидаемое значение оператора(+-*/): " + signOper);
        }

        operands = inputText.split(c);
        try {
            RomToArabicConvert X1 = RomToArabicConvert.valueOf(operands[0]);
            RomToArabicConvert X2 = RomToArabicConvert.valueOf(operands[1]);
            a = X1.getArab();
            b = X2.getArab();
        }catch (IllegalArgumentException e){
            System.out.println("числа должны быть в промежутке между 1 и 10: " + e.getMessage());
            return;
        }


        if ((a < 0) | (a > 10) | (b < 0) | (b > 10)){   //обработка ошибки при выходе за допустимые пределы при вводе данных
            System.out.println("числа должны быть в промежутке между 1 и 10");
        }else {                                         //вычисление выражения (значение в "y")
            switch (signOper) {
                case ('+') -> y = a + b;
                case ('-') -> y = a - b;
                case ('*') -> y = a * b;
                case ('/') -> y = a / b;
                default -> throw new IllegalStateException("Не ожидаемое значение оператора(+-*/): " + signOper);
            }

            if (y <= 0){ //обработка ошибки в случае получения отрицательного числа
                System.out.println("в римской системе нет отрицательных чисел и нуля");
            }else {     //преобразование результата в римскую систему счисления и вывод
                RomToArabicConvert r = RomToArabicConvert.getRom(y-1);
                System.out.println(r);
            }

        }

    }


}
