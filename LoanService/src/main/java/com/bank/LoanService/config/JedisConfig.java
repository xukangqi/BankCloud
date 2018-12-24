package com.bank.LoanService.config;

import com.bank.LoanService.component.JedisClient;
import com.bank.LoanService.component.JedisClientCluster;
import com.bank.LoanService.component.JedisClientSingle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;
@Configuration
public class JedisConfig {

    @Bean
    public JedisClient jedisClient(){
        return new JedisClientSingle(new JedisPool("127.0.0.1",6379));

//        Set<HostAndPort> set=new HashSet<>();
//        set.add(new HostAndPort("127.0.0.1",7001));
//        set.add(new HostAndPort("127.0.0.1",7002));
//        set.add(new HostAndPort("127.0.0.1",7003));
//        set.add(new HostAndPort("127.0.0.1",7004));
//        set.add(new HostAndPort("127.0.0.1",7005));
//        set.add(new HostAndPort("127.0.0.1",7006));
//        return new JedisClientCluster(new JedisCluster(set));
    }
}
