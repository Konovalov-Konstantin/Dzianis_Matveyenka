package org.example.java.generics.parametriedmethods2;

public class Runner {
    public static void main(String[] args) {
        Team<Employee> employeeTeam1 = new Team<>("employees1");
        Team<Employee> employeeTeam2 = new Team<>("employees2");
        Team<Student> studentTeam1 = new Team<>("students1");
        Team<Student> studentTeam2 = new Team<>("students2");
        Team<Schoolar> schoolarTeam1 = new Team<>("schoolars1");
        Team<Schoolar> schoolarTeam2 = new Team<>("schoolars2");

        init(employeeTeam1, employeeTeam2, studentTeam1, studentTeam2, schoolarTeam1, schoolarTeam2);

        employeeTeam1.playWith(employeeTeam2);
        schoolarTeam1.playWith(schoolarTeam2);
        studentTeam1.playWith(studentTeam2);
    }

    private static void init(Team<Employee> employeeTeam1, Team<Employee> employeeTeam2, Team<Student> studentTeam1, Team<Student> studentTeam2, Team<Schoolar> schoolarTeam1, Team<Schoolar> schoolarTeam2) {
        for (int i = 0; i < 3; i++) {
            Participant e1 = new Employee("e1", 20);
            Participant e2 = new Employee("e2", 20);
            Participant st1 = new Student("st1", 20);
            Participant st2 = new Student("st2", 20);
            Participant sc1 = new Schoolar("sc1", 20);
            Participant sc2 = new Schoolar("sc2", 20);
            employeeTeam1.add((Employee) e1);
            employeeTeam2.add((Employee) e2);
            studentTeam1.add((Student) st1);
            studentTeam2.add((Student) st2);
            schoolarTeam1.add((Schoolar) sc1);
            schoolarTeam2.add((Schoolar) sc2);
        }
    }
}
