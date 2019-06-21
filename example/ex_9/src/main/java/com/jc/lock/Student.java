/**
 * Created by jingchun.zhang on 2019/4/16.
 */
package com.jc.lock;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2019/4/16
 */
public class Student {

    private Teacher teacher;

    private int process;    //答题进度

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public synchronized int getProcess() {
        return process;
    }

    public synchronized void setProcess(int process) {
        this.process = process;
        if (process == 100) {
            teacher.studentNotify(this);    //学生答完题，通知老师
        }
    }
}
