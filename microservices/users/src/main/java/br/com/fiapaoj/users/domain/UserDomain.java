package br.com.fiapaoj.users.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;
import java.util.UUID;

public class UserDomain {

	private String id;

	private String name;

	private String nickname;

	private Date createdAt;

	private UserDomain(final String name, final String nickname){
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.nickname = nickname;
		this.createdAt = new Date();
	}

	private UserDomain(final String id, final String name, final String nickname, final Date createdAt){
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.createdAt = createdAt;
	}

	public static UserDomain of(final String name, final String nickname){
		return new UserDomain(name, nickname);
	}

	public static UserDomain of(final String id, final String name, final String nickname, final Date createdAt){
		return new UserDomain(id, name, nickname, createdAt);
	}

	public UserDomain(){}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}