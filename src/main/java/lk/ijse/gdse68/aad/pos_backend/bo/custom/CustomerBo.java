package lk.ijse.gdse68.aad.pos_backend.bo.custom;

import lk.ijse.gdse68.aad.pos_backend.bo.SuperBO;
import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBo extends SuperBO {
//    String saveCustomer(CustomerDto customerDto, Connection connection);

    boolean saveCustomer(CustomerDto customerDto, Connection connection);

    CustomerDto getCustomer(String customerId, Connection connection);

    boolean updateCustomer(String customerId, CustomerDto customerDto, Connection connection);

    boolean deleteCustomer(String customerId, Connection connection);

    List<CustomerDto> getAllCustomers(Connection connection);
}
