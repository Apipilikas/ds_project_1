public class Thiseas{
   

    public static void main(String args[]){
        ThiseasMaze labyrinth = new ThiseasMaze(args[0]);
        System.out.println("File read correctly: " + labyrinth.txtReadCorrectly);
        if (labyrinth.txtReadCorrectly){
            labyrinth.setG();
            labyrinth.printMazeTable();
            labyrinth.solveMaze();
        }
        else
            System.out.println("Program did not finish with a solution.");
    }
}