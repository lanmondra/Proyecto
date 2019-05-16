package util;

public enum Color {

    EXITO("\u001B[32m"),
    ERROR("\u001B[31m"),
    DEFAULT("\u001B[0m"),
    GREEN("\033[32;1m"),
    BULE("\033[36m");

    String color;

    private Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }

}
