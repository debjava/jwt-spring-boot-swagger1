package com.ddlab.rnd;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.User;
import com.ddlab.rnd.entity.UserDTO;
import com.ddlab.rnd.role.Role;
import com.ddlab.rnd.service.UserAuthService;

@Component
public class BootStrapInitializer implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private UserAuthService userAuthService;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		ModelMapper mapper = new ModelMapper();
		UserDTO user = new UserDTO();
		user.setEmail("deba.java@gmail.com");
		user.setId(123);
		user.setPassword("Deba@1234");
		user.setUsername("deba");
		user.setRoles(Arrays.asList(Role.ROLE_ADMIN));
		userAuthService.signup(mapper.map(user, User.class));
	}

}
