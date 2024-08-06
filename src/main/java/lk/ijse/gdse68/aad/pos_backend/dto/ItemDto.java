package lk.ijse.gdse68.aad.pos_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ItemDto {

    private String itemCode;
    private String itemName;
    private int itemQty;
    private double unitPrice;
}
