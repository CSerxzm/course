<template>
  <div class="student-course-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-notebook-2"></i> 选课管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="query-form">
        <el-row :gutter="20">
          <el-col :span="2">
            <el-button @click="create" icon="el-icon-plus">新增选课</el-button>
          </el-col>

          <el-col :offset="16" :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="课程名"
              v-model="queryForm.name"
            />
          </el-col>

          <el-col :span="3">
            <el-button @click="query" type="primary">搜索 </el-button>
          </el-col>
        </el-row>
      </div>

      <div class="table">
        <el-table :data="tableData" stripe>
          <el-table-column label="选课Id" prop="id" width="80px" />
          <el-table-column label="课程名" prop="courseName" />
          <el-table-column label="学生名" prop="studentName" />
          <el-table-column label="学生班级" prop="className" />
          <el-table-column label="日常分" min-width="80px" prop="dailyScore" />
          <el-table-column label="期末分" prop="examScore" width="80px" />
          <el-table-column label="总分" prop="score" width="80px" />
          <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">
              <el-button @click="edit(scope.row.id)" size="mini" type="success"
                >编辑
              </el-button>
              <el-button
                @click="deleteItem(scope.row.id)"
                size="mini"
                type="danger"
                >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-row justify="center" type="flex">
          <el-pagination
            :current-page.sync="pageIndex"
            :page-size="pageSize"
            :total="pageSize * pageCount"
            @current-change="getPage"
            background
            layout="prev, pager, next"
          >
          </el-pagination>
        </el-row>
      </div>

      <el-dialog :visible.sync="editing" title="编辑" width="30%">
        <el-form :model="entityForm" label-width="70px" ref="form">
          <el-form-item label="学生">
            <el-select
              :disabled="entityForm.id !== -1"
              placeholder="请选择学生"
              v-model="entityForm.studentId"
            >
              <el-option
                :key="index"
                :label="item.name"
                :value="item.id"
                v-for="(item, index) in students"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="课程">
            <el-select
              :disabled="entityForm.id !== -1"
              placeholder="请选择课程"
              v-model="entityForm.courseId"
            >
              <el-option
                :key="index"
                :label="item.name"
                :value="item.id"
                v-for="(item, index) in courses"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="日常得分">
            <el-input type="number" v-model="entityForm.dailyScore"></el-input>
          </el-form-item>
          <el-form-item label="考试得分">
            <el-input type="number" v-model="entityForm.examScore"></el-input>
          </el-form-item>
          <el-form-item label="总分">
            <el-input type="number" v-model="entityForm.score"></el-input>
          </el-form-item>
        </el-form>
        <span class="dialog-footer" slot="footer">
          <el-button @click="save" type="primary">确 定</el-button>
          <el-button @click="editing = false">取 消</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/admin/studentCourse";
import * as CourseApi from "../../api/admin/course";
import * as StudentApi from "../../api/admin/student";

export default {
  name: "AdminStudentCourse",
  data() {
    return {
      queryForm: {
        courseName: "",
      },
      entityForm: {},
      tableData: [],
      pageSize: api.pageSize,
      pageCount: 1,
      pageIndex: 1,
      editing: false,
      courses: [],
      students: [],
    };
  },
  methods: {
    query() {
      api.getPageCount(this.queryForm.courseName).then((res) => {
        this.pageCount = res;
        this.pageIndex = 1;
        this.getPage(1);
      });
    },
    getPage(pageIndex) {
      api.getPage(pageIndex, this.queryForm.courseName).then((res) => {
        this.tableData = res;
      });
    },
    create() {
      this.entityForm = {
        id: -1,
        studentId: null,
        courseId: null,
        dailyScore: null,
        examScore: null,
        score: null,
      };
      this.editing = true;
    },
    edit(id) {
      api.get(id).then((res) => {
        this.entityForm = res;
        this.editing = true;
      });
    },
    save() {
      if (this.entityForm.id === -1) {
        api.create(this.entityForm).then(() => {
          this.finishSave();
        });
      } else {
        api.update(this.entityForm).then(() => {
          this.finishSave();
        });
      }
    },
    finishSave() {
      this.$message.success("成功");
      this.getPage(this.pageIndex);
      this.editing = false;
    },
    deleteItem(id) {
      api.deleteItem(id).then(() => {
        this.$message.success("删除成功");
        this.getPage(this.pageIndex);
      });
    },
    getCoursesAndStudents() {
      CourseApi.listName().then((res) => {
        //console.log(res);
        this.courses = res;
      });
      StudentApi.listName().then((res) => {
        this.students = res;
      });
    },
  },
  created() {
    this.query();
    this.getCoursesAndStudents();
  },
};
</script>

<style scoped></style>
