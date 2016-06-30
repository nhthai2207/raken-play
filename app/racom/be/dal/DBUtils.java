package racom.be.dal;

import java.util.List;
import java.util.Map;

public interface DBUtils {
	public void startTransaction();

	public void executeTransaction();

	public void insertRowData(String columnFamily, String key, Map<String, String> data) throws DALException; // done

	public void insertSuperColumnData(String columnFamily, String key, String superColumnName, Map<String, String> data); // done

	public Long getCounterValue(String cf, String key, String columnName); // done

	public Map<String, Long> getCounterByRange(String columnFamily, String key, List<String> columns); // done

	public Map<String, Long> getTotalCounterByRangeOfMultiKeys(String columnFamily, List<String> keys, String keyStart, String keyFinish);

	public void incrementCounter(String cf, String key, String columnName, long value);

	public void decrementCounter(String cf, String key, String columnName, long value);
	
	public int countRowByRange(String columnFamily, String key, String rangeStart, String rangeEnd);
	
	public List<String> getDataByRange(String columnFamily, String key, String rangeStart, String rangeEnd);

	//public List<HSuperColumn<String, String, String>> getSCListByRange(String columnFamily, String rowKey, String startKey, String endKey) throws DALException;

	//public ColumnSlice<String, String> getSubSliceInSuperColumn(String columnFamily, String key, String superColumnName, String startKey, String endKey);

	// public <SN, N, V> Map<N, V> getMapFromSuperColumn(HSuperColumn<SN, N, V> superColumn);

	// public ColumnSlice<String, String> getColumnSlice(String columnFamily, String rowKey, List<String> columnNames);

	// public ColumnSlice<String, String> getColumnSlice(HSuperColumn hSuperColumn);

}
