package sbp.dao;

import sbp.sqlTable.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository {

    private final Connection connection;

    /**
     * Takes a parameter.
     * @param connection
     */
    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Takes an information of table.Create new table.
     * @param informationTable
     * @return
     */
    public boolean createTable(String informationTable) {

        String sql = informationTable;

        try(Statement statement = connection.createStatement();) {
            int affectedRows = statement.executeUpdate(sql);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * Takes an information of person and name table. Added this information to table.
     * @param person
     * @return
     */
    public boolean addPerson (Person person){

       try (Statement statement = connection.createStatement();){

           String sql = String.format("INSERT INTO %s ('id', 'name', 'age', 'city') VALUES (%s, '%s', %s, '%s')",
                   person.getNameTable(), person.getId(), person.getName(), person.getAge(), person.getCity());

           boolean hasResultSet = statement.execute(sql);
           int affectedRows = statement.getUpdateCount();
           System.out.println( "affectedRows = " + affectedRows);

       }catch (SQLException e) {
           return false;
       }
        return true;
    }

    /**
     * Takes an age of person and id.Change Age on new age.
     * @param id
     * @param Age
     * @return
     */
    public boolean changeAge (int id, int Age) {

        try (Statement statement = connection.createStatement();){

           int affectedRows = statement.executeUpdate("UPDATE person SET age = " + Age + " WHERE id = " + id +";");
           System.out.println( "affectedRows = " + affectedRows);

            if (affectedRows == 0 ){
                return false;
            }

        }catch (SQLException e){
            return false;
        }
    return true;
    }

    /**
     * Takes a name table and delete his.
     * @param nameTable
     * @return
     */
    public boolean deleteTable(String nameTable){

        String sqlNameTable = nameTable;

       try (Statement statement = connection.createStatement();){
           statement.executeUpdate(sqlNameTable);
       } catch (SQLException e) {
           e.printStackTrace();
           return false;
       }
       return true;
    }
}

