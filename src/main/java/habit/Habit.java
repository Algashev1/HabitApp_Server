package habit;

import java.util.Calendar;

public class Habit {

    private int id;
    private String name;
    private String question;
    private String time;
    private Calendar creationDate;

    public Habit(int id, String name, String question, String time, Calendar creationDate) {
        this.id = id;
        this.name = name;
        this.question = question;
        this.time = time;
        this.creationDate = creationDate;
    }

    public Habit(String name, String question) {
        this.name = name;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }

    public String getTime() {
        return time;
    }

    public Calendar getCreationDate() {
        return creationDate;
    }
}