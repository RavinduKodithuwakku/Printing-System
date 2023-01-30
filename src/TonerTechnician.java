public class TonerTechnician extends Technician implements Runnable{

    private String name;
    private LaserPrinter printer;

    public TonerTechnician(String name, LaserPrinter printer) {
        super();
        this.name = name;
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i=0; i<3; i++) {
            printer.replaceTonerCartridge();
            Technician.sleep(this.name);
        }
        System.out.println("[" + this.name + "]" + " Finished cartridges replaced: " + printer.getTonerRefilledCount());
    }

    @Override
    public String toString() {
        return "[Toner Technician] Cartridges replaced = " + printer.getTonerRefilledCount();
    }

}
