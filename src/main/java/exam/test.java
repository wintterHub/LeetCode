package exam;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class test {

	private Map<String, Object> map = new SMap<String, Object>();
	private Map<String, Object> map2 = new SMap<String, Object>();
	private Map<String, Object> map3 = new SMap<String, Object>();

	private Map<String, Object> map4 = new HashMap<String, Object>();
	private Map<String, Object> map5 = new HashMap<String, Object>();

	private JSONObject jo1 = new JSONObject();
	private JSONObject jo2 = new JSONObject();

	@Test
	public void test1() {
		map.put("1", 1);
		map.put("2", true);
		map.put("4", map);

		map2.put("a", "a");
		map2.put("b", map);

		System.out.println(map);
	}

	@Test
	public void test2() {
		map.put("1", 1);
		map.put("2", "2");
		map2.put("a", "a");
		map2.put("b", "b");
		map.put("3", map2); // <map2,map>
		map.put("4", map2); // <map,?>
		System.out.println(map);
	}

	@Test
	public void test3() {
		map.put("1", 1);
		map.put("2", true);
		map2.put("a", "a");
		map2.put("b", "b");
		map2.put("c", map);
		map.put("4", map2);// <map2,map>
		System.out.println(map);
	}

	@Test
	public void test4() {
		map.put("1", 1);
		map.put("2", "2");

		map2.put("a", "a");
		map2.put("b", "b");

		map3.put("a1", map2);// <map2,map3>

		map.put("4", map3); // <map3,map>
		map.put("3", map2); // <map2,map>
		System.out.println(map);
	}

	@Test
	public void test5() {
		map.put("1", 1);
		map.put("2", "2");

		map2.put("a", "a");
		map2.put("b", map3);

		map3.put("a1", map);

		map.put("3", map2);
		map.put("4", map3);
		System.out.println(map);
	}

	@Test
	public void test6() {
		map5.put("2", "3");
		map4.put("0", map5);

		System.out.println(map4);
	}

	// @Test
	// public void test4() {
	// jo1.put("1", "0");
	// jo1.put("2", "0");
	// jo1.put("3", jo2);
	//
	// jo2.put("a", "0");
	// jo2.put("b", "0");
	// jo2.put("c", jo1);
	//
	// System.out.println(jo2.toJSONString());
	// }

}
