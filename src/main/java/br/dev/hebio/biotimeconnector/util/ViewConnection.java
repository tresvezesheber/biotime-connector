package br.dev.hebio.biotimeconnector.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class ViewConnection {

    @Value("${sql.view.path}")
    private String viewPath;

    @Value("${sql.view.username}")
    private String viewUsername;

    @Value("${sql.view.password}")
    private String viewPassword;

    public List<?> executeQuery(String query) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection conn = DriverManager.getConnection(viewPath, viewUsername, viewPassword);
        Statement stmt = conn.createStatement();
        //String query = query;
        ResultSet rs = stmt.executeQuery(query);

        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            int numColumns = rsmd.getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 1; i <= numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, rs.getObject(column_name));
            }
            json.put(obj.toMap());
        }

        return json.toList();
    }
}