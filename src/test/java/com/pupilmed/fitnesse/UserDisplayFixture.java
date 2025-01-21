package com.pupilmed.fitnesse;

import com.pupilmed.pupilmedapi.model.User;
import fit.RowFixture;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

public class UserDisplayFixture extends RowFixture {
    private List<User> users;

    @Override
    public Object[] query() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // URL endpointu
        String url = "http://localhost:8080/user/findAll";

        // Ustawienie nagłówków autoryzacji
        HttpHeaders headers = new HttpHeaders();
        String credentials = "111111111:haslo1"; // login:haslo
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        headers.set("Authorization", "Basic " + encodedCredentials);

        // Utworzenie obiektu HTTP Entity z nagłówkami
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Wykonanie żądania GET z autoryzacją
        ResponseEntity<User[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, User[].class);

        // Zwrócenie odpowiedzi jako tablicy
        return response.getBody();
    }

    @Override
    public Class<?> getTargetClass() {
        return User.class;
    }
}
