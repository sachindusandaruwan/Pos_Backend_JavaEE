package lk.ijse.gdse68.aad.pos_backend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.gdse68.aad.pos_backend.bo.BOFactory;
import lk.ijse.gdse68.aad.pos_backend.bo.custom.CustomerBo;
import lk.ijse.gdse68.aad.pos_backend.bo.custom.impl.CustomerBoImpl;
import lk.ijse.gdse68.aad.pos_backend.dto.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@WebServlet(urlPatterns = "/customer")

public class CustomerController extends HttpServlet {

    Connection connection;

    static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    Jsonb jsonb = JsonbBuilder.create();

//    CustomerBo customerBo = new CustomerBoImpl();
    CustomerBo customerBo=(CustomerBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    public void init() throws ServletException {
        //System.out.println("hiii charaka dimuth awa");

        System.out.println("Init method Invoked");
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

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("doPost method Invoked");
//
//        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            System.out.println("enawada");
//        }else {
//            try(var writer = resp.getWriter()){
//                System.out.println("enawada11");
//                Jsonb jsonb = JsonbBuilder.create();
//                CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);
//                writer.write(customerBo.saveCustomer(customerDto, connection));
//            }catch (Exception e){
//
//            }
//        }
//    }

   ////////////////////////////////////////////// //////////////////////////////////////

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost method Invoked");

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else {
            try(var writer = resp.getWriter()){
                Jsonb jsonb = JsonbBuilder.create();
                CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);
                if (customerBo.saveCustomer(customerDto, connection)){
                    resp.setStatus(HttpServletResponse.SC_CREATED);
                }else{
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            }catch (Exception e){
                logger.error("Something went wrong in customer post method!");
            }
        }
    }





    ///////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("doget method Invoked");
//
//        try(var writer = resp.getWriter()) {
//            Jsonb jsonb = JsonbBuilder.create();
//           var customerId = req.getParameter("id");
//           resp.setContentType("application/json");
//           jsonb.toJson(customerBo.getCustomer(customerId,connection),writer);
//
//
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        String id = req.getParameter("id");
        if (id != null && !id.trim().isEmpty()) {
            try(var writer = resp.getWriter()){
                Jsonb jsonb = JsonbBuilder.create();
                var customerId = req.getParameter("id");
                resp.setContentType("application/json");
                var customer = customerBo.getCustomer(customerId, connection);
                if (customer != null && customer.getSalary() > 1 ){
                    jsonb.toJson(customer, writer);
                    logger.info("Customer retrieved successfully: {}", customer);
                }else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    logger.error("Customer not found: id={}", id);
                    writer.write("Customer not found");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if(req.getParameter("id") == null) {
            try(var writer =resp.getWriter()){
                List<CustomerDto> customers = customerBo.getAllCustomers(connection);
                jsonb.toJson(customers, writer);
                logger.info("All customers retrieved successfully");
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                logger.error("Failed to retrieve customers",e);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(var writer = resp.getWriter()) {
            var customerId = req.getParameter("id");
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDto customerDto= jsonb.fromJson(req.getReader(), CustomerDto.class);
            if (customerBo.updateCustomer(customerId,customerDto,connection)){
                System.out.println("customer updated");
                writer.write("customer updated successfully");
            }
            else {
                writer.write("customer not updated");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doDelete method Invoked");
        try(var writer = resp.getWriter()) {
            var customerId = req.getParameter("id");
            if (customerBo.deleteCustomer(customerId, connection)){
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
                System.out.println("customer deleted!!!");
            writer.write("customer deleted successfully");
        }else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write("Something went wrong");
            }
        }

    }




}
