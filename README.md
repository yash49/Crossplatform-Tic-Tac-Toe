# Multiplayer Tic Tac Toe (Made with socket programming and multi-threading of JAVA)

• 	Developed Tic Tac Toe game which works over LAN connection.

•	 Used concepts of socket programming and multi-threading of JAVA programming in this game development.

•	 The unique feature of this project is that it allows multiple pair of players to play game concurrently.

•	 Game allows the players either to create a virtual game room or to join a virtual game room.

•	 When player choose to create a game room then system will generate a room which contains a unique name.

•	 When player choose to join a game room then system will ask for the game room name and once user enters it, he will be entered in that room where he can play against the other 
player who created that room.

•	 Once the game will get over, system will destroy that particular game room and then same name room will be available for re-creation.

## Instructions : -


1) First Run the Server file by writing `java Server` in your command prompt. Your Server has been started and your are good to go.

2) Now to play games between multiple players open command prompt in any computer which is in same network as the computer running `Server.java`. Then type `java Client` and Enter the IP address of Server, Which will start the game. Here you have 2 options :- Create Room or Join Room.

3) If a person has already created the room then you can join that room OR you can create your own room. If a room with already same name exists the you can not create the room with that name. To join the room you will have to give the name of the room that you want to join.

4) If you have created the room, Your game will not start until someone has joined your room. Once a person has joined your room the game will begin. After the game has been finished the room will be destroyed and name of that room is available.

###### Note :- You can create any numbers of rooms concurrently. 

#### This is the Compilation Sequence

![COMPILATION SEQUENCE](https://user-images.githubusercontent.com/48802492/88671239-b204fd80-d103-11ea-8b0f-80ace65c71f5.jpg)


### The Sequence of execution.

#### [1]
```
C:\Users\yashs\Downloads\src-20200728T121143Z-001\src>java Server
Total rooms : 0
Server started
```

#### [2]
```
C:\Users\yashs\Downloads\src-20200728T121143Z-001\src>java Client
Enter server IP : 127.0.0.1
Select any one : 1)Create room 2)Join room
1
Please Enter the name of the room :
ROOM1
Waiting for the opponent to join the room
```

#### [3]
```
C:\Users\yashs\Downloads\src-20200728T121143Z-001\src>java Client
Enter server IP : 127.0.0.1
Select any one : 1)Create room 2)Join room
2
Please Enter the name of the room :
ROOM2
Enter valid room name
Please Enter the name of the room :
ROOM1
```


### Here's the Screenshots

![ss1](https://user-images.githubusercontent.com/48802492/88670739-11164280-d103-11ea-89b8-f98ec353e8bd.png)

![ss2](https://user-images.githubusercontent.com/48802492/88670732-0f4c7f00-d103-11ea-8e8d-5a0e6eee2809.png)

![ss3](https://user-images.githubusercontent.com/48802492/88670737-107dac00-d103-11ea-8b5e-888fb21a7e13.png)



