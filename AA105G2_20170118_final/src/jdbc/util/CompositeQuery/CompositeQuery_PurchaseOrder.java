package jdbc.util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CompositeQuery_PurchaseOrder {

		public static String get_aCondition_For_Oracle(String columnName, String value) {
			String aCondition = null;
			if ("pur_no".equals(columnName) || "member_no".equals(columnName) || "pur_status".equals(columnName) || "pur_sum".equals(columnName)) // �Ω��L
				aCondition = columnName + "=" + value;
			else if ("pur_name".equals(columnName) || "pur_add".equals(columnName)) // �Ω�varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("pur_date".equals(columnName))                          // �Ω�Oracle��date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
			return aCondition + " ";
		}
//�ƦX�d�ߥD�n��k
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

					System.out.println("���e�X�d�߸�ƪ�����count = " + count);
				}
			}	
			return whereCondition.toString();
		}

/* public static void main(String argv[]) {
			// �t�X req.getParameterMap()��k �^�� java.util.Map<java.lang.String,java.lang.String[]> ������
			Map<String, String[]> map = new TreeMap<String, String[]>();
			map.put("pur_no", new String[] { "10060" });
			map.put("member_no", new String[] { "10001" });
			map.put("pur_status", new String[] { "1" });
			map.put("pur_date", new String[] { "2017-01-06" });
			map.put("pur_sum", new String[] { "100" });
			map.put("pur_name", new String[] { "�L�[�n" });
			map.put("pur_add", new String[] { "�x�_�������ϭ��y�n���@�q122��" });
			map.put("action", new String[] { "getXXX" }); // �`�NMap�̭��|�t��action��key

			String finalSQL = "select * from purchase_order "
					          + CompositeQuery_PurchaseOrder.get_WhereCondition(map)
					          + "order by pur_date";
			System.out.println("����finalSQL = " + finalSQL);
		}*/
	}