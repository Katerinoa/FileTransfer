package com.practice.filetransfer.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Configuration
//该注解用于指定Redis配置类
@EnableRedisRepositories
public class RedisConfig {

	/* 使用@Value注解从应用程序的配置中读取Redis服务器的主机名，端口号和密码。 */
	@Value("${spring.redis.host}")
	String redisHost;
	@Value("${spring.redis.port}")
	int redisPort;
	@Value("${spring.redis.password}")
	String redisPassword;


	/**
	 * JedisConnectionFactory bean是一个连接到Redis服务器的工厂。它使用从应用程序配置中读取的主机名，端口号和密码来配置连接。
	 * 启用了连接池功能，并使用buildPoolConfig方法生成的JedisPoolConfig来配置连接池。
	 */
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName(redisHost);
		configuration.setPassword(redisPassword);
		configuration.setPort(redisPort);
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(configuration);
		connectionFactory.setUsePool(true);
		connectionFactory.setPoolConfig(buildPoolConfig());
		return connectionFactory;
	}


	/**
	 *  buildPoolConfig方法创建并配置JedisPoolConfig bean。
	 *  这个bean定义了连接池的最大连接数，最大/最小空闲连接数，测试连接的规则等。
	 */
	private JedisPoolConfig buildPoolConfig() {
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(128);
		poolConfig.setMaxIdle(128);
		poolConfig.setMinIdle(16);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setTestWhileIdle(true);
		poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
		poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
		poolConfig.setNumTestsPerEvictionRun(3);
		poolConfig.setBlockWhenExhausted(true);
		return poolConfig;
	}

	/**
	 *  RedisTemplate bean是一个模板类，用于执行Redis命令。使用之前定义的JedisConnectionFactory bean作为连接工厂，
	 *  使用StringRedisSerializer和JdkSerializationRedisSerializer定义键和值的序列化方式。
	 *  启用事务支持，并在设置所有属性后调用afterPropertiesSet方法。
	 */
	@Bean
	public RedisTemplate<?, ?> redisTemplate() {

		RedisTemplate<String, Object> template = new RedisTemplate<>();
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		// 指定使用的ClassLoader，防止Devtools的两种classLoader造成的CastException
		// Refer：https://github.com/spring-projects/spring-boot/issues/9444
		JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer(getClass().getClassLoader());
		/**
		 * JdkSerializationRedisSerializer是Spring提供的Redis序列化器
		 * 它使用Java的序列化机制将对象序列化为字节数组，并在需要时将字节数组反序列化为对象。
		 *
		 * 在这行代码中，它创建了一个JdkSerializationRedisSerializer实例，并使用getClass().getClassLoader()方法获取当前类的ClassLoader作为参数。
		 * 这个ClassLoader用于在反序列化时加载类。
		 *
		 * JdkSerializationRedisSerializer将被用于序列化RedisTemplate的值。
		 */
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(serializer);

		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(serializer);

		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
	}
}
