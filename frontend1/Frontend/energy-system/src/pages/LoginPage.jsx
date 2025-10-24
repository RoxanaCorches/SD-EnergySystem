import React, { useState } from "react";
import { login } from "../services/authService";
import { useNavigate } from "react-router-dom";
import Button from "../components/Button.jsx";

export default function LoginPage() {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const data = await login(username, password);
      console.log("Login success:", data);
      
      // Salvez token-ul și rolul în localStorage
      localStorage.setItem("token", data.token);
      localStorage.setItem("role", data.role);

      // Redirecționez în funcție de rol
      if (data.role === "ADMIN") {
       navigate("/admin");
      } else if (data.role === "CLIENT") {
        navigate("/client");
      } else {
        setError("Rol necunoscut");
      }
      // eslint-disable-next-line no-unused-vars
    } catch (err) {
      setError("Username sau parolă incorectă");
    } finally {
      setLoading(false);
    }
  };

  /*
  const handleRegisterClick = () => {
    // Verific dacă utilizatorul este admin
    const role = localStorage.getItem("role");
    if (role === "ADMIN") {
      window.location.href = "/register";
    } else {
      setError("Doar administratorii pot accesa pagina de înregistrare");
    }
  };
*/
  return (
    <div className="w-screen h-screen flex">
      {/* Left Container */}
      <div className="hidden lg:flex w-1/2 h-full bg-[#F8F8F8] items-center justify-center">
        <div className="text-center">
          <h1 className="text-4xl font-bold text-gray-800 mb-4">Energy System</h1>
          <p className="text-lg text-gray-600 mb-8">Manage your energy consumption efficiently</p>
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

      {/* Right Container - Login Form */}
      <div className="w-full lg:w-1/2 h-full bg-white flex items-center justify-center p-8">
        <div className="w-full max-w-md">
          <div className="text-center mb-8">
            <h2 className="text-3xl font-bold text-gray-800 mb-2">Welcome Back!</h2>
            <p className="text-gray-600">Sign in to your account</p>
          </div>
          
          <form onSubmit={handleSubmit} className="space-y-6">
            {error && (
              <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded-xl">
                {error}
              </div>
            )}
            
            <div>
                <label htmlFor="username" className="block text-gray-800 mb-2 text-3lg font-bold">Username</label>
              <input
                type="text"
                placeholder="Enter username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                className="w-full px-4 py-3 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 placeholder-gray-400"
                required
                disabled={loading}
              />
            </div>
            
            <div>
                <label htmlFor="password" className="block text-gray-800 mb-2 text-3lg font-bold">Password</label>
              <input
                type="password"
                placeholder="Enter password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="w-full px-4 py-3 border border-gray-200 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 placeholder-gray-400"
                required
                disabled={loading}
              />
            </div>

            <Button type="submit" disabled={loading}>
              {loading ? "Loading..." : "Login"}
            </Button>
          </form>
        </div>
      </div>
    </div>
  );
}