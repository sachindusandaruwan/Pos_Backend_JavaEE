package lk.ijse.gdse68.aad.pos_backend.bo;

import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;

import java.sql.Connection;

public interface ItemBo {

    String saveItem(ItemDto itemDto, Connection connection);

    ItemDto getItem(String itemCode, Connection connection);

    boolean updateItem(String itemCode, ItemDto itemDto, Connection connection);

    boolean deleteItem(String itemCode, Connection connection);
}
