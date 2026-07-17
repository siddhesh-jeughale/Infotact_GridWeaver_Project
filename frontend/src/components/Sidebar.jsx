import { Link, useLocation } from 'react-router-dom';

const Sidebar = () => {
    const location = useLocation();
    
    const links = [
        { name: 'Dashboard', path: '/' },
        { name: 'Devices', path: '/devices' },
        { name: 'Batteries', path: '/batteries' },
        { name: 'Telemetry', path: '/telemetry' },
        { name: 'Events', path: '/events' }
    ];

    return (
        <aside className="w-64 bg-gray-900 text-white min-h-screen flex flex-col pt-6">
            <div className="px-6 pb-6 border-b border-gray-700">
                <h2 className="text-sm uppercase text-gray-400 tracking-wider">Menu</h2>
            </div>
            <nav className="flex-1 mt-4 space-y-2 px-2">
                {links.map((link) => (
                    <Link
                        key={link.name}
                        to={link.path}
                        className={`block px-4 py-3 rounded-lg transition-colors ${
                            location.pathname === link.path 
                                ? 'bg-blue-600 text-white shadow-lg shadow-blue-900/20' 
                                : 'text-gray-400 hover:bg-gray-800 hover:text-white'
                        }`}
                    >
                        {link.name}
                    </Link>
                ))}
            </nav>
        </aside>
    );
};
export default Sidebar;
