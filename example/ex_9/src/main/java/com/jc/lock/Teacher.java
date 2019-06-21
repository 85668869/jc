/**
 * Created by jingchun.zhang on 2019/4/16.
 */
package com.jc.lock;

import java.util.List;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2019/4/16
 */
public class Teacher {
    List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public synchronized void studentNotify(Student student) {
        students.remove(student);   //将已完成考试的学生从列表中移除
    }

    public synchronized void getAllStudentStatus() {
        for (Student student : students) {
            System.out.println(student.getProcess());
        }
    }

}
