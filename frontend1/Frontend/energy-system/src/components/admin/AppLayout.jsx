import Header from "../Header.jsx";
import Sidebar from "./Sidebar.jsx";
import Container from "../Container.jsx";
import {Outlet} from "react-router-dom";

export default function AppLayout(){
    return (
        <div className="h-screen w-full flex flex-col m-0">
            <Header />
            <div className="flex flex-1">
                <Sidebar />

                <main className="flex-1 bg-gray-100 p-6 overflow-auto">
                    <Outlet />
                </main>
            </div>
        </div>
    );
}