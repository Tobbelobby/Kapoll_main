/**/
package com.example.Kapoll.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;



@Service
public class RabbitListeners {


    @RabbitListener(queues = {"PollResults"})
    public void messageReceiver(String pollResult) {
        System.out.println("New Poll Result: " + pollResult);


    }
    @RabbitListener(queues = {"Poll"})
    public void pollReceiver(String message){
        System.out.println("New Poll Created: "+ message);
    }


}
