package racom.be.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

public class CommonUtils {
	public static DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static String numberOutput(Double number) {
		if(number.isInfinite() || number.isNaN()){
			return "0";
		}
		return String.valueOf(decimalFormat.format(number));
	}

	public static <T> T getJSONValue(Class<T> type, String key, JSONObject root, T defaultValue) {
		try {
			Object object = root.get(key);
			if (object != null && !isEmptyString(object.toString())) {
				T cast = type.cast(object);
				return cast;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			//play.Logger.error(String.format("ERROR:  %s ", key) ,e);
		}
		return defaultValue;
	}

	public static Double parseDouble(String key, JSONObject root) {
		Object object = root.get(key);
		try {
			if (object != null && !isEmptyString(object.toString())) {
				if (object instanceof String) {
					return Double.parseDouble((String) object);
				} else if (object instanceof Integer) {
					return ((Integer) object).doubleValue();
				} else {
					return (Double) object;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.error(String.format("ERROR:  %s ", key) ,e);
		
		}
		return null;
	}

	public static boolean isEmptyString(String s) {
		return s == null || "".equals(s.trim()) || "null".equals(s);
	}
	public static boolean isMiddleString(String startDate, String endDate, String now) {
		return  startDate.compareTo(now) <= 0 && endDate.compareTo(now) >= 0;
		
	}


	public static Calendar convertFromFE(String date) {
		if(CommonUtils.isEmptyString(date)){
			return null;
		}
		Calendar calendar = Calendar.getInstance();		
		try {
			calendar.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			play.Logger.error(String.format("ERROR: %s ", date) ,e);
		}
		return calendar;
	}
	
	public static Map<String,String> convertJsonToMap(JSONObject json){
		Map<String, String> ret = new HashMap<String, String>();
		Iterator<String> keys = json.keys();
		for (Iterator<String> iterator = keys; iterator.hasNext();) {
			String key = iterator.next();
			ret.put(key, json.getString(key));
		}
		
		return ret;
	}
	
}
