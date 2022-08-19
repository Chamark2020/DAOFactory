package Lesson_02;

import Lesson_02.dao.CarDAO;
import Lesson_02.dao.ClientDAO;
import Lesson_02.dao.DAOFactory;
import Lesson_02.dao.IDAOFactory;
import Lesson_02.entity.Car;
import Lesson_02.entity.Client;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IDAOFactory factory = DAOFactory.getInstance();
        CarDAO carDAO = factory.getCarDAO();

//        List<Car> list = carDAO.getAll();
//        for (Car car : list) {
//            System.out.println(car.getId() + " | " + car.getMark() + " | " + car.getModel() + " | " + car.getEngine_volume() +
//                    " | " + car.getCost() + " | " + car.getSpeed());
//        }

//       Car car = carDAO.getById(1);
//       System.out.println(car.getId() + " | " + car.getMark() + " | " + car.getModel() + " | " + car.getEngine_volume() +
//                    " | " + car.getCost() + " | " + car.getSpeed());
//
//       carDAO.updatePrice(14000, 1);
//
//       carDAO.remove(1);

        ClientDAO clientDAO = factory.getClientDAO();

//        List<Client> list = clientDAO.getAll();
//        for (Client client : list) {
//            System.out.println(client.getId() + " | " + client.getLName() + " | " + client.getFName() + " | " + client.getAge());
//        }

        Client client = new Client();
//        client.setAge(26);
//        client.setLName("Ivanov");
//        client.setFName("Ivan");
//        clientDAO.add(client);

        clientDAO.remove(2);
//        carDAO.remove(3);
//        clientDAO.updateAge(10, 2);
//        client = clientDAO.getById(4);
//        System.out.println(client.getId() + " | " + client.getLName() + " | " + client.getFName() + " | " + client.getAge());



    }
}
