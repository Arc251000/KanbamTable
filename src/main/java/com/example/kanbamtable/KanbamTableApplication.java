package com.example.kanbamtable;

import com.example.kanbamtable.model.entity.News;
import com.example.kanbamtable.model.entity.UserAccount;
import com.example.kanbamtable.repository.NewsJPARepository;
import com.example.kanbamtable.repository.UserAccountJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KanbamTableApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KanbamTableApplication.class, args);
	}



	@Autowired
	UserAccountJPARepository ur;

	@Autowired
	NewsJPARepository nr2;

	@Autowired
	PasswordEncoder bc;

	@Override
	public void run(String... args) throws Exception {


		UserAccount u1 = new UserAccount();
		u1.setEmail("arianbrv2510@gmail.com");
		u1.setName("admin");
		u1.setPassword(bc.encode("password"));
		u1.setRole("ROLE_ADMIN");
		ur.save(u1);


		UserAccount u2 = new UserAccount();
		u1.setEmail("arianbrv25101@gmail.com");
		u1.setName("Usuario2");
		u1.setPassword(bc.encode("password"));
		u1.setRole("ROLE_ADMIN");
		ur.save(u1);


		UserAccount u3 = new UserAccount();
		u1.setEmail("arianbrv25102@gmail.com");
		u1.setName("Usuario3");
		u1.setPassword(bc.encode("password"));
		u1.setRole("ROLE_ADMIN");
		ur.save(u1);

		News news = new News();
		news.setText("Bienvenido a KanbanTable");
		news.setImgUrl("sdgdfh");
		nr2.save(news);
	}
}
