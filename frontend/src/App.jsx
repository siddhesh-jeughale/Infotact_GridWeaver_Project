import { BrowserRouter, Routes, Route } from 'react-router-dom';
import DashboardLayout from './layouts/DashboardLayout';
import Dashboard from './pages/Dashboard';
import Devices from './pages/Devices';
import Batteries from './pages/Batteries';
import Telemetry from './pages/Telemetry';
import Events from './pages/Events';

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<DashboardLayout />}>
                    <Route index element={<Dashboard />} />
                    <Route path="devices" element={<Devices />} />
                    <Route path="batteries" element={<Batteries />} />
                    <Route path="telemetry" element={<Telemetry />} />
                    <Route path="events" element={<Events />} />
                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
