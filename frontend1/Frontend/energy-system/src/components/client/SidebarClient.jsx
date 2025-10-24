import { NavLink } from "react-router-dom";
import { FaHome } from "react-icons/fa";
import { IoMdLogOut } from "react-icons/io";


export default function SidebarClient() {
    const linkClass = ({ isActive }) =>
        `flex items-center gap-3 px-4 py-3 rounded-xl transition-all duration-200 ${
            isActive
                ? "bg-gray-600 text-white shadow-md" // activ: negru complet, text alb
                : "text-gray-600 hover:bg-gray-200 hover:text-black" // inactiv: gri închis, hover gri deschis
        }`;

    return (
        <aside className="w-64 h-screen bg-white text-black shadow-lg flex flex-col py-12">
            <div className="px-10 mb-12">
                <h1 className="text-2xl font-bold text-gray-500 tracking-wide">
                    Energy System
                </h1>
            </div>

            <ul className="flex flex-col space-y-6 px-4 text-lg font-medium">
                <li>
                    <NavLink to="/client" className={linkClass}>
                        <FaHome className=" text-gray-400 text-2xl" />
                        <span className="text-gray-500">Home</span>
                    </NavLink>
                </li>

                <li>
                    <NavLink to="/myDevices" className={linkClass}>
                        <FaHome className=" text-gray-400 text-2xl" />
                        <span className="text-gray-500">My Device</span>
                    </NavLink>
                </li>

                <li className="mt-auto">
                    <NavLink
                        to="/"
                        className={linkClass}>
                        <IoMdLogOut className="text-gray-400 text-2xl" />
                        <span className="text-gray-500">Logout</span>
                    </NavLink>
                </li>
            </ul>
        </aside>
    );
}
