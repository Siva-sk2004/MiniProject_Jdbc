package HosMan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentScheduler {
    public static void scheduleAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_id, doctor_name, appointment_date_time) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, appointment.getPatientId());
            preparedStatement.setString(2, appointment.getDoctorName());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentDateTime()));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    appointment.setAppointmentId(generatedKeys.getInt(1));
                    System.out.println("Appointment scheduled successfully! Appointment ID: " + appointment.getAppointmentId());
                }
            } else {
                System.out.println("Failed to schedule the appointment.");
            }
        } catch (SQLException e) {
            System.err.println("Error while scheduling the appointment: " + e.getMessage());
        }
    }

   

        public static void updateAppointment(Appointment appointment) {
            String sql = "UPDATE appointments SET doctor_name = ?, appointment_date_time = ? WHERE appointment_id = ?";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, appointment.getDoctorName());
                preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(appointment.getAppointmentDateTime()));
                preparedStatement.setInt(3, appointment.getAppointmentId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Appointment updated successfully!");
                } else {
                    System.out.println("Failed to update the appointment.");
                }
            } catch (SQLException e) {
                System.err.println("Error while updating the appointment: " + e.getMessage());
            }
        }

        public static void deleteAppointment(int appointmentId) {
            String sql = "DELETE FROM appointments WHERE appointment_id = ?";

            try (Connection connection = DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setInt(1, appointmentId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Appointment deleted successfully!");
                } else {
                    System.out.println("Failed to delete the appointment. Appointment ID not found.");
                }
            } catch (SQLException e) {
                System.err.println("Error while deleting the appointment: " + e.getMessage());
            }
        }


            public static void showAllAppointments() {
                String sql = "SELECT * FROM appointments";

                try (Connection connection = DBConnection.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    ResultSet resultSet = preparedStatement.executeQuery();

                    List<Appointment> allAppointments = new ArrayList<>();
                    while (resultSet.next()) {
                        int appointmentId = resultSet.getInt("appointment_id");
                        int patientId = resultSet.getInt("patient_id");
                        String doctorName = resultSet.getString("doctor_name");
                        java.sql.Timestamp appointmentTimestamp = resultSet.getTimestamp("appointment_date_time");

                        Appointment appointment = new Appointment();
                        appointment.setAppointmentId(appointmentId);
                        appointment.setPatientId(patientId);
                        appointment.setDoctorName(doctorName);
                        appointment.setAppointmentDateTime(appointmentTimestamp.toLocalDateTime());
                        allAppointments.add(appointment);
                    }

                    if (allAppointments.isEmpty()) {
                        System.out.println("No appointments found.");
                    } else {
                    	System.out.println();
                        System.out.println("Appointments:");
                        System.out.println();
                        for (Appointment appointment : allAppointments) {
                            System.out.println("Appointment ID: " + appointment.getAppointmentId());
                            System.out.println("Patient ID: " + appointment.getPatientId());
                            System.out.println("Doctor Name: " + appointment.getDoctorName());
                            System.out.println("Appointment Date and Time: " + appointment.getAppointmentDateTime());
                            System.out.println();
                        }
                    }
                } catch (SQLException e) {
                    System.err.println("Error while retrieving appointments: " + e.getMessage());
                }
            }
        }

           



