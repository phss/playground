import numpy as np
import scipy.interpolate as inter

x = np.array([1, 2, 3, 4, 5])
y = np.array([1, 8, 27, 64, 125])

# Polynomial fit
z = np.polyfit(x, y, 3)
print z

# Lagrange polynomial
z = inter.lagrange(x, y)
print z(6)
