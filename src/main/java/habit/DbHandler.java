package habit;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DbHandler {

    private static final String CON_STR = "jdbc:sqlite:habitApp.db";

    private static DbHandler instance = null;

    private Connection connection;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    private DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public void addHabit(Habit habit) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Habit(`name_habit`, `question_habit`) " +
                        "VALUES(?, ?)")) {
            statement.setObject(1, habit.getName());
            statement.setObject(2, habit.getQuestion());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Habit> getAllHabit() {
        try (Statement statement = this.connection.createStatement()) {
            List<Habit> list = new ArrayList<Habit>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Habit");
            while (resultSet.next()) {
                list.add(new Habit(resultSet.getInt("id_habit"),
                        resultSet.getString("name_habit"),
                        resultSet.getString("question_habit"),
                        resultSet.getString("time")));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void updateHabit(int id, String name, String question, String time) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE Habit SET name_habit = ?, question_habit = ?, time = ? WHERE id_habit = ?")) {
            statement.setObject(1, name);
            statement.setObject(2, question);
            statement.setObject(3, time);
            statement.setObject(4, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTimeHabit(int id, String time) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE Habit SET time = ? WHERE id_habit = ?")) {
            statement.setObject(1, time);
            statement.setObject(2, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHabit(int id) {
        try  {
            PreparedStatement statement = this.connection.prepareStatement(
                    "DELETE FROM Habit WHERE id_habit = ?");
            statement.setObject(1, id);
            statement.execute();
            statement = this.connection.prepareStatement(
                    "DELETE FROM Mark WHERE id_habit = ?");
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMark(Mark mark) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Mark(`date_mark`, `id_habit`) " +
                        "VALUES(?, ?)")) {
            statement.setDate(1, new Date(mark.getDate().getTimeInMillis()));
            statement.setInt(2, mark.getIdHabit());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mark> getAllMark(Date date1, Date date2) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM Mark WHERE date_mark <= ? and date_mark >= ?")) {
            statement.setDate(1, date1);
            statement.setDate(2, date2);
            List<Mark> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(resultSet.getDate("date_mark"));
                list.add(new Mark(resultSet.getInt("id_mark"),
                        calendar,
                        resultSet.getInt("id_habit")));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Mark> getAllMarkByHabit(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM Mark WHERE id_habit = ?")) {
            statement.setInt(1, id);
            List<Mark> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(resultSet.getDate("date_mark"));
                list.add(new Mark(resultSet.getInt("id_mark"),
                        calendar,
                        resultSet.getInt("id_habit")));
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void deleteMark(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Mark WHERE id_mark = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
