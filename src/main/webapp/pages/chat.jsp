<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- кажется надо убрвать, но еще раз подумать  -->
<c:if test="{${empty(requestScope['sessionId'])}==0">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>
<html>
<head>
    <title>Chat</title>
    <link href="<c:url value="/styles/main.css" />" rel="stylesheet">
</head>
<body>
<table class="table_main">
    <tr class="top top_name">
        <td colspan="3" class="td_top_name">
            <security:csrfInput/>
            <sec:authorize access="!isAuthenticated()">
                <c:redirect url="/logout"></c:redirect>
            </sec:authorize>
           <sec:authorize access="isAuthenticated()">
            <sec:authentication property="principal.username" />
           </sec:authorize>
        </td>
    </tr>
    <tr class="top">
        <td class="td_right_left_block">Friends:
            <br>
            <form method="post" name="search" action="chat/searchNicks?${_csrf.parameterName}=${_csrf.token}">
                <input type="text" placeholder="Введите ник собеседника" name="nicknameIn" required/>
                <input type="submit" name="searchBtn" value="Найти друга">
            </form>
        </td>
        <td>
            <p >Chat with: <br> <span id="chat_with"></span> </p>

        </td>
        <td class=""></td>
    </tr>
    <tr class="tr_body">
        <td >
            <table >
                    <c:forEach var="row" items="${requestScope['nicknameArr']}">
                        <tr class="tr_found_nicks"> <td onclick="chat_with.innerHTML= this.innerHTML;">
                           ${row.nickname}
                        </td></tr>
                    </c:forEach>
                <tr>
                    <td>

                    </td>
                </tr>
            </table>
        </td>
        <td>
            <form method="post" action="chat/messages?${_csrf.parameterName}=${_csrf.token}" name="messages">
                <table>

                    <tr>
                        <td class="messages">
                            test_msg <br>
                        <span id="log" ></span>
                        </td>
                    </tr>
                    <div id="sent_messages">

                    </div>
                <tr>
                    <td class="td_messages_input" >
                <input type="text" placeholder="Введите текст сообщения" name="msgText" id="in" required/>
                <input type="submit" name="sendMsg" onclick="send()" value="Отправить сообщение">
                       <!--onclick="alert('123');alert(sent_messages.innerHTML);sent_messages.innerHTML=sent_messages.innerHTML+'<tr><td>'+${requestScope['msg1']}'</td></tr>'"-->
                    </td>
                </tr>
                </table>
            </form>
        </td>
        <td ></td>
    </tr>

    <tr class="bottom">
        <td colspan="3">

        </td>
    </tr>
</table>


<<script type="text/javascript">
    var socket = new WebSocket("ws://localhost:8080/greeting");

    socket.onopen = function() {
        alert("Соединение установлено.");
    };

    socket.onclose = function(event) {
        if (event.wasClean) {
            alert('Соединение закрыто');
        } else {
            alert('Обрыв соединения');
        }
        alert('Код: ' + event.code + ' причина: ' + event.reason);
    };
    socket.onmessage = function(event) {
        var logarea = document.getElementById("log");
        logarea.value = event.data+"n"+logarea.value;
    };
    socket.onerror = function(error) {
        alert("Ошибка " + error.message);
    };

    function send() {
        var s = document.getElementById("in").value;
        socket.send(s);
    }

</script>
</body>
</html>
