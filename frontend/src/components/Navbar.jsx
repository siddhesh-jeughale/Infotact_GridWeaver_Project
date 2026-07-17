import { Link } from 'react-router-dom';

const Navbar = () => {
    return (
        <nav className="bg-gray-800 text-white p-4 flex justify-between items-center shadow-md">
            <div className="font-bold text-xl tracking-wider text-blue-400">⚡ GridWeaver</div>
            <div className="flex space-x-4">
                <span className="text-gray-300">Admin</span>
            </div>
        </nav>
    );
};
export default Navbar;
