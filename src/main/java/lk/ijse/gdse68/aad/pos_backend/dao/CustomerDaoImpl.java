package lk.ijse.gdse68.aad.pos_backend.dao;

import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

    public static String SAVE_CUSTOMER = "Insert into customer values (?,?,?,?)";

    @Override
    public String saveCustomer(CustomerDto customerDto, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SAVE_CUSTOMER);
            preparedStatement.setString(1, customerDto.getId());
            preparedStatement.setString(2, customerDto.getName());
            preparedStatement.setString(3, customerDto.getAddress());
            preparedStatement.setString(4, String.valueOf(customerDto.getSalary()));

            if (preparedStatement.executeUpdate() != 0) {
                return "Customer Saved Successfully!";
            } else {
                return "Something went wrong!";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }
}


