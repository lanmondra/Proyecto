package empleado.errores;

public enum codigoError {
    LOGIN_INCORRECTO(111),
    CONTRASEÑA_INCORRECTA(222);

    private int codigo;

    private codigoError(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoError() {

        return codigo;

    }

}
