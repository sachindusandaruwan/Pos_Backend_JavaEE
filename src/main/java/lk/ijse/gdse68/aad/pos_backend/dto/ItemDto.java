package lk.ijse.gdse68.aad.pos_backend.dto;

import lk.ijse.gdse68.aad.pos_backend.entity.Item;
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

    public static ItemDto toDto(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setItemCode(item.getItemCode());
        itemDto.setItemName(item.getItemName());
        itemDto.setItemQty(item.getItemQty());
        itemDto.setUnitPrice(item.getUnitPrice());
        return itemDto;
    }
}
