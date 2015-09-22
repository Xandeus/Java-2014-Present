import java.util.Scanner;
public class LoanCalculator {
	public static void main(String args[]){
		String finished = "no";
		do{
			Scanner userInput = new Scanner(System.in);
			System.out.println("Enter your principal");
			int principal = userInput.nextInt();
			
			System.out.println("Enter your rate");
			double rate = userInput.nextDouble();
			
			System.out.println("Enter your weekly payment");
			int payment = userInput.nextInt();
			
			System.out.println("Enter number of weeks");
			int week = userInput.nextInt();
			do{
				System.out.println("Principal:" + principal + "\t" + " Rate:" + rate + "\t" +" Payment:" + (payment -(principal * rate)) + "\t" + " Week:" + week);
				week--;
				principal -= (payment -(principal * rate));
			}while(week>0);
			System.out.println("Again y/n");
			finished = userInput.next();
		}while(finished.equalsIgnoreCase("y"));
	}
}
