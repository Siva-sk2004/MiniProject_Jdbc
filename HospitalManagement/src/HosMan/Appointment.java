package HosMan;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private String doctorName;
    private LocalDateTime appointmentDateTime;
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}
	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}

    
    

    // Constructor, getters, and setters.
    // Implement the constructor, getters, and setters for the attributes.
}
