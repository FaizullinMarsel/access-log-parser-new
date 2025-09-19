import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int totalLineNumber = 0;
        int startChar;
        int endChar;
        String userAgent="";
        int yandexBot = 0;
        int googleBot = 0;
        String value = "";
        System.out.println("Введите название файла и нажмите <Enter>: ");
        String text = new Scanner(System.in).nextLine();
        File path = new File(text);
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader reader =
                    new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                totalLineNumber++;
                int length = line.length();
                if (length > 1024){
                    throw  new TooLongLengthLine("Длина строки больше 1024 символов.Номер строки: " + totalLineNumber);
                }
                startChar = line.lastIndexOf("(");
                endChar = line.lastIndexOf(")");
                if (startChar != -1 && endChar != -1 && startChar < endChar){
                    userAgent = line.substring(startChar+1, endChar);
                }
                String[] parts = userAgent.split(";");
                if (parts.length >= 2) {
                    int fragment = parts[1].indexOf("/");
                    if (fragment > 0){
                        value= parts[1].substring(0, fragment).replace(" ","");
                    }
                }
                    if (value.equals("YandexBot")) yandexBot++;
                    if (value.equals("Googlebot")) googleBot++;
            }
            System.out.println("Доля запросов YandexBot = " + (yandexBot*100/totalLineNumber) + "%" + "\n"
                    + "Доля запросов Googlebot = " + (googleBot*100/totalLineNumber) + "%");

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
