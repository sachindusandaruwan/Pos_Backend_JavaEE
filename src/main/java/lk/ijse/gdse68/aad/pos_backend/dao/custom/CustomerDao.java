package lk.ijse.gdse68.aad.pos_backend.dao.custom;

import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;
import lk.ijse.gdse68.aad.pos_backend.entity.Customer;

import java.sql.Connection;
import java.util.List;

public interface CustomerDao {
//    String saveCustomer(CustomerDto customerDto, Connection connection);

    boolean saveCustomer(Customer customer, Connection connection);

    CustomerDto getCustomer(String customerId, Connection connection);

    boolean updateCustomer(String customerId, CustomerDto customerDto, Connection connection);

    boolean deleteCustomer(String customerId, Connection connection);

    List<Customer> getAll(Connection connection);
}
