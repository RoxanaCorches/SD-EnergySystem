const API_BASE = "http://localhost"; // dacă folosești gateway

// 🔹 LOGIN
export async function login(username, password) {
  try {
    const response = await fetch(`${API_BASE}/auth/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({ username, password }),
    });

    if (!response.ok) {
      if (response.status === 401) throw new Error("Credențiale invalide");
      throw new Error(`Eroare server (${response.status})`);
    }

    const data = await response.json();
    //console.log("Login successful:", data);
    const token = data.token;
    const user = data.authUserDTO;
    const role = data.authUserDTO.role;

    // Salvăm tokenul și rolul local
    localStorage.setItem("token", token);
    localStorage.setItem("role", role);
    localStorage.setItem("user", JSON.stringify(user));

    return { token, role, user };
  } catch (error) {
    console.error("Login error:", error);
    throw error;
  }
}


// 🔹 REGISTER
export async function register(name, username, password, email, address, age) {
  try {
    const response = await fetch(`${API_BASE}/auth/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({ name, username, password, email, address, age }),
    });

    if (!response.ok) {
      if (response.status === 401) throw new Error("Credențiale invalide");
      throw new Error(`Eroare server (${response.status})`);
    }

    const data = await response.json();
    //console.log("Login successful:", data);
    const token = data.token;
    const user = data.authUserDTO;
    const role = data.authUserDTO.role;

    // Salvăm tokenul și rolul local
    localStorage.setItem("token", token);
    localStorage.setItem("role", role);
    localStorage.setItem("user", JSON.stringify(user));

    return { token, role, user };
  } catch (error) {
    console.error("Login error:", error);
    throw error;
  }
}


// 🔹 LOGOUT
export function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
  localStorage.removeItem("user");
}

/*
// 🔹 UTILITARE
export function getToken() {
  return localStorage.getItem("token");
}

export function getRole() {
  return localStorage.getItem("role");
}

export function getUser() {
  const data = localStorage.getItem("user");
  return data ? JSON.parse(data) : null;
}

export function isAuthenticated() {
  return !!getToken();
}

export function isAdmin() {
  return getRole() === "ADMIN";
}

export function isClient() {
  return getRole() === "CLIENT";
}
*/