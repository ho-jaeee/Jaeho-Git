package com.jaeho.CRUD_project;

import com.jaeho.CRUD_project.entity.Post;
import com.jaeho.CRUD_project.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudProjectApplication {

	private final PostRepository postRepository;

	public CrudProjectApplication(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(CrudProjectApplication.class, args);
	}



}
