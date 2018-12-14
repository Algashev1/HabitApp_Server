package habit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@RestController
public class MarkController {

    @RequestMapping("/marks")
    public List<Mark> marks(@RequestParam(value = "year") int year, @RequestParam(value = "month") int month,
                            @RequestParam(value = "day") int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date d1 = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.DATE, -5);
        Date d2 = new Date(calendar.getTimeInMillis());

        try {
            DbHandler dbHandler = DbHandler.getInstance();
            return dbHandler.getAllMark(d1, d2);

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @RequestMapping("/marksByHabit")
    public List<Mark> marks(@RequestParam(value = "id") int id) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            return dbHandler.getAllMarkByHabit(id);

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @RequestMapping("/addMark")
    public void addMark(@RequestParam(value = "id") int id, @RequestParam(value = "year") int year,
                        @RequestParam(value = "month") int month, @RequestParam(value = "day") int day) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.addMark(new Mark(calendar, id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteMark")
    public void addHabit(@RequestParam(value = "id") int id) {
        try {
            DbHandler dbHandler = DbHandler.getInstance();
            dbHandler.deleteMark(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
