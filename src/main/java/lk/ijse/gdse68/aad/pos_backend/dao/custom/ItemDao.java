package lk.ijse.gdse68.aad.pos_backend.dao.custom;

import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;
import lk.ijse.gdse68.aad.pos_backend.entity.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemDao {
    String saveItem(ItemDto itemDto, Connection connection);

    ItemDto getItem(String itemCode, Connection connection);

    boolean updateItem(String itemCode, ItemDto itemDto, Connection connection);

    boolean deleteItem(String itemCode, Connection connection);

    List<Item> getAll(Connection connection);
}
