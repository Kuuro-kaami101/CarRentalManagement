package context;
import java.sql.Connection;
import java.sql.DriverManager;

public interface DBContext {
    public static String DRIVERNAME="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String DBURL="jdbc:sqlserver://localhost:1433;databaseName=CarRentalManagement;encrypt=false;trustServerCertificate=false;loginTimeout=30;";
    public static String USERDB="sa";
    public static String PASSDB="123";  
}
 