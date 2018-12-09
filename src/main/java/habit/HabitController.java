package habit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HabitController {

    @RequestMapping("/habits")
    public List<Habit> habits() {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            return dbHandler.getAllHabit();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @RequestMapping("/addHabit")
    public void addHabit(@RequestParam(value="name") String name, @RequestParam(value="question") String question) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.addHabit(new Habit(name, question));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateHabit")
    public void updateHabit(@RequestParam(value="id") int id, @RequestParam(value="name") String name, @RequestParam(value="question") String question) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.updateHabit(id, name, question);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteHabit")
    public void addHabit(@RequestParam(value="id") int id) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.deleteHabit(id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
