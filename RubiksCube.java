public class RubiksCube {
    private int n; // Size of the cube (n x n)
    private int[][][] cube; // Cube representation (6 faces, each n x n)
    
    // Constructor
    public RubiksCube(int size) {
        this.n = size;
        cube = new int[6][n][n];
        initializeCube();
    }
    
    // Initialize cube with default colors (0-5 representing different colors for faces)
    private void initializeCube() {
        for (int face = 0; face < 6; face++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    cube[face][i][j] = face; // Each face has a unique color
                }
            }
        }
    }
    
    // Rotate a face 90 degrees clockwise
    private void rotateFaceClockwise(int face) {
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n - 1 - i] = cube[face][i][j];
            }
        }
        cube[face] = temp;
    }
    
    // Rotate a row (layer) across adjacent faces horizontally
    public void rotateRow(int row, boolean clockwise) {
        if (clockwise) {
            // Rotate row clockwise: move row from one face to the next
            int[] temp = cube[0][row];
            cube[0][row] = cube[4][row];
            cube[4][row] = cube[2][row];
            cube[2][row] = cube[5][row];
            cube[5][row] = temp;
        } else {
            // Rotate row counterclockwise
            int[] temp = cube[0][row];
            cube[0][row] = cube[5][row];
            cube[5][row] = cube[2][row];
            cube[2][row] = cube[4][row];
            cube[4][row] = temp;
        }
    }
    
    // Rotate a column (layer) across adjacent faces vertically
    public void rotateColumn(int col, boolean clockwise) {
        if (clockwise) {
            // Move column data clockwise between adjacent faces
            int[] temp = new int[n];
            for (int i = 0; i < n; i++) {
                temp[i] = cube[0][i][col];
                cube[0][i][col] = cube[1][i][col];
                cube[1][i][col] = cube[2][i][col];
                cube[2][i][col] = cube[3][i][col];
                cube[3][i][col] = temp[i];
            }
        } else {
            // Move column data counterclockwise
            int[] temp = new int[n];
            for (int i = 0; i < n; i++) {
                temp[i] = cube[0][i][col];
                cube[0][i][col] = cube[3][i][col];
                cube[3][i][col] = cube[2][i][col];
                cube[2][i][col] = cube[1][i][col];
                cube[1][i][col] = temp[i];
            }
        }
    }
    
    // Print the state of the cube (for debugging purposes)
    public void printCube() {
        for (int face = 0; face < 6; face++) {
            System.out.println("Face " + face + ":");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(cube[face][i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        RubiksCube cube = new RubiksCube(3); // Initialize a 3x3 Rubik's Cube
        cube.printCube(); // Print initial state
        
        System.out.println("Rotating top row clockwise...");
        cube.rotateRow(0, true); // Rotate the top row clockwise
        cube.printCube(); // Print updated state
        
        System.out.println("Rotating first column counterclockwise...");
        cube.rotateColumn(0, false); // Rotate the first column counterclockwise
        cube.printCube(); // Print updated state
        
        // NEW: Rotate the front face clockwise
        System.out.println("Rotating front face clockwise...");
        cube.rotateFaceClockwise(0); // Rotating face 0 (the front face)
        cube.printCube(); // Print updated state
    }
    
}
