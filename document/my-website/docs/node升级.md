今天在安装docusaurus时，报错，大概意思是版本太低

```shell
npm WARN EBADENGINE Unsupported engine {
npm WARN EBADENGINE   package: 'create-docusaurus@3.1.1',
npm WARN EBADENGINE   required: { node: '>=18.0' },
npm WARN EBADENGINE   current: { node: 'v16.17.0', npm: '8.15.0' }
npm WARN EBADENGINE }
npm WARN EBADENGINE Unsupported engine {
npm WARN EBADENGINE   package: '@docusaurus/logger@3.1.1',
npm WARN EBADENGINE   required: { node: '>=18.0' },
npm WARN EBADENGINE   current: { node: 'v16.17.0', npm: '8.15.0' }
npm WARN EBADENGINE }
npm WARN EBADENGINE Unsupported engine {
npm WARN EBADENGINE   package: '@docusaurus/utils@3.1.1',
npm WARN EBADENGINE   required: { node: '>=18.0' },
npm WARN EBADENGINE   current: { node: 'v16.17.0', npm: '8.15.0' }
npm WARN EBADENGINE }
npm WARN deprecated stable@0.1.8: Modern JS already guarantees Array#sort() is a stable sort, so this library is deprecated. See the compatibility table on MDN: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/sort#browser_compatibility
[ERROR] Minimum Node.js version not met :(
[INFO] You are using Node.js v16.17.0, Requirement: Node.js >=18.0.
```

所以根据提示，我们需要升级npm和node的版本。

于是开始了升级npm，命令如下：

```shell
// 清除npm缓存
npm cache clean -f
// 全局安装
npm install npm -g
```

结果还是报错，意思是我们node版本太低，需要先升级node。报错如下：

```shell
npm ERR! code EBADENGINE
npm ERR! engine Unsupported engine
npm ERR! engine Not compatible with your version of node/npm: npm@10.5.0
npm ERR! notsup Not compatible with your version of node/npm: npm@10.5.0
npm ERR! notsup Required: {"node":"^18.17.0 || >=20.5.0"}
npm ERR! notsup Actual:   {"npm":"8.15.0","node":"v16.17.0"}
```

尝试几番，一直安装不下来，后来得知可以通过包 `n`安装升级 NodeJs

```shell
// 安装n包
sudo npm install n -g
// 安装稳定版
sudo n stable
```

执行后node终于安装成功

```shell
  installing : node-v20.11.1
       mkdir : /usr/local/n/versions/node/20.11.1
       fetch : https://nodejs.org/dist/v20.11.1/node-v20.11.1-darwin-x64.tar.xz
     copying : node/20.11.1
   installed : v20.11.1 (with npm 10.2.4)
```

接下来安装npm

