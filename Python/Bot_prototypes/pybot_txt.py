import os
from datetime import datetime
import shutil

# Load unsafe words from a text file
def load_unsafe_words(txt_file):
    with open(txt_file, 'r') as file:
        # Read each line, strip whitespace, and convert to lowercase
        unsafe_words = set(line.strip().lower() for line in file if line.strip())
    return unsafe_words

# Check each line in the text file for unsafe words
def check_text_file(file_path, unsafe_words):
    unsafe_lines = []
    with open(file_path, 'r') as file:
        for line_number, line in enumerate(file, start=1):
            line_content = line.strip().lower()
            words_in_line = set(line_content.split())
            unsafe_matches = words_in_line.intersection(unsafe_words)
            if unsafe_matches:
                unsafe_lines.append((line_number, line_content, unsafe_matches))
    
    return unsafe_lines

# Save the unsafe chat text file to a designated folder with sequential naming
def save_unsafe_text_file(file_path, unsafe_folder, detection_count):
    # Create the directory if it doesn't exist
    if not os.path.exists(unsafe_folder):
        os.makedirs(unsafe_folder)
    
    # Define the new file path with a sequential filename
    unsafe_file_path = os.path.join(unsafe_folder, f"unsafe_chat_{detection_count}.txt")
    shutil.copy(file_path, unsafe_file_path)
    return unsafe_file_path

# Process all text files in a folder
def process_text_files_in_folder(folder_path, txt_file):
    unsafe_words = load_unsafe_words(txt_file)
    unsafe_folder = "unsafeChats_txt"  # Folder to save files with unsafe words
    
    detection_count = 1  # Counter for sequential naming
    for filename in os.listdir(folder_path):
        file_path = os.path.join(folder_path, filename)
        # Check if the file is a text file
        if filename.lower().endswith('.txt'):
            print(f"\nProcessing file: {filename}")
            try:
                unsafe_lines = check_text_file(file_path, unsafe_words)
                if unsafe_lines:
                    print("Chat Status: Unsafe Chat")
                    print("Unsafe words detected in the following lines:")
                    for line_number, line, matches in unsafe_lines:
                        print(f"- Line {line_number}: '{line}' | Unsafe words: {', '.join(matches)}")
                    
                    # Save the text file with unsafe content to `unsafeChats_txt` folder with a sequential name
                    save_unsafe_text_file(file_path, unsafe_folder, detection_count)
                    detection_count += 1
                else:
                    print("Chat Status: Safe Chat")
                    print("No unsafe words detected.")
            except Exception as e:
                print(f"Error processing {filename}: {e}")

# Run with the folder path containing text files and the .txt file with unsafe words
process_text_files_in_folder("chats_txt", "unsafe_words.txt")
