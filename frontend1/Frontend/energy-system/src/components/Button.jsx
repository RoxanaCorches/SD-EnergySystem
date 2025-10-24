import React from "react";

export default function Button({ children, onClick, type = "button", disabled = false }) {
    return (
        <button
            type={type}
            onClick={onClick}
            disabled={disabled}
            className="w-full bg-black text-white py-3 px-4 rounded-xl font-semibold hover:bg-gray-800 transform hover:scale-105 transition-all duration-200 shadow-lg hover:shadow-xl border-none cursor-pointer disabled:opacity-50 disabled:cursor-not-allowed"
            style={{ backgroundColor: "black", color: "white" }}
        >
            {children}
        </button>
    );
}