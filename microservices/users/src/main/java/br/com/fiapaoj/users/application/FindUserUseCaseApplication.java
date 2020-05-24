package br.com.fiapaoj.users.application;

import br.com.fiapaoj.users.data.UserRepository;
import br.com.fiapaoj.users.domain.UserDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FindUserUseCaseApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(FindUserUseCaseApplication.class);

	private final UserRepository userRepository;

	public FindUserUseCaseApplication(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Optional<UserDomain> find(final String id){
		LOGGER.info("m=find(id={})", id);

		return userRepository.findById(id);
	}

	public Set<UserDomain> findAll(){
		LOGGER.info("m=findAll()");

		return userRepository.findAll().stream().collect(Collectors.toSet());
	}
}