/**
 * Created by jingchun.zhang on 2018/12/27.
 */
package com.jc.es.config;

import java.net.InetAddress;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/12/27
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.jc.es.dao")
public class ElasticsearchConfiguration {

    @Bean
    public Client client() {
        Settings settings = Settings.builder().put("cluster.name", "???").build();
        TransportClient client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new
//            InetSocketTransportAddress("127.0.0.1", 9300));
//
////        Client client = TransportClient.builder().settings(settings) .build().addTransportAddress( new InetSocketTransportAddress( InetAddress
////            .getByName("127.0.0.1"), 9300));
//
////        Settings settings = Settings.settingsBuilder().put("cluster.name","my-application").put("client.transport.sniff", true).build();
//        client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipAddress1), iPort1));


        return client;
    }


}
