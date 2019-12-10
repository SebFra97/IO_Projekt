package ioproj.example.spring.ioproj.db;

import ioproj.example.spring.ioproj.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StudentRow {

    protected StudentRow(){}

    public StudentRow( String name, String number, String group1) {
        this.name = name;
        this.number = number;
        this.group1 = group1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String number;

    private String group1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGroup1() {
        return group1;
    }

    public void setGroup1(String group1) {
        this.group1 = group1;
    }

    public Student toStudent(){
        return new Student(
                this.getId(),
                this.getName(),
                this.getNumber(),
                this.getGroup1());
    }

    public Set<ScoreRow> getScores() {
        return scores;
    }

    public void setScores(Set<ScoreRow> scores) {
        this.scores = scores;
    }

    @OneToMany(mappedBy = "student")
    private Set<ScoreRow> scores;


}
