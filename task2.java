import java.util.ArrayList;
import java.util.LinkedList;

public class task2 {
    /**1. Создайте функцию, которая повторяет каждый символ в строке n раз*/
    public static String repeat(String str, int n){
        String out="";
        for (char c: str.toCharArray()){
            for(int j=0; j<n; j++){
                out+=c;
            }
        }
        return out;
    }

    /**2. Создайте функцию, которая принимает массив и возвращает разницу между самыми большими и самыми маленькими числами.*/
    public static int diffMaxMin(int[] arr){
        int max=arr[0];
        int min=arr[0];
        for (int num : arr){
            max=Integer.max(max,num);
            min=Integer.min(min,num);
        }
        return max-min;
    }

    /**3. Создайте функцию, которая принимает массив в качестве аргумента и возвращает true или false в зависимости от того,
     * является ли среднее значение всех элементов массива целым числом или нет.*/
    public static boolean isAvgWhole(int[] arr){
        int sum=0;
        for (int num: arr){
            sum+=num;
        }
        return (((double)sum)/arr.length)%1==0;
    }

    /**4. Создайте метод, который берет массив целых чисел и возвращает массив, в котором каждое целое число
     * является суммой самого себя + всех предыдущих чисел в массиве.*/
    public static Integer[] cumulativeSum(int[] arr){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(arr[0]);
        for (int i=1;i<arr.length;i++){
            list.add(arr[i]+list.getLast());
        }
        return list.toArray(new Integer[list.size()]);
    }

    /**5. Создайте функцию, которая возвращает число десятичных знаков, которое имеет число (заданное в виде строки).
     * Любые нули после десятичной точки отсчитываются в сторону количества десятичных знаков.*/
    public static int getDecimalPlaces(String str){
        if (!str.contains(".")) return 0;
        return str.split("\\.")[1].length();
    }

    /**6. Создайте функцию, которая при заданном числе возвращает соответствующее число Фибоначчи.*/
    public static int Fibonacci(int a){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);list.add(2);
        for(int i=2;i<a;i++){
            list.add(list.getLast()+list.get(list.size()-2));
        }
        return list.getLast();
    }

    /**7. Почтовые индексы состоят из 5 последовательных цифр. Учитывая строку, напишите функцию,
     * чтобы определить, является ли вход действительным почтовым индексом.
     * Действительный почтовый индекс выглядит следующим образом:
     * – Должно содержать только цифры (не допускается использование нецифровых цифр).
     * – Не должно содержать никаких пробелов.
     * – Длина не должна превышать 5 цифр.*/
    public static boolean isValid(String str){
        return str.matches("[0-9]{5}");
    }

    /**8. Пара строк образует странную пару, если оба из следующих условий истинны:
     * – Первая буква 1-й строки = последняя буква 2-й строки.
     * – Последняя буква 1-й строки = первая буква 2-й строки.
     * – Создайте функцию, которая возвращает true, если пара строк представляет собой странную пару, и false в противном случае.*/
    public static boolean isStrangePair(String str1, String str2){
        return str2.startsWith(String.valueOf(str1.charAt(str1.length()-1))) && str2.endsWith(String.valueOf(str1.charAt(0)));
    }

    /**9. Создайте две функции: isPrefix(word, prefix-) и isSuffix (word, -suffix).
     * – isPrefix должен возвращать true, если он начинается с префиксного аргумента.
     * – isSuffix должен возвращать true, если он заканчивается аргументом суффикса.
     * – В противном случае верните false.*/
    public static boolean isPrefix(String str1, String str2){
        return str1.startsWith(str2.replace("-",""));
    }

    public static boolean isSuffix(String str1, String str2){
        return str1.endsWith(str2.replace("-",""));
    }

    /**10. Создайте функцию, которая принимает число (шаг) в качестве аргумента и возвращает количество полей
     * на этом шаге последовательности.*/
    public static int boxSeq(int a){
        if (a%2==0) return a;
        return a+2;
    }
}
