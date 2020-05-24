package br.com.fiapaoj.users.application;

import static org.mockito.BDDMockito.given;
import static org.junit.Assert.assertEquals;

import br.com.fiapaoj.users.data.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= { MetricsCreatedUsersUseCaseApplication.class })
@TestPropertySource(properties = {"spring.cloud.config.enabled=false"}, locations = "classpath:application-test.properties")
public class MetricsCreatedUsersUseCaseApplicationTest {

	private static final Long COUNT_INITIALIZE = 100L;

	private MetricsCreatedUsersUseCaseApplication metricsCreatedUsersUseCaseApplication;

	@MockBean
	private UserRepository userRepository;

	@Before
	public void before(){
		given(userRepository.count()).willReturn(100L);
		metricsCreatedUsersUseCaseApplication = new MetricsCreatedUsersUseCaseApplication(userRepository);
	}

	@Test
	public void countTest(){
		final Long count = metricsCreatedUsersUseCaseApplication.count();
		assertEquals(COUNT_INITIALIZE, count);
	}

}
