redis发布与订阅功能
===

一、创建publisher，使用redisTemplate发布消息的方法，将消息发布到channel中
```java
@Service
public class PubServiceImpl implements PubService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private ChannelTopic channelTopic;

	@Autowired
	private ChannelTopic channelTopic2;

	public void Publisher(String message) {
		stringRedisTemplate.convertAndSend(channelTopic.getTopic(), message);

		stringRedisTemplate.convertAndSend(channelTopic2.getTopic(), message + "channelTopic2");
	}
```

二、在spring配置文件中创建channel，构造方法中需指定渠道名
```xml
	<!-- Channel设置 -->
	<bean id="channelTopic" class="org.springframework.data.redis.listener.ChannelTopic">
		<constructor-arg value="user:topic" />
	</bean>
	
		<bean id="channelTopic2" class="org.springframework.data.redis.listener.ChannelTopic">
		<constructor-arg value="user:topic2" />
	</bean>
```

三、创建subscriber，需要实现MessageListener接口的类，MessageListener接口中定义了onMessage方法，也就是接收消息的方法，每当Channel中有消息，onMessage方法会被自动调用
```java
public interface SubService extends MessageListener {

}
public class SubServiceImpl implements SubService {

	@Autowired
	private ChannelTopic channelTopic;

	@Autowired
	private ChannelTopic channelTopic2;

	private MessageList messageList = new MessageList();

	public void onMessage(Message message, byte[] pattern) {
		System.out.println("------channelTopic-------" + channelTopic.getTopic());
		System.out.println("-----channelTopic2--------" + channelTopic2.getTopic());
		messageList.add(message.toString());
	}
```

四、将messageListener注册到RedisMessageListenerContainer中，messageListener包含每一对subscriber与channel的对应信息，可以有多个messageListener，每个messageListener必须注册到RedisMessageListenerContainer中
```xml
	<bean id="topicMessageListener" class="com.kuanxue.service.impl.SubServiceImpl">
	</bean>
		<bean id="topicContainer"
		class="org.springframework.data.redis.listener.RedisMessageListenerContainer"
		destroy-method="destroy">
		<property name="connectionFactory" ref="jedisConnectionFactory" />
		<property name="messageListeners">
			<map>
				<entry key-ref="topicMessageListener">
					<ref bean="channelTopic2" />
				</entry>
			</map>
		</property>
	</bean>
```