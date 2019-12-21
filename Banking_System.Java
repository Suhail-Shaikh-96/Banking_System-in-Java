import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class bank extends JFrame implements ActionListener,MenuListener
{
	JTable jt;
	javax.swing.JScrollPane jScrollPane1;
JTextField txt[];
JTextArea ta;
JButton btn1,btn2;
JLabel lbl[];
String text[]={"Accno","Name","Address","Branch","Balance"};
JMenuBar bar;
JMenu menu1,menu2;
JMenuItem mitem[];
String label[]={"Create account","Display All Accs","Withdraw Amount","Deposit Amount","Search Account","Delete Account","Exit"};

public bank()
	{
    jScrollPane1 = new javax.swing.JScrollPane();
	setTitle("Banking System");	
	JPanel pnl1=new JPanel(new GridLayout(5,2));  //JPanel for create account screen labels and textbox	
	txt=new JTextField[5];                       //Area for entering the text in TextField next to text label for create account screen	
	lbl=new JLabel[5];                           //Labels for TextField for create account screen	

	Container c=getContentPane();                

	bar=new JMenuBar();
	setJMenuBar(bar);

	menu1=new JMenu("Tasks");
	menu2=new JMenu("About");
	menu2.addMenuListener(this);

	bar.add(menu1);	   //Adding menu1 to JMenuBar
	bar.add(menu2);	   //Adding menu2 to JMenuBar
	
	mitem=new JMenuItem[6];    //Adding MenuItems in menu1 i.e tasks
	for(int i=0;i<=6;i++)
		{
		mitem[i]=new JMenuItem(label[i]);   //Assigns labels to menuItem which are defined in line 17
		mitem[i].addActionListener(this);
		menu1.add(mitem[i]);
		}
	
	for(int i=0;i<lbl.length;i++)
		{
		lbl[i]=new JLabel(text[i],JLabel.CENTER);   //Creating 5 labels appearing in panel 1 in create account screen
		lbl[i].setFont(new Font("sans-serif",Font.BOLD,16));
		pnl1.add(lbl[i]);                           // pnl1 is JPanel for create account screen 

		txt[i]=new JTextField();                      //Area for entering the text in TextField next to text label for create account screen	
		txt[i].setForeground(Color.BLACK);
		txt[i].setDisabledTextColor(Color.BLACK);
		txt[i].setFont(new Font("sans-serif",Font.BOLD,16));
		pnl1.add(txt[i]);
		}
	c.add(pnl1,"Center");              //Adding the Create account panel 1  to container

	JPanel pnl2=new JPanel(new FlowLayout(FlowLayout.RIGHT));
	btn1=new JButton("Create");	
	btn1.addActionListener(this);
	pnl2.add(btn1);                 //Adding create button to buttons panel 2 in create account screen 
	
	btn2=new JButton("Exit");	    
	btn2.addActionListener(this);
	pnl2.add(btn2);                 //Adding Exit button to buttons panel 2 in create account screen              

	add(pnl2,"South");
	
	jt.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseClicked(java.awt.event.MouseEvent evt) {
	    	jTableMouseClicked(evt);
	    }
	});
	jScrollPane1.setViewportView(jt);
//	   setVisible(true);
	}

public void menuCanceled(MenuEvent e) 
	{}
public void menuDeselected(MenuEvent e) 
	{}
public void menuSelected(MenuEvent e) 
	{
	JMenu s=(JMenu)e.getSource();
	
	if(s==menu2)
		JOptionPane.showMessageDialog(null,"Developers: Suhail : Shreyak : Samar : Sweta : Nirali");	
	} 

public void actionPerformed(ActionEvent e)
	{
	String cmd=e.getActionCommand();  //tells which action is performed by giving the text on that 
			
	if(cmd.equalsIgnoreCase("exit"))
		System.exit(0);

	 //Action after you click on the deposit amount MenuItem in menu 1. 
	if(cmd.equalsIgnoreCase("deposit amount"))    
		{
		for(int i=2;i<lbl.length;i++)        //disable the labels from 2 to 4  as only 0 and 1 are required where 0 is 'Accno' and 1 is 'Amount'
			{
			lbl[i].setVisible(false);
			txt[i].setVisible(false);
			btn1.setText("Deposit");       //Display Deposit on btn1
			}
		for(int i=1;i<txt.length;i++)	   //Set value in all textbox to null
			{
			txt[i].setText("");
			txt[i].setEnabled(true);
			}	
		lbl[1].setText("Amount");           //Display Amount on 2nd label and 1st label is by default 'Accno' in all screens
		}    //Deposit screen ends

	
	 //Action after you click on the  withdraw Amount MenuItem in menu 1.	
	if(cmd.equalsIgnoreCase("withdraw amount"))
		{
		for(int i=2;i<lbl.length;i++)         
			{
			lbl[i].setVisible(false);
			txt[i].setVisible(false);
			btn1.setText("Withdraw");         //Display Withdraw on btn1
			}
		for(int i=1;i<txt.length;i++)	   
			{
			txt[i].setText("");
			txt[i].setEnabled(true);
			}	
		lbl[1].setText("Amount");            //Display Amount on 2nd label
		} //withdraw screen ends

	
	if(cmd.equalsIgnoreCase("withdraw"))   //If user clicks on withdraw button in 'withdraw amount' screen perform function withdraw().
		withdraw();	

	if(cmd.equalsIgnoreCase("deposit"))    //If user clicks on deposit button in 'deposit amount' screen perform function deposit()
		deposit();
	
	if(cmd.equalsIgnoreCase("create"))     //If user clicks on create button in 'create account' screen perform function add()
		add();
	
	
	//Action after you click on the  'Create Account' MenuItem in menu 1.
	if(cmd.equalsIgnoreCase("Create Account"))
		{
		for(int i=2;i<lbl.length;i++)  //here all labels and textbox are true since it needs all 5 of them. 0 and 1 are already setvisible(true)
			{
			lbl[i].setVisible(true);
			txt[i].setVisible(true);
			}

		for(int i=0;i<txt.length;i++)	
			{
			txt[i].setText("");
			txt[i].setForeground(Color.BLACK);
			txt[i].setEnabled(true);
			}	
		btn1.setText("Create");
		lbl[1].setText("Name");
		} //Create Account screen ends

	
	if(cmd.equalsIgnoreCase("display all accs"))   //If user clicks on 'Display all Accs' menuItem perform function displayall()
		displayall();
		
	//Adding delete account screen
	//Action after you click on the  'delete Account' MenuItem in menu 1.
	if(cmd.equalsIgnoreCase("delete account"))
	{
	for(int i=2;i<lbl.length;i++)
		{
		lbl[i].setVisible(true);
		txt[i].setVisible(true);
		}

	for(int i=1;i<txt.length;i++)	
		{
		txt[i].setText("");
		txt[i].setEnabled(false);
		}	
	btn1.setText("Search");
	lbl[1].setText("Name");
	btn2.setText("delete");
	}//delete Account screen ends

	if(cmd.equalsIgnoreCase("delete"))     //If user clicks on delete button in 'delete account' screen perform function add()
		delete();
	
	
	//Action after you click on the  'Search Account' MenuItem in menu 1.
	if(cmd.equalsIgnoreCase("search account"))
		{
		for(int i=2;i<lbl.length;i++)
			{
			lbl[i].setVisible(true);
			txt[i].setVisible(true);
			}

		for(int i=2;i<txt.length;i++)	
			{
			txt[i].setText("");
			txt[i].setEnabled(false);
			}	
		btn1.setText("Search");
		btn2.setText("SearchbyName");
		lbl[1].setText("Name");
		}//Search Account screen ends
	
	if(cmd.equalsIgnoreCase("search"))
		search('o');
	
	if(cmd.equalsIgnoreCase("SearchbyName"))
		Uptadetable();
	}

public void Uptadetable() //This will display the Jtable when we search account by Name 
{
	
	try {
 String tname;
 tname=txt[1].getText();

 DefaultTableModel model = new DefaultTableModel(new String[]{"accno", "name", "address","branch","balance"}, 0);
 
 Class.forName("com.mysql.jdbc.Driver"); //static block of Java class that loads the driver
 
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost/eventsdb","root","india123");  //Get  connection to database
	PreparedStatement psmt=con.prepareStatement("select * from bank where name="+tname);	             //create a prepared statement to prevent sql injection attacks
	ResultSet rs=psmt.executeQuery(); 
	
	while(rs.next())
	{
	    String d = rs.getString("accno");
	    String e = rs.getString("name");
	    String f = rs.getString("address");
	    String g = rs.getString("branch");
	    String h = rs.getString("balance");
	    
	    model.addRow(new Object[]{d, e, f,g,h});
	}
	
	jt.setModel(model);
    jt.setVisible(true);

		for(int i=0;i<txt.length;i++)
		txt[i].setText("");

	}
	catch(Exception ex)
	{
	System.out.println("\n! Error : "+ex+"\n");
	}
}//update table ends


public void jTableMouseClicked(java.awt.event.MouseEvent evt) //When click on specific entry in the jtable that row is taken in textbox //Added for retrieving specific record if there are multiple account holders with same name as suggested by prof
{
	int index=jt.getSelectedRow();
	TableModel model=jt.getModel();
	
	String accno=model.getValueAt(index, 0).toString();
	String name=model.getValueAt(index, 1).toString();
	String address=model.getValueAt(index, 2).toString();
	String branch=model.getValueAt(index, 3).toString();
	String balance=model.getValueAt(index, 4).toString();

	txt[0].setText(accno);
	txt[1].setText(name);
	txt[2].setText(address);
	txt[3].setText(branch);
	txt[4].setText(balance);
	
}


public void add()  //method after clicking on 'create' button in 'Create account' menuitem
	{
	try{
	String accno,cname,address,branch,balance;

	accno=txt[0].getText();
	cname=txt[1].getText();
	address=txt[2].getText();
	branch=txt[3].getText();
	balance=txt[4].getText();

	Class.forName("com.mysql.jdbc.Driver"); //static block of Java class that loads the driver
	
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost/eventsdb","root","india123");  //Get  connection to database
	PreparedStatement psmt=con.prepareStatement("insert into bank values(?,?,?,?,?)");	             //create a prepared statement to prevent sql injection attacks
	psmt.setString(1,accno);
	psmt.setString(2,cname);
	psmt.setString(3,address);
	psmt.setString(4,branch);
	psmt.setString(5,balance);
	int status=psmt.executeUpdate();  //it tells number of rows affected  by the above sql preparedstatement. So if a new entry is added it will show msg "Account created" else "Account not created"
	if(status==1)	
		JOptionPane.showMessageDialog(this,"Account created ...");
	else
		JOptionPane.showMessageDialog(this,"Acccount not created ...");
	for(int i=0;i<txt.length;i++)
		txt[i].setText("");
	}
	
	catch(Exception ex)
		{
		System.out.println("\n! Error : "+ex+"\n");
		}
	}	


public void delete()
{
	try 
	{
	int taccno;
	taccno=Integer.parseInt(txt[0].getText());

		Class.forName("com.mysql.jdbc.Driver"); //static block of Java class that loads the driver
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost/eventsdb","root","india123");  //Get  connection to database
		PreparedStatement psmt=con.prepareStatement("delete from bank where aacno="+taccno);	             //create a prepared statement to prevent sql injection attacks
		
		int status=psmt.executeUpdate();  //it tells number of rows affected  by the above sql preparedstatement. So if a new entry is deleted it will show msg "Account deleted" else "Account not deleted"
		if(status==1)	
			JOptionPane.showMessageDialog(this,"Account deleted ...");
		else
			JOptionPane.showMessageDialog(this,"Acccount not deleted ...");
		for(int i=0;i<txt.length;i++)
			txt[i].setText("");
		
	}
	catch(Exception ex)
	{
		System.out.println("\n! Error: "+ex+"");
	}
}
//method for displaying the content after you click on "Display All Accs" 
public void displayall()
	{
	try{
	String s1=width("Accno",10)+width("Name",20)+width("Address",30)+width("Branch",30)+width("Balance",10)+"\n";   //used for adding blank space after each object(word)
	
	for(int i=0;i<110;i++)
		s1+="-";           //adding line  ----------------------------------------------------
	s1+="\n";
	try{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	
	Connection con=DriverManager.getConnection("jdbc:odbc:ccit");
	Statement smt=con.createStatement();	
	ResultSet rs=smt.executeQuery("select * from bank");  //store result in rs.
	while(rs.next())
		{
		s1+=width(rs.getString(1),10);
		s1+=width(rs.getString(2),20);
		s1+=width(rs.getString(3),30);
		s1+=width(rs.getString(4),30);
		s1+=width(rs.getString(5),10)+"\n";
		}
	con.close();
	}catch(Exception ex)
		{
		System.out.println("! ERROR : "+ex.getCause());
		}
	
	display d=new display();
	d.setLocation(300,300);
	d.setSize(600,300);
	d.disp(s1);	
	d.setVisible(true);
	}catch(Exception ex)
		{
		System.out.println("\n! ERROR : "+ex+"\n");
		}
	}
//method for adding space after line
public String width(String s,int n)
	{
	int i=1;
	for(i=s.length();i<=n;i++)
		s+=" ";
	return s;
	}

//method for searching the info for acc no.
public boolean search(char ch)
	{
	String s="";
	int found=0;
	boolean recfound=false;
	try{
	int taccno;
	taccno=Integer.parseInt(txt[0].getText());

	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
	Connection con=DriverManager.getConnection("jdbc:odbc:ccit");
	Statement smt=con.createStatement();	
	ResultSet rs=smt.executeQuery("select * from bank where accno="+taccno);
	s+="Account Information \n\n";
	for(int i=0;i<40;i++)
		s+="-";
	s+="\n\n";
	if(rs.next())
		{
		if(ch=='o')
			{
			txt[0].setText(rs.getString(1));
			txt[1].setText(rs.getString(2));
			txt[2].setText(rs.getString(3));
			txt[3].setText(rs.getString(4));
			txt[4].setText(rs.getString(5));	
			found=1;
			}
		else
			recfound=true;
		}
	con.close();

	if(found==0&&ch=='o')
		{
		JOptionPane.showMessageDialog(this,"Record not found ...");
		for(int i=0;i<txt.length;i++)
			txt[i].setText("");
		}
	}catch(Exception ex)
		{
		System.out.println("\n"+ex+"\n");
		}
	return recfound;
	}

//method when you click on deposit button in deposit amount menuitem
public void deposit()
	{
	try{
	int a=Integer.parseInt(txt[0].getText());		
	boolean s1=search('d');	
	
	if(!s1)
		{
		JOptionPane.showMessageDialog(this,"Record not found");
		for(int i=0;i<txt.length;i++)
			txt[i].setText("");
		}
	else
		{	
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	
		Connection con=DriverManager.getConnection("jdbc:odbc:ccit");
		PreparedStatement psmt=con.prepareStatement("update bank set balance=balance+"+txt[1].getText()+" where accno="+txt[0].getText());	
		int status=psmt.executeUpdate();
		if(status==1)
			JOptionPane.showMessageDialog(null,"Amount Deposited ...");
		for(int i=0;i<txt.length;i++)
			txt[i].setText("");                                //if record not found set all the text boxes null
		}
	}catch(Exception ex)
		{
		System.out.println("\n"+ex+"\n");
		}
	}

//method that executes when you click on withdraw
public void withdraw()
	{
	try{
	int a=Integer.parseInt(txt[0].getText());		
	boolean found=search('d');	
	if(!found)	
		{
		JOptionPane.showMessageDialog(null,"Record not found...");	
		for(int i=0;i<txt.length;i++)
			txt[i].setText("");                         //if record not found set all the text boxes null
		}
	else
		{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
		Connection con=DriverManager.getConnection("jdbc:odbc:ccit");
		Statement smt=con.createStatement();	
		ResultSet rs=smt.executeQuery("select balance from bank where accno="+a);
		int balance=0;
		if(rs.next())
			balance=rs.getInt(1);                         //current balance before withdrawing
		int amount=Integer.parseInt(txt[1].getText());    //amount to be withdrawn
		if((balance-amount)>=500)
			{
			smt.executeUpdate("update bank set balance=balance-"+amount+" where accno="+txt[0].getText());
						
			JOptionPane.showMessageDialog(null,"Amount Withdrawn ...");
			for(int i=0;i<txt.length;i++)
				txt[i].setText("");
			}
			else
				{
				JOptionPane.showMessageDialog(null,"Insufficient amount ...");
				for(int i=0;i<txt.length;i++)
					txt[i].setText("");
				}
		}
	}catch(Exception ex)
		{
		System.out.println("\n"+ex+"\n");
		}
	}
}

class display extends JDialog
{
JTextArea ta;

public void disp(String s)
	{
	setModal(true);
	Container c=this.getContentPane();	
	ta=new JTextArea(s);
	ta.setFont(new Font("Monospaced",Font.BOLD,15));
	c.add(new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),"Center");
	}
}

class banktestw
{
public static void main(String args[])
	{
	bank b=new bank();
	b.setSize(400,300);
	b.setLocation(400,300);
	b.setVisible(true);
	}
}