# Pybot to extract the text form the existing screenshot image of the chat.
import pytesseract
import cv2
import os
from datetime import datetime
import shutil

# Configure Tesseract executable path (for Linux, e.g., Kali)
pytesseract.pytesseract.tesseract_cmd = '/usr/bin/tesseract'  # Update path if necessary

# Load unsafe words from a text file
def load_unsafe_words(txt_file):
    with open(txt_file, 'r') as file:
        # Read each line, strip whitespace, and convert to lowercase
        unsafe_words = set(line.strip().lower() for line in file if line.strip())
    return unsafe_words

# Extract lines of text from the provided image and check for unsafe words line-by-line
def extract_and_check_text(image_path, unsafe_words):
    img = cv2.imread(image_path)
    if img is None:
        raise ValueError(f"Could not read the image file '{image_path}'. Please check the file path and integrity.")
    
    text = pytesseract.image_to_string(img).lower()
    unsafe_lines = []
    for line in text.splitlines():
        words_in_line = set(line.split())
        unsafe_matches = words_in_line.intersection(unsafe_words)
        if unsafe_matches:
            unsafe_lines.append((line, unsafe_matches))
    
    return unsafe_lines, text

# Save image to a new folder if it contains unsafe words
def save_unsafe_image(image_path, unsafe_folder, filename):
    # Create the directory if it doesn't exist
    if not os.path.exists(unsafe_folder):
        os.makedirs(unsafe_folder)
    
    # Define the new file path and save the image there
    unsafe_image_path = os.path.join(unsafe_folder, filename)
    shutil.copy(image_path, unsafe_image_path)
    return unsafe_image_path

# Process all images in a folder
def process_images_in_folder(folder_path, txt_file):
    unsafe_words = load_unsafe_words(txt_file)
    unsafe_folder = "unsafe_chats_img"  # Folder to save images with unsafe words
    
    # Iterate over all files in the folder
    for filename in os.listdir(folder_path):
        image_path = os.path.join(folder_path, filename)
        # Check if the file is an image
        if filename.lower().endswith(('.png', '.jpg', '.jpeg', '.bmp', '.gif')):
            print(f"\nProcessing image: {filename}")
            try:
                unsafe_lines, full_text = extract_and_check_text(image_path, unsafe_words)
                if unsafe_lines:
                    print("Chat Status: Unsafe Chat")
                    print("Unsafe words detected in the following lines:")
                    for line, matches in unsafe_lines:
                        print(f"- Line: '{line}' | Unsafe words: {', '.join(matches)}")
                    
                    # Save the image with unsafe content to `unsafe_chats` folder
                    save_unsafe_image(image_path, unsafe_folder, filename)
                else:
                    print("Chat Status: Safe Chat")
                    print("No unsafe words detected.")
            except Exception as e:
                print(f"Error processing {filename}: {e}")

# Run with the folder path containing images and the .txt file with unsafe words
process_images_in_folder("Chats", "unsafe_words.txt")
