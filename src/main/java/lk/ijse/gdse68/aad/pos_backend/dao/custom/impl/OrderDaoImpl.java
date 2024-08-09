//package lk.ijse.gdse68.aad.pos_backend.dao.custom.impl;
//
//import lk.ijse.gdse68.aad.pos_backend.dao.custom.OrderDao;
//import lk.ijse.gdse68.aad.pos_backend.entity.Order;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.SQLException;
//
//public class OrderDaoImpl implements OrderDao {
//
//        public static String SAVE_ORDER = "insert into order values (?,?,?)";
//        @Override
//        public String saveOrder(Order order, Connection connection) {
//            try {
//                var ps = connection.prepareStatement(SAVE_ORDER);
//                ps.setString(1, order.getOrderID());
//                ps.setString(2, order.getCustomerId());
//                ps.setDouble(3, order.getTotal());
////            ps.setDate(3, (Date) order.getDate());
////            ps.setDouble(3, order.getSubTotal());
//
//                if (ps.executeUpdate() != 0 ){
//
//                    return "Order Saved Successfully";
//                }else {
//                    System.out.println("dao eka");
//                    return "Something went wrong";
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//
