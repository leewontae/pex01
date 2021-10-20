import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import org.junit.runner.RunWith;
//import org.zerock.controller.config.RootConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.sql.DataSource;
import java.sql.Connection;

import static junit.framework.TestCase.fail;

@RunWith(SpringJUnit4ClassRunner.class)

//xml인 경우
@ContextConfiguration(
        locations = {"file:web/WEB-INF/applicationContext.xml"}
)

//java인경우
//@ContextConfiguration(classes={RootConfig.class})

@Log4j
public class JDBCTest1 {

    @Setter(onMethod_= {@Autowired})
    private DataSource dataSource;

    @Setter(onMethod_={@Autowired})
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testMyBatis(){

        try(SqlSession session = sqlSessionFactory.openSession();
            Connection con = session.getConnection();
        ){
            log.info(" session "+session);
            log.info(" con "+con);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testConnection(){
        try(Connection con = dataSource.getConnection()){
            log.info(" con "+ con);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }
}
