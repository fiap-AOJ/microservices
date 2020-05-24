package br.com.fiapaoj.users.controllers;

import br.com.fiapaoj.users.application.*;
import br.com.fiapaoj.users.domain.UserDomain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.collections.keyvalue.AbstractMapEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@Api( //
	  value = "/v1/users",//
	  consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,//
	  tags = { "Usuários" })
@RestController
@RequestMapping(value = "/v1/users", //
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final AddUserUseCaseApplication addUserUseCaseApplication;
	private final FindUserUseCaseApplication findUserUseCaseApplication;
	private final UpdateUserUseCaseApplication updateUserUseCaseApplication;
	private final DeleteUserUseCaseApplication deleteUserUseCaseApplication;
	private final MetricsCreatedUsersUseCaseApplication metricsCreatedUsersUseCaseApplication;
	private final Converter<UserDomain, UserDto> userDomainToUserDtoConverter;

	public UserController(final AddUserUseCaseApplication addUserUseCaseApplication, //
			final FindUserUseCaseApplication findUserUseCaseApplication, //
			final UpdateUserUseCaseApplication updateUserUseCaseApplication, //
			final DeleteUserUseCaseApplication deleteUserUseCaseApplication, //
			final MetricsCreatedUsersUseCaseApplication metricsCreatedUsersUseCaseApplication, //
			final Converter<UserDomain, UserDto> userDomainToUserDtoConverter) {
		this.addUserUseCaseApplication = addUserUseCaseApplication;
		this.findUserUseCaseApplication = findUserUseCaseApplication;
		this.updateUserUseCaseApplication = updateUserUseCaseApplication;
		this.deleteUserUseCaseApplication = deleteUserUseCaseApplication;
		this.metricsCreatedUsersUseCaseApplication = metricsCreatedUsersUseCaseApplication;
		this.userDomainToUserDtoConverter = userDomainToUserDtoConverter;
	}

	@ApiOperation(value = "Consultar métrica de usuários cadastrados")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Solicitação realizada com sucesso"), //
			@ApiResponse(code = 500, message = "Ocorreu algum erro inesperado") })
	@GetMapping("/metrics")
	public ResponseEntity<Map.Entry<String, Long>> getMetrics(){
		LOGGER.info("m=getMetrics()");

		final Long createdUsersCount = metricsCreatedUsersUseCaseApplication.count();
		final AbstractMap.SimpleEntry<String, Long> entryOfCreatedUsersCount = new AbstractMap.SimpleEntry<>("createdUsersCount", createdUsersCount);

		return ok(entryOfCreatedUsersCount);
	}

	@ApiOperation(value = "Consultar todos os usuários cadastrados")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Solicitação realizada com sucesso"), //
			@ApiResponse(code = 500, message = "Ocorreu algum erro inesperado") })
	@GetMapping
	public ResponseEntity<Set<UserDto>> findAll(){
		LOGGER.info("m=findAll()");

		final Set<UserDto> users = findUserUseCaseApplication.findAll()
				.stream() //
				.map(userDomainToUserDtoConverter::convert) //
				.collect(Collectors.toSet());
		return ok(users);
	}

	@ApiOperation(value = "Consultar um usuário cadastrado")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Solicitação realizada com sucesso"), //
			@ApiResponse(code = 204, message = "Usuário solicitado não encontrado"), //
			@ApiResponse(code = 500, message = "Ocorreu algum erro inesperado") })
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> find(@PathVariable("id") final String id){
		LOGGER.info("m=find(id={})", id);

		final Optional<UserDomain> optionalOfUserDomain = findUserUseCaseApplication.find(id);
		return optionalOfUserDomain //
				.map(userDomainToUserDtoConverter::convert) //
				.map(ResponseEntity::ok) //
				.orElse(buildNoContentResponse());
	}

	private ResponseEntity<UserDto> buildNoContentResponse() {
		return noContent().build();
	}

	@ApiOperation(value = "Cadastrar um usuário")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Solicitação realizada com sucesso"), //
			@ApiResponse(code = 500, message = "Ocorreu algum erro inesperado") })
	@PostMapping
	public ResponseEntity<String> add(@RequestParam("name") final String name, @RequestParam("nickname") final String nickname){
		LOGGER.info("m=add(name={}, nickanme={})", name, nickname);

		final String id = addUserUseCaseApplication.add(name, nickname);
		return ok(id);
	}

	@ApiOperation(value = "Atualizar um usuário cadastrado")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Solicitação realizada com sucesso"), //
			@ApiResponse(code = 500, message = "Ocorreu algum erro inesperado") })
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") final String id, @RequestParam("name") final String name){
		LOGGER.info("m=update(id={}, name={})", id, name);

		updateUserUseCaseApplication.update(id, name);
		return ok().build();
	}

	@ApiOperation(value = "Remover um usuário cadastrado")
	@ApiResponses(value = { //
			@ApiResponse(code = 200, message = "Solicitação realizada com sucesso"), //
			@ApiResponse(code = 422, message = "Não foi possível processar a silicitação"), //
			@ApiResponse(code = 500, message = "Ocorreu algum erro inesperado") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") final String id) {
		LOGGER.info("m=delete(id={})", id);

		final Boolean isSuccess = deleteUserUseCaseApplication.delete(id);
		if(isSuccess){
			return ok()
					.build();
		}

		return unprocessableEntity()
				.build();
	}
}