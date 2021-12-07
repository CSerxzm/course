<template>
  <div class="info-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-id-badge"></i> 个人信息
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <el-form :model="entityForm" class="info-form" label-width="80px">
        <el-form-item label="学号">
          <el-input disabled v-model="entityForm.number"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input disabled v-model="entityForm.name"></el-input>
        </el-form-item>
        <el-form-item label="学院">
          <el-input disabled v-model="entityForm.departmentName"></el-input>
        </el-form-item>
        <el-form-item label="专业">
          <el-input disabled v-model="entityForm.majorName"></el-input>
        </el-form-item>
        <el-form-item label="班级">
          <el-input disabled v-model="entityForm.className"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="entityForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="Email">
          <el-input v-model="entityForm.email"></el-input>
        </el-form-item>
        <el-form-item label="家庭地址">
          <el-input v-model="entityForm.address"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input type="password" v-model="entityForm.password"></el-input>
        </el-form-item>
        <el-form-item label=" ">
          <el-button @click="update" type="primary">确认更改</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/student/info";

export default {
  name: "StudentInfo",
  data() {
    return {
      entityForm: {
        number: "",
        name: "",
        departmentName: "",
        majorName: "",
        className: "",
        phone: "",
        email: "",
        address: "",
        birthday: "",
        sex: "",
        password: null,
      },
    };
  },
  methods: {
    get() {
      api.get().then((res) => {
        this.entityForm = res;
      });
    },
    update() {
      api.update(this.entityForm).then(() => {
        this.$message.success("更新信息成功!");
      });
    },
  },
  created() {
    this.get();
  },
};
</script>

<style scoped>
.info-form {
  min-width: 400px;
  width: 35%;
  margin: auto;
}
</style>
