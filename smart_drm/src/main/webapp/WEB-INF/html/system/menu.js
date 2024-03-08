$(document).ready(function(){
   loadGrid();
});

function loadGrid() {
   $('#index_dataGrid').treegrid({
      url:'../../system/menu/dataGrid?noCache=' + new Date().getTime(),
      fit:true,
      fitColumns: true,
      rownumbers:true,
      nowrap:false,
      toolbar:'#tb',
      method:'GET',
      idField:'menuId',
      treeField:'menuName',
      columns:[[
         {field: '', align:'left', checkbox:true},
         {field: 'menuId', align: 'center', title: '菜单编号', width:fixWidth(0.05)},
         {field: 'menuName', halign:'center', align: 'left', title: '菜单名称', width:fixWidth(0.10)},
         {field: 'url', halign:'center', align: 'left', title: '菜单地址', width:fixWidth(0.10)},
         {field: 'orderNum', align: 'center', title: '排序', width:fixWidth(0.05)},
         {field: 'menuType', align: 'center', title: '类型', width:fixWidth(0.05), formatter:typeFormatter},
         {field: 'visible', align: 'center', title: '可见', width:fixWidth(0.05), formatter:visualFormatter},
         {field: 'perms', align: 'center', title: '权限标识', width:fixWidth(0.10)}
      ]],
      // 格式化treeGrid数据
      loadFilter:function (data){
         let rows = data.rows;
         for (let i = 0; i < rows.length; i++) {
            rows[i].id = rows[i].menuId;
            if (rows[i].menuType === 'M'){
               rows[i].state = 'closed';
            } else {
               // 二级菜单要_parentId，一级菜单不要_parentId
               rows[i]._parentId = rows[i].parentId;
            }
         }
         return data;
      }
   });
}

// 新增
let operateType = "";
function add(){
   operateType = "add";
   $('#editForm').form('clear');
   init();
   $('#editDialog').dialog('open').dialog('center');
}

// 保存
function save(){
   let sysMenu = {};

   let menuName = $('#menuName').textbox('getValue');
   if (menuName === '' || checkSpace(menuName)){
      $.messager.alert('提醒', '菜单名称不能为空!', 'warning');
      return;
   }
   sysMenu['menuName'] = menuName;

   let menuType = $('#menuType').combobox('getValue');
   if (menuType === '' || checkSpace(menuType)){
      $.messager.alert('提醒', '菜单类型不能为空!', 'warning');
      return;
   }
   sysMenu['menuType'] = menuType;

   if (!$("#parentIdTr").is(":hidden")){
      let parentId = $('#parentId').combobox('getValue');
      if (parentId === '' || checkSpace(parentId)){
         $.messager.alert('提醒', '上级菜单不能为空!', 'warning');
         return;
      }
      sysMenu['parentId'] = parentId;
   }

   sysMenu['url'] = $('#url').textbox('getValue');
   sysMenu['perms'] = $('#perms').textbox('getValue');
   sysMenu['orderNum'] = $('#orderNum').textbox('getValue');
   sysMenu['visible'] = $('input[name="visible"]:checked').val();

   let postUrl = '';
   if (operateType === 'add'){
      postUrl = '../../system/menu/save';
   } else if (operateType === 'update'){
      postUrl = '../../system/menu/update';
   }

   $.ajax({
      type: 'POST',
      url:postUrl,
      data:JSON.stringify(sysMenu),
      contentType: 'application/json;charset=UTF-8',
      async:false,
      success: function(data){
         if (data.success){
            $.messager.alert('成功', data.message, 'info');
            $('#editDialog').dialog('close');
            $('#index_dataGrid').treegrid('reload');
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

//删除
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
                  $('#index_dataGrid').treegrid('reload');
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

function init(){

   $('#menuType').combobox({
      onChange:function (newValue,oldValue) {
         if (newValue === 'M'){
            $('#parentIdTr').prop('hidden', true)
         } else {
            $('#parentIdTr').prop('hidden', false)
         }
      }
   });

   $('#parentIdTr').prop('hidden', true)

   $('#parentId').combobox({
      url:'../../system/menu/getMenuCombobox?noCache=' + new Date().getTime(),
      editable:false,
      panelHeight:'auto',
      valueField:'menuId',
      textField:'menuName',
      loadFilter: function (data) {
         return data.rows.filter(item => item.menuType !== "C");
      }
   })

   //菜单状态：默认显示
   $('input[name="visible"][value="0"]').prop('checked', true);
}
