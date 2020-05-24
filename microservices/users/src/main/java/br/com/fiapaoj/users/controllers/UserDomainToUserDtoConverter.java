package br.com.fiapaoj.users.controllers;

import br.com.fiapaoj.users.domain.UserDomain;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class UserDomainToUserDtoConverter implements Converter<UserDomain, UserDto> {

	@Override
	public UserDto convert(final UserDomain source) {
		final UserDto userDto = new UserDto();
		userDto.setId(source.getId());
		userDto.setName(source.getName());
		userDto.setNickname(source.getNickname());
		userDto.setCreatedAt(source.getCreatedAt());

		return userDto;
	}
}