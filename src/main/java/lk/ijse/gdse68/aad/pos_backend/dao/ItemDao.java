package lk.ijse.gdse68.aad.pos_backend.dao;

import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;

import java.sql.Connection;

public interface ItemDao {
    String saveItem(ItemDto itemDto, Connection connection);
}