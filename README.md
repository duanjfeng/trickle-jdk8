# trickle-jdk8: demos for jdk8 new features

初步了解了jdk8的新特性，尝试应用了部分主要API，以备查阅。<br/>
包括并发增强、函数、IO增强、lambda表达式、语言增强、Stream、新的时间函数、工具增强等8个部分。<br/>
参考[Java SE 8 for the Really Impatient](http://ptgmedia.pearsoncmg.com/images/9780321927767/samplepages/0321927761.pdf) by Cay S.Horstmann。<br/>

##concurrent
*   原子变量增强
*   Future增强
*   ConcurrentHashMap增强
*   锁增强（StampedLock）

##function
*	Supplier（生产者）
*	Consumer（一元消费者）
*	Predicate（断言）
*	Function（一元函数）
*	UnaryOperator（一元操作）
*	IntFunction（入参为int的一元函数）
*	ToIntFunction（出参为int的一元函数）
*	BiFunction（二元函数）
*	BinaryOperator（二元操作）
*   BiConsumer（二元消费者）

##io
*   文件访问增强

##lambda
*   lambda表达式的语法
*   方法引用

##lang
*   接口的默认方法和静态方法
*   注解增强
*	反射方法参数

##stream
*   stream之创建
*   stream之map（映射）
*   stream之filter（过滤）
*   stream之摘取和拼接
*   stream之简单聚合
*   stream之高级聚合
*   stream之收集
*   stream之并发

##time
*   获取时间
*   格式化和解析

##util
*   Comparator增强
*   base64编码
