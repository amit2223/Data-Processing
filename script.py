import time
import json
import requests
from datetime import datetime, timezone

# API endpoint
API_URL = "http://localhost:8080/api/publish-data"

# Function to process data in a memory-efficient way
def process_large_json(file_path):
    with open(file_path, "r") as file:
        data = json.load(file)  # Load JSON (Assuming the entire file is an array)
    
    previous_timestamp = None

    for entry in data:
        # Extract timestamp in milliseconds
        timestamp_ms = int(entry["timestamp"]["$date"]["$numberLong"])  # Extract epoch in ms

        # Introduce delay based on timestamp difference
        if previous_timestamp:
            delay = (timestamp_ms - previous_timestamp) / 1000.0  # Convert ms to seconds
            print(f"Waiting {delay} seconds before sending next data...")
            time.sleep(delay)  # Wait before sending the next entry

        # Extract instance data
        instance_id = list(entry["instances"].keys())[0]  # Extract instance ID (e.g., "1")
        instance_data = entry["instances"][instance_id]  # Extract actual instance data

        # Prepare the payload for API
        payload = {
            "timestamp": timestamp_ms,  # Send timestamp as epoch milliseconds
            "pos_x": instance_data["pos_x"],
            "pos_y": instance_data["pos_y"],
            "vel_x": instance_data["vel_x"],
            "vel_y": instance_data["vel_y"],
            "confidence": instance_data["confidence"],
            "sensors": instance_data["sensors"]
        }

        # Send data to API
        response = requests.post(API_URL, json=payload)

        print(f"Sent data: {payload}, Response: {response.status_code}")

        previous_timestamp = timestamp_ms  # Update previous timestamp

# Call the function with your JSON file path
process_large_json("FilteredDataHuman.json")
