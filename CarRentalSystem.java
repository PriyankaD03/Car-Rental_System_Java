import java.util.Scanner;

public class CarRentalSystem {
    private Car[] cars;
    private Customer[] customers;
    private int carCount;
    private int customerCount;

    public CarRentalSystem(int maxCars, int maxCustomers) {
        cars = new Car[maxCars];
        customers = new Customer[maxCustomers];
        carCount = 0;
        customerCount = 0;
        addCar("ABC123", "Toyota Camry");
        addCar("XYZ456", "Honda Accord");
        addCar("LMN789", "Ford Mustang");
        addCar("QRS012", "Chevrolet Malibu");
        addCar("TUV345", "Nissan Altima");
    }

    public void addCar(String licensePlate, String model) {
        System.out.println("Trying to add car: " + model + " with license plate " + licensePlate); // Debug line
        if (carCount < cars.length) {
            cars[carCount++] = new Car(licensePlate, model);
            System.out.println("Car added successfully!");
        } else {
            System.out.println("Car limit reached. Cannot add more cars.");
        }
    }
    
    public void addCustomer(String name, String licenseNumber) {
        if (customerCount < customers.length) {
            customers[customerCount++] = new Customer(name, licenseNumber);
            System.out.println("Customer added: " + name);
        } else {
            System.out.println("Customer limit reached. Cannot add more customers.");
        }
    }

    public void rentCar(String licensePlate, String customerName) {
        Car car = findCar(licensePlate);
        if (car != null && !car.isRented()) {
            car.rent();
            ConfirmationSlip slip = new ConfirmationSlip(customerName, licensePlate, "Rented");
            slip.printSlip();
        } else {
            System.out.println("Car is either not available or already rented. Please check the license plate.");
        }
    }

    public void returnCar(String licensePlate, String customerName) {
        Car car = findCar(licensePlate);
        if (car != null && car.isRented()) {
            car.returnCar();
            ConfirmationSlip slip = new ConfirmationSlip(customerName, licensePlate, "Returned");
            slip.printSlip();
        } else {
            System.out.println("Car is either not found or not rented. Please check the license plate.");
        }
    }

    private Car findCar(String licensePlate) {
        for (Car car : cars) {
            if (car != null && car.getLicensePlate().equals(licensePlate)) {
                return car;
            }
        }
        return null;
    }

    public void listAvailableCars() {
        System.out.println("Available Cars:");
        boolean availableCars = false;
        for (Car car : cars) {
            if (car != null && !car.isRented()) {
                System.out.println("Model: " + car.getModel() + ", License Plate: " + car.getLicensePlate());
                availableCars = true;
            }
        }
        if (!availableCars) {
            System.out.println("No cars available.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarRentalSystem rentalSystem = new CarRentalSystem(10, 10);
    
        while (true) {
            System.out.println("------- MENU --------");
            System.out.println("1. Add Car");
            System.out.println("2. Add Customer");
            System.out.println("3. Rent Car");
            System.out.println("4. Return Car");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
    
            if (!scanner.hasNextInt()) {  // Check if input is an integer
                System.out.println("Invalid input! Please enter a number between 1 and 5.");
                scanner.next();  // Consume invalid input
                continue;  // Restart loop
            }
    
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
    
            switch (choice) {
                case 1:
                    System.out.print("Enter license plate: ");
                    String licensePlate = scanner.nextLine();
                    System.out.print("Enter model: ");
                    String model = scanner.nextLine();
                    rentalSystem.addCar(licensePlate, model);
                    break;
                case 2:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter license number: ");
                    String licenseNumber = scanner.nextLine();
                    rentalSystem.addCustomer(name, licenseNumber);
                    break;
                case 3:
                    System.out.print("Enter license plate of the car to rent: ");
                    String rentLicensePlate = scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String customerNameForRent = scanner.nextLine();
                    rentalSystem.rentCar(rentLicensePlate, customerNameForRent);
                    break;
                case 4:
                    System.out.print("Enter license plate of the car to return: ");
                    String returnLicensePlate = scanner.nextLine();
                    System.out.print("Enter customer name: ");
                    String customerNameForReturn = scanner.nextLine();
                    rentalSystem.returnCar(returnLicensePlate, customerNameForReturn);
                    break;
                case 5:
                    System.out.println("Exiting the system. Thank you!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
}
