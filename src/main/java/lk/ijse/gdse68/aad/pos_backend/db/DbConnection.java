package lk.ijse.gdse68.aad.pos_backend.db;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    Connection connection;
    DbConnection dbConnection;
    public DbConnection(){

    }
    public Connection getDbConnection(){
        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/pos");
            this.connection = pool.getConnection();
            System.out.println("Connection initialized: " + this.connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
