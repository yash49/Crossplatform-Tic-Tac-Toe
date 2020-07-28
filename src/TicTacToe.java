
public class TicTacToe {
	
	static String whoWon(String status)
	{
		if(status.charAt(0)=='X'&&status.charAt(1)=='X'&&status.charAt(2)=='X')
			return "X";
		if(status.charAt(3)=='X'&&status.charAt(4)=='X'&&status.charAt(5)=='X')
			return "X";
		if(status.charAt(6)=='X'&&status.charAt(7)=='X'&&status.charAt(8)=='X')
			return "X";
		if(status.charAt(0)=='X'&&status.charAt(3)=='X'&&status.charAt(6)=='X')
			return "X";
		if(status.charAt(1)=='X'&&status.charAt(4)=='X'&&status.charAt(7)=='X')
			return "X";
		if(status.charAt(2)=='X'&&status.charAt(5)=='X'&&status.charAt(8)=='X')
			return "X";
		if(status.charAt(0)=='X'&&status.charAt(4)=='X'&&status.charAt(8)=='X')
			return "X";
		if(status.charAt(2)=='X'&&status.charAt(4)=='X'&&status.charAt(6)=='X')
			return "X";
		
		if(status.charAt(0)=='O'&&status.charAt(1)=='O'&&status.charAt(2)=='O')
			return "O";
		if(status.charAt(3)=='O'&&status.charAt(4)=='O'&&status.charAt(5)=='O')
			return "O";
		if(status.charAt(6)=='O'&&status.charAt(7)=='O'&&status.charAt(8)=='O')
			return "O";
		if(status.charAt(0)=='O'&&status.charAt(3)=='O'&&status.charAt(6)=='O')
			return "O";
		if(status.charAt(1)=='O'&&status.charAt(4)=='O'&&status.charAt(7)=='O')
			return "O";
		if(status.charAt(2)=='O'&&status.charAt(5)=='O'&&status.charAt(8)=='O')
			return "O";
		if(status.charAt(0)=='O'&&status.charAt(4)=='O'&&status.charAt(8)=='O')
			return "O";
		if(status.charAt(2)=='O'&&status.charAt(4)=='O'&&status.charAt(6)=='O')
			return "O";
		
		for(int i=0;i<9;i++)
		{
			if(status.charAt(i)=='X' || status.charAt(i)=='O')
			{}
			else
				return "NotDraw";
		}
		
		return "Draw";
	}
	
	static void printBoard(String s)
	{
		String arr[]=s.split("");
		
		System.out.println();
		
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(" "+arr[i]);
			
			if((i+1)%3!=0)
			System.out.print(" |");
			else
			{
	
				System.out.println();
				if(i!=8)
				System.out.println("-----------");
			}
		}
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		printBoard("012345678");
	//	System.out.println(whoWon("XOXOXOOXO"));
	}
	
}
