/**
 * Created by Admin on 2017-05-10.
 */
const http = require('http');
const socketio = require('socket.io');
const fs = require('fs');

const express = require('express');
const app = express();

app.use(express.static(__dirname+'/public'));

// http 서버를 Express 서버로 생성한다.
const server = http.createServer(app);
server.listen(3000,function () {
   console.log("Server runnig at http://localhost:3000");
});

const io = socketio.listen(server); // socket서버를 생성하고 실행합니다.
io.sockets.on('connection',function (socket) { // 클라이언트가 연결 되었을 때 실행될 이벤트입니다.

    // 접속한 브라우저 객체를 영역에 넣는다.
    // 접속한 브라우저 객체를 대화 풀에 접속 시킨다.
    socket.join("chat"); // join : 어디에 들어가라. chat 이라는 영역에 넣겠다.

    socket.on("broadcast",function (data) {
        console.log("Client send data : " + data);

        // socket.emit('receive',data);
        // 전체 브라우저에게 receive 이벤트를 수행 시킨다.
        io.sockets.in("chat").emit('receive',data);
    });

});

app.use(function (request, response) {
    fs.readFile("client.html","utf-8",function (err,data) {
        response.type("text/html");
        response.send(data);
    });
});