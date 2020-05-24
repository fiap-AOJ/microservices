package br.com.fiapaoj.users.application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {FindUserUseCaseApplication.class})
@TestPropertySource(properties = {"spring.cloud.config.enabled=false"}, locations = "classpath:application-test.properties")
public class FindUserUseCaseApplicationTest {

	@Autowired
	private FindUserUseCaseApplication findUserUseCaseApplication;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void findAllTest(){
		given(userRepository.findAll()).willReturn(buildMockUsers());

		final Set<UserDomain> users = findUserUseCaseApplication.findAll();
		assertFalse(users.isEmpty());
	}

	@Test
	public void findAllEmptyDataTest(){
		given(userRepository.findAll()).willReturn(buildMockEmptyUsers());

		final Set<UserDomain> users = findUserUseCaseApplication.findAll();
		assertTrue(users.isEmpty());
	}

	private List<UserDomain> buildMockEmptyUsers() {
		return Collections.emptyList();
	}

	private List<UserDomain> buildMockUsers() {
		return Stream.of(buildMockUser()).collect(Collectors.toList());
	}

	private UserDomain buildMockUser() {
		return UserDomain.of("test", "test");
	}

	@Test
	public void findByIdTest(){
		final UserDomain userDomain = buildMockUser();

		given(userRepository.findById(userDomain.getId())).willReturn(Optional.of(userDomain));

		final Optional<UserDomain> optionalOfUserDomain = findUserUseCaseApplication.find(userDomain.getId());
		assertTrue(optionalOfUserDomain.isPresent());
		assertEquals(userDomain, optionalOfUserDomain.get());
	}

	@Test
	public void findByIdNotFound(){
		final UserDomain userDomain = buildMockUser();

		given(userRepository.findById(userDomain.getId())).willReturn(Optional.empty());

		final Optional<UserDomain> optionalOfUserDomain = findUserUseCaseApplication.find(userDomain.getId());
		assertFalse(optionalOfUserDomain.isPresent());
	}
}
