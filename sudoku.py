class Solution(object):
    def solveSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: None Do not return anything, modify board in-place instead.
        """
        # Call the helper function to solve the Sudoku
        self.solve(board)
        
    def solve(self, board):
        # Find an empty spot on the board (represented by '.')
        empty_cell = self.find_empty(board)
        
        # If no empty spots are found, the Sudoku is solved
        if not empty_cell:
            return True
        
        # Get the row and column of the empty spot
        row, col = empty_cell
        
        # Try numbers 1 through 9 in the empty spot
        for num in range(1, 10):
            if self.is_valid(board, row, col, str(num)):
                # Place the number if it is valid
                board[row][col] = str(num)
                
                # Recursively try to solve the rest of the board
                if self.solve(board):
                    return True
                
                # If the current placement doesn't work, undo the move (backtrack)
                board[row][col] = '.'
        
        # If no number works in this spot, return False (triggering backtracking)
        return False

    def find_empty(self, board):
        # Find an empty cell (denoted by '.') in the Sudoku grid
        for i in range(9):
            for j in range(9):
                if board[i][j] == '.':
                    return (i, j)  # Return row, column of the empty cell
        return None  # No empty cells, the board is complete

    def is_valid(self, board, row, col, num):
        # Check if placing 'num' in the given row and column is valid
        
        # Check the row
        for i in range(9):
            if board[row][i] == num:
                return False
        
        # Check the column
        for i in range(9):
            if board[i][col] == num:
                return False
        
        # Check the 3x3 sub-box
        start_row = (row // 3) * 3
        start_col = (col // 3) * 3
        for i in range(3):
            for j in range(3):
                if board[start_row + i][start_col + j] == num:
                    return False
        
        return True
