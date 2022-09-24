import java.util.HashSet;

public class Sudoku {
    private final char[][] data;
    private static int ROW_SIZE = 9;
    private static int COLUMN_SIZE = 9;
    private static int BOX_ROW_COLUMN_SIZE = 3;
    public Sudoku() {
        data = new char[Sudoku.ROW_SIZE][Sudoku.COLUMN_SIZE];

        int i=0;
        while (i<Sudoku.ROW_SIZE) {
            int j=0;
            while (j<Sudoku.COLUMN_SIZE) {
                data[i][j] = ' ';
                j++;
            }
            i++;
        }
    }

    public void set(int  row, int column, char c)
    {
        try {
            while(c!=' ') {
                data[row][column] = c;
                Integer.parseInt(String.valueOf(c));
                break;
            }
        }
        catch(NumberFormatException exception){
            System.out.printf("Trying to set illegal character %c to (%d, %d)!\n", c, row,column);
            data[row][column]=' ';
        } catch(ArrayIndexOutOfBoundsException exception)
        {
            System.out.printf("Trying to access illegal cell (%d, %d)!\n", row, column);
        }
    }
    public void print()
    {
        System.out.println("#####################################");
        int k=0;
        for(int i=0;i<Sudoku.ROW_SIZE;i++)
        {
            System.out.print("#");
            for(int j=0;j<Sudoku.COLUMN_SIZE;j++) {
                if(k==2) {
                    k=0;
                    System.out.printf(" %c #",data[i][j]);
                } else {
                    k+=1;
                    System.out.printf(" %c |",this.data[i][j]);
                }
            }
            if((i+1)%3==0) {
                System.out.println("\n#####################################");
            } else {
                if(i<8)
                    System.out.println("\n#---+---+---#---+---+---#---+---+---#");
            }
        }
    }
    public boolean check()
    {
        boolean result = notInRow(this.data);
        return result;
    }

    private boolean notInRow(char arr[][])
    {
        HashSet<Character> set ;
        for (int i=0; i< 9;i++)
        {
            set = new HashSet<>();
            for(int j=0;j<9;j++)
            {
                if(set.contains(arr[i][j]))
                {
                    System.out.printf("Row %d has multiple %c's!\n",i,arr[i][j]);
                    return false;
                }
                if (arr[i][j] !=' ')
                    set.add(arr[i][j]);
            }
        }
        return notInCol(this.data);
    }
    private boolean notInCol(char arr[][])
    {
        HashSet<Character> set;
        for(int i=0;i<Sudoku.ROW_SIZE;i++) {
            set = new HashSet<>();
            for(int j=0;j<Sudoku.COLUMN_SIZE;j++) {
                if (set.contains(arr[j][i])) {
                    System.out.printf("Column %d has multiple %c's!\n", i, arr[j][i]);
                    return false;
                }
                if (arr[j][i]!= ' ')
                    set.add(arr[j][i]);
            }
        }
        return notInBox(this.data);
    }
    private boolean notInBox(char arr[][])
    {
        HashSet<Character> st;
        for(int i=0;i<7;i+=3)
        {
            for(int j=0;j<7;j+=3)
            {
                st = new HashSet<>();
                for(int row =0; row<Sudoku.BOX_ROW_COLUMN_SIZE;row++){
                    for(int col = 0; col<Sudoku.BOX_ROW_COLUMN_SIZE; col++)
                    {
                        char current = arr[row + i][col + j];

                        if (st.contains(current))
                        {
                            System.out.printf("Block at (%d, %d) has multiple %c's!\n", i, j, current);
                            return false;
                        }

                        if( current !=' ')
                            st.add(current);
                    }
                }
            }
        }
        return true;
    }
}
