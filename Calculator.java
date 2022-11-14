package Calculator;

import java.util.Scanner;

class Calculator {
    int arg1, arg2;
    String operator;
    static byte key1;
    static byte key2;

    int strToInt(String arg) throws Exception {
        int num;
        switch (arg) {
            case "1":
                num = 1;
                key1 = 1;
                break;
            case "2":
                num = 2;
                key1 = 1;
                break;
            case "3":
                num = 3;
                key1 = 1;
                break;
            case "4":
                num = 4;
                key1 = 1;
                break;
            case "5":
                num = 5;
                key1 = 1;
                break;
            case "6":
                num = 6;
                key1 = 1;
                break;
            case "7":
                num = 7;
                key1 = 1;
                break;
            case "8":
                num = 8;
                key1 = 1;
                break;
            case "9":
                num = 9;
                key1 = 1;
                break;
            case "10":
                num = 10;
                key1 = 1;
                break;
// Соответствие римских чисел арабским
            case "I":
                num = 1;
                key2 = 1;
                break;
            case "II":
                num = 2;
                key2 = 1;
                break;
            case "III":
                num = 3;
                key2 = 1;
                break;
            case "IV":
                num = 4;
                key2 = 1;
                break;
            case "V":
                num = 5;
                key2 = 1;
                break;
            case "VI":
                num = 6;
                key2 = 1;
                break;
            case "VII":
                num = 7;
                key2 = 1;
                break;
            case "VIII":
                num = 8;
                key2 = 1;
                break;
            case "IX":
                num = 9;
                key2 = 1;
                break;
            case "X":
                num = 10;
                key2 = 1;
                break;
            default:
                throw new FormatOperandException();
        }
        return num;
    }

    int result(int _arg1, int _arg2, String _operator) throws Exception {
        return switch (_operator) {
            case "+" -> _arg1 + _arg2;
            case "-" -> _arg1 - _arg2;
            case "/" -> _arg1 / _arg2;
            case "*" -> _arg1 * _arg2;
            default -> throw new FormatException();
        };
    }

    // Перевод из арабских чисел в римские
    String toRome(int argIn) {
        int uni = argIn % 10;
        int dec = (argIn % 100) / 10;
        String _uni;
        String _dec;
        if (argIn == 100)
            return "C";
        _dec = switch (dec) {
            case 1 -> "X";
            case 2 -> "XX";
            case 3 -> "XXX";
            case 4 -> "XL";
            case 5 -> "L";
            case 6 -> "LX";
            case 7 -> "LXX";
            case 8 -> "LXXX";
            case 9 -> "XC";
            default -> "";
        };

        _uni = switch (uni) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> "";
        };
        return _dec + _uni;
    }

    public static String calc(String input) throws Exception {
        int resultat;
        String resultatInString = "";
        Calculator calc = new Calculator();

        try {
            String[] primMass = input.split(" ");
// Проверка на соответствие требуемому формату ввода /операнд1 оператор операнд2/
            if (primMass.length != 3)
                throw new FormatException();

            calc.arg1 = calc.strToInt(primMass[0]);
            calc.arg2 = calc.strToInt(primMass[2]);
// Проверка на соответствие обоих числа одному формату (арабскому или римскому)
            if (key2 == key1)
                throw new ArabicAndRomeException();
            else {
                calc.operator = primMass[1];
                resultat = calc.result(calc.arg1, calc.arg2, calc.operator);
                if (key1 == 1)
                    resultatInString = String.valueOf(resultat);
                else if (key2 == 1) {
                    if (resultat < 1) //проверка на результат в римском исчислении
                        throw new NegativRome();
                    else resultatInString = calc.toRome(resultat); // перевод из арабских в римские
                }
            }
        } catch (FormatOperandException e) {
            System.err.println("Неверный формат числа. Требуемый диапазон (1 - 10) или (I - X)");
            System.exit(1);
        } catch (FormatException e) {
            System.err.println("Формат математической операции не удовлетворяет " +
                    "заданию - два операнда и один оператор (+, -, /, *) через пробел");
            System.exit(1);
        } catch (NegativRome e) {
            System.err.println("Результат в Римском исчислени не может быть меньше единицы");
            System.exit(1);
        } catch (ArabicAndRomeException e) {
            System.err.println("Используются одновременно разные системы счисления");
            System.exit(1);
        }
        return resultatInString;
    }

    public static void main(String[] args) throws Exception {
        String otvet;
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите строку для вычисления:");
        String primer = scan.nextLine();
        otvet = calc(primer);
        System.out.println("Результат: " + otvet);
    }
}







