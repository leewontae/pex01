import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.fail;

@Log4j
public class test2 {
    static {
        try{
            Class.forName("oracle.jdbc.OracleDriver");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection(){
        try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
                "BOOK_EX","1234")){
            log.info(conn);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
