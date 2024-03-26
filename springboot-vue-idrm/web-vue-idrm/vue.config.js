const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // lintOnSave=false取消驼峰命名强校验
  lintOnSave: false
})
