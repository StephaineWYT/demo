package wen.demo.controller;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wen.demo.entity.Student;
import wen.demo.repository.StudentRepo;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping(value = "/hello")
    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    @RequestMapping(value = "/insert")
    public List<Student> insert(@RequestParam("name") String name, @RequestParam("age") String age) {
        System.out.println("method insert()");

        Student student = new Student();
        student.setName(name);
        student.setAge(age);

        studentRepo.save(student);
        return studentRepo.findAll();
    }

    @RequestMapping("/findById")
    public Student findById(@RequestParam("id") Integer id) {
        return studentRepo.findById(id).orElse(null);
    }

    @RequestMapping("/findByName")
    public List<Student> findByName(@RequestParam("name") String name) {
        return studentRepo.findByName(name);
    }

    @RequestMapping("/deleteByName")
    public List<Student> deleteByName(String name) {
        System.out.println("method deleteByName()");

        List<Student> studentList = studentRepo.findByName(name);
        if (null != studentList) {
            for (int i = 0; i < studentList.size(); i++) {
                studentRepo.delete(studentList.get(i));
            }
        }
        return studentRepo.findAll();
    }

    @RequestMapping(value = "addMoreStudents")
    public List<Student> addMoreStudents(@RequestParam("students") String students) {
        System.out.println("method addMoreStudents()");

        List<Student> studentList = JSONArray.parseArray(students, Student.class);
        if (null != studentList) {
            for (int i = 0; i < studentList.size(); i++) {
                insert(studentList.get(i).getName(), studentList.get(i).getAge());
            }
        }
        return studentRepo.findAll();
    }

}
