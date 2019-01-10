package com.vishal.demo.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User {
	
	@Id
	@Column(name = "emailId" , nullable = false)
	private String emailId;
	private String password;
	
	@JsonIgnore
	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Role> roleList = new ArrayList<>();

	public User(){
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void addRole(Role role){
		this.roleList.add(role);
	}

	public List<GrantedAuthority> getAuthorities(){
		List<GrantedAuthority> authorities = new ArrayList<>();

		for (Role role: this.getRoleList()){
			authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
		}

		return authorities;
	}

	public boolean hasRole(Role role){
		return getAuthorities().contains(new SimpleGrantedAuthority(role.getName().toString()));
	}

	@Override
	public String toString() {
		return "User{" +
				"emailId='" + emailId + '\'' +
				", password='" + password + '\'' +
				", roleList=" + roleList +
				'}';
	}
}
