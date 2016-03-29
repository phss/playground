import numpy as np
import scipy.interpolate as inter

x = np.array([1, 2, 3, 4, 5])
y = np.array([1, 8, 27, 64, 125])
fit_sum = 0

for n in range(1, len(x)):
  z = inter.lagrange(x[:n], y[:n])
  for i in range(len(x)):
    x_i = x[i]
    y_i = y[i]
    z_i = z(x_i)
    if z_i != y_i:
      fit_sum += z_i
      break

print fit_sum
