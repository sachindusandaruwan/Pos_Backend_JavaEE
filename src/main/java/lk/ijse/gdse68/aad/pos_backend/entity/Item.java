package lk.ijse.gdse68.aad.pos_backend.entity;

import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private String itemCode;
    private String itemName;
    private int itemQty;
    private double unitPrice;
    public static Item toEntity(ItemDto itemDto){
        Item item = new Item();
        item.setItemCode(itemDto.getItemCode());
        item.setItemName(itemDto.getItemName());
        item.setItemQty(itemDto.getItemQty());
        item.setUnitPrice(itemDto.getUnitPrice());
        return item;
    }
}
