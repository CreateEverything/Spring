package com.kaishengit.myAcitveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class ActiveMQTest {
    @Test
    public void addNewMessage() throws JMSException {
        //1.创建ConnectionFactory
        String brokerUrl = "tcp://localhost:61616";
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        //2.创建出Connection
        Connection connection = connectionFactory.createConnection();
        //开启
        connection.start();
        //3.创建出Session(是否使用事务,指定签收消息的模式)
        //AUTO_ACKNOWLEDGE  自动签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);//当使用事务的时候就必须在后面提交事务    使用手动签收的时候
        //4.创建 Destination 目的地对象
            //可创建出多种类型  test-Message较为常用
        Destination destination = session.createQueue("test-Message");
        //5.创建消息的生产者
        MessageProducer messageProducer = session.createProducer(destination);
        //设置持久化模式

        //6.创建消息
        TextMessage textMessage = session.createTextMessage("Hello,2");
        //7.发送消息
        messageProducer.send(textMessage);
        //提交事务

        //8.释放资源
        messageProducer.close();
        session.close();
        connection.close();
    }
    @Test
    public void getMessage() throws JMSException, IOException {
        //1. 创建连接工厂
        ConnectionFactory connectionFactory = new
                ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);//默认的代理网址
        //2. 创建并启动连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3. 创建Session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);//AUTO_ACKNOWLEDGE自动签收
        //4. 创建目的地对象
        Queue queue = session.createQueue("test-Message");
        //5. 创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //6. 获取消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e){
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }
}
// 当使用订阅的方式的时候，只需要将24 51 行 改成Topic