import numpy as np
from sympy import *

x1, x2 = symbols('x1 x2')

f1 = sin(x1) + x2**2 - 1
f2 = x1**2 + cos(x2) - 2

df1_dx1 = f1.diff(x1) 
df1_dx2 = f1.diff(x2)  

df2_dx1 = f2.diff(x1) 
df2_dx2 = f2.diff(x2)  

# Print the results
print("f1:", f1)
print("∂f1/∂x1:", df1_dx1)
print("∂f1/∂x2:", df1_dx2)
print("∂f1/∂x1:", df2_dx1)
print("∂f1/∂x2:", df2_dx2)