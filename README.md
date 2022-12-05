# 动态ORM框架
一个基于mybatis-plus框架开发的ORM扩展框架，实现全链路从接口入参到数据库入库的操作。

# 为什么会存在？
成熟问题的产品的数据模型已经稳定，但在实际推广过程中总存在一些数据模型上的定开需求，例如增加额外的参考字段等。这些字段往往只是用来做存储展示，并不存在业务逻辑。
传统方法下我们需要修改产品代码来满足客户需求。
基于以上情况，需要可以通过配置的方式实现从接口层到数据存储层的动态实现方式。

# 原理
1. 在spring boot应用启动前加载orm.xml配置文件配置
2. 基于动态代理的方式实现对mybatis plus的数据类进行动态代理并替换jvm中原本的数据表类
3. Mybatis plus解析数据类时加载到的实际类位代理后的类，实现对Mybatis plus的默认支持的sql的修改
4. 通过`@JsonAnySetter`和`@JsonAnyGetter`注解将请求中未mapping到实体属性的字段赋值到字段`feature` 的map中
5. 通过代理类中新增的数据库字段的代理`get`方法实现从`feature` map中读取属性并插入到数据库
6. 基于对Spring mvc的改造同样支持GET请求中的未映射的字段赋值到字段`feature` 的map中

# 约束
1. VO类需要继承BaseVO
2. DO类需要继承BaseDO
3. 需要确保数据传递过程中所有的DTO类继承BaseDTO,已保证从VO->DO过程中的数据能够正确的传递

# 样例
演示在用户数据模型下，通过修改orm.xml配置增加name和address属性以支持name和address的数据存储
## 原始代码中表结构如下
```
@TableName(value = "tbl_user_info",autoResultMap = true)
public class UserInfoDo extends BaseDO {
    /**
     *
     */
    @TableId
    @TableField("id")
    private Long id;

    @TableField("user_name")
    private String userName;

    @TableField("nick_name")
    private String nickName;

    @TableField("password")
    private String password;
}
```
## 数据库表新增字段name和address
```
CREATE TABLE `tbl_user_info` (
   `id` bigint(11) NOT NULL AUTO_INCREMENT,
   `user_name` varchar(255) DEFAULT NULL,
   `nick_name` varchar(255) DEFAULT NULL,
   `password` varchar(255) DEFAULT NULL,
   `feature` json DEFAULT NULL,
   `name` varchar(255) DEFAULT NULL,
   `address` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
```


## 配置ORM.XML配置文件（支持jar包外部配置）
```
<tables>
     <table type="com.example.demo.dataobject.UserInfoDo">
         <column name="name" type="java.lang.String"/>
         <column name="address" type="java.lang.String"/>
     </table>
 </tables>
```

## 调用增加用户接口增加name 和address入参
![Image](https://github.com/Roger3Lee/artframework-orm-stater/tree/master/png/新增.png)

## 查询
![Image](https://github.com/Roger3Lee/artframework-orm-stater/tree/master/png/查询.png)

# VNext
1. 支持对orm.xml配置字段的校验
