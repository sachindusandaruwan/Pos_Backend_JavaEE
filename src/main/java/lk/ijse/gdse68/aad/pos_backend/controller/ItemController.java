package lk.ijse.gdse68.aad.pos_backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.aad.pos_backend.bo.ItemBo;
import lk.ijse.gdse68.aad.pos_backend.bo.ItemBoImpl;
import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;
import lk.ijse.gdse68.aad.pos_backend.dto.ItemDto;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {

    Connection connection;

    ItemBo itemBo=new ItemBoImpl();

    @Override
    public void init() throws ServletException {
        System.out.println("mn awaa item");
        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();
            System.out.println("Connection initialized: " + this.connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost method Invoked!!!");
        try{
            if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                System.out.println("eewww");
            }else {
                try(var writer = resp.getWriter()){
                    System.out.println("enawada halo");
                    Jsonb jsonb = JsonbBuilder.create();
                    System.out.println("enawada halo 2");
                    ItemDto itemDto = jsonb.fromJson(req.getReader(), ItemDto.class);
                    System.out.println("enawada halo3");
                    writer.write(itemBo.saveItem(itemDto, connection));
                    System.out.println("enawada hlo 4");
                }catch (Exception e){

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doget method Invoked");

        try(var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            var itemCode = req.getParameter("itemCode");
            resp.setContentType("application/json");
            jsonb.toJson(itemBo.getItem(itemCode,connection),writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var itemCode = req.getParameter("itemCode");
            System.out.println(itemCode);
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto= jsonb.fromJson(req.getReader(), ItemDto.class);
//            System.out.println("hu hu");
            if (itemBo.updateItem(itemCode,itemDto,connection)){
                System.out.println("Item updated");
                writer.write("Item updated successfully");
            }
            else {
                writer.write("Item not updated");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doDelete method Invoked");
        try(var writer = resp.getWriter()) {
            var itemCode = req.getParameter("itemCode");
            if (itemBo.deleteItem(itemCode, connection)){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                System.out.println("Item deleted!!!");
                writer.write("Item deleted successfully");
            }else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Something went wrong");
            }
        }

    }
}
