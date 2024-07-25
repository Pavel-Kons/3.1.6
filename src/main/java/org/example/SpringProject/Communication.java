package org.example.SpringProject;

import org.example.SpringProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    private final RestTemplate restTemplate;
    private final String url = "http://94.198.50.185:7081/api/users/";

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getHeader() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> forEntity = template.getForEntity("http://google.bg", String.class);
        forEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        System.out.println();
        System.out.println(forEntity.getHeaders().getFirst("Set-Cookie"));
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        return responseEntity.getBody();
    }

    public User getUser(long id) {
//        return restTemplate.getForObject(url + id, User.class);
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<User>>() {
                        });
        List<User> users = responseEntity.getBody();
        return users.get((int) id);
    }

    public void saveUser(User user) {
        long id = user.getId();
        if (id == 0) {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(url, user, String.class);
            System.out.println("New user added");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(url, user);
            System.out.println("User with id: " + id + " updated");
        }
    }

    public void deleteUser(Long id) {
        restTemplate.delete(url, id);
        System.out.println("User with id " + id + " deleted");
    }
}
