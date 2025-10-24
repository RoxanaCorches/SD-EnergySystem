import { useEffect, useState } from 'react';
import avatar from '../assets/avatar.png';
import energy_system from "../assets/energy_system.png"

export default function Header() {
    const [username, setUsername] =useState("");

    useEffect(() =>{
        const userData = localStorage.getItem("user");
        console.log("Datele despre user",userData);
        if(userData){
            const user = JSON.parse(userData);
            console.log("Numele", user.username);
            setUsername(user.username);
        }
    },[]);


    return (
        <header className="bg-white-500 text-black-300 h-20 w-screen flex items-center justify-between px-6">

            <div className="flex items-center space-x-3">
                <img
                    src={energy_system}
                    alt="Logo"
                    className="h-12 w-12 rounded-full object-cover"
                />
            </div>

            <div className="flex items-center space-x-3 text-lg font-medium">
                <img
                    src={avatar}
                    alt="Manager"
                    className="h-12 w-12 rounded-full object-cover"
                />
                <span>Welcome, {username}!</span>
            </div>

        </header>
    );
}
