package lk.ijse.gdse68.aad.pos_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetails {
    private String orderId;
    private String itemCode;
    private  double unitPrice;
    private int qty;
}
