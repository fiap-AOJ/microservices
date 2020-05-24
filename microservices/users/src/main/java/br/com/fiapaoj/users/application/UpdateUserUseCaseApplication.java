package br.com.fiapaoj.users.application;

import br.com.fiapaoj.users.data.UserRepository;
import br.com.fiapaoj.users.domain.UserDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserUseCaseApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserUseCaseApplication.class);

	private final UserRepository userRepository;
	private final FindUserUseCaseApplication findUserUseCaseApplication;
	private final DeleteUserUseCaseApplication deleteUserUseCaseApplication;

	public UpdateUserUseCaseApplication(final UserRepository userRepository, //
			final FindUserUseCaseApplication findUserUseCaseApplication, //
			final DeleteUserUseCaseApplication deleteUserUseCaseApplication) {
		this.userRepository = userRepository;
		this.findUserUseCaseApplication = findUserUseCaseApplication;
		this.deleteUserUseCaseApplication = deleteUserUseCaseApplication;
	}

	public void update(final String id, final String name){
		LOGGER.info("m=update(id={}, name={})", id, name);

		final Optional<UserDomain> optionalOfUserDomain = findUserUseCaseApplication.find(id);
		final UserDomain userDomain = optionalOfUserDomain.orElseThrow(() -> buildException(id));
		final Boolean isSucess = deleteUserUseCaseApplication.delete(id);
		userRepository.save(UserDomain.of(id, name, userDomain.getNickname(), userDomain.getCreatedAt()));
	}

	private IllegalArgumentException buildException(final String id) {
		return new IllegalArgumentException(String.format("Não foi encontrado o usuário para o identificador %s", id));
	}
}