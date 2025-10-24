import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  css: {
    postcss: "./postcss.config.cjs",
  },
  server: {
    proxy: {
      "/auth": {
        target: "http://localhost:8082", // <- Auth Service
        changeOrigin: true,
        secure: false,
      },
      "/users": {
        target: "http://localhost:8083", // <- User Service
        changeOrigin: true,
        secure: false,
      },
      "/devices": {
        target: "http://localhost:8081", // <- Device Service
        changeOrigin: true,
        secure: false,
      },
    },
  },
});
