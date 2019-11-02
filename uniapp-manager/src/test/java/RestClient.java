import com.junfeng.platform.manager.UniappManagerApplication;
import com.junfeng.platform.manager.service.UserService;
import com.pig4cloud.pig.common.core.util.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述
 *
 * @author daiysh
 * @date 2019-10-16 11:16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UniappManagerApplication.class)
public class RestClient {

	@Autowired
	private UserService userService;
	@Test
	public void test() {
		R<Object> objectR = userService.login("daiysh","LFEt60GoMTj5/mQQ7HjJeA==");

		System.out.println("result:"+objectR);
	}
}
