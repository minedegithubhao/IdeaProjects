<script xmlns="http://www.w3.org/1999/html">
export default {
  name: "Main",
  data() {
    return {
      tableData: [],
      pageSize: 10,
      pageNumber: 1,
      total: 0,
      name: '',
      sex:'',
      sexs:[{
        value: '',
        label: '全部'
      },{
        value: '1',
        label: '男'
      }, {
        value: '0',
        label: '女'
      }]
    }
  },
  methods: {
    // loadGet() {
    //   this.$axios.get(this.$httpUrl + '/user/list').then(res => res.data).then(res => {
    //     console.log(res)
    //     this.tableData = res;
    //   })
    // },
    loadPost() {
      this.$axios.post(this.$httpUrl + '/user/pageConditionCC', {
        pageSize: this.pageSize,
        pageNumber: this.pageNumber,
        params: {
          name: this.name,
          sex: this.sex
        }
      }).then(res => res.data).then(res => {
        if (res.code === 200) {
          this.tableData = res.data
          this.total = res.total
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleSizeChange(val) {
      this.pageSize = val;
      this.loadPost();
    },
    handleCurrentChange(val) {
      this.pageNumber = val;
      this.loadPost();
    },
    reset(){
      this.name = '';
      this.sex = '';
      this.loadPost();
    }
  },
  beforeMount() {
    this.loadPost();
  },
}
</script>

<template>
  <div>
    <div style="margin-bottom: 5px">
      <!-- 绑定回车事件@keyup.enter.native -->
      <el-input v-model="name" placeholder="请输入名字" style="width: 200px" suffix-icon="el-icon-search"
                @keyup.enter.native="loadPost"></el-input>
      <el-select v-model="sex" filterable placeholder="请选择" style="margin-left: 5px">
        <el-option
            v-for="item in sexs"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData"
              :header-cell-style="{background:'dark'}"
              border
    >
      <el-table-column prop="id" label="ID" width="60"></el-table-column>
      <el-table-column prop="no" label="账号" width="180"></el-table-column>
      <el-table-column prop="name" label="姓名" width="180"></el-table-column>
      <el-table-column prop="sex" label="性别" width="180">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.sex === 1 ? 'primary' : 'success'"
              disable-transitions>{{ scope.row.sex === 1 ? '男' : '女' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="roleId" label="角色" width="180">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
              disable-transitions>
            {{ scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '普通管理员' : '其他') }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="状态" width="180"></el-table-column>
      <el-table-column prop="operate" label="操作">
        <el-button size="small" type="success">编辑</el-button>
        <el-button size="small" type="danger">删除</el-button>
      </el-table-column>
    </el-table>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNumber"
        :page-sizes="[10, 20, 30, 40, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>
  <!--  <el-table-->
  <!--      :data="tableData"-->
  <!--      height="250"-->
  <!--      style="width: 100%">-->
  <!--    <el-table-column-->
  <!--        prop="id"-->
  <!--        label="日期"-->
  <!--        width="180">-->
  <!--    </el-table-column>-->
  <!--    <el-table-column-->
  <!--        prop="no"-->
  <!--        label="姓名"-->
  <!--        width="180">-->
  <!--    </el-table-column>-->
  <!--    <el-table-column-->
  <!--        prop="name"-->
  <!--        label="地址">-->
  <!--    </el-table-column>-->
  <!--  </el-table>-->
</template>

<style scoped>

</style>