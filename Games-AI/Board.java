


public class Board{

    private int[][] tiles;


    public Board(int[][] tiles){
        this.tiles=tiles;
    }

    public int hamming(){
        int count=0;
        for(int i=0;i<tiles.length;i++){
            for(int j=0;j<tiles.length;j++){
                if(tiles[i][j]!=0 && tiles[i][j]!=i*tiles.length+j+1){
                    count++;
                }
            }
        }
        return count;
    }

    public int manhattan(){
        int i;
    }
}