<script>
import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
import Main from "@/components/Main.vue";

export default {
  name: "Index",
  components: {Main, Header, Aside},
  data() {
    return {
      isCollapse: false,
      aside_width: '200px',
      collapse_icon: 'el-icon-s-fold'
    }
  },
  methods: {
    doCollapse() {
      this.isCollapse = !this.isCollapse;
      // 展开/关闭侧边栏
      !this.isCollapse === true ? this.aside_width = '200px' : this.aside_width = '64px'
      // 切换图标
      !this.isCollapse === true ? this.collapse_icon = 'el-icon-s-fold' : this.collapse_icon = 'el-icon-s-unfold'
    }
  },
}
</script>

<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="aside_width" style="background-color: rgb(238, 241, 246);height: 100%">
      <Aside :isCollapse="isCollapse"></Aside>
    </el-aside>

    <el-container style="height: 100%">
      <el-header style="text-align: right; font-size: 12px; border-bottom: rgba(169,169,169,0.3) 1px solid;">
        <Header @doCollapse="doCollapse" :collapse_icon="collapse_icon"></Header>
      </el-header>

      <el-main style="height: 100%">
        <Main></Main>
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.el-header {
  //background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
  margin-left: -1px;
}

.el-main {
  padding: 5px;
}
</style>