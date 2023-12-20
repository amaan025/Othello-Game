import java.util.*; 
import java.lang.Math;

public class MoveChooser 
{

    public static int static_evaluation_function(BoardState boardState)
    {

        int val_black = 0;
        int val_white = 0;

        int [][] stat_fun = {{120,-20,20,5,5,20,-20,120},
                             {-20,-40,-5,-5,-5,-5,-40,-20},
                             {20,-5,15,3,3,15,-5,20},
                             {5,-5,3,3,3,3,-5,5},
                             {5,-5,3,3,3,3,-5,5},
                             {20,-5,15,3,3,15,-5,20},
                             {-20,-40,-5,-5,-5,-5,-40,-20},
                             {120,-20,20,5,5,20,-20,120}};
        for(int i =0; i<=7;i++)
        {
            for(int j=0;j<=7;j++)
            {
                if (boardState.getContents(i,j) == 1)
                    val_white = stat_fun[i][j] + val_white;
                if (boardState.getContents(i,j) == -1)
                    val_black = stat_fun[i][j] + val_black;
            }
        }
        
        return (val_white - val_black);
       
    }

    public static int Node_eval(BoardState boardstate, int searchdepth)
    {
        ArrayList<Move> legal_moves= boardstate.getLegalMoves();
        ArrayList<Integer> max_val= new ArrayList<Integer>();
        ArrayList<Integer> min_val= new ArrayList<Integer>();
        if(boardstate.gameOver() || legal_moves.size() == 0 )
            return static_evaluation_function(boardstate);
        else if(boardstate.colour == 1)
        {
           // int max_val;
            for(int i=0;i<legal_moves.size();i++)
             {
                 BoardState boardstate1 = boardstate.deepCopy();
                 boardstate1.makeLegalMove(legal_moves.get(i).x , legal_moves.get(i).y);
                 max_val.add(alpha_beta(boardstate1,searchdepth-1,-2147483647,2147483645));
                 
             }
        return Collections.max(max_val);
        }
        else 
        {
            //int min_val;
            for(int i=0;i<legal_moves.size();i++)
             {
                 BoardState boardstate1 = boardstate.deepCopy();
                 boardstate1.makeLegalMove(legal_moves.get(i).x , legal_moves.get(i).y);
                 min_val.add(alpha_beta(boardstate1,searchdepth-1,-2147483647,2147483645));
                 
             }
        return Collections.min(min_val);
        }
    }

    public static int alpha_beta(BoardState boardstate, int searchdepth, int alpha, int beta)
    {
        ArrayList<Move> legal_moves= boardstate.getLegalMoves();
        if(searchdepth == 0 || boardstate.gameOver())
            return static_evaluation_function(boardstate);
        else if(boardstate.colour == 1)
        {
            int counter = 0;
            alpha = Integer.MIN_VALUE;
            while((alpha < beta) && (counter < legal_moves.size()) && (legal_moves != null))
            {
                BoardState boardstate1 = boardstate.deepCopy();
                boardstate1.makeLegalMove(legal_moves.get(counter).x , legal_moves.get(counter).y);
                alpha = Math.max(alpha_beta(boardstate1, searchdepth-1,alpha,beta),alpha);
                counter ++;
            }
        return alpha;
        }
        else
        {
            int counter = 0;
            beta = Integer.MAX_VALUE;
            while((alpha < beta) && (counter < legal_moves.size()) && (legal_moves != null))
            {
                BoardState boardstate1 = boardstate.deepCopy();
                boardstate1.makeLegalMove(legal_moves.get(counter).x , legal_moves.get(counter).y);
                beta = Math.min(alpha_beta(boardstate1, searchdepth-1,alpha,beta),beta);
                counter ++;
            }
        return beta;
        }
    }



    // public static int minimax_val(BoardState boardState,  int searchDepth)
    // {  
    //     //depth = Othello.searchDepth;
    //     ArrayList<Move> legal_moves= boardState.getLegalMoves();
    //     if (searchDepth == 0 || boardState.gameOver())
    //         return static_evaluation_function(boardState);
        
    //     else if (boardState.colour == 1)
    //     {
    //         int max_val = Integer.MIN_VALUE;
    //         for(int i=0;i<legal_moves.size();i++)
    //         {
    //             BoardState boardstate1 = boardState.deepCopy();
    //             boardstate1.makeLegalMove(legal_moves.get(i).x , legal_moves.get(i).y);
    //             max_val=Math.max(minimax_val(boardstate1, searchDepth-1),max_val);     
    //         }
    //         return max_val;
    //     }
    //     else //if (boardState.colour == -1)
    //     {
    //         int min_val = Integer.MAX_VALUE;
    //         for(int i=0;i<legal_moves.size();i++)
    //         {
    //             BoardState boardstate1 = boardState.deepCopy();
    //             boardstate1.makeLegalMove(legal_moves.get(i).x , legal_moves.get(i).y);
    //             min_val=Math.min(minimax_val(boardstate1, searchDepth-1),min_val);     
    //         }
    //         return min_val;
    
    //     }

    // }


    // public static Move minimax(BoardState boardState ,int searchdepth)
    // {
    //     // alpha = Integer.MIN_VALUE;
    //     // beta = Integer.MAX_VALUE;
    //     // ArrayList<Move> legal_moves= boardState.getLegalMoves();
    //     // if (legal_moves.size() == 0)
    //     //     return null;
    //     Move move = new Move(0, 0);
    //     ArrayList<Move> legal_moves= boardState.getLegalMoves();
    //         int max = Integer.MIN_VALUE ;
    //         if(legal_moves.size()==0 )
    //             return null;
    //         for(int i=0; i<legal_moves.size(); i++)
    //         {
    //             BoardState boardstate1 = boardState.deepCopy();
    //             boardstate1.makeLegalMove(legal_moves.get(i).x , legal_moves.get(i).y);
    //             int value = minimax_val(boardstate1, searchdepth-1);
    //             if(value > max)
    //             {
    //                 max = value;
    //                 move=legal_moves.get(i);   
    //             }
           
    //     }
    //     return move;
    //     // else //(boardState.colour == -1)
    //     // {
    //     //     int min = Integer.MAX_VALUE ;
    //     //     for(int i=0;i<legal_moves.size();i++)
    //     //     {
    //     //         int value = minimax_val(boardState, searchdepth-1);
    //     //         if(value < min)
    //     //         {
    //     //             min = value;
    //     //             move = legal_moves.get(i);   
    //     //         }
    //     //     }
    //     //     return move;
    //     // }    
    //     // //return  move;
    // }
    

    public static Move chooseMove(BoardState boardState)
    {
    ArrayList<Integer> value = new ArrayList<Integer>();
    int searchDepth= Othello.searchDepth;
    ArrayList<Move> moves= boardState.getLegalMoves();
    if(moves.isEmpty())
        return null;
    for (int i =0;i< moves.size();i++)
    {
        BoardState boardstate1 = boardState.deepCopy();
        boardstate1.makeLegalMove(moves.get(i).x , moves.get(i).y);
        if(boardstate1.getLegalMoves() != null)
            value.add(Node_eval(boardstate1,searchDepth -1));
        else
            return moves.get(i);
    }
    if(value.size() != 0)
        return moves.get(value.indexOf(Collections.max(value)));
    else
        return null;
    }
}
