## 项目属性配置

- 自定义属性

  - @Value("${custom_prop}")

  - 一组属性：yml归类

    @Component 自动创建beans

    @ConfigurationProperties (prefix = "my_prefix") 自动映射到类中

    之后在Controller中，实例进行@Autowired

  - 开发环境和生产环境 配置不同

    在application中写spring.profiles.active: version_no

    在另外写不同环境的设置：application-[version_x], 中括号及其内部为替换内容

## 启动方式

1. cd到项目文件夹 #输入 mvn spring-boot:run
2. cd到项目文件夹 先#输入 mvn install； 再cd到target目录 #输入 java -jar xxx-SNAPSHOT.jar
3. IDEA内 run 一下 xxxApplication
4. IDEA内 run 一下相应的单元测试

## Controller 的使用

- @Controller 处理http请求（必须配合一个模版来使用）

- @RestController 相当于@ResponseBody + @Controller, return 即显示

- @RequestMapping 配置url映射，参数(value = {"/siteA", "/siteB"...}, method = RequestMethod.Get ) 可以对特定方法进行映射，也可以对整个类进行映射（层级拼接起来）。Get和Post方式用于不同的客户端，不写的话同时生效。

  - @GetMapping相当于Get方法的RequestMapping
  - Get:获取 Post:创建 Put:更新 DELETE:删除

- @PathVariable 获取url中的数据，加入设要获取id=123

  - 简洁法：url=...:8080/../say/123

    RequestMapping中value="/say/{id}", 相应函数的参数中可以写(@PathVariable("id") Integer myId)，前一个id要等同于url中的变量，后一个是自己设定的变量

  - 标准法：url=...:8080/../say?id=123

  - 默认值：在value后面写required=false //是否不传，再跟defaultValue="123"

- @RequestParam 获取请求参数的值

- @GetMapping 组合注解

## 数据库操作

- Spring-Data-Jpa

  定义了一系列对象持久化的标准

- 注意：数据库自动建表，表名不能是关键字，比如check