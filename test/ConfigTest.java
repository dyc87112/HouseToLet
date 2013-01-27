import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nextplus.housetolet.domain.Rental;
import com.nextplus.housetolet.domain.Room;
import com.nextplus.housetolet.domain.User;
import com.nextplus.housetolet.repository.RentalRepository;
import com.nextplus.housetolet.repository.RoomRepository;
import com.nextplus.housetolet.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring-core.xml","classpath:spring-jpa.xml"})
public class ConfigTest {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private RentalRepository rentalRepository;
	
	@Before
	public void setUp() {
	}

	@Test
	@Transactional
	public void testFindByUser() {
		User user = userRepository.findOne(1L);
		System.out.println(user);
		List<Room> rooms = roomRepository.findByUser(user);
	}
	
	
	
}
