import Vue from "vue";
import VueRouter from "vue-router";
import Container from "./views/Container.vue";
import Login from "./views/Login";
import StudentSelect from "./views/student/StudentSelect";
import StudentCourse from "./views/student/StudentCourse";
import StudentInfo from "./views/student/StudentInfo";
import StudentSchedule from "./views/student/StudentSchedule";
import StudentExam from "./views/student/StudentExam";
import StudentScore from "./views/student/StudentScore";
import TeacherCourse from "./views/teacher/TeacherCourse";
import TeacherCourseManager from "./views/teacher/TeacherCourseManager";
import TeacherSchedule from "./views/teacher/TeacherSchedule";
import TeacherGrade from "./views/teacher/TeacherGrade";
import AdminDepartment from "./views/admin/AdminDepartment";
import AdminMajor from "./views/admin/AdminMajor";
import AdminClass from "./views/admin/AdminClass";
import AdminStudent from "./views/admin/AdminStudent";
import AdminTeacher from "./views/admin/AdminTeacher";
import AdminCourse from "./views/admin/AdminCourse";
import AdminStudentCourse from "./views/admin/AdminStudentCourse";
import Home from "./views/Home";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "container",
    component: Container,
    children: [
      {
        path: "/student",
        name: "student-home",
        component: Home
      },
      {
        path: "/student/course/select",
        name: "student-course-select",
        component: StudentSelect
      },
      {
        path: "/student/course",
        name: "student-course",
        component: StudentCourse
      },
      {
        path: "/student/timetable",
        name: "student-timetable",
        component: StudentSchedule
      },
      {
        path: "/student/exam",
        name: "student-exam",
        component: StudentExam
      },
      {
        path: "/student/score",
        name: "student-score",
        component: StudentScore
      },
      {
        path: "/student/info",
        name: "student-info",
        component: StudentInfo
      },
      {
        path: "/teacher",
        name: "teacher-home",
        component: Home
      },
      {
        path: "/teacher/course/manager",
        name: "teacher-course",
        component: TeacherCourseManager
      },
      {
        path: "/teacher/course",
        name: "teacher-course",
        component: TeacherCourse
      },
      {
        path: "/teacher/timetable",
        name: "teacher-timetable",
        component: TeacherSchedule
      },
      {
        path: "/teacher/grade",
        name: "teacher-grade",
        component: TeacherGrade
      },
      {
        path: "/admin",
        name: "admin-home",
        component: Home
      },
      {
        path: "/admin/department",
        name: "admin-department",
        component: AdminDepartment
      },
      {
        path: "/admin/major",
        name: "admin-major",
        component: AdminMajor
      },
      {
        path: "/admin/class",
        name: "admin-class",
        component: AdminClass
      },
      {
        path: "/admin/student",
        name: "admin-student",
        component: AdminStudent
      },
      {
        path: "/admin/teacher",
        name: "admin-teacher",
        component: AdminTeacher
      },
      {
        path: "/admin/course",
        name: "admin-course",
        component: AdminCourse
      },
      {
        path: "/admin/student/course",
        name: "admin-student-course",
        component: AdminStudentCourse
      }
    ]
  },
  {
    path: "/login",
    name: "login",
    component: Login
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
