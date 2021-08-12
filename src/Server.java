import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;

public class Server extends Thread{
	
	static ArrayList<Room> roomlist = new ArrayList<>();
	Player p;
	String forWhat;
	
	public Server(Player p,String f) {
		this.p = p;
		forWhat = f;
	}
	
	public void run()
	{
		//System.out.println("IN Thread for "+forWhat);
		if(forWhat.equals("create"))
		{
			label:
			{
				while(true)
				{
					String roomname = p.receive();
					
					//System.out.println("Room name : "+roomname);
					
					int flag = 0;
					for(Room r:roomlist)
					{
						if(r.roomid.equals(roomname))
						{
							flag = 1;
							break;
						}
					}
					
					if(flag==0)
					{
						//System.out.println("VALID");
						p.send("valid");
						Room r = new Room(roomname,p);
						roomlist.add(r);
						break label;
					}
					else
					{
						//System.out.println("INVALID");
						p.send("invalid");
					}
				}
			}
		}
		else
		{
			label:
			{
				while(true)
				{
					
					String roomname = p.receive();
					Room room = null;
					int flag=0;
					for(Room r:roomlist)
					{
						if(r.roomid.equals(roomname))
						{
							flag=1;
							room = r;
							break;
						}
					}
					if(flag==0)
					{
						p.send("roomNotFound");
					}
					else
					{
						p.send("roomFound");
						int i = roomlist.indexOf(room);
						room.setSecondPlayer(p);
						room.startGame();
						roomlist.remove(i);
						break label;
					}
				}
			}
		}
		System.out.println("Total rooms : "+roomlist.size());
		for(Room r:roomlist)
			System.out.println(r.roomid);
	}
	
	
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try
		{
			System.out.println("Total rooms : "+roomlist.size());
			for(Room r:roomlist)
				System.out.println(r.roomid);
			
			int portno = 3137;
			try {
				serverSocket = new ServerSocket(portno);
				System.out.println("Server started");
				
				Socket s = null;
				
				while(true)
				{
					
						s = serverSocket.accept();
						DataOutputStream dos = new DataOutputStream(s.getOutputStream());
						DataInputStream dis = new DataInputStream(s.getInputStream());
						
						Player player = new Player(s, dis, dos);
						
						String selectedOption = player.receive();
						
						if(selectedOption.equalsIgnoreCase("create"))
						{
							//System.out.println("IN CREATE");
							Server server = new Server(player,"create");
							server.start();
						}
						if(selectedOption.equalsIgnoreCase("join"))
						{
							//System.out.println("IN JOIN");
							Server server = new Server(player,"join");
							server.start();
						}
						
						
						
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		finally {
			try {
				serverSocket.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

class ClientHandler extends Thread
{
	Player player1;
	Player player2;
	
	public ClientHandler(Player p1,Player p2) {
		
		player1 = p1;
		player2 = p2;
				
		// TODO Auto-generated constructor stub
	}
	
	public void run()
	{
		String status = "123456789";
		int c=0;
		try
		{
			try {
				
				player1.dos.writeUTF("X");
				player2.dos.writeUTF("O");
				
				while(true)
				{
					String ans = TicTacToe.whoWon(status);
					if(ans.equalsIgnoreCase("NotDraw"))
					{
						if(c%2==0)
						{
							player1.send("yourturn"+"#"+status);
							player2.send("notyourturn"+"#"+status);
							status = player1.receive();
						}
						else
						{
							player2.send("yourturn"+"#"+status);
							player1.send("notyourturn"+"#"+status);
							status = player2.receive();
						}
						c++;
					}
					else if(ans.equals("X"))
					{
						player1.send("won"+"#"+status);
						player2.send("lose"+"#"+status);
						break;
					}
					else if(ans.equals("O"))
					{
						player1.send("lose"+"#"+status);
						player2.send("won"+"#"+status);
						break;
					}
					else
					{
						player1.send("draw"+"#"+status);
						player2.send("draw"+"#"+status);
						break;
					}
				}
				
				
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
		}
		finally 
		{
			player1.closeEverything();
			player2.closeEverything();
		}
		
		
	}
	
}
