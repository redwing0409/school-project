package jdbc.util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompositeQuery_Commodity {
		public static String get_aCondition_For_Oracle(String columnName, String value) {
			String aCondition = null;
			if ("pcm_no".equals(columnName) || "com_price".equals(columnName) || "com_status".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("com_no".equals(columnName) || "sup_no".equals(columnName) || "com_name".equals(columnName) || "com_desc".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("com_shelf_date".equals(columnName) || "com_off_date".equals(columnName) )                          // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
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