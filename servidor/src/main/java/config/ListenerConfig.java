package config;



import data.impl.commondatabase.DBConnection;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener()
public class ListenerConfig implements ServletContextListener {

    DBConnection pool;

    @Inject
    public ListenerConfig(DBConnection pool) {

        this.pool = pool;

    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        pool.closePool();


    }


}
