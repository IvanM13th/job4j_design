package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(this.properties.getProperty("driver_class"));
        String url = this.properties.getProperty("url");
        String username = this.properties.getProperty("username");
        String password = this.properties.getProperty("password");
        connection = DriverManager.getConnection(url, username, password);
    }

    public void createTable(String tableName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String crTable = String.format(
                    "create table if not exists %s(%s, %s, %s, %s);",
                    tableName,
                    "id serial primary key",
                    "name text",
                    "quantity int",
                    "existence boolean"
            );
            statement.execute(crTable);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        try (Statement st = connection.createStatement()) {
            String dropTable = String.format(
                    "drop table %s",
                    tableName
            );
            st.execute(dropTable);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        try (Statement st = connection.createStatement()) {
            String newColumn = String.format(
                    "alter table %s add %s %s",
                    tableName, columnName, type
            );
            st.execute(newColumn);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        try (Statement st = connection.createStatement()) {
            String drop = String.format(
                    "alter table %s drop column %s",
                    tableName, columnName
            );
            st.execute(drop);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement st = connection.createStatement()) {
            String rename = String.format(
                    "alter table %s rename %s to %s",
                    tableName, columnName, newColumnName
            );
            st.execute(rename);
        }
    }

    private static Properties getProperties() {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("table_editor.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor tEditor = new TableEditor(getProperties())) {
            tEditor.createTable("table2");
            System.out.println(getTableScheme(tEditor.connection, "table2"));

            tEditor.addColumn("table2", "count", "int");
            System.out.println(getTableScheme(tEditor.connection, "table2"));

            tEditor.dropColumn("table2", "existence");
            System.out.println(getTableScheme(tEditor.connection, "table2"));

            tEditor.renameColumn("table2", "count", "amount");
            System.out.println(getTableScheme(tEditor.connection, "table2"));

            tEditor.dropTable("table2");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
