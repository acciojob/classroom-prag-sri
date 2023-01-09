package com.driver;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    private HashMap<String,Student> studentDb;
    private HashMap<String,Teacher> teacherDb;
    private HashMap<String,List<String>> teacherStudentDb;

    public StudentRepository(HashMap<String, Student> studentDb, HashMap<String, Teacher> teacherDb, HashMap<String, List<String>> teacherStudentDb) {
        this.studentDb = studentDb;
        this.teacherDb = teacherDb;
        this.teacherStudentDb = teacherStudentDb;
    }

    void addStudent(Student student){
        String key= student.getName();
        studentDb.put(key,student);
    }

    void addTeacher(Teacher teacher){
        String key= teacher.getName();
        teacherDb.put(key,teacher);
    }

    void addStudentTeacherPair(String studentName, String teacherName){
        List<String> students= new ArrayList<>();
        if(studentDb.containsKey(studentName) && teacherDb.containsKey(teacherName))
        {
            if(teacherStudentDb.containsKey(teacherName))
            {
                students= teacherStudentDb.get(teacherName);
            }
            students.add(studentName);
            teacherStudentDb.put(teacherName,students);
        }
    }

    Student getStudentByName(String name){
        return studentDb.get(name);
    }

    Teacher getTeacherByName(String name){
        return teacherDb.get(name);
    }

    List<String> getStudentsByTeacherName(String name){
        List<String> students= new ArrayList<>();
        if(teacherStudentDb.containsKey(name))
            students= teacherStudentDb.get(name);
        return students;
    }

    List<String> getAllStudents(){
        List<String> result= new ArrayList<>();
        for(Student s: studentDb.values())
        {
            result.add(s.getName());
        }
        return result;
    }

    void deleteTeacherByName(String teacher){
        List<String> students= teacherStudentDb.get(teacher);
        for(int i=0; i<students.size(); i++)
        {
            studentDb.remove(students.get(i));
        }
        teacherStudentDb.remove(teacher);
        teacherDb.remove(teacher);
    }

    void deleteAllTeachers(){
        for(String teacher: teacherStudentDb.keySet())
        {
            List<String> students= teacherStudentDb.get(teacher);
            for(int i=0; i<students.size(); i++)
            {
                studentDb.remove(students.get(i));
            }
        }
        teacherDb.clear();
        teacherStudentDb.clear();
    }

}
