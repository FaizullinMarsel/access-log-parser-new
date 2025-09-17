public class TooLongLengthLine extends RuntimeException{
    public TooLongLengthLine() {
        super("Длина строки больше 1024 символов");
    }
}
