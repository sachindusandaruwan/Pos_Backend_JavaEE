package lk.ijse.gdse68.aad.pos_backend.dao;

import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;

import java.sql.Connection;

public interface CustomerDao {
    String saveCustomer(CustomerDto customerDto, Connection connection);
}
