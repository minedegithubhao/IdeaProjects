import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  server: {
    proxy: {// 代理配置
      '/api': { // 匹配所有以 /api 开头的请求
        target: 'http://localhost:8080', // 目标服务器地址
        changeOrigin: true,// 是否需要修改源
        rewrite: (path) => path.replace(/^\/api/, ''),// 重写路径
      },
    }
  }
})
