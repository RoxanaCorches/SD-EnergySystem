import Header from "../Header.jsx";
import Sidebar from "./SidebarClient.jsx";
import {Outlet} from "react-router-dom";
import SidebarClient from "./SidebarClient.jsx";



export default function AppLayoutClient(){
    return (
        <div className="h-screen w-full flex flex-col m-0">
            <Header />
            <div className="flex flex-1">
                <SidebarClient />

                <main className="flex-1 bg-gray-100 p-6 overflow-auto">
                    <Outlet />
                </main>
            </div>
        </div>
    );
}