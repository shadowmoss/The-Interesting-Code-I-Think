## Mybatis源码阅读，官方文档阅读
  mybatis通过构建生成SqlSession进行与数据库交互，SqlSession由SqlSessionFactory进行构建，SqlSessionFactory由SqlSessionFactoryBuilder构建。  
构建SqlSessionFactory需要配置mybatis-config.xml文件。该文件中主要可以配置`<environments></environments>`元素,可配置需要使用的`<environment></environment>`  
元素，在其中可以配置数据源以及数据源配套的事务管理。  
#### Environments元素
该元素下可配置多个`environment`元素,每一个元素可配置对应数据源，并设置对应`environemnt`的id,可在父级的`environments`上配置`default`属性,  
设置该SqlSessionFactory创建SqlSession时默认使用的`environment`。一个SqlSession只能使用单独一个`environment`,单个SqlSession无法使用多个`environment`  
如果有多个不同的数据源，只能通过重新生成SqlSessionFactory，并指定对应的`environment`id进行指定配置.
#### Mappers元素
mybatis-config.xml中的`<mappers>`元素也是通过SqlSessionFactory加载并解析为对应的`Statement`的,可通过给对应的映射语句，设置`dataBaseId`,  
判断当前该`Statement`是否需要添加到`Collection<Statement>`当中。而在解析创建数据源对象时,SqlSessionFactory会通过获取Connection对象下的getMetaData()方法  
获取当前数据源对应的厂商名称,如果配置根据不同的数据库厂商,使用不同的`Statement`。需要在mybatis-config.xml文件中配置:  
```xml
<databaseIdProvider type="DB_VENDOR">
        <property name="MySQL" value="mysql"/>
</databaseIdProvider>
```
这样会提供一个mybatis实现的`VendorDatabaseIdProvider.class`类型对象，他是`DatabaseIdProvider`接口的实现对象。这样在进行数据源创建时,会根据数据库厂商类型,记录当前SqlSessionFactory所用的  
数据库厂商。  
在`Mappers`映射器加载配置解析创建Statement时，会判断当前正在构建的Statement是否配置了`dataBaseId`,如果没有配置`dataBaseId`,则正常构建,如果有`dataBaseId`但是同当前配置的数据源厂商对应不上,则不对该语句进行构建。