package com.klayrocha.helpdesk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.klayrocha.helpdesk.api.repository.UserRepository;
import com.klayrocha.helpdesk.api.security.entity.User;
import com.klayrocha.helpdesk.api.security.enums.ProfileEnum;


@SpringBootApplication
public class HelpDeskApplication {

	public static void main(String[] args) {
	    SpringApplication.run(HelpDeskApplication.class, args);
	}
	
    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            initUsers(userRepository, passwordEncoder);
        };

    }
    
	private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {

	    User admin = new User();
        admin.setEmail("adm@adm");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setProfile(ProfileEnum.ROLE_ADMIN);

        User technician = new User();
        technician.setEmail("tec@tec");
        technician.setPassword(passwordEncoder.encode("123456"));
        technician.setProfile(ProfileEnum.ROLE_TECHNICIAN);

        User user = new User();
        user.setEmail("user@user");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setProfile(ProfileEnum.ROLE_CUSTOMER);

        if ( userRepository.findByEmail( admin.getEmail() ) == null  &&
             userRepository.findByEmail( technician.getEmail() ) == null &&
             userRepository.findByEmail( user.getEmail() ) == null ){

            userRepository.save(admin);
            userRepository.save(technician);
            userRepository.save(user);
        }

	}
}
