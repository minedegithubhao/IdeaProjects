$(document).ready(function(){
   loadGrid();
});

function loadGrid() {
   $('#index_dataGrid').treegrid({
      // title:'所在位置:系统管理/用户管理',
      url:'../../system/menu/dataGrid?date=' + new Date().getTime(),
      fit:true,
      fitColumns: true,
      nowrap:false,
      idField:'menuId',
      treeField:'menuName',
      toolbar:'#tb',
      method:'GET',
      columns:[[
         {field: '', align:'center', checkbox:true},
         {field: 'menuId', align: 'center', title: '菜单编号', width:fixWidth(0.05)},
         {field: 'menuName', align: 'left', title: '菜单名称', width:fixWidth(0.10)},
         {field: 'orderNum', align: 'center', title: '排序', width:fixWidth(0.05)},
         {field: 'menuType', align: 'center', title: '类型', width:fixWidth(0.05), formatter:typeFormatter},
         {field: 'visible', align: 'center', title: '可见', width:fixWidth(0.05), formatter:visualFormatter},
         {field: 'perms', align: 'center', title: '权限标识', width:fixWidth(0.10)}
      ]],
      onBeforeLoad:function(param){

      },
      onLoadSuccess:function(row, data) {

      },
      // 格式化treeGrid数据
      loadFilter:function (data){
         let rows = data.rows;
         for (let i = 0; i < rows.length; i++) {
            rows[i].id = rows[i].menuId;
            rows[i]._parentId = rows[i].parentId;
            if (rows[i].menuType !== 'F'){
               rows[i].state = 'closed';
            }
         }
         return data;
      }
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
   $('#index_dataGrid').datagrid('options').url = '../../system/role/dataGrid?date=' + new Date().getTime();
   $('#index_dataGrid').datagrid('load',getParams());
}

let operateType = "";
function add(){
   operateType = "add";
   $('#editForm').form('clear');
   $('#editDialog').dialog('open').dialog('center');
}

function save(){
   let sysRole = {};

   let roleName = $('#roleName').textbox('getValue');
   if (roleName === '' || checkSpace(roleName)){
      $.messager.alert('提醒', '角色名称不能为空!', 'warning');
      return;
   }
   sysRole['roleName'] = roleName;

   let roleKey = $('#roleKey').textbox('getValue');
   if (roleKey === '' || checkSpace(roleKey)){
      $.messager.alert('提醒', '登录账户不能为空!', 'warning');
      return;
   }
   sysRole['roleKey'] = roleKey;

   let roleSort = $('#roleSort').textbox('getValue');
   if (roleSort === '' || checkSpace(roleSort)){
      $.messager.alert('提醒', '显示顺序不能为空!', 'warning');
      return;
   }
   let numRegExp = /^[0-9]\d*$/;
   if (!numRegExp.test(roleSort)){
      $.messager.alert('提醒', '显示顺序必须为数字!', 'warning');
      return;
   }
   sysRole['roleSort'] = roleSort;

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

function update(){
   operateType = 'update';
   let row = $("#index_dataGrid").datagrid("getSelected");
   if (row === undefined || row == null){
      $.messager.alert('提醒', '请选择一条记录!', 'warning');
      return;
   }
   $('#editForm').form('clear');
   $('#editDialog').dialog('open').dialog('center');
   getSysRoleById(row.roleId);
}

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

function typeFormatter(value,row,index){
   let result;
   switch (value) {
      case "M":
         result = "目录";
         break;
      case "C":
         result = "菜单";
         break;
      case "F":
         result = "按钮";
         break;
      default:
         result = "未定义";
   }
   return result;
}

function visualFormatter(value,row,index){
   let result;
   switch (value) {
      case "0":
         result = "显示";
         break;
      case "1":
         result = "隐藏";
         break;
      default:
         result = "未定义";
   }
   return result;
}
