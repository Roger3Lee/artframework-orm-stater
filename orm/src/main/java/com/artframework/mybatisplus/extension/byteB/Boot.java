//package com.artframework.mybatisplus.extension.byteB;
//
//import com.artframework.mybatisplus.extension.model.dataobject.BaseDO;
//import com.artframework.mybatisplus.extension.model.dataobject.BaseDoInterceptor;
//import net.bytebuddy.ByteBuddy;
//import net.bytebuddy.description.type.TypeDescription;
//import net.bytebuddy.dynamic.DynamicType;
//import net.bytebuddy.implementation.FixedValue;
//import net.bytebuddy.implementation.MethodDelegation;
//import net.bytebuddy.matcher.ElementMatchers;
//import org.aspectj.apache.bcel.Repository;
//
//import java.lang.reflect.Modifier;
//
//public class Boot {
//
//    public static void main(String[] args) throws Exception {
//        // 1 基本用法    https://juejin.cn/post/6844904151546069006
//        base();
//
//        // 2 耗时、入参出参    https://juejin.cn/post/6844904155102838792
//        use();
//
//        // 3 使用委托实现抽象类方法并注入自定义注解信息   https://juejin.cn/post/6844904159427166221
////        abstractAnnonation();
//    }
//
////    private static void abstractAnnonation() {
////        // -- 生成含有注解的泛型实现字类
//        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
//                // 创建复杂类型的泛型注解
//                .subclass(TypeDescription.Generic.Builder.parameterizedType(Repository.class, String.class).build())
//                // 添加类信息包括地址
//                .name(Repository.class.getPackage().getName().concat(".").concat("UserRepository"))
//                // 匹配处理的方法
//                .method(ElementMatchers.named("queryData"))
//                // 交给委托函数
//                .intercept(MethodDelegation.to(BaseDoInterceptor.class))
//                // 拦截对应注解
////                .annotateMethod(AnnotationDescription.Builder.ofType(RpcGatewayMethod.class)
////                        .define("methodName", "queryData")
////                        .define("methodDesc", "查询数据").build())
////                .annotateType(AnnotationDescription.Builder.ofType(RpcGatewayClazz.class)
////                        .define("alias", "dataApi")
////                        .define("clazzDesc", "查询数据信息")
////                        .define("timeOut", 350L).build())
//                .make();
////        // 输出类信息到目标文件夹下
////        outputClazz(dynamicType.getBytes(), 4);
////    }
//
//    private static void use() throws Exception {
//        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
//                // 继承的类
//                .subclass(BaseDO.class)
//                // 被监控的方法
//                .defineMethod("main", void.class, Modifier.PUBLIC)
//                // 具体监控实现类
//                // 定义参数
//                .withParameter(String[].class, "args")
//                // 定义一个局部变量为"Hello World!"
//                .intercept(MethodDelegation.to(BaseDoInterceptor.class))
//                .make();
//        // 加载类
//        Class<?> clazz = dynamicType.load(Boot.class.getClassLoader())
//                .getLoaded();
//        // 调用方法，测试监控效果
//        // ！！！用反射调用才有效果，
//        //      那premain怎么结合bytebuddy使用?通过agentBuilder.with(listener).installOn(inst)绑定
//        clazz.getMethod("main").invoke(clazz.newInstance(), "10001");
//    }
//
//    private static void base() throws Exception {
//        // 1 第一次输出一个简单的结构体
//        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
//                // 定义继承的类
//                .subclass(Object.class)
//                // 定义命名空间
//                .name("com.bd")
//                .make();
//        // 输出类字节码
//
//        // 2 增加一些参数、属性信息
//        DynamicType.Unloaded<?> dynamicType2 = new ByteBuddy()
//                // 定义继承的类
//                .subclass(Object.class)
//                // 定义命名空间
//                .name("com.bd")
//                // 定义一个main方法，public权限，并且是static
//                .defineMethod("main", void.class, Modifier.PUBLIC + Modifier.STATIC)
//                // 定义参数
//                .withParameter(String[].class, "args")
//                // 定义一个局部变量为"Hello World!"
//                .intercept(FixedValue.value("Hello World!"))
//                .make();
//
//        // 3 委托函数使用
//        DynamicType.Unloaded<?> dynamicType3 = new ByteBuddy()
//                // 定义继承的类
//                .subclass(BaseDO.class)
//                // 定义命名空间
//                .name("com.bd")
//                // 定义一个main方法，public权限，并且是static
//                .defineMethod("main", void.class, Modifier.PUBLIC)
//                // 定义参数
//                .withParameter(String[].class, "args")
//                // 定义委托的类，委托调用Hi.main()方法
//                .intercept(MethodDelegation.to(BaseDoInterceptor.class))
//                .make();
//        // 加载类
//        Class<?> clazz = dynamicType3.load(Boot.class.getClassLoader()).getLoaded();
//        // 反射调用
//        clazz.getMethod("main", String[].class).invoke(clazz.newInstance(), (Object) new String[1]);
//    }
//
////    private static void outputClazz(byte[] bytes, Integer num) {
////        FileOutputStream out = null;
////        try {
////            String pathName = Boot.class.getResource("/").getPath() + "ByteBuddyHelloWorld_" + num + ".class";
////            out = new FileOutputStream(pathName);
////            System.out.println("类输出路径：" + pathName);
////            out.write(bytes);
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            if (null != out) {
////                try {
////                    out.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
//
//
//}