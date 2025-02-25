public class ConfirmationSlip {
    private String customerName;
    private String licensePlate;
    private String action; // "Rented" or "Returned"

    public ConfirmationSlip(String customerName, String licensePlate, String action) {
        this.customerName = customerName;
        this.licensePlate = licensePlate;
        this.action = action;
    }

    public void printSlip() {
        System.out.println("----- Confirmation Slip -----");
        System.out.println("Customer Name: " + customerName);
        System.out.println("License Plate: " + licensePlate);
        System.out.println("Action: " + action);
        System.out.println("-----------------------------");
    }
}