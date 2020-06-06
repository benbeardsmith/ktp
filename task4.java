import java.util.*;

public class task4 {
    /**1. Бесси работает над сочинением для своего класса писателей. Поскольку ее почерк довольно плох,
     * она решает напечатать эссе с помощью текстового процессора. Эссе содержит N слов (1≤N≤100),
     * разделенных пробелами. Каждое слово имеет длину от 1 до 15 символов включительно и состоит только
     * из прописных или строчных букв. Согласно инструкции к заданию, эссе должно быть отформатировано
     * очень специфическим образом: каждая строка должна содержать не более K (1≤K≤80) символов, не считая пробелов.
     * К счастью, текстовый процессор Бесси может справиться с этим требованием, используя следующую стратегию:
     * – Если Бесси набирает Слово, и это слово может поместиться в текущей строке, поместите его в эту строку.
     *      В противном случае поместите слово на следующую строку и продолжайте добавлять к этой строке.
     *      Конечно, последовательные слова в одной строке все равно должны быть разделены одним пробелом.
     *      В конце любой строки не должно быть места.
     * – К сожалению, текстовый процессор Бесси только что сломался.
     *      Пожалуйста, помогите ей правильно оформить свое эссе! */
    public static String Text(int n, int k, String str){
        String[] text=str.split(" ");   //Разбиваем текст по пробелам
        String out = "";    //результат
        String line="";     //счетчик

        for (String word: text){
            if (line.length()+word.length()>k){ //если выходит за пределы к
                out+="\r\n" + word + " ";   //добавляем на следующую строку
                line=word;
            }
            else{
                out+=word+" ";  //добавляем в эту же строку
                line+=word;
            }
        }
        return out;
    }

    /**2. Напишите функцию, которая группирует строку в кластер скобок. Каждый кластер должен быть сбалансирован.*/
    public static String[] split(String str){
        List<String> list=new ArrayList<String>();
        int count=0;    //счетчик скобок
        String buff=""; //накопление скобок

        for(char c: str.toCharArray()){
            if (c=='(') count++;    //+ счетчик
            else count--;   //- счетчик
            buff+=c;    //запоминание скобки

            if (count==0) {
                list.add(buff); //добавляем в лист
                buff="";
            }
        }

        return list.toArray(new String[list.size()]);
    }

    /**3. Создайте две функции toCamelCase () и toSnakeCase (), каждая из которых берет одну строку и преобразует ее либо в camelCase, либо в snake_case.*/
    public static String toCamelCase(String str){
        String[] text=str.split("_");   //разбиваем по пробелам
        String out=text[0];
        for(int i=1;i<text.length;i++)
            out+=text[i].substring(0,1).toUpperCase()+text[i].substring(1); //переводим первую букву в верхний регистр
        return out;
    }

    public static String toSnakeCase(String str){
        String out="";
        for(int i=0;i<str.length();i++){
            if (str.charAt(i)>=65 && str.charAt(i)<=90) //большие буквы
                out+="_"+String.valueOf(str.charAt(i)).toLowerCase(); //уменьшаем и добавляем _
            else
                out+=str.charAt(i); //добавляем
        }
        return out;
    }

    /**4. Напишите функцию, которая вычисляет сверхурочную работу и оплату, связанную с сверхурочной работой.

     Работа с 9 до 5: обычные часы работы После 5 вечера это сверхурочная работа Ваша функция получает массив с 4 значениями:
     – Начало рабочего дня, в десятичном формате, (24-часовая дневная нотация)
     – Конец рабочего дня. (Тот же формат)
     – Почасовая ставка
     – Множитель сверхурочных работ

     Ваша функция должна возвращать: $ + заработанные в тот день (округлены до ближайшей сотой) */
    public static String overTime(double[] arr){
        double res=0;
        if (17-arr[0]>0) res+=(17-arr[0])*arr[2];   //без переработки
        if (arr[1]-17>0) res+=(arr[1]-17)*arr[2]*arr[3]; //переработка
        return String.format("$%.2f",res);
    }

    /**5. Индекс массы тела (ИМТ) определяется путем измерения вашего веса в килограммах и деления на квадрат вашего роста в метрах.
     * Категории ИМТ таковы:

     Недостаточный вес: <18,5
     Нормальный вес: 18.5-24.9
     Избыточный вес: 25 и более Создайте функцию, которая будет принимать вес и рост (в килограммах, фунтах, метрах или дюймах)
     и возвращать ИМТ и связанную с ним категорию. Округлите ИМТ до ближайшей десятой.*/
    public static String BMI(String m, String h){
        double mas=Double.valueOf(m.split(" ")[0]); //вес
        if (m.contains("pounds")) mas*=0.4536;            //перевод в кг
        double height=Double.valueOf(h.split(" ")[0]);//рост
        if (m.contains("inches")) height*=0.0254;       // перевод в метры

        double score=Math.round((mas/(height*height)) * 10) / 10;

        if (score<18.5) return score+" Underweight";
        else if (score<25) return score+" Normal weight";
        else return score+" Overweight";
    }

    /**6. Создайте функцию, которая принимает число и возвращает его мультипликативное постоянство,
     * которое представляет собой количество раз, которое вы должны умножать цифры в num, пока не достигнете одной цифры.*/
    public static int bugger(int num){
        int count=0;    //кол-во делений
        while(num>9){
            int mul=1;  //перемножение
            for (char c: String.valueOf(num).toCharArray()){
                mul*=c-48; //результат перемножения
            }
            count++;
            num=mul;
        }
        return count;
    }

    /**7. Напишите функцию, которая преобразует строку в звездную стенографию. Если символ повторяется n раз, преобразуйте его в символ*n. */
    public static String toStarShorthand(String str){
        int count=1;
        char c=str.charAt(0);
        String out="";
        for(int i=1;i<str.length();i++){
            if (str.charAt(i)==c) count++;  //считаем кол-во поряд идущих символов
            else {if (count>1)
                out+=c+"*"+count;   //если подряд больше 1
            else
                out+=c;
                c=str.charAt(i);
                count=1;
            }
        }
        if (count>1) out+=c+"*"+count;
        else out+=c;
        return out;
    }

    /**8. Создайте функцию, которая возвращает true, если две строки рифмуются, и false в противном случае.
     * Для целей этого упражнения две строки рифмуются, если последнее слово из каждого предложения содержит одни и те же гласные.*/
    public static boolean doesRhyme(String str1, String str2){
        String word1 = str1.substring(str1.lastIndexOf(" ")).toLowerCase(); //последнее слово
        String word2 = str2.substring(str2.lastIndexOf(" ")).toLowerCase();

        for (char c: "aeoiuy".toCharArray()){ //проверяем на наличие или отсутствие буквы в слове
            if (word1.contains(String.valueOf(c))!=word2.contains(String.valueOf(c))) return false;
        }
        return true;
    }

    /**9. Создайте функцию, которая принимает два целых числа и возвращает true,
     * если число повторяется три раза подряд в любом месте в num1 и то же самое число повторяется два раза подряд в num2.*/
    public static boolean trouble(long num1, long num2){
        String n1=String.valueOf(num1);
        String n2=String.valueOf(num2);
        for(int i=0;i<10;i++){
            String a=i+String.valueOf(i)+i; //3 подряд цифры
            String b=String.valueOf(i)+i;   //2 подряд цифры
            if (n1.contains(a) && n2.contains(b)) return true;
        }
        return false;
    }

    /**10. Предположим, что пара одинаковых символов служит концами книги для всех символов между ними.
     * Напишите функцию, которая возвращает общее количество уникальных символов (книг, так сказать) между всеми парами концов книги.*/
    public static int countUniqueBooks(String str, char c){
        boolean activ=false;
        Set<Character> set = new HashSet<Character>();
        for (int i=0; i<str.length();i++) { //смотрим каждый мивол
            if (activ && str.charAt(i)!=c)  //если было начало книги
                set.add(str.charAt(i));
            if (str.charAt(i)==c) activ=!activ;//меняем состояние считывания
        }
        return set.size();
    }
}
