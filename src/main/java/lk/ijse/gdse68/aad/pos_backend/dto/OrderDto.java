package lk.ijse.gdse68.aad.pos_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private String orderID;
    private String customerId;
//        private Date date;
//   private double subTotal;
    private double total;
}
