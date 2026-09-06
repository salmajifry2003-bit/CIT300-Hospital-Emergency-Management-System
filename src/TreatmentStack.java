public class TreatmentStack {

    private static class SNode {
        TreatmentRecord record;
        SNode next;

        SNode(TreatmentRecord record) {
            this.record = record;
        }
    }

    private SNode top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(TreatmentRecord record) {
        SNode newNode = new SNode(record);
        newNode.next = top;
        top = newNode;
        System.out.println("Treatment record for Patient " + record.patientId + " pushed to history.");
    }

    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to remove.");
            return null;
        }
        TreatmentRecord record = top.record;
        top = top.next;
        return record;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("No treatment records found.");
            return;
        }
        SNode current = top;
        while (current != null) {
            System.out.println("   " + current.record);
            current = current.next;
        }
    }
}