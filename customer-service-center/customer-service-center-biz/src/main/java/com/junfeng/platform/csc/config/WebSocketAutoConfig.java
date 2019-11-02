package com.junfeng.platform.csc.config;

import com.junfeng.platform.csc.Interceptor.TokenInterceptor;
import com.junfeng.platform.csc.Interceptor.UserInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class WebSocketAutoConfig implements WebSocketMessageBrokerConfigurer  {

    /*private final WebSocketDecoratorFactory webSocketDecoratorFactory;*/
    /*private final PrincipalHandshakeHandler principalHandshakeHandler;*/
	
	private final UserInterceptor userInterceptor;
	private final TokenInterceptor tokenInterceptor;

	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/online")     //开启/bullet端点
                .setAllowedOrigins("*")
				.addInterceptors(tokenInterceptor)
				/*.setHandshakeHandler(principalHandshakeHandler)*/
                .withSockJS();                      //使用sockJS
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

    	registry.enableSimpleBroker("/user");
    }

	/**
	 * 配置客户端入站通道拦截器
	 */
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.setInterceptors(userInterceptor);
	}

/*    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
    	registration.addDecoratorFactory(webSocketDecoratorFactory);
    }*/
}
