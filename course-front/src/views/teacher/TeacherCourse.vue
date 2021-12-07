<template>
  <div class="course-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-s-management"></i> 授课查看
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <el-row :gutter="20">
        <el-col :span="2">
          <el-button @click="create" icon="el-icon-plus">新增课程</el-button>
        </el-col>
      </el-row>
      <div class="table">
        <el-table :data="tableData" stripe>
          <el-table-column label="课程Id" prop="id" />
          <el-table-column label="课程名" prop="name" />
          <el-table-column label="上课年级" prop="grade" />
          <el-table-column label="课程学分" prop="credit" />
          <el-table-column label="上课时间" prop="time" />
          <el-table-column label="上课地点" prop="location" />
          <el-table-column label="选课人数" prop="selectedCount" />
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
      </div>
      <el-dialog :visible.sync="editing" title="编辑" width="30%">
        <el-form :model="entityForm" label-width="70px" ref="form">
          <el-form-item label="课程名">
            <el-input v-model="entityForm.name"></el-input>
          </el-form-item>
          <el-form-item label="上课年级">
            <el-input type="number" v-model="entityForm.grade"></el-input>
          </el-form-item>
          <el-form-item label="上课时间">
            <el-select v-model="courseDay">
              <el-option
                :key="index"
                :label="item"
                :value="index"
                v-for="(item, index) in days"
              >
              </el-option>
            </el-select>
            <el-select v-model="courseTime">
              <el-option
                :key="index"
                :label="item"
                :value="index"
                v-for="(item, index) in times"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="时长(节)">
            <el-input type="number" v-model="courseLength"></el-input>
          </el-form-item>
          <el-form-item label="上课地点">
            <el-input v-model="entityForm.location"></el-input>
          </el-form-item>
          <el-form-item label="学分">
            <el-input type="number" v-model="entityForm.credit"></el-input>
          </el-form-item>
          <el-form-item label="课容量">
            <el-input type="number" v-model="entityForm.maxSize"></el-input>
          </el-form-item>
          <el-form-item label="考试地点">
            <el-input v-model="entityForm.examLocation"></el-input>
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
import * as api from "../../api/teacher/course";

export default {
  name: "TeacherCourse",
  data() {
    return {
      entityForm: {},
      tableData: [],
      courseDay: "",
      courseTime: "",
      courseLength: 0,
      days: [
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
        "星期日",
      ],
      times: [
        "第一节",
        "第二节",
        "第三节",
        "第四节",
        "第五节",
        "第六节",
        "第七节",
        "第八节",
        "第九节",
      ],
    };
  },
  methods: {
    edit(id) {
      api.get(id).then((res) => {
        let split = res.time.split("-");
        this.courseDay = parseInt(split[0]) - 1;
        this.courseTime = parseInt(split[1]) - 1;
        this.courseLength = parseInt(split[2]);
        this.entityForm = res;
        this.editing = true;
      });
    },
    deleteItem(id) {
      api.deleteItem(id).then(() => {
        this.$message.success("删除成功");
        this.getList();
      });
    },
    create() {
      this.entityForm = {
        id: -1,
        teacherId: null,
        name: "",
        grade: 2019,
        time: "",
        location: "",
        credit: 2,
        maxSize: 50,
        examDate: null,
        examLocation: null,
      };
      this.courseDay = 1;
      this.courseTime = 1;
      this.courseLength = 2;
      this.editing = true;
    },
    save() {
      let day = this.courseDay + 1;
      let time = this.courseTime + 1;
      this.entityForm.time = day + "-" + time + "-" + this.courseLength;

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
      this.getList();
      this.editing = false;
    },
    getList() {
      api.list().then((res) => {
        this.tableData = res;
      });
    },
  },
  created() {
    this.getList();
  },
};
</script>

<style scoped></style>
