<script setup>
import { Plus, Upload } from '@element-plus/icons-vue'
import { ref } from 'vue'
import avatar from '@/assets/default.png'
import useUserInfoStore from '@/stores/userInfo'
import { useTokenStore } from '@/stores/token'
import { ElMessage } from 'element-plus'
import { updateUserAvatarService } from '@/api/user'
const userInfoStore = useUserInfoStore()
const tokenStore = useTokenStore()

const uploadRef = ref()

//用户头像地址
const imgUrl = ref(userInfoStore.info.userPic)

// 上传成功回调函数
const uploadSuccess = (response) => {
    imgUrl.value = response.data
    ElMessage.success(response.msg ? response.msg : '上传成功')
}

// 更新头像
const updateAvatar = async () => {
    let result = await updateUserAvatarService(imgUrl.value)
    ElMessage.success(result.msg ? result.msg : '修改成功')
    userInfoStore.setUserInfo({ userPic: imgUrl.value })
}
</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>更换头像</span>
            </div>
        </template>
        <el-row>
            <el-col :span="12">
                <!-- 
                    auto-upload:设置是否自动上传
                    action:设置服务器接口路径
                    name:设置上传的文件字段名
                    headers:设置上传的请求头
                    on-success:设置上传成功的回调函数
                -->
                <el-upload ref="uploadRef" class="avatar-uploader" action="/api/upload" name="file"
                    :headers="{ 'Authorization': tokenStore.token }" :on-success="uploadSuccess" :auto-upload="true"
                    :show-file-list="false">
                    <img v-if="imgUrl" :src="imgUrl" class="avatar" />
                    <img v-else :src="avatar" width="278" />
                </el-upload>
                <br />
                <el-button type="primary" :icon="Plus" size="large"
                    @click="uploadRef.$el.querySelector('input').click()">
                    选择图片
                </el-button>
                <el-button type="success" :icon="Upload" size="large" @click="updateAvatar">
                    上传头像
                </el-button>
            </el-col>
        </el-row>
    </el-card>
</template>

<style lang="scss" scoped>
.avatar-uploader {
    :deep() {
        .avatar {
            width: 278px;
            height: 278px;
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
            width: 278px;
            height: 278px;
            text-align: center;
        }
    }
}
</style>