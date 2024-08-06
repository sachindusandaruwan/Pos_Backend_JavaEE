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
}
