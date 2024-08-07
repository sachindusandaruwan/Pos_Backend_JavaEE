package lk.ijse.gdse68.aad.pos_backend.bo;

import lk.ijse.gdse68.aad.pos_backend.dao.ItemDao;
import lk.ijse.gdse68.aad.pos_backend.dao.ItemDaoImpl;
import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;

import java.sql.Connection;

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
}
