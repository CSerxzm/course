<template>
  <div class="login-bg">
    <div class="login-form">
      <div class="form-title">学生信息管理系统</div>
      <el-form
        :model="formData"
        :rules="rules"
        class="form-content"
        label-width="0px"
        ref="form"
      >
        <el-form-item prop="username">
          <el-input v-model="formData.username">
            <span slot="prepend"><i>学号</i></span>
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            @keyup.enter.native="submit()"
            type="password"
            v-model="formData.password"
          >
            <span slot="prepend"><i>密码</i></span>
          </el-input>
        </el-form-item>

        <el-form-item prop="userType">
          <el-radio-group v-model="formData.userType">
            <el-radio label="1">学生</el-radio>
            <el-radio label="2">教师</el-radio>
            <el-radio label="3">管理员</el-radio>
          </el-radio-group>
          <el-checkbox v-model="checked">&nbsp;记住密码</el-checkbox>
        </el-form-item>

        <div class="login-bt" v-loading="this.$store.state.loading">
          <el-button @click="submit()" type="primary">登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { login } from "../api/user";

export default {
  data: function() {
    return {
      formData: {
        username: "",
        password: "",
        userType: "1"
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        userType: [
          { required: true, message: "请选择用户类型", trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submit() {
      this.$refs.form.validate(valid => {
        if (valid) {
          login(
            this.formData.username,
            this.formData.password,
            this.formData.userType
          ).then(res => {
            this.$message.success("登录成功: " + res.username);
            this.$store.commit("login", res);
            this.$router.push({ name: "container" });
          });
        }
      });
    }
  }
};
</script>

<style scoped>
.login-bg {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url("../assets/bg.jpg");
  background-size: 100% 100%;
}

.form-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: #fff;
  border-bottom: 1px solid #ddd;
}

.login-form {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 350px;
  margin: -190px 0 0 -170px;
  border-radius: 5px;
  background:#677998;
}

.form-content {
  padding: 30px 30px;
}

.login-bt {
  text-align: center;
}

.login-bt button {
  width: 100%;
  height: 36px;
}


</style>
