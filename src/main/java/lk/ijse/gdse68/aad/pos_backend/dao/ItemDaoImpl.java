package lk.ijse.gdse68.aad.pos_backend.dao;


import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {

    public static String SAVE_ITEM="INSERT into item values(?,?,?,?)";

    @Override
    public String saveItem(ItemDto itemDto, Connection connection) {
        PreparedStatement ps =null;
        try {
            System.out.println("awa 1");
            ps = connection.prepareStatement(SAVE_ITEM);
            ps.setString(1, itemDto.getItemCode());
            ps.setString(2, itemDto.getItemName());
            ps.setString(3, String.valueOf(itemDto.getItemQty()));
            ps.setString(4, String.valueOf(itemDto.getUnitPrice()));
            System.out.println("awa 2");
            if (ps.executeUpdate() != 0 ){
                System.out.println("awa 3");
                return "Item Saved Successfully";
            }else {
                System.out.println("awa 4");
                return "Something went wrong";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
