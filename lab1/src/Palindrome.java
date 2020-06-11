public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {     //перебо всех данных
            String s = args[i];
            if (isPalindrome(s)){                   //проверка на палиндром
                System.out.print(s+" ");              //вывод
            }
        }
    }

    public static String reverseString(String str){
        String temp="";
        for (int i=0;i<str.length();i++){        //перебор букв
            temp=str.charAt(i)+temp;            //запись в обратном порядке
        }
        return temp;
    }

    public static boolean isPalindrome(String s){
        return s.equals(reverseString(s));          //сравнение слова с обратным словом
    }
}
