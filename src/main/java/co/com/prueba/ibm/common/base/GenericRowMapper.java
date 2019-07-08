package co.com.prueba.ibm.common.base;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;

public class GenericRowMapper {
	
	HashMap<String, Object> fields;

	public HashMap<String, Object> getFields() {
		return fields;
	}

	public void setFields(HashMap<String, Object> fields) {
		this.fields = fields;
	}
	
	public boolean existsColumn(String field, int type, ResultSet row) {
		boolean exist = true;
		try {
			switch(type) {
			case Types.INTEGER: 
				row.getInt(field);
				break;
			case Types.FLOAT:
				row.getFloat(field);
				break;
			case Types.VARCHAR:
				row.getString(field);
				break;
			case Types.DATE:
				row.getDate(field);
				break;
			}
			
		}catch(Exception ex) {
			System.err.println("Error: "+ex.getMessage() +((ex.getCause() != null)?" Cause:"+ex.getCause().getMessage():" None"));
			exist = false;
		}
		
		return exist;
	}
	
}
