<template>
    <div class="container">
      <div class="form-body">
        <h2>注册账户</h2>
        <el-form ref="form" :model="registerForm" label-width="0px">
          <el-form-item  class="form-item">
            <el-input placeholder="请输入用户名" v-model="registerForm.username"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input placeholder="请输入密码" v-model="registerForm.password" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit" class="form-confirm">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
</template>

<script>
    export default {
      name: "Register",
      data() {
        return {
          registerForm:{
            username:'',
            password:'',
          }
        }
      },
      methods: {
        onSubmit(){

        }
      }

    }
</script>

<style scoped>
  .container{
    height: 100%;
    width: 100%;
    background-image: url("../../static/bg.png");
    background-size: cover;
    position: fixed;
    left: 0px;
    top:0px;
  }
  .form-body{
    border-radius: 10px;
    margin: 100px auto auto;
    width: 25%;
    min-width: 200px;
    padding: 30px 30px 15px 30px;
    background-color: rgba(255,255,255,0.5);
    box-shadow: 5px 3px 10px rgba(0,0,0,0.9);
  }
  .form-confirm{
    width: 100%;
    background-color: #3388B1;
  }

</style>
