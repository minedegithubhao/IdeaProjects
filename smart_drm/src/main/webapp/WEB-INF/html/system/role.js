$(document).ready(function(){
   loadGrid();
});

function loadGrid() {
   $('#index_dataGrid').datagrid({
      url:'../../system/role/dataGrid?noCache=' + new Date().getTime(),
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
         {field: 'roleId', align: 'center', title: '角色编号', width:fixWidth(0.1)},
         {field: 'roleName', align: 'center', title: '角色名称', width:fixWidth(0.1)},
         {field: 'roleKey', align: 'center', title: '角色编码', width:fixWidth(0.1)},
         {field: 'roleSort', align: 'center', title: '显示顺序', width:fixWidth(0.1)},
         {field: 'status', align: 'center', title: '状态', width:fixWidth(0.1), formatter:statusFormatter},
         {field: 'createTime', align: 'center', title: '创建时间', width:fixWidth(0.2), formatter:dateTimeFormatter}
      ]]
   });
}

function getParams(){
   return {
      roleName: $('#roleNameSearch').val(),
      roleKey: $('#roleKeySearch').val(),
      status: $('#statusSearch').val()
   };
}

function searchInfo(){
   $('#index_dataGrid').datagrid('options').url = '../../system/role/dataGrid?noCache=' + new Date().getTime();
   $('#index_dataGrid').datagrid('load',getParams());
}

//新增
let operateType = "";
function add(){
   operateType = "add";
   $('#editForm').form('clear');
   $('#editDialog').dialog('open').dialog('center');
}

//保存
function save(){
   let sysRole = {};

   let roleName = $('#roleName').textbox('getValue');
   sysRole['roleName'] = roleName;
   if (roleName === '' || checkSpace(roleName)){
      $.messager.alert('提醒', '角色名称不能为空!', 'warning');
      return;
   }

   let roleKey = $('#roleKey').textbox('getValue');
   sysRole['roleKey'] = roleKey;
   if (roleKey === '' || checkSpace(roleKey)){
      $.messager.alert('提醒', '登录账户不能为空!', 'warning');
      return;
   }

   let roleSort = $('#roleSort').textbox('getValue');
   sysRole['roleSort'] = roleSort;
   if (roleSort === '' || checkSpace(roleSort)){
      $.messager.alert('提醒', '显示顺序不能为空!', 'warning');
      return;
   }
   let numRegExp = /^[0-9]\d*$/;
   if (!numRegExp.test(roleSort)){
      $.messager.alert('提醒', '显示顺序必须为数字!', 'warning');
      return;
   }

   sysRole['status'] = $('#status').combobox('getValue');
   sysRole['roleId'] = $('#id').val();

   let postUrl = '';
   if (operateType === 'add'){
      postUrl = '../../system/role/save';
   } else if (operateType === 'update'){
      postUrl = '../../system/role/update';
   }

   $.ajax({
      type: 'POST',
      url:postUrl,
      data:JSON.stringify(sysRole),
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

//修改
function update(){
   operateType = 'update';
   $('#editForm').form('clear');
   let row = $("#index_dataGrid").datagrid("getSelected");
   if (row === undefined || row == null){
      $.messager.alert('提醒', '请选择一条记录!', 'warning');
      return;
   }
   getSysRoleById(row.roleId);
   $('#editDialog').dialog('open').dialog('center');
}

// 删除
function remove(){
   $.messager.confirm('确认', '是否删除?', function (r){
      if (r){
         let row = $("#index_dataGrid").datagrid("getSelected");
         $.ajax({
            type:'GET',
            url:'../../system/role/remove',
            data:{roleId:row.roleId},
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

function getSysRoleById(roleId){
   $.ajax({
      type:'GET',
      url:'../../system/role/getRoleById',
      data:{roleId:roleId},
      dataType:'json',
      contentType: 'application/json',
      async:false,
      success:function (data) {
         if (data.success){
            let user = data.rows;
            $('#id').val(user.roleId);
            $('#roleName').textbox('setValue', user.roleName);
            $('#roleKey').textbox('setValue', user.roleKey);
            $('#roleSort').textbox('setValue', user.roleSort);
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
