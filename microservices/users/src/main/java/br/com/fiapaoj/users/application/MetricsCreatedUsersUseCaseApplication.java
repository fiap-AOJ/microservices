package br.com.fiapaoj.users.application;

import br.com.fiapaoj.users.data.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class MetricsCreatedUsersUseCaseApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MetricsCreatedUsersUseCaseApplication.class);

	public static final AtomicLong count;
	static{
		count = new AtomicLong(0l);
	}

	private final UserRepository userRepository;

	public MetricsCreatedUsersUseCaseApplication(final UserRepository userRepository) {
		this.userRepository = userRepository;
		initializeMetrics();
	}

	private void initializeMetrics() {
		count.addAndGet(userRepository.count());
	}

	public final void increment(){
		count.incrementAndGet();
	}

	public final Long count(){
		return count.get();
	}
}
