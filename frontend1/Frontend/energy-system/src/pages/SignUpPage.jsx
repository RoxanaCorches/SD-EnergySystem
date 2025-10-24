import React, { useState } from "react";
import Button from "../components/Button.jsx";

export default function RegisterPage() {
    const [name, setName] = useState("");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [address, setAddress] = useState("");
    const [age, setAge] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setLoading(true);
        // TODO: adaugă logica de submit
    };

    return (

        <div className="w-screen h-screen flex">
            {/* container stanga*/}
            <div className="hidden lg:flex w-1/2 h-full bg-[#F8F8F8] items-center justify-center">
                <div className="text-center px-8">
                    <h1 className="text-5xl font-bold text-gray-800 mb-4">Energy System</h1>
                    <p className="text-lg text-gray-600 mb-8">
                        Manage your energy consumption efficiently
                    </p>
                    <div className="space-y-3">
                        <div className="flex items-center justify-center space-x-3">
                            <div className="w-3 h-3 bg-green-500 rounded-full"></div>
                            <span className="text-gray-700">Real-time monitoring</span>
                        </div>
                        <div className="flex items-center justify-center space-x-3">
                            <div className="w-3 h-3 bg-yellow-500 rounded-full"></div>
                            <span className="text-gray-700">Smart analytics</span>
                        </div>
                        <div className="flex items-center justify-center space-x-3">
                            <div className="w-3 h-3 bg-red-500 rounded-full"></div>
                            <span className="text-gray-700">Energy optimization</span>
                        </div>
                    </div>
                </div>
            </div>



            {/* Right Side - Form */}
            <div className="w-full lg:w-1/2 h-full bg-white flex items-center justify-center p-8">
                <div className="w-full max-w-lg">
                    <div className="text-center mb-8">
                        <h2 className="text-4xl font-bold text-gray-800 mb-2">Create Account</h2>
                        <p className="text-gray-600">Complete the form to sign up</p>
                    </div>

                    <form onSubmit={handleSubmit} className="space-y-6">
                        {error && (
                            <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded-xl">
                                {error}
                            </div>
                        )}

                        {/* Grid layout for desktop */}
                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div>
                                <label className="block text-gray-700 mb-1 font-medium">Name</label>
                                <input
                                    type="text"
                                    placeholder="Enter name"
                                    value={name}
                                    onChange={(e) => setName(e.target.value)}
                                    className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
                                    required
                                    disabled={loading}
                                />
                            </div>

                            <div>
                                <label className="block text-gray-700 mb-1 font-medium">Username</label>
                                <input
                                    type="text"
                                    placeholder="Enter username"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
                                    required
                                    disabled={loading}
                                />
                            </div>

                            <div>
                                <label className="block text-gray-700 mb-1 font-medium">Password</label>
                                <input
                                    type="password"
                                    placeholder="Enter password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
                                    required
                                    disabled={loading}
                                />
                            </div>

                            <div>
                                <label className="block text-gray-700 mb-1 font-medium">Email</label>
                                <input
                                    type="email"
                                    placeholder="Enter email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
                                    required
                                    disabled={loading}
                                />
                            </div>

                            <div>
                                <label className="block text-gray-700 mb-1 font-medium">Address</label>
                                <input
                                    type="text"
                                    placeholder="Enter address"
                                    value={address}
                                    onChange={(e) => setAddress(e.target.value)}
                                    className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
                                    required
                                    disabled={loading}
                                />
                            </div>

                            <div>
                                <label className="block text-gray-700 mb-1 font-medium">Age</label>
                                <input
                                    type="number"
                                    placeholder="Enter age"
                                    value={age}
                                    onChange={(e) => setAge(e.target.value)}
                                    className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
                                    required
                                    disabled={loading}
                                />
                            </div>
                        </div>

                        <Button type="submit" disabled={loading}>
                            {loading ? "Loading..." : "Sign up"}
                        </Button>
                    </form>
                </div>
            </div>
        </div>
    );
}
