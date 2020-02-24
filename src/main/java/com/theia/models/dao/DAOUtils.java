package com.theia.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.theia.config.DatabaseTable;
import com.theia.config.TheiaConfiguration;
import com.theia.enums.Fields;
import com.theia.models.media.beans.Media;
import com.theia.utils.MediaUtils;
import com.theia.utils.Utils;

public class DAOUtils {

	private static Connection connection;
	
	public static Connection getConnection() {
		if (connection == null) {
	    	try {
				connection = DriverManager.getConnection(
						TheiaConfiguration.getDatabaseConfigInstance().getUrl() + TheiaConfiguration.getDatabaseConfigInstance().getName(), 
						TheiaConfiguration.getDatabaseConfigInstance().getUser(), 
						TheiaConfiguration.getDatabaseConfigInstance().getPassword());
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
		return connection;
    }

	public static ResultSet prepareSelectAndQuery(DatabaseTable table, Map<String, String> queryParameters) throws SQLException {
    	StringBuilder query = new StringBuilder("SELECT * FROM " + table.getName());
    	if (queryParameters != null && !queryParameters.isEmpty()) {
    		query.append(" WHERE ");
    		for(String parameter : queryParameters.keySet()) {
        		query.append(parameter + "='" + queryParameters.get(parameter) + "' AND ");
        	}
        	query.append("1=1");
    	}
    	query.append(" ORDER BY " + table.getName() + "." + table.getType().toString() + "_" + Fields.TITRE);
    	System.out.println(query.toString());
    	return getConnection().prepareStatement(query.toString()).executeQuery();
    }

	public static ResultSet prepareSelectOrQuery(DatabaseTable table, Map<String, List<String>> queryParameters) throws SQLException {
    	StringBuilder query = new StringBuilder("SELECT * FROM " + table.getName());
    	if (queryParameters != null && !queryParameters.isEmpty()) {
    		query.append(" WHERE (");
    		for(String parameter : queryParameters.keySet()) {
    			for(String value : queryParameters.get(parameter)) {
    				query.append(parameter + "='" + value.trim() + "' OR ");
    			}
            	query.append(" 0=1) AND ");
    		}
        	query.append("1=1");
    	}
    	query.append(" ORDER BY " + table.getName() + "." + table.getType().toString() + "_" + Fields.TITRE);
    	System.out.println(query.toString());
    	return getConnection().prepareStatement(query.toString()).executeQuery();
	}
	
	public static ResultSet prepareSelectSpecificField(DatabaseTable table, String field) throws SQLException {
    	StringBuilder query = new StringBuilder("SELECT " + field + " FROM " + table.getName() + " ORDER BY " + field + " ASC");
    	System.out.println(query.toString());
    	return getConnection().prepareStatement(query.toString()).executeQuery();
    }

	public static ResultSet prepareSelectInnerJoinSeriesQuery(String query, Map<String, String> queryParameters, String orderBy) throws SQLException {
		StringBuilder queryBuilder = new StringBuilder(query);
		if (queryParameters != null && !queryParameters.isEmpty()) {
    		queryBuilder.append(" WHERE "); 
    		for(String parameter : queryParameters.keySet()) {
        		queryBuilder.append(parameter + "='" + queryParameters.get(parameter) + "' AND ");
        	}
	        queryBuilder.append("1=1");
	        queryBuilder.append(" ORDER BY " + orderBy);
    	}
    	System.out.println(queryBuilder.toString());
    	return getConnection().prepareStatement(queryBuilder.toString()).executeQuery();
    }
	
	public static ResultSet prepareSelectAndQuery(DatabaseTable table) throws SQLException {
		return prepareSelectAndQuery(table, null);
    }

	public static int prepareInsertQuery(DatabaseTable table, Map<String, String> queryParameters) throws SQLException {
		Connection connection = getConnection();
    	StringBuilder query = new StringBuilder("INSERT INTO " + table.getName() + " (");
    	if (queryParameters != null && !queryParameters.isEmpty()) {
    		query.append(String.join(", ", queryParameters.keySet())); 
    		query.append(") VALUES ('"); 
    		query.append(String.join("', '", Utils.replaceValuesInMap(queryParameters)));
    		query.append("');");
    	}
    	System.out.println(query.toString());
    	connection.createStatement().executeUpdate(query.toString());
    	ResultSet resultSet = connection.prepareStatement("SELECT LAST_INSERT_ID();").executeQuery();
    	System.out.println("Entry added");
    	return (resultSet != null && resultSet.first()) ? resultSet.getInt(1) : -1;
    }
	
	public static int prepareTruncateQuery(DatabaseTable table) throws SQLException {
		Connection connection = getConnection();
    	String query = "TRUNCATE TABLE " + table.getName();
    	System.out.println(query.toString());
    	connection.createStatement().execute(query.toString());
    	return 0;
    }
	
	public static boolean isValidQuery(DatabaseTable table, Map<String, String> queryParameters) {
		List<String> mandatoryFields = Arrays.asList(table.getMandatoryFields().split(","));
		if (!queryParameters.keySet().containsAll(mandatoryFields)){
			System.out.println("Mandatory parameters missing in map: " + queryParameters); 
			return false;
		}
		return true;
	}
	
	public static boolean isEntryExists(DatabaseTable table, Map<String, String> queryParameters) {
		try {
			ResultSet resultSet = prepareSelectAndQuery(table, queryParameters);
			if (resultSet.next()) {
	            return !Utils.isValueNull(resultSet.getString(table.getType().name() + "_" + Fields.ID));
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String getFieldStringValue(ResultSet resultSet, String field) {
		try{
			return resultSet.getString(field);
		}catch(SQLException ex) {
			return null;
		}
	}
	
	public static int getFieldIntValue(ResultSet resultSet, String field) {
		try{
			return resultSet.getInt(field);
		}catch(SQLException ex) {
			return -1;
		}
	}
}
