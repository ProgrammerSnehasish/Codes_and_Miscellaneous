# Pybot to extract the text form the recently taken screenshot image of the chat.
import pyautogui
import pytesseract
import cv2
import os
from datetime import datetime

# Configure Tesseract executable path (for Linux, e.g., Kali)
pytesseract.pytesseract.tesseract_cmd = '/usr/bin/tesseract'  # Update path as necessary

# Load unsafe words from a text file
def load_unsafe_words(txt_file):
    with open(txt_file, 'r') as file:
        # Read each line, strip whitespace, and convert to lowercase
        unsafe_words = set(line.strip().lower() for line in file if line.strip())
    return unsafe_words

# Capture a screenshot
def take_screenshot():
    # Define the save directory
    save_dir = "saved_screenshots"
    # Create the directory if it doesn't exist
    if not os.path.exists(save_dir):
        os.makedirs(save_dir)
    # Define the file path with a timestamped filename
    screenshot_path = os.path.join(save_dir, f"screenshot_{datetime.now().strftime('%Y%m%d_%H%M%S')}.png")
    # Capture and save the screenshot
    screenshot = pyautogui.screenshot()
    screenshot.save(screenshot_path)
    return screenshot_path

# Extract text from image using OCR
def extract_text_from_image(image_path):
    img = cv2.imread(image_path)
    text = pytesseract.image_to_string(img)
    return text.lower()

# Compare extracted words with unsafe words and find matching lines
def check_chat_safety(extracted_text, unsafe_words):
    lines = extracted_text.splitlines()
    unsafe_matches = {}
    for i, line in enumerate(lines):
        words_in_line = set(line.split())
        matches = words_in_line.intersection(unsafe_words)
        if matches:
            unsafe_matches[i + 1] = (line, matches)  # Line number starts from 1
    if unsafe_matches:
        return "Unsafe Chat", unsafe_matches
    else:
        return "Safe Chat", {}

# Save unsafe chat text in the unsafe_chats directory
def save_unsafe_chat(extracted_text, detection_count):
    # Define the save directory
    save_dir = "unsafe_chats_img"
    if not os.path.exists(save_dir):
        os.makedirs(save_dir)
    # Define the file path with sequential naming
    chat_path = os.path.join(save_dir, f"unsafe_chat_{detection_count}.txt")
    # Save the chat text
    with open(chat_path, "w") as file:
        file.write(extracted_text)
    print(f"Chat saved to: {chat_path}")

# Main function to run the entire flow
def main(txt_file):
    unsafe_words = load_unsafe_words(txt_file)
    screenshot_path = take_screenshot()
    extracted_text = extract_text_from_image(screenshot_path)
    status, matched_lines = check_chat_safety(extracted_text, unsafe_words)
    print(f"Chat Status: {status}")
    
    if matched_lines:
        # Print each line with unsafe words
        for line_num, (line, words) in matched_lines.items():
            print(f"Line {line_num}: '{line}' contains unsafe words: {', '.join(words)}")
        
        # Save unsafe chat text sequentially
        detection_count = len(os.listdir("unsafe_chats")) + 1
        save_unsafe_chat(extracted_text, detection_count)
    else:
        print("No unsafe words detected.")

# Run with your .txt file path
main("unsafe_words.txt")
