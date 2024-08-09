package lk.ijse.gdse68.aad.pos_backend.entity;

import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Customer {
    private String id;
    private String name;
    private String address;
    private double salary;

    public static Customer toEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setAddress(customerDto.getAddress());
        customer.setSalary(customerDto.getSalary());
        return customer;
    }
}
