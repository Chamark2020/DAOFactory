package Lesson_02.dao;

import Lesson_02.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientJDBCDAO implements ClientDAO {

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsshop", "root", "m?5C?Y9Wd#gaW{J@");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Ошибка создания подключения к БД");
        }
        return null;
    }

    @Override
    public void add(Client client) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Client> allClients = getAll();
        try {
            connection = getConnection();
            statement = connection.prepareStatement("insert into clients (LName, Fname, age) values (?, ?, ?)");
            for (Client allClient : allClients) {
                if (allClient.getLName().equals(client.getLName()) && allClient.getFName().equals(client.getFName()) && allClient.getAge() == client.getAge()) {
                    System.out.println("Такой клиент уже присутствует в базе.");
                    return;
                }
            }

            statement.setString(1, client.getLName());
            statement.setString(2, client.getFName());
            statement.setInt(3, client.getAge());

            int clientAdded = statement.executeUpdate();

            System.out.println("База данных клиентов успешно обновлена в количестве - " + clientAdded + " чел.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public List<Client> getAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Client> allClients = new ArrayList<>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from clients");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String LName = rs.getString(2);
                String FName = rs.getString(3);
                int age = rs.getInt(4);
                Client client = new Client();
                client.setId(id);
                client.setLName(LName);
                client.setFName(FName);
                client.setAge(age);
                allClients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return allClients;
    }

    @Override
    public Client getById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from clients where id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int clientId = rs.getInt(1);
                String LName = rs.getString(2);
                String FName = rs.getString(3);
                int age = rs.getInt(4);
                Client client = new Client();
                client.setId(clientId);
                client.setLName(LName);
                client.setFName(FName);
                client.setAge(age);
                return client;
            } else {
                System.out.println("Такого id клиента нет в базе");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void updateAge(int age, int clientId) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement("update clients set age = ? where id = ?");
            statement.setInt(1, age);
            statement.setInt(2, clientId);
            statement.executeUpdate();

            System.out.println("Возраст клиента под номером id = " + clientId + " - успешно обновлен");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось обновить возраст клиента");
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    // Не работает. Не пойму почему
    @Override
    public void remove(int id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("delete from clients where id = ?");
            statement.setInt(1, id);
            int deletedValues = statement.executeUpdate();
            System.out.println("Values deleted: " + deletedValues);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
