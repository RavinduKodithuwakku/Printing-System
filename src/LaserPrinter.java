public class LaserPrinter implements ServicePrinter{
    private final int ID;
    private int currentPaperLevel;
    private int currentTonerLevel;
    private int documentPrinted = 0;
    private int paperRefilled = 0;
    private int tonerReplaced = 0;


    public int getPaperRefilledCount() {
        return paperRefilled;
    }


    public int getTonerRefilledCount() {
        return tonerReplaced;
    }


    public LaserPrinter(int ID, int currentPaperLevel, int currentTonerLevel) {
        super();
        this.ID = ID;
        this.currentPaperLevel = currentPaperLevel;
        this.currentTonerLevel = currentTonerLevel;
    }

    @Override
    public synchronized void printDocument(Document document) {
        String studentName = document.getUserID();
        String docName = document.getDocumentName();
        int numberOfPages = document.getNumberOfPages();
        System.out.println("[" + studentName + "]" + "  preparing to print " + docName + " (" + numberOfPages + " pages)");
        while (this.currentPaperLevel < numberOfPages || this.currentTonerLevel < numberOfPages) {
            if (this.currentTonerLevel < numberOfPages && this.currentTonerLevel > Minimum_Toner_Level){
                System.out.println(ConsoleColours.GREEN + " Not Enough Toner " + ConsoleColours.RESET);
                System.out.println("Did not call the replaceToner method \nManually exiting application ...");
                System.exit(0);
            }
            if (this.currentTonerLevel < numberOfPages && tonerReplaced == 3){
                System.out.println(ConsoleColours.GREEN + " Not Enough Toner " + ConsoleColours.RESET);
                System.out.println("replaceTonerCartridge method already called three times \nManually exiting application ...");
                System.exit(0);
            }
            if(this.currentPaperLevel < numberOfPages && paperRefilled == 3){
                System.out.println(ConsoleColours.GREEN + " Not Enough Toner " + ConsoleColours.RESET);
                System.out.println("refillPaper method already called three times \nManually exiting application ...");
                System.exit(0);
            }
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.currentPaperLevel -= numberOfPages;
        this.currentTonerLevel -= numberOfPages;
        this.documentPrinted++;
        System.out.println("[" + studentName + "]" + "  document " + docName + " printed - " + numberOfPages + " pages");
        System.out.println(this);
        notifyAll();
    }


    @Override
    //    replacing the toner cartridge
    public synchronized void replaceTonerCartridge() {
        while (this.currentTonerLevel >= Minimum_Toner_Level){
            try {
                wait(5000);
            }catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }this.currentTonerLevel = Full_Toner_Level;
        tonerReplaced++;
        System.out.println(ConsoleColours.YELLOW + "LaserPrinter - Toner refilled" + ConsoleColours.RESET);
        System.out.println(this);
        notifyAll();
    }

    @Override
    // adding more papers
    public synchronized void refillPaper() {
        while (this.currentPaperLevel > ((Full_Paper_Tray - SheetsPerPack))) {
            try {
                wait(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public String toString() {
        return ConsoleColours.PURPLE + "[LaserPrinter(" + ID + ")]  Paper Level: " +
                currentPaperLevel + ", Toner Level:" +
                currentTonerLevel + ", Documents Printed:" +
                documentPrinted + ConsoleColours.RESET;

    }
}
