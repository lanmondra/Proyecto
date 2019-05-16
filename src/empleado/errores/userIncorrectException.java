package empleado.errores;

public class userIncorrectException extends Exception {

    private int codigoError;

    static void userIncorrectException() {
    }

    public userIncorrectException(String message) {

        super(message);

    }

    public userIncorrectException(String mensaje, Throwable causa) {
        super(mensaje, causa);

    }

    public userIncorrectException(String mensaje, Throwable causa, codigoError codigoError) {
        super(mensaje, causa);
        this.codigoError = codigoError.getCodigoError();

    }

    public int getCodigoError() {
        return codigoError;
    }

}
