import os
import urllib.parse

# ODEX Light SVG
odex_light_svg = """<svg xmlns='http://www.w3.org/2000/svg' xml:space='preserve' id='Calque_1' x='0' y='0' version='1.1' viewBox='0 0 463 166'><style>.st0{fill:#32325d}</style><path d='M55 42.3c13.3 0 21.8 1.9 25.3 5.8 4.7 5 4.4 17.1-.6 36.1-2.7 9.9-5.1 17.2-7.3 21.8-4.3 8.7-9.7 14.4-16.3 17-5.2 2-13 3.1-23.3 3.1-13.6 0-22.2-1.9-25.7-5.8-3.2-3.5-4.3-9.3-3.4-17.4.5-4.1 1.9-10.7 4.4-19.7 4.9-18.3 11-30 18.4-35 5.6-3.9 15.1-5.9 28.5-5.9m-2.7 10.1c-9.5 0-15.9 1.1-19.1 3.3-4.4 3.1-8.6 12.3-12.8 27.6-4.4 16.6-5.3 26.4-2.5 29.4 2 2.2 7.8 3.2 17.4 3.2 9.5 0 15.8-1.1 18.9-3.2 4.4-3.1 8.7-12.6 13-28.5s5-25.4 2.3-28.4c-2-2.2-7.7-3.4-17.2-3.4M190.2 8.7l-22.5 84c-.4 1.5-.6 2.7-.8 3.4-2.6 9.5-8 15.9-16.5 19.2-5.4 2.1-13.7 3.1-24.9 3.1-15 0-24.1-3.3-27.2-9.8-2.6-5.4-1.7-16.1 2.5-32 4.2-15.5 8.9-26.1 14.2-31.7 6.4-6.8 17.3-10.2 32.6-10.2 7.6 0 13.5.6 17.8 1.7l14.1-52.7h13.6zm-27.8 44.7c-2.7-1.2-7.3-1.8-14-1.8-6 0-10.3.3-13.1 1-4.9 1.2-8.6 3.8-11.3 7.7-2.3 3.4-4.4 8.9-6.4 16.5-2.8 10.4-3.6 17.6-2.5 21.6 1.3 4.6 6.8 6.9 16.5 6.9 8.2 0 14.2-1.3 18.1-4 3.2-2.3 5.8-6.6 7.7-13l7.1-26.6zM166.5 97.4l-7.4 27.5h12.5l7.4-27.5zM227.7 100.3h12.2c-.4 1.5-.6 2.7-.8 3.4-2.6 9.5-8 15.9-16.5 19.2-5.4 2.1-13.7 3.1-24.9 3.1-15 0-24.1-3.3-27.2-9.8-2.6-5.4-1.7-16.1 2.5-32 4.2-15.5 8.9-26.1 14.2-31.7 6.4-6.8 17.3-10.2 32.6-10.2 11.1 0 18.6 1.2 22.6 3.5 6.1 3.6 7.7 12 5 25-.6 2.8-1.9 8-3.8 15.6H185c-3.1 11.9-4.1 19.4-3 22.7 1.7 4.6 7.8 6.9 18.5 6.9 12.6 0 20.3-1.8 23.2-5.4 1.3-1.7 2.6-5.1 4-10.3m6.2-23.2c1.8-7 2.7-11.4 2.8-13.3.4-5.3-1.1-8.7-4.4-10.2-2.7-1.2-7.3-1.8-14-1.8-6 0-10.3.3-13.1 1-4.9 1.2-8.6 3.8-11.3 7.7-2.3 3.4-4.4 8.9-6.4 16.5z' class='st0'/><path d='M363.3 125.3h-18.8l-27.1-30.1-71 62.2h-6.1l68.8-73.1-35.2-39.6h19L317.6 73l70.2-60.5h6.1l-67.3 71.7z' style='fill:#3ecf8e'/></svg>"""

odex_dark_svg = odex_light_svg.replace(".st0{fill:#32325d}", ".st0{fill:#FFFFFF}")

def to_data_uri(svg):
    # Encode matching Vite's data:image/svg+xml format:
    # '%3csvg...'
    encoded = urllib.parse.quote(svg, safe="/:?=")
    # Fix quote specifics
    encoded = encoded.replace("<", "%3c").replace(">", "%3e").replace("#", "%23")
    return f"data:image/svg+xml,{encoded}"

new_light_data_uri = to_data_uri(odex_light_svg)
new_dark_data_uri = to_data_uri(odex_dark_svg)

files_to_patch = [
    r"c:\odex2\open-suite-webapp\build\webapp\assets\loading-button-DPQAc18M.js",
    r"C:\Program Files\Apache Software Foundation\Tomcat 11.0\webapps\axelor-erp\assets\loading-button-DPQAc18M.js"
]

# Search any other JS in open-suite-webapp and Tomcat
for base in [r"c:\odex2\open-suite-webapp", r"C:\Program Files\Apache Software Foundation\Tomcat 11.0\webapps\axelor-erp"]:
    for root, dirs, files in os.walk(base):
        for f in files:
            if f.endswith(".js"):
                fp = os.path.join(root, f)
                if fp not in files_to_patch:
                    files_to_patch.append(fp)

patched_count = 0
for fp in files_to_patch:
    if not os.path.exists(fp):
        continue
    try:
        with open(fp, "r", encoding="utf-8") as f:
            content = f.read()

        orig = content

        # Replace Axelor SVG paths in JS
        # Look for the old light SVG data URI
        old_light_marker = "%3cstyle%3e.st0{fill:%2332325d}%3c/style%3e"
        old_dark_marker = "%3cstyle%3e.st0{fill:%23FFFFFF}%3c/style%3e"

        if "Calque_1" in content:
            # Replace dark constant
            start_y = content.find('const y="data:image/svg+xml,')
            if start_y != -1:
                end_y = content.find('"', start_y + 9)
                if end_y != -1:
                    content = content[:start_y] + f'const y="{new_dark_data_uri}"' + content[end_y+1:]

            # Replace light constant
            start_A = content.find('A="data:image/svg+xml,')
            if start_A != -1:
                end_A = content.find('"', start_A + 3)
                if end_A != -1:
                    content = content[:start_A] + f'A="{new_light_data_uri}"' + content[end_A+1:]

            if content != orig:
                with open(fp, "w", encoding="utf-8") as f:
                    f.write(content)
                patched_count += 1
                print(f"Patched JS bundle: {fp}")
    except Exception as e:
        print(f"Error patching {fp}: {e}")

print(f"Total patched bundles: {patched_count}")
