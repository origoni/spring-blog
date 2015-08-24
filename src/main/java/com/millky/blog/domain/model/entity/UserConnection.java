package com.millky.blog.domain.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.millky.blog.domain.model.entity.UserConnection.ProjectId;

import lombok.Data;

@Data
@Entity
@IdClass(ProjectId.class)
@Table(name = "USERCONNECTION", uniqueConstraints = {
		@UniqueConstraint(name = "UserConnectionRank", columnNames = { "userId", "providerId", "rank" }),
		@UniqueConstraint(name = "providerUser", columnNames = { "providerId", "providerUserId" }) })
public class UserConnection {
	@Id
	@GeneratedValue
	@Column(name = "USERCONNECTIONIDX", nullable = false)
	private int userConnectionIdx;

	@Id
	@Column(name = "USERID")
	private String userId;

	@Id
	@Column(name = "PROVIDERID")
	private String providerId;

	@Id
	@Column(name = "PROVIDERUSERID")
	private String providerUserId;

	@Column(name = "RANK")
	private int rank;

	@Column(name = "DISPLAYNAME")
	private String displayName;

	@Column(name = "PROFILEURL", length = 512)
	private String profileUrl;

	@Column(name = "IMAGEURL", length = 512)
	private String imageUrl;

	@Column(name = "ACCESSTOKEN")
	private String accessToken;

	@Column(name = "SECRET")
	private String secret;

	@Column(name = "REFRESHTOKEN")
	private String refreshToken;

	@Column(name = "EXPIRETIME")
	private Long expireTime;

	@Data
	static class ProjectId implements Serializable {
		private static final long serialVersionUID = 1L;

		String userId;
		String providerId;
		String providerUserId;
	}
}
