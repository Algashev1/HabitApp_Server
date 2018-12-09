package habit;

import java.util.Calendar;

public class Mark {
    private int id;
    private Calendar date;
    private int idHabit;

    Mark (int id, Calendar date, int idHabit) {
        this.id = id;
        this.date = date;
        this.idHabit = idHabit;
    }

    Mark ( Calendar date, int idHabit) {
        this.date = date;
        this.idHabit = idHabit;
    }

    public int getId() {
        return id;
    }

    public Calendar getDate() {
        return date;
    }

    public int getIdHabit() {
        return idHabit;
    }
}
