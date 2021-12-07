import UserType from "./userType";
import Permission from "./permission";

let SideBarItem = {};

SideBarItem.items = [
  {
    icon: "el-icon-user-solid",
    index: "/student",
    title: "学生首页",
    userType: UserType.student,
    permission: Permission.no
  },
  {
    icon: "el-icon-s-management",
    index: "/student/course",
    title: "课程查看",
    userType: UserType.student,
    permission: Permission.no
  },
  {
    icon: "el-icon-notebook-2",
    index: "/student/timetable",
    title: "课表查看",
    userType: UserType.student,
    permission: Permission.no
  },
  {
    icon: "el-icon-s-order",
    index: "/student/course/select",
    title: "选修课程",
    userType: UserType.student,
    permission: Permission.no
  },
  {
    icon: "el-icon-postcard",
    index: "/student/score",
    title: "成绩查看",
    userType: UserType.student,
    permission: Permission.no
  },
  {
    icon: "el-icon-s-tools",
    index: "/student/info",
    title: "个人信息",
    userType: UserType.student,
    permission: Permission.no
  },
  {
    icon: "el-icon-user-solid",
    index: "/teacher",
    title: "教师首页",
    userType: UserType.teacher,
    permission: Permission.no
  },
  {
    icon: "el-icon-s-management",
    index: "/teacher/course",
    title: "授课查看",
    userType: UserType.teacher,
    permission: Permission.no
  },
  {
    icon: "el-icon-notebook-2",
    index: "/teacher/timetable",
    title: "课表查看",
    userType: UserType.teacher,
    permission: Permission.no
  },
  {
    icon: "el-icon-postcard",
    index: "/teacher/grade",
    title: "成绩录入",
    userType: UserType.teacher,
    permission: Permission.no
  },
  {
    icon: "el-icon-user-solid",
    index: "/admin",
    title: "管理员首页",
    userType: UserType.admin,
    permission: Permission.no
  },
  {
    icon: "el-icon-s-home",
    index: "/admin/department",
    title: "学院管理",
    userType: UserType.admin,
    permission: Permission.department
  },
  {
    icon: "el-icon-office-building",
    index: "/admin/major",
    title: "专业管理",
    userType: UserType.admin,
    permission: Permission.major
  },
  {
    icon: "el-icon-notebook-1",
    index: "/admin/course",
    title: "课程管理",
    userType: UserType.admin,
    permission: Permission.course
  },
  {
    icon: "el-icon-notebook-2",
    index: "/admin/student/course",
    title: "选课管理",
    userType: UserType.admin,
    permission: Permission.studentCourse
  },
  {
    icon: "el-icon-reading",
    index: "/admin/class",
    title: "班级管理",
    userType: UserType.admin,
    permission: Permission.class
  },
  {
    icon: "el-icon-user",
    index: "/admin/student",
    title: "学生管理",
    userType: UserType.admin,
    permission: Permission.student
  },
  {
    icon: "el-icon-user-solid",
    index: "/admin/teacher",
    title: "教师管理",
    userType: UserType.admin,
    permission: Permission.teacher
  },

];

export default SideBarItem;
