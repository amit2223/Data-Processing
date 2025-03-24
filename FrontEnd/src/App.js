import React, { useState } from "react";
import GraphComponent from "./GraphComponent";
import HeatmapComponent from "./HeatmapComponent";

const App = () => {
  const [startTime, setStartTime] = useState(null);
  const [endTime, setEndTime] = useState(null);
  const [selectedMetric, setSelectedMetric] = useState("cumulativeUsers");

  return (
    <div>
      <h2>Tracking Dashboard</h2>
      <GraphComponent 
        setStartTime={setStartTime} 
        setEndTime={setEndTime} 
        selectedMetric={selectedMetric} 
        setSelectedMetric={setSelectedMetric} 
      />
      <HeatmapComponent startTime={startTime} endTime={endTime} />
    </div>
  );
};

export default App;
