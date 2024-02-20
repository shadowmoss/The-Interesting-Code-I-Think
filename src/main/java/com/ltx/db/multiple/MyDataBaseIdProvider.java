package com.ltx.db.multiple;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

public class MyDataBaseIdProvider implements DatabaseIdProvider {
    @Override
    public void setProperties(Properties p) {

    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        return null;
    }
}
