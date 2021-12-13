import java.io.*;
import java.util.*;

class ThiseasMaze{    

    char[][] mazeTable;
    int lineSize;
    int columnSize;
    int line_e, column_e;
    boolean flag = false;
    boolean txtReadCorrectly = true;
    
    ThiseasMaze(String path){

        int lines, columns;                             
        BufferedReader reader = null;
        String line;
        String token;
        String filepath;
        
        try{
            filepath = path;
            reader = new BufferedReader(new FileReader(new File(filepath)));
            line = reader.readLine();

            //Reading 1st txt line
            StringTokenizer st = new StringTokenizer(line," ");
            token = st.nextToken();
            lines = Integer.parseInt(token.trim());
            this.lineSize = lines;
            token = st.nextToken();
            columns = Integer.parseInt(token.trim());
            this.columnSize = columns;  
            line = reader.readLine();

            //Reading 2nd txt line
            st = new StringTokenizer(line," ");
            token = st.nextToken();
            this.line_e = Integer.parseInt(token.trim());
            token = st.nextToken();
            this.column_e = Integer.parseInt(token.trim());

            char[][] table = new char[lines][columns];                      //Dimiourgia tou Array to opoio periexei ton pinaka pou dinetai sto txt arxeio.
            for (int i = 0; i < lines; i++){                                //Diavazoume to txt arxeio grammi pros grammi.
                line = reader.readLine();
                st = new StringTokenizer(line, " ");                        //"Spame" tin grammi se kommatia pou xwrizontai metaksy tous me keno xaraktira (" ").
                for (int j = 0; j <= columns; j++){                          //Diavazoume ta stoixeia twn stilwn gia kathe grammi.
                    if (j < columns){
                        if (st.hasMoreTokens()){
                            token = st.nextToken();                                 
                            table[i][j] = token.trim().charAt(0);                   //Thetoume se kathe stoixeio tou Array to antistoixo stoixeio tou txt arxeiou.
                        }
                        else{
                            txtReadCorrectly = false;
                            System.out.println("Error occured when reading item in " + i + " line and " + j + " column (>> Missing << from given " + filepath + " file).");
                            reader.close();
                            throw new Exception();
                        }
                    }
                    else
                        if (st.hasMoreTokens()){
                            txtReadCorrectly = false;
                            System.out.println("Line " + i + " has MORE columns than described in 1st line of " + filepath + " file.");
                            reader.close();
                            throw new Exception();
                        }
                }
            }
            if (reader.readLine() != null){
                reader.close();
                txtReadCorrectly = false;
                throw new Exception();
            }
            reader.close();
            this.mazeTable = table;
            this.flag = true;
        }
        catch (IOException ex){
            System.err.println(">!< Path not found or Incorrect >!<");
        }
        catch(Exception ex){
            System.out.println("Exception Message: Given size and table do NOT match.");
        }
    }

    public char[][] getArray(){
        return mazeTable;
    }

    //Sets an Array's element 'V' (for Visited)
    public void setV(int i, int j){            
        mazeTable[i][j] = 'V';
    }

    //Checks if Current Position in Array has a neighbour above.
    public boolean hasUp(int i, int j){     
        if (mazeTable[i - 1][j] == '0' || mazeTable[i - 1][j] == 'G')
            return true;
        else
            return false;
    }

    //Checks if Current Position in Array has a neighbour below.
    public boolean hasDown(int i, int j){   
        if (mazeTable[i + 1][j] == '0' || mazeTable[i + 1][j] == 'G')
            return true;
        else
            return false;
    }

    //Checks if Current Position in Array has a neighbour at left.
    public boolean hasLeft(int i, int j){   
        if (mazeTable[i][j - 1] == '0' || mazeTable[i][j - 1] == 'G')
            return true;
        else
            return false;
    }

    //Checks if Current Position in Array has a neighbour at right.
    public boolean hasRight(int i, int j){  
        if (mazeTable[i][j + 1] == '0' || mazeTable[i][j + 1] == 'G')
            return true;
        else
            return false;
    }

    //Sets 'G' every '0' found in Array's sides.
    public void setG(){                      
        for (int i = 0; i < lineSize; i++){
            if (mazeTable[i][0] == '0')
                mazeTable[i][0] = 'G';
            if (mazeTable[i][columnSize - 1] == '0')
                mazeTable[i][columnSize - 1] = 'G';
        }

        for (int j = 0; j < columnSize; j++){
            if(mazeTable[0][j] == '0')
                mazeTable[0][j] = 'G';
            if (mazeTable[lineSize - 1][j] == '0')
                mazeTable[lineSize - 1][j] = 'G';
        }    
    }

    //Prints Array
    public void printMazeTable(){
        System.out.println("---Image of given Maze---");
        for (int i = 0; i < lineSize; i++){
            for (int j = 0; j < columnSize; j++)
                System.out.print(mazeTable[i][j] + " ");
            System.out.println("");
        }
    }

    public boolean isG(int i, int j){
        if (mazeTable[i][j] == 'G'){
            System.out.println("Solution is: (" + i + "," + j + ").");
            return true;
        }
        return false;
    }

    //Checks if the given E coordinates are the same with Array's enter position
    public boolean checkingE(){
        if (mazeTable[line_e][column_e] == 'E')
            return true;
        else {
            System.out.println("Given E coordinates do not match Array's entry point.");
            return false;
        }
    }

    //Checks if there is another 'E' in the Array
    public boolean checkingForAnotherE(){
        int counter = 0;
        for (int i = 0 ; i < lineSize; i++){
            for (int j = 0; j < columnSize; j++){
                if (mazeTable[i][j] == 'E')
                    counter++;
            }
        }
        if (counter > 1){
            System.out.println("There are more than one 'E's in the Array.");
            return true;
        }
        else
            return false;
    }

    public void solveMaze(){
        StringStackImpl<String> conjunctionsStack = new StringStackImpl();
        int[] location = new int[2];                                    //location[0] == i coord || location[1] == j coord || (location[0], locatoion[1]) == (i,j)
        location[0] = line_e;
        location[1] = column_e;
        
        //Elegxos gia to an to 'E' einai topothetimeno symfwna me to .txt arxeio.
        if (! checkingE())
            flag = false;
        
        //Elegxos gia to an uparxei deutero 'E'.
        if (checkingForAnotherE())
            flag = false;

        while (flag){
            boolean left = false, right = false, up = false, down = false;  //Eksasfalizoume oti DEN yparxei geitoniko stoixeio akoma kai otan ftasoume sta akra tou Array.
            if (location[0] - 1 >= 0)                                       //Prostateuoume tis methodous hasUp(), hasDown(), hasLeft(), hasRight() apo to na psaksoun se stoixeio tou Array ektos oriwn. 
                up = hasUp(location[0], location[1]);
            if (location[0] + 1 < lineSize)
                down = hasDown(location[0], location[1]);
            if (location[1] - 1 >= 0)
                left = hasLeft(location[0], location[1]); 
            if (location[1] + 1 < columnSize)
                right = hasRight(location[0], location[1]);
            
            //Elegxoume posoi geitones yparxoun
            //An exw = 1 --> up or down or right or left \\ An exw (>1) --> vriskomaste se stavrodromi
            int sum = 0;
            if (up)
                sum++;
            if (down)
                sum++;
            if (left)
                sum++;
            if (right)
                sum++;

            String location_str = location[0]+","+location[1];
            //Periptwsi pou exoume MONO 1 geitona.
            if (sum == 1){  
                if (isG(location[0], location[1]))
                    flag = false;
                else{                                
                setV(location[0], location[1]);             //Thetoume "V" molis episkeptomaste kathe stoixeio tou Array.
                if (up)
                    location[0] -= 1;                         
                else if (down)
                    location[0] += 1;
                else if (left)
                    location[1] -= 1;
                else if (right)
                    location[1] += 1;
                }
            }
            //Ean exoume parapanw apo 1 geitones tote kanoume Push sti Stoiva tis sintetagmenes tis topothesias stin opoia tha prepei na epistrepsoume gia na akolouthisoume allo monopati.
            else if (sum > 1){                              
                setV(location[0], location[1]);
                conjunctionsStack.push(location_str);
                if (isG(location[0], location[1]))
                    flag = false;
                else{
                    setV(location[0], location[1]);
                    if (up)
                        location[0] -= 1;
                    else if (down)
                        location[0] += 1;
                    else if (left)
                        location[1] -= 1;
                    else if (right)
                        location[1] += 1;
                }
            }
            else if (sum == 0) {    //Vriskomaste eite se adieksodo eite se kapoio akro tou pinaka me periexomeno 'G'.
                if (isG(location[0], location[1]))
                    flag = false;
                else if (conjunctionsStack.size() > 0){     //Vriskomaste se adieksodo omws yparxei simeio sto opoio na kanoume Backtrack.
                    setV(location[0], location[1]);         //Thetoume 'V' to teleutaio kommati tou monopatiou pou mas odigise se adieksodo
                    String str = conjunctionsStack.pop();   //kai sti sinexeia kanoume Pop.
                    StringTokenizer st = new StringTokenizer(str,",");
                    location[0] = Integer.parseInt(st.nextToken());
                    location[1] = Integer.parseInt(st.nextToken());
                }
                else if (conjunctionsStack.size() == 0){    //Vriskomaste se adieksodo kai DEN yparxei simeio sto opoio na kanoume Backtrack.
                    flag = false;                           //I diadikasia tis epilysis teleiwse.
                    System.out.println(">!< No solution found >!<");
                }
            }
        }
    }
}