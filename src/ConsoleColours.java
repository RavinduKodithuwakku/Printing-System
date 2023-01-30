public enum ConsoleColours {

    RESET("\033[0m"),
    RED("\033[0;31m"),
    GREEN("\033[0;32m"),
    PURPLE("\033[0;35m"),
    YELLOW("\033[0;33m");


    String consoleColor;

    ConsoleColours(String consoleColor) {
        this.consoleColor = consoleColor;
    }

    @Override
    public String toString() {
        return consoleColor;
    }
}
