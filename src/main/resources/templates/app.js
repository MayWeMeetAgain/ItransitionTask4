var stompClient = null;

function connect(username) {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({ username: username, }, function() {
        console.log('Web Socket is connected');
        stompClient.subscribe('/topic/messages/' + username, function(response) {
            let data = JSON.parse(response.body);
        });
    });
}

function sendMsg(to, from, text) {
    stompClient.send("/app/chat/" + to, {}, JSON.stringify({
        fromUsername:from,
        message:text
    }))
}

$(function() {
    // $("form").on('submit', function(e) {
    //     e.preventDefault();
    // });
    $("#login").click(function() {
        connect($("#username").val());
    });
    $("#send").click(function() {
        stompClient.send("/qwa/app/hello", {}, $("#name").val());
    });
});