package org.example.SpringProject;

import org.example.SpringProject.configuration.Config;
import org.example.SpringProject.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

//@SpringBootApplication
public class SpringProjectApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringProjectApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        System.out.println("    Get all users");
        Communication communication = context.getBean("communication", Communication.class);
        List<User> allUsers = communication.getAllUsers();
        System.out.println(allUsers);
        System.out.println();

        System.out.println("    Get user by id");
        User singleUser = communication.getUser(1);
        System.out.println(singleUser);

//        User userJames = new User(3L, "James", "Brown", (byte) 17);
//        communication.saveUser(userJames);
//        System.out.println();

//        communication.deleteUser(2L);
        communication.getHeader();
    }
}
