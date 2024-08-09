package lk.ijse.gdse68.aad.pos_backend.dao.custom.impl;

import lk.ijse.gdse68.aad.pos_backend.dao.custom.CustomerDao;
import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;
import lk.ijse.gdse68.aad.pos_backend.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    public static String SAVE_CUSTOMER = "Insert into customer values (?,?,?,?)";
    public static String GET_CUSTOMER_BY_ID = "select * from customer where id=?";
    public static String UPDATE_CUSTOMER = "UPDATE customer SET name=?, address=?, salary=? WHERE id=?";
    public static String DELETE_CUSTOMER = "DELETE FROM customer WHERE id=?";
    public static String GET_ALL_CUSTOMERS ="SELECT * FROM customer";

//    @Override
//    public String saveCustomer(CustomerDto customerDto, Connection connection) {
//        PreparedStatement preparedStatement =null;
//        try {
//            preparedStatement = connection.prepareStatement(SAVE_CUSTOMER);
//            preparedStatement.setString(1, customerDto.getId());
//            preparedStatement.setString(2, customerDto.getName());
//            preparedStatement.setString(3, customerDto.getAddress());
//            preparedStatement.setString(4, String.valueOf(customerDto.getSalary()));
//
//            if (preparedStatement.executeUpdate() != 0) {
//                return "Customer Saved Successfully!";
//            } else {
//                return "Something went wrong!";
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//
//        }
//
//    }


 ///////////////////////////////////////////////////////////////////////////////////
 @Override
 public boolean saveCustomer(Customer customer, Connection connection) {
     PreparedStatement preparedStatement = null;
     try {
         preparedStatement = connection.prepareStatement(SAVE_CUSTOMER);
         preparedStatement.setString(1, customer.getId());
         preparedStatement.setString(2, customer.getName());
         preparedStatement.setString(3, customer.getAddress());
         preparedStatement.setString(4, String.valueOf(customer.getSalary()));

         if (preparedStatement.executeUpdate() != 0){
             return true;
         }else {
             return false;
         }
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

 }


 //////////////////////////////////////////////////////////////////////////////

    @Override
    public CustomerDto getCustomer(String customerId, Connection connection) {
        var customerDto = new CustomerDto();
        try {
            var preparedStatement = connection.prepareStatement(GET_CUSTOMER_BY_ID);
            preparedStatement.setString(1, customerId);

            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customerDto.setId(resultSet.getString("id"));
                customerDto.setName(resultSet.getString("name"));
                customerDto.setAddress(resultSet.getString("address"));
                customerDto.setSalary(resultSet.getDouble("salary"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerDto;
    }

    @Override
    public boolean updateCustomer(String customerId, CustomerDto customerDto, Connection connection) {
        try {
            var preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
            preparedStatement.setString(1, customerDto.getName());
            preparedStatement.setString(2, customerDto.getAddress());
            preparedStatement.setDouble(3, customerDto.getSalary());

            preparedStatement.setString(4, customerId);
            return preparedStatement.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(String customerId, Connection connection) {
        try {
            var pst=connection.prepareStatement(DELETE_CUSTOMER);
            pst.setString(1, customerId);
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll(Connection connection) {
        List<Customer> customers = new ArrayList<>();
        try{
            var ps = connection.prepareStatement(GET_ALL_CUSTOMERS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                customers.add(new Customer(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getDouble("salary")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    }



