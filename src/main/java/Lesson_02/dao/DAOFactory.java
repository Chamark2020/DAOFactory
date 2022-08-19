package Lesson_02.dao;



public class DAOFactory implements IDAOFactory{

    private static IDAOFactory factory;

    private DAOFactory() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Драйвер успешно загружен");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized IDAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }

    @Override
    public CarDAO getCarDAO() {
        return new CarJDBCDAO();
    }

    @Override
    public ClientDAO getClientDAO() {
        return new ClientJDBCDAO();
    }
}
