import { useEffect, useState } from 'react';
import api from '../api/axiosConfig';

const Dashboard = () => {
    const [stats, setStats] = useState({ devices: 0, batteries: 0 });

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

    return (
        <div className="space-y-6">
            <h1 className="text-3xl font-bold text-gray-800">Microgrid Dashboard</h1>
            
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
                    <h3 className="text-gray-500 text-sm font-medium">Grid Load</h3>
                    <p className="text-3xl font-bold text-blue-600 mt-2">-- %</p>
                </div>
                <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100">
                    <h3 className="text-gray-500 text-sm font-medium">Active Faults</h3>
                    <p className="text-3xl font-bold text-red-500 mt-2">0</p>
                </div>
            </div>

            <div className="bg-white p-6 rounded-xl shadow-sm border border-gray-100 h-96 flex items-center justify-center">
                <p className="text-gray-400 italic">GIS Map Placeholder (Leaflet integration pending in Week 2)</p>
            </div>
        </div>
    );
};
export default Dashboard;
