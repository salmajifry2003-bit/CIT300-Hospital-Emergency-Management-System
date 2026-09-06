import java.util.Scanner;

public class Main {

    static PatientBST patientRecords = new PatientBST();
    static EmergencyQueue emergencyQueue = new EmergencyQueue();
    static TreatmentStack treatmentHistory = new TreatmentStack();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> addToEmergencyQueue();
                case 3 -> treatNextPatient();
                case 4 -> searchPatient();
                case 5 -> deletePatient();
                case 6 -> patientRecords.inorderTraversal();
                case 7 -> emergencyQueue.display();
                case 8 -> treatmentHistory.display();
                case 9 -> manageVisitHistory();
                case 0 -> System.out.println("Exiting system. Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
            System.out.println();
        } while (choice != 0);

        sc.close();
    }

    static void printMenu() {
        System.out.println("========= HOSPITAL EMERGENCY MANAGEMENT SYSTEM =========");
        System.out.println("1. Register new patient (BST)");
        System.out.println("2. Add patient to Emergency Queue");
        System.out.println("3. Treat next patient in queue (moves to Treatment Stack)");
        System.out.println("4. Search patient by ID (BST)");
        System.out.println("5. Delete patient (BST)");
        System.out.println("6. Display all patients (in-order traversal)");
        System.out.println("7. Display emergency queue");
        System.out.println("8. Display treatment history (stack)");
        System.out.println("9. Manage a patient's visit history (linked list)");
        System.out.println("0. Exit");
    }

    static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("Enter Medical Condition: ");
        String condition = sc.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientRecords.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    static void addToEmergencyQueue() {
        int id = readInt("Enter Patient ID to add to queue: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("Patient not found. Please register the patient first.");
            return;
        }
        emergencyQueue.enqueue(patient);
    }

    static void treatNextPatient() {
        Patient patient = emergencyQueue.dequeue();
        if (patient == null) return;

        System.out.println("Now treating: " + patient);
        System.out.print("Enter treatment details: ");
        String details = sc.nextLine();
        System.out.print("Enter completion date (e.g. 2026-09-06): ");
        String date = sc.nextLine();

        TreatmentRecord record = new TreatmentRecord(patient.getPatientId(), patient.getName(), details, date);
        treatmentHistory.push(record);

        Visit visit = new Visit(
                (int) (Math.random() * 9000) + 1000,
                date, "Dr. On Duty", patient.getMedicalCondition(), details
        );
        patient.getVisitHistory().addVisit(visit);
        System.out.println("Treatment complete and recorded in patient's visit history.");
    }

    static void searchPatient() {
        int id = readInt("Enter Patient ID to search: ");
        Patient patient = patientRecords.search(id);
        System.out.println(patient == null ? "Patient not found." : "Found: " + patient);
    }

    static void deletePatient() {
        int id = readInt("Enter Patient ID to delete: ");
        patientRecords.delete(id);
        System.out.println("Delete operation completed (if ID existed).");
    }

    static void manageVisitHistory() {
        int id = readInt("Enter Patient ID: ");
        Patient patient = patientRecords.search(id);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println("a) Add visit  b) Remove visit  c) Search visit  d) Display all");
        System.out.print("Choose option: ");
        String option = sc.nextLine();

        switch (option) {
            case "a" -> {
                int visitId = readInt("Visit ID: ");
                System.out.print("Visit Date: ");
                String date = sc.nextLine();
                System.out.print("Doctor Name: ");
                String doctor = sc.nextLine();
                System.out.print("Diagnosis: ");
                String diagnosis = sc.nextLine();
                System.out.print("Treatment: ");
                String treatment = sc.nextLine();
                patient.getVisitHistory().addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
                System.out.println("Visit added.");
            }
            case "b" -> {
                int visitId = readInt("Visit ID to remove: ");
                boolean removed = patient.getVisitHistory().removeVisit(visitId);
                System.out.println(removed ? "Visit removed." : "Visit ID not found.");
            }
            case "c" -> {
                int visitId = readInt("Visit ID to search: ");
                Visit v = patient.getVisitHistory().searchVisit(visitId);
                System.out.println(v == null ? "Visit not found." : "Found: " + v);
            }
            case "d" -> patient.getVisitHistory().display();
            default -> System.out.println("Invalid option.");
        }
    }

    static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }
}