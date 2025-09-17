import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        File path = new File("access.log");
        int totalLineNumber = 0;
        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;
        try{
            FileReader fileReader = new FileReader(path);
            BufferedReader reader =
                    new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                totalLineNumber++;
                int length = line.length();
                if (length > 1024){
                    throw  new TooLongLengthLine();
                } else if (length > maxLength){
                    maxLength = length;
                } else if(length<minLength){
                    minLength = length;
                }
            }
            System.out.println("Общее количество строк в файле: " + totalLineNumber);
            System.out.println("Самая длинная строка: " + maxLength);
            System.out.println("Самая короткая строка: " + minLength);

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
