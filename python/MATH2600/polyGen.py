from sympy import symbols, expand
import re

pop = [341948, 1092759, 5491, 49375, 1340000, 365, 2500, 78200, 867023, 14000, 23700, 70700, 304500, 138000, 2602000]

vel = [4.81, 5.88, 3.31, 4.90, 5.62, 2.76, 2.27, 3.85, 5.21, 3.70, 3.27, 4.31, 4.42, 4.39, 5.05]

var = symbols('x')

def L(degree, x_points):
    numerator = 1
    denominator = 1

    for i in range(len(x_points)):
        if i != degree:
            numerator *= var - x_points[i]
            denominator *= x_points[degree] - x_points[i]
    
    return expand(numerator/denominator)

def larange(x_points, y_points):
    total = 0

    for i in range(len(x_points)):
        total += y_points[i] * L(i, x_points)
    
    latex_expr = to_latex(total)

    print(latex_expr)

def to_latex(expression):
    """Convert e-notation to LaTeX scientific notation and format for LaTeX"""
    expr_str = str(expression)
    
    # Replace e-notation with LaTeX scientific notation and round to 3 decimals
    def replace_e(match):
        coefficient = match.group(1)
        exponent = match.group(2)
        coeff_val = round(float(coefficient), 3)
        return f"{coeff_val} \\times 10^{{{exponent}}}"
    
    expr_str = re.sub(r'([\d.]+)e([+-]?\d+)', replace_e, expr_str)
    
    # Round standalone decimal numbers to 3 decimals
    def round_decimal(match):
        num_str = match.group(0)
        try:
            coeff = float(num_str)
            return str(round(coeff, 3))
        except:
            return num_str
    
    # Match decimals (including leading zeros like 0.001024...) with various lookaheads
    expr_str = re.sub(r'\d+\.\d+', round_decimal, expr_str)
    
    # Replace x**n with x^{n} for LaTeX
    expr_str = re.sub(r'x\*\*(\d+)', r'x^{\1}', expr_str)
    
    # Add space around operators for readability
    expr_str = re.sub(r'(\d|\})\s*([+-])\s*', r'\1 \2 ', expr_str)
    
    return expr_str


larange(pop, vel)