package ru.otus.spring.exam.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.exam.domain.Student;
import ru.otus.spring.exam.service.ExamService;

@ShellComponent
@RequiredArgsConstructor
public class ExamApplicationCommands {
    private final ExamService examService;
    private String firstName;
    private String lastName;

    @ShellMethod(value = "Enter first name", key = {"n", "name"})
    public String setFirstName(@ShellOption(defaultValue = "Unknown") String firstName) {
        this.firstName = firstName;
        return String.format("Your name is %s", this.firstName);
    }

    @ShellMethod(value = "Enter last name", key = {"s", "surname"})
    public String setLastName(@ShellOption(defaultValue = "Unknown") String lastName) {
        this.lastName = lastName;
        return String.format("Your surname is %s", this.lastName);
    }

    @ShellMethod(value = "Take exam", key = {"e", "exam"})
    @ShellMethodAvailability(value = "isExamAvailable")
    public void takeExam() {
        Student student = new Student(lastName, firstName);
        examService.run(student);
    }

    private Availability isExamAvailable() {
        if (this.lastName != null && this.firstName != null) {
            return Availability.available();
        }
        return Availability.unavailable("You must enter your name and surname first");
    }

}
