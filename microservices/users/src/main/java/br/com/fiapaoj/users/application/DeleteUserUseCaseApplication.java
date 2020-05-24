package br.com.fiapaoj.users.application;

import br.com.fiapaoj.users.data.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserUseCaseApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserUseCaseApplication.class);

	private final UserRepository userRepository;

	public DeleteUserUseCaseApplication(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Boolean delete(final String id){
		LOGGER.info("m=delete(id={})", id);
		try {
			final Boolean exists = userRepository.existsById(id);
			if(exists){
				userRepository.deleteById(id);
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		}catch (Exception exception){
			LOGGER.error("exception(message={}, cause={})", exception.getMessage(), exception);
			return Boolean.FALSE;
		}
	}
}