package com.products.demo.api.v1.local.api_franchise.logs_franchise.adapters.bd1;


import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name = "logs_francchise" )
public class LogFranchise {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Integer id;

	private Integer user_id;
	private String action;
	private Timestamp created;
	private Integer code;
	private String message;
	private String description;
	private String class_path;
	private String ip;
	private String url;
	private String method;
	private String payload;

	public LogFranchise(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClass_path() {
		return class_path;
	}

	public void setClass_path(String class_path) {
		this.class_path = class_path;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "LogTask [id=" + id + ", user_id=" + user_id + ", action=" + action + ", created=" + created + ", code="
				+ code + ", message=" + message + ", description=" + description + ", class_path=" + class_path
				+ ", ip=" + ip + ", url=" + url + ", method=" + method + ", payload=" + payload + "]";
	}
}
