package ru.levelp.java.junior.Chat2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChatController {
    private final ChatFacadeDAO dao;
    @Autowired
    public ChatController(ChatFacadeDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/")
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "chat/searchNicks")
    public String getNickname(@RequestParam String nicknameIn, ModelMap model) {
        List<User> user;
        user = dao.findUserByNickName(nicknameIn);
        if (!user.isEmpty()){
            model.put("nicknameArr", user);
        } else {
            model.put("nicknameArr", user);
        }
        return "chat";
    }
    @RequestMapping(method = RequestMethod.POST, value = "chat")
    public String test ( ModelMap model){
       // model.put("nicknameArr", user);
        return "chat";
    }
    @RequestMapping(method = RequestMethod.POST, value = "chat/messages")
    public String getMsg(@RequestParam String msgText, ModelMap model) throws Exception{
        //dao.emitMoney(amount);
        //double newBalance = dao.ensureRootUser().getBalance();

        // model.put("amount", amount);
       // model.put("msg1", msgText);

       // Message message= new Message();
       // message.setBody(msgText);

        return "chat";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/to_singup")
    public String getSingup() throws Exception{
        return "singup";
    }
    @RequestMapping(method = RequestMethod.POST, value = "singup")
    public String createUser(@RequestParam String login, String nickname, String password, String confirmPassword, ModelMap model) {

       // try {
        //вынести в веб
            User user = new User();

        user = dao.createUser(login, nickname, password);
            //double newBalance = dao.ensureRootUser().getBalance();
            //model.put("password", password);

        //}
        return "login";
    }
  /*  @RequestMapping(method = RequestMethod.POST, value = "/pages/login")
    public String findUser(@RequestParam String login, String password, ModelMap model) {

        // try {
        int sessionId = dao.createSession(login, password);
        // если логин и пароль верные, уходим в чат, иначе возвращаем в форму login ошибку
        if (sessionId != 0){

            return "chat";
        }else{
            model.put("error", "Неверный логин либо пароль");
            return "login";
        }

    }*/
}
