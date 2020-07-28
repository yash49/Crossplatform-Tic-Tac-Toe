import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Player {
	final Socket socket;
	final DataInputStream dis;
	final DataOutputStream dos;
	
	public Player(Socket s,DataInputStream i,DataOutputStream o)
	{
		socket = s;
		dis = i;
		dos = o;
	}
	
	void send(String s)
	{
		try {
			dos.writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	String receive()
	{
		String s = "";
		
		try {
			s=dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	void closeEverything()
	{
		try {
			socket.close();
			dis.close();
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
