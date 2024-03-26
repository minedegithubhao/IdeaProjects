<script>
export default {
  name: "Header",
  methods:{
    toUser(){
      console.log("toUser")
    },
    logout(){
      console.log("logout")
    }
  }
}
</script>

<template>
  <div style="display: flex; line-height: 60px">
    <div>
      <i class="el-icon-s-fold" style="font-size: 20px"></i>
    </div>
    <div style="flex: 1; text-align: center;font-size: 20px">
      <span>欢迎来到资源管理系统</span>
    </div>
    <el-dropdown>
      <span>王小虎<i class="el-icon-arrow-down" style="margin-left: 5px"></i></span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<style scoped>

</style>