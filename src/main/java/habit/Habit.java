package habit;

public class Habit {

    private int id;
    private String name;
    private String question;

    public Habit(int id, String name, String question) {
        this.id = id;
        this.name = name;
        this.question = question;
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
}