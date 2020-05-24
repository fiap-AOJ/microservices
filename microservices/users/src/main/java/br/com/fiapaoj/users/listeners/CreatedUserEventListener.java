package br.com.fiapaoj.users.listeners;


import br.com.fiapaoj.users.application.MetricsCreatedUsersUseCaseApplication;
import br.com.fiapaoj.users.domain.UserDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class CreatedUserEventListener extends AbstractMongoEventListener<UserDomain> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreatedUserEventListener.class);

	private final MetricsCreatedUsersUseCaseApplication metricsCreatedUsersUseCaseApplication;

	public CreatedUserEventListener(final MetricsCreatedUsersUseCaseApplication metricsCreatedUsersUseCaseApplication) {
		this.metricsCreatedUsersUseCaseApplication = metricsCreatedUsersUseCaseApplication;
	}

	@Override
	public void onAfterSave(final AfterSaveEvent<UserDomain> event) {
		LOGGER.info("m=onAfterSave(event={})", event);

		final UserDomain userDomain = event.getSource();
		LOGGER.info("Novo usuário criado {}", userDomain.getNickname());
		metricsCreatedUsersUseCaseApplication.increment();
	}
}
