package br.com.fiapaoj.users.application;

import br.com.fiapaoj.users.data.UserRepository;
import br.com.fiapaoj.users.domain.UserDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AddUserUseCaseApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddUserUseCaseApplication.class);

	private final UserRepository userRepository;

	public AddUserUseCaseApplication(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String add(final String name, final String nickname){
		LOGGER.info("m=add(name={}, nickname={})", name, nickname);

		final UserDomain userDomain = UserDomain.of(name, nickname);
		userRepository.save(userDomain);
		return userDomain.getId();
	}
}