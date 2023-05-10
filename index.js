const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const { Server } = require("socket.io");
const io = new Server(server);
const PORT = process.env.PORT||3000;

var usernames = {};
var rooms = [
  { name: "main"}
];

app.get('/', (req, res) => {
    res.write(`<h1>Socket IO Start on Port : ${PORT}</h1>`);
    res.end();
});



io.on('connection', (socket) => {
  console.log('a user connected');
  socket.on('message',(ms)=>{
    io.emit('message',ms);
  });

  socket.on("createUser", function (username) {
    socket.username = username;
    usernames[username] = username;
    socket.currentRoom = "main";
    socket.join("main");

    console.log(`User ${username} created on server successfully.`);

    socket.emit("updateChat", "INFO", "You have joined the Main room");
    socket.broadcast
      .to("main")
      .emit("updateChat", "INFO", username + " has joined Maim room");
      io.sockets.emit("clearUsers", usernames);
    for (var user in usernames){
    io.sockets.emit("updateUsers", user);
    }
    socket.emit("updateRooms", rooms, "global");
  });

  socket.on("sendMessage", function (data) {
    io.sockets.to(socket.currentRoom).emit("updateChat", socket.username, data);
  });


  socket.on("createRoom", function (room) {
    if (room != null) {
      rooms.push({ name: room, creator: socket.username });
      io.sockets.emit("updateRooms", rooms, null);
    }
  });

  socket.on("updateRooms", function (room) {
    socket.broadcast
      .to(socket.currentRoom)
      .emit("updateChat", "INFO", socket.username + " left room");
    socket.leave(socket.currentRoom);
    socket.currentRoom = room;
    socket.join(room);
    socket.emit("updateChat", "INFO", "You have joined " + room + " room");
    socket.broadcast
      .to(room)
      .emit(
        "updateChat",
        "INFO",
        socket.username + " has joined " + room + " room"
      );
  });

  socket.on("disconnect", function () {
    console.log(`User ${socket.username} disconnected from server.`);
    delete usernames[socket.username];
    io.sockets.emit("clearUsers", usernames);
    for (var user in usernames){
    io.sockets.emit("updateUsers", user);
    }
    socket.broadcast.emit(
      "updateChat",
      "INFO",
      socket.username + " has disconnected"
    );
  });




});




server.listen(PORT, () => {
  console.log('listening on *:3000');
});