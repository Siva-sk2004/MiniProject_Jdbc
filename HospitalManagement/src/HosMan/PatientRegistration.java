package HosMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientRegistration {
    public static void registerPatient(Patient patient) {
        String sql = "INSERT INTO patients (patient_name, patient_address, patient_phone) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, patient.getPatientName());
            preparedStatement.setString(2, patient.getPatientAddress());
            preparedStatement.setString(3, patient.getPatientPhone());

            preparedStatement.executeUpdate();
            System.out.println("Patient registered successfully!");
        } catch (SQLException e) {
            System.err.println("Error while registering the patient: " + e.getMessage());
        }
    }
    
        public static void updatePatient(Patient patient) {
            String sql = "UPDATE patients SET patient_name = ?, patient_address = ?, patient_phone = ? WHERE patient_id = ?";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, patient.getPatientName());
                preparedStatement.setString(2, patient.getPatientAddress());
                preparedStatement.setString(3, patient.getPatientPhone());
                preparedStatement.setInt(4, patient.getPatientId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Patient information updated successfully!");
                } else {
                    System.out.println("Failed to update patient information.");
                }
            } catch (SQLException e) {
                System.err.println("Error while updating patient information: " + e.getMessage());
            }
        }

    public static void deletePatient(int patientId) {
        String sql = "DELETE FROM patients WHERE patient_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, patientId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("Failed to delete patient. Patient ID not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error while deleting patient: " + e.getMessage());
        }
    }
    

        public static List<Patient> getAllPatients() {
            List<Patient> patients = new ArrayList<>();
            String sql = "SELECT * FROM patients";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    Patient patient = new Patient();
                    patient.setPatientId(resultSet.getInt("patient_id"));
                    patient.setPatientName(resultSet.getString("patient_name"));
                    patient.setPatientAddress(resultSet.getString("patient_address"));
                    patient.setPatientPhone(resultSet.getString("patient_phone"));

                    patients.add(patient);
                }
            } catch (SQLException e) {
                System.err.println("Error while fetching patients: " + e.getMessage());
            }

            return patients;
        }
    }


