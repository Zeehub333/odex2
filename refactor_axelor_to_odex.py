import os
import re
import sys

BASE_DIR = os.path.dirname(os.path.abspath(__file__))

TEXT_EXTENSIONS = {
    '.java', '.xml', '.gradle', '.properties', '.json', '.yaml', '.yml',
    '.csv', '.md', '.txt', '.html', '.js', '.css', '.bat', '.cmd', '.sh', '.groovy'
}

IGNORE_KEYWORDS = {'node_modules', '.yarn', 'map-viewer', 'build', '.gradle', '.git', '.idea', 'venv', '__pycache__', 'open-suite-webapp'}

def should_skip(path):
    parts = path.replace('\\', '/').split('/')
    return any(p in IGNORE_KEYWORDS for p in parts)

def is_text_file(filepath):
    _, ext = os.path.splitext(filepath)
    return ext.lower() in TEXT_EXTENSIONS or os.path.basename(filepath) in {'LICENSE', 'Dockerfile', 'version.txt'}

def replace_content_in_file(filepath):
    try:
        with open(filepath, 'r', encoding='utf-8', errors='ignore') as f:
            content = f.read()

        original = content

        # 1. Project & Module dependencies
        content = content.replace(':modules:axelor-', ':modules:odex-')
        content = content.replace(':modules:axelor_', ':modules:odex_')
        content = content.replace('modules/axelor-open-suite', 'modules/odex-open-suite')
        content = content.replace('axelor-open-suite', 'odex-open-suite')
        content = content.replace('axelor-erp', 'odex-erp')
        content = content.replace('Axelor ERP', 'Odex ERP')
        content = content.replace('Axelor Open Suite', 'Odex Open Suite')
        content = content.replace('AXELOR OPEN SUITE', 'ODEX OPEN SUITE')

        # 2. Titles and module headers
        content = re.sub(r'title\s*=\s*["\']Axelor\s+', 'title = "Odex ', content)
        content = re.sub(r'title\s+["\']Axelor\s+', 'title "Odex ', content)
        content = re.sub(r'description\s*=\s*["\']Axelor\s+', 'description = "Odex ', content)
        content = re.sub(r'description\s+["\']Axelor\s+', 'description "Odex ', content)

        # 3. Fix any accidental mangling of core Axelor platform schemas or plugins
        content = content.replace("id 'com.odex.app'", "id 'com.axelor.app'")
        content = content.replace('id "com.odex.app"', 'id "com.axelor.app"')
        content = content.replace('http://odex.com/xml/ns/', 'http://axelor.com/xml/ns/')
        content = content.replace('https://repository.odex.com', 'https://repository.axelor.com')
        content = content.replace('com.odex.addons:', 'com.axelor.addons:')

        if content != original:
            with open(filepath, 'w', encoding='utf-8') as f:
                f.write(content)
            return True
    except Exception as e:
        print(f"Error on {filepath}: {e}", flush=True)
    return False

def rename_module_folders():
    print("[1/3] Renaming module directories (axelor-* -> odex-*)...", flush=True)
    count = 0
    for name in os.listdir(BASE_DIR):
        if name.startswith('axelor-') and os.path.isdir(os.path.join(BASE_DIR, name)):
            new_name = 'odex-' + name[7:]
            old_path = os.path.join(BASE_DIR, name)
            new_path = os.path.join(BASE_DIR, new_name)
            try:
                if not os.path.exists(new_path):
                    os.rename(old_path, new_path)
                    count += 1
            except Exception as e:
                print(f"Error renaming {name}: {e}", flush=True)
    print(f"  Done. Renamed {count} directories.", flush=True)

def rename_files():
    print("[2/3] Renaming files (axelor-* -> odex-*)...", flush=True)
    count = 0
    for root, dirs, files in os.walk(BASE_DIR):
        if should_skip(root):
            continue
        dirs[:] = [d for d in dirs if not should_skip(os.path.join(root, d))]
        for file in files:
            new_file = None
            if file.startswith('axelor-'):
                new_file = 'odex-' + file[7:]
            elif file.startswith('axelor_'):
                new_file = 'odex_' + file[7:]

            if new_file and new_file != file:
                old_path = os.path.join(root, file)
                new_path = os.path.join(root, new_file)
                try:
                    os.rename(old_path, new_path)
                    count += 1
                except Exception as e:
                    print(f"Error renaming file {file}: {e}", flush=True)
    print(f"  Done. Renamed {count} files.", flush=True)

def replace_contents():
    print("[3/3] Replacing content in all module files...", flush=True)
    total_files = 0
    modified = 0
    for root, dirs, files in os.walk(BASE_DIR):
        if should_skip(root):
            continue
        dirs[:] = [d for d in dirs if not should_skip(os.path.join(root, d))]
        for file in files:
            filepath = os.path.join(root, file)
            if is_text_file(filepath):
                total_files += 1
                if replace_content_in_file(filepath):
                    modified += 1
    print(f"  Done. Scanned {total_files} files, updated {modified} files.", flush=True)

def main():
    print("==============================================================================", flush=True)
    print("                ODEX REBRANDING TOOL (Axelor -> Odex)", flush=True)
    print("==============================================================================", flush=True)
    rename_module_folders()
    rename_files()
    replace_contents()
    print("==============================================================================", flush=True)
    print("  [SUCCESS] All files, folders, and contents updated to 'odex'!", flush=True)
    print("==============================================================================", flush=True)

if __name__ == '__main__':
    main()
