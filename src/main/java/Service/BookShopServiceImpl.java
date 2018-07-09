package Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import DAO.BookShopDao;


@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;

    /*
    * 事务属性:
    *   1:事务的传播行为:propagation=Propagation.xxx
    *   2:事务的隔离级别:isolation=Isolation.xxx
    *   3:事务是否回滚的异常:noRollbackFor = {xxxException.class}
    *   4.指定事务是否只读(标识这个事务是否只读数据不更新数据):readOnly=true|false
    *   5.指定强制回滚之前,事务可以占用的时间 : timeout = x (x为秒)
    * */

    /*
    * Spring Hibernate 事务的流程
    * 1.在方法开始之前
    *   ①获取Session
    *   ②把Session和当前线程绑定,这样就可以在DAO中使用SessionFactory
    *   的getCurrentSession()方法来获取Session了
    *   ③开启事务
    * 2.若方法正常结束,即没有出现异常,则
    *   ①提交事务
    *   ②使和当前线程绑定的Session解除绑定
    *   ③关闭Session
    * 3.若(业务)方法出现异常
    *   ①回滚事务
    *   ②使和当前线程绑定的Session解除绑定
    *   ③关闭Session
    * */

    @Override
    public void purchase(String username, String isbn) {
        int price = bookShopDao.fineBookPriceByIsbn(isbn);
        bookShopDao.updateBookStock(isbn);
        bookShopDao.updateUserAccount(username,price);
    }
}
