/**/
package com.example.Kapoll;
import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import com.example.Kapoll.Kapoll_db.tables.Poll;
import com.example.Kapoll.Kapoll_db.tables.Poll_result;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.stereotype.Service;



@Service
public class Receiver {

    public final CachingConnectionFactory cachingConnectionFactory;

    public Receiver(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @RabbitListener(queues = {"PollResults"})
    public void messageReceiver(Poll_result pollResult){

        System.out.println("Some thing:" + pollResult);


    }
    @RabbitListener(queues = {"Poll"})
    public void pollReceiver(Poll poll){

        System.out.println("Some thing: "+ poll);
    }



}
