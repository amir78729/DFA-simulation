import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * @author Amirhossein Alibakhshi (id: 9731096)
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //in each step of this loop we can create and test an accepter:
        while (true){
            System.out.println("CHOOSE ONE OF THIS OPTIONS:");
            System.out.println(" A - USE A DEFAULT DFA");
            System.out.println(" B - CREATE YOUR OWN DFA");
            System.out.println(" C - CREATE DFA FROM A TEXT FILE");
            System.out.println(" Q - EXIT THE PROGRAM");
            String command = sc.nextLine();
            if (command.equals("Q") || command.equals("q")){
                System.out.println("END OF THE PROGRAM...");
                break;
            }else if (!command.equals("b") && !command.equals("B") && !command.equals("A") && !command.equals("a") && !command.equals("C") && !command.equals("c")){
                System.out.println("PLEASE ENTER A VALID INPUT!");
            }else{
                // we are going to have an accepter by user's command:
                Accepter accepter;

                // to choose how this DFA is going to be:
                if (command.equals("b") || command.equals("B")){
                    System.out.println("CREATING YOUR OWN DFA:");
                    accepter = new Accepter();
                }else if (command.equals("A") || command.equals("a")){
                    System.out.println("USING A DEFAULT DFA:");
                    accepter = new Accepter(true);
                }else{
                    System.out.println("USING A DFA DESCRIBED IN A TEXT FILE:");
                    System.out.println("CHOOSE ONE OF THIS OPTIONS:");
                    System.out.println(" A - THE TEXT FILE NAME IS \"DFA_Input_1.txt\"");
                    System.out.println(" B - THE TEXT FILE HAS ANOTHER NAME");
                    String cmdForTextFileName = sc.nextLine();
                    String filePath;
                    while(true){
                        if (cmdForTextFileName.toLowerCase().equals("a")){
                            filePath = "DFA_Input_1";
                            break;
                        }else if (cmdForTextFileName.toLowerCase().equals("b")){
                            System.out.println("ENTER THE FILE'S NAME (NO \".txt\")");
                            filePath = sc.nextLine().trim();
                            break;
                        }else{
                            System.out.println("PLEASE ENTER A VALID OPTION!");
                        }
                    }
                    try {
                        accepter = new Accepter(".\\"+ filePath +".txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.out.println("FAILED TO READ THE FILE\nPLEASE TRY AGAIN!");
                        continue;
                    }
                }

                accepter.getAcceptorsStatus();
                System.out.print("NOW ");
                // in each step of this loop, the accepter will check an input string and check if it's valid or not:
                while (true){
                    System.out.println("YOU CAN ENTER STRINGS WITH THIS ALPHABETS:");
                    System.out.println(accepter.getAccepterAlphabet());
                    System.out.println("ENTER \" /quit \" TO GO BACK.");
                    System.out.println("ENTER \" /status \" TO GET THE DFA'S STATUS.");
                    String inputString = sc.nextLine();
                    if (inputString.toLowerCase().equals("/quit".trim())){
                        System.out.println("END OF GETTING INPUT STRINGS...");
                        break;
                    }else if (inputString.toLowerCase().equals("/status".trim())){
                        accepter.getAcceptorsStatus();
                    }else{
                        accepter.isTheInputStringValid(inputString);
                    }
                    System.out.println("\n///////////////////////////////////////////\n");
                }
            }
        }
    }
}
