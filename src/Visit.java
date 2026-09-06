public class Visit {
    int visitId;
    String visitDate;
    String doctorName;
    String diagnosis;
    String treatment;
    Visit next;

    public Visit(int visitId, String visitDate, String doctorName, String diagnosis, String treatment) {
        this.visitId = visitId;
        this.visitDate = visitDate;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Visit ID: " + visitId +
                " | Date: " + visitDate +
                " | Doctor: " + doctorName +
                " | Diagnosis: " + diagnosis +
                " | Treatment: " + treatment;
    }
}