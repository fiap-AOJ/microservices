package br.com.fiapaoj.users.application;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;

import br.com.fiapaoj.users.data.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {DeleteUserUseCaseApplication.class})
@TestPropertySource(properties = {"spring.cloud.config.enabled=false"}, locations = "classpath:application-test.properties")
public class DeleteUserUseCaseApplicationTest {

	@Autowired
	private DeleteUserUseCaseApplication deleteUserUseCaseApplication;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void deleteTest(){
		final String id = UUID.randomUUID().toString();
		given(userRepository.existsById(id)).willReturn(Boolean.TRUE);

		final Boolean isSuccess = deleteUserUseCaseApplication.delete(id);
		assertTrue(isSuccess);
	}

	@Test
	public void deleteWithFailedTest(){
		final String id = UUID.randomUUID().toString();

		given(userRepository.existsById(id)).willReturn(Boolean.FALSE);

		final Boolean isSuccess = deleteUserUseCaseApplication.delete(id);
		assertFalse(isSuccess);
	}

}
