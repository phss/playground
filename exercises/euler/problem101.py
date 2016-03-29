import numpy as np
import scipy.interpolate as inter

# Cubic values:
#x = np.array([1, 2, 3, 4, 5])
#y = np.array([1, 8, 27, 64, 125])

y = np.array([1, 683, 44287, 838861, 8138021, 51828151, 247165843, 954437177, 3138105961, 9090909091, 23775972551])
x = np.array(range(1, len(y)+1))
print x
fit_sum = 0

for n in range(1, len(x)+1):
  z = inter.lagrange(x[:n], y[:n])
  for i in range(len(x)):
    x_i = x[i]
    y_i = y[i]
    z_i = z(x_i)
    if abs(z_i - y_i) > 5: # Crappy adjustment to rounding
      print n, x_i, y_i, z_i
      fit_sum += z_i
      break

print fit_sum
