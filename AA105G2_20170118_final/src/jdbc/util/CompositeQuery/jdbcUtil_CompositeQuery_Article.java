/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Article {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("article_no".equals(columnName) || "member_no".equals(columnName) ) // 用於其他
			aCondition = columnName + "=" + value;
		else if ("member_acc".equals(columnName)) //用於沒有此帳號
			aCondition = "member_no" + "=" + "0";
		else if ("article_title".equals(columnName) || "article_content".equals(columnName) || "article_time".equals(columnName) ) // 用於varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("article_views".equals(columnName))                          // 用於Oracle的date
			aCondition = columnName + " > " + value + " ";
		return aCondition + " ";
	}

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
					whereCondition.append(" where article_status=1 and " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("article_no", new String[] { "1" });
		map.put("member_no", new String[] { "10001" });
		map.put("article_title", new String[] { "篇" });
		map.put("article_content", new String[] { "內容" });
		map.put("article_views", new String[] { "0" });
		map.put("article_time", new String[] { "2016-12-26" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from article "
				          + jdbcUtil_CompositeQuery_Article.get_WhereCondition(map)
				          + "order by article_time";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
