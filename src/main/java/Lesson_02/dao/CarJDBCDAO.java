package Lesson_02.dao;

import Lesson_02.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarJDBCDAO implements CarDAO {

    @Override
    public void add(Car car) {
        Connection connection = null;

        connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("insert into cars(mark, model, engine_volume, cost, max_speed) values(?,?,?,?,?)");
            statement.setString(1, car.getMark());
            statement.setString(2, car.getModel());
            statement.setDouble(3,car.getEngine_volume());
            statement.setInt(4, car.getCost());
            statement.setInt(5, car.getSpeed());
            statement.execute();

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

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carsshop", "root", "m?5C?Y9Wd#gaW{J@");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Car> getAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Car> allCars = new ArrayList<>();
        try {
            connection = getConnection();
            statement = connection.prepareStatement("select * from cars");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String mark = resultSet.getString(2);
                String model = resultSet.getString(3);
                double engine_volume = resultSet.getDouble(4);
                int cost = resultSet.getInt(5);
                int speed = resultSet.getInt(6);
                Car car = new Car();
                car.setId(id);
                car.setMark(mark);
                car.setModel(model);
                car.setEngine_volume(engine_volume);
                car.setCost(cost);
                car.setSpeed(speed);
                allCars.add(car);

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
        return allCars;
    }

    @Override
    public Car getById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();

            statement = connection.prepareStatement("select * from cars where id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int carId = rs.getInt(1);
                String mark = rs.getString(2);
                String model = rs.getString(3);
                double engine_volume = rs.getDouble(4);
                int cost = rs.getInt(5);
                int speed = rs.getInt(6);
                Car car = new Car();
                car.setId(carId);
                car.setMark(mark);
                car.setModel(model);
                car.setEngine_volume(engine_volume);
                car.setCost(cost);
                car.setSpeed(speed);
                return car;
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
    public void updatePrice(int price, int carId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();

            statement = connection.prepareStatement("update cars set cost = ? where id = ?");
            statement.setInt(1, price);
            statement.setInt(2, carId);
            int updatedValues = statement.executeUpdate();
            System.out.println("Values updated: " + updatedValues);
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
    public void remove(int id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("delete from cars where id = ?");
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
