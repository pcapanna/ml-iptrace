package com.ml.iptrace.infrastructure.out_adapter.persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSocketConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

//@Configuration
//class PersistenceConfiguration {

//  @Bean
//  public LettuceConnectionFactory redisConnectionFactory() {
//    return new LettuceConnectionFactory(new RedisSocketConfiguration("/var/run/redis.sock"));
//  }
//}
