package lk.ijse.gdse68.aad.pos_backend.bo;

import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;

import java.sql.Connection;

public interface CustomerBo {
    String saveCustomer(CustomerDto customerDto, Connection connection);
}
