package lk.ijse.gdse68.aad.pos_backend.bo.custom.impl;

import lk.ijse.gdse68.aad.pos_backend.bo.custom.CustomerBo;
import lk.ijse.gdse68.aad.pos_backend.dao.custom.CustomerDao;
import lk.ijse.gdse68.aad.pos_backend.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;
import lk.ijse.gdse68.aad.pos_backend.entity.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static lk.ijse.gdse68.aad.pos_backend.dao.custom.impl.CustomerDaoImpl.GET_ALL_CUSTOMERS;

public class CustomerBoImpl implements CustomerBo {
    CustomerDao customerDao=new CustomerDaoImpl();

    @Override
    public boolean saveCustomer(CustomerDto customerDto, Connection connection) {
        return customerDao.saveCustomer(Customer.toEntity(customerDto), connection);
    }

    @Override
    public CustomerDto getCustomer(String customerId, Connection connection) {
        return customerDao.getCustomer(customerId,connection);
    }

    @Override
    public boolean updateCustomer(String customerId, CustomerDto customerDto, Connection connection) {
        return customerDao.updateCustomer(customerId,customerDto,connection);
    }

    @Override
    public boolean deleteCustomer(String customerId, Connection connection) {
        return customerDao.deleteCustomer(customerId,connection);
    }

    @Override
    public List<CustomerDto> getAllCustomers(Connection connection) {
        List<Customer> customers = customerDao.getAll(connection);
        return customers.stream()
                .map(customer -> new CustomerDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getSalary()
                ))
                .collect(Collectors.toList());
    }
}



