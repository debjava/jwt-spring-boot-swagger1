package com.ddlab.rnd.entity;

import java.util.List;

import com.ddlab.rnd.role.Role;

import lombok.Data;

@Data
public class UserDTO {

	private Integer id;

	private String username;

	private String email;

	private String password;

	List<Role> roles;
}
