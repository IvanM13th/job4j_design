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
        String crTable = String.format(
                "create table if not exists %s (id serial primary key);",
                tableName
        );
        runStatement(crTable);
    }

    public void dropTable(String tableName) throws SQLException {
        String drop = String.format(
                "drop table %s",
                tableName
        );
        runStatement(drop);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String newColumn = String.format(
                "alter table %s add %s %s",
                tableName, columnName, type
        );
        runStatement(newColumn);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String drop = String.format(
                "alter table %s drop column %s",
                tableName, columnName
        );
        runStatement(drop);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String rename = String.format(
                "alter table %s rename %s to %s",
                tableName, columnName, newColumnName);
        runStatement(rename);
    }

    private void runStatement(String command) throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.execute(command);
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

            tEditor.renameColumn("table2", "count", "amount");
            System.out.println(getTableScheme(tEditor.connection, "table2"));

            tEditor.dropColumn("table2", "amount");
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
