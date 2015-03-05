#redis发布与订阅功能

###一、创建publisher，使用redisTemplate发布消息的方法，将消息发布到channel中

###二、在spring配置文件中创建channel，构造方法中需指定渠道名

###三、创建subscriber，需要实现MessageListener接口的类，MessageListener接口中定义了onMessage方法，也就是接收消息的方法，每当Channel中有消息，onMessage方法会被自动调用

###四、将messageListener注册到RedisMessageListenerContainer中，messageListener包含每一对subscriber与channel的对应信息，可以有多个messageListener，每个messageListener必须注册到RedisMessageListenerContainer中
