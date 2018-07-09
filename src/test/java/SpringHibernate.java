import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import Service.BookShopService;
import Service.Cashier;

public class SpringHibernate {

    private ApplicationContext applicationContext;
    private BookShopService bookShopService;
    private Cashier cashier;
    {
        applicationContext = new ClassPathXmlApplicationContext(
                "XML/applicationContext.xml");
        bookShopService = applicationContext.getBean(BookShopService.class);
        cashier = applicationContext.getBean(Cashier.class);
    }

    @Test
    public void testCashier() {
        cashier.checkout("aa",Arrays.asList("1001","1002"));
    }

    @Test
    public void testBookShopService() {
        bookShopService.purchase("aa","1001");
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
}
