
const express = require('express');
const http = require('http');
const fs = require('fs');
const socketio = require('socket.io');

const app = express();

app.use(express.static(__dirname+'/public'));

const server = http.createServer(app);
server.listen(3000,function(){
    console.log("Server running at http://localhost:3000");
});

const users = []; // 현재 접속한 인원을 저장하기 위한 배열 users
 
const io = socketio.listen(server);
io.sockets.on('connection',function(socket){

    console.log(io.sockets.in("chat").sockets);
    
    //prompt 로 전달받은 이름을 관리함
    socket.on("join",function(name){
        
        users.push({"name" : name, "id": socket.id});

        socket.join("chat");
        socket.emit('getMyId',socket.id); // 방금접속한 브라우저에게만 id를 파라미터로 보내준다. 

        // 지금 접속해 있는 소켓 모두에게 chat이라는 영역에 들어있는 모든 socket들을 보냄..
        // io.sockets.in("chat").sockets : 지금 접속해 있는 소켓들. 
        io.sockets.in("chat").emit("displayAllUsers",users);
    
    });

    socket.on("broadcast",function(data){

        if (data.receiver == 'all') {
             io.sockets.in("chat").emit('receive',data);
        }
        else {
            socket.emit("receive",data);
            io.sockets.in("chat").sockets[data.receiver].emit("receive",data);
        }
        
    });

    // socket이 끊어지면 호출된다.
    socket.on("disconnect",function(){
        console.log("연결이 끊어짐!! | "+ socket.id);
        
        for ( let i in users ){
            if ( users[i].id == socket.id ){
                io.sockets.in("chat").emit("receive",{
                    "name" : "system",
                    "chat" : users[i].name + "님이 퇴장하였습니다.",
                    "receiver" : "all",
                    "sender": ""
                });
                users.splice(i,1);
                break;
            }
        }
        io.sockets.in("chat").emit("displayAllUsers",users);

    });  

    socket.on("newPost",function(newPostUrl){
        io.sockets.in("chat").emit("popupNews",newPostUrl);
    });

});


app.use(function(request,response){
    fs.readFile("client.html","utf-8",function(err,data){
        response.type("text/html");
        response.send(data);
    });
});