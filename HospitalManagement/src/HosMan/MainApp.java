package HosMan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1. Register Patient");
            System.out.println("2. Show Patient");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Show Appointments");
            System.out.println("5. Create Medical Record");
            System.out.println("6. Show MedicalRecords");
            System.out.println();
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer.

            switch (choice) {
                case 1:
                    // Patient registration code
                    System.out.print("Enter patient name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter patient address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter patient phone: ");
                    String phone = scanner.nextLine();

                    Patient newPatient = new Patient();
                    newPatient.setPatientName(name);
                    newPatient.setPatientAddress(address);
                    newPatient.setPatientPhone(phone);

                    PatientRegistration.registerPatient(newPatient);
                    break;
                
                case 2:
                
                List<Patient> patientsList = PatientRegistration.getAllPatients();

                // Check if the patients list is not empty
                if (!patientsList.isEmpty()) {
                    System.out.println();
                    System.out.println("List of Patients:");
                    System.out.println();
                    for (Patient patient : patientsList) {
                        System.out.println("Patient ID: " + patient.getPatientId());
                        System.out.println("Name: " + patient.getPatientName());
                        System.out.println("Address: " + patient.getPatientAddress());
                        System.out.println("Phone: " + patient.getPatientPhone());
                        System.out.println();
                    }
                } else {
                    System.out.println("No patients found.");
                }
                break;




                
                case 3:
                    // Appointment scheduling code
                    System.out.print("Enter patient ID: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer.

                    System.out.print("Enter doctor name: ");
                    String doctorName = scanner.nextLine();

                    System.out.print("Enter appointment date and time (YYYY-MM-DD HH:mm): ");
                    String appointmentDateTimeStr = scanner.nextLine();
                    LocalDateTime appointmentDateTime = LocalDateTime.parse(appointmentDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


                    Appointment newAppointment = new Appointment();
                    newAppointment.setPatientId(patientId);
                    newAppointment.setDoctorName(doctorName);
                    newAppointment.setAppointmentDateTime(appointmentDateTime);

                    AppointmentScheduler.scheduleAppointment(newAppointment);
                    break;
                    
                    
                case 4:
                	 
                	AppointmentScheduler.showAllAppointments();
                	break;
 	
                	
                case 5:
                    // Medical record creation code
                    System.out.print("Enter patient ID: ");
                    int patientIdMed = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character after reading the integer.

                    System.out.print("Enter diagnosis: ");
                    String diagnosis = scanner.nextLine();

                    System.out.print("Enter treatment: ");
                    String treatment = scanner.nextLine();

                    System.out.print("Enter record date and time (YYYY-MM-DD HH:mm): ");
                    String recordDateTimeStr = scanner.nextLine();
                    LocalDateTime recordDateTime = LocalDateTime.parse(recordDateTimeStr,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    MedicalRecord newRecord = new MedicalRecord();
                    newRecord.setPatientId(patientIdMed);
                    newRecord.setDiagnosis(diagnosis);
                    newRecord.setTreatment(treatment);
                    newRecord.setRecordDateTime(recordDateTime);

                    MedicalRecordManager.createMedicalRecord(newRecord);
                    break;
                    
                case 6:
                	
                	 List<MedicalRecord> medicalRecordsList = MedicalRecordManager.getAllMedicalRecords();

                     // Check if the medical records list is not empty
                     if (!medicalRecordsList.isEmpty()) {
                         System.out.println();
                         System.out.println("List of Medical Records:");
                         System.out.println();

                         for (MedicalRecord record : medicalRecordsList) {
                             System.out.println("Record ID: " + record.getRecordId());
                             System.out.println("Patient ID: " + record.getPatientId());
                             System.out.println("Diagnosis: " + record.getDiagnosis());
                             System.out.println("Treatment: " + record.getTreatment());
                             System.out.println("Record Date and Time: " + record.getRecordDateTime());
                             System.out.println();
                         }
                     } else {
                         System.out.println("No medical records found.");
                     }
                     break;
            
                		

                     
                default:
                    System.out.println("Invalid choice.");
                	
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
