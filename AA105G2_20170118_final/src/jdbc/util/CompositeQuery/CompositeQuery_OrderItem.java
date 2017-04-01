package jdbc.util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompositeQuery_OrderItem {
		public static String get_aCondition_For_Oracle(String columnName, String value) {
			String aCondition = null;
			if ("ord_price".equals(columnName) || "ord_qty".equals(columnName) || "return_qty".equals(columnName) || "ship_status".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("pur_no".equals(columnName) || "com_no".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			return aCondition + " ";
		}
	//複合查詢主要方法
			public static String get_WhereCondition(Map<String, String[]> map) {
				Set<String> keys = map.keySet();
				StringBuffer whereCondition = new StringBuffer();
				int count = 0;
				for (String key : keys) {
					String value = map.get(key)[0];
					if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
						count++;
						String aCondition = get_aCondition_For_Oracle(key, value.trim());

						if (count == 1)
							whereCondition.append(" where " + aCondition);
						else
							whereCondition.append(" and " + aCondition);

						System.out.println("有送出查詢資料的欄位數count = " + count);
					}
				}	
				return whereCondition.toString();
			}
		}