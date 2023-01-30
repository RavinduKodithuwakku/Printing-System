public class Technician {
    static void sleep(String name) {

        int MIN_SLEEPING_TIME = 1;
        int MAX_SLEEPING_TIME = 5;

        int num = (int) (Math.random() * (MAX_SLEEPING_TIME - MIN_SLEEPING_TIME) + MIN_SLEEPING_TIME);
        try {
            Thread.sleep(num * 1000L);
            System.out.println(ConsoleColours.RED + "[" + name + "]" + "  sleeping for " + num + " seconds" + ConsoleColours.RESET);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
