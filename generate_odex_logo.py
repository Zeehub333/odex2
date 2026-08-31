import re

# Precise coordinates for ODEX logo in the exact Axelor branding style:
# Slant vector: slope = 116.2 / -31.1 ≈ -3.736 (-15 degrees)
# Colors:
# Dark Slate / Navy: #32325d
# Emerald Green: #3ecf8e

# Glyphs:
# 1. Letter 'o':
# Repositioned from original (originally at x ~ 358.6) to x = 50.0
# Offset: dx = -308.6
# 2. Letter 'd':
# Bowl based on 'o'/'a' + ascender reaching y=8.7
# 3. Letter 'e':
# Repositioned from original (originally at x ~ 245.7) to x = 205.0
# Offset: dx = -40.7
# 4. Letter 'x':
# Repositioned from original (originally at x ~ 185.3) to x = 360.0
# Offset: dx = +174.7

svg_template = """<svg xmlns="http://www.w3.org/2000/svg" xml:space="preserve" id="Calque_1" x="0" y="0" version="1.1" viewBox="0 0 463 166">
  <style>
    .st0 { fill: #32325d; }
    .st1 { fill: #3ecf8e; }
  </style>
  <!-- Letter 'o' -->
  <g transform="translate(-304, 0)">
    <path class="st0" d="M358.6 42.3c13.3 0 21.8 1.9 25.3 5.8 4.7 5 4.4 17.1-.6 36.1-2.7 9.9-5.1 17.2-7.3 21.8-4.3 8.7-9.7 14.4-16.3 17-5.2 2-13 3.1-23.3 3.1-13.6 0-22.2-1.9-25.7-5.8-3.2-3.5-4.3-9.3-3.4-17.4.5-4.1 1.9-10.7 4.4-19.7 4.9-18.3 11-30 18.4-35 5.6-3.9 15.1-5.9 28.5-5.9m-2.7 10.1c-9.5 0-15.9 1.1-19.1 3.3-4.4 3.1-8.6 12.3-12.8 27.6-4.4 16.6-5.3 26.4-2.5 29.4 2 2.2 7.8 3.2 17.4 3.2 9.5 0 15.8-1.1 18.9-3.2 4.4-3.1 8.7-12.6 13-28.5s5-25.4 2.3-28.4c-2-2.2-7.7-3.4-17.2-3.4"/>
  </g>

  <!-- Letter 'd' -->
  <g transform="translate(-154, 0)">
    <!-- Ascender Stem + Bowl of 'd' -->
    <path class="st0" d="M344.2 8.7l-22.5 84c-.4 1.5-.6 2.7-.8 3.4-2.6 9.5-8 15.9-16.5 19.2-5.4 2.1-13.7 3.1-24.9 3.1-15 0-24.1-3.3-27.2-9.8-2.6-5.4-1.7-16.1 2.5-32 4.2-15.5 8.9-26.1 14.2-31.7 6.4-6.8 17.3-10.2 32.6-10.2 7.6 0 13.5.6 17.8 1.7l14.1-52.7zm-27.8 44.7c-2.7-1.2-7.3-1.8-14-1.8-6 0-10.3.3-13.1 1-4.9 1.2-8.6 3.8-11.3 7.7-2.3 3.4-4.4 8.9-6.4 16.5-2.8 10.4-3.6 17.6-2.5 21.6 1.3 4.6 6.8 6.9 16.5 6.9 8.2 0 14.2-1.3 18.1-4 3.2-2.3 5.8-6.6 7.7-13z"/>
    <!-- Lower terminal spur of 'd' -->
    <path class="st0" d="M320.5 97.4l-7.4 27.5h12.5l7.4-27.5z"/>
  </g>

  <!-- Letter 'e' -->
  <g transform="translate(-18, 0)">
    <path class="st0" d="M245.7 100.3h12.2c-.4 1.5-.6 2.7-.8 3.4-2.6 9.5-8 15.9-16.5 19.2-5.4 2.1-13.7 3.1-24.9 3.1-15 0-24.1-3.3-27.2-9.8-2.6-5.4-1.7-16.1 2.5-32 4.2-15.5 8.9-26.1 14.2-31.7 6.4-6.8 17.3-10.2 32.6-10.2 11.1 0 18.6 1.2 22.6 3.5 6.1 3.6 7.7 12 5 25-.6 2.8-1.9 8-3.8 15.6H203c-3.1 11.9-4.1 19.4-3 22.7 1.7 4.6 7.8 6.9 18.5 6.9 12.6 0 20.3-1.8 23.2-5.4 1.3-1.7 2.6-5.1 4-10.3m6.2-23.2c1.8-7 2.7-11.4 2.8-13.3.4-5.3-1.1-8.7-4.4-10.2-2.7-1.2-7.3-1.8-14-1.8-6 0-10.3.3-13.1 1-4.9 1.2-8.6 3.8-11.3 7.7-2.3 3.4-4.4 8.9-6.4 16.5z"/>
  </g>

  <!-- Letter 'x' (The Signature Green Cross) -->
  <g transform="translate(178, 0)">
    <path class="st1" d="M185.3 125.3h-18.8l-27.1-30.1-71 62.2h-6.1l68.8-73.1-35.2-39.6h19L139.6 73l70.2-60.5h6.1l-67.3 71.7z"/>
  </g>
</svg>
"""

with open("c:/odex2/odex_logo.svg", "w", encoding="utf-8") as f:
    f.write(svg_template)

print("odex_logo.svg generated successfully")
