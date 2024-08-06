package lk.ijse.gdse68.aad.pos_backend.bo;

import lk.ijse.gdse68.aad.pos_backend.dao.CustomerDao;
import lk.ijse.gdse68.aad.pos_backend.dao.CustomerDaoImpl;
import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;

import java.sql.Connection;

public class CustomerBoImpl implements CustomerBo{
    CustomerDao customerDao=new CustomerDaoImpl();

    @Override
    public String saveCustomer(CustomerDto customerDto, Connection connection) {
        return customerDao.saveCustomer(customerDto, connection);
    }
}
