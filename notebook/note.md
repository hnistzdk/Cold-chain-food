##注解的使用
- @repository这个注解，其实根据以往这么久的开发经验，
  在dao层不用写这个注解spring也是可以实例化的。
  但是通常将dao接口注入到service层的时候，需要写@Resource这个注解（经验）。
  这里简单介绍下各种注解的使用。

- @Resource（这个注解属于J2EE的），默认安照名称进行装配，名称可以通过name属性进行指定 ，
  如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找，
  如果注解写在setter方法上默认取属性名进行装配。
  当找不到与名称匹配的bean时才按照类型进行装配。
  但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
  
```java
    @Resource(name="baseDao")
    private BaseDao baseDao;
```

- @Autowired 默认按类型装配（这个注解是属业spring的），默认情况下必须要求依赖对象必须存在，
  如果要允许null值，可以设置它的required属性为false，
  如：@Autowired(required=false) ，如果我们想使用名称装配可以结合
  @Qualifier注解进行使用，如下：

```java
@Autowired()
@Qualifier("baseDao")
private BaseDao baseDao;

```

- @Controller("Bean的名称")

    定义控制层Bean,如Action

- @Service("Bean的名称")

    定义业务层Bean

- @Repository("Bean的名称")

    定义DAO层Bean

- @Component
  
    定义Bean, 不好归类时使用.

###定义Bean的作用域和生命过程

- @Scope("prototype")

    值有:singleton,prototype,session,request,session,globalSession

- @PostConstruct
    相当于init-method,使用在方法上，当Bean初始化时执行。
  
- @PreDestroy
    相当于destroy-method，使用在方法上，当Bean销毁时执行。
  
###声明式事务
@Transactional
