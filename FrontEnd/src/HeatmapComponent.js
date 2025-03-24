import React, { useEffect, useState } from "react";
import heatmap from "heatmap.js"; 
import moment from "moment"; 

const API_URL = "http://localhost:8080/api/data"; // API endpoint

const HeatmapComponent = () => {
  const [startTime, setStartTime] = useState(null);
  const [endTime, setEndTime] = useState(null);

  // Fetch heatmap data on component mount
  useEffect(() => {
    fetch(`${API_URL}/heatmap`)
      .then((res) => res.json())
      .then((data) => {
        console.log("Fetched heatmap data:", data); 

       
        if (data.length > 0) {
          setStartTime(data[0].timestamp); 
          setEndTime(data[data.length - 1].timestamp);
        }

        createHeatmap(data);
      })
      .catch((error) => {
        console.error("Error fetching heatmap data:", error);
      });
  }, []);

  const formatTimestamp = (timestamp) => {
    return moment(timestamp).format("YYYY-MM-DD HH:mm:ss");
  };

  const createHeatmap = (data) => {
    const container = document.getElementById("heatmapContainer");
    const containerWidth = container.offsetWidth;
    const containerHeight = container.offsetHeight;

    // Find the maximum x and y values in the data
    const maxX = Math.max(...data.map((point) => point.posX));
    const maxY = Math.max(...data.map((point) => point.posY));

    // Prepare data for heatmap.js
    const points = data.map((point) => ({
      x: Math.floor((point.posX / maxX) * containerWidth), 
      y: Math.floor((point.posY / maxY) * containerHeight), 
      value: point.intensity,
    }));

    console.log("Formatted points for heatmap:", points);

    // Initialize the heatmap instance
    const heatmapInstance = heatmap.create({
      container: container, 
      radius: 30, 
      maxOpacity: 0.5,
      minOpacity: 0, 
      blur: 0.85, 
    });

    console.log("Heatmap instance created:", heatmapInstance); 

    // Set the heatmap data
    heatmapInstance.setData({
      max: Math.max(...points.map((p) => p.value)), 
      data: points, 
    });

    // Add x and y axes with 6 divisions
    addAxes(containerWidth, containerHeight, maxX, maxY);
  };

  const addAxes = (width, height, maxX, maxY) => {
    const container = document.getElementById("heatmapContainer");

    // Create x-axis
    const xAxis = document.createElement("div");
    xAxis.style.position = "absolute";
    xAxis.style.bottom = "0";
    xAxis.style.left = "0";
    xAxis.style.width = "100%";
    xAxis.style.height = "20px";
    xAxis.style.borderTop = "1px solid black";
    xAxis.style.display = "flex";
    xAxis.style.justifyContent = "space-between";
    xAxis.style.alignItems = "center";
    xAxis.style.padding = "0 10px";

    const xLabels = Array.from({ length: 6 }, (_, i) => ((maxX / 5) * i).toFixed(2)); // 6 divisions
    xLabels.forEach((label) => {
      const labelElement = document.createElement("span");
      labelElement.innerText = label;
      xAxis.appendChild(labelElement);
    });

    container.appendChild(xAxis);

    // Create y-axis
    const yAxis = document.createElement("div");
    yAxis.style.position = "absolute";
    yAxis.style.top = "0";
    yAxis.style.left = "0";
    yAxis.style.width = "20px";
    yAxis.style.height = "100%";
    yAxis.style.borderRight = "1px solid black";
    yAxis.style.display = "flex";
    yAxis.style.flexDirection = "column";
    yAxis.style.justifyContent = "space-between";
    yAxis.style.alignItems = "center";
    yAxis.style.padding = "10px 0";

    // Add y-axis labels (6 divisions)
    const yLabels = Array.from({ length: 6 }, (_, i) => ((maxY / 5) * i).toFixed(2)); // 6 divisions
    yLabels.forEach((label) => {
      const labelElement = document.createElement("span");
      labelElement.innerText = label;
      yAxis.appendChild(labelElement);
    });

    container.appendChild(yAxis);
  };

  return (
    <div>
      <h3>Positional Heatmap</h3>
      {/* Heatmap container */}
      <div
        id="heatmapContainer"
        style={{
          width: "100%",
          height: "500px",
          position: "relative",
        }}
      ></div>

      {/* Displaying start and end time */}
      <div>
        <h4>Start Time: {startTime ? formatTimestamp(startTime) : "Loading..."}</h4>
        <h4>End Time: {endTime ? formatTimestamp(endTime) : "Loading..."}</h4>
      </div>
    </div>
  );
};

export default HeatmapComponent;