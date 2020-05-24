package br.com.fiapaoj.users.application;

import static org.mockito.BDDMockito.given;

import br.com.fiapaoj.users.data.UserRepository;
import br.com.fiapaoj.users.domain.UserDomain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= { //
		UpdateUserUseCaseApplication.class, //
		DeleteUserUseCaseApplication.class
})
@TestPropertySource(properties = {"spring.cloud.config.enabled=false"}, locations = "classpath:application-test.properties")
public class UpdateUserUseCaseApplicationTest {

	@Autowired
	private UpdateUserUseCaseApplication updateUserUseCaseApplication;

	@MockBean
	private FindUserUseCaseApplication findUserUseCaseApplication;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void updateUserTest(){
		final UserDomain userDomain = buildMockUser();

		given(findUserUseCaseApplication.find(userDomain.getId())).willReturn(Optional.of(userDomain));

		updateUserUseCaseApplication.update(userDomain.getId(), "Test update name");
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateUserNotFoundTest(){
		final UserDomain userDomain = buildMockUser();

		given(findUserUseCaseApplication.find(userDomain.getId())).willReturn(Optional.empty());

		updateUserUseCaseApplication.update(userDomain.getId(), "Test update name");
	}

	private UserDomain buildMockUser() {
		return UserDomain.of("test", "test");
	}
}
