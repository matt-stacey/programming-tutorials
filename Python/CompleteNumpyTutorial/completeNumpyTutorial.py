# Complete Python NumPy Tutorial
# Keith Galli
# https://www.youtube.com/watch?v=GB9ByFAIAH4

import numpy as np
import sys
from scipy.io.matlab.miobase import arr_dtype_number
from scipy import stats

a = np.array([1, 2, 3], dtype = "int16")
print(a)
print(a.ndim)
print(a.shape)
print(a.dtype)
print(a.size) # by element
print(a.itemsize)
print(a.size * a.itemsize) # total size
print(a.nbytes) # same thing

b = np.array([[9., 8., 7.], [6., 5., 4.]])
print(b)
print(b.ndim)
print(b.shape) # row, column
print(b.dtype)

a = np.array([[1, 2, 3, 4, 5, 6, 7], [8, 9, 10, 11, 12, 13, 14]])
print(a.shape)
print(a[1, 5]) # 13
print(a[0, :]) # first row
print(a[:, 2]) # column AS A ROW
print(a[0, 1:6:2]) # start:stop:step (stop exclusive)
a[1, 5] = 20
print(a)
a[:, 2] = [5, 5]
print(a)

b = np.array([[[1, 2], [3, 4]], [[5, 6], [7, 8]]])
print(b)
print(b[0, 1, 1]) # work outside in: 4
print(b[:, 1, :]) # : all depths, 1 2nd row, : all columns...returns a 2x2, how do we know orientation?
b[:, 1, :] = [[9, 9], [8, 8]]
print(b)

print(np.zeros(5))
print(np.zeros((2, 3, 3))) # pass as tuple
print(np.ones((4,2), dtype = 'int16'))
print(np.full((2,2), 99, dtype = 'int16'))
print(np.full(a.shape, 4))
print(np.full_like(a, 4))
print(np.random.rand(4,2)) # not tuples
print(np.random.randint(-4, 4, size = (3,2)))
print(np.identity(3))

arr = np.array([[1, 2, 3]])
r1 = np.repeat(arr, 3, axis = 0)
print(arr)
print(r1)

test = np.ones((5, 5)) # pass as tuples!
test[1:-1, 1:-1] = np.zeros((3, 3))
test[2, 2] = 9
print(test)

copy = test # this is a pointer
copy[2, 2] = 8
print(test)
print(copy)
copy = np.copy(test)
test[2, 2] = 9
print(test)
print(copy)

# array math
a = np.array([1, 2, 3, 4])
print(a)
print(a + 2)
print(a - 2)
print(a * 2)
print(a / 2)
b = np.array([1, 0, 1, 0])
print(a + b)
print(a ** 2)
print(np.sin(a))
# https://docs.scipy.org/doc/numpy/reference/routines.math.html

# Linear Algebra
a = np.full((2,3), 1)
print(a)
b = np.full((3, 2), 2)
print(b)
print(np.matmul(a, b))
c = np.identity(3)
print(np.linalg.det(c)) # 1
print(np.linalg.inv(c)) # also identity
# https://docs.scipy.org/doc/numpy/reference/routines.linalg.html

# Statistics
stats = np.array([[1, 2, 3], [4, 5, 6]])
print(np.min(stats, axis = 1)) # axis: 0 rows, 1 cols
print(np.max(stats, axis = 0))
print(np.sum(stats))

# Reorganizing
before = np.array([[1, 2, 3, 4], [5, 6, 7, 8]])
print(before.shape)
print(before.reshape((8, 1)))
print(before.reshape((2, 2, 2)))
v1 = np.array([1, 2, 3, 4])
v2 = np.array([5, 6, 7, 8])
print(np.vstack([v1, v2, v2[::-1], v1[::-1]]))
h1 = np.ones((2,4))
h2 = np.zeros((2,2))
print(np.hstack([h1, h2, h1]))

# Miscellaneous
# Load data from file
filedata = np.genfromtxt('data.txt', delimiter = ',')
print(filedata)

# advanced masking and boolean masking
print(filedata[filedata > 50]) # you can index with a list!
print(np.any(filedata > 50, axis = 0))
print(np.all(filedata > 50, axis = 0))
 
matrix = np.array([[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]])
print(matrix)
print(matrix[2:4, 0:2])
print(matrix[[0, 1, 2, 3], [1, 2, 3, 4]])
print(matrix[[0, 3, 4], 3:])