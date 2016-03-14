<!DOCTYPE html>
<html>
<head>
    <title>WebSocket Stomp Receiving Example</title>
</head>
<body>
<div>
    <button>测试发送消息！</button>
    <h3>Messages:</h3>
    <ol id="messages"></ol>
</div>
<script type="text/javascript" src="//cdn.jsdelivr.net/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript">
    var websocket = new WebSocket("ws://localhost:8080/chat/1/ios/websocket");
    websocket.onopen = function (evnt) {
    };
    websocket.onmessage = function (evnt) {
        if (evnt.data == "h") {
            //websocket.send('o["pong"]');
        }
        $("#messages").append("(<font color='red'>" + evnt.data + "</font>)<br/>")
    };
    websocket.onerror = function (evnt) {
    };
    websocket.onclose = function (evnt) {
    }
    $("button").click(function () {
        websocket.send('{"head":{"type":"sendMessage","device":"Web","from":"2247","to":"U23155_G3063","toType":"group","sessionId":"1hdbujr9yvj5yalt5gdeo9ec8","mId":105,"mallId":"1"},"body":{"type":"text","content":"11111"}}');
    });
</script>

</body>
</html>