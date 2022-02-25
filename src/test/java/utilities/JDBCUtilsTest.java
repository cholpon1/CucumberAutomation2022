package utilities;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JDBCUtilsTest {
    public static void main(String[] args) throws SQLException {

        JDBCUtils.establishConnection();

        List<Map<String, Object>> data=JDBCUtils.runQuery("SELECT * FROM employees");
        System.out.println(data.get(0));

        JDBCUtils.closeDatabase();






    }


}
