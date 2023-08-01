package HosMan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordManager {
    public static void createMedicalRecord(MedicalRecord record) {
        String sql = "INSERT INTO medical_records (patient_id, diagnosis, treatment, record_date_time) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, record.getPatientId());
            preparedStatement.setString(2, record.getDiagnosis());
            preparedStatement.setString(3, record.getTreatment());
            preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(record.getRecordDateTime()));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    record.setRecordId(generatedKeys.getInt(1));
                    System.out.println("Medical record created successfully! Record ID: " + record.getRecordId());
                }
            } else {
                System.out.println("Failed to create the medical record.");
            }
        } catch (SQLException e) {
            System.err.println("Error while creating the medical record: " + e.getMessage());
        }
    }
    
   

        public static void updateMedicalRecord(MedicalRecord record) {
            String sql = "UPDATE medical_records SET diagnosis = ?, treatment = ?, record_date_time = ? WHERE record_id = ?";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, record.getDiagnosis());
                preparedStatement.setString(2, record.getTreatment());
                preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(record.getRecordDateTime()));
                preparedStatement.setInt(4, record.getRecordId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Medical record updated successfully!");
                } else {
                    System.out.println("Failed to update the medical record.");
                }
            } catch (SQLException e) {
                System.err.println("Error while updating the medical record: " + e.getMessage());
            }
        }

        public static void deleteMedicalRecord(int recordId) {
            String sql = "DELETE FROM medical_records WHERE record_id = ?";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, recordId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Medical record deleted successfully!");
                } else {
                    System.out.println("Failed to delete the medical record. Record ID not found.");
                }
            } catch (SQLException e) {
                System.err.println("Error while deleting the medical record: " + e.getMessage());
            }
        }

        public static List<MedicalRecord> getAllMedicalRecords() {
            List<MedicalRecord> medicalRecords = new ArrayList<>();
            String sql = "SELECT * FROM medical_records";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    MedicalRecord record = new MedicalRecord();
                    record.setRecordId(resultSet.getInt("record_id"));
                    record.setPatientId(resultSet.getInt("patient_id"));
                    record.setDiagnosis(resultSet.getString("diagnosis"));
                    record.setTreatment(resultSet.getString("treatment"));
                    record.setRecordDateTime(resultSet.getTimestamp("record_date_time").toLocalDateTime());

                    medicalRecords.add(record);
                }
            } catch (SQLException e) {
                System.err.println("Error while fetching medical records: " + e.getMessage());
            }

            return medicalRecords;
        }
    }


