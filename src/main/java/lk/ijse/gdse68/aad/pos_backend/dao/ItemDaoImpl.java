package lk.ijse.gdse68.aad.pos_backend.dao;


import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;
import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {

    public static String SAVE_ITEM="INSERT into item values(?,?,?,?)";
    public static String GET_ITEM_BY_ITEMCODE = "select * from item where item_code=?";

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

    @Override
    public ItemDto getItem(String itemCode, Connection connection) {
        var itemDto = new ItemDto();
        try {
            var preparedStatement = connection.prepareStatement(GET_ITEM_BY_ITEMCODE);
            preparedStatement.setString(1, itemCode);

            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                itemDto.setItemCode(resultSet.getString("item_code"));
                itemDto.setItemName(resultSet.getString("item_name"));
                itemDto.setItemQty(resultSet.getInt("item_qty"));
                itemDto.setUnitPrice(resultSet.getDouble("unit_price"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDto;
    }
}
