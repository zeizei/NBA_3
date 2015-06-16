package common.datastructure;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class HotVo {
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer buffer = new StringBuffer();
		if (fields != null) {
			for (int i = 1; i < fields.length; i++) {
				try {
					fields[i].setAccessible(true);
					buffer.append(fields[i].get(this)).append("--");
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			return buffer.toString();
		}
		return null;
	}

	public boolean AutoEncapsulate(String[] fields, Object[] contents) {
		boolean isSucceed = true;
		if (fields != null && contents != null && fields.length == contents.length) {
			Class<?> c = this.getClass();
			for (int i = 0; i < fields.length; i++) {
				StringBuffer buffer = new StringBuffer();
				try {
					Field field = c.getDeclaredField(fields[i]);
					Class<?> MethodType = field.getType();
					buffer.append("set");
					buffer.append(fields[i].substring(0, 1).toUpperCase());
					buffer.append(fields[i].substring(1));
					Method method = c.getDeclaredMethod(buffer.toString(), MethodType);
					method.invoke(this, contents[i]);
				} catch (NoSuchFieldException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (SecurityException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					isSucceed = false;
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					isSucceed = false;
					e.printStackTrace();
				}
			}
		}
		return isSucceed;
	}

	public double cutTail(double number, int tail) {
		BigDecimal bigDecimal = new BigDecimal(number);
		double result = bigDecimal.setScale(tail, BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
	}// 保留四位小数
}
