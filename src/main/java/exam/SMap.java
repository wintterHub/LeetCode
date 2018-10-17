package exam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SMap<K extends String, V> extends HashMap<K, V> implements Map<K, V> {

	private static final long serialVersionUID = 1L;

	private List<Map<?, ?>> childList = new ArrayList<Map<?, ?>>();
	private List<Map<?, ?>> parentList = new ArrayList<Map<?, ?>>();

	@Override
	public String toString() {
		return formatJson(mapSerializer(this));
	}

	/**
	 * map序列化
	 */
	@SuppressWarnings("unchecked")
	private String mapSerializer(Map<?, ?> value) {
		Set<String> keySet = (Set<String>) value.keySet();
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (String key : keySet) {
			if (value.get(key) instanceof Map) {
				Map<?, ?> map = (Map<?, ?>) value.get(key);
				if (isParent(value, map)) {
					sb.append("\"" + key + "\"" + ":\"$ref\",");
				} else {
					childList.add(map);
					parentList.add(value);
					sb.append("\"" + key + "\"" + ":" + mapSerializer(map) + ",");
				}
			} else if (value.get(key) instanceof Number || value.get(key) instanceof Boolean) {
				sb.append("\"" + key + "\"" + ":" + value.get(key) + ",");
			} else {
				sb.append("\"" + key + "\"" + ":\"" + value.get(key) + "\",");
			}
		}
		sb.replace(sb.length() - 1, sb.length(), "");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 是否有父子关系
	 */
	private boolean isParent(Map<?, ?> map, Map<?, ?> child) {
		if (map == null) {
			return false;
		} else if (map == child) {
			return true;
		} else {
			List<Integer> indexList = new ArrayList<Integer>();
			int index = 0;
			for (Map<?, ?> m1 : parentList) {
				if (child == m1) {
					indexList.add(index);
					index++;
				}
			}
			for (Integer i : indexList) {
				if (this == child || childList.get(i) == map) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * JSON格式化
	 */
	public static String formatJson(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr))
			return "";
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		boolean isInQuotationMarks = false;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);
			switch (current) {
			case '"':
				if (last != '\\') {
					isInQuotationMarks = !isInQuotationMarks;
				}
				sb.append(current);
				break;
			case '{':
			case '[':
				sb.append(current);
				if (!isInQuotationMarks) {
					sb.append('\n');
					indent++;
					addIndentBlank(sb, indent);
				}
				break;
			case '}':
			case ']':
				if (!isInQuotationMarks) {
					sb.append('\n');
					indent--;
					addIndentBlank(sb, indent);
				}
				sb.append(current);
				break;
			case ',':
				sb.append(current);
				if (last != '\\' && !isInQuotationMarks) {
					sb.append('\n');
					addIndentBlank(sb, indent);
				}
				break;
			default:
				sb.append(current);
			}
		}

		return sb.toString();
	}

	/**
	 * 添加space
	 */
	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append('\040').append('\040');
		}
	}

}
