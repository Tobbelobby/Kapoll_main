package Kapoll_db;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class EclipselinkConfig extends JpaBaseConfiguration {
    JpaProperties properties;
    protected EclipselinkConfig(DataSource ds, JpaProperties props, ObjectProvider<JtaTransactionManager> txm) {
        super(ds, props, txm);
    }
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }
    protected Map<String, Object> getVendorProperties() {
        return new HashMap<>(properties.getProperties());
    }
}
