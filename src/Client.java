import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


import java.awt.GraphicsConfiguration;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;

public class Client {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
	
		First frame = null;
		
		Socket s = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;
		
		
			//String ip = "127.0.0.1";
			
			System.out.print("Enter server IP : ");
			String ip = sc.next();
			int portno = 3137;
			
			try {
				s = new Socket(ip,portno);
		
		
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				
				int option=-1;
				while(!(option==1 || option==2))
				{
					System.out.println("Select any one : 1)Create room 2)Join room");
					option = sc.nextInt();
				}
				
				//telling server to create room
				if(option==1)
				{
					dos.writeUTF("create");	
					
					while(true)
					{
						System.out.println("Please Enter the name of the room : ");
						String roomname = sc.next();
						dos.writeUTF(roomname);
						
						String isValid = dis.readUTF();
						if(isValid.equalsIgnoreCase("valid"))
						{
							break;
						}
						System.out.println(roomname+" ROOM already exists.");
					}
							
					System.out.println("Waiting for the opponent to join the room");
				}
				else
				{
					dos.writeUTF("join");
					
					label:
					{
						while(true)
						{
							System.out.println("Please Enter the name of the room : ");
							String roomname = sc.next();
							dos.writeUTF(roomname);
							String isFound = dis.readUTF();
							if(isFound.equals("roomFound"))
								break label;
							
							System.out.println("Enter valid room name");
						}
					}
					
				}
				
				
				
				
				//obtaining symbol
				String symbol = dis.readUTF();
				
				//System.out.println("Your SYMBOL : "+symbol);
				
				
				String data = "";
				String entireString = "";
				String status = "";
				
				frame=new First(symbol);
			
				frame.btn[0].addActionListener(frame.al);
				frame.btn[1].addActionListener(frame.al);
				frame.btn[2].addActionListener(frame.al);
				frame.btn[3].addActionListener(frame.al);
				frame.btn[4].addActionListener(frame.al);
				frame.btn[5].addActionListener(frame.al);
				frame.btn[6].addActionListener(frame.al);
				frame.btn[7].addActionListener(frame.al);
				frame.btn[8].addActionListener(frame.al);
				
				
				
				while(true)
				{
					entireString = dis.readUTF();
					String x[] = entireString.split("#");
					data = x[0];
					status = x[1];
					int i=-1;
					
					if(data.equalsIgnoreCase("won"))
					{
						//TicTacToe.printBoard(status);
						//System.out.println("You won..!!!");

						//disable all buttons
						frame.updateStr(status);
						frame.disableAll();
						frame.frame.setTitle("You won..!!!");
						System.out.println("You won..!!!");
						break;
					}
					if(data.equalsIgnoreCase("lose"))
					{
							//	TicTacToe.printBoard(status);
					//	System.out.println("Sorry, You Lost. Better luck next Time.");
						
						//disable all buttons
						frame.updateStr(status);
						frame.disableAll();
						frame.frame.setTitle("Sorry, You Lost. Better luck next Time.");
						System.out.println("Sorry, You Lost. Better luck next Time.");
						break;
					}
					if(data.equalsIgnoreCase("draw"))
					{
						//	TicTacToe.printBoard(status);
					//	System.out.println("Game is Drawn");
						
						//disable all buttons
						frame.updateStr(status);
						frame.disableAll();
						frame.frame.setTitle("Game is Drawn");
						System.out.println("Game is Drawn");
						break;
					}
					
					if(data.equalsIgnoreCase("yourturn"))
					{
						//status is the string that is Ex. 123456789
						//enable valid buttons
						frame.updateStr(status);
						frame.enableSelected(status);
						frame.frame.setTitle("Take your turn");
						String tmp=status;
						while(tmp.equals(status)){

							tmp = First.createStr(frame.btn);

						}
						/*TicTacToe.printBoard(status);
						while(!status.contains(i+""))
						{
							System.out.println("Please enter valid position : ");
							i = sc.nextInt();
						}
						status = status.replace(i+"",symbol);*/

						status = tmp;
						//disable all button
						frame.disableAll();
						dos.writeUTF(status);
					}
					else
					{
						/*TicTacToe.printBoard(status);
						System.out.println("Waiting for opponent");*/
						frame.updateStr(status);
						frame.frame.setTitle("Waiting for opponent");
						//disable all buttons
						frame.disableAll();
						
					}
					
				}
				
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
			try {
				
				dis.close();
				dos.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}
}

 class First{
		JFrame frame;
		JButton btn[];
		static char state='O';
		ActionListener al=new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
  			{
    		// display/center the jdialog when the button is pressed
	 	   	JButton jb=(JButton)e.getSource();
	 	   	jb.setText(state+"");
	 	   	jb.setEnabled(false);
	 	   //	System.out.println(createStr(btn));
  			}
  		};
  		
			


		First(String symbol){

			state = symbol.charAt(0);
			

			frame = new JFrame("Flow Layout");
			//JButton button1, button2, button3,button4,button5,button6,button7,button8,button9;
			btn=new JButton[9];

			for(int i=0;i<btn.length;i++){
				btn[i]=new JButton(" ");
				btn[i].setFont(new Font("Arial", Font.PLAIN, 50));
			}

			for(int i=0;i<btn.length;i++){
				frame.add(btn[i]);
			}
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new GridLayout(3,3));
			frame.setSize(300,300);  
			frame.setVisible(true);  	
		}
		
		void disableAll(){
			for(int i=0;i<btn.length;i++){
				btn[i].setEnabled(false);
			}
		}

		void enableSelected(String str){
			char ch[]=str.toCharArray();
			for(int i=0;i<btn.length;i++){
				if(!(ch[i]=='O' || ch[i]=='X')){
				btn[i].setEnabled(true);
				}
				else{
				btn[i].setEnabled(false);	
				}
			}
		}
		
		void updateStr(String str){
			char ch[]=str.toCharArray();
			for(int i=0;i<btn.length;i++){
				if(ch[i]=='X' || ch[i]=='O')
					btn[i].setText(ch[i]+"");
				else
					btn[i].setText(" ");	
				}
			}
		

		static String createStr(JButton btn[]){
			String str="";
			for(int i=0;i<btn.length;i++){
				
				if(btn[i].getText().equals(" "))
					str=str+(i+1);
				else
					str=str+btn[i].getText();
			}
			return str;
		}
		
	}
