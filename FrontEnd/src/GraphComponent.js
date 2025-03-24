import React, { useEffect, useState } from "react";
import { LineChart, Line, XAxis, YAxis, Tooltip, Legend, ResponsiveContainer } from "recharts";
import moment from "moment"; 

const API_URL = "http://localhost:8080/api/data";

const GraphComponent = ({ setStartTime, setEndTime, selectedMetric, setSelectedMetric }) => {
  const [graphData, setGraphData] = useState([]);

  useEffect(() => {
    fetch(`${API_URL}/graph`)
      .then((res) => res.json())
      .then((data) => {
        setGraphData(data);
        if (data.length > 0) {
          setStartTime(data[0].timestamp);
          setEndTime(data[data.length - 1].timestamp);
        }
      });
  }, []);

  const formatTimestamp = (timestamp) => {
    return moment(timestamp).format("YYYY-MM-DD HH:mm:ss");
  };

  const CustomTooltip = ({ payload, label }) => {
    if (payload && payload.length > 0) {
      const data = payload[0].payload;
      return (
        <div className="custom-tooltip">
          <p>{`Time: ${formatTimestamp(data.timestamp)}`}</p>
          <p>{`${selectedMetric}: ${data[selectedMetric]}`}</p>
        </div>
      );
    }
    return null;
  };

  return (
    <div>
      <h3>Tracking Graph</h3>

      {/* Dropdown to select the metric */}
      <select value={selectedMetric} onChange={(e) => setSelectedMetric(e.target.value)}>
        <option value="cumulativeUsers">Total Humans</option>
        <option value="avgX">Avg X Position</option>
        <option value="avgY">Avg Y Position</option>
      </select>

      {/* Graph Display */}
      <ResponsiveContainer width="100%" height={400}>
        <LineChart data={graphData}>
          <XAxis
            dataKey="timestamp"
            tickFormatter={formatTimestamp} // Format X-axis timestamp with moment.js
          />
          <YAxis />
          <Tooltip content={<CustomTooltip />} />
          <Legend />
          <Line type="monotone" dataKey={selectedMetric} stroke="#ff7300" name={selectedMetric} />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default GraphComponent;
