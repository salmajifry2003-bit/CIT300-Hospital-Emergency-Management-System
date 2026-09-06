public class EmergencyQueue {

    private static class QNode {
        Patient patient;
        QNode next;

        QNode(Patient patient) {
            this.patient = patient;
        }
    }

    private QNode front, rear;

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Patient patient) {
        QNode newNode = new QNode(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Patient " + patient.getPatientId() + " added to emergency queue.");
    }

    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to treat.");
            return null;
        }
        Patient patient = front.patient;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return patient;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting.");
            return;
        }
        QNode current = front;
        int position = 1;
        while (current != null) {
            System.out.println("   " + position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }
}