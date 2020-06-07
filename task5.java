import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.*;

public class task5 {
    /**1. Создайте две функции, которые принимают строку и массив и возвращают закодированное или декодированное сообщение.
     * Первая буква строки или первый элемент массива представляет собой символьный код этой буквы.
     * Следующие элементы-это различия между символами: например, A +3 --> C или z -1 --> y. */
    public static int[] encrypt(String str){
        char[] arrChar=str.toCharArray(); //массив символов
        int[] arr=new int[arrChar.length]; //результирующий массив
        arr[0]=arrChar[0];  //первый символ
        for (int i=1;i<arrChar.length;i++){
            arr[i]=arrChar[i]-arrChar[i-1]; //разница
        }
        return arr;
    }

    public static String decrypt(int[] arr){
        String text="";
        int count=0; //прошлый код
        for (int i: arr){
            text+=String.valueOf((char)(i+count)); //перевод
            count=i+count;
        }
        return text;
    }

    /**2. Создайте функцию, которая принимает имя шахматной фигуры, ее положение и целевую позицию.
     * Функция должна возвращать true, если фигура может двигаться к цели, и false, если она не может этого сделать.
     * Возможные входные данные - "пешка", "конь", "слон", "Ладья", "Ферзь"и " король".*/
    public static boolean canMove(String str,String start, String end){
        char ch1 = start.charAt(0);
        int num1 = Integer.parseInt(String.valueOf(start.charAt(1)));
        char ch2 = end.charAt(0);
        int num2 = Integer.parseInt(String.valueOf(end.charAt(1)));
        if (ch1==ch2 && num1==num2) return false;
        
        if (str=="King") {//Король
            if (Math.abs(ch1 - ch2) < 2 && Math.abs(num1 - num2) < 2) return true;
            return false;
        }else
        if (str=="Queen") {//Ферзь
            if ((ch1 == ch2 && num1 != num2) || (ch1 != ch2 && num1 == num2)) return true;
            if (Math.abs(ch1 - ch2) == Math.abs(num1 - num2)) return true;
        }else
        if (str=="Rook") {//Ладья
            if ((ch1 == ch2 && num1 != num2) || (ch1 != ch2 && num1 == num2)) return true;
            return false;
        }else
        if (str=="Bishop") {//Слон
            if (Math.abs(ch1 - ch2) == Math.abs(num1 - num2)) return true;
            return false;
        }else
        if (str=="Knight") {//Конь
            if ((Math.abs(ch1 - ch2) == 2 && Math.abs(num1 - num2) == 1) || (Math.abs(ch1 - ch2) == 1 && Math.abs(num1 - num2) == 2))
                return true;
            return false;
        }else
        if (str=="Pawn") {//Пешка
            if (ch1 == ch2 && num1 == 2 && num2 == 4) return true;
            if (ch1 == ch2 && num1 == (num2 - 1)) return true;
            return false;
        }
        
        return false;
    }

    /**3. Входная строка может быть завершена, если можно добавить дополнительные буквы, и никакие буквы не должны быть удалены,
     * чтобы соответствовать слову. Кроме того, порядок букв во входной строке должен быть таким же, как и порядок букв в последнем слове.
     * Создайте функцию, которая, учитывая входную строку, определяет, может ли слово быть завершено.*/
    public static boolean canComplete(String str1,String str2){
        int pos=-1; //начальная позиция
        for(char c: str1.toCharArray()) {//перебор дополняемого слова
            if (str2.indexOf(String.valueOf(c))<pos) return false;//если позиция найденного символа левее
            else pos=str2.indexOf(String.valueOf(c));//если правее, то продолжаем проверку
        }
        return true;
    }

    /**4. Создайте функцию, которая принимает числа в качестве аргументов, складывает их вместе
     * и возвращает произведение цифр до тех пор, пока ответ не станет длиной всего в 1 цифру. */
    public static int sumDigProd(int... number){
        int num=0;
        for (int i=0;i<number.length;i++) {
            num+=number[i];
        }

        while(num>9){
            int mul=1;  //перемножение
            for (char c: String.valueOf(num).toCharArray()){
                mul*=c-48; //результат перемножения
            }
            num=mul;
        }
        return num;
    }

    /**5. Напишите функцию, которая выбирает все слова, имеющие все те же гласные (в любом порядке и / или количестве),
     * что и первое слово, включая первое слово. */
    public static String[] sameVowelGroup(String[] str){
        List<String> list=new ArrayList<String>();
        Set<Character> set = new HashSet<>(); //набор гласных из первого слова
        for (char c:str[0].toCharArray()){
            if (String.valueOf(c).matches("[aeyuio]")){//заполняем набор
                set.add(c);
            }
        }

        for(String s: str){
            boolean flag=true;
            for(char c: set){//проверяем совпадение с набором
                flag&=s.contains(String.valueOf(c));
            }
            if (flag) list.add(s);
        }

        return list.toArray(new String[list.size()]);
    }

    /**6. Создайте функцию, которая принимает число в качестве аргумента и возвращает true, если это число является
     * действительным номером кредитной карты, а в противном случае-false. Номера кредитных карт должны быть длиной
     * от 14 до 19 цифр и проходить тест Луна, описанный ниже:
     * – Удалите последнюю цифру (это"контрольная цифра").
     * – Переверните число.
     * – Удвойте значение каждой цифры в нечетных позициях. Если удвоенное значение имеет более 1 цифры,
     *      сложите цифры вместе (например, 8 x 2 = 16 ➞ 1 + 6 = 7).
     * – Добавьте все цифры.
     * – Вычтите последнюю цифру суммы (из шага 4) из 10.
     * Результат должен быть равен контрольной цифре из Шага 1.*/
    public static boolean validateCard (long num){
        if (!String.valueOf(num).matches("[0-9]{14,19}")) return false; //проверка длины
        //step 1-2
        long lastNum=num%10; //последний символ
        num=num/10; //убираем последний символ
        StringBuilder convert = new StringBuilder();
        num = Long.parseLong(convert.append(num).reverse().toString()); //переварачиваем
        //step 3-4
        int sum=0;
        String str=String.valueOf(num); //переводим в строку
        for (int i=0;i<str.length();i++){
            if (i%2==0) { //нечетное
                int number = Integer.parseInt(String.valueOf(str.charAt(i))) * 2;//домнажаем
                sum += number / 10;
                sum += number % 10;
            }
            else
                sum += Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        //step 5
        if (10-sum%10==lastNum) //сравниваем
            return true;
        return false;
    }

    /**7. Напишите функцию, которая принимает положительное целое число от 0 до 999 включительно
     * и возвращает строковое представление этого целого числа, написанное на английском языке.*/
    public static String numToEng(int number){
        String s="";
        if (number==0) return "zero";
        switch (number/100){
            case 1: {s+="one hundred ";break;} //сотни
            case 2: {s+="two hundred ";break;}
            case 3: {s+="three hundred ";break;}
            case 4: {s+="four hundred ";break;}
            case 5: {s+="five hundred ";break;}
            case 6: {s+="six hundred ";break;}
            case 7: {s+="seven hundred ";break;}
            case 8: {s+="eight hundred ";break;}
            case 9: {s+="nine hundred ";break;}
        }
        switch (number/10%10){
            case 1: {switch (number%10){
                case 1: {s+="eleven";return s;} //от 10 до 19
                case 2: {s+="twelve";return s;}
                case 3: {s+="thirteen";return s;}
                case 4: {s+="fourteen";return s;}
                case 5: {s+="fifteen";return s;}
                case 6: {s+="sixteen";return s;}
                case 7: {s+="seventeen";return s;}
                case 8: {s+="eighteen";return s;}
                case 9: {s+="nineteen";return s;}}}
            case 2: {s+="twenty ";break;} //десятки
            case 3: {s+="thirty ";break;}
            case 4: {s+="forty ";break;}
            case 5: {s+="fifty ";break;}
            case 6: {s+="sixty ";break;}
            case 7: {s+="seventy ";break;}
            case 8: {s+="eighty ";break;}
            case 9: {s+="ninety ";break;}
        }
        switch (number%10){
            case 1: {s+="one";break;} //единцы
            case 2: {s+="two";break;}
            case 3: {s+="three";break;}
            case 4: {s+="four";break;}
            case 5: {s+="five";break;}
            case 6: {s+="six";break;}
            case 7: {s+="seven";break;}
            case 8: {s+="eight";break;}
            case 9: {s+="nine";break;}
        }
        return s;
    }

    public static String numToRus(int number){
        String s="";
        if (number==0) return "ноль";
        switch (number/100){
            case 1: {s+="сто ";break;} //сотни
            case 2: {s+="двести ";break;}
            case 3: {s+="триста ";break;}
            case 4: {s+="четыреста ";break;}
            case 5: {s+="пятьсот ";break;}
            case 6: {s+="шестьсот ";break;}
            case 7: {s+="семьсот ";break;}
            case 8: {s+="восемьсот ";break;}
            case 9: {s+="девятьсот ";break;}
        }
        switch (number/10%10){
            case 1: {s+="десять ";break;} //десятки
            case 2: {s+="двадцать ";break;}
            case 3: {s+="тридцать ";break;}
            case 4: {s+="сорок ";break;}
            case 5: {s+="пятьдесят ";break;}
            case 6: {s+="шестьдесят ";break;}
            case 7: {s+="семьдесят ";break;}
            case 8: {s+="восемьдесят ";break;}
            case 9: {s+="девяносто ";break;}
        }
        switch (number%10){
            case 1: {s+="один";break;} //единицы
            case 2: {s+="два";break;}
            case 3: {s+="три";break;}
            case 4: {s+="четыре";break;}
            case 5: {s+="пять";break;}
            case 6: {s+="шесть";break;}
            case 7: {s+="семь";break;}
            case 8: {s+="восемь";break;}
            case 9: {s+="девять";break;}
        }
        return s;
    }

    /**8. Создайте функцию, которая возвращает безопасный хеш SHA-256 для данной строки.
     * Хеш должен быть отформатирован в виде шестнадцатеричной цифры.*/
    public static String getSha256Hash(String str)throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance("SHA-256"); //задаем кодировку
        byte[] out=digest.digest(str.getBytes(StandardCharsets.UTF_8)); //создаем и кодируем
        return HexBin.encode(out).toLowerCase();
    }

    /**9. Напишите функцию, которая принимает строку и возвращает строку с правильным регистром.
     * Слова and, the, of и in должны быть строчными. Все остальные слова должны иметь первый символ
     * в верхнем регистре, а остальные-в Нижнем. */
    public static String correctTitle(String str){
        String out ="";
        String[] text = str.toLowerCase().split(" ");//делим по пробелам
        for(String s:text)
        {
            String word = String.valueOf(str.charAt(0)).toUpperCase();
            if(str.equals("and")||str.equals("the")||str.equals("of")||str.equals("in")) out = out + str + " ";
            else out = out + word +str.substring(1)+" "; //добавляем измененное слово
        }
        return out;
    }

    /**10. Напишите функцию, которая принимает целое число n и возвращает "недопустимое", если n не является
     * центрированным шестиугольным числом или его иллюстрацией в виде многострочной прямоугольной строки в противном случае.*/
    public static String hexLattice(int num){
        String text="";
        int n=1;
        int k=1;
        while (n<num){ //проверяем, что это центрированный шестиугольник
            n+=6*k;
            k++;
        }
        if (n!=num) return "Invalid";

        for (int i=-(k-1);i<k;i++){ //цикл от -(кол-во строк до середины) до +(кол-во строк до середины)
            if (i!=0) text+=String.format("%" + Math.abs(i) + "s", ""); // начальный отступ
            text+=String.format("%" + (k-Math.abs(i)+1) + "s", "").replace(" "," o")+"\r\n"; //записываем о
        }
        return text;
    }
}
