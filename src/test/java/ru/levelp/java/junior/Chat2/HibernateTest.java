package ru.levelp.java.junior.Chat2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class HibernateTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ChatFacadeDAO dao;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        ChatFacadeDAO chDAO = new ChatFacadeDAO(em);
        user = chDAO.createUser("root", "r1", "mmm");
        //em.persist(user);
        //em.getTransaction().commit();

        assertEquals("root", em.find(User.class, user.getUserId()).getLogin());
    }

/*    @Test
    public void testTransactions() throws Exception {
        em.getTransaction().begin();

        User user = new User();
        user.setLogin("root");
        user.setBalance(1.0);

        em.persist(user);

        Message t = new Message();
        t.setDate(new Date());
        t.setAmount(10);
        t.setUser(user);
        t.setTarget(user);

        em.persist(t);

        user.setBalance(10.0);

        em.getTransaction().commit();

        em.refresh(user);

        List<Message> messages = user.getMessages();
        assertNotNull(messages);
        assertEquals(1, messages.size());
        assertEquals(t, messages.get(0));
    }

    @Test
    public void testEnsureRootUSer() throws Exception {
        User user = dao.ensureRootUser();
        User user2 = dao.ensureRootUser();

        assertSame(user, user2);
    }

    @Test
    public void testEmitMoney() throws Exception {
        dao.ensureRootUser();

        dao.emitMoney(10.0);

        assertEquals(10.0, dao.findUser(User.RootUserName).getBalance(), 0.01);
        assertEquals(10.0, dao.findUser(User.RootUserName).getMessages().get(0).getAmount(), 0.01);

        dao.emitMoney(7.0);

        assertEquals(17.0, dao.findUser(User.RootUserName).getBalance(), 0.01);
        assertEquals(7, dao.findUser(User.RootUserName).getMessages().get(1).getAmount(), 0.01);
    }*/
}
