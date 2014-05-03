
package server6;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server6
{
	private int port_no=4321;
	private ServerSocket src;
	private static Socket skt=null;

	Server6()
	{
		try{
			src = new ServerSocket(port_no);
			System.out.println("Socket Initialized");
			commIn();		
		}catch(Exception e){e.printStackTrace();}
	}

	public void commIn()
	{
		try{
		while(true){
		skt=src.accept();		
		requestStart(skt);
		sender();
		}
		}catch(Exception e) {e.printStackTrace();}
	}

	public void sender()
	{
		String message="";

		    message =show();
			if(message.equalsIgnoreCase("quit"))
			{
				System.exit(0);
			}
			else
			{
				SendMessage(message);
			}

	}

	public static void SendMessage(String msg)
	{
		try{

			OutputStream out = skt.getOutputStream();
			ObjectOutputStream oos=new ObjectOutputStream(out);
			oos.writeObject(msg);
		}
		catch(Exception e)
		{}
	}

	public static String show()
	{
		String msg="";
		try{

			
			Scanner scn=new Scanner(System.in);
			msg=scn.nextLine();

		}
		catch(Exception e)
		{}
		return msg;
	}


	public void requestStart(final Socket client)
	{
		Thread t = new Thread(){
			public void run()
			{
				try
				{
					InputStream is=client.getInputStream();
					ObjectInputStream ois=new ObjectInputStream(is);
					Object msg = ois.readObject();
					System.out.println("====Client Say's====");
					System.out.println(msg);
					System.out.println("====You Answer========");
				}
				catch(Exception e){
				e.printStackTrace();
				}
			}			
		};
		t.start();
	}

	public static void main(String...args)
	{
		new Server6();
	}



}
