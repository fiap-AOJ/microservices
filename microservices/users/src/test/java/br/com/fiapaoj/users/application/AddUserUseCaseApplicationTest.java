package br.com.fiapaoj.users.application;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import br.com.fiapaoj.users.data.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {AddUserUseCaseApplication.class})
@TestPropertySource(properties = {"spring.cloud.config.enabled=false"}, locations = "classpath:application-test.properties")
public class AddUserUseCaseApplicationTest {

	@Autowired
	private AddUserUseCaseApplication addUserUseCaseApplication;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void addUserTest(){
		final String name = "test";
		final String nickenam = "test";
		final String id = addUserUseCaseApplication.add(name, nickenam);

		assertNotNull(id);
	}

	@Test(expected = IllegalArgumentException.class)
	public void addUserWithFailedTest(){
		given(userRepository.save(any())).willThrow(new IllegalArgumentException());

		final String name = "test";
		final String nickenam = "test";
		addUserUseCaseApplication.add(name, nickenam);
	}
}