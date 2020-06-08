
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class task6 {
    /**1. Число Белла - это количество способов, которыми массив из n элементов может быть разбит на непустые подмножества.
     * Создайте функцию, которая принимает число n и возвращает соответствующее число Белла. */
    public static int bell(int in){
        int[][] stirl = new int[in+1][in+1];
        stirl[0][0]=1;
        stirl[in][in]=1;
        for (int i=1; i<=in;i++) {   //числа Стирлинга
            for (int j = 1; j < in; j++){
                stirl[i][j]=stirl[i-1][j-1]+j*stirl[i-1][j];
            }
        }
        int sum=0;
        for (int j=0;j<=in;j++)sum+=stirl[in][j]; //сумма Стирлинга 2 рода = число Белла
        return sum;
    }

    /**2. – Если слово начинается с согласного, переместите первую букву (буквы) слова до гласного до конца слова и добавьте «ay» в конец.
     *    – Если слово начинается с гласной, добавьте "yay" в конце слова. */
    public static String translateWord(String text){
        if (text.isEmpty()) return "";
        if(!Character.isLetter(text.charAt(0))) return "";
        int ind = -1;
        int len = text.length();
        
        for (int i = 0; i < len;i++)//начинается на гласную
        {
            if ((text.charAt(i)=='a')||(text.charAt(i)=='e')||(text.charAt(i)=='i')||(text.charAt(i)=='o')||(text.charAt(i)=='u')||(text.charAt(i)=='y')||
                    (text.charAt(i)=='A')||(text.charAt(i)=='E')||(text.charAt(i)=='I')||(text.charAt(i)=='O')||(text.charAt(i)=='U')||(text.charAt(i)=='Y')) {
                ind = i; //запоминаем начало гласной
                break;
            }
        }
        if (ind == -1) return "-1";
        else if((text.charAt(0)=='a')||(text.charAt(0)=='e')||(text.charAt(0)=='i')||(text.charAt(0)=='o')||(text.charAt(0)=='u')||(text.charAt(0)=='y')||
                (text.charAt(0)=='A')||(text.charAt(0)=='E')||(text.charAt(0)=='I')||(text.charAt(0)=='O')||(text.charAt(0)=='U')||(text.charAt(0)=='Y'))
            return text+"yay";//если с гласной
        else return text.substring(ind)+text.substring(0,ind)+"ay"; //если с согласной
    }

    public static String translateSentence(String text){
        String str="";
        String buff="";
        for(char c: text.toCharArray()){
            if (!Character.isLetter(c)) {
                str += translateWord(buff);
                str += c;
                buff = "";
            }
            else{
                buff+=c;
            }
        }
        return str+=translateWord(buff);
    }

    /**3. Учитывая параметры RGB (A) CSS, определите, является ли формат принимаемых значений допустимым или нет.
     * Создайте функцию, которая принимает строку (например, " rgb(0, 0, 0)") и возвращает true,
     * если ее формат правильный, в противном случае возвращает false.*/
    public static boolean validColor(String str){
        String[] rgb=str.split("[\\(\\),]"); //убираем знаки разделения
        if (rgb[0]!="rgb" || rgb[0]!="rgba") return false;
        for (int i=1;i<rgb.length;i++){ //проверяем все значения
            if (rgb[i].matches("\\d")) {
                if (Integer.parseInt(rgb[i]) < 0 || Integer.parseInt(rgb[i]) > 255) return false;
            }
            else return false;
        }
        return true;
    }

    /**4. Создайте функцию, которая принимает URL (строку), удаляет дублирующиеся параметры запроса и параметры,
     * указанные во втором аргументе (который будет необязательным массивом).*/
    public static String stripUrlParams(String url, String[] arrParam) throws MalformedURLException{
        URL urlN = new URL(url);
        String[] params=urlN.getQuery().split("&");//разбиваем параметры
        String out=urlN.getProtocol()+"://"+urlN.getHost();//формируем адрес

        Map<String, String> map = new HashMap<String, String>();
        for(String p: params) {
            map.put(p.split("=")[0],p.split("=")[1]);//вносим все параметры со значениями
        }
        for(String p: arrParam){//убираем не нужные
            map.remove(p);
        }
        if (map.size()!=0) {//формируем параметры
            out+="?";
            for (Map.Entry<String, String> entry : map.entrySet()) {
                out += entry.getKey() + "=" + entry.getValue() + "&";
            }
        }
        return out.substring(0,out.length()-1);
    }

    public static String stripUrlParams(String url)throws MalformedURLException {
        return stripUrlParams(url,new String[0]);
    }

    /**5. Напишите функцию, которая извлекает три самых длинных слова из заголовка газеты и преобразует их в хэштеги.
     * Если несколько слов одинаковой длины, найдите слово, которое встречается первым.*/
    public static String[] getHashTags(String str){
        List<String> words=Arrays.asList(str.toLowerCase().split(" "));//разбиваем по пробелам

        Comparator<String> compare = (String  o1, String o2) -> o2.length()-o1.length();
        Collections.sort(words, compare);//сортируем

        for(int i=0;i<words.size();i++){
            words.add("#"+words.remove(i));//добавляем теги
        }
        while (words.size()>3) words.remove(words.size()-1);//убираем лишнии слова

        return words.toArray(new String[words.size()]);
    }

    /**6. Создайте функцию, которая принимает число n и возвращает n-е число в последовательности Улама.*/
    public static int ulam(int n){
        int[] arr = new int[n];
        arr[0]=1;
        arr[1]=2;
        int l=2;
        int next=3;
        while (next<Integer.MAX_VALUE && l<n){
            int k =0;
            for (int i=0;i<l;i++){
                for (int j=l-1; j>i; j--){ //перебор сумм
                    if (arr[i]+arr[j]==next && arr[i]!=arr[j])
                        k++;
                    else if (k > 1)
                        break;
                }
                if (k > 1)
                    break;
            }
            if (k == 1) {
                arr[l]=next;
                l++;
            }
            next++;
        }
        return arr[n-1];
    }

    /**7. Напишите функцию, которая возвращает самую длинную неповторяющуюся подстроку для строкового ввода.*/
    public static String longestNonrepeatingSubstring(String str){
        String out = "";
        for (int i =0;i<str.length();i++)
        {
            Set<Character> set = new HashSet<>();
            int j=i;
            for (;j<str.length();j++) //добавляем символы
            {
                char c = str.charAt(j);
                if (set.contains(c))
                {
                    break;
                } else {
                    set.add(c);
                }
            }
            if (out.length()<j-i+1)//сохраняем результат
            {
                out=str.substring(i,j);
            }
        }
        return out;
    }

    /**8. Создайте функцию, которая принимает арабское число и преобразует его в римское число.*/
    public static String convertToRoman(int num){
        int count=1000;
        String leter="MDCLXVI"; //набор цифр
        int i=0;
        String out="";
        while(i!=7){ //перебор всех цифр
            if (num/count>0) {
                out += leter.charAt(i); //добавляем цифры
                num-=count;
            }
            else {
                if (i % 2 == 0) count = count / 2; //перемещаемся по 1000-500-100...
                else count = count / 5;
                i++;
            }
        }
        return out;
    }

    /**9. Создайте функцию, которая принимает строку и возвращает true или false в зависимости от того,
     * является ли формула правильной или нет.*/
    public static boolean formula(String str) {
        if (!str.matches("^(\\d+\\s[\\*\\+-/]\\s\\d+\\s=\\s\\d+)$"))//проверка на формулу
            return false;
        String[] left = str.split("=")[0].trim().split(" "); //разделяем на левую и правую часть
        String right = str.split("=")[1].trim();
        int fun = 0;

        //проверяем результат формулы
        if (left[1] == "+") fun = Integer.parseInt(left[0]) + Integer.parseInt(left[2]);
        if (left[1] == "-") fun = Integer.parseInt(left[0]) - Integer.parseInt(left[2]);
        if (left[1] == "*") fun = Integer.parseInt(left[0]) * Integer.parseInt(left[2]);
        if (left[1] == "/") fun = Integer.parseInt(left[0]) / Integer.parseInt(left[2]);

        if (fun == Integer.parseInt(right)) return true;
        return false;
    }

    /**10. Создайте функцию, которая возвращает значение true, если само число является палиндромом
     * или любой из его потомков вплоть до 2 цифр (однозначное число - тривиально палиндром).
     * Прямой потомок числа создается путем суммирования каждой пары соседних цифр, чтобы создать цифры следующего числа.*/
    public static boolean palindromedescendant(int num){
        String str=String.valueOf(num);
        while(true){ //переварачиваем и сравниваем
            if (str.equals(new StringBuilder(str).reverse().toString())){
                return true;
            }else{
                if (str.length()%2==1)
                    return false;
                String newStr=""; //находим сумму пар
                for (int i=1;i<str.length();i+=2){
                    newStr+=Integer.parseInt(String.valueOf(str.charAt(i)))+Integer.parseInt(String.valueOf(str.charAt(i-1)));
                }
                str=newStr;
            }
        }
    }
}
