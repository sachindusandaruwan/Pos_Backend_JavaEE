package lk.ijse.gdse68.aad.pos_backend.dao.custom.impl;


import lk.ijse.gdse68.aad.pos_backend.dao.custom.ItemDao;
import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;
import lk.ijse.gdse68.aad.pos_backend.entity.Customer;
import lk.ijse.gdse68.aad.pos_backend.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {

    public static String SAVE_ITEM="INSERT into item values(?,?,?,?)";
    public static String GET_ITEM_BY_ITEMCODE = "select * from item where item_code=?";
    public static String UPDATE_ITEM = "UPDATE item SET item_name=?, item_qty=?, unit_price=? WHERE item_code=?";
    public static String DELETE_ITEM = "DELETE FROM item WHERE item_code=?";
    public static String GET_ALL_ITEMS ="SELECT * FROM item";

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

    @Override
    public boolean updateItem(String itemCode, ItemDto itemDto, Connection connection) {
//        System.out.println("hii mn walaya");
        try {
            var preparedStatement = connection.prepareStatement(UPDATE_ITEM);
            preparedStatement.setString(1, itemDto.getItemName());
            preparedStatement.setInt(2, itemDto.getItemQty());
            preparedStatement.setDouble(3, itemDto.getUnitPrice());

            preparedStatement.setString(4, itemCode);
//            System.out.println("kkkkkk");
            return preparedStatement.executeUpdate() != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteItem(String itemCode, Connection connection) {
        try {
            var pst=connection.prepareStatement(DELETE_ITEM);
            pst.setString(1, itemCode);
            return pst.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getAll(Connection connection) {
        List<Item> items = new ArrayList<>();
        try{
            var ps = connection.prepareStatement(GET_ALL_ITEMS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                items.add(new Item(
                        rs.getString("item_code"),
                        rs.getString("item_name"),
                        rs.getInt("item_qty"),
                        rs.getDouble("unit_price")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return items;
    }
}
