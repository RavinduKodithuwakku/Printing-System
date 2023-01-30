public class Student implements Runnable {

    private final String name;
    private final Printer printer;

    public Student(String name, Printer printer) {
        super();
        this.name = name;
        this.printer = printer;
    }

    @Override
    public void run() {
        Document []documents = new Document[5];
        for (int i = 1; i <= 5; i++) {
            int minPages = 1;
            int maxPages = 10;

            int numberOfPages = (int) (Math.random() * (maxPages - minPages) + minPages);
            documents[i - 1] = new Document(this.name, "CW_" + i, numberOfPages);
        }
        int noOfPages = 0;

        for (Document doc: documents) {
            printer.printDocument(doc);
            noOfPages += doc.getNumberOfPages();

            int minSleepingTime = 1;
            int maxSleepTime = 5;
            int num = (int) (Math.random() * (maxSleepTime - minSleepingTime) + minSleepingTime);

            try {
                Thread.sleep(num*1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(ConsoleColours.GREEN + "[" + this.name + "]" + " Finished Printing All 5 Documents : "
                + noOfPages + " pages" + ConsoleColours.RESET);

    }

}
