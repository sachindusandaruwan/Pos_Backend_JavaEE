package lk.ijse.gdse68.aad.pos_backend.bo.custom.impl;

import lk.ijse.gdse68.aad.pos_backend.bo.custom.ItemBo;
import lk.ijse.gdse68.aad.pos_backend.dao.custom.ItemDao;
import lk.ijse.gdse68.aad.pos_backend.dao.custom.impl.ItemDaoImpl;
import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;
import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;
import lk.ijse.gdse68.aad.pos_backend.entity.Customer;
import lk.ijse.gdse68.aad.pos_backend.entity.Item;

import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao=new ItemDaoImpl();

    @Override
    public String saveItem(ItemDto itemDto, Connection connection) {

        return itemDao.saveItem(itemDto,connection);
    }

    @Override
    public ItemDto getItem(String itemCode, Connection connection) {
        return itemDao.getItem(itemCode,connection);
    }

    @Override
    public boolean updateItem(String itemCode, ItemDto itemDto, Connection connection) {
//        System.out.println("mn update eke itemImpl ekta awa");
        return itemDao.updateItem(itemCode,itemDto,connection);
    }

    @Override
    public boolean deleteItem(String itemCode, Connection connection) {
        return itemDao.deleteItem(itemCode,connection);
    }

    @Override
    public List<ItemDto> getAllItems(Connection connection) {
        List<Item> items = itemDao.getAll(connection);
        return items.stream()
                .map(item -> new ItemDto(
                        item.getItemCode(),
                        item.getItemName(),
                        item.getItemQty(),
                        item.getUnitPrice()
                ))
                .collect(Collectors.toList());
    }
}
