<script setup>
import { addArticleCategoryService, articleCategoryListService, deleteArticleCategoryService, updateArticleCategoryService } from '@/api/article'
import {
    Delete,
    Edit
} from '@element-plus/icons-vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { ref } from 'vue'
const categorys = ref([])
const articleCategoryList = async () => {
    const result = await articleCategoryListService()
    if (result.code === 0) {
        categorys.value = result.data
    }
}
articleCategoryList()

//控制添加分类弹窗
const dialogVisible = ref(false)

//添加分类数据模型
const categoryModel = ref({})
//添加分类表单校验
const rules = {
    categoryName: [
        { required: true, message: '请输入分类名称', trigger: 'blur' },
    ],
    categoryAlias: [
        { required: true, message: '请输入分类别名', trigger: 'blur' },
    ]
}

//定义变量控制标题的展示
const title = ref('')

//添加分类
const addArticleCategory = async () => {
    let result = await addArticleCategoryService(categoryModel.value)
    ElMessage.success(result.msg ? result.msg : '添加成功')
    // 重置表单并刷新列表
    resetAndRefresh()
}

// 修改分类
const showDialog = (row) => {
    // 打开弹窗
    dialogVisible.value = true
    title.value = '修改分类'

    // 将当前的分类数据赋值给categoryModel
    categoryModel.value = {
        categoryName: row.categoryName,
        categoryAlias: row.categoryAlias,
        id: row.id
    }
}

//修改文章分类
const updateArticleCategory = async () => {
    // 点击确认将事件修改为提交
    let result = await updateArticleCategoryService(categoryModel.value)
    ElMessage.success(result.msg ? result.msg : '修改成功')
    // 重置表单并刷新列表
    resetAndRefresh()
}

// 重置表单并刷新列表
const resetAndRefresh = () => {
    // 刷新列表
    articleCategoryList();
    // 关闭弹窗
    dialogVisible.value = false;
    // 清空 model 中的数据
    categoryModel.value = {};
};

// 保存文章分类
const saveArticleCategory = () => {
    if (title.value === '添加分类') {
        addArticleCategory()
    } else {
        updateArticleCategory()
    }
}

// 删除文章分类
const deleteArticleCategory = (id) => {
    // 确认框
    ElMessageBox.confirm(
        '你确定删除该分类信息?',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            let result = await deleteArticleCategoryService(id)
            ElMessage.success(result.msg ? result.msg : '删除成功')
        }).catch(() => {
            ElMessage.info('取消删除')
        }).finally(() => {
            // 刷新列表
            articleCategoryList();
        });

}

</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>文章分类</span>
                <div class="extra">
                    <!-- 在模板中，Vue 会自动解包 ref 对象，因此你可以直接使用 title 而不需要 .value -->
                    <el-button type="primary"
                        @click="dialogVisible = true; title = '添加分类'; categoryModel = {}">添加分类</el-button>
                </div>
            </div>
        </template>
        <el-table :data="categorys" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="分类名称" prop="categoryName"></el-table-column>
            <el-table-column label="分类别名" prop="categoryAlias"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger"
                        @click="deleteArticleCategory(row.id)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!-- 添加分类弹窗 -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item label="分类名称" prop="categoryName">
                    <el-input v-model="categoryModel.categoryName" minlength="1" maxlength="10"></el-input>
                </el-form-item>
                <el-form-item label="分类别名" prop="categoryAlias">
                    <el-input v-model="categoryModel.categoryAlias" minlength="1" maxlength="15"></el-input>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">取消</el-button>
                    <el-button type="primary" @click="saveArticleCategory()"> 确认 </el-button>
                </span>
            </template>
        </el-dialog>
    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>