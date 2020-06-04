import java.util.*;

public class task3 {

    public static void main(String[] args) {
        System.out.println(nextPrime(11));
    }

    /**1. Квадратное уравнение ax2 + bx + c = 0 имеет либо 0, либо 1, либо 2 различных решения для действительных значений x.
     * учитывая a, b и c, вы должны вернуть число решений в уравнение.*/
    public static int solutions(int a, int b, int c){
        int D = b*b-4*a*c; // находим Дискриминант
        if (D>0) return 2; // Д>0 2 корня
        if (D==0) return 1;// Д=0 1 корень
        else return 0; //// Д<0 0 корней
    }

    /**2. Напишите функцию, которая возвращает позицию второго вхождения " zip " в строку, или -1,
     *  если оно не происходит по крайней мере дважды. Ваш код должен быть достаточно общим,
     *  чтобы передать все возможные случаи, когда "zip" может произойти в строке. */
    public static int findZip(String str){
        str=str.replaceFirst("zip",""); //убираем 1 вхождение
        if (str.contains("zip"))return str.indexOf("zip")+3; // находим 2 вхождение
        return -1; //если нет 2 вхождения
    }

    /**3. Создайте функцию, которая проверяет, является ли целое число совершенным числом или нет.
     * Совершенное число - это число, которое можно записать как сумму его множителей, исключая само число. */
    public static boolean checkPerfect(int n){
        int sum=0;      //сумма множителей числа
        for(int i=1;i<=n/2;i++){
            if (n%i==0)sum+=i;  //число является множителем числа
        }
        return n==sum;
    }

    /**4. Создайте функцию, которая принимает строку и возвращает новую строку
     * с заменой ее первого и последнего символов, за исключением трех условий:
     * – Если длина строки меньше двух, верните "несовместимо".".
     * – Если первый и последний символы совпадают, верните "два-это пара.". */
    public static String flipEndChars(String str){
        char start=str.charAt(0);   //первая буква
        char end=str.charAt(str.length()-1);    // последняя буква
        if (str.length()<2) return "Incompatible."; //в слове меньше 2 букв
        else if (start==end) return "Two's a pair.";//буквы совпадают
        else return end+str.substring(1,str.length()-1)+start; //меняем буквы в слове
    }

    /**5. Создайте функцию, которая определяет, является ли строка допустимым шестнадцатеричным кодом.*/
    public static boolean isValidHexCode(String str){
        return str.matches("#[a-fA-F0-9]{6}");
    }

    /**6. Напишите функцию, которая возвращает true, если два массива имеют одинаковое количество уникальных элементов,
     * и false в противном случае.*/
    public static boolean same(Integer[] arr1, Integer[] arr2){
        return new HashSet<Integer>(Arrays.asList(arr1)).size()==new HashSet<Integer>(Arrays.asList(arr2)).size(); //создаем hashSet'ы и сверяем их длину
    }

    /**7. Число Капрекара-это положительное целое число, которое после возведения в квадрат и разбиения
     * на две лексикографические части равно сумме двух полученных новых чисел:
     * – Если количество цифр квадратного числа четное, то левая и правая части будут иметь одинаковую длину.
     * – Если количество цифр квадратного числа нечетно, то правая часть будет самой длинной половиной,
     *      а левая-самой маленькой или равной нулю, если количество цифр равно 1.
     * – Учитывая положительное целое число n, реализуйте функцию, которая возвращает true, если это число Капрекара, и false, если это не так.*/
    public static boolean isKaprekar(int n){
        int a=n*n; //возводим в квадрат
        int cout = (int)Math.ceil((double)String.valueOf(a).length()/2); // среднее количество знаков
        int num1 = (int)(a%Math.pow(10,cout)); // правая часть
        int num2 = (int)(a/Math.pow(10,cout)); // левая часть
        return num1+num2==n;
    }

    /**8. Напишите функцию, которая возвращает самую длинную последовательность последовательных нулей в двоичной строке.*/
    public static String longestZero(String str){
        int length=0;       //наибольшая длина
        String res="";      //строка нолей
        while (str.indexOf("1")>-1){    //пока есть 1
            if (str.indexOf("1")>length) {  //если найденная последовательность 0 больше предыдущей
                length = str.indexOf("1");  //запоминаем длину
                res = str.substring(0, str.indexOf("1"));   //запоминаем строку
            }
            str=str.substring(str.indexOf("1")+1);  //удаляем провереный элемент
        }
        if (str.length()>length) {  //если последовательность в конце
            length = str.length();
            res=str;
        }
        return res;
    }

    /**9. Если задано целое число, создайте функцию, которая возвращает следующее простое число.
     * Если число простое, верните само число.*/
    public static int nextPrime(int n){
        for (int i=2; i<n/2;i++){   //проверка на простое число
            if (n%i==0) {
                return nextPrime(n+1);  //если не простое проверяем следующее
            }
        }
        return n;
    }

    /**10. Учитывая три числа, x, y и z, определите, являются ли они ребрами прямоугольного треугольника.*/
    public static boolean rightTriangle(int x,int y, int z) {
        return Math.sqrt(z*z+y*y)==x || Math.sqrt(x*x+z*z)==y || Math.sqrt(x*x+y*y)==z; //используем теорему Пифагора
    }
}
