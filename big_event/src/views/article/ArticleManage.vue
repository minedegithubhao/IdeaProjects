<script setup>
import {
    Delete,
    Edit
} from '@element-plus/icons-vue'

import { addArticleService, articleCategoryListService, articleListService, deleteArticleService, getArticleDetailService, updateArticleService } from '@/api/article'
import { useTokenStore } from '@/stores/token.js'; //导入token
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import { ElMessage, ElMessageBox } from 'element-plus'
import { ref } from 'vue'
const tokenstore = useTokenStore()

//文章分类数据模型
const categorys = ref([])

//用户搜索时选中的分类id
const categoryId = ref('')

//用户搜索时选中的发布状态
const state = ref('')

//文章列表数据模型
const articles = ref([
    {
        "id": 5,
        "title": "陕西旅游攻略",
        "content": "兵马俑,华清池,法门寺,华山...爱去哪去哪...",
        "coverImg": "https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png",
        "state": "草稿",
        "categoryId": 2,
        "createTime": "2023-09-03 11:55:30",
        "updateTime": "2023-09-03 11:55:30"
    },
    {
        "id": 5,
        "title": "陕西旅游攻略",
        "content": "兵马俑,华清池,法门寺,华山...爱去哪去哪...",
        "coverImg": "https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png",
        "state": "草稿",
        "categoryId": 2,
        "createTime": "2023-09-03 11:55:30",
        "updateTime": "2023-09-03 11:55:30"
    },
    {
        "id": 5,
        "title": "陕西旅游攻略",
        "content": "兵马俑,华清池,法门寺,华山...爱去哪去哪...",
        "coverImg": "https://big-event-gwd.oss-cn-beijing.aliyuncs.com/9bf1cf5b-1420-4c1b-91ad-e0f4631cbed4.png",
        "state": "草稿",
        "categoryId": 2,
        "createTime": "2023-09-03 11:55:30",
        "updateTime": "2023-09-03 11:55:30"
    },
])

//分页条数据模型
const pageNum = ref(1)//当前页
const total = ref(20)//总条数
const pageSize = ref(3)//每页条数

//当每页条数发生了变化，调用此函数
const onSizeChange = (size) => {
    pageSize.value = size
    articleList()
}
//当前页码发生变化，调用此函数
const onCurrentChange = (num) => {
    pageNum.value = num
    articleList()
}

// 获取文章分类数据
const articleCategoryList = async () => {
    const result = await articleCategoryListService()
    if (result.code === 0) {
        categorys.value = result.data
    }
}
articleCategoryList()

// 获取文章列表数据
const articleList = async () => {
    // 请求参数
    let params = {
        categoryId: categoryId.value,
        state: state.value,
        pageNum: pageNum.value,
        pageSize: pageSize.value
    }
    const result = await articleListService(params)
    if (result.code === 0) {
        articles.value = result.data.items
        total.value = result.data.total
    }
    // 分类code转义为中文，遍历为每个文章添加一个categoryName属性
    articles.value.forEach(item => {
        categorys.value.forEach(c => {
            if (item.categoryId === c.id) {
                item.categoryName = c.categoryName
            }
        })
    })
}
articleList()

// 重置搜索条件
const reset = () => {
    categoryId.value = ''
    state.value = ''
}

import { Plus } from '@element-plus/icons-vue'
//控制抽屉是否显示
const visibleDrawer = ref(false)
//添加表单数据模型
const articleModel = ref({
    title: '',
    categoryId: '',
    coverImg: '',
    content: '',
    state: ''
})

// 文件上传成功回调方法
const uploadSuccess = (response) => {
    // 调用成功后将该图片地址赋值给coverImg
    console.log(response.data)
    articleModel.value.coverImg = response.data
}

// 添加文章接口
const addArticle = async (type) => {
    if (type === 'draft') {
        articleModel.value.state = '草稿'
    } else {
        articleModel.value.state = '已发布'
    }
    // 如果标题是修改文章，则调用修改接口
    if (articleModel.value.id) {
        let result = await updateArticleService(articleModel.value)
        ElMessage.success(result.msg ? result.msg : '修改成功')
    }else{
        let result = await addArticleService(articleModel.value)
        ElMessage.success(result.msg ? result.msg : '添加成功')
    }
    visibleDrawer.value = false
    // 重置表单并刷新列表
    articleModel.value = {}
    articleList()
}

// 删除文章接口
const deleteArticle = async (id) => {
    // 提示确定是否删除
    ElMessageBox.confirm(
        '你确定删除该文章信息?',
        '温馨提示',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(async () => {
            let result = await deleteArticleService(id)
            ElMessage.success(result.msg ? result.msg : '删除成功')
        }).catch(() => {
            ElMessage.info('取消删除')
        }).finally(() => {
            // 刷新列表
            articleList();
        });
}

const title = ref('添加文章')
// 编辑文章接口
const editArticle = async (id) => {
    // 修改title
    title.value = '编辑文章'
    // 打开抽屉
    visibleDrawer.value = true
    // 获取文章详情
    let result = await getArticleDetailService(id)
    //赋值
    articleModel.value = result.data
}
</script>
<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>文章管理</span>
                <div class="extra">
                    <el-button type="primary" @click="visibleDrawer = true">添加文章</el-button>
                </div>
            </div>
        </template>
        <!-- 搜索表单 -->
        <el-form inline class="demo-form-inline">
            <el-form-item label="文章分类：">
                <el-select placeholder="请选择" v-model="categoryId">
                    <el-option v-for="c in categorys" :key="c.id" :label="c.categoryName" :value="c.id">
                    </el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="发布状态：">
                <el-select placeholder="请选择" v-model="state">
                    <el-option label="已发布" value="已发布"></el-option>
                    <el-option label="草稿" value="草稿"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="articleList()">搜索</el-button>
                <el-button @click="reset()">重置</el-button>
            </el-form-item>
        </el-form>
        <!-- 文章列表 -->
        <el-table :data="articles" style="width: 100%">
            <el-table-column label="文章标题" width="400" prop="title"></el-table-column>
            <el-table-column label="分类" prop="categoryName"></el-table-column>
            <el-table-column label="发表时间" prop="createTime"> </el-table-column>
            <el-table-column label="状态" prop="state"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary" @click="editArticle(row.id)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteArticle(row.id)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
        <!-- 分页条 -->
        <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[3, 5, 10, 15]"
            layout="jumper, total, sizes, prev, pager, next" background :total="total" @size-change="onSizeChange"
            @current-change="onCurrentChange" style="margin-top: 20px; justify-content: flex-end" />
    </el-card>

    <!-- 抽屉 -->
    <el-drawer v-model="visibleDrawer" :title="title" direction="rtl" size="50%">
        <!-- 添加文章表单 -->
        <el-form :model="articleModel" label-width="100px">
            <el-form-item label="文章标题">
                <el-input v-model="articleModel.title" placeholder="请输入标题"></el-input>
            </el-form-item>
            <el-form-item label="文章分类">
                <el-select placeholder="请选择" v-model="articleModel.categoryId">
                    <el-option v-for="c in categorys" :key="c.id" :label="c.categoryName" :value="c.id">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="文章封面">

                <!-- 
                    auto-upload:设置是否自动上传
                    action:设置服务器接口路径
                    name:设置上传的文件字段名
                    headers:设置上传的请求头
                    on-success:设置上传成功的回调函数
                -->

                <el-upload class="avatar-uploader" :auto-upload="true" :show-file-list="false" action="/api/upload"
                    name="file" :headers="{ 'Authorization': tokenstore.token }" :on-success="uploadSuccess">
                    <img v-if="articleModel.coverImg" :src="articleModel.coverImg" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
            </el-form-item>
            <el-form-item label="文章内容">
                <div class="editor">
                    <quill-editor theme="snow" v-model:content="articleModel.content" contentType="html">
                    </quill-editor>
                </div>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="addArticle('public')">发布</el-button>
                <el-button type="info" @click="addArticle('draft')">草稿</el-button>
            </el-form-item>
        </el-form>
    </el-drawer>
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

.demo-form-inline .el-input {
    --el-input-width: 220px;
}

.demo-form-inline .el-select {
    --el-select-width: 220px;
}

/* 抽屉样式 */
.avatar-uploader {
    :deep() {
        .avatar {
            width: 178px;
            height: 178px;
            display: block;
        }

        .el-upload {
            border: 1px dashed var(--el-border-color);
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            transition: var(--el-transition-duration-fast);
        }

        .el-upload:hover {
            border-color: var(--el-color-primary);
        }

        .el-icon.avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            text-align: center;
        }
    }
}

.editor {
    width: 100%;

    :deep(.ql-editor) {
        min-height: 200px;
    }
}
</style>