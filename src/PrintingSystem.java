public class PrintingSystem {
    public static void main(String[] args) throws InterruptedException {
        LaserPrinter laserPrinter = new LaserPrinter(150, 200, 100);
        System.out.println(laserPrinter);

        ThreadGroup studentThreadGroup = new ThreadGroup("Student Thread Group");
        ThreadGroup technicianThreadGroup = new ThreadGroup("Technician Thread Group");

        Thread[] students = new Thread[4];

        for (int i = 0; i < 4; i++) {
            Runnable runnable01 = new Student("Student " + (i + 1), laserPrinter);
            students[i] = new Thread(studentThreadGroup, runnable01, "Student " + (i + 1));
        }

        Runnable runnable02 = new PaperTechnician("Paper Technician", laserPrinter);
        Runnable runnable03 = new TonerTechnician("Toner Technician", laserPrinter);
        Thread paperTechnician = new Thread(technicianThreadGroup, runnable02, "Paper Technician");
        Thread tonerTechnician = new Thread(technicianThreadGroup, runnable03, "Toner Technician");

        for (Thread thread : students) {
            thread.start();
        }

        paperTechnician.start();
        tonerTechnician.start();

        for (Thread thread : students) {
            thread.join();
        }

        System.out.println(ConsoleColours.GREEN + "Finished Printing All Documents" + ConsoleColours.RESET);

        System.out.println(laserPrinter);
        System.out.println(runnable02);
        System.out.println(runnable03);

        if (paperTechnician.isAlive() || tonerTechnician.isAlive()) {
            System.out.println("Technicians are still working. Manually stopping work...");
            System.exit(0);
        }
    }
}