package br.edu.udc.sistemas.pwm2018.infra;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import br.edu.udc.sistemas.pwm2018.infra.annotation.Column;
import br.edu.udc.sistemas.pwm2018.infra.annotation.DataType;
import br.edu.udc.sistemas.pwm2018.infra.annotation.Transient;

@SuppressWarnings("all")
public class Factory {
	
	public static Object[] createByResultSet(ResultSet rst, Class <?> c) throws Exception {
		Field fieldList[] = Reflection.getFields(c, false);
		Collection <Object> result = new Vector <Object> ();
		while (rst.next()) {
			Object obj = c.getConstructor().newInstance();
			for (int i = 0; i < fieldList.length; i++) {
				Column column = fieldList[i].getAnnotation(Column.class);
				switch (column.type()) {
					case BOOLEAN:
						Reflection.setFieldValue(fieldList[i], obj, rst.getBoolean(column.name()));
						break;
					case CHAR:
						Reflection.setFieldValue(fieldList[i], obj, rst.getString(column.name()));
						break;
					case DATE:
						Reflection.setFieldValue(fieldList[i], obj, rst.getDate(column.name()));
						break;
					case DATETIME:
						Reflection.setFieldValue(fieldList[i], obj, rst.getDate(column.name()));
						break;
					case TIME:
						Reflection.setFieldValue(fieldList[i], obj, rst.getTime(column.name()));
						break;
					case FLOAT:
						Reflection.setFieldValue(fieldList[i], obj, rst.getFloat(column.name()));
						break;
					case LONG:
						Reflection.setFieldValue(fieldList[i], obj, rst.getLong(column.name()));
						break;
					case INTEGER:
						Reflection.setFieldValue(fieldList[i], obj, rst.getInt(column.name()));
						break;
					case STRING:
						Reflection.setFieldValue(fieldList[i], obj, rst.getString(column.name()));
						break;
					case OBJECT:
						Integer idFK = rst.getInt(column.name());
						Object objFK = fieldList[i].getType().getConstructor().newInstance();
						Reflection.setIdFieldValue(objFK,idFK);
						Reflection.setFieldValue(fieldList[i], obj, objFK);						
					default:
						break;
				}
			}
			result.add(obj);
		}
		return (Object[]) result.toArray(new Object[result.size()]);
	}
	
	private static Object getFormatedValue(Field field, String rawValue) {
		Column column = field.getAnnotation(Column.class);
		DataType type;
		if (column != null) {
			type = column.type();
		} else {
			Transient transientColumn = field.getAnnotation(Transient.class);
			type = transientColumn.type();
		}
		if (rawValue != null) {
			switch (type) {
			case CHAR:
				if (rawValue.length() > 0) {
					return rawValue.charAt(0);
				}
				break;
			case STRING:
				return rawValue;
			case DATE:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					return sdf.parse(rawValue);
				} catch (Exception e) {
					return null;
				}
			case TIME:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					return sdf.parse(rawValue);
				} catch (Exception e) {
					return null;
				}
			case DATETIME:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					return sdf.parse(rawValue);
				} catch (Exception e) {
					return null;
				}
			case BOOLEAN:
				return rawValue.equals("true") || (!rawValue.equals("0") && (!rawValue.equals("false"))); 
			case INTEGER:
				try {
					return Integer.parseInt(rawValue);
				} catch (Exception e) {
					return null;
				}
			case LONG:
				try {
					return Long.parseLong(rawValue);
				} catch (Exception e) {
					return null;
				}
			case FLOAT:
				try {
					return Float.parseFloat(rawValue);
				} catch (Exception e) {
					return null;
				}
			case OBJECT:
				try {
					Integer id = Integer.parseInt(rawValue);
					Object objFK = field.getType().getConstructor().newInstance();
					Reflection.setIdFieldValue(objFK, id);
					return objFK;				
				} catch (Exception e) {
					return null;
				}
			default:
				break;
			}
		}
		return null;
	}
	
	public static Object createById(Integer id, Class <?> c) throws Exception {
		Object obj = c.getConstructor().newInstance();
		Reflection.setIdFieldValue(obj, id);
		return obj;
	}
	
	public static Object createByPost(HashMap <String,String> postData,  Class<?> c) throws Exception {
		Object obj = c.getConstructor().newInstance();
		Field fields[] = Reflection.getFields(c,true);
		for (Iterator iterator = postData.keySet().iterator(); iterator.hasNext();) {
			String postFieldName = (String) iterator.next();
			for (int i = 0; i < fields.length; i++) {
				if (postFieldName.equalsIgnoreCase(fields[i].getName())) {
					Reflection.setFieldValue(fields[i], obj, getFormatedValue(fields[i], postData.get(postFieldName)));
					break;
				}
			}
		}
		return obj;
	}
	

	
}
