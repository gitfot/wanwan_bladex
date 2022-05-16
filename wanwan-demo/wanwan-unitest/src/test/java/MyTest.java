import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wanwan 2022/3/9
 */
public class MyTest {



	@Test
	public void test1() {

		String joi1 = StringUtils.join(Lists.newArrayList(1, 2, 3),",");
		String joi2 = StringUtils.join(Lists.newArrayList("1", "2", "3"),",");
		System.out.println(joi1);
		System.out.println(joi2);
	}
}
