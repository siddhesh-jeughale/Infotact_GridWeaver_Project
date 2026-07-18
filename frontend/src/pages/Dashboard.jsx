import { useEffect, useState } from 'react';
import api from '../api/axiosConfig';

const Dashboard = () => {
    const [stats, setStats] = useState({ devices: 0, batteries: 0 });
    const [telemetry, setTelemetry] = useState([]);
    const [avgGridLoad, setAvgGridLoad] = useState(0);

    useEffect(() => {
        const fetchStats = async () => {
            try {
                const [devices, batteries] = await Promise.all([
                    api.get('/devices'),
                    api.get('/batteries')
                ]);
                setStats({ devices: devices.data.length, batteries: batteries.data.length });
            } catch (error) {
                console.error("Failed to fetch stats", error);
            }
        };
        fetchStats();
    }, []);

    useEffect(() => {
        const fetchTelemetry = async () => {
            try {
                const res = await api.get('/telemetry/latest');
                setTelemetry(res.data);
                if (res.data.length > 0) {
                    const avg = res.data.reduce((sum, t) => sum + t.gridLoadPercentage, 0) / res.data.length;
                    setAvgGridLoad(avg.toFixed(1));
                }
            } catch (error) {
                console.error("Failed to fetch telemetry", error);
            }
        };

        fetchTelemetry();
        const interval = setInterval(fetchTelemetry, 2000); // Polling every 2s
        return () => clearInterval(interval);
    }, []);

    const startSimulation = async () => {
        await api.post('/simulator/start?count=5000'); // 5000 concurrent threads
        alert("Started 5000 concurrent virtual threads!");
    };

    const stopSimulation = async () => {
        await api.post('/simulator/stop');
        alert("Stopped simulation.");
    };

    return (
        <div className="space-y-6 pb-10">
            <div className="flex justify-between items-center">
                <h1 className="text-3xl font-bold text-gray-800">Microgrid Dashboard</h1>
                <div className="space-x-4">
                    <button onClick={startSimulation} className="bg-blue-600 text-white px-4 py-2 rounded-lg font-medium shadow-sm hover:bg-blue-700 transition-colors">Start 5k Node Simulation</button>
                    <button onClick={stopSimulation} className="bg-red-500 text-white px-4 py-2 rounded-lg font-medium shadow-sm hover:bg-red-600 transition-colors">Stop</button>
                </div>
            </div>
            
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
                    <h3 className="text-gray-500 text-sm font-medium">Total Devices</h3>
                    <p className="text-3xl font-bold text-gray-800 mt-2">{stats.devices}</p>
                </div>
                <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
                    <h3 className="text-gray-500 text-sm font-medium">Total Batteries</h3>
                    <p className="text-3xl font-bold text-gray-800 mt-2">{stats.batteries}</p>
                </div>
                <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
                    <h3 className="text-gray-500 text-sm font-medium">Avg Grid Load</h3>
                    <p className="text-3xl font-bold text-blue-600 mt-2">{avgGridLoad} %</p>
                </div>
                <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
                    <h3 className="text-gray-500 text-sm font-medium">Active Faults</h3>
                    <p className="text-3xl font-bold text-red-500 mt-2">0</p>
                </div>
            </div>

            <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
                <div className="p-4 bg-gray-50 border-b border-gray-100 font-bold text-gray-700">Live Telemetry Feed (Polling)</div>
                <table className="w-full text-left border-collapse text-sm">
                    <thead>
                        <tr className="border-b border-gray-100">
                            <th className="p-4 font-medium text-gray-500">Timestamp</th>
                            <th className="p-4 font-medium text-gray-500">Device ID</th>
                            <th className="p-4 font-medium text-gray-500">Power (kW)</th>
                            <th className="p-4 font-medium text-gray-500">Load (%)</th>
                        </tr>
                    </thead>
                    <tbody>
                        {telemetry.map(t => (
                            <tr key={t.id} className="border-b border-gray-50 hover:bg-gray-50">
                                <td className="p-4 text-gray-600">{new Date(t.timestamp).toLocaleTimeString()}</td>
                                <td className="p-4 font-medium text-gray-800">Node-{t.deviceId}</td>
                                <td className="p-4 text-green-600 font-medium">{t.powerOutputKw} kW</td>
                                <td className="p-4 text-blue-600">{t.gridLoadPercentage}%</td>
                            </tr>
                        ))}
                        {telemetry.length === 0 && (
                            <tr><td colSpan="4" className="p-4 text-center text-gray-500">No live telemetry detected. Start the simulation.</td></tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};
export default Dashboard;
