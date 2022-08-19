package Lesson_02.dao;

import Lesson_02.entity.Car;
import Lesson_02.entity.Client;

import java.util.List;

public interface ClientDAO {
    void add(Client client);

    List<Client> getAll();

    Client getById(int id);

    void updateAge(int age, int clientId);

    void remove(int id);
}
