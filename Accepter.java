import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 * @author Amirhossein Alibakhshi (id: 9731096)
 */
public class Accepter {

    //fields:
    private ArrayList<State> accepterStates;
    private ArrayList<String> accepterAlphabet;
    private ArrayList<Path> accepterPaths;

    //constructor:
    /**
     * user input constructor
     * - alphabets
     * - states
     * - start state
     * - final state(s)
     * - paths
     */
    public Accepter(){
        Scanner sc = new Scanner(System.in);

        //alphabets
        System.out.println("ENTER THE ALPHABETS:");
        accepterAlphabet = new ArrayList<>(separateStringBySpace(sc.nextLine()));

        //states
        System.out.println("ENTER THE STATES:");
        ArrayList<String> stateTitles = new ArrayList<>(separateStringBySpace(sc.nextLine()));
        accepterStates = new ArrayList<>();
        for (String title : stateTitles){
            accepterStates.add(new State(title));
        }

        //start state
        System.out.println("ENTER THE START STATE:" );
        String startStateTitle = sc.nextLine().trim();
        for (State state : accepterStates){
            if(state.getTitle().equals(startStateTitle)){
                state.setStarsState(true);
            }
        }

        //final states
        System.out.println("ENTER THE FINAL STATE(S):" );
        ArrayList<String> finalStatesTitle = new ArrayList<>(separateStringBySpace(sc.nextLine().trim()));
        for (State state : accepterStates){
            if(finalStatesTitle.contains(state.getTitle())){
                state.setFinalState(true);
            }
        }

        //paths
        System.out.println("ENTER THE PATHS:\n(USE THIS FORMAT: START_STATE ALPHABET END_STATE)");
        accepterPaths = new ArrayList<>();
        while (true){
            ArrayList<String> pathInputString = new ArrayList<>(separateStringBySpace(sc.nextLine()));
            if (pathInputString.size() == 3){
                Path path = new Path(pathInputString.get(0), pathInputString.get(1), pathInputString.get(2));
                accepterPaths.add(path);
            }else{
                break;
            }
        }
    }

    /**
     * default constructor
     * a b
     * Q0 Q1 Q2
     * Q0
     * Q1
     * Q0 a Q1
     * Q0 b Q1
     * Q1 a Q2
     * Q1 b Q2
     * Q2 a Q2
     * Q2 b Q2
     */
    public Accepter(boolean bool){
        //alphabets
        accepterAlphabet = new ArrayList<>();
        accepterAlphabet.add("a");
        accepterAlphabet.add("b");

        //states
        accepterStates = new ArrayList<>();
        accepterStates.add(new State("Q0"));
        accepterStates.add(new State("Q1"));
        accepterStates.add(new State("Q2"));

        //start state
        for (State state : accepterStates){
            if(state.getTitle().equals("Q0")){
                state.setStarsState(true);
            }
        }

        //final states
        ArrayList<String> finalStatesTitle = new ArrayList<>();
        finalStatesTitle.add("Q1");
        for (State state : accepterStates){
            if(finalStatesTitle.contains(state.getTitle())){
                state.setFinalState(true);
            }
        }

        //paths
        accepterPaths = new ArrayList<>();
        accepterPaths.add(new Path("Q0", "a" , "Q1"));
        accepterPaths.add(new Path("Q0", "b" , "Q1"));
        accepterPaths.add(new Path("Q1", "a" , "Q2"));
        accepterPaths.add(new Path("Q1", "b" , "Q2"));
        accepterPaths.add(new Path("Q2", "a" , "Q2"));
        accepterPaths.add(new Path("Q2", "b" , "Q2"));
    }

    //constructor:
    /**
     *txt constructor
     */
    public Accepter(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        accepterPaths = new ArrayList<>();
        System.out.println("TEXT IN "+filePath+".txt :");
        while (sc.hasNextLine())
            System.out.println(sc.nextLine());
        System.out.println("");
        sc = new Scanner(file);
        //a counter to detect which line is scanning:
        int lineNumber = 1;
        while (sc.hasNextLine()) {
            switch (lineNumber){
                case 1:
                    // reading alphabets
                    System.out.println("READING ALPHABETS:");
                    accepterAlphabet = new ArrayList<>(separateStringBySpace(sc.nextLine()));
//                    System.out.println(accepterAlphabet);
                    for (String s : accepterAlphabet){
                        System.out.print(s + " ");
                    }
                    System.out.println("");
                    break;
                case 2:
                    // reading states
                    System.out.println("READING STATES:");
                    ArrayList<String> stateTitles = new ArrayList<>(separateStringBySpace(sc.nextLine()));
                    accepterStates = new ArrayList<>();
                    for (String title : stateTitles){
                        accepterStates.add(new State(title));
                    }
                    for (State s : accepterStates){
                        System.out.print(s.getTitle() + " ");
                    }
                    System.out.println("");
//                    System.out.println(accepterStates);
                    break;
                case 3:
                    // start state
                    System.out.println("READING THE START STATE:" );
                    String startStateTitle = sc.nextLine().trim();
                    for (State state : accepterStates){
                        if(state.getTitle().equals(startStateTitle)){
                            state.setStarsState(true);
                        }
                    }
                    System.out.println(getStartState().getTitle());
                    break;
                case 4:
                    // final states
                    System.out.println("READING THE FINAL STATE(S):" );
                    ArrayList<String> finalStatesTitle = new ArrayList<>(separateStringBySpace(sc.nextLine().trim()));
                    for (State state : accepterStates){
                        if(finalStatesTitle.contains(state.getTitle())){
                            state.setFinalState(true);
                        }
                    }
                    for (State s : getFinalStates()){
                        System.out.print(s.getTitle() + " ");
                    }
                    System.out.println("");
//                    System.out.println(getFinalStates());
                    break;
                default:
                    // paths:
                    System.out.println("READING A PATH");
                    ArrayList<String> pathInputString = new ArrayList<>(separateStringBySpace(sc.nextLine()));
                    Path path = new Path(pathInputString.get(0), pathInputString.get(1), pathInputString.get(2));
                    accepterPaths.add(path);
                    System.out.println(path.getStartState() + " " + path.getInputAlphabet() + " " + path.getEndState() + " ");
//                    System.out.println(pathInputString);
                    break;
            }
//            System.out.println(sc.nextLine());
            lineNumber++;
        }
    }

    //finding the start state:
    public State getStartState(){
        for (State state : this.accepterStates){
            if (state.isStarsState()){
                return state;
            }
        }
        System.out.println("NO START STATE HAS BEEN DEFINED!");
        return null;
    }

    //finding final state(s):
    public ArrayList<State> getFinalStates(){
        ArrayList<State> finalStates = new ArrayList<>();
        for (State state : this.accepterStates){
            if (state.isFinalState()){
                finalStates.add(state);
            }
        }
        return finalStates;
    }

    //change a input string to a string array list (separated by state)
    public List<String> separateStringBySpace (String input){
        String inputString = input;
        String[] substrings = inputString.split(" ");
        List<String> result = Arrays.asList(substrings);
        return result;
    }

    //get accepter's status - alphabets, states (normal, start and final) and paths:
    public void getAcceptorsStatus(){
        System.out.println("---------------------------------------");
        System.out.println(">>> ALPHABETS:");
        for (String alphabet : accepterAlphabet){
            System.out.println("   >>> " + alphabet);
        }
        System.out.println("---------------------------------------");
        System.out.println(">>> ALL OF THE STATES:");
        for (State state : accepterStates){
            System.out.println("   >>> " + state.getTitle());
        }
        System.out.println("---------------------------------------");
        System.out.println(">>> START STATES: ");
        System.out.println("   >>> " + getStartState().getTitle());
        System.out.println("---------------------------------------");
        System.out.println(">>> FINAL STATES:");
        for (State state : getFinalStates()){
            System.out.println("   >>> " + state.getTitle());
        }
        System.out.println("---------------------------------------");
        System.out.println(">>> PATHS:");
        for (Path path : accepterPaths){
            System.out.println("   >>> " + path);
        }
    }

    //finding a path with its start state and input alphabet:
    public Path findPathByInitialStateAndInputAlphabet (String initialState , String input){
        for (Path p : accepterPaths){
            if ( p.getStartState().equals(initialState) && p.getInputAlphabet().equals(input)){
                return p;
            }
        }
        return null;
    }

    //getting a state with it's title:
    public State getStateByTitle (String stateTitle) {
        for ( State s : accepterStates){
            if (s.getTitle().equals(stateTitle)){
                return s;
            }
        }
        return null;
    }

    //finding out if the user input is valid for this accepter
    public void isTheInputStringValid(String inputString){
        System.out.println("---------------------------------------");
        System.out.println("INPUT STRING: \" " + inputString + " \"");
        System.out.println("( 1 = SUCCESSFUL STEP , 0 = FAILED)");
        boolean[] inputStatus = new boolean[inputString.length()];
        String currentState = getStartState().getTitle();
        String[] inputArray = inputString.split("");
        String currentAlphabet = inputArray[0];
        Path currentPath;
        int index = 0 ;
        for (int i = 0 ; i < inputString.length() ; i++){
            try {
                index = i;
                currentAlphabet = inputArray[i];
                currentPath = findPathByInitialStateAndInputAlphabet(currentState, currentAlphabet);
                if (currentPath.toString()!=null){
                    System.out.println(currentPath);
                    inputStatus[index] = true;
                }
//                System.out.println((getStateByTitle(currentPath.getEndState()).isFinalState()));
                currentState = currentPath.getEndState();
                printTheInputStringStatus(inputString, inputStatus , index);
            } catch (Exception e){
                inputStatus[index] = false;
                System.out.println("ERROR FOUND WHILE READING INPUT \"" + currentAlphabet + "\"!");
                printTheInputStringStatus(inputString, inputStatus, index);
                System.out.println("THIS STRING IS NOT VALID!!!!");
                return;
            }
        }
        System.out.println("THE STRING WAS DETECTED SUCCESSFULLY!");
        System.out.println("CURRENT STATE: ( " + currentState + " )");
        if (getStateByTitle(currentState).isFinalState()){
            System.out.println("( "+ currentState +" ) IS A FINAL STATE, SO THE\nSTRING \"" + inputString +"\" IS VALID.");
        }else{
            System.out.println("( "+ currentState +" ) IS NOT A FINAL STATE, SO THE\nSTRING \"" + inputString +"\" ISN'T VALID.");
        }
    }

    //showing the user's input string in each step of checking:
    public void printTheInputStringStatus(String input , boolean[] status , int index){
        String[] inputArray = input.split("");
        System.out.println("");
        for ( int i = 0 ; i < input.length() ; i++ ){
            System.out.print("| " + inputArray[i] + " ");
        }
        System.out.println("|");
        for ( int i = 0 ; i < input.length() ; i++ ){
            if (i <= index){
                if (status[i]){
                    System.out.print("| 1 ");
                }else{
                    System.out.print("| 0 ");
                }
            }else{
                System.out.print("| - ");
            }
        }
        System.out.println("|\n");
    }


    //setters and getters:
    public ArrayList<State> getAccepterStates() {
        return accepterStates;
    }
    public void setAccepterStates(ArrayList<State> accepterStates) {
        this.accepterStates = accepterStates;
    }
    public ArrayList<String> getAccepterAlphabet() {
        return accepterAlphabet;
    }
    public void setAccepterAlphabet(ArrayList<String> accepterAlphabet) {
        this.accepterAlphabet = accepterAlphabet;
    }
    public ArrayList<Path> getAccepterPaths() {
        return accepterPaths;
    }
    public void setAccepterPaths(ArrayList<Path> accepterPaths) {
        this.accepterPaths = accepterPaths;
    }
}
