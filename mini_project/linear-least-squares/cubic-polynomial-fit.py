import numpy as np

x = np.array([1, 2, 3, 4, 5])
y = np.array([1, 8, 27, 64, 125])
z = np.polyfit(x, y, 3)

print z
