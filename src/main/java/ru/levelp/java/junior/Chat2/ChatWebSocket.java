package ru.levelp.java.junior.Chat2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/echo")
public class ChatWebSocket {
        private Logger logger = Logger.getLogger(this.getClass().getName());
        @OnOpen
        public void start(Session session) {
                logger.info("Connected ... " + session.getId());
                try {
                        session.getBasicRemote().sendText("start");
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

        @OnClose
        public void end(Session session, CloseReason closeReason) {
                logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
        }

        @OnMessage
        public String incoming(String message) {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                try {
                        logger.info("Received ...." + message);
                        String userInput = bufferRead.readLine();
                        return userInput;
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }

        @OnError
        public void onError(Throwable t) throws Throwable {
            //TODO
        }
}
