package habit;

public class Habit {

    private int id;
    private String name;
    private String question;
    private String time;

    public Habit(int id, String name, String question, String time) {
        this.id = id;
        this.name = name;
        this.question = question;
        this.time = time;
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
}