import { useEffect, useState } from 'react';
import api from '../api/axiosConfig';

const Devices = () => {
    const [devices, setDevices] = useState([]);

    useEffect(() => {
        api.get('/devices').then(res => setDevices(res.data)).catch(console.error);
    }, []);

    return (
        <div>
            <h1 className="text-3xl font-bold text-gray-800 mb-6">Device Management</h1>
            <div className="bg-white rounded-xl shadow-sm border border-gray-100 overflow-hidden">
                <table className="w-full text-left border-collapse">
                    <thead>
                        <tr className="bg-gray-50 border-b border-gray-100">
                            <th className="p-4 font-medium text-gray-500">ID</th>
                            <th className="p-4 font-medium text-gray-500">Name</th>
                            <th className="p-4 font-medium text-gray-500">Type</th>
                            <th className="p-4 font-medium text-gray-500">Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        {devices.map(device => (
                            <tr key={device.id} className="border-b border-gray-50">
                                <td className="p-4">{device.id}</td>
                                <td className="p-4">{device.name}</td>
                                <td className="p-4">{device.type}</td>
                                <td className="p-4">{device.status}</td>
                            </tr>
                        ))}
                        {devices.length === 0 && (
                            <tr><td colSpan="4" className="p-4 text-center text-gray-500">No devices found.</td></tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};
export default Devices;
