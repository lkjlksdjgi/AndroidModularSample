# AndroidModularSample
Android 组件化架构学习
参考资料：<br>
<http://blog.csdn.net/guiying712/article/details/55213884>  <br>
<http://blog.csdn.net/guiying712/article/details/78057120> <br>
网上关于组件化开发资料也很多，这里就不多说了，路由用的阿里的[ARouter](https://github.com/alibaba/ARouter)

#### 这里说一个要注意的地方

一般的依赖库都是放在 ***Common***这个模块下的，阿里的路由框架<br>
```
compile 'com.alibaba:arouter-api:1.2.4'
```
这个可以放在***Common***这个模块中，但是<br><br>
```
annotationProcessor "com.alibaba:arouter-compiler:1.1.4"

android{
 javaCompileOptions {
            annotationProcessorOptions {
                arguments = [moduleName: project.getName()]
            }
        }
}
```
这个必须在每个模块中单独引用
