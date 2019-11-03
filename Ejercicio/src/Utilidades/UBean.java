package Utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class UBean {

	public static ArrayList<Field> obtenerAtributos(Object o){
		Class c = o.getClass();
		Field[] att = c.getDeclaredFields();
		ArrayList<Field> atributos = new ArrayList<Field>();
		for(Field f : att){
			atributos.add(f);
		}

		return atributos;
	}

	public static void ejecutarSet(Object o, String att, Object valor){
		String metodo = "set";
		metodo += att;
		Class c = o.getClass();
		Method[] met = c.getDeclaredMethods();
		for(Method m : met){
			if(m.getName().equalsIgnoreCase(metodo)){
				Object [] param = new Object[1];
				param[0]=valor;
				try {
					m.invoke(o, param);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Object ejecutarGet(Object o, String att){
		String metodo = "get";
		metodo += att;
		Class c = o.getClass();
		Method[] met = c.getDeclaredMethods();
		for(Method m : met){
			if(m.getName().equalsIgnoreCase(metodo)){
				Object [] param = new Object[0];
				try {
					Object obj = m.invoke(o, param);
					return obj;
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}

