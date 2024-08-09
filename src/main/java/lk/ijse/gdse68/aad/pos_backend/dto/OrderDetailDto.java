package lk.ijse.gdse68.aad.pos_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailDto {

    private String orderId;
    private String itemCode;
    private  double unitPrice;
    private int qty;

}
