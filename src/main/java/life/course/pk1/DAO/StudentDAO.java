package life.course.pk1.DAO;

import life.course.pk1.Mappers.StudentMapper;
import life.course.pk1.Models.Student;
import life.course.pk1.Utill.StudentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StudentDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public StudentDAO(){
        jdbcTemplate = null;
    }

    public int addStudent(Student student){
        return jdbcTemplate.update("INSERT INTO students(email, fio, faculty, department, groupe, password) VALUES(?, ?, ?, ?, ?, ?)",
                student.getEmail(), student.getFio(), student.getFaculty(), student.getDepartment(), student.getGroupe(), student.getPassword());
    }
    public Student getUser(int id){
        return jdbcTemplate.query("SELECT * FROM students WHERE id = ?",  new Object[]{id}, new StudentMapper())
                .stream().findAny().orElseThrow(StudentNotFoundException::new);
    }
}
