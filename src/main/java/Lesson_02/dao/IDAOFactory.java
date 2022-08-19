package Lesson_02.dao;

public interface IDAOFactory {
    CarDAO getCarDAO();

    ClientDAO getClientDAO();
}
