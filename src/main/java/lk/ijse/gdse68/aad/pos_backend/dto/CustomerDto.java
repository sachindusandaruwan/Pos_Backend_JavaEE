package lk.ijse.gdse68.aad.pos_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CustomerDto {
    private String id;
    private String name;
    private String address;
    private double salary;
}
