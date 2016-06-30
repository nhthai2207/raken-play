package racom.be.dal;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class UtilityDao {

	final static long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;

	

	/**
	 * Convert ByteArray To String
	 * 
	 * @param byteArray
	 * @return String
	 */
	public static String ConvertByteArrayUUITTimeToString(byte[] byteArray) {
		// String string = "";
		// for (byte b : byteArray) {
		// string += (char) (b + 128);
		// }
		// return string;

		long msb = 0;
		long lsb = 0;
		assert byteArray.length == 16;
		for (int i = 0; i < 8; i++)
			msb = (msb << 8) | (byteArray[i] & 0xff);
		for (int i = 8; i < 16; i++)
			lsb = (lsb << 8) | (byteArray[i] & 0xff);

		com.eaio.uuid.UUID u = new com.eaio.uuid.UUID(msb, lsb);
		return u.toString();
	}

	/**
	 * Convert specific Date To UUID
	 * 
	 * @param date
	 * @return UUID
	 */
	public static UUID uuidForDate(Date date) {
		final long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;

		long origTime = date.getTime();
		long time = origTime * 10000 + NUM_100NS_INTERVALS_SINCE_UUID_EPOCH;
		long timeLow = time & 0xffffffffL;
		long timeMid = time & 0xffff00000000L;
		long timeHi = time & 0xfff000000000000L;
		long upperLong = (timeLow << 32) | (timeMid >> 16) | (1 << 12) | (timeHi >> 48);
		return new UUID(upperLong, 0xC000000000000000L);
	}

	/**
	 * Convert String To ByteArray
	 * 
	 * @param string
	 * @return byte array
	 */
	public static byte[] ConvertStringUUIDTimeToByteArray(String string) {
		UUID uuid = java.util.UUID.fromString(string);
		long msb = uuid.getMostSignificantBits();
		long lsb = uuid.getLeastSignificantBits();
		byte[] buffer = new byte[16];

		for (int i = 0; i < 8; i++) {
			buffer[i] = (byte) (msb >>> 8 * (7 - i));
		}
		for (int i = 8; i < 16; i++) {
			buffer[i] = (byte) (lsb >>> 8 * (7 - i));
		}
		return buffer;
	}

	public static boolean compareByteArray(byte[] left, byte[] right) {
		if (left.length != right.length)
			return false;
		for (int i = 0; i < right.length; i++) {
			if (left[i] != right[i])
				return false;
		}
		return true;
	}

	public static String putStringToJsonString(String key, String value, String src) {
		try {
			JSONObject json = new JSONObject(src);
			json.put(key, value);
			return json.toString();
		} catch (JSONException e) {
		}
		return "";
	}
}
