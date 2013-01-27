import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nextplus.housetolet.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring-core.xml","classpath:spring-jpa.xml"})
public class UserTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void setUp() {
	}

	@Test
	public void testFindUserByUsername() {
	}
	
	@Test
	public void testFindUserByEmail() {
	}

}
