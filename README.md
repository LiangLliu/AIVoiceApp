# 内容

## 技术

Kotlin + 组件化 + Kotlin Gradle DSL

## 组件化APP

* APP 空壳 + 若干的组件
* 分工清晰，明确

## 构建组件化

App
Module
    - 笑话
    - 地图
    - 星座
    - 语言设置
    - 系统设置
    - 天气
    - 应用管理
    - 开发者管理

lib:
    - lib_base
    - lib_network
    - lib_voice
    
## 路由
* ARouter
[ARouter github](https://github.com/alibaba/ARouter)

## 语音识别名词 [百度AI开发平台](http://ai.baidu.com/)
* TTS/发音
* ASR/语义识别
* WakeUp/唤醒


## 适配器

## 服务保活
* 像素保活：相当于开启了一个窗口像素1px，来达到保活的手段
* 系统自带：系统做了一些友好的保活 - FLAG
* JobScheduler:
    * 工作任务：标记这个服务一直在工作，也是作为一种进程死后复活的手段
    * 缺点：耗电，高版本不兼容
* 进程相互唤醒：
    * 双进程保活

* 前台服务：
    * 在前台运行，绑定通知栏，在服务中创建通知栏

## 应用启动
应用启动 -> Application(Base App) -> run InitService -> init 渠道
                                -> MainActivity - onCreate() -> run VoiceService
                                
## 唤醒词
小爱同学、小袁同学、小袁小袁

## 注意点

* APP签名问题，在百度官方申请的app package 不与 appid不一致时会失败