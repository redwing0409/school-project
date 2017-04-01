/*
 *  1. �U�νƦX�d��-�i�ѫȤ���H�N�W�����Q�d�ߪ����
 *  2. ���F�קK�v�T�į�:
 *        �ҥH�ʺA���͸U��SQL������,���d�ҵL�N�ĥ�MetaData���覡,�]�u�w��ӧO��Table�ۦ���ݭn�ӭӧO�s�@��
 * */


package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Article {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("article_no".equals(columnName) || "member_no".equals(columnName) ) // �Ω��L
			aCondition = columnName + "=" + value;
		else if ("member_acc".equals(columnName)) //�Ω�S�����b��
			aCondition = "member_no" + "=" + "0";
		else if ("article_title".equals(columnName) || "article_content".equals(columnName) || "article_time".equals(columnName) ) // �Ω�varchar
			aCondition = columnName + " like '%" + value + "%'";
		else if ("article_views".equals(columnName))                          // �Ω�Oracle��date
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

				System.out.println("���e�X�d�߸�ƪ�����count = " + count);
			}
		}
		
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// �t�X req.getParameterMap()��k �^�� java.util.Map<java.lang.String,java.lang.String[]> ������
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("article_no", new String[] { "1" });
		map.put("member_no", new String[] { "10001" });
		map.put("article_title", new String[] { "�g" });
		map.put("article_content", new String[] { "���e" });
		map.put("article_views", new String[] { "0" });
		map.put("article_time", new String[] { "2016-12-26" });
		map.put("action", new String[] { "getXXX" }); // �`�NMap�̭��|�t��action��key

		String finalSQL = "select * from article "
				          + jdbcUtil_CompositeQuery_Article.get_WhereCondition(map)
				          + "order by article_time";
		System.out.println("����finalSQL = " + finalSQL);

	}
}
