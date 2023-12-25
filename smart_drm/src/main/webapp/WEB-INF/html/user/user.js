$(document).ready(function(){
   loadGrid();
});

function loadGrid() {
   $('#index_dataGrid').datagrid({
      // title:'所在位置:系统管理/用户管理',
      url:'../userController/query?date=' + new Date().getTime(),
      fit:true,
      nowrap:false,
      pagination:true,
      singleSelect:true,
      rownumbers:true,
      checkbox:true,
      fixColumns:true,
      checkOnSelect:true,
      selectOnCheck:true,
      loadMsg:'正在努力加载数据，请稍后...',
      pageSize:10,
      pageList:[10,20,50],
      toolbar:'#tb',
      method:'GET',
      queryParams:getParams(),
      columns:[[
         {field: '', align:'center', checkbox:true},
         {field: 'id', align: 'center', title: '用户名'},
         {field: 'username', align: 'center', title: '真实姓名'},
         {field: 'status', align: 'center', title: '状态'}
      ]],
      onBeforeLoad:function(param){
         // param.username = $('#username').val();
         // param.realname = $('#realname').val();
         // param.role = $('#role').val();
         // param.department = $('#department').val();
         // param.email = $('#email').val();
         // param.phone = $('#phone').val();
      },
      onLoadSuccess:function(data) {
         // $('#index_dataGrid').datagrid('clearSelections');
      }
   });
}

function getParams(){
   return {
      username: $('#username').val(),
      realname: $('#realname').val(),
      role: $('#role').val(),
      department: $('#department').val(),
      email: $('#email').val(),
      phone: $('#phone').val()
   };
}

function search(){
   $('#index_dataGrid').datagrid('load',getParams());
}