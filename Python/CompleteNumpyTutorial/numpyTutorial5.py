# Learn NUMPY in 5 minutes
# Python Programmer
# https://www.youtube.com/watch?v=xECXZ3tyONo

import numpy as np

a = np.zeros(3)
print(a)
print(type(a)) # ndarray
print(type(a[0])) # float64

z = np.zeros(10)
print(z)
print(z.shape)

z.shape = (10, 1) # row, column notation
print(z)
print(z.shape)

z = np.empty(3)
print(z)

l = np.linspace(2, 10, 5) # start, stop, elements
print(l)

arr = np.array([10, 20])
print(arr)
print(type(arr))

b_list = ([9,8,7,6,5,4,3],[1,2,3,4,5,6,7])
mat = np.array(b_list)
print(mat)
print(mat.shape)

np.random.seed(0)
randArr = np.random.randint(10, size = 6)
print(randArr)
print(randArr[0])   # first element
print(randArr[0:2]) # slicing applies
print(randArr[-1])  # last element

from skimage import io
photo = io.imread('D:\Programming\Python\PythonTutorial\harrier.jpg')
print(type(photo))
print(photo.shape)

import matplotlib.pyplot as plt
plt.imshow(photo)
plt.show() # script pauses until image closed

plt.imshow(photo[::-1]) # flip rows: mirror vertically
plt.show()

plt.imshow(photo[:, ::-1]) # flip columns: mirror horizontally
plt.show()

plt.imshow(photo[90:275, 140:410]) # show only selected area
plt.show()

plt.imshow(photo[::2, ::2]) # every other row/column
plt.show()

plt.imshow(photo[:,:,0].T) # transpose (rotate left), here only includes green
plt.show()

photo_sin = np.sin(photo) # why here? not necessary...just illustrating ability
print(np.sum(photo))
print(np.prod(photo))
print(np.mean(photo))
print(np.std(photo))
print(np.var(photo))
print(np.min(photo))
print(np.max(photo))
print(np.argmin(photo))
print(np.argmax(photo))

arr = np.array([1,2,3,4,5])
print(arr < 3)
print(arr > 3)
print(arr[arr > 3])

photo_masked = np.where(photo > 100, 255, 0) # where values are >100, make them 255 else make them 0
plt.imshow(photo_masked)
plt.show()

a_arr = np.array([1,2,3,4,5])
b_arr = np.array([6,7,8,9,10])

print(a_arr + b_arr)
print(a_arr + 30)
print(a_arr * b_arr)
print(a_arr * 10)
print(a_arr @ b_arr) # dot product

x = np.array([2,1,4,3,5])
print(np.sort(x))