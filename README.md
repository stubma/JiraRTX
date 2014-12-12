这是什么
=======

这个项目包含了一个jira插件(RTX Notifier)和一个服务端(RTX Agent), jira插件用来监听jira的事件, 并把这些事件转换为消息发送到服务端, 服务端再将消息转发到RTX Server, 实现通知功能. jira插件和服务端通过socket连接, 所以可以部署在不同的服务器上.

插件基于jira 6.3.11开发, 在jira 6.3.6, 6.3.11上测试成功

为什么
===

因为使用jira管理项目的关系, 同时使用RTX沟通, 希望能有一个jira和RTX配合的功能, 问度娘和狗狗, 看到其他人也有这样的需求, 但是并未搜索到现成的轮子, 只好自己写一个了.

如何使用
===

1. 安装RTX Server到机器A
2. 安装RTX Server SDK到机器A, 参照其文档做设置
3. 安装Jira到机器B
4. 安装Atlassian Plugin SDK, 编译rtxnotifier, 拷贝到jira的插件目录
5. 启动jira后, 导航条会看到RTX菜单, 选择Config RTX Agent, 配置RTX Agent的地址和端口, 然后保存. 端口缺省是20141, 写死在RTXAgent.java里的
6. 在机器A上安装eclipse, 打开rtxagent项目, 运行RTXAgent.java. 我还没有打包成jar, 所以就先通过eclipse运行吧.

试着在机器A创建一个项目, 然后创建一个issue, 对这个issue做一些操作, 如果在机器B的eclipse console里看到发过来的通知消息, 则ok