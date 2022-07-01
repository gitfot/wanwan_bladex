import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author wanwan 2022/7/1
 */
@Configuration
public class TestConfig {

	@Bean
	@Scope("prototype")
	public MyTest testBean() {
		return new MyTest();
	}
}

