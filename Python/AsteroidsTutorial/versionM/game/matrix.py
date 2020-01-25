import pyglet, math


class Matrix(list):
    rows = None
    cols = None
    size = None
    matrix = []

    def __init__(self, *args, **kwargs):
        self.rows = None
        self.cols = None
        self.size = None
        self.matrix = []
        
        # see if args has the right input
        if args:
            if isinstance(args[0], Matrix):
                raise TypeError('use Matrix.clone() function to copy matrices')
            elif isinstance(args[0], list):
                for row in args[0]:
                    if not isinstance(row, list):
                        raise TypeError('matrix must be a list of lists')
                    new_row = []
                    for col in row:
                        new_row.append(col)
                    self.matrix.append(new_row)
            elif isinstance(args[0], tuple):
                self.size = args[0][0:2]
            elif len(args) > 1:
                if isinstance(args[0], int) and isinstance(args[1], int):
                    self.rows, self.cols = args[0], args[1]
                elif isinstance(args[0], int):
                    self.cols = args[0]
                elif isinstance(args[1], int):
                    self.cols = args[1]
                else:
                    raise RuntimeError('no way to make this matrix')
            elif len(args) == 1 and isinstance(args[0], int):
                self.cols = args[0]
            else:
                raise RuntimeError('no way to make this matrix')

        # but overwrite if kwargs has it too
        if kwargs:
            self.rows = kwargs.get('rows', None)
            self.cols = kwargs.get('cols', None)
            self.size = kwargs.get('size', None)
            self.matrix = kwargs.get('matrix', None)

        def blank_matrix_from_size(size):
            matr = []
            for r in range(size[0]):
                row_to_add = []
                for c in range(size[1]):
                    row_to_add.append(0)
                matr.append(row_to_add)
            return matr

        def sizing(input):
            output = list(input)
            r = len(output)
            c = 0
            for row in range(r):
                this_c = len(output[row])
                c = c if c > this_c else this_c
            for row in range(r):
                for col in range(c + 1):
                    try:
                        throwaway = output[row][col]
                        if not isinstance(throwaway, (int, float)):
                            raise TypeError
                    except IndexError:
                        if col < c:  # hole in the matrix
                            output[row].append(0)
            return output

        if not self.matrix:
            matrix = []
            if self.size:
                self.rows, self.cols = self.size
                self.matrix = blank_matrix_from_size(self.size)
            elif self.rows and self.cols:
                self.size = (self.rows, self.cols)
                self.matrix = blank_matrix_from_size(self.size)
            else:
                if not self.rows:
                    self.rows = 1
                if not self.cols:
                    self.cols = 1
                self.size = (self.rows, self.cols)
                self.matrix = blank_matrix_from_size(self.size)
        else:
            self.matrix = sizing(self.matrix)
            self.size = (len(self.matrix), len(self.matrix[0]))
            self.rows, self.cols = self.size
    
    def transpose(self):
        output = []
        for c in range(self.cols):
            new_row = []
            for r in range(self.rows):
                new_row.append(self.matrix[r][c])
            output.append(new_row)
        return Matrix(matrix=output)
    
    def clone(self):
        output = []
        for r in range(self.rows):
            out_row = []
            for c in range(self.cols):
                out_row.append(self.matrix[r][c])
            output.append(out_row)
        return Matrix(matrix=output)
        
    # operator overloading
    def __str__(self):
        output = ""
        for r in range(self.rows):
            row_out = '[[' if r == 0 else ' ['
            for c in range(self.cols):
                row_out = row_out + str(self.matrix[r][c])
                row_out = row_out + ', ' if c < self.cols - 1 else row_out
            row_out = row_out + '],\n' if r < self.rows - 1 else row_out + ']]'
            output += row_out
        return output

    def __add__(self, other):

        output = []
        
        if isinstance(other, Matrix) and self.size == other.size:
            for r in range(self.rows):
                new_row = []
                for c in range(self.cols):
                    new_row.append(self.matrix[r][c] + other.matrix[r][c])
                output.append(new_row)
        elif isinstance(other, (int, float)):
            for r in range(self.rows):
                new_row = []
                for c in range(self.cols):
                    new_row.append(self.matrix[r][c] + other)
                output.append(new_row)
        else:
            raise TypeError('incompatible summand')
        
        return Matrix(matrix=output)

    def __sub__(self, other):
        
        output = []
        
        if isinstance(other, Matrix) and self.size == other.size:
            for r in range(self.rows):
                new_row = []
                for c in range(self.cols):
                    new_row.append(self.matrix[r][c] - other.matrix[r][c])
                output.append(new_row)
        elif isinstance(other, (int, float)):
            for r in range(self.rows):
                new_row = []
                for c in range(self.cols):
                    new_row.append(self.matrix[r][c] - other)
                output.append(new_row)
        else:
            raise TypeError('incompatible subtrahend')

    def __mul__(self, other):
        
        output = []
        
        if isinstance(other, Matrix) and self.size[1] == other.size[0]:
            for r in range(self.rows):
                new_row = []
                for c in range(other.cols):
                    sum = 0
                    for i in range(self.cols):
                        sum += self.matrix[r][i] * other.matrix[i][c]
                    new_row.append(sum)
                output.append(new_row)
        elif isinstance(other, (int, float)):
            for r in range(self.rows):
                new_row = []
                for c in range(self.cols):
                    new_row.append(self.matrix[r][c] * other)
                output.append(new_row)
        else:
            raise TypeError('incompatible factor')
        
        return Matrix(matrix=output)
    
    def __len__(self):
        return 1

    @staticmethod
    def randomize(upper, lower):
        # FIXME set all values to somewhere between upper and lower
        pass

    @staticmethod
    def eye(size):
        output = Matrix(size=(size, size))
        for i in range(size):
            output.matrix[i][i] = 1
        return output
