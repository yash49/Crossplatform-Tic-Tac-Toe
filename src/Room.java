
public class Room {
	
	Player player1;
	Player player2;
	String roomid;
	
	public Room(String r,Player p) {
		roomid = r;
		player1 = p;
		// TODO Auto-generated constructor stub
	}
	
	void setSecondPlayer(Player p)
	{
		player2 = p;
	}
	
	void startGame()
	{
		ClientHandler c = new ClientHandler(player1, player2);
		c.start();
	}
	
}
