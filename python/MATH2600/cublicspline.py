from scipy.interpolate import CubicSpline

import numpy as np

x = [3.0,3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.8,3.9]
y = [20.08, 22.20, 24.53, 27.12, 29.96, 33.11, 36.60, 40.45, 44.70, 49.40]

spline = CubicSpline(x, y)

def latex_defcs(cs):
    for i in range(len(cs.x)-1):
        d, c, b, a = cs.c[:, i]
        print(f"\\def\\biggerSpline{chr(i+65)}{{{a:.4f} + {b:.4f}*(x-{cs.x[i]}) + {c:.4f}*(x-{cs.x[i]})^2 + {d:.4f}*(x-{cs.x[i]})^3}}")
        
print(spline.derivative()(3.45))

print(spline.integrate(3.3,3.6))