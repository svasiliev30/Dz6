import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.sqlite.SQLiteException;
import sbp.dao.ProductRepository;
import sbp.sqlTable.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestCreateTable {

    private final String jdbcUrl = "jdbc:sqlite:C:\\work\\learn programmer\\data base\\sql_lite_dz.db";

    /**
     * Create table with id, name, age, city.
     * @throws SQLException
     */
    @Test
    void createTable()  {
        String sql = "CREATE TABLE person (" +
                "id VARCHAR(50)," +
                "name VARCHAR(50)," +
                "age VARCHAR(50)," +
                "city VARCHAR(100)" +
                ");";

        try(Connection connection = DriverManager.getConnection(jdbcUrl);) {

            ProductRepository repository = new ProductRepository(connection);
            Assertions.assertTrue(repository.createTable(sql));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Error create table person.Table exists
     * @throws SQLException
     */
    @Test
    void createTableThrows()  {
        String sql = "CREATE TABLE person (" +
                "id VARCHAR(50)," +
                "name VARCHAR(50)," +
                "age VARCHAR(50)," +
                "city VARCHAR(100)" +
                ");";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);) {

            ProductRepository repository = new ProductRepository(connection);
            Assertions.assertFalse(repository.createTable(sql));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Added information to the table. Table person creating/
     * @throws SQLException
     */
    @Test
    void addInformation() throws SQLException {

        try(Connection connection = DriverManager.getConnection(jdbcUrl);) {

            ProductRepository repository = new ProductRepository(connection);

            List<Person> list = new ArrayList<>();
            list.add(new Person(1, "Дурин", "Кхазад-Дум", 160, "person"));
            list.add(new Person(2, "Торин", "Эребор", 99, "person"));
            list.add(new Person(3, "Трар", "Эребор", 120, "person"));
            list.add(new Person(4, "Трор", "Эребор", 138, "person"));
            list.add(new Person(5, "Дурин6", "Кхазад-Дум", 218, "person"));

            for (int i = 0; i < list.size(); i++) {
                Assertions.assertTrue(repository.addPerson(list.get(i)));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Added information to the table. Table person not created
     * @throws SQLException
     */
    @Test
    void addInformationError() throws SQLException {

       try( Connection connection = DriverManager.getConnection(jdbcUrl);) {

           ProductRepository repository = new ProductRepository(connection);
           Person person = new Person(1, "Дурин6", "Кхазад-Дум", 218, "person123");

           Assertions.assertFalse(repository.addPerson(person));

       }catch (SQLException e){
           e.printStackTrace();
       }
    }

    /**
     * Changing information to new.
     * @throws SQLException
     */
    @Test
    void changeName () throws SQLException {

        try (Connection connection = DriverManager.getConnection(jdbcUrl);) {

            ProductRepository repository = new ProductRepository(connection);
            Assertions.assertTrue(repository.changeAge(2, 119));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Changing information to new.Error input information
     * @throws SQLException
     */
    @Test
    void changeNameError () throws SQLException {

        try (Connection connection = DriverManager.getConnection(jdbcUrl);) {

            ProductRepository repository = new ProductRepository(connection);
            Assertions.assertFalse(repository.changeAge(150, 118));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Delete table.
     * @throws SQLException
     */
    @Test
    void deleteTable() throws SQLException {

        String nameTable = "DROP TABLE person";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);) {

            ProductRepository repository = new ProductRepository(connection);
            Assertions.assertTrue(repository.deleteTable(nameTable));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * Delete table.Will be name table error.
     * @throws SQLException
     */
    @Test
    void deleteTableError() throws SQLException {

        String nameTable = "DROP TABLE person12345";

        try (Connection connection = DriverManager.getConnection(jdbcUrl);) {

            ProductRepository repository = new ProductRepository(connection);
            Assertions.assertFalse(repository.deleteTable(nameTable));

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

