$(document).ready(function(){
   loadGrid();
});

function loadGrid() {
   $('#index_dataGrid').datagrid({
      // title:'所在位置:系统管理/用户管理',
      url:'../../system/user/dataGrid?noCache=' + new Date().getTime(),
      fit:true,
      fitColumns: true,
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
         {field: 'userId', align: 'center', title: '用户ID', width:fixWidth(0.1)},
         {field: 'loginName', align: 'center', title: '登录名称', width:fixWidth(0.1)},
         {field: 'userName', align: 'center', title: '用户名称', width:fixWidth(0.1)},
         {field: 'status', align: 'center', title: '状态', width:fixWidth(0.1), formatter:statusFormatter},
         {field: 'phonenumber', align: 'center', title: '手机', width:fixWidth(0.1)},
         {field: 'createTime', align: 'center', title: '创建时间', width:fixWidth(0.2), formatter:dateTimeFormatter}
      ]],
      onBeforeLoad:function(param){

      },
      onLoadSuccess:function(data) {

      }
   });
}

function getParams(){
   return {
      loginName: $('#loginNameSearch').val(),
      userName: $('#userNameSearch').val(),
      status: $('#statusSearch').val()
   };
}

// 查询
function searchInfo(){
   $('#index_dataGrid').datagrid('options').url = '../../system/user/dataGrid?noCache=' + new Date().getTime();
   $('#index_dataGrid').datagrid('load',getParams());
}

// 新增
let operateType = "";
function add(){
   operateType = "add";
   $('#userForm').form('clear');
   $('#editDialog').dialog('open').dialog('center');
}

// 保存
function save(){
   let user = {};

   let loginName = $('#loginName').textbox('getValue');
   user['loginName'] = loginName;
   if (loginName === '' || checkSpace(loginName)){
      $.messager.alert('提醒', '用户名称不能为空!', 'warning');
      return;
   }else if (loginName.length > 30){
      $.messager.alert('提醒', '用户名称不能超过30个字符!', 'warning');
      return;
   }

   let userName = $('#userName').textbox('getValue');
   user['userName'] = userName;
   if (userName === '' || checkSpace(userName)){
      $.messager.alert('提醒', '登录账户不能为空!', 'warning');
      return;
   }else if (userName.length > 30){
      $.messager.alert('提醒', '登录账户不能超过30个字符!', 'warning');
      return;
   }

   let password = $('#password').textbox('getValue');
   user['password'] = password;
   if (password === '' || checkSpace(password)){
      $.messager.alert('提醒', '密码不能为空!', 'warning');
      return;
   }else if (password.length > 30){
      $.messager.alert('提醒', '密码不能超过30个字符!', 'warning');
      return;
   }

   user['status'] = $('#status').combobox('getValue');
   user['userId'] = $('#userId').val();

   let postUrl = '';
   if (operateType === 'add'){
      postUrl = '../../system/user/save';
   } else if (operateType === 'update'){
      postUrl = '../../system/user/update';
   }

   $.ajax({
      type: 'POST',
      url:postUrl,
      data:JSON.stringify(user),
      contentType: 'application/json;charset=UTF-8',
      async:false,
      success: function(data){
         if (data.success){
            $.messager.alert('成功', data.message, 'info');
            $('#editDialog').dialog('close');
            $('#index_dataGrid').datagrid('reload');
         }else {
            $.messager.alert('失败', data.message, 'error');
         }
      },
      error:function (data){
         $.messager.alert('失败', data.message, 'error');
      }
   })
}

// 删除
function remove(){
   $.messager.confirm('确认', '是否删除?', function (r){
      if (r){
         let row = $("#index_dataGrid").datagrid("getSelected");
         $.ajax({
            type:'GET',
            url:'../../system/user/remove',
            data:{userId:row.userId},
            dataType:'json',
            contentType: 'application/json',
            async:false,
            success:function (data) {
               if (data.success){
                  $.messager.alert('成功', data.message, 'info');
                  $('#index_dataGrid').datagrid('reload');
               }else {
                  $.messager.alert('失败', data.message, 'error');
               }
            },
            error:function (data) {
               $.messager.alert('失败', data.message, 'error');
            }
         })
      }
   });
}

//修改
function update(){
   operateType = 'update';
   let row = $("#index_dataGrid").datagrid("getSelected");
   debugger
   if (row === undefined || row == null){
      $.messager.alert('提醒', '请选择一条记录!', 'warning');
      return;
   }
   $('#userForm').form('clear');
   $('#editDialog').dialog('open').dialog('center');
   debugger
   getUserById(row.userId);
}

function getUserById(userId){
   $.ajax({
      type:'GET',
      url:'../../system/user/getUserById',
      data:{userId:userId},
      dataType:'json',
      contentType: 'application/json',
      async:false,
      success:function (data) {
         if (data.success){
            let user = data.rows;
            debugger
            $('#userId').val(user.userId);
            $('#loginName').textbox('setValue', user.loginName);
            $('#userName').textbox('setValue', user.userName);
            $('#password').textbox('setValue', user.password);
            $('#status').combobox('setValue', user.status);
         }else {
            $('#editDialog').dialog('close');
         }
      },
      error:function (data) {
         $.messager.alert('失败', data.message, 'error');
         $('#editDialog').dialog('close');
      }
   })
}
