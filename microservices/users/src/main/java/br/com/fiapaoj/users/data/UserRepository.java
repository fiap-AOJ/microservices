package br.com.fiapaoj.users.data;

import br.com.fiapaoj.users.domain.UserDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDomain, String> { }