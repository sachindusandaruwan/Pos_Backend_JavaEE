package lk.ijse.gdse68.aad.pos_backend.bo.custom;

import lk.ijse.gdse68.aad.pos_backend.bo.SuperBO;
import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;

import java.sql.Connection;
import java.util.List;

public interface ItemBo extends SuperBO {

    String saveItem(ItemDto itemDto, Connection connection);

    ItemDto getItem(String itemCode, Connection connection);

    boolean updateItem(String itemCode, ItemDto itemDto, Connection connection);

    boolean deleteItem(String itemCode, Connection connection);

    List<ItemDto> getAllItems(Connection connection);
}
