import numpy as np
import scipy.interpolate as inter

x = np.array([1, 2, 3, 4, 5])
y = np.array([1, 8, 27, 64, 125])
n = 3

z = inter.lagrange(x[:n], y[:n])
print z(6)
