package ru.levelp.java.junior.Chat2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatFacadeDAO {
    private final EntityManager em;

    @Autowired
    public ChatFacadeDAO(EntityManager em) {
        this.em = em;
    }

    @Autowired
    public PasswordEncoder encoder;


    public User findUserByLogin(String login) {
        try {
            return em.createNamedQuery(User.FindByLogin, User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }
    }
    public List<User> findUserByNickName(String nickname) {
        try {
            return em.createNamedQuery(User.FindByNickname, User.class)
                    .setParameter("nickname", nickname)
                    .getResultList();
        } catch (NoResultException notFound) {
            return null;
        }
    }

    public User createUserAccount(String login, String password)throws EntityExistsException{
        return null;
    }
    public User createUser(String login, String nickname, String password) throws EntityExistsException {
        try
        {
            // Уникальными должны быть и мэйл и никнейм, посмотреть как отличить эксепшены, если не отличить разнести на 2 функции, возвращать пользователю читабельный ответ в случае ошибки
            em.getTransaction().begin();
            User user = new User();
            user.setLogin(login);
            user.setNickname(nickname);
            user.setPassword(encoder.encode(password));
            // С пользователем не должно быть проблем
            user.setNaviDate(new Timestamp(System.currentTimeMillis()));
            em.persist(user);
            em.getTransaction().commit();
            return user;
        }catch (Throwable t) {
            // Если Сессия уже запущена обработать
           // Если овторна вставка
            em.getTransaction().rollback();
        }
        return null;
    }
}
